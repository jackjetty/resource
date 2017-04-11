package com.rising.mobilepayment.bean;

import java.util.Date;

public class QQPerMonthRecord {

	private Integer id;

	private String phoneNumber;

	private String QQNumber;

	private Date checkTime;

	private Integer sendScore;

	private Float cost;

	private Float payMoney;

	private String status;

	private String month;

	private Integer payMethodId;

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

	public String getQQNumber() {
		return QQNumber;
	}

	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(Integer payMethodId) {
		this.payMethodId = payMethodId;
	}

}
