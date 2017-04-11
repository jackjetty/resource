package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_QQPERMONTHRECORD2")
public class QQPermonthRecode2 {
	private Integer id;
	private String phoneNumber;
	private String QQNumber;
	private Date checkTime;
	private Integer sendScore;
	private float cost;
	private float payMoney;
	private String status;
	private String month;

	public QQPermonthRecode2() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "QQPermonthRecodeIdNext2")
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

	public String getQQNumber() {
		return QQNumber;
	}

	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
