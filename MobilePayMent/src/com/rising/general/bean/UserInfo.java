/**
 * UserInfo.java
 * com.rising.mobilepayment.bean
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   下午5:27:06
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.general.bean;

import java.util.Date;

public class UserInfo {
	private Integer UserId;
	private String PhoneNumber;
	private String Password;
	private String UserName;
	private Date Birthday;
	private String Sex;
	private String QQNumber;
	private float AllScore;
	private String Email;
	private String Address;
	private Date RegisterTime;
	private String FromWay;
	private String UserStatus;

	public UserInfo() {
	}

	public UserInfo(String PhoneNumber, String Password) {
		this.PhoneNumber = PhoneNumber;
		this.Password = Password;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getQQNumber() {
		return QQNumber;
	}

	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}

	public float getAllScore() {
		return AllScore;
	}

	public void setAllScore(float allScore) {
		AllScore = allScore;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Date getRegisterTime() {
		return RegisterTime;
	}

	public void setRegisterTime(Date registerTime) {
		RegisterTime = registerTime;
	}

	public String getFromWay() {
		return FromWay;
	}

	public void setFromWay(String fromWay) {
		FromWay = fromWay;
	}

	public String getUserStatus() {
		return UserStatus;
	}

	public void setUserStatus(String userStatus) {
		UserStatus = userStatus;
	}

}
