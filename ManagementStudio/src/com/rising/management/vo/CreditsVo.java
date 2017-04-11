package com.rising.management.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditsVo {
	private String phoneNumber;
	private Integer allScore;
	private String placeName;
	private double payMoney;
	private String registerTime;
	private String lastTradeTime;
	public CreditsVo(String phoneNumber, Date registerTime,
			Integer allScore, String placeName,double payMoney,String lastTradeTime) {
		this.phoneNumber = phoneNumber;
		this.registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(registerTime);
		this.allScore = allScore;
		this.placeName = placeName;
		this.payMoney = payMoney;
		if(lastTradeTime==null){
			this.lastTradeTime = "";
		}else{
			this.lastTradeTime = lastTradeTime;
		}
		
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getAllScore() {
		return allScore;
	}
	public void setAllScore(Integer allScore) {
		this.allScore = allScore;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getLastTradeTime() {
		return lastTradeTime;
	}
	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

}
