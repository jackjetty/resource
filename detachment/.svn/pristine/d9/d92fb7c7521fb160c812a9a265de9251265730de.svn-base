package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;

import com.detachment.pojo.TbFormalAccident;



public class TbAccidentJsp {
	private String formlAccidentId;
	private String reportTime;
	private String address;
	private String accidentState;
	
	
	public TbAccidentJsp(TbFormalAccident tba){
		this.formlAccidentId=tba.getFormlAccidentId();
		this.reportTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tba.getReportTime());
		this.address=tba.getAddress();
		this.accidentState=tba.getAccidentState();
	}
	
	
	
	public String getFormlAccidentId() {
		return formlAccidentId;
	}
	public void setFormlAccidentId(String formlAccidentId) {
		this.formlAccidentId = formlAccidentId;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccidentState() {
		return accidentState;
	}
	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}
	
	
	
}
