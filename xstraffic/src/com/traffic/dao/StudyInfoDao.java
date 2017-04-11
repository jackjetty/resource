package com.traffic.dao;

import java.util.ArrayList;

import com.traffic.pojo.TbProblems;
import com.traffic.pojo.TbStudyInfo;



public interface StudyInfoDao {
	public void addStudyInfo(TbStudyInfo pb);
	
	public Integer getStudyInfoSize(String studyNumber,String idCard,Integer score);

	public ArrayList<TbStudyInfo> getStudyInfo(String studyNumber,String idCard,Integer score,
			Integer start, Integer pageSize);
	
	public ArrayList<TbStudyInfo> getStudyInfoJsp(String idCard,String openId,Integer start, Integer pageSize);
}
