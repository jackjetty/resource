package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import net.sourceforge.jtds.jdbc.ClobImpl;

import com.detachment.pojo.TbFeedBack;

public class TbFeedBackVo {
	private Integer feedBackId;
	private String feedTime;
	private String feedOpenId;
	private String feedText;
	private String nickName;
	private String phoneNumber;
	private String type;

	public TbFeedBackVo(TbFeedBack tfb, HashMap<String, Object> map1,
			HashMap<String, Object> map2) {
		this.feedBackId = tfb.getFeedBackId();
		this.feedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tfb
				.getFeedTime());
		this.feedOpenId = tfb.getFeedOpenId();
		this.feedText = tfb.getFeedText();
		this.nickName = (String) map1.get(String.valueOf(tfb.getFeedOpenId()));
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

	public TbFeedBackVo(Object[] objects) {
		this.feedBackId = (Integer) objects[0];
		this.feedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objects[1]);
		this.feedOpenId = (String) objects[2];
		this.feedText = (String) objects[3];
		this.phoneNumber = (String) objects[6];
		this.nickName = (String) objects[7];
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
