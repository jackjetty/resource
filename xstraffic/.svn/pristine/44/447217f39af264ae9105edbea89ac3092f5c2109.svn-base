package com.traffic.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbUser;

public class UserDto  implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7836668396347097542L;
	private String userId1;
	private String userId;
	private String userPassword;
	private String openId;
	private String userName;
 
	private String roleId;
	private String dirvingLicense;
	private String identityCard;
	private String phoneNumber;
	private String joinTime;
	private Boolean simplifyProcess;
	private String remark;
	private String status;
	

	public UserDto(TbUser tu,HashMap<String,Object> map1) {
		if(tu.getUserId().equals(tu.getOpenId())){
			this.userId1 = "";
		}else{
			this.userId1 = tu.getUserId();
		}
		this.userId = tu.getUserId();
		this.userPassword = tu.getUserPassword();
		this.openId = tu.getOpenId();
		this.userName = tu.getUserName(); 
		this.roleId = (String) map1.get(String.valueOf(tu.getRoleId()));
		this.dirvingLicense = tu.getDirvingLicense();
		this.identityCard = tu.getIdentityCard();
		this.phoneNumber = tu.getPhoneNumber();
		if(tu.getJoinTime() !=null){
			this.joinTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tu
					.getJoinTime());
		}else{
			this.joinTime = "";
		}
		
		this.simplifyProcess = tu.getSimplifyProcess();
		this.remark = tu.getRemark();
		this.status = tu.getStatus();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	 

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDirvingLicense() {
		return dirvingLicense;
	}

	public void setDirvingLicense(String dirvingLicense) {
		this.dirvingLicense = dirvingLicense;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public Boolean getSimplifyProcess() {
		return simplifyProcess;
	}

	public void setSimplifyProcess(Boolean simplifyProcess) {
		this.simplifyProcess = simplifyProcess;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId1() {
		return userId1;
	}

	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}
	
	

}
