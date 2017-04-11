package com.rising.mobilepayment.bean;

import java.util.Date;

public class UserPayLimit {
	private Integer id;
	private Integer limitId;
	private String phoneNumber;
	private Float hasPayed;
	private Date lastPayTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLimitId() {
		return limitId;
	}

	public void setLimitId(Integer limitId) {
		this.limitId = limitId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Float getHasPayed() {
		return hasPayed;
	}

	public void setHasPayed(Float hasPayed) {
		this.hasPayed = hasPayed;
	}

	public Date getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(Date lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

}
