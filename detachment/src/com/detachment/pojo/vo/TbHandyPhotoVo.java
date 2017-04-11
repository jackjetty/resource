package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.detachment.pojo.TbHandyPhoto;


public class TbHandyPhotoVo {
	private String HandyPhotoId;
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
	public TbHandyPhotoVo(){
		
	}

	 

	public TbHandyPhotoVo(TbHandyPhoto tbHandyPhoto,String username,String nickname) {
		this.HandyPhotoId = tbHandyPhoto.getHandyPhotoId();
		this.reportOpenId = tbHandyPhoto.getReportOpenId();
		if(tbHandyPhoto.getReportTime()==null){
			this.reportTime = null;
		}else{
			this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbHandyPhoto
					.getReportTime());
		}
		this.reportPhoneNumber = tbHandyPhoto.getReportPhoneNumber();
		this.address = tbHandyPhoto.getAddress();
		this.locationX =tbHandyPhoto.getLocationX();
		this.locationY =tbHandyPhoto.getLocationY();
		if("".equals(tbHandyPhoto.getHandyPhotoState()) && tbHandyPhoto.getHandyPhotoState()==null){
			this.handyPhotoState = "上报";
		}else{
			this.handyPhotoState = tbHandyPhoto.getHandyPhotoState();
		}
		
		this.accepter = tbHandyPhoto.getAccepter();
		if(tbHandyPhoto.getAcceptTime()==null){
			this.acceptTime = null;
		}else{
			this.acceptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbHandyPhoto
					.getAcceptTime());
		}
		this.remark = tbHandyPhoto.getRemark();
		this.dealResult = tbHandyPhoto.getDealResult();
		
		this.userName =  username;
		this.nickName =  nickname;
	}
	
	
	
	public String getHandyPhotoId() {
		return HandyPhotoId;
	}

	public void setHandyPhotoId(String HandyPhotoId) {
		this.HandyPhotoId = HandyPhotoId;
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
