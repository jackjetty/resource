package com.rising.management.service.impl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.AnswerFeedBack;
import com.rising.management.dao.AnswerFeedBackDao;
import com.rising.management.service.AnswerFeedBackService;

@Service("answerFeedBackService")
public class AnswerFeedBackServiceImpl implements AnswerFeedBackService{
	Log log = LogFactory.getLog(AnswerFeedBackServiceImpl.class);
	
	@Autowired
	AnswerFeedBackDao answerFeedBackDao;

	@Override
	public HashMap<String, Object> addAnswerFeedBack(AnswerFeedBack ab) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			answerFeedBackDao.addAnswerFeedBack(ab);
			result.put("respCode", 0);
			result.put("respInfo", "信息保存成功!");
		} catch (Exception e) {
			log.error("保存信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}

		return result;
	}
}
