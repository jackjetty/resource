package com.traffic.pojo;

public class TbStudyInfo implements java.io.Serializable{
	private Integer id;
	private String studyNumber;
	private String idCard;
	private String phoneNumber;
	private String resultInfo;
	private Integer score;
	private String openId;
	
	public TbStudyInfo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudyNumber() {
		return studyNumber;
	}

	public void setStudyNumber(String studyNumber) {
		this.studyNumber = studyNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	
	
}
