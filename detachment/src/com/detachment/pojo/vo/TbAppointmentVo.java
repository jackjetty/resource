package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.detachment.pojo.TbAppointment;




public class TbAppointmentVo {
	private Integer appointmentId;
	private String tbAppointmentTypeName;
	private String appointmentStartTime;
	private String appointmentEndTime;
	private String appointmentTheme;
	private String appointmentDesc;
	private String appointmentAddress;
	private Integer appointmentSum;
	private Integer appointmentCount;
	private String appointmentState;
	private String appointmentRemark;
	
	public TbAppointmentVo(){}
	
	public TbAppointmentVo(TbAppointment tat,HashMap<String,Object> map){
		this.appointmentId=tat.getAppointmentId();
		this.tbAppointmentTypeName=(String) map.get(tat.getTbAppointmentType().getAppointmentTypeId());
		this.appointmentStartTime=new SimpleDateFormat("yyyy-MM-dd").format(tat.getAppointmentStartTime().getTime());
		this.appointmentEndTime=new SimpleDateFormat("yyyy-MM-dd").format(tat.getAppointmentEndTime().getTime());
		this.appointmentTheme=tat.getAppointmentTheme();
		this.appointmentDesc=tat.getAppointmentDesc();
		this.appointmentAddress=tat.getAppointmentAddress();
		this.appointmentSum=tat.getAppointmentSum();
		this.appointmentCount=tat.getAppointmentCount();
		this.appointmentState=tat.getAppointmentState();
		this.appointmentRemark=tat.getAppointmentRemark();
	}
	
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getTbAppointmentTypeName() {
		return tbAppointmentTypeName;
	}

	public void setTbAppointmentTypeName(String tbAppointmentTypeName) {
		this.tbAppointmentTypeName = tbAppointmentTypeName;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}
	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}
	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	public String getAppointmentTheme() {
		return appointmentTheme;
	}
	public void setAppointmentTheme(String appointmentTheme) {
		this.appointmentTheme = appointmentTheme;
	}
	public String getAppointmentDesc() {
		return appointmentDesc;
	}
	public void setAppointmentDesc(String appointmentDesc) {
		this.appointmentDesc = appointmentDesc;
	}
	public String getAppointmentAddress() {
		return appointmentAddress;
	}
	public void setAppointmentAddress(String appointmentAddress) {
		this.appointmentAddress = appointmentAddress;
	}
	public Integer getAppointmentSum() {
		return appointmentSum;
	}
	public void setAppointmentSum(Integer appointmentSum) {
		this.appointmentSum = appointmentSum;
	}
	public Integer getAppointmentCount() {
		return appointmentCount;
	}
	public void setAppointmentCount(Integer appointmentCount) {
		this.appointmentCount = appointmentCount;
	}
	public String getAppointmentState() {
		return appointmentState;
	}
	public void setAppointmentState(String appointmentState) {
		this.appointmentState = appointmentState;
	}
	public String getAppointmentRemark() {
		return appointmentRemark;
	}
	public void setAppointmentRemark(String appointmentRemark) {
		this.appointmentRemark = appointmentRemark;
	}
	
	
	
	
	
}
