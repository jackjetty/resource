package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.AppStartPicture;

public interface AppStartPictureService {
	public HashMap<String, Object> getAppStartPicture(String pictureName,String startTime, 
			String endTime,Integer pageSize, Integer pageIndex);
	public Integer getAppStartPictureSize(String pictureName, String startTime,
			String endTime);
	public HashMap<String, Object> addAppStartPicture(AppStartPicture asp);
	public HashMap<String, Object> updateAppStartPicture(AppStartPicture asp);
	public HashMap<String, Object> removeAppStartPicture(String ids);
	public AppStartPicture getAppStartPictureById(Integer id);
	public HashMap<String, Object> changeOperate(Integer id);
}
