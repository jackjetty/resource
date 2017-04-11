package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.UserCheckinService;

@Controller("userCheckinAction")
public class UserCheckinAction {
	Log log = LogFactory.getLog(UserCheckinAction.class);
	private Integer pageSize;
	private Integer pageIndex;
	private HashMap<String, Object> resultMap;
	private String phoneNumber;
	private String checkinTime;
	
	@Autowired
	UserCheckinService userCheckinService;
	
	
	
	public String doUserCheckin(){
		return "success";
	}
	
	public String getUserCheckin(){
		resultMap=userCheckinService.getUserCheckin(phoneNumber, checkinTime, pageSize, pageIndex);
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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}
	
	
	
	
}
