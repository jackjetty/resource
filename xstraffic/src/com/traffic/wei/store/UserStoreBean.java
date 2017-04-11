package com.traffic.wei.store;
import java.io.Serializable;
public class UserStoreBean implements Serializable{ 
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 5779347971684843542L;
 
	 
	private String openId;
	private String userName;
	private String nickName;
	private String phoneNumber;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	 
	
}