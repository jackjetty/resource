package com.detachment.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.CarMoveDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.UserDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbCarMove;
import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.TbWeiUser;
import com.detachment.pojo.vo.TbCarMoveVo;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.GpsToBaiDu;
import com.detachment.web.service.CarMoveService;

@Service("carMoveService")
public class CarMoveServiceImpl implements CarMoveService{
	@Autowired
	private CarMoveDao carMoveDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private WeiUserDao weiUserDao;
	
	@Autowired
	private InfoPicDao infoPicDao;
	
	
	
	@Override
	public HashMap<String, Object> getCarMove(String startTime, String endTime,
			String reportPhoneNumber, String carNumber, String carMoveState,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		 
		
		if(CommonUtil.trim(startTime).equalsIgnoreCase("")){
			startTime=CommonUtil.getBeforeWeekDateStrForm();
		}
		if(CommonUtil.trim(endTime).equalsIgnoreCase("")){
			endTime=CommonUtil.getCurrentDateStrForm();
		}
		reportPhoneNumber=CommonUtil.trim(reportPhoneNumber);
		carNumber=CommonUtil.trim(carNumber);
		carMoveState=CommonUtil.trim(carMoveState);
		
		
		
		String hql=" select count(ta.carMoveId) from TbCarMove as ta  ,TbWeiUser as tu     ";
		
		String StrOrder="";  
      
        
            
        String StrCdt=""; 
        
        ArrayList arraylist=new ArrayList();
        
        StrCdt=" where   (ta.reportOpenId=tu.openId) ";
        
        
        
        StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        StrCdt+="   (CONVERT(varchar(100), ta.reportTime, 23) between ? and ? ) ";
        arraylist.add(startTime); 
        arraylist.add(endTime); 
        
        if( reportPhoneNumber.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( ta.reportPhoneNumber like ? ) ";
            arraylist.add("%"+reportPhoneNumber+"%");  
        }
	    
        if( carNumber.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( ta.carNumber like ? ) ";
            arraylist.add("%"+carNumber+"%");  
        }
        
        if( carMoveState.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( ta.carMoveState like ? ) ";
            arraylist.add("%"+carMoveState+"%");  
        }
        
        Integer listSize=carMoveDao.findCount(hql+StrCdt, arraylist);
        
        StrOrder=" order by ta.reportTime desc"; 
        hql="select ta.carMoveId,ta.reportOpenId,ta.reportTime,ta.reportPhoneNumber " +
        		",ta.carNumber,ta.address,ta.locationX,ta.locationY,ta.carMoveState,ta.accepter,ta.acceptTime,ta.remark," +
        		"tu.nickname,tu.realName from TbCarMove as ta ,TbWeiUser as tu ";
	 
		List mResult=carMoveDao.findPage(hql+StrCdt+StrOrder, (pageIndex - 1) * pageSize, pageSize, arraylist);
        
		Object[] rows;
		String carMoveId;
		TbCarMoveVo tbCarMoveVo;
		List<TbCarMoveVo> carMoveVoList=new ArrayList<TbCarMoveVo>();
		for (Iterator iter = mResult.iterator(); iter.hasNext();) {
			  rows=(Object[]) iter.next();
			  tbCarMoveVo=new TbCarMoveVo();
			  carMoveId=CommonUtil.trim(rows[0]); 
			  tbCarMoveVo.setCarMoveId(carMoveId);
			  tbCarMoveVo.setReportOpenId(CommonUtil.trim(rows[1]));
			  tbCarMoveVo.setReportTime(CommonUtil.getDateForm(rows[2],"yyyy-MM-dd HH:mm:ss"));
			  tbCarMoveVo.setReportPhoneNumber(CommonUtil.trim(rows[3]));
			  tbCarMoveVo.setCarNumber(CommonUtil.trim(rows[4]));
			  tbCarMoveVo.setAddress(CommonUtil.trim(rows[5]));
			  tbCarMoveVo.setLocationX(CommonUtil.trim(rows[6]));
			  tbCarMoveVo.setLocationY(CommonUtil.trim(rows[7]));
			  tbCarMoveVo.setCarMoveState(CommonUtil.trim(rows[8]));
			  tbCarMoveVo.setAccepter(CommonUtil.trim(rows[9]));
			  tbCarMoveVo.setAcceptTime(CommonUtil.getDateForm(rows[10],"yyyy-MM-dd HH:mm:ss"));
			  tbCarMoveVo.setRemark(CommonUtil.trim(rows[11]));
			  tbCarMoveVo.setNickName(CommonUtil.trim(rows[12]));
			  tbCarMoveVo.setUserName(CommonUtil.trim(rows[13]));
			  
			  hql="select count(*) from TbInfoPic where processId =? and recordNo = ?"; 
			  tbCarMoveVo.setPicInfo(new Integer(infoPicDao.findCount(hql, Constant.CARMOVE,carMoveId)).toString());
			  carMoveVoList.add(tbCarMoveVo);  
		 } 
		resultMap.put("listSize", listSize);
		resultMap.put("rows",carMoveVoList);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId) {
		return carMoveDao.getCarMovePic(carMoveId);
		
	}

	@Override
	public HashMap<String, Object> getMoveCarAddress(String carMoveId) {
		ArrayList<Object[]> addr = carMoveDao.getMoveCarAddress(carMoveId);
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
		TbCarMove tc = carMoveDao.getMoveCarById(carMoveId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(tc==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
		}
		if(!"处理".equals(tc.getCarMoveState())){
			tc.setCarMoveState("处理");
			tc.setAcceptTime(new Timestamp(new Date().getTime()));
			tc.setAccepter(managerName);
			carMoveDao.exchangeStatus(tc);
			map.put("respInfo","成功处理自助移车!");
		}else{
			map.put("respInfo","该自助移车已处理!");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> saveFailInfo(String carMoveId,
			String managerName, String remark) {
		TbCarMove tc = carMoveDao.getMoveCarById(carMoveId);
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
		TbCarMove tbCarMove=carMoveDao.findById(carMoveId);
		String reportOpenId=CommonUtil.trim(tbCarMove.getReportOpenId()); 
		TbWeiUser tbWeiUser=weiUserDao.findById(reportOpenId);
		String nickname="";
		String username="";
		if(tbWeiUser!=null){
			nickname=CommonUtil.trim(tbWeiUser.getNickname());
			username=CommonUtil.trim(tbWeiUser.getRealName()); 		
		}  
		TbCarMoveVo tbCarMoveVo=new TbCarMoveVo(tbCarMove,username,nickname);
		return tbCarMoveVo;
	}

	@Override
	public HashMap<String, Object> saveprocedureMessage1(String managerName,
			String carMoveId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			carMoveDao.updateMoveCarStateSuccess(managerName, carMoveId);
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
			carMoveDao.updateMoveCarStateFail(carMoveId);
			map.put("respCode", 0);
			map.put("respInfo", "处理结果发送成功！");
		} catch (Exception e) {
			map.put("respCode", -1);
			map.put("respInfo", "处理结果发送失败！");
		}
		return map;
	}
	
}
