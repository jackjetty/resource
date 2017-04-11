package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.Prize;

public class PrizeVo {
	private Integer prizeId;

	private Integer amount;

	private Integer leftAmount;

	private String name;

	private String describe;

	private String addTime;
	
	private String imgName;

	private String begin;

	private String end;

	private String sendWay;

	private Integer sendScore;

	public PrizeVo(Prize prize) {
		this.addTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prize
				.getAddTime());
		this.amount = prize.getAmount();
		if (prize.getBegin() == null) {
			this.begin = "";
		} else {
			this.begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(prize.getBegin());
		}

		this.describe = prize.getDescribe();
		if (prize.getEnd() == null) {
			this.end = "";
		} else {
			this.end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prize
					.getEnd());
		}
		this.imgName = prize.getImgName();
		this.leftAmount = prize.getLeftAmount();
		this.name = prize.getName();
		this.prizeId = prize.getPrizeId();
		this.sendScore = prize.getSendScore();
		this.sendWay = prize.getSendWay();
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}
	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

}
