package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.PhoneRound;

public interface PhoneRoundService {
	public  HashMap<String,Object> getPhoneRoundService(Integer pageSize,Integer pageIndex);
	public HashMap<String,Object> addPhoneRound(PhoneRound pr);
	public HashMap<String,Object> updatePhoneRound(String status,Integer id);
	public HashMap<String, Object> deleteByIds(String ids);

}
