package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_UserInfo")
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
	private Date RegisterTime;
	private String Address;

	public UserInfo() {
	}

	public UserInfo(String PhoneNumber, String Password) {
		this.PhoneNumber = PhoneNumber;
		this.Password = Password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "UserIdNext")
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

	public Date getRegisterTime() {
		return RegisterTime;
	}

	public void setRegisterTime(Date registerTime) {
		RegisterTime = registerTime;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
