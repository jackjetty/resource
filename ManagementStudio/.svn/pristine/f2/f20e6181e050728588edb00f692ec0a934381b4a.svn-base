package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.OrderInfoService;
import com.rising.management.vo.RandomDraw;

@Controller("randomDrawAction")
public class RandomDrawAction {
	private String startTime;
	private String endTime;
	private String phoneNumber;
	private String sm;
	private String place;

	private Integer prizeId;
	private Integer payMoneyMin;
	private Integer payMoneyMax;
	private Integer scoreMin;
	private Integer scoreMax;

	private HashMap<String, Object> resultMap;

	private ArrayList<RandomDraw> ard;
	@Autowired
	OrderInfoService orderInfoService;

	public String doRandom() {
		place = "null";
		return "success";
	}

	public String doTenInfo() {
		if("null".equals(place)){
			place = null;
		}
		ard = orderInfoService.getOrderInfoByTen(prizeId, startTime, endTime,
				payMoneyMin, payMoneyMax, scoreMin, scoreMax, place);
		return "success";
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public ArrayList<RandomDraw> getArd() {
		return ard;
	}

	public void setArd(ArrayList<RandomDraw> ard) {
		this.ard = ard;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getPayMoneyMin() {
		return payMoneyMin;
	}

	public void setPayMoneyMin(Integer payMoneyMin) {
		this.payMoneyMin = payMoneyMin;
	}

	public Integer getPayMoneyMax() {
		return payMoneyMax;
	}

	public void setPayMoneyMax(Integer payMoneyMax) {
		this.payMoneyMax = payMoneyMax;
	}

	public Integer getScoreMin() {
		return scoreMin;
	}

	public void setScoreMin(Integer scoreMin) {
		this.scoreMin = scoreMin;
	}

	public Integer getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(Integer scoreMax) {
		this.scoreMax = scoreMax;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
