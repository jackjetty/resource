package com.detachment.web.service;

import java.util.HashMap;
import java.util.List;

import com.detachment.pojo.TbBaseInfo;
import com.detachment.pojo.TbQuestion;

public interface QuestionnaireService {

	HashMap<String, String> getAllSchool();

	List<TbQuestion> getTenRandomQuestion();

	void saveBaseInfo(String openId, TbBaseInfo baseInfo);

	void saveQuestionRecord(String openId, String answer);

	HashMap<String, Object> submitExamine(String openId, String suggestion);

}
