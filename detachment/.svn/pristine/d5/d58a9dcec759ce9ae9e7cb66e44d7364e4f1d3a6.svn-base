package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.HistoryTypeDao;
import com.detachment.pojo.TbHistoryType;
import com.detachment.web.service.HistoryTypeService;

@Service
public class HistoryTypeServiceImpl implements HistoryTypeService{
	
	@Autowired
	HistoryTypeDao historyTypeDao;
	
	@Override
	public HashMap<String, Object> getHistoryTypeIdName() {
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		ArrayList<TbHistoryType> tht=(ArrayList<TbHistoryType>) historyTypeDao.findByHQL("from TbHistoryType order by showOrder");
		String HistoryTypeId="";
		String HistoryTypeName="";
		for(TbHistoryType th:tht){
			HistoryTypeId=HistoryTypeId+th.getHistoryTypeId()+",";
			HistoryTypeName=HistoryTypeName+th.getHistoryTypeName()+",";
		}
		resultMap.put("HistoryTypeId", HistoryTypeId);
		resultMap.put("HistoryTypeName", HistoryTypeName);
		return resultMap;
	}

}
