package com.detachment.pojo;

import java.sql.Timestamp;

/**
 * TbFeedBack entity. @author MyEclipse Persistence Tools
 */

public class TbFeedBack implements java.io.Serializable {

	// Fields

	private Integer feedBackId;
	private Timestamp feedTime;
	private String feedOpenId;
	private String feedText;
	private String recordType;
	private String feedBackState;

	// Constructors

	/** default constructor */
	public TbFeedBack() {
	}

	/** minimal constructor */
	public TbFeedBack(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}

	/** full constructor */
	public TbFeedBack(Integer feedBackId, Timestamp feedTime,
			String feedOpenId, String feedText, String recordType,
			String feedBackState) {
		this.feedBackId = feedBackId;
		this.feedTime = feedTime;
		this.feedOpenId = feedOpenId;
		this.feedText = feedText;
		this.recordType = recordType;
		this.feedBackState = feedBackState;
	}

	// Property accessors

	public Integer getFeedBackId() {
		return this.feedBackId;
	}

	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}

	public Timestamp getFeedTime() {
		return this.feedTime;
	}

	public void setFeedTime(Timestamp feedTime) {
		this.feedTime = feedTime;
	}

	public String getFeedOpenId() {
		return this.feedOpenId;
	}

	public void setFeedOpenId(String feedOpenId) {
		this.feedOpenId = feedOpenId;
	}

	public String getFeedText() {
		return this.feedText;
	}

	public void setFeedText(String feedText) {
		this.feedText = feedText;
	}

	public String getRecordType() {
		return this.recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getFeedBackState() {
		return this.feedBackState;
	}

	public void setFeedBackState(String feedBackState) {
		this.feedBackState = feedBackState;
	}

}