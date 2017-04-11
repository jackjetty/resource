package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.OperateRecord;


public class OperateRecordVo {

	private Integer operateId;

	private String manager;

	private String operateType;

	private String operateContent;

	private String operateTime;

	private String result;

	private String cause;
	
	public OperateRecordVo(OperateRecord record){
		this.cause = record.getCause();
		this.manager = record.getManager();
		this.operateContent = record.getOperateContent();
		this.operateId = record.getOperateId();
		this.operateType = record.getOperateType();
		this.result = record.getResult();
		this.operateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(record.getOperateTime());
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
