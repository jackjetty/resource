package com.detachment.json;

import java.util.List;

public class AccidentBean {
	private String reportPhoneNumber;
	private String accidentid;
	private String address;
	private String locationX;
	private String locationY;
	private List<String> picUrl;
	public String getReportPhoneNumber() {
		return reportPhoneNumber;
	}
	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	public List<String> getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(List<String> picUrl) {
		this.picUrl = picUrl;
	}
	public String getAccidentid() {
		return accidentid;
	}
	public void setAccidentid(String accidentid) {
		this.accidentid = accidentid;
	}

}
