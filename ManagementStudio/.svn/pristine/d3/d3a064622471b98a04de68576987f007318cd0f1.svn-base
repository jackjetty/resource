package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_WinningInfo")
public class WinningInfo {

	private Integer id;

	private String phoneNumber;

	private Integer lotteryId;

	private Date winTime;

	private String hasSendMessage;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "WinningInfoIdNext")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Date getWinTime() {
		return winTime;
	}

	public void setWinTime(Date winTime) {
		this.winTime = winTime;
	}

	public String getHasSendMessage() {
		return hasSendMessage;
	}

	public void setHasSendMessage(String hasSendMessage) {
		this.hasSendMessage = hasSendMessage;
	}

}
