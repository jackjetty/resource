/**
 * GetAndroidHomePageServiceImpl.java
 * com.rising.mobilepayment.service.impl
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-27   下午1:50:49
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.AppVersion;
import com.rising.mobilepayment.bean.SalesInfoResult;
import com.rising.mobilepayment.bean.SalesInformation;
import com.rising.mobilepayment.mapper.AppStartPictureMapper;
import com.rising.mobilepayment.mapper.AppVersionMapper;
import com.rising.mobilepayment.mapper.FaqMapper;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.mapper.SalesInformationMapper;
import com.rising.mobilepayment.mapper.WinnerListMapper;
import com.rising.mobilepayment.service.GetIOSHomePageService;

@Service
public class GetIOSHomePageServiceImpl implements GetIOSHomePageService {

	private final String PhoneType = "iOS";

	@Autowired
	AppVersionMapper appVersionMapper;

	@Autowired
	SalesInformationMapper salesInformationMapper;
	
	@Autowired MessageMapper messageMapper;
	
	@Autowired FaqMapper faqMapper;

	@Autowired
	WinnerListMapper winnerListMapper;
	
	@Autowired AppStartPictureMapper appStartPictureMapper;

	@Override
	public String getIOSHomePageInformation() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		AppVersion lastVersion;
		//String content =null;
		String QBPrompt = null;
		ArrayList<SalesInformation> as;
		ArrayList<SalesInformation> salesList;
		try {
			lastVersion = appVersionMapper
					.findLastVersionByPhoneType(this.PhoneType);
			
		 
			as = salesInformationMapper.findByInforId("12");
			//salesList = salesInformationMapper.findByInforId("12");
			//as = salesInformationMapper.getAllSalesInfo2();
			salesList = salesInformationMapper.getValiSalesInfo();
		//content = messageMapper.getMessageContentByUse("ToFriend");
			QBPrompt = messageMapper.getMessageContentByUse("QBPrompt");
			result.put("respCode", 0);
			result.put("respInfo", "");
			if(lastVersion !=null ){
				result.put("versionCode", lastVersion.getVersionCode());
				result.put("versionName", lastVersion.getVersionName());
				if(lastVersion.getAPKUrl()!=null){
					result.put("versionInfo", lastVersion.getAPKUrl().split("&"));
				}
				result.put("versionUpdate", lastVersion.getForbidden());
			}
			result.put("SalesInfo", as);
			ArrayList<SalesInfoResult> asr = new ArrayList<SalesInfoResult>();
			for (int i = 0; i < salesList.size(); i++) {
				SalesInfoResult result1 = new SalesInfoResult();
				result1.setContent(salesList.get(i).getActContent());
				result1.setObjid(salesList.get(i).getProductId());
				asr.add(result1);
			}
			//result.put("ToFriend", content);
			result.put("QBPrompt", QBPrompt);
			result.put("listMarketing", asr);
		} catch (Exception e) {
			result.put("respCode", -206);
			result.put("respInfo", "数据库查询出现异常！");
			e.printStackTrace();
		}
		return new Gson().toJson(result);
	}

}
