package com.rising.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.AppStartPicture;
import com.rising.management.dao.AppStartPictureDao;
import com.rising.management.service.AppStartPictureService;

@Service("appStartPictureService")
public class AppStartPictureServiceImpl implements AppStartPictureService{
	Log log = LogFactory.getLog(AppStartPictureServiceImpl.class);
	
	@Autowired
	AppStartPictureDao appStartPictureDao;
	
	@Override
	public HashMap<String, Object> getAppStartPicture(String pictureName,
			String startTime, String endTime, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(pictureName)){
			pictureName=null;
		}
		if("".equals(startTime)){
			startTime=null;
		}
		if("".equals(endTime)){
			endTime=null;
		}
		Integer listSize =appStartPictureDao.getAppStartPictureSize(pictureName, startTime, endTime);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<AppStartPicture> asp=appStartPictureDao.getAppStartPicture(pictureName, startTime, endTime,
				start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", asp);
		return resultMap;
	}

	@Override
	public Integer getAppStartPictureSize(String pictureName, String startTime,
			String endTime) {
		if("".equals(pictureName)){
			pictureName=null;
		}
		if("".equals(startTime)){
			startTime=null;
		}
		if("".equals(endTime)){
			endTime=null;
		}
		Integer listSize =appStartPictureDao.getAppStartPictureSize(pictureName, startTime, endTime);
		return listSize;
	}

	@Override
	public HashMap<String, Object> addAppStartPicture(AppStartPicture asp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			appStartPictureDao.addAppStartPicture(asp);
			result.put("respCode", 0);
			result.put("respInfo", "图片添加成功!");
		} catch (Exception e) {
			log.error("添加图片时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加图片时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateAppStartPicture(AppStartPicture asp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			appStartPictureDao.updateAppStartPicture(asp);
			result.put("respCode", 0);
			result.put("respInfo", "图片信息修改成功!");
		} catch (Exception e) {
			log.error("修改图片信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改图片信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removeAppStartPicture(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> is = new ArrayList<Integer>();
			String[] iss = ids.split(",");
			for (String id : iss) {
				try {
					Integer i = Integer.parseInt(id);
					is.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			appStartPictureDao.removeAppStartPicture(is);
			result.put("respCode", 0);
			result.put("respInfo", "图片信息删除成功!");
		} catch (Exception e) {
			log.error("删除图片信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除图片信息时出现异常!");
		}
		return result;
	}

	@Override
	public AppStartPicture getAppStartPictureById(Integer id) {
		AppStartPicture asp=appStartPictureDao.getAppStartPictureById(id);
		return asp;
	}

	@Override
	public HashMap<String, Object> changeOperate(Integer id) {
		AppStartPicture asp=appStartPictureDao.getAppStartPictureById(id);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(asp==null){
			map.put("respInfo","要操作的App开机图片已被删除了，请刷新后进行其他操作！");
			return map;
		}
		if("启用".equals(asp.getOperate())){
			asp.setOperate("结束");
			asp.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			appStartPictureDao.updateAppStartPicture(asp);
			map.put("respInfo","修改状态成功!");
		}else{
			asp.setOperate("启用");
			asp.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			appStartPictureDao.updateAppStartPicture(asp);
			map.put("respInfo","修改状态成功!");
		}
		return map;
	}

}
