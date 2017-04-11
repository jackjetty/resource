package com.traffic.pojovo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbCongest;

public class TbCongestVo {
	private String congestId;
	private String reportOpenId;
	private String reportTime;
	private String reportPhoneNumber;
	private String congestState;
	private String accepter;
	private String acceptTime;
	private String remark;
	private String dealResult;
	private String picInfo;
	private String textInfo;
	
	public TbCongestVo(TbCongest tc,HashMap<String,Object> map1,HashMap<String,Object> map2) {
		this.congestId = tc.getCongestId();
		this.reportOpenId = tc.getReportOpenId();
		if(tc.getReportTime()==null){
			this.reportTime = null;
		}else{
			this.reportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
					.getReportTime());
		}
		this.picInfo = (String) map1.get(String.valueOf(tc.getCongestId()));
		this.textInfo = (String) map2.get(String.valueOf(tc.getCongestId()));
		this.reportPhoneNumber = tc.getReportPhoneNumber();
		this.congestState = tc.getCongestState();
		this.accepter = tc.getAccepter();
		if(tc.getAcceptTime()==null){
			this.acceptTime = null;
		}else{
			this.acceptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc
					.getAcceptTime());
		}
		this.remark = tc.getRemark();
		this.dealResult = tc.getDealResult();
	}
	public String getCongestId() {
		return congestId;
	}
	public void setCongestId(String congestId) {
		this.congestId = congestId;
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
	public String getCongestState() {
		return congestState;
	}
	public void setCongestState(String congestState) {
		this.congestState = congestState;
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
	public String getTextInfo() {
		return textInfo;
	}
	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}
	
	
}
