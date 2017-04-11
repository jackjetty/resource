package com.detachment.dao;

import java.util.ArrayList;

import com.detachment.pojo.TbProblems;

public interface TbProblemsDao extends BaseDao<TbProblems>{
public ArrayList<TbProblems> getProblemsByNum();
	
	public ArrayList<TbProblems> getProblems();
	
	public Integer getProblemsSize(String problem);

	public ArrayList<TbProblems> getProblems(String problem,
			Integer start, Integer pageSize);
	
	public void addProblem(TbProblems pb);
	
	public TbProblems getTbProblemsById(Integer id);
	
	public ArrayList<TbProblems> getAllProblems();
}
