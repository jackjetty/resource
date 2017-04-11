package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.SweepStakesPrize;
import com.rising.management.service.SweepStakesPrizeService;

@Controller("sweepstakesPrizeAction")
public class SweepstakesPrizeAction {
	Log log = LogFactory.getLog(SweepstakesPrizeAction.class);

	private Integer pageSize;

	private Integer pageIndex;

	private String activityId;

	private String prizeId;

	private Integer prizeNumber;

	private String userLimit;

	private Integer id;

	private String ids;

	private Integer payMoneyLimit;

	private String payMoneyType;

	private String frequency;

	private Float rate;

	private String userFrequency;

	private String show;

	private String needMessage;

	private String needCode;

	private Integer oneToMany;

	private Integer userFrequencyNumber;

	private Integer frequencyNumber;

	private String payMoneyTime;

	private HashMap<String, Object> resultMap;

	@Autowired
	SweepStakesPrizeService sweepStakesPrizeService;

	public String doSweepstakesPrize() {
		return "success";
	}

	public String getSweepStakesPrize() {
		resultMap = sweepStakesPrizeService.getSweepStakesPrize(activityId,
				prizeId, pageSize, pageIndex);
		return "success";
	}

	public String getPrizeInfoAndSweepInfo() {
		resultMap = sweepStakesPrizeService.getPrizeInfoAndSweepInfo();
		return "success";
	}

	public String addSweepStakesPrize() {
		SweepStakesPrize sp = new SweepStakesPrize();
		sp.setActivityId(Integer.parseInt(activityId));
		sp.setPrizeId(Integer.parseInt(prizeId));
		sp.setPrizeNumber(prizeNumber);
		sp.setUserLimit(userLimit);
		sp.setPayMoneyTime(payMoneyTime);
		sp.setPayMoneyType(payMoneyType);
		sp.setPayMoneyLimit(payMoneyLimit);
		sp.setFrequency(frequency);
		sp.setFrequencyNumber(frequencyNumber);
		sp.setRate(rate);
		sp.setUserFrequency(userFrequency);
		sp.setUserFrequencyNumber(userFrequencyNumber);
		sp.setShow(show);
		sp.setNeedCode(needCode);
		sp.setNeedMessage(needMessage);
		sp.setOneToMany(oneToMany);		
		resultMap = sweepStakesPrizeService.addSweepStakesPrize(sp);
		return "success";
	}

	public String updateSweepStakesPrize() {
		SweepStakesPrize sp = new SweepStakesPrize();
		sp.setActivityId(Integer.parseInt(activityId));
		sp.setPrizeId(Integer.parseInt(prizeId));
		sp.setPrizeNumber(prizeNumber);
		sp.setUserLimit(userLimit);
		sp.setId(id);
		sp.setPayMoneyTime(payMoneyTime);
		sp.setPayMoneyLimit(payMoneyLimit);
		sp.setPayMoneyType(payMoneyType);
		sp.setFrequency(frequency);
		sp.setFrequencyNumber(frequencyNumber);
		sp.setRate(rate);
		sp.setUserFrequency(userFrequency);
		sp.setUserFrequencyNumber(userFrequencyNumber);
		sp.setShow(show);
		sp.setNeedCode(needCode);
		sp.setNeedMessage(needMessage);
		sp.setOneToMany(oneToMany);
		resultMap = sweepStakesPrizeService.updateSweepStakesPrize(sp);
		return "success";
	}

	public String removeSweepStakesPrize() {
		resultMap = sweepStakesPrizeService.removeSweepStakesPrize(ids);
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

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public Integer getUserFrequencyNumber() {
		return userFrequencyNumber;
	}

	public void setUserFrequencyNumber(Integer userFrequencyNumber) {
		this.userFrequencyNumber = userFrequencyNumber;
	}

	public Integer getFrequencyNumber() {
		return frequencyNumber;
	}

	public void setFrequencyNumber(Integer frequencyNumber) {
		this.frequencyNumber = frequencyNumber;
	}

	public String getPayMoneyTime() {
		return payMoneyTime;
	}

	public void setPayMoneyTime(String payMoneyTime) {
		this.payMoneyTime = payMoneyTime;
	}

}
