package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.AppVersion;


public interface AppVersionDao {

	public Integer getAppVersionListSize();

	public ArrayList<AppVersion> getAppVersion(Integer start, Integer pageSize);

	public void addAppVersion(AppVersion a);

	public void deleteById(int id);

	public void updateAppVersion(AppVersion a);

	public AppVersion getAppVersionById(Integer appVId);

}
