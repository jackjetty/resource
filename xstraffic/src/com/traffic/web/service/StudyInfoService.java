package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbStudyInfo;



public interface StudyInfoService {
	public HashMap<String, Object> addStudyInfo(TbStudyInfo pb);
	
	public HashMap<String, Object> getStudyInfo(String studyNumber,String idCard,Integer score,
			Integer pageSize, Integer pageIndex);

	public Integer getStudyInfoSize(String studyNumber,String idCard,Integer score);
	
	public ArrayList<TbStudyInfo> getStudyInfoJsp(String idCard,String openId, Integer pageSize, Integer pageIndex);
}
