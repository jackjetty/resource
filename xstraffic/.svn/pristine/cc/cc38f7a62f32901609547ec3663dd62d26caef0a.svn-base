package com.traffic.pojovo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbCarMove;

public class TbCarMoveVo {
	private String carMoveId;
	private String reportOpenId;
	private String reportTime;
	private String reportPhoneNumber;
	private String carNumber;
	private String address;
	private String locationX;
	private String locationY;
	private String carMoveState;
	private String accepter;
	private String remark;
	private String picInfo;
	private String userName;
	private String acceptTime;
	private String nickName;
	public TbCarMoveVo(TbCarMove tc, HashMap<String, Object> map1,HashMap<String, Object> map2 ) {
		this.carMoveId = tc.getCarMoveId();
		this.reportOpenId = tc.getReportOpenId();
		this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
				.getReportTime());
		if(tc.getAcceptTime()==null){
			this.acceptTime = null;
		}else{
			this.acceptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
					.getAcceptTime());
		}
		this.reportPhoneNumber = tc.getReportPhoneNumber();
		this.carNumber = tc.getCarNumber();
		this.address = tc.getAddress();
		this.locationX = tc.getLocationX();
		this.locationY = tc.getLocationY();
		this.carMoveState = tc.getCarMoveState();
		this.accepter = tc.getAccepter();
		this.remark = tc.getRemark();
		this.picInfo = (String) map1.get(String.valueOf(tc.getCarMoveId()));
		this.userName = (String) map2.get(String.valueOf(tc.getReportOpenId()));
		 
	}
	
	
	public TbCarMoveVo(TbCarMove tc, HashMap<String, Object> map2 ) {
		this.carMoveId = tc.getCarMoveId();
		this.reportOpenId = tc.getReportOpenId();
		this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
				.getReportTime());
		if(tc.getAcceptTime()==null){
			this.acceptTime = null;
		}else{
			this.acceptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
					.getAcceptTime());
		}
		this.reportPhoneNumber = tc.getReportPhoneNumber();
		this.carNumber = tc.getCarNumber();
		this.address = tc.getAddress();
		this.locationX = tc.getLocationX();
		this.locationY = tc.getLocationY();
		this.carMoveState = tc.getCarMoveState();
		this.accepter = tc.getAccepter();
		this.remark = tc.getRemark();
		this.userName = (String) map2.get(String.valueOf(tc.getReportOpenId())); 
	}

	public String getCarMoveId() {
		return carMoveId;
	}

	public void setCarMoveId(String carMoveId) {
		this.carMoveId = carMoveId;
	}

	public String getReportOpenId() {
		return reportOpenId;
	}

	public void setReportOpenId(String reportOpenId) {
		this.reportOpenId = reportOpenId;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportPhoneNumber() {
		return reportPhoneNumber;
	}

	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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

	public String getCarMoveState() {
		return carMoveState;
	}

	public void setCarMoveState(String carMoveState) {
		this.carMoveState = carMoveState;
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

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
	
	

}
