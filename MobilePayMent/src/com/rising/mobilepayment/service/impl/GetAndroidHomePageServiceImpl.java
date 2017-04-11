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
import com.rising.mobilepayment.bean.AppStartPicture;
import com.rising.mobilepayment.bean.AppVersion;
import com.rising.mobilepayment.bean.KindyReminder;
import com.rising.mobilepayment.bean.SalesInfoResult;
import com.rising.mobilepayment.bean.SalesInformation;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.mapper.AppStartPictureMapper;
import com.rising.mobilepayment.mapper.AppVersionMapper;
import com.rising.mobilepayment.mapper.FaqMapper;
import com.rising.mobilepayment.mapper.KindyReminderMapper;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.mapper.PublicInfoMapper;
import com.rising.mobilepayment.mapper.SalesInformationMapper;
import com.rising.mobilepayment.mapper.WinnerListMapper;
import com.rising.mobilepayment.service.GetAndroidHomePageService;

@Service("getAndroidHomePageService")
public class GetAndroidHomePageServiceImpl implements GetAndroidHomePageService {

	private final String PhoneType = "Android";
	
	@Autowired PublicInfoMapper publicInfoMapper;

	@Autowired
	AppVersionMapper appVersionMapper;

	@Autowired
	SalesInformationMapper salesInformationMapper;
	
	@Autowired MessageMapper messageMapper;
	
	@Autowired FaqMapper faqMapper;

	@Autowired
	WinnerListMapper winnerListMapper;
	
	@Autowired AppStartPictureMapper appStartPictureMapper;

