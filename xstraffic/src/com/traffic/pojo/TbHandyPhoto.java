package com.traffic.pojo;

import java.sql.Timestamp;

/**
 * TbHandyPhoto entity. @author MyEclipse Persistence Tools
 */

public class TbHandyPhoto implements java.io.Serializable {

	// Fields

	private String handyPhotoId;
	private String reportOpenId;
	private Timestamp reportTime;
	private String reportPhoneNumber;
	private String address;
	private String locationX;
	private String locationY;
	private String handyPhotoState;
	private String accepter;
	private Timestamp acceptTime;
	private String remark;
	private String dealResult;

	// Constructors

	/** default constructor */
	public TbHandyPhoto() {
	}

	/** minimal constructor */
	public TbHandyPhoto(String handyPhotoId) {
		this.handyPhotoId = handyPhotoId;
	}

	/** full constructor */
	public TbHandyPhoto(String handyPhotoId, String reportOpenId,
			Timestamp reportTime, String reportPhoneNumber, String address,
			String locationX, String locationY, String handyPhotoState,
			String accepter, Timestamp acceptTime, String remark,
			String dealResult) {
		this.handyPhotoId = handyPhotoId;
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

	// Property accessors

	public String getHandyPhotoId() {
		return this.handyPhotoId;
	}

	public void setHandyPhotoId(String handyPhotoId) {
		this.handyPhotoId = handyPhotoId;
	}

	public String getReportOpenId() {
		return this.reportOpenId;
	}

	public void setReportOpenId(String reportOpenId) {
		this.reportOpenId = reportOpenId;
	}

	public Timestamp getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportPhoneNumber() {
		return this.reportPhoneNumber;
	}

	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocationX() {
		return this.locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return this.locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getHandyPhotoState() {
		return this.handyPhotoState;
	}

	public void setHandyPhotoState(String handyPhotoState) {
		this.handyPhotoState = handyPhotoState;
	}

	public String getAccepter() {
		return this.accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public Timestamp getAcceptTime() {
		return this.acceptTime;
	}

	public void setAcceptTime(Timestamp acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDealResult() {
		return this.dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

}