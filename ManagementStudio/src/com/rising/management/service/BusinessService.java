package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Business;

public interface BusinessService {

	public ArrayList<Business> getBusiness();
	
	public HashMap<String, Object> getBusAndBtype();

	public HashMap<String, Object> getBusinessInfo(Integer busId, Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> updateBusiness(Business b);

	public HashMap<String, Object> removeBusiness(String busIds);

	public HashMap<String, Object> addBusiness(Business b);
	
}
