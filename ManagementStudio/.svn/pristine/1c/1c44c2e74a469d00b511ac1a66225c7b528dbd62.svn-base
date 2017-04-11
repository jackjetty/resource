package com.rising.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.ScoreRule;
import com.rising.management.dao.ScoreRuleDao;
import com.rising.management.service.ScoreRuleService;
import com.rising.management.vo.ScoreRuleVo;

@Service("scoreRuleService")
public class ScoreRuleServiceImpl implements ScoreRuleService {
	Log log = LogFactory.getLog(ScoreRuleServiceImpl.class);
	@Autowired
	ScoreRuleDao scoreRuleDao;
	@Override
	public HashMap<String, Object> getScoreRule(String startTime,
			String endTime, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate=null;
		Date endDate=null;
		if("".equals(startTime)){
			startDate = null;
		}else{
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if("".equals(endTime)){
			endDate = null;
		}else{
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
			Integer listSize = scoreRuleDao.getScoreRuleListSize(startDate, endDate);
			Integer start = (pageIndex-1)*pageSize;
			ArrayList<ScoreRuleVo> srv = new ArrayList<ScoreRuleVo>();
			ArrayList<ScoreRule> sr =scoreRuleDao.getScoreRule(startDate,endDate,start,pageSize);
			for(ScoreRule s:sr){
				srv.add(new ScoreRuleVo(s));
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows",srv);
	
		
		return resultMap;
	}
	@Override
	public HashMap<String, Object> addScoreRule(ScoreRule sr) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try {
		ArrayList<ScoreRule> bs =scoreRuleDao.findAll();
		ArrayList<Integer> bId = new ArrayList<Integer>();
		for(int i=0;i<bs.size();i++){
			bId.add(bs.get(i).getRuleId());
		}
		if(bId.contains(sr.getRuleId())){
			resultMap.put("respInfo", "规则编号不能重复");
		}else{
			scoreRuleDao.addScoreRule(sr);
			resultMap.put("respInfo", "添加积分信息成功！");
			resultMap.put("respCode", 0);
		}
		} catch (Exception e) {
			log.error("添加积分时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加积分时出现异常！");
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> removeScoreRule(String ruleIds) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String [] ruleIdArray =ruleIds.split(",");
		String lastId = "";
		try{
			for(String idObj : ruleIdArray){
				lastId=idObj;
				if(!"".equals(idObj)){
					Integer ruleId = Integer.parseInt(idObj);
					scoreRuleDao.deleteById(ruleId); 
					resultMap.put("respInfo", "删除积分信息成功!");
				}
			}
		}catch(Exception e){
			log.error("删除id为"+lastId+"的积分信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除积分信息时出现异常!");
			
		}
		
		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> updateScoreRule(ScoreRule sr) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			scoreRuleDao.updateScoreRule(sr);
			resultMap.put("respInfo", "更新积分信息成功！");
			resultMap.put("respCode", 0);
		}catch(Exception e){
			log.error("更新积分信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "更新积分信息时出现异常!");
			resultMap.put("respCode", -1);
			
		}
		return resultMap;
	}

	
}
