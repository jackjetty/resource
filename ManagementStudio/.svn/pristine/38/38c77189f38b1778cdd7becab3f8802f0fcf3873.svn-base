package com.rising.management.vo;

import java.text.SimpleDateFormat;
import com.rising.management.bean.ScoreRule;

public class ScoreRuleVo {
	private Integer ruleId;
	private String ruleContent;
	private String startTime;
	private String endTime;
	private String remark;
	private Float payMoney;
	private Float buyScore;
	private String operate;
	private Integer sendScore;
	public ScoreRuleVo(ScoreRule rs) {
		
		this.ruleId = rs.getRuleId();
		this.ruleContent = rs.getRuleContent();
		this.startTime = new SimpleDateFormat("yyyy-MM-dd").format(rs.getStartTime());
		this.endTime = new SimpleDateFormat("yyyy-MM-dd").format(rs.getEndTime());
		this.remark = rs.getRemark();
		this.payMoney = rs.getPayMoney();
		this.buyScore = rs.getBuyScore();
		this.operate = rs.getOperate();
		this.sendScore = rs.getSendScore();
	}
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
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
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
