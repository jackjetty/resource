package com.rising.management.dao;

import java.util.ArrayList;
import com.rising.management.bean.BackInfo;

public interface BackInfoDao {
	public Integer getBackInfoListSize(String email,String phoneNumber);
	public ArrayList<BackInfo> findByPhoneNumber(String email, String phoneNumber, Integer start, Integer pageSize);
	public void modifyStatus(String status,Integer fbid);
	public void saveprocedureMessage(String procedureMessage,Integer fbid);
}
