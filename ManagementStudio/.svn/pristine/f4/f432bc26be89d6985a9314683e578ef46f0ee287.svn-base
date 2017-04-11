package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.WinnerList;

public class WinnerListVo {

	private Integer id;
	private String phoneNumber;
	private String winTime;
	private String sortDetail;
	private String prize;
	private String place;
	private String Manager;
	private String sortId;

	
	public WinnerListVo(WinnerList winner){
		this.id = winner.getId();
		this.phoneNumber = winner.getPhoneNumber();
		this.Manager = winner.getManager();
		this.place = winner.getPlace();
		this.prize = winner.getPrize();
		this.sortDetail = winner.getSortDetail();
		this.sortId = winner.getSortId();
		this.winTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(winner.getWinTime());
	}
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

	public String getWinTime() {
		return winTime;
	}

	public void setWinTime(String winTime) {
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
