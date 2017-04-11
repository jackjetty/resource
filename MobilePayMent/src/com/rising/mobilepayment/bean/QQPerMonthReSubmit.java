package com.rising.mobilepayment.bean;

import java.util.Date;

public class QQPerMonthReSubmit {

	private Integer id;
	private String phoneNumber;
	private String productId;
	private Float payMoney;
	private Date submitTime;
	private String qqNumber;
	private String submitResult;

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(String submitResult) {
		this.submitResult = submitResult;
	}

}
