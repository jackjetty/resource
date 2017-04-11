package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.rising.management.bean.PhoneMessage;
import com.rising.management.dao.PhoneMessageDao;
import com.rising.management.service.PhoneMessageService;

@Service("phoneMessageService")
public class PhoneMessageServiceImpl implements PhoneMessageService{
	Log log = LogFactory.getLog(PhoneMessageServiceImpl.class);
	
	@Autowired
	PhoneMessageDao phoneMessageDao;
	

	@Override
	public HashMap<String, Object> getPhoneMessage(String use, String describe,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(use)){
			use=null;
		}
		if("".equals(describe)){
			describe=null;
		}

		Integer listSize = phoneMessageDao.getPhoneMessageSize(use, describe);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<PhoneMessage> am= phoneMessageDao.getPhoneMessage(use, describe, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", am);
		return resultMap;
	}

	@Override
	public Integer getPhoneMessageSize(String use, String describe) {
		if("".equals(use)){
			use=null;
		}
		if("".equals(describe)){
			describe=null;
		}

		Integer in = phoneMessageDao.getPhoneMessageSize(use, describe);
		return in;
	}

	@Override
	public HashMap<String, Object> addPhoneMessage(PhoneMessage pm) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			phoneMessageDao.addPhoneMessage(pm);
			result.put("respCode", 0);
			result.put("respInfo", "信息添加成功!");
		} catch (Exception e) {
			log.error("添加信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updatePhoneMessage(PhoneMessage pm) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			phoneMessageDao.updatePhoneMessage(pm);
			result.put("respCode", 0);
			result.put("respInfo", "信息修改成功!");
		} catch (Exception e) {
			log.error("修改信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removePhoneMessage(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] idss = ids.split(",");
			for (String id : idss) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			phoneMessageDao.removePhoneMessage(ai);
			result.put("respCode", 0);
			result.put("respInfo", "信息删除成功!");
		} catch (Exception e) {
			log.error("删除信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除信息时出现异常!");
		}
		return result;
	}



}
