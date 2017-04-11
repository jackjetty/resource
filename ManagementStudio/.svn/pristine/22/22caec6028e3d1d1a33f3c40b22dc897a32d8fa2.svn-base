package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.BackInfo;
import com.rising.management.dao.BackInfoDao;
import com.rising.management.service.BackInfoService;
import com.rising.management.vo.BackInfoVo;
@Service("BackInfoService")
public class BackInfoServiceImpl implements BackInfoService {
	Log log = LogFactory.getLog(BackInfoServiceImpl.class);
	@Autowired
	BackInfoDao backinfoDao;
	@Override
	public HashMap<String, Object> findByPhoneNumber(String email,String phoneNumber,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		if ("".equals(email)) {
			email = null;
		}
		Integer listSize = backinfoDao.getBackInfoListSize(email,phoneNumber);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<BackInfoVo> abv = new ArrayList<BackInfoVo>();
		ArrayList<BackInfo> ar = backinfoDao.findByPhoneNumber(email,phoneNumber, start, pageSize);
		for (BackInfo backInfo : ar) {
			abv.add(new BackInfoVo(backInfo));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", abv);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> modifyStatus(String status,Integer fbid ) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			backinfoDao.modifyStatus(status, fbid);
			resultMap.put("success", true);
			resultMap.put("respInfo", "修改状态成功！");
		} catch (Exception e) {
			log.error("修改状态时发生异常！" + e.toString());
			resultMap.put("respInfo", "修改状态时发生异常！");
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> saveprocedureMessage(
			String procedureMessage, Integer fbid) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			backinfoDao.saveprocedureMessage(procedureMessage, fbid);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "保存处理过程成功！");
		} catch (Exception e) {
			log.error("保存信息时发生异常！" + e.toString());
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "保存信息时发生异常！");
		}
		return resultMap;
	}
}
	


