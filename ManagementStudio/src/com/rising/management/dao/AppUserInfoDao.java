package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.AppUserInfo;



public interface AppUserInfoDao {
	public Integer getAppUserInfoSize(String phoneNumber,String address);

	public ArrayList<AppUserInfo> getAppUserInfo(String phoneNumber,String address, Integer start,Integer pageSize);
	
	public void addAppUserInfo(AppUserInfo au);
	
	public void updateAppUserInfo(AppUserInfo au);
	
	public void removeAppUserInfo(ArrayList<Integer> aus);
	
	public ArrayList<Object[]> getNoOrderAppUser(String phoneNumber,String address,Integer start,Integer pageSize);
	public Integer getNoOrderAppUserSize(String phoneNumber,String address);
}
