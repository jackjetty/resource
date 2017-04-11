package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;

import com.detachment.pojo.TbAppointmentRecord;

public class TbAppointmentRecordVo {
	private String appointmentTypeName;
	private String identityCard;
	private String fileNum;
	private String appointmentTime;
	private String nickName;
	private String appointmentTheme; // 主题
	private String userName; // 姓名
	private String phoneNumber; // 联系电话
	
	public TbAppointmentRecordVo() {
		super();
	}
	
	// !tinker 2014-09-12
	// 增加主题字段
	public TbAppointmentRecordVo(Object[] obj){
		this.appointmentTypeName=(String) obj[0];
		this.identityCard=(String) obj[1];
		this.fileNum=(String) obj[2];
		this.appointmentTime=new SimpleDateFormat("yyyy-MM-dd").format(obj[3]);
		this.nickName=(String) obj[4];
		this.appointmentTheme = (String) obj[5];
	}
	
	// tinker 2014-09-15
	public TbAppointmentRecordVo(TbAppointmentRecord appointmentRecord) {
		this.appointmentTypeName = appointmentRecord.getTbAppointment().getTbAppointmentType().getAppointmentTypeName();
		this.identityCard = appointmentRecord.getIdentityCard();
		this.fileNum = appointmentRecord.getFileNum();
		this.appointmentTime = new SimpleDateFormat("yyyy-MM-dd").format(appointmentRecord.getAppointmentTime());
		this.nickName = appointmentRecord.getTbWeiUser().getNickname();
		this.appointmentTheme = (String) appointmentRecord.getTbAppointment().getAppointmentTheme();
		this.userName = (String) appointmentRecord.getUserName();
		this.phoneNumber = (String) appointmentRecord.getPhoneNumber();
	}

	public String getAppointmentTypeName() {
		return appointmentTypeName;
	}

	public void setAppointmentTypeName(String appointmentTypeName) {
		this.appointmentTypeName = appointmentTypeName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAppointmentTheme() {
		return appointmentTheme;
	}

	public void setAppointmentTheme(String appointmentTheme) {
		this.appointmentTheme = appointmentTheme;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
