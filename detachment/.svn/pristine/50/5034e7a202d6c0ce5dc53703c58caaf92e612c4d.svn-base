package com.detachment.web.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbBaseInfo;
import com.detachment.pojo.TbQuestion;
import com.detachment.web.service.QuestionnaireService;

/**
 * @author wengboyu
 * 
 */
@Controller
public class QuestionnaireAction {
	// input
	private TbBaseInfo baseInfo;
	private String openId;
	private String suggestion;
	private String answer;
	
	// output
	private HashMap<String, String> schoolList;
	private List<TbQuestion> questionList;
	private HashMap<String, Object> result;
	
	// service
	@Autowired
	private QuestionnaireService questionnaireService;
	
	// method
	
	public String doExamine() {
		schoolList = questionnaireService.getAllSchool();
		return "success";
	}
	
	public String doExamine2() {
		questionList = questionnaireService.getTenRandomQuestion();
		questionnaireService.saveBaseInfo(openId, baseInfo);
		return "success";
	}
	
	public String doExamine3() {
		questionnaireService.saveQuestionRecord(openId, answer);
		return "success";
	}
	
	public String submitExamine() {
		result = questionnaireService.submitExamine(openId, suggestion);
		return "success";
	}

	// property accessors
	
	public TbBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(TbBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public HashMap<String, String> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(HashMap<String, String> schoolList) {
		this.schoolList = schoolList;
	}

	public List<TbQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<TbQuestion> questionList) {
		this.questionList = questionList;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	
}
