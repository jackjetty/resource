package com.rising.mobilepayment.bean;

import java.util.Date;

public class OperateLog {
	private Integer OperateId;
	private String PhoneNumber;
	private String OperateType;
	private Date OperateTime;
	private String Os;
	private String Client;
	private String Version;

	public Integer getOperateId() {
		return OperateId;
	}

	public void setOperateId(Integer operateId) {
		OperateId = operateId;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getOperateType() {
		return OperateType;
	}

	public void setOperateType(String operateType) {
		OperateType = operateType;
	}

	public Date getOperateTime() {
		return OperateTime;
	}

	public void setOperateTime(Date operateTime) {
		OperateTime = operateTime;
	}

	public String getOs() {
		return Os;
	}

	public void setOs(String os) {
		Os = os;
	}

	public String getClient() {
		return Client;
	}

	public void setClient(String client) {
		Client = client;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

}
