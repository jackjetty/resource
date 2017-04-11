package com.traffic.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.AccidentDao;
import com.traffic.dao.MoveCarDao;
import com.traffic.dao.TakingPhotosDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbTakingPhotos;
import com.traffic.pojovo.TbTakingPhotosVo;
import com.traffic.web.service.TakingPhotosService;

@Service("takingPhotosService")
public class TakingPhotosServiceImpl implements TakingPhotosService {
	@Autowired
	UserDao userDao;
	@Autowired
	TakingPhotosDao takingPhotosDao;
	@Autowired
	MoveCarDao moveCarDao;
	
	@Autowired
	AccidentDao accidentDao;
	@Override
	public HashMap<String, Object> getTakingPhotos(String startTime, String endTime,
			String takingPhoneNumber, String nickName,Integer pageSize, Integer pageIndex,String handyPhotoState) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(takingPhoneNumber)) {
			takingPhoneNumber = null;
		}
		if("".equals(nickName)){
			nickName = null;
		}
		if("".equals(handyPhotoState)){
			handyPhotoState = null;
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
		Integer listSize = takingPhotosDao.getTakingPhotosSize(startDate, endDate,
				takingPhoneNumber,nickName,handyPhotoState);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbTakingPhotos> ta = takingPhotosDao.getMoveCar(startDate,endDate,takingPhoneNumber, start, pageSize,nickName,handyPhotoState);
		ArrayList<TbTakingPhotosVo> tav = new ArrayList<TbTakingPhotosVo>();
		HashMap<String, Object> pic =  moveCarDao.getMovePic();
		HashMap<String,Object> userName = userDao.getUserName();
	 
		HashMap<String, Object> textContent = accidentDao.getTbAccidentText();
		for (int i = 0; ta != null && i < ta.size(); i++) {
			TbTakingPhotosVo order = new TbTakingPhotosVo(ta.get(i),pic,userName, textContent);
			tav.add(order);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",tav);
		resultMap.put("respCode", 0);
		
		return resultMap;
	}
	@Override
	public HashMap<String, Object> getTbAddress(String takingPhotosId) {
		ArrayList<Object[]> addr = takingPhotosDao.getTbAddress(takingPhotosId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		for (Object[] objs : addr) {
			resultMap.put("locationX", objs[0]);
			resultMap.put("locationY", objs[1]);
			resultMap.put("address", objs[2]);
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> updateState(String takingPhotosId,
			String managerName) {
		TbTakingPhotos tc = takingPhotosDao.getTpById(takingPhotosId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(tc==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
		}
		if(!"受理".equals(tc.getHandyPhotoState())){
			tc.setHandyPhotoState("受理");
			tc.setAcceptTime(new Date());
			tc.setAccepter(managerName);
			takingPhotosDao.updateState(tc);
			map.put("respInfo","成功处理随手拍!");
		}else{
			map.put("respInfo","该随手拍已处理!");
		}
		return map;
	
	}
}
