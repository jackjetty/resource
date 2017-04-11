package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TbFormalAccidentVo {
	private String formlAccidentId;
	private String reportOpenId;
	private Date reportTime;
	private String reportPhoneNumber;
	private String reporterType;
	private String address;
	private String locationX;
	private String locationY;
	private String accidentState;
	private String accepter;
	private String remark;
	private String departmentId;
	private String department;
	private String emergencyCallString;
	private Boolean emergencyCall;

	private String nickname;
	private String realName;
	private String reportTimeString;
	private String textInfo;
	private String pictureInfo;

	public TbFormalAccidentVo() {
	}

	public TbFormalAccidentVo(String formlAccidentId, Date reportTime,
			String reportPhoneNumber, String reporterType, String address,
			String locationX, String locationY, String accidentState,
			String departmentId, String realName, String nickname,
			String accepter) {
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
	}

	public String getFormlAccidentId() {
		return formlAccidentId;
	}

	public void setFormlAccidentId(String formlAccidentId) {
		this.formlAccidentId = formlAccidentId;
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

	public String getReporterType() {
		return reporterType;
	}

	public void setReporterType(String reporterType) {
		this.reporterType = reporterType;
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

	public String getAccidentState() {
		return accidentState;
	}

	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getEmergencyCallString() {
		return emergencyCallString;
	}

	public void setEmergencyCallString(String emergencyCallString) {
		this.emergencyCallString = emergencyCallString;
	}

	public Boolean getEmergencyCall() {
		return emergencyCall;
	}

	public void setEmergencyCall(Boolean emergencyCall) {
		this.emergencyCall = emergencyCall;
		if(emergencyCall!=null &&emergencyCall){
			this.emergencyCallString = "需要出警";
		}else{
			this.emergencyCallString = "不需要";
		}
		
	}

}
