package com.traffic.pojovo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbFeedBack;

public class TbFeedBackVo {
	private Integer feedBackId;
	private String feedTime;
	private String feedOpenId;
	private String feedText;
	private String nickName;
	private String phoneNumber;
	private String type;
	private String op1;
	

	public String getOp1() {
		return op1;
	}

	public void setOp1(String op1) {
		this.op1 = op1;
	}

	public TbFeedBackVo(TbFeedBack tfb,  
			HashMap<String, Object> map2) {
		this.feedBackId = tfb.getFeedBackId();
		this.feedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tfb
				.getFeedTime());
		this.feedOpenId = tfb.getFeedOpenId();
		this.feedText = tfb.getFeedText();
		 
		this.phoneNumber = (String) map2
				.get(String.valueOf(tfb.getFeedOpenId()));

	}
	
	public TbFeedBackVo(String feedTime, String feedText) {
		this.feedTime = feedTime;
		this.feedText = feedText;

	}


	public TbFeedBackVo(String feedTime, String feedText,String feedType) {
		this.feedTime = feedTime;
		this.feedText = feedText;
		this.type=feedType;

	}

	public Integer getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getFeedTime() {
		return feedTime;
	}

	public void setFeedTime(String feedTime) {
		this.feedTime = feedTime;
	}

	public String getFeedOpenId() {
		return feedOpenId;
	}

	public void setFeedOpenId(String feedOpenId) {
		this.feedOpenId = feedOpenId;
	}

	public String getFeedText() {
		return feedText;
	}

	public void setFeedText(String feedText) {
		this.feedText = feedText;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
