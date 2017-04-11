package com.traffic.pojovo;

import java.text.SimpleDateFormat;

import com.traffic.pojo.TbOperateRecord;

public class TbOperateRecordVo {
	private String operateId;
	private String manager;
	private String operateType;
	private String operateContent;
	private String operateTime;
	private String result;
	private String cause;

	public TbOperateRecordVo(TbOperateRecord tor) {
		this.operateId = tor.getOperateId().toString();
		this.manager = tor.getManager();
		this.operateType = tor.getOperateType();
		this.operateContent = tor.getOperateContent();
		this.operateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(tor.getOperateTime());
		this.result = tor.getResult();
		this.cause = tor.getCause();
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
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
