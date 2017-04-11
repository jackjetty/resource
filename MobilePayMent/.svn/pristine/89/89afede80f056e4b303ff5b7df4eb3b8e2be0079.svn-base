package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.Faq;
import com.rising.mobilepayment.commom.PageObject;
import com.rising.mobilepayment.mapper.FaqMapper;
import com.rising.mobilepayment.service.FaqService;

@Service("faqService")
public class FaqServiceImpl implements FaqService {
	
	@Autowired FaqMapper faqMapper;

	@Override
	public String getFaq(PageObject page) {
		Integer allSize = faqMapper.getAllSize();
		HashMap<String,Object> map = new HashMap<String, Object>();
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		int pageIndex = page.getPageIndex();
		int listSize = page.getListSize();
		int start = (pageIndex - 1) * listSize + 1;
		int end = start + listSize - 1;
		map.put("start",start );
		map.put("end",end );
		ArrayList<Faq> af = null;
		try {
			af = faqMapper.getFaq(map);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "");
			resultMap.put("pageIndex", page.getPageIndex());
			Integer pageSize = allSize % page.getListSize() == 0 ? allSize
					/ page.getListSize() : allSize / page.getListSize() + 1;
			resultMap.put("pageSize", pageSize);
			resultMap.put("listFaq", af);
		}catch(Exception e){
			resultMap.put("respCode",-206);
			resultMap.put("respInfo","数据库查询出现异常！");
		}
		String resultGson = new Gson().toJson(resultMap);
		return resultGson;
	}

}
