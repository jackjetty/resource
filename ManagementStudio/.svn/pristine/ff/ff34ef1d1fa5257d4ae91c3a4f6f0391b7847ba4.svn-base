package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.AppStartPicture;


public interface AppStartPictureDao {
	public Integer getAppStartPictureSize(String pictureName, String startTime, String endTime);

	public ArrayList<AppStartPicture> getAppStartPicture(String pictureName, String startTime, String endTime, Integer start,
			Integer pageSize);
	
	public void addAppStartPicture(AppStartPicture asp);
	
	public void updateAppStartPicture(AppStartPicture asp);
	
	public void removeAppStartPicture(ArrayList<Integer> ids);
	
	public AppStartPicture getAppStartPictureById(Integer id);
}
