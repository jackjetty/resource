package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.QQPerMonthStatus;
import com.rising.management.dao.QQPermonthStatusDao;
import com.rising.management.service.QQPerMonthStatusService;


@Service("qQPerMonthStatusService")
public class QQPerMonthStatusServiceImpl implements QQPerMonthStatusService {
	
	@Autowired QQPermonthStatusDao qQPermonthStatusDao;
	
	@Override
	public HashMap<String, Object> getStatus(String phoneNumber, String status,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = qQPermonthStatusDao.getQQPermonthStatusListSize(
				phoneNumber, status);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<QQPerMonthStatus> pr = qQPermonthStatusDao
				.getQQPermonthStatus(phoneNumber, status, pageSize,
						start);
		for (QQPerMonthStatus qqPerMonthStatus : pr) {
			qqPerMonthStatus.setStatus(getStatus(qqPerMonthStatus.getStatus()));
			if(qqPerMonthStatus.getCancelDate().indexOf("1900")!=-1){
				qqPerMonthStatus.setCancelDate("");
			}
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", pr);
		return resultMap;
	}
	
	public String getStatus(String status){
		Integer iStatus = Integer.parseInt(status);
		switch(iStatus){
		case 0:return "正在申请";
		case 1:return "已开通";
		case 2:return "已取消";
		case 3:return "已停止";
		case 4:return "已暂停";
		default:return "";
		}
	}

	

}
