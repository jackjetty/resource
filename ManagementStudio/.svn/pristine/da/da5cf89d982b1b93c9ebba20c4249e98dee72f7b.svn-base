package com.rising.management.dao.impl;

import org.springframework.stereotype.Component;

import com.rising.management.bean.AnswerFeedBack;
import com.rising.management.dao.AnswerFeedBackDao;


@Component("answerFeedBackDao")
public class AnswerFeedBackDaoImpl extends BaseDaoImpl implements AnswerFeedBackDao{

	@Override
	public void addAnswerFeedBack(AnswerFeedBack ab) {
		getSession().save(ab);
		getSession().flush();
		
	}

}
