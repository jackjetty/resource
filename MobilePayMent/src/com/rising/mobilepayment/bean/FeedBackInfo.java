package com.rising.mobilepayment.bean;

import java.util.Date;

public class FeedBackInfo {
	private Integer FBId;
	private String PhoneNumber;
	private String ContactNumber;
	private String Email;
	private String FBContent;
	private Date FBTime;

	public Integer getFBId() {
		return FBId;
	}

	public void setFBId(Integer fBId) {
		FBId = fBId;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFBContent() {
		return FBContent;
	}

	public void setFBContent(String fBContent) {
		FBContent = fBContent;
	}

	public Date getFBTime() {
		return FBTime;
	}

	public void setFBTime(Date fBTime) {
		FBTime = fBTime;
	}

}
