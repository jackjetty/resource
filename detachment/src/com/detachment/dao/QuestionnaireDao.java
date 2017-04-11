package com.detachment.dao;

import java.util.List;

import com.detachment.pojo.TbBaseInfo;
import com.detachment.pojo.TbInvestigateSchool;
import com.detachment.pojo.TbQuestion;
import com.detachment.pojo.TbQuestionRecord;

public interface QuestionnaireDao {

	List<TbInvestigateSchool> findSchoolList();

	List<TbQuestion> findQuestionList();

	void saveBaseInfo(TbBaseInfo baseInfo);

	void saveQuestionRecord(TbQuestionRecord record);

	void updateSuggestion(String openId, String suggestion);

	void deleteRecord(String openId);

	List<TbQuestion> findTextQuestionList();

	List<TbQuestion> findPictureQuestionList();

}
