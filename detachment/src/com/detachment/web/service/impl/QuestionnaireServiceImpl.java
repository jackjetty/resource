package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.QuestionnaireDao;
import com.detachment.pojo.TbBaseInfo;
import com.detachment.pojo.TbInvestigateSchool;
import com.detachment.pojo.TbQuestion;
import com.detachment.pojo.TbQuestionRecord;
import com.detachment.web.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	// dao
	@Autowired
	private QuestionnaireDao questionnaireDao;
	
	@Override
	public HashMap<String, String> getAllSchool() {
		HashMap<String, String> schoolMap = new HashMap<String, String>();
		List<TbInvestigateSchool> schoolList = questionnaireDao.findSchoolList();
		for (TbInvestigateSchool school : schoolList) {
			schoolMap.put("" + school.getSchoolId(), school.getSchoolName());
		}
		return schoolMap;
	}

	@Override
	public List<TbQuestion> getTenRandomQuestion() {
		Integer NUMBEROFTEXTQUESTION = 8; // 8个随机文字题目
		Integer NUMBEROFPICTUREQUESTION = 2; // 2个随机图片题目
		
		List<TbQuestion> questionList = questionnaireDao.findTextQuestionList();
		List<TbQuestion> questionList2 = questionnaireDao.findPictureQuestionList();
		List<TbQuestion> partQuestionList = new ArrayList<TbQuestion>();
		List<Integer> numberList = new ArrayList<Integer>(); 
		
		int length = questionList.size();
		int length2 = questionList2.size();
		
		outter1:
		for (int i = 0; i < NUMBEROFTEXTQUESTION;) {
			Integer n = (int) Math.floor(Math.random() * length); // N个题目 0 ~ (N-1) 之间的随机正整数
			// 若是存在该随机数 丢弃该随机数 重新获取一个
			for (Integer number : numberList) {
				if (number == n) {
					continue outter1;
				}
			}
			numberList.add(n);
			i++;
		}
		
		for (int i = 0; i < NUMBEROFTEXTQUESTION; i++) {
			partQuestionList.add(questionList.get(numberList.get(i)));
		}
		
		numberList.clear();
		
		outter2:
		for (int i = 0; i < NUMBEROFPICTUREQUESTION;) {
			Integer n = (int) Math.floor(Math.random() * length2); // N个题目 0 ~ (N-1) 之间的随机正整数
			// 若是存在该随机数 丢弃该随机数 重新获取一个
			for (Integer number : numberList) {
				if (number == n) {
					continue outter2;
				}
			}
			numberList.add(n);
			i++;
		}
		
		for (int i = 0; i < NUMBEROFPICTUREQUESTION; i++) {
			partQuestionList.add(questionList2.get(numberList.get(i)));
		}
		
		return partQuestionList;
	}

	@Override
	public void saveBaseInfo(String openId, TbBaseInfo baseInfo) {
		baseInfo.setBaseInfoId(openId);
		baseInfo.setFinishTime(new Date());
		questionnaireDao.saveBaseInfo(baseInfo);
	}

	@Override
	public void saveQuestionRecord(String openId, String answer) {
		questionnaireDao.deleteRecord(openId);
		for (String recordStr : answer.split(",")) {
			TbQuestionRecord record = new TbQuestionRecord();
			record.setBaseInfoId(openId);
			record.setQuestionId(Integer.parseInt(recordStr.split(":")[0].trim()));
			record.setResponseValue(recordStr.split(":")[1].trim());
			questionnaireDao.saveQuestionRecord(record);
		}
	}

	@Override
	public HashMap<String, Object> submitExamine(String openId, String suggestion) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			questionnaireDao.updateSuggestion(openId, suggestion);
			result.put("respCode", 0);
			result.put("respInfo", "提交成功!");
		} catch (Exception e) {
			result.put("respCode", -1);
			result.put("respInfo", "提交失败!");
		}
		return result;
	}

}
