package com.detachment.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TbInfoText entity. @author MyEclipse Persistence Tools
 */

public class TbInfoText implements java.io.Serializable {

	// Fields

	private TbInfoTextId id;
	private Date textTime;
	private String textMessage;
	private String textTimeString;

	// Constructors

	/** default constructor */
	public TbInfoText() {
	}

	/** minimal constructor */
	public TbInfoText(TbInfoTextId id) {
		this.id = id;
	}

	/** full constructor */
	public TbInfoText(TbInfoTextId id, Timestamp textTime, String textMessage) {
		this.id = id;
		this.textTime = textTime;
		this.textMessage = textMessage;
	}

	// Property accessors

	public TbInfoTextId getId() {
		return this.id;
	}

	public void setId(TbInfoTextId id) {
		this.id = id;
	}

	public Date getTextTime() {
		return this.textTime;
	}

	public void setTextTime(Date textTime) {
		this.textTime = textTime;
		this.textTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(textTime);
	}

	public String getTextMessage() {
		return this.textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getTextTimeString() {
		return textTimeString;
	}

	public void setTextTimeString(String textTimeString) {
		this.textTimeString = textTimeString;
	}

}