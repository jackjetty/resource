package com.rising.management.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserVo {
	private String phoneNumber;
	private String registerTime;
	private Integer successTimes;
	private Integer failedTimes;
	private String lastTradeTime;

	public NewUserVo(String phoneNumber, Date registerTime,
			Integer successTimes, Integer failedTimes,String lastTradeTime) {
		this.phoneNumber = phoneNumber;
		this.registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(registerTime);
		this.successTimes = successTimes;
		this.failedTimes = failedTimes;
		if(lastTradeTime==null){
			this.lastTradeTime = "";
		}else{
			this.lastTradeTime = lastTradeTime;
		}
		
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getSuccessTimes() {
		return successTimes;
	}

	public void setSuccessTimes(Integer successTimes) {
		this.successTimes = successTimes;
	}

	public Integer getFailedTimes() {
		return failedTimes;
	}

	public void setFailedTimes(Integer failedTimes) {
		this.failedTimes = failedTimes;
	}

	public String getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

}
