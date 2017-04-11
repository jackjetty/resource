package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.SalesInformation;





public interface SalesInformationDao {
	public Integer getSalesListSize(String actName, String actStartTime, String actEndTime);

	public ArrayList<SalesInformation> getSalesInfo(String actName, String actStartTime, String actEndTime, Integer start,
			Integer pageSize);
	
	public ArrayList<SalesInformation> getSalesInfoRmationByactName(String actName);
	
	public ArrayList<SalesInformation> getSalesInfoRmationAll();
	
	public void addSalesInformation(SalesInformation sales);
	
	public void updateSalesInformation(SalesInformation sales);
	
	public void removeSalesInformation(ArrayList<Integer> sales);
	
	public ArrayList<SalesInformation> getSalesInfoBySortCode(Integer sortCode);
	
	public ArrayList<SalesInformation> getSalesInfoByInforId(Integer inforId);
	
	public void changeSortCode(Integer inforId,Integer sortCode);
	
	public Integer getMaxSortCode();

}
