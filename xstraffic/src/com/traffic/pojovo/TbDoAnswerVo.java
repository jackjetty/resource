package com.traffic.pojovo;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.traffic.pojo.TbDoAnswer;

public class TbDoAnswerVo {
	private Integer doAnswerId;
	//answerTime,answerOpenId,answerText,doAnswerState
	private String answerTime;
	private String answerOpenId;
	private String answerText;
	private String nickName;
	private String phoneNumber;
	private String doAnswerState;
	private String type;
	private String op1;
	

	public String getOp1() {
		return op1;
	}

	public void setOp1(String op1) {
		this.op1 = op1;
	}

	public TbDoAnswerVo(TbDoAnswer tbDoAnswer, 
			HashMap<String, Object> map2) {
		this.doAnswerId = tbDoAnswer.getDoAnswerId();
		this.answerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbDoAnswer
				.getAnswerTime());
		this.answerOpenId = tbDoAnswer.getAnswerOpenId();
		this.answerText = tbDoAnswer.getAnswerText();
		this.nickName = "";
		this.phoneNumber = (String) map2
				.get(String.valueOf(tbDoAnswer.getAnswerOpenId()));
		this.doAnswerState=tbDoAnswer.getDoAnswerState();

	}
	
	public TbDoAnswerVo(String answerTime, String answerText) {
		this.answerTime = answerTime;
		this.answerText = answerText;

	}


	public TbDoAnswerVo(String answerTime, String answerText,String feedType) {
		this.answerTime = answerTime;
		this.answerText = answerText;
		this.type=feedType;

	}

	 
	public Integer getDoAnswerId() {
		return doAnswerId;
	}

	public void setDoAnswerId(Integer doAnswerId) {
		this.doAnswerId = doAnswerId;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswerOpenId() {
		return answerOpenId;
	}

	public void setAnswerOpenId(String answerOpenId) {
		this.answerOpenId = answerOpenId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getDoAnswerState() {
		return doAnswerState;
	}

	public void setDoAnswerState(String doAnswerState) {
		this.doAnswerState = doAnswerState;
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