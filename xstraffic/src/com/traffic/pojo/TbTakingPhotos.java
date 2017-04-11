package com.traffic.pojo;

import java.util.Date;

public class TbTakingPhotos {
	private String takingPhotosId;
	private String reportOpenId;
	private Date reportTime;
	private String reportPhoneNumber;
	private String address;
	private String locationX;
	private String locationY;
	private String handyPhotoState;
	private String accepter;
	private Date acceptTime;
	private String remark;
	private String dealResult;
	public TbTakingPhotos() {

	}
	public TbTakingPhotos(String takingPhotosId, String reportOpenId,
			Date reportTime, String reportPhoneNumber, String address,
			String locationX, String locationY, String handyPhotoState,
			String accepter, Date acceptTime, String remark, String dealResult) {
		this.takingPhotosId = takingPhotosId;
		this.reportOpenId = reportOpenId;
		this.reportTime = reportTime;
		this.reportPhoneNumber = reportPhoneNumber;
		this.address = address;
		this.locationX = locationX;
		this.locationY = locationY;
		this.handyPhotoState = handyPhotoState;
		this.accepter = accepter;
		this.acceptTime = acceptTime;
		this.remark = remark;
		this.dealResult = dealResult;
	}


	public String getTakingPhotosId() {
		return takingPhotosId;
	}

	public void setTakingPhotosId(String takingPhotosId) {
		this.takingPhotosId = takingPhotosId;
	}

	public String getReportOpenId() {
		return reportOpenId;
	}

	public void setReportOpenId(String reportOpenId) {
		this.reportOpenId = reportOpenId;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

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

	public String getHandyPhotoState() {
		return handyPhotoState;
	}

	public void setHandyPhotoState(String handyPhotoState) {
		this.handyPhotoState = handyPhotoState;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

}
