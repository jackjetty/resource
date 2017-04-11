package com.traffic.web.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ProblemsDao;
import com.traffic.pojo.TbProblems;
import com.traffic.web.service.ProblemsService;

@Service("problemsService")
public class ProblemsServiceImpl implements ProblemsService{
	Log log = LogFactory.getLog(ProblemsServiceImpl.class);
	
	@Autowired
	ProblemsDao problemsDao;
	
	@Override
	public ArrayList<TbProblems> getProblemsByNum(Integer num) {
		ArrayList<TbProblems> pb=new ArrayList<TbProblems>();
		ArrayList<TbProblems> radio=problemsDao.getProblemsByChoiceType("1");
		for(int i=0;i<num;i++){
			pb.add(radio.get(i));
		}
		return pb;
	}

	@Override
	public HashMap<String, Object> getProblems(String problem,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(problem)){
			problem=null;
		}
		Integer listSize = problemsDao.getProblemsSize(problem);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbProblems> ta=problemsDao.getProblems(problem, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ta);
		return resultMap;
	}

	@Override
	public Integer getProblemsSize(String problem) {
		if("".equals(problem)){
			problem=null;
		}
		Integer listSize = problemsDao.getProblemsSize(problem);
		return listSize;
	}

	@Override
	public HashMap<String, Object> addProblem(TbProblems pb) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			problemsDao.addProblem(pb);
			resultMap.put("respInfo", "导入成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("导入题库时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "导入时出现异常");
		}
		
		return resultMap;
	}

	@Override
	public ArrayList<TbProblems> getProblemsByIds(String ids) {
		ArrayList<TbProblems> tps=new ArrayList<TbProblems>();
		String[] pid = ids.split(",");
			for(String id : pid){
				if(!"".equals(id)){
					TbProblems tp=problemsDao.getTbProblemsById(Integer.parseInt(id));
					tps.add(tp);
				}
			}
		return tps;
	}

	@Override
	public ArrayList<TbProblems> getAllProblems() {
		ArrayList<TbProblems> tps=problemsDao.getAllProblems();
		return tps;
	}

}
