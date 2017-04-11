package com.detachment.dao;


import java.util.ArrayList; 

import org.rising.wei.bean.news.MsgItemNewsBean;

import com.detachment.pojo.TbHistory;

public interface HistoryDao extends BaseDao<TbHistory>{
	public Integer getHistorySize(String historyTypeId);
	
	public ArrayList<TbHistory> getTbHistory(String historyTypeId, Integer start, Integer pageSize); 
	
	public void changeHistoryTypeById(String historyId,String historyTypeId);
	
	public void changeValid(String historyId,boolean valid);
	
	public ArrayList<TbHistory> getHistoryToJsp(String historyTypeId, Integer start, Integer pageSize);
	
	public void add(MsgItemNewsBean msgItemNewsBean);
}
