package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.AppUserInfoService;

@Controller("appUserInfoAction")
public class AppUserInfoAction {

	Log log = LogFactory.getLog(AppUserInfoAction.class);

	private Integer pageSize;

	private Integer pageIndex;

	private String phoneNumber;

	private String userName;

	private String address;

	private String startTime;
	
	private String endTime;
	
	private Boolean noOrder;

	@Autowired
	AppUserInfoService appUserInfoService;

	private HashMap<String, Object> resultMap;

	public String doAppUserInfo() {
		return "success";
	}

	public String getAppUserInfo() {
		resultMap = appUserInfoService.getAppUserInfo(phoneNumber, address,startTime,endTime,noOrder,
				pageSize, pageIndex);
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Boolean getNoOrder() {
		return noOrder;
	}

	public void setNoOrder(Boolean noOrder) {
		this.noOrder = noOrder;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
