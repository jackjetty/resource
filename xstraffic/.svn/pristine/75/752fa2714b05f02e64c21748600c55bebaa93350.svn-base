package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.StudyInfoDao;
import com.traffic.pojo.TbStudyInfo;
import com.traffic.web.service.StudyInfoService;

@Service("studyInfoService")
public class StudyInfoServiceImpl implements StudyInfoService{
	Log log = LogFactory.getLog(StudyInfoServiceImpl.class);
	
	@Autowired
	StudyInfoDao studyInfoDao;
	
	@Override
	public HashMap<String, Object> addStudyInfo(TbStudyInfo pb) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			studyInfoDao.addStudyInfo(pb);
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("添加学习信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加学习信息时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getStudyInfo(String studyNumber,
			String idCard,Integer score, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(studyNumber)){
			studyNumber=null;
		}
		if("".equals(idCard)){
			idCard=null;
		}
		Integer listSize =studyInfoDao.getStudyInfoSize(studyNumber, idCard,score);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbStudyInfo> si=studyInfoDao.getStudyInfo(studyNumber, idCard, score, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows",si);
		return resultMap;
	}

	@Override
	public Integer getStudyInfoSize(String studyNumber, String idCard,Integer score) {
		if("".equals(studyNumber)){
			studyNumber=null;
		}
		if("".equals(idCard)){
			idCard=null;
		}
		Integer listSize =studyInfoDao.getStudyInfoSize(studyNumber, idCard,score);
		return listSize;
	}

	@Override
	public ArrayList<TbStudyInfo> getStudyInfoJsp(String idCard,String openId,
			Integer pageSize, Integer pageIndex) {
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbStudyInfo> si=studyInfoDao.getStudyInfoJsp(idCard,openId, start, pageSize);
		return si;
	}

}
