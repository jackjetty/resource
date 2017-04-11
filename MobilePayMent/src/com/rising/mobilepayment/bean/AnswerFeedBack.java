package com.rising.mobilepayment.bean;

import java.util.Date;

public class AnswerFeedBack {
	
	private Integer id;
	
	private String toUserPhoneNumber;
	
	private String readed;
	
	private String content;
	
	private Date answerTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToUserPhoneNumber() {
		return toUserPhoneNumber;
	}

	public void setToUserPhoneNumber(String toUserPhoneNumber) {
		this.toUserPhoneNumber = toUserPhoneNumber;
	}

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	
}
