package com.rising.management.action;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.AnswerFeedBack;
import com.rising.management.service.AnswerFeedBackService;

@Controller("answerFeedBackAction")
public class AnswerFeedBackAction {
	Log log = LogFactory.getLog(AnswerFeedBackAction.class);
	
	private String toUserPhoneNumber;
	private String readed;
	private String content;
	private Date answerTime;
	
	@Autowired
	AnswerFeedBackService answerFeedBackService;
	
	private HashMap<String, Object> resultMap;
	
	public String saveAnswerFeedBack(){
		AnswerFeedBack ab=new AnswerFeedBack();
		ab.setToUserPhoneNumber(toUserPhoneNumber);
		ab.setContent(content);
		ab.setReaded("NO");
		ab.setAnswerTime(new Date());
		resultMap = answerFeedBackService.addAnswerFeedBack(ab);
		return "success";
	}

	
	
	
	public String getToUserPhoneNumber() {
		return toUserPhoneNumber;
	}

	public void setToUserPhoneNumber(String toUserPhoneNumber) {
		this.toUserPhoneNumber = toUserPhoneNumber;
	}

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	
	
}
