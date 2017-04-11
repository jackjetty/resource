package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="RS_GETSCORERULE")

public class ScoreRule {
	private Integer ruleId;
	private String ruleContent;
	private Date startTime;
	private Date endTime;
	private String remark;
	private Float payMoney;
	private Float buyScore;
	private String operate;
	private Integer sendScore;
	public ScoreRule(){}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "RuleIdNext")
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleContent() {
		return ruleContent;
	}
	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public Float getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}
	public Float getBuyScore() {
		return buyScore;
	}
	public void setBuyScore(Float buyScore) {
		this.buyScore = buyScore;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public Integer getSendScore() {
		return sendScore;
	}
	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}
	

}
