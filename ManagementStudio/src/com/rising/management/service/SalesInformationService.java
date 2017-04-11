package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;


import com.rising.management.bean.SalesInformation;

public interface SalesInformationService {
	public HashMap<String, Object> getSalesInfo(String actName, String actStartTime, String actEndTime,
			Integer pageSize, Integer pageIndex);
	public ArrayList<SalesInformation> getSalesInformationByactName(String actName);
	public Integer getSalesListSize(String actName, String actStartTime, String actEndTime);
	public ArrayList<SalesInformation> getSalesInformationAll();
	public HashMap<String, Object> addSalesInformation(SalesInformation sales);

	public HashMap<String, Object> updateSalesInformation(SalesInformation sale);
	
	public ArrayList<SalesInformation> getSalesInfoBySortCode(Integer sortCode);
	
	public ArrayList<SalesInformation> getSalesInfoByInforId(Integer inforId);
	
	public HashMap<String,Object> removeSalesInformation(String inforIds);
	
	public HashMap<String,Object> changeSortCode(Integer inforId1,Integer sortCode1,Integer inforId2,Integer sortCode2);
	
	public Integer getMaxSortCode();
}
