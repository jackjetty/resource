package com.detachment.pojo;

import java.sql.Timestamp;

/**
 * TbCustRes entity. @author MyEclipse Persistence Tools
 */

public class TbCustRes implements java.io.Serializable {

	// Fields

	private Integer custResId;
	private TbUser tbUser;
	private String processId;
	private String recordNo;
	private Timestamp custResTime;
	private String custResContent;
	private String openId;
	private String custResType;

	// Constructors

	/** default constructor */
	public TbCustRes() {
	}

	/** minimal constructor */
	public TbCustRes(Integer custResId) {
		this.custResId = custResId;
	}

	/** full constructor */
	public TbCustRes(Integer custResId, TbUser tbUser, String processId,
			String recordNo, Timestamp custResTime, String custResContent,
			String openId, String custResType) {
		this.custResId = custResId;
		this.tbUser = tbUser;
		this.processId = processId;
		this.recordNo = recordNo;
		this.custResTime = custResTime;
		this.custResContent = custResContent;
		this.openId = openId;
		this.custResType = custResType;
	}

	// Property accessors

	public Integer getCustResId() {
		return this.custResId;
	}

	public void setCustResId(Integer custResId) {
		this.custResId = custResId;
	}

	public TbUser getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRecordNo() {
		return this.recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public Timestamp getCustResTime() {
		return this.custResTime;
	}

	public void setCustResTime(Timestamp custResTime) {
		this.custResTime = custResTime;
	}

	public String getCustResContent() {
		return this.custResContent;
	}

	public void setCustResContent(String custResContent) {
		this.custResContent = custResContent;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCustResType() {
		return this.custResType;
	}

	public void setCustResType(String custResType) {
		this.custResType = custResType;
	}

}