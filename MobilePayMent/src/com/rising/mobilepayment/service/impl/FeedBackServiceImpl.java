package com.rising.mobilepayment.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.FeedBackInfo;
import com.rising.mobilepayment.mapper.FeedBackInfoMapper;
import com.rising.mobilepayment.service.FeedBackService;

@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	
	Log log = LogFactory.getLog(FeedBackServiceImpl.class);

	@Autowired
	FeedBackInfoMapper feedBackInfoMapper;

	@Override
	public String record(HashMap<String, String> map) {
		FeedBackInfo info = new FeedBackInfo();
		info.setEmail(map.get("Email"));
		info.setFBTime(new Date());
		info.setPhoneNumber(map.get("PhoneNumber"));
		info.setContactNumber(map.get("ContactNumber"));
		try {
			info.setFBContent(URLDecoder.decode(map.get("FBContent"),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HashMap<String,Object> result = new HashMap<String,Object>();
		try {
			feedBackInfoMapper.add(info);
			result.put("respCode",0);
			result.put("respInfo", "");
		} catch (Exception e) {
			log.error("record()->插入返回信息时出错"+e.toString());
			result.put("respCode",-12);
			result.put("respInfo","反馈失败！");
		}
		return new Gson().toJson(result);
	}

}
