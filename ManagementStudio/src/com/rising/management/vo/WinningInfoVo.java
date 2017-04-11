package com.rising.management.vo;

import java.text.SimpleDateFormat;

public class WinningInfoVo {
	private String phoneNumber;
	private String name;
	private Integer amount;
	private Integer leftAmount;
	private String winTime;
	private String describe;
	private String hasSendMessage;
	private String detail;

	public WinningInfoVo(Object[] obj) {
		this.phoneNumber = (String) obj[0];
		this.name = (String) obj[1];
		this.winTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(obj[2]);
		this.describe = (String) obj[3];
		this.hasSendMessage = (String) obj[4];
		if("no".equals(hasSendMessage)){
			this.detail = "未发送";
		}else{
			this.detail = this.hasSendMessage;
		}

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}

	public String getWinTime() {
		return winTime;
	}

	public void setWinTime(String winTime) {
		this.winTime = winTime;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getHasSendMessage() {
		return hasSendMessage;
	}

	public void setHasSendMessage(String hasSendMessage) {
		this.hasSendMessage = hasSendMessage;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
