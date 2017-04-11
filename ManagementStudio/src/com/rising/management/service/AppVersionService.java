package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.AppVersion;

public interface AppVersionService {

	public HashMap<String, Object> getAppVersionByPage(Integer pageIndex, Integer pageSize);

	public HashMap<String, Object> addAppVersion(AppVersion a);

	public HashMap<String, Object> deleteByIds(String appVIds);

	public HashMap<String, Object> updateAppVersion(AppVersion a);

	public HashMap<String, Object> exchangeStatusById(Integer appVId);
	
	

}
