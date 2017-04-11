package com.rising.management.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.rising.management.bean.OrderInfo;

public class OrderInfoVo {
	private String phoneNumber;
	private String productName;
	private String targetNumber;
	private String payStatus;
	private String orderTime;
	private String payReturnCode;
	private float payMoney;
	private float sendScore;
	private String place;
	private String os;
	private String client;
	private String version;

	public OrderInfoVo(OrderInfo orderInfo, HashMap<String, Object> map) {
		this.phoneNumber = orderInfo.getPhoneNumber();
		this.productName = (String) map.get(orderInfo.getProductId());
		this.targetNumber = orderInfo.getTargetNumber();
		this.payStatus = orderInfo.getPayStatus();
		if (orderInfo.getOrderTime() == null) {
			this.orderTime = null;
		} else {
			this.orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(orderInfo.getOrderTime());
		}
		this.payReturnCode = orderInfo.getPayReturnCode();
		this.payMoney = orderInfo.getPayMoney();
		this.sendScore = orderInfo.getSendScore();
		this.os = orderInfo.getOs();
		this.client = orderInfo.getClient();
		this.version = orderInfo.getVersion();

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductId(String productName) {
		this.productName = productName;
	}

	public String getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayReturnCode() {
		return payReturnCode;
	}

	public void setPayReturnCode(String payReturnCode) {
		this.payReturnCode = payReturnCode;
	}

	public float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}

	public float getSendScore() {
		return sendScore;
	}

	public void setSendScore(float sendScore) {
		this.sendScore = sendScore;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
