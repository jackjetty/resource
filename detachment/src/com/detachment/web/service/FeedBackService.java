package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.vo.TbFeedBackVo;

public interface FeedBackService {

	public HashMap<String, Object> feedBackPage(int pageIndex,String dateFrom,String dateEnd);

	public Integer getFeedBackSize(String startTime, String endTime,String recordType);

	public ArrayList<TbFeedBackVo> getFeedBackVo(Integer size, Integer i,
			String startTime, String endTime,String recordType);

	public TbFeedBackVo getFeedBackById(Integer feedBackId);

	public HashMap<String, Object> getUserFbInfo(Integer feedBackId);
	
	
	public HashMap<String, Object> feedBackChatPage(int feedBackId);
	
	public HashMap<String,Object> getUserFbInfoReply(Integer feedBackId);
	
	public HashMap<String,Object> feedBackCustRes(String  feedOpenId,String content);

}
