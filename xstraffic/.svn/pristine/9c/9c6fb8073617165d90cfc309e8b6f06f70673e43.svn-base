package com.traffic.pojovo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbAccident;

public class TbAccidentVo {
	private String accidentId;
	private String reportOpenId;
	private String reportTime;
	private String reportPhoneNumber;
	private String reporterType;
	private String userName;
	private String address;
	private String locationX;
	private String locationY;
	private String accidentState;
	private String accepter;
	private String picInfo;
	private String voiceInfo;
	private String nickName;
	private String remark;
	private String policeOpnContent;
	private String claimOpnContent;
	private String textInfo;
	private String partyPhoneNumber;

	public TbAccidentVo(TbAccident ta, HashMap<String, Object> map1,
			HashMap<String, Object> map2, HashMap<String, Object> map3,
			HashMap<String, Object> map4, HashMap<String, Object> map5,
			HashMap<String, Object> map6, HashMap<String, Object> map7,String userNameVo) {
		this.accidentId = ta.getAccidentId();
		this.reportOpenId = ta.getReportOpenId();
		this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ta
				.getReportTime());
		this.reportPhoneNumber = ta.getReportPhoneNumber();
		this.reporterType = ta.getReporterType();
		this.userName = (String) map4.get(String.valueOf(ta.getReportOpenId()));
		this.policeOpnContent = (String) map5.get(String.valueOf(ta
				.getAccidentId()));
		this.claimOpnContent = (String) map6.get(String.valueOf(ta
				.getAccidentId()));
		this.textInfo = (String) map7.get(String.valueOf(ta.getAccidentId()));
		this.address = ta.getAddress();
		this.locationX = ta.getLocationX();
		this.locationY = ta.getLocationY();
		this.accepter = userNameVo;
//		if ((String) map5.get(String.valueOf(ta.getAccidentId())) == null
//				&& (String) map6.get(String.valueOf(ta.getAccidentId())) == null) {
//			this.accidentState = "上报";
//		} else {
			this.accidentState = ta.getAccidentState();
	//	}
		this.picInfo = (String) map1.get(String.valueOf(ta.getAccidentId()));
		this.voiceInfo = (String) map2.get(String.valueOf(ta.getAccidentId()));
		this.nickName = (String) map3.get(String.valueOf(ta.getReportOpenId()));

	}
	
	
	public TbAccidentVo(TbAccident ta, HashMap<String, Object> map4) {
		this.accidentId = ta.getAccidentId();
		this.reportOpenId = ta.getReportOpenId();
		this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ta
				.getReportTime());
		this.address = ta.getAddress();
		this.reportPhoneNumber = ta.getReportPhoneNumber();
		this.reporterType = ta.getReporterType();
		this.userName = (String) map4.get(String.valueOf(ta.getReportOpenId()));
		this.accepter = ta.getAccepter();
		this.accidentState = ta.getAccidentState();
		this.nickName = "";

	}
	
	/**
	 * @author tinker
	 * @create 2014年9月19日
	 * @param accidentId
	 * @param reportTime
	 * @param reportPhoneNumber
	 * @param partyPhoneNumber
	 * @param reporterType
	 * @param address
	 * @param accidentState
	 * @param nickName
	 */
	public TbAccidentVo(){
		
	}
	public TbAccidentVo(Object[] obj) {
		super();
		this.accidentId = (String) obj[0];
		this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj[1]);
		this.reporterType = (String) obj[2];
		this.nickName = (String) obj[3];
		this.reportPhoneNumber = (String) obj[4];
		this.partyPhoneNumber = (String) obj[5];;
		this.address = (String) obj[6];
		this.accidentState = (String) obj[7];
	}

	public String getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
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

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public String getVoiceInfo() {
		return voiceInfo;
	}

	public void setVoiceInfo(String voiceInfo) {
		this.voiceInfo = voiceInfo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPoliceOpnContent() {
		return policeOpnContent;
	}

	public void setPoliceOpnContent(String policeOpnContent) {
		this.policeOpnContent = policeOpnContent;
	}

	public String getClaimOpnContent() {
		return claimOpnContent;
	}

	public void setClaimOpnContent(String claimOpnContent) {
		this.claimOpnContent = claimOpnContent;
	}

	public String getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}


	public String getPartyPhoneNumber() {
		return partyPhoneNumber;
	}


	public void setPartyPhoneNumber(String partyPhoneNumber) {
		this.partyPhoneNumber = partyPhoneNumber;
	}

}
