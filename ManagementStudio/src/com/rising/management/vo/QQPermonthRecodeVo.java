package com.rising.management.vo;

import java.text.SimpleDateFormat;
import com.rising.management.bean.QQPermonthRecode;

public class QQPermonthRecodeVo {
	private Integer id;
	private String phoneNumber;
	private String QQNumber;
	private String checkTime;
	private Integer sendScore;
	private float cost;
	private float payMoney;
	private String status;
	private String month;

	public QQPermonthRecodeVo(QQPermonthRecode pr) {
		this.id = pr.getId();
		this.phoneNumber = pr.getPhoneNumber();
		this.QQNumber = pr.getQQNumber();
		this.checkTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pr
				.getCheckTime());
		this.sendScore = pr.getSendScore();
		this.cost = pr.getCost();
		this.payMoney = pr.getPayMoney();
		this.status = pr.getStatus();
		this.month = pr.getMonth();
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

	public String getQQNumber() {
		return QQNumber;
	}

	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(float payMoney) {
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

}
