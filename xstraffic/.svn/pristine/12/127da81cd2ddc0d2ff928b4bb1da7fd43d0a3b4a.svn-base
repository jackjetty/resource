package com.traffic.web.service;

import java.util.HashMap;
import java.util.List;

import com.traffic.pojovo.TbDoAnswerVo;

public interface DoAnswerService {
	//, ,startTime,endTime,feedBackState 
	public HashMap<String, Object> getDoAnswer(int page, int rows,String startTime, String endTime ,String doAnswerState );
	 
	public HashMap<String, Object>  getUserDaInfo(int doAnswerId);
	public List<TbDoAnswerVo> getFeedBackVoList(String startTime,String endTime,String doAnswerState); 
	public HashMap<String, Object> addAnswerCustRes(int doAnswerId,String content);
}
