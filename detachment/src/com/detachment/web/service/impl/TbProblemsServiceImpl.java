package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.TbProblemsDao;
import com.detachment.pojo.TbProblems;
import com.detachment.web.service.TbProblemsService;

@Service
public class TbProblemsServiceImpl implements TbProblemsService{
	
	@Autowired
	TbProblemsDao tbProblemsDao;
	
	@Override
	public HashMap<String, Object> saveTbProblem(TbProblems tp) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		tbProblemsDao.save(tp);
		resultMap.put("code", 1);
		resultMap.put("info", "success");
		return resultMap;
	}
	
	@Override
	public ArrayList<TbProblems> getProblemsByNum(Integer num) {
		ArrayList<TbProblems> pb=new ArrayList<TbProblems>();
		ArrayList<TbProblems> radio=tbProblemsDao.getProblemsByNum();
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
		Integer listSize = tbProblemsDao.getProblemsSize(problem);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbProblems> ta=tbProblemsDao.getProblems(problem, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ta);
		return resultMap;
	}

	@Override
	public Integer getProblemsSize(String problem) {
		if("".equals(problem)){
			problem=null;
		}
		Integer listSize = tbProblemsDao.getProblemsSize(problem);
		return listSize;
	}

	@Override
	public HashMap<String, Object> addProblem(TbProblems pb) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			tbProblemsDao.addProblem(pb);
			resultMap.put("respInfo", "导入成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
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
					TbProblems tp=tbProblemsDao.getTbProblemsById(Integer.parseInt(id));
					tps.add(tp);
				}
			}
		return tps;
	}

	@Override
	public ArrayList<TbProblems> getAllProblems() {
		ArrayList<TbProblems> tps=tbProblemsDao.getAllProblems();
		return tps;
	}

}