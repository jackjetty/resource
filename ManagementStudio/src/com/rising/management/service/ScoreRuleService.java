package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.ScoreRule;


public interface ScoreRuleService {

	public HashMap<String, Object> getScoreRule(String startTime, String endTime,
			Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> addScoreRule(ScoreRule sr);

	public HashMap<String, Object> removeScoreRule(String ruleIds);

	public HashMap<String, Object> updateScoreRule(ScoreRule sr);

	

}
