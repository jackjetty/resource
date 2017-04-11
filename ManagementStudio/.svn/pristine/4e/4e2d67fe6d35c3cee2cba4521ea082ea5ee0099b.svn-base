package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.PayLimit;
import com.rising.management.dao.PayLimitDao;
import com.rising.management.service.PayLimitService;
@Service("payLimitService")
public class PayLimitServiceImpl implements PayLimitService{
	Log log = LogFactory.getLog(PayLimitServiceImpl.class);
	@Autowired PayLimitDao payLimitDao;
	@Override
	public HashMap<String, Object> getPayLimit(String limitType,
			Integer pageSize, Integer pageIndex) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Integer listSize = payLimitDao.getPayLimitListSize(limitType);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<PayLimit> pl = payLimitDao.getPayLimit(limitType,start,pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", pl);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> addPayLimit(PayLimit p) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
	try{
		payLimitDao.addpayLimit(p);
		resultMap.put("respInfo", "添加系统限额信息成功！");
		resultMap.put("respCode", 0);
	}catch (Exception e) {
		log.error("添加积分时出现异常！"+e.toString());
		e.printStackTrace();
		resultMap.put("respCode", -1);
		resultMap.put("respInfo", "添加系统限额时出现异常！");
	}
	return resultMap;
	}

	@Override
	public HashMap<String, Object> removePayLimit(String limitIds) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String [] limitIdArray =limitIds.split(",");
		String lastId = "";
		try{
			for(String idObj : limitIdArray){
				lastId=idObj;
				if(!"".equals(idObj)){
					Integer limitId = Integer.parseInt(idObj);
					payLimitDao.deleteById(limitId); 
					resultMap.put("respInfo", "删除系统限额信息成功!");
				}
			}
		}catch(Exception e){
			log.error("删除id为"+lastId+"的系统限额信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除系统限额信息时出现异常!");
			
		}
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updatePayLimit(PayLimit p) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			payLimitDao.updatePayLimit(p);
			resultMap.put("respInfo", "更新系统限额信息成功！");
			resultMap.put("respCode", 0);
		}catch(Exception e){
			log.error("更新积分信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "更新系统限额信息时出现异常!");
			resultMap.put("respCode", -1);
			
		}
		return resultMap;
	}


}
