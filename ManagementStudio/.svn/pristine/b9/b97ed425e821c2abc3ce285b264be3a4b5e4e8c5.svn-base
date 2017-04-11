package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Business;

public interface BusinessDao {

	public ArrayList<Business> getBusiness();

	public ArrayList<Business> getBusinessInfo(Integer busId, Integer start,
			Integer pageSize);
	public Integer getBusinessInfoListSize(Integer busId);

	public void deleteById(Integer lastId);

	public ArrayList<Business> findAll();

	public void addBusiness(Business b);

	public void updateProduct(Business b);
	
	public HashMap<String,Object> getBusIdAndBusName();

	public ArrayList<String> getBtype(Integer busId);
	
	public HashMap<String,Object> getBtypeFeemethod(Integer busId);
	
	public HashMap<String,Object> getBtypeFeemethodByplace();

	public ArrayList<String> getBtype();

	public ArrayList<String> getBtype2(Integer busId);

}
