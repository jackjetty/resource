package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.PhoneMessage;




public interface PhoneMessageDao {
	public Integer getPhoneMessageSize(String use,String describe);

	public ArrayList<PhoneMessage> getPhoneMessage(String use,String describe, Integer start,Integer pageSize);
	
	public void addPhoneMessage(PhoneMessage pm);
	
	public void updatePhoneMessage(PhoneMessage pm);
	
	public void removePhoneMessage(ArrayList<Integer> pms);
}
