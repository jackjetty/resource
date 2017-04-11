package com.traffic.pojovo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbTakingPhotos;

public class TbTakingPhotosVo {
	private String takingPhotosId;
	private String reportOpenId;
	private String reportTime;
	private String reportPhoneNumber;
	private String address;
	private String locationX;
	private String locationY;
	private String handyPhotoState;
	private String accepter;
	private String acceptTime;
	private String remark;
	private String dealResult;
	private String picInfo;
	private String userName;
	private String nickName;
	private String textInfo;

	public TbTakingPhotosVo(TbTakingPhotos tc, HashMap<String, Object> map1,
			HashMap<String, Object> map2,  HashMap<String, Object> map4) {
		this.takingPhotosId = tc.getTakingPhotosId();
		this.reportOpenId = tc.getReportOpenId();
		if(tc.getReportTime()==null){
			this.reportTime = null;
		}else{
			this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
					.getReportTime());
		}
		this.reportPhoneNumber = tc.getReportPhoneNumber();
		this.address = tc.getAddress();
		this.locationX = tc.getLocationX();
		this.locationY = tc.getLocationY();
		if("".equals(tc.getHandyPhotoState()) && tc.getHandyPhotoState()==null){
			this.handyPhotoState = "上报";
		}else{
			this.handyPhotoState = tc.getHandyPhotoState();
		}
		
		this.accepter = tc.getAccepter();
		if(tc.getAcceptTime()==null){
			this.acceptTime = null;
		}else{
			this.acceptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
					.getAcceptTime());
		}
		this.remark = tc.getRemark();
		this.dealResult = tc.getDealResult();
		this.picInfo = (String) map1
				.get(String.valueOf(tc.getTakingPhotosId()));
		this.userName = (String) map2.get(String.valueOf(tc.getReportOpenId())); 
		this.textInfo = (String) map4.get(String.valueOf(tc.getTakingPhotosId()));
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

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}

}
