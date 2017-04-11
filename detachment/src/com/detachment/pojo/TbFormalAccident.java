package com.detachment.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TbFormalAccident entity. @author MyEclipse Persistence Tools
 */

public class TbFormalAccident implements java.io.Serializable {

	// Fields

	private String formlAccidentId;
	private String reportOpenId;
	private Date reportTime;
	private String reportPhoneNumber;
	private String reporterType;
	private String address;
	private String locationX;
	private String locationY;
	private String accidentState;
	private Date acceptTime;
	private String accepter;
	private String remark;
	private String departmentId;

	private String nickname;
	private String realName;
	private String reportTimeString;
	private String textInfo;
	private String pictureInfo;
	private Boolean emergencyCall;

	// Constructors

	public Boolean isEmergencyCall() {
		return emergencyCall;
	}

	public void setEmergencyCall(Boolean emergencyCall) {
		this.emergencyCall = emergencyCall;
	}

	/** default constructor */
	public TbFormalAccident() {
	}

	/** minimal constructor */
	public TbFormalAccident(String formlAccidentId) {
		this.formlAccidentId = formlAccidentId;
	}

	public TbFormalAccident(String formlAccidentId, Date reportTime,
			String reportPhoneNumber, String reporterType, String address,
			String locationX, String locationY, String accidentState,
			String departmentId, String realName, String nickname,
			String accepter, Boolean emergencyCall) {
		this.formlAccidentId = formlAccidentId;
		this.reportTime = reportTime;
		this.reportPhoneNumber = reportPhoneNumber;
		this.reporterType = reporterType;
		this.address = address;
		this.locationX = locationX;
		this.locationY = locationY;
		this.accidentState = accidentState;
		this.departmentId = departmentId;
		this.nickname = nickname;
		this.realName = realName;
		this.reportTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(reportTime);
		this.accepter = accepter;
		this.emergencyCall = emergencyCall;
	}

	/** full constructor */
	public TbFormalAccident(String formlAccidentId, String reportOpenId,
			Timestamp reportTime, String reportPhoneNumber,
			String reporterType, String address, String locationX,
			String locationY, String accidentState, String accepter,
			String remark, String departmentId, boolean emergencyCall) {
		this.formlAccidentId = formlAccidentId;
		this.reportOpenId = reportOpenId;
		this.reportTime = reportTime;
		this.reportPhoneNumber = reportPhoneNumber;
		this.reporterType = reporterType;
		this.address = address;
		this.locationX = locationX;
		this.locationY = locationY;
		this.accidentState = accidentState;
		this.accepter = accepter;
		this.remark = remark;
		this.departmentId = departmentId;
		this.emergencyCall = emergencyCall;
	}

	// Property accessors

	public String getFormlAccidentId() {
		return this.formlAccidentId;
	}

	public void setFormlAccidentId(String formlAccidentId) {
		this.formlAccidentId = formlAccidentId;
	}

	public String getReportOpenId() {
		return this.reportOpenId;
	}

	public void setReportOpenId(String reportOpenId) {
		this.reportOpenId = reportOpenId;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
		this.reportTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(reportTime);
	}

	public String getReportPhoneNumber() {
		return this.reportPhoneNumber;
	}

	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}

	public String getReporterType() {
		return this.reporterType;
	}

	public void setReporterType(String reporterType) {
		this.reporterType = reporterType;
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

	public String getAccidentState() {
		return this.accidentState;
	}

	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}

	public String getAccepter() {
		return this.accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getReportTimeString() {
		return reportTimeString;
	}

	public void setReportTimeString(String reportTimeString) {
		this.reportTimeString = reportTimeString;
	}

	public String getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}

	public String getPictureInfo() {
		return pictureInfo;
	}

	public void setPictureInfo(String pictureInfo) {
		this.pictureInfo = pictureInfo;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Boolean getEmergencyCall() {
		return emergencyCall;
	}

}