package com.rising.management.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.QQPerMonthStatusService;
import com.rising.management.service.QQPermonthRecodeService;

@Controller("qqPermonthRecodeAction")
public class QQPermonthRecodeAction {
	private Integer pageSize;

	private Integer pageIndex;

	private String phoneNumber;

	private String startTime;

	private String endTime;
	
	private String status;

	private HashMap<String, Object> resultMap;

	@Autowired
	QQPermonthRecodeService qqPermonthRecodeService;
	
	@Autowired
	QQPerMonthStatusService qQPerMonthStatusService;

	Log log = LogFactory.getLog(QQPermonthRecodeAction.class);

	public String doQQPermonthRecode() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}

	public String getQQPermonthRecode() {
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		resultMap = qqPermonthRecodeService.getQQPermonthRecode(phoneNumber,
				startTime, endTime, pageSize, pageIndex);
		return "success";
	}
	
	public String doQQPermonthStatus(){
		return "success";
	}
	
	public String getQQPermonthStatus(){
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		if("null".equals(status)){
			status = null;
		}
		resultMap = qQPerMonthStatusService.getStatus(phoneNumber,status,pageSize,pageIndex);
		return "success";
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
