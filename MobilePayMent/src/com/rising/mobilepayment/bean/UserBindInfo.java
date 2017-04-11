package com.rising.mobilepayment.bean;

import java.util.Date;

public class UserBindInfo {

	private String registerPhoneNumber;

	private String bindNumber;

	private Date bindTime;

	private String bindResult;

	private String checkResult;

	private Date checkTime;

	private String remark;

	private String valid;

	public String getRegisterPhoneNumber() {
		return registerPhoneNumber;
	}

	public void setRegisterPhoneNumber(String registerPhoneNumber) {
		this.registerPhoneNumber = registerPhoneNumber;
	}

	public String getBindNumber() {
		return bindNumber;
	}

	public void setBindNumber(String bindNumber) {
		this.bindNumber = bindNumber;
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public String getBindResult() {
		return bindResult;
	}

	public void setBindResult(String bindResult) {
		this.bindResult = bindResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

}
