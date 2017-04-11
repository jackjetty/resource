package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_SWEEPSTAKESPRIZE")
public class SweepStakesPrize {
	private Integer id;
	private Integer activityId;
	private Integer prizeId;
	private Integer prizeNumber;
	private String userLimit;
	private Integer payMoneyLimit;
	private String payMoneyType;
	private String payMoneyTime;
	private String frequency;
	private Integer frequencyNumber;
	private Float rate;
	private String userFrequency;
	private Integer userFrequencyNumber;
	private String show;
	private String needMessage;
	private String needCode;
	private Integer oneToMany;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "SweepstakesPrizeIdNext")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getPrizeNumber() {
		return prizeNumber;
	}

	public void setPrizeNumber(Integer prizeNumber) {
		this.prizeNumber = prizeNumber;
	}

	public String getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(String userLimit) {
		this.userLimit = userLimit;
	}

	public Integer getPayMoneyLimit() {
		return payMoneyLimit;
	}

	public void setPayMoneyLimit(Integer payMoneyLimit) {
		this.payMoneyLimit = payMoneyLimit;
	}

	public String getPayMoneyType() {
		return payMoneyType;
	}

	public void setPayMoneyType(String payMoneyType) {
		this.payMoneyType = payMoneyType;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getUserFrequency() {
		return userFrequency;
	}

	public void setUserFrequency(String userFrequency) {
		this.userFrequency = userFrequency;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getNeedMessage() {
		return needMessage;
	}

	public void setNeedMessage(String needMessage) {
		this.needMessage = needMessage;
	}

	public String getNeedCode() {
		return needCode;
	}

	public void setNeedCode(String needCode) {
		this.needCode = needCode;
	}

	public Integer getOneToMany() {
		return oneToMany;
	}

	public void setOneToMany(Integer oneToMany) {
		this.oneToMany = oneToMany;
	}

	public String getPayMoneyTime() {
		return payMoneyTime;
	}

	public void setPayMoneyTime(String payMoneyTime) {
		this.payMoneyTime = payMoneyTime;
	}

	public Integer getFrequencyNumber() {
		return frequencyNumber;
	}

	public void setFrequencyNumber(Integer frequencyNumber) {
		this.frequencyNumber = frequencyNumber;
	}

	public Integer getUserFrequencyNumber() {
		return userFrequencyNumber;
	}

	public void setUserFrequencyNumber(Integer userFrequencyNumber) {
		this.userFrequencyNumber = userFrequencyNumber;
	}

}