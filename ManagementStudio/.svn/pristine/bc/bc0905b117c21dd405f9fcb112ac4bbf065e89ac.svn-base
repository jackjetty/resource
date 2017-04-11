package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.BackInfo;

public class BackInfoVo {
	private Integer fbid;
	private String phoneNumber;
	private String email;
	private String fbcontent;
	private String fbtime;
	private String contactnumber;
	private String status;
	private String procedureMessage;

	public BackInfoVo(BackInfo info) {
		this.fbid = info.getFbid();
		this.phoneNumber = info.getPhoneNumber();
		this.email = info.getEmail();
		this.fbcontent = info.getFbcontent();
		this.fbtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(info
				.getFbtime());
		;
		this.contactnumber = info.getContactnumber();
		this.status = info.getStatus();
		this.procedureMessage=info.getProcedureMessage();
	}

	public Integer getFbid() {
		return fbid;
	}

	public void setFbid(Integer fbid) {
		this.fbid = fbid;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFbcontent() {
		return fbcontent;
	}

	public void setFbcontent(String fbcontent) {
		this.fbcontent = fbcontent;
	}

	public String getFbtime() {
		return fbtime;
	}

	public void setFbtime(String fbtime) {
		this.fbtime = fbtime;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcedureMessage() {
		return procedureMessage;
	}

	public void setProcedureMessage(String procedureMessage) {
		this.procedureMessage = procedureMessage;
	}
	
}
