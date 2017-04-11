package com.detachment.pojo;

import java.sql.Timestamp;

/**
 * TbOperateRecord entity. @author MyEclipse Persistence Tools
 */

public class TbOperateRecord implements java.io.Serializable {

	// Fields

	private Integer operateId;
	private String manager;
	private String operateType;
	private String operateContent;
	private Timestamp operateTime;
	private String result;
	private String cause;

	// Constructors

	/** default constructor */
	public TbOperateRecord() {
	}

	/** minimal constructor */
	public TbOperateRecord(Integer operateId) {
		this.operateId = operateId;
	}

	/** full constructor */
	public TbOperateRecord(Integer operateId, String manager,
			String operateType, String operateContent, Timestamp operateTime,
			String result, String cause) {
		this.operateId = operateId;
		this.manager = manager;
		this.operateType = operateType;
		this.operateContent = operateContent;
		this.operateTime = operateTime;
		this.result = result;
		this.cause = cause;
	}

	// Property accessors

	public Integer getOperateId() {
		return this.operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getOperateContent() {
		return this.operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}