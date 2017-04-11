package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.SalesInformation;
import com.rising.mobilepayment.mapper.SalesInformationMapper;
import com.rising.mobilepayment.service.SalesInformationService;

@Service("salesInformationService")
public class SalesInformationServiceImpl implements SalesInformationService {
	Log log = LogFactory.getLog(SalesInformationServiceImpl.class);

	@Autowired
	SalesInformationMapper salesInformationMapper;

	@Override
	public String findSalesInformationById(ArrayList<Integer> inforIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<SalesInformation> salesInfo; 
		try{
			salesInfo = salesInformationMapper.findById(inforIds);
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("SalesInfoDetail", salesInfo);
		}catch(Exception e){
			log.error("findOrderInfoById()->查询促销详细信息时出错！"+e.toString());
			result.put("respCode", -206);
			result.put("respInfo", "数据库查询出现异常！");
		}
		
		return new Gson().toJson(result);
	}

	@Override
	public SalesInformation getSalesInforImageById(HashMap<String, Object> param) {
		SalesInformation info = salesInformationMapper.getImage(param);
		return info;
	}

	@Override
	public String getValiSalesInfo2(Integer pageIndex, Integer pageSize) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		int start = (pageIndex - 1) * pageSize + 1;
		int end = start +pageSize - 1;
		map.put("start",start );
		map.put("end",end );
		ArrayList<SalesInformation> asif2 = new ArrayList<SalesInformation>();
		ArrayList<SalesInformation> asif = salesInformationMapper.getValiSalesInfo2(map);
		for (SalesInformation salesInformation : asif) {
			SalesInformation s1 = new SalesInformation();
			s1.setInforId(salesInformation.getInforId());
			s1.setActContent(salesInformation.getActContent());
			s1.setImgName(salesInformation.getImgName());
			s1.setActTopic(salesInformation.getActTopic());
			s1.setUrl(salesInformation.getUrl()==null?"":salesInformation.getUrl());
			s1.setOpen(salesInformation.getOpen()==null?"":salesInformation.getOpen());
			asif2.add(s1);
		}
		HashMap<String,Object> map2 = new HashMap<String, Object>();
		map2.put("respCode", 0);
		map2.put("salesDetail", asif2);
		map2.put("respInfo", "");
		return new Gson().toJson(map2);
	}

}
