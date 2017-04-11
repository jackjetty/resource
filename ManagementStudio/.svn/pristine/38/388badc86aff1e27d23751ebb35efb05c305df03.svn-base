package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.SalesInformation;
import com.rising.management.dao.SalesInformationDao;
import com.rising.management.service.SalesInformationService;

@Service("salesInformationService")
public class SalesInformationServiceImpl implements SalesInformationService{
	
	Log log = LogFactory.getLog(SalesInformationServiceImpl.class);
	
	@Autowired
	SalesInformationDao salesInformationDao;
	
	@Override
	public HashMap<String, Object> getSalesInfo(String actName, String actStartTime,
			String actEndTime, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(actName)){
			actName=null;
		}
		if("".equals(actStartTime)){
			actStartTime=null;
		}
		if("".equals(actEndTime)){
			actEndTime=null;
		}
		Integer listSize = salesInformationDao.getSalesListSize(actName, actStartTime, actEndTime);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<SalesInformation> am= salesInformationDao.getSalesInfo(actName, actStartTime, actEndTime, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", am);
		return resultMap;
	}

	@Override
	public ArrayList<SalesInformation> getSalesInformationByactName(
			String actName) {
		ArrayList<SalesInformation> am=salesInformationDao.getSalesInfoRmationByactName(actName);
		return am;
	}

	@Override
	public Integer getSalesListSize(String actName, String actStartTime,
			String actEndTime) {
		if("".equals(actName)){
			actName=null;
		}
		if("".equals(actStartTime)){
			actStartTime=null;
		}
		if("".equals(actEndTime)){
			actEndTime=null;
		}
		Integer in=salesInformationDao.getSalesListSize(actName, actStartTime, actEndTime);
		return in;
	}

	@Override
	public ArrayList<SalesInformation> getSalesInformationAll() {
		ArrayList<SalesInformation> listAll=salesInformationDao.getSalesInfoRmationAll();
		return listAll;
	}

	@Override
	public HashMap<String, Object> addSalesInformation(SalesInformation sales) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			sales.setSortCode(getMaxSortCode()+1);
			salesInformationDao.addSalesInformation(sales);
			result.put("respCode", 0);
			result.put("respInfo", "促销活动添加成功!");
		} catch (Exception e) {
			log.error("添加SalesInformation时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加促销信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateSalesInformation(SalesInformation sales) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			salesInformationDao.updateSalesInformation(sales);
			result.put("respCode", 0);
			result.put("respInfo", "促销活动修改成功!");
		} catch (Exception e) {
			log.error("修改SalesInformation时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respCode", -2);
			result.put("respInfo", "修改促销信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public ArrayList<SalesInformation> getSalesInfoBySortCode(Integer sortCode) {
		ArrayList<SalesInformation> am=salesInformationDao.getSalesInfoBySortCode(sortCode);
		return am;
	}

	@Override
	public ArrayList<SalesInformation> getSalesInfoByInforId(Integer inforId) {
		ArrayList<SalesInformation> am=salesInformationDao.getSalesInfoByInforId(inforId);
		return am;
	}

	@Override
	public HashMap<String, Object> removeSalesInformation(String inforIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> sales = new ArrayList<Integer>();
			String[] ids = inforIds.split(",");
			for (String id : ids) {
				try {
					Integer i = Integer.parseInt(id);
					sales.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			salesInformationDao.removeSalesInformation(sales);
			result.put("respCode", 0);
			result.put("respInfo", "记录删除成功!");
		} catch (Exception e) {
			log.error("删除SalesInformation时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除记录时出现异常!");
		}
		return result;
		
	}

	@Override
	public HashMap<String, Object> changeSortCode(Integer inforId1,
			Integer sortCode1, Integer inforId2, Integer sortCode2) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			salesInformationDao.changeSortCode(inforId1, sortCode2);
			salesInformationDao.changeSortCode(inforId2, sortCode1);
			result.put("respCode", 0);
			result.put("respInfo", "位置互换成功!");
		} catch (Exception e) {
			log.error("修改sortCode时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "修改sortCode时出现异常!");
		}
		
		return result;
	}

	@Override
	public Integer getMaxSortCode() {
		Integer aa=salesInformationDao.getMaxSortCode();
		 
		return aa;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
