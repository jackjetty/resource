package com.rising.management.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.ScoreRule;
import com.rising.management.service.ScoreRuleService;

@Controller("scoreRuleAction")
public class ScoreRuleAction {
	private Integer pageSize;
	private Integer pageIndex;
	private Integer ruleId;
	private String ruleIds;
	private String ruleContent;
	private String startTime;
	private String endTime;
	private float payMoney;
	private float buyScore;
	private Integer sendScore;
	private String operate;
	private String remark;
	@Autowired ScoreRuleService scoreRuleService;
	
	private HashMap<String, Object> resultMap;
	public String doScoreRule(){
		return "success";
	}
	public String getScoreRule(){
		resultMap = scoreRuleService.getScoreRule(startTime,endTime,pageSize,pageIndex);
		return "success";
	}
	public String addScoreRule(){
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ScoreRule sr = new ScoreRule();
		sr.setRuleContent(ruleContent);
		sr.setStartTime(startDate);
		sr.setEndTime(endDate);
		sr.setBuyScore(buyScore);
		sr.setPayMoney(payMoney);
		sr.setSendScore(sendScore);
		sr.setOperate(operate);
		sr.setRemark(remark);
		resultMap = scoreRuleService.addScoreRule(sr);
		return "success";
	}
	public String removeScoreRule(){
		resultMap = scoreRuleService.removeScoreRule(ruleIds);
		return "success";
	}
	public String updateScoreRule(){
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		ScoreRule sr = new ScoreRule();
		sr.setRuleId(ruleId);
		sr.setRuleContent(ruleContent);
		sr.setStartTime(startDate);
		sr.setEndTime(endDate);
		sr.setBuyScore(buyScore);
		sr.setPayMoney(payMoney);
		sr.setSendScore(sendScore);
		sr.setOperate(operate);
		sr.setRemark(remark);
		resultMap = scoreRuleService.updateScoreRule(sr);
		return "success";
	}
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleIds() {
		return ruleIds;
	}
	public void setRuleIds(String ruleIds) {
		this.ruleIds = ruleIds;
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
	public float getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}
	public float getBuyScore() {
		return buyScore;
	}
	public void setBuyScore(float buyScore) {
		this.buyScore = buyScore;
	}
	public Integer getSendScore() {
		return sendScore;
	}
	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	

}
