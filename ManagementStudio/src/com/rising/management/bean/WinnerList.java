package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_WinnerList")
public class WinnerList {
	private Integer id;
	private String phoneNumber;
	private Date winTime;
	private String sortDetail;
	private String prize;
	private String place;
	private String Manager;
	private String sortId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "WinnerListIdNext")
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

	public Date getWinTime() {
		return winTime;
	}

	public void setWinTime(Date winTime) {
		this.winTime = winTime;
	}

	public String getSortDetail() {
		return sortDetail;
	}

	public void setSortDetail(String sortDetail) {
		this.sortDetail = sortDetail;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getManager() {
		return Manager;
	}

	public void setManager(String manager) {
		Manager = manager;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

}
