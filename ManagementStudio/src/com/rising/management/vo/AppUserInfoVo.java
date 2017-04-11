package com.rising.management.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.rising.management.bean.AppUserInfo;

public class AppUserInfoVo {
	private Integer userId;
	private String phoneNumber;
	private String password;
	private String userName;
	private Date birthDay;
	private String sex;
	private String qqNumber;
	private Float allScore;
	private String email;
	private String placeName;
	private String registerTime;
	private String lastTradeTime;
	private String fromWay;
	private String fromWayDetail;
	private String os;
	private String client;

	public AppUserInfoVo(AppUserInfo au, HashMap<String, Object> map,HashMap<String, Object> map2,String os,
			String client) {
		this.userId = au.getUserId();
		this.phoneNumber = au.getPhoneNumber();
		this.password = au.getPassword();
		this.userName = au.getUserName();
		this.birthDay = au.getBirthDay();
		this.sex = au.getSex();
		this.qqNumber = au.getQqNumber();
		this.allScore = au.getAllScore();
		this.email = au.getEmail();
		this.placeName = (String) map.get(au.getAddress());
		this.registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(au.getRegisterTime());
		this.fromWay = au.getFromWay();
		this.fromWayDetail = (String) map2.get(au.getFromWay());
		if(os==null){
			this.os="";
		}else{
			this.os=os.toLowerCase();
		}
		if(client==null){
			this.client="";
		}else{
			this.client=client.toLowerCase();	
		}
		
	}

	public AppUserInfoVo(Object[] objects, HashMap<String, Object> map,String os,String client) {
		if (objects.length == 4) {
			this.phoneNumber = (String) objects[0];
			this.registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(objects[1]);
			this.allScore = Float.parseFloat(String.valueOf(objects[2]));
			this.placeName = (String) map.get(objects[3]);
			this.lastTradeTime = "";
			if(os==null){
				this.os="";
			}else{
				this.os=os.toLowerCase();
			}
			if(client==null){
				this.client="";
			}else{
				this.client=client.toLowerCase();	
			}
		} else {
			this.phoneNumber = (String) objects[0];
			this.registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(objects[1]);
			this.placeName = (String) map.get(objects[3]);
			this.allScore = Float.parseFloat(String.valueOf(objects[2]));
			if(os==null){
				this.os="";
			}else{
				this.os=os.toLowerCase();
			}
			if(client==null){
				this.client="";
			}else{
				this.client=client.toLowerCase();	
			}
			if (objects[4].toString().indexOf("-") == -1) {
				this.lastTradeTime = "";
			} else {
				this.lastTradeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(objects[4]);
			}

		}

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public Float getAllScore() {
		return allScore;
	}

	public void setAllScore(Float allScore) {
		this.allScore = allScore;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public String getFromWay() {
		return fromWay;
	}

	public void setFromWay(String fromWay) {
		this.fromWay = fromWay;
	}

	public String getFromWayDetail() {
		return fromWayDetail;
	}

	public void setFromWayDetail(String fromWayDetail) {
		this.fromWayDetail = fromWayDetail;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}
