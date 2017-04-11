package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_Prize")
public class Prize {

	private Integer prizeId;

	private Integer amount;

	private Integer leftAmount;

	private String name;

	private String describe;

	private Date addTime;
	
	private String imgName;
	
	private byte[] img1;
	
	private byte[] img2;

	private Date begin;

	private Date end;

	private String sendWay;

	private Integer sendScore;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "PrizeIdNext")
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
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

	public byte[] getImg1() {
		return img1;
	}

	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}

	public byte[] getImg2() {
		return img2;
	}

	public void setImg2(byte[] img2) {
		this.img2 = img2;
	}


}
