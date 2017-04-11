package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_FeedBackInfo")
public class BackInfo {
	private Integer fbid;
	private String phoneNumber;
	private String email;
	private String fbcontent;
	private Date fbtime;
	private String contactnumber;
	private String status;
	private String procedureMessage;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "FbIdNext")
	public Integer getFbid() {
		return fbid;
	}

	public BackInfo() {

	}

	public BackInfo(Integer fbid, String phoneNumber, String email,
			String fbcontent, Date fbtime, String contactnumber, String status) {
		super();
		this.fbid = fbid;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.fbcontent = fbcontent;
		this.fbtime = fbtime;
		this.contactnumber = contactnumber;
		this.status = status;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setFbid(Integer fbid) {
		this.fbid = fbid;
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

	public Date getFbtime() {
		return fbtime;
	}

	public void setFbtime(Date fbtime) {
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