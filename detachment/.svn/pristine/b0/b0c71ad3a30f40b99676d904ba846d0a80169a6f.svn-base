package com.detachment.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.detachment.dao.QuestionnaireDao;
import com.detachment.pojo.TbBaseInfo;
import com.detachment.pojo.TbInvestigateSchool;
import com.detachment.pojo.TbQuestion;
import com.detachment.pojo.TbQuestionRecord;

@Repository
public class QuestionnaireDaoImpl extends BaseDaoImpl<TbQuestionRecord> implements QuestionnaireDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TbInvestigateSchool> findSchoolList() {
		String hql = "from TbInvestigateSchool";
		List<TbInvestigateSchool> schoolList = getSession().createQuery(hql).list();
		return schoolList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbQuestion> findQuestionList() {
		String hql = "from TbQuestion";
		List<TbQuestion> questionList = getSession().createQuery(hql).list();
		return questionList;
	}

	@Override
	public void saveBaseInfo(TbBaseInfo baseInfo) {
		getSession().saveOrUpdate(baseInfo);
		getSession().flush();
	}

	@Override
	public void saveQuestionRecord(TbQuestionRecord record) {
		getSession().saveOrUpdate(record);
		getSession().flush();
	}

	@Override
	public void updateSuggestion(String openId, String suggestion) {
		String hql = "update TbBaseInfo set suggestion=:suggestion,state='finish' where baseInfoId=:baseInfoId";
		getSession().createQuery(hql).setParameter("suggestion", suggestion).setParameter("baseInfoId", openId).executeUpdate();
	}

	@Override
	public void deleteRecord(String openId) {
		String hql = "delete from TbQuestionRecord where baseInfoId=:baseInfoId";
		getSession().createQuery(hql).setParameter("baseInfoId", openId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbQuestion> findTextQuestionList() {
		String hql = "from TbQuestion where questionType='TEXT'";
		List<TbQuestion> questionList = getSession().createQuery(hql).list();
		return questionList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbQuestion> findPictureQuestionList() {
		String hql = "from TbQuestion where questionType='TEXTANDPICTURE'";
		List<TbQuestion> questionList = getSession().createQuery(hql).list();
		return questionList;
	}

}
