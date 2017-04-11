package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import com.traffic.pojo.TbProblems;

public interface ProblemsService {
	public ArrayList<TbProblems> getProblemsByNum(Integer num);
	
	public HashMap<String, Object> getProblems(String problem,Integer pageSize, Integer pageIndex);

	public Integer getProblemsSize(String problem);
	
	public HashMap<String, Object> addProblem(TbProblems pb);
	
	public ArrayList<TbProblems> getProblemsByIds(String ids);
	
	public ArrayList<TbProblems> getAllProblems();
	
}
