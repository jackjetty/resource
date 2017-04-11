package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.SweepStakesPrize;

public class SweepStakesPrizeVo {
	private Integer id;
	private String activityName;
	private String prizeName;
	private Integer prizeNumber;
	private String userLimit;
	private Integer payMoneyLimit;
	private String payMoneyType;
	private String frequency;
	private String frequencyCode;
	private String rate;
	private String userFrequency;
	private String userFrequencyCode;
	private String show;
	private String needMessage;
	private String needCode;
	private Integer oneToMany;
	private String payMoneyTime;
	private String payMoneyTimeCode;
	private Integer frequencyNumber;
	private Integer userFrequencyNumber;

	public SweepStakesPrizeVo(SweepStakesPrize sp,
			HashMap<String, Object> map1, HashMap<String, Object> map2,
			HashMap<String, Object> map3, HashMap<String, Object> map4) {
		this.id = sp.getId();
		this.activityName = (String) map1.get(sp.getActivityId().toString());
		this.prizeName = (String) map2.get(sp.getPrizeId().toString());
		this.prizeNumber = sp.getPrizeNumber();
		if (map3.get(sp.getUserLimit()) == null) {
			this.userLimit = "全省";
		} else {
			this.userLimit = (String) map3.get(sp.getUserLimit());
		}
		this.payMoneyLimit = sp.getPayMoneyLimit();
		this.payMoneyType = getPayMoneyType(sp.getPayMoneyType(), map4);
		setFrequency(sp.getFrequency());
		this.frequencyCode = sp.getFrequency();
		this.rate = new Float(sp.getRate() * 100).intValue() + "%";
		setUserFrequency(sp.getUserFrequency());
		this.userFrequencyCode = sp.getUserFrequency();
		this.show = sp.getShow();
		this.needCode = sp.getNeedCode();
		this.needMessage = sp.getNeedMessage();
		this.oneToMany = sp.getOneToMany();
		this.frequencyNumber = sp.getFrequencyNumber();
		this.userFrequencyNumber = sp.getUserFrequencyNumber();
		setPayMoneyTime(sp.getPayMoneyTime());
		this.payMoneyTimeCode = sp.getPayMoneyTime();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
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
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("PerMonth", "每月");
		map.put("PerYearYear", "每年");
		map.put("PerWeek", "每周");
		map.put("PerDay", "每天");
		this.frequency = map.get(frequency);
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUserFrequency() {
		return userFrequency;
	}

	public void setUserFrequency(String userFrequency) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("PerMonth", "每月");
		map.put("PerYear", "每年");
		map.put("PerWeek", "每周");
		map.put("PerDay", "每天");
		this.userFrequency = map.get(userFrequency);
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

	public static String getPayMoneyType(String payMoneyType,
			HashMap<String, Object> map) {
		if(payMoneyType.startsWith("&")){
			payMoneyType = payMoneyType.substring(1, payMoneyType.length());
		}
		if(payMoneyType.endsWith("&")){
			payMoneyType = payMoneyType.substring(0,payMoneyType.length()-1);
		}
		String[] type = payMoneyType.split("&");
		String moneyType = "";
		for (int i = 0; i < type.length; i++) {
			if (i == type.length - 1) {
				moneyType = moneyType + map.get(type[i]);
			} else {
				moneyType = moneyType + map.get(type[i]) + "&";
			}

		}
		return moneyType;
	}

	public String getPayMoneyTime() {
		return payMoneyTime;
	}

	public void setPayMoneyTime(String payMoneyTime) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("AllTime", "总计");
		map.put("ThisYear", "当年");
		map.put("ThisMonth", "当月");
		map.put("ThisWeek", "当周");
		map.put("ThisDay", "当天");
		this.payMoneyTime = map.get(payMoneyTime);
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

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public String getUserFrequencyCode() {
		return userFrequencyCode;
	}

	public void setUserFrequencyCode(String userFrequencyCode) {
		this.userFrequencyCode = userFrequencyCode;
	}

	public String getPayMoneyTimeCode() {
		return payMoneyTimeCode;
	}

	public void setPayMoneyTimeCode(String payMoneyTimeCode) {
		this.payMoneyTimeCode = payMoneyTimeCode;
	}

}