	@Autowired KindyReminderMapper  kindyReminderMapper;
	@Override
	public String getAndroidHomePageInformation() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		AppVersion lastVersion;
		ArrayList<SalesInformation> as;
		ArrayList<SalesInformation> as2;
		ArrayList<SalesInformation> salesList;
		String content =null;
		String QBPrompt = null;
		String FlowPrompt=null; 
		String FlowPackageName=null;
		String FlowPackageCode=null;
		String FlowPackagePrice=null;
		String FlowPackageSize=null;
		String [] useString = new String[]{"ToFriend","QBPrompt","FlowPrompt","FlowPackageName","FlowPackageCode","FlowPackagePrice","FlowPackageSize","ShareTitle","ShareContent","ShareImageUrl","ShareTitleUrl","ShareSite","ShareSiteUrl"};
		Integer publicInfoNumber = 0;
		Integer salesNumber = 0;
		try {
			as2 = salesInformationMapper.getBusinessSalesInfo();
			ArrayList<HashMap<String,Object>> ass2 = new ArrayList<HashMap<String,Object>>();
			for (SalesInformation salesInformation : as2) {
				HashMap<String,Object> mso = new HashMap<String, Object>();
				mso.put("code", String.valueOf(salesInformation.getBusId()));
				mso.put("name",salesInformation.getActName()==null?"":salesInformation.getActName());
				mso.put("homeTip", salesInformation.getActContent()==null?"":salesInformation.getActContent());
				ass2.add(mso);
			}
			lastVersion = appVersionMapper
					.findLastVersionByPhoneType(this.PhoneType);
			as = salesInformationMapper.getAllSalesInfo();
			ArrayList<HashMap<String,Object>> ass = new ArrayList<HashMap<String,Object>>();
			for (SalesInformation salesInformation : as) {
				HashMap<String,Object> mso = new HashMap<String, Object>();
				mso.put("InforId",salesInformation.getInforId());
				mso.put("ImgName",salesInformation.getImgName());
				mso.put("Url", salesInformation.getUrl()==null?"":salesInformation.getUrl());
				mso.put("WholeUrl",  CommonUtil.trim(salesInformation.getWholeUrl()));
				mso.put("Title",salesInformation.getActTopic()==null?"":salesInformation.getActTopic());
				mso.put("LoadMethod",salesInformation.getOpen()==null?"":salesInformation.getOpen());
				ass.add(mso);
			}
			
			salesList = salesInformationMapper.getValiSalesInfo();
			ArrayList<KindyReminder> akr = kindyReminderMapper.getAll();
			for (KindyReminder kindyReminder : akr) {
				if(kindyReminder.getChargeTipContent()==null){
					kindyReminder.setChargeTipContent("");
				}
				
				if(kindyReminder.getChargeTipTitle()==null){
					kindyReminder.setChargeTipTitle("");
				}
				
				if(kindyReminder.getConfirmTipTitle()==null){
					kindyReminder.setConfirmTipTitle("");
				}
				
				if(kindyReminder.getConfirmTipContent()==null){
					kindyReminder.setConfirmTipContent("");
				}
				
			}
			ArrayList<HashMap<String,String>> bigMap =  messageMapper.getMessageContentByUse2(useString);
			HashMap<String,String> mapString = new HashMap<String, String>();
			for (HashMap<String, String> hashMap : bigMap) {
				mapString.put(hashMap.get("USE"),hashMap.get("CONTENT"));
			}
			content = mapString.get("ToFriend");
			QBPrompt = mapString.get("QBPrompt");
			HashMap<String,String> shareConfig = new HashMap<String, String>();
			shareConfig.put("title", mapString.get("ShareTitle"));
			shareConfig.put("content",  mapString.get("ShareContent"));
			shareConfig.put("imageUrl",  mapString.get("ShareImageUrl"));
			shareConfig.put("titleUrl",  mapString.get("ShareTitleUrl"));
			shareConfig.put("site",  mapString.get("ShareSite"));
			shareConfig.put("siteUrl",  mapString.get("ShareSiteUrl"));
			FlowPrompt  = mapString.get("FlowPrompt");
			FlowPackageName= mapString.get("FlowPackageName");
			FlowPackageCode= mapString.get("FlowPackageCode");
			FlowPackagePrice= mapString.get("FlowPackagePrice");
			FlowPackageSize= mapString.get("FlowPackageSize");
			ArrayList<AppStartPicture> aasp = appStartPictureMapper.getInfo();
			publicInfoNumber = publicInfoMapper.getAllSize();
			//salesNumber = salesList.size();
			AppStartPicture asp = null;
			if(aasp!=null && aasp.size()>0){
				asp =  aasp.get(0);
			}else{
				asp = new AppStartPicture();
				asp.setPictureName("");
			}
			result.put("ProductInfo", akr);
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("ToFriend", content);
			result.put("businessList", ass2);

           /* ArrayList<String>  SysExitUrlRegs = new ArrayList<String>();
            SysExitUrlRegs.add("^http://.+/WebAppServer/version1/page/index.jsp.*$");
            SysExitUrlRegs.add("^http://.+/WebAppServer/version1/page/preferential.jsp.*$");
            SysExitUrlRegs.add("^http://.+/WebAppServer/version1/page/mine.jsp.*$");
			result.put("SysExitUrlRegs",SysExitUrlRegs);*/
			result.put("QBPrompt", QBPrompt);
			result.put("FlowPrompt", FlowPrompt);
			result.put("FlowPackageName", FlowPackageName);
			result.put("FlowPackageCode", FlowPackageCode);
			result.put("FlowPackagePrice", FlowPackagePrice);
			result.put("FlowPackageSize", FlowPackageSize);
			result.put("AppStartImageName", asp.getPictureName());
			result.put("MessageNumber", publicInfoNumber);
			result.put("DiscountNumber", salesNumber);
			result.put("shareInfo",shareConfig);
			if(lastVersion !=null ){
				result.put("versionCode", lastVersion.getVersionCode());
				result.put("versionName", lastVersion.getVersionName());
				if(lastVersion.getAPKUrl()!=null){
					result.put("versionInfo", lastVersion.getAPKUrl().split("&"));
				}
				result.put("versionUpdate", lastVersion.getForbidden());
			}
			result.put("SalesInfo", ass);
			ArrayList<SalesInfoResult> asr = new ArrayList<SalesInfoResult>();
			for (int i = 0; i < salesList.size(); i++) {
				SalesInfoResult result1 = new SalesInfoResult();
				result1.setContent(salesList.get(i).getActContent());
				result1.setObjid(salesList.get(i).getProductId());
				result1.setContentType(salesList.get(i).getDiscount()==null?"string":"number");
				asr.add(result1);
			}
			result.put("listMarketing", asr);
		} catch (Exception e) {
			result.put("respCode", -206);
			result.put("respInfo", "数据库查询出现异常！");
			e.printStackTrace();
		}
		return new Gson().toJson(result);
	}

}
