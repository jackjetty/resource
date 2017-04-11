package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.AppUserInfo;
import com.rising.management.dao.AdvertisingWayDao;
import com.rising.management.dao.AppUserInfoDao;
import com.rising.management.dao.OperateLogDao;
import com.rising.management.dao.OrderInfoDao;
import com.rising.management.dao.PlaceDao;
import com.rising.management.service.AppUserInfoService;
import com.rising.management.vo.AppUserInfoVo;

@Service("appUserInfoService")
public class AppUserInfoServiceImpl implements AppUserInfoService {
	Log log = LogFactory.getLog(AppUserInfoServiceImpl.class);

	@Autowired
	AppUserInfoDao appUserInfoDao;

	@Autowired
	PlaceDao placoDao;

	@Autowired
	OrderInfoDao orderInfoDao;
	
	@Autowired
	AdvertisingWayDao advertisingWayDao;
	
	@Autowired
	OperateLogDao operateLogDao;

	@Override
	public HashMap<String, Object> getAppUserInfo(String phoneNumber,
			String address,String startTime,String endTime,Boolean noOrder,Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String operateType="UserRegister";
		if("".equals(phoneNumber)){
			phoneNumber=null;
		}
		if("".equals(address)){
			address=null;
		}
		ArrayList<AppUserInfoVo> aov=new ArrayList<AppUserInfoVo>();
		HashMap<String,Object> placeName=placoDao.getPlaceName();
		if("".equals(startTime)&&"".equals(endTime)&&noOrder==false){
			Integer listSize = appUserInfoDao.getAppUserInfoSize(phoneNumber, address);
			Integer start = (pageIndex - 1) * pageSize;
			ArrayList<AppUserInfo> am= appUserInfoDao.getAppUserInfo(phoneNumber, address, start, pageSize);
			HashMap<String,Object> map = advertisingWayDao.getAdvertisingMap();
			for(int i=0;am!=null &&i<am.size();i++){
				String os=operateLogDao.getOs(operateType,am.get(i).getPhoneNumber());
				String client=operateLogDao.getClient(operateType,am.get(i).getPhoneNumber());
				AppUserInfoVo appUserInfoVo=new AppUserInfoVo(am.get(i),placeName,map,os,client);
				String lastTradeTime = orderInfoDao.getLastTradeTime(appUserInfoVo.getPhoneNumber());
				if("null".equals(lastTradeTime)){
					appUserInfoVo.setLastTradeTime("");
				}else{
					appUserInfoVo.setLastTradeTime(lastTradeTime);
				}
				aov.add(appUserInfoVo);
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows", aov);
		}else if((!"".equals(startTime))||!"".equals(endTime)&&noOrder==false){
			if("".equals(startTime)){
				startTime =null;
			}
			if("".equals(endTime)){
				endTime = null;
			}
			Integer listSize = orderInfoDao.getAppUserInfoSize2(phoneNumber, address,startTime,endTime);
			Integer start = (pageIndex - 1) * pageSize;
			ArrayList<Object[]>ao = orderInfoDao.getAppUserInfo2(phoneNumber, address,startTime,endTime, start, pageSize);
			for (Object[] objects : ao) {
				String os=operateLogDao.getOs(operateType,(String)objects[0]);
				String client=operateLogDao.getClient(operateType,(String)objects[0]);
				AppUserInfoVo appUserInfoVo=new AppUserInfoVo(objects,placeName,os,client);
				aov.add(appUserInfoVo);
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows", aov);
		}else if("".equals(startTime)&&noOrder==true){
			Integer listSize = appUserInfoDao.getNoOrderAppUserSize(phoneNumber, address);
			Integer start = (pageIndex - 1) * pageSize;
			ArrayList<Object[]> am= appUserInfoDao.getNoOrderAppUser(phoneNumber, address, start, pageSize);
			for(Object[] objects : am){
				String os=operateLogDao.getOs(operateType,(String)objects[0]);
				String client=operateLogDao.getClient(operateType,(String)objects[0]);
				AppUserInfoVo appUserInfoVo=new AppUserInfoVo(objects,placeName,os,client);
				aov.add(appUserInfoVo);
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows", aov);
		}

		
		return resultMap;
	}

//	@Override
//	public Integer getAppUserInfoSize(String phoneNumber, String address) {
//		if ("".equals(phoneNumber)) {
//			phoneNumber = null;
//		}
//		if ("".equals(address)) {
//			address = null;
//		}
//
//		Integer in = appUserInfoDao.getAppUserInfoSize(phoneNumber, address);
//		return in;
//	}

	@Override
	public HashMap<String, Object> addAppUserInfo(AppUserInfo au) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			appUserInfoDao.addAppUserInfo(au);
			result.put("respCode", 0);
			result.put("respInfo", "信息添加成功!");
		} catch (Exception e) {
			log.error("添加信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}

		return result;
	}

	@Override
	public HashMap<String, Object> updateAppUserInfo(AppUserInfo au) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			appUserInfoDao.updateAppUserInfo(au);
			result.put("respCode", 0);
			result.put("respInfo", "信息修改成功!");
		} catch (Exception e) {
			log.error("修改信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改信息时出现异常!");
		}

		return result;
	}

	@Override
	public HashMap<String, Object> removeAppUserInfo(String userIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] ids = userIds.split(",");
			for (String id : ids) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			appUserInfoDao.removeAppUserInfo(ai);
			result.put("respCode", 0);
			result.put("respInfo", "信息删除成功!");
		} catch (Exception e) {
			log.error("删除信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除信息时出现异常!");
		}
		return result;
	}

}
