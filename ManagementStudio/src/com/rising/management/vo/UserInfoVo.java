package com.rising.management.vo;

public class UserInfoVo {

	// 手机号吗
	private String phoneNumber;

	// 归属地
	private String address;

	// 注册时间 yyyy-MM-dd HH:mm:ss
	private String registerTime;

	// 当前积分
	private Float allScore;

	// 总充值笔数
	private Integer allOrder;

	// 成功充值笔数
	private Integer successOrder;

	// 失败充值笔数
	private Integer failedOrder;

	// 总充值金额
	private Float payMoney;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getAllOrder() {
		return allOrder;
	}

	public void setAllOrder(Integer allOrder) {
		this.allOrder = allOrder;
	}

	public Float getAllScore() {
		return allScore;
	}

	public void setAllScore(Float allScore) {
		this.allScore = allScore;
	}

	public Integer getSuccessOrder() {
		return successOrder;
	}

	public void setSuccessOrder(Integer successOrder) {
		this.successOrder = successOrder;
	}

	public Integer getFailedOrder() {
		return failedOrder;
	}

	public void setFailedOrder(Integer failedOrder) {
		this.failedOrder = failedOrder;
	}

	public Float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}

}
