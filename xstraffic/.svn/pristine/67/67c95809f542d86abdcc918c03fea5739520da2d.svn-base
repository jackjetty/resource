package com.traffic.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.MoveCarDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbCarMove;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojovo.TbCarMoveVo;
import com.traffic.util.GpsToBaiDu;
import com.traffic.web.service.MoveCarService;

@Service("moveCarService")
public class MoveCarServiceImpl implements MoveCarService {
	@Autowired
	MoveCarDao moveCarDao;
	@Autowired
	UserDao userDao;

	@Override
	public HashMap<String, Object> getCarMove(String startTime, String endTime,
			String reportPhoneNumber, String carNumber, String carMoveState,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(reportPhoneNumber)) {
			reportPhoneNumber = null;
		}
		if ("".equals(carNumber)) {
			carNumber = null;
		}
		if ("".equals(carMoveState)) {
			carMoveState = null;
		}
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime.toString() + " 00:00:00");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime.toString() + " 23:59:59");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		Integer listSize = moveCarDao.getMoveCar(startDate, endDate,
				reportPhoneNumber, carNumber, carMoveState);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbCarMove> ta = moveCarDao.getMoveCar(startDate,endDate,reportPhoneNumber, carNumber,start, pageSize,carMoveState);
		ArrayList<TbCarMoveVo> tav = new ArrayList<TbCarMoveVo>();
		HashMap<String, Object> pic =  moveCarDao.getMovePic();
		HashMap<String,Object> userName = userDao.getUserName();
		 
		for (int i = 0; ta != null && i < ta.size(); i++) {
			TbCarMoveVo order = new TbCarMoveVo(ta.get(i),pic,userName);
			tav.add(order);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",tav);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId) {
		return moveCarDao.getCarMovePic(carMoveId);
		
	}

	@Override
	public HashMap<String, Object> getMoveCarAddress(String carMoveId) {
		ArrayList<Object[]> addr = moveCarDao.getMoveCarAddress(carMoveId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		double locationY=Double.parseDouble(addr.get(0)[0].toString());
		double locationX=Double.parseDouble(addr.get(0)[1].toString());
		String[] xy=GpsToBaiDu.ToBaiduAddress(locationX, locationY).split("==");
		resultMap.put("locationX", xy[0]);
		resultMap.put("locationY", xy[1]);
		resultMap.put("address", addr.get(0)[2]);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> exchangeStatus(String carMoveId,String managerName) {
		TbCarMove tc = moveCarDao.getMoveCarById(carMoveId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(tc==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
		}
		if(!"处理".equals(tc.getCarMoveState())){
			tc.setCarMoveState("处理");
			tc.setAcceptTime(new Timestamp(new Date().getTime()));
			tc.setAccepter(managerName);
			moveCarDao.exchangeStatus(tc);
			map.put("respInfo","成功处理自助移车!");
		}else{
			map.put("respInfo","该自助移车已处理!");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> saveFailInfo(String carMoveId,
			String managerName, String remark) {
		TbCarMove tc = moveCarDao.getMoveCarById(carMoveId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(tc==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
		}else{
			tc.setCarMoveState("失败");
			tc.setAcceptTime(new Timestamp(new Date().getTime()));
			tc.setAccepter(managerName);
			tc.setRemark(remark);
			map.put("respInfo","成功处理自助移车!");
		}
		
		return map;
	}
	
	
	@Override
	public TbCarMoveVo getCarMoveByCarMoveId(String carMoveId) {
		TbCarMove tcm=moveCarDao.getMoveCarById(carMoveId);
		HashMap<String,Object> userName = userDao.getUserName();
		 
		TbCarMoveVo tcmv=new TbCarMoveVo(tcm,userName );
		return tcmv;
	}

	@Override
	public HashMap<String, Object> saveprocedureMessage1(String managerName,
			String carMoveId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			moveCarDao.updateMoveCarStateSuccess(managerName, carMoveId);
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "保存审核结果时发生异常！");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> responseWei(String carMoveId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			moveCarDao.updateMoveCarStateFail(carMoveId);
			map.put("respCode", 0);
			map.put("respInfo", "处理结果发送成功！");
		} catch (Exception e) {
			map.put("respCode", -1);
			map.put("respInfo", "处理结果发送失败！");
		}
		return map;
	}
	

}
