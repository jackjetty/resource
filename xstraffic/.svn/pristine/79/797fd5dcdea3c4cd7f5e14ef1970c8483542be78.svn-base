package com.traffic.dao;

import java.util.ArrayList;

import com.traffic.pojo.TbProblems;

public interface ProblemsDao {
	public ArrayList<TbProblems> getProblemsByNum();
	
	public ArrayList<TbProblems> getProblemsByChoiceType(String choiceType);
	
	public Integer getProblemsSize(String problem);

	public ArrayList<TbProblems> getProblems(String problem,
			Integer start, Integer pageSize);
	
	public void addProblem(TbProblems pb);
	
	public TbProblems getTbProblemsById(Integer id);
	
	public ArrayList<TbProblems> getAllProblems();
}
