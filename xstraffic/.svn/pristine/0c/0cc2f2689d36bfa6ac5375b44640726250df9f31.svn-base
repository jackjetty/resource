package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojovo.TbFeedBackVo;

public interface FeedBackService {

	public HashMap<String, Object> getFeedBack(String startTime, String endTime ,String feedBackState,
			Integer pageSize, Integer pageIndex);

	public Integer getFeedBackSize(String startTime, String endTime ,String feedBackState);

	public ArrayList<TbFeedBackVo> getFeedBackVo(  String startTime, String endTime,String feedBackState);

	public TbFeedBackVo getFeedBackById(Integer feedBackId);

	public HashMap<String, Object> getUserFbInfo(Integer feedBackId);
	
	 
	
	public HashMap<String,Object> addFeedCustRes(Integer feedBackId,String content );

}
