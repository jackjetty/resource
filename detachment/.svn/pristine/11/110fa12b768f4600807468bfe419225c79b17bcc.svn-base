package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.TbHtmlTypeDao;
import com.detachment.pojo.TbHtmlType;
import com.detachment.web.service.TbHtmlTypeService;

@Service
public class TbHtmlTypeServiceImpl implements TbHtmlTypeService{
	
	@Autowired
	TbHtmlTypeDao tbHtmlTypeDao;
	
	@Override
	public HashMap<String, Object> getTbHtmlTypeIdName() {
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		ArrayList<TbHtmlType> tht=(ArrayList<TbHtmlType>) tbHtmlTypeDao.findByHQL("from TbHtmlType");
		String htmlTypeId="";
		String htmlTypeName="";
		for(TbHtmlType th:tht){
			htmlTypeId=htmlTypeId+th.getHtmlTypeId()+",";
			htmlTypeName=htmlTypeName+th.getHtmlTypeName()+",";
		}
		resultMap.put("htmlTypeId", htmlTypeId);
		resultMap.put("htmlTypeName", htmlTypeName);
		return resultMap;
	}

	@Override
	public TbHtmlType getHtmlTypeById(String id) {
		return tbHtmlTypeDao.findById(id);
	}

}
