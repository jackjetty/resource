package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.UserCheckin;


public class UserCheckinVo {
	private Integer id;
	private String phoneNumber;
	private String checkinTime;
	private Integer sendScore;
	
	public UserCheckinVo(UserCheckin uc){
		this.id=uc.getId();
		this.phoneNumber=uc.getPhoneNumber();
		this.checkinTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(uc.getCheckinTime());
		this.sendScore=uc.getSendScore();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getSendScore() {
		return sendScore;
	}
	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}
	
	
}
