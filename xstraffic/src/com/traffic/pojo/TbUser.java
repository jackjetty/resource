package com.traffic.pojo;

import java.sql.Timestamp;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */

public class TbUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String userPassword;
	private String openId;
	private String userName;
	private String roleId;
	private String dirvingLicense;
	private String identityCard;
	private String phoneNumber;
	private Timestamp joinTime;
	private Boolean simplifyProcess;
	private String remark;
	private String status;

	// Constructors

	/** default constructor */
	public TbUser() {
	}

	/** minimal constructor */
	public TbUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public TbUser(String userId, String userPassword, String openId,
			String userName,   String roleId,
			String dirvingLicense, String identityCard, String phoneNumber,
			Timestamp joinTime, Boolean simplifyProcess, String remark,
			String status) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.openId = openId;
		this.userName = userName; 
		this.roleId = roleId;
		this.dirvingLicense = dirvingLicense;
		this.identityCard = identityCard;
		this.phoneNumber = phoneNumber;
		this.joinTime = joinTime;
		this.simplifyProcess = simplifyProcess;
		this.remark = remark;
		this.status = status;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

 

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDirvingLicense() {
		return this.dirvingLicense;
	}

	public void setDirvingLicense(String dirvingLicense) {
		this.dirvingLicense = dirvingLicense;
	}

	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	public Boolean getSimplifyProcess() {
		return this.simplifyProcess;
	}

	public void setSimplifyProcess(Boolean simplifyProcess) {
		this.simplifyProcess = simplifyProcess;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	 

}