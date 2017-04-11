package com.detachment.wap.service.impl;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

import java.util.regex.Pattern; 
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service; 

import com.alibaba.fastjson.JSON; 
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.PersonalPromotionDao;
import com.detachment.dao.UserDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbPersonalPromotion;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.WeiUtil;
 
import com.detachment.wap.service.PersonalPromotionWapService; 
import com.detachment.wei.thread.FileThread;
 
@Service("personalPromotionWapService")  
public class PersonalPromotionWapServiceImpl implements PersonalPromotionWapService{
	@Autowired
	private PersonalPromotionDao personalPromotionDao; 
	@Override
	public HashMap<String, Object> getPersonalPromotionInfo(String openId) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		openId=CommonUtil.trim(openId);
		TbPersonalPromotion tbPersonalPromotion=personalPromotionDao.getTbPersonalPromotionByOpenId(openId) ;
		if(tbPersonalPromotion==null)
			 return null;
		String personalName=CommonUtil.trim(tbPersonalPromotion.getPersonalName());
		String personalPhone=CommonUtil.trim(tbPersonalPromotion.getPersonalPhone());
		//+File.separator
		String picPath= Constant.PERSONALPROMOTION.toLowerCase()+"/"; 
		picPath+=tbPersonalPromotion.getPersonalSceneId()+".jpg"; 
		result.put("openId", openId); 
		result.put("personalName", personalName);
		result.put("personalPhone", personalPhone);
		result.put("picPath", picPath);
		return result;
	} 
	public HashMap<String,Object> updatePersonalInfo(String openId,String personalName,String personalPhone){
		HashMap<String,Object> result=new HashMap<String,Object>();
		openId=CommonUtil.trim(openId);
		TbPersonalPromotion tbPersonalPromotion=personalPromotionDao.getTbPersonalPromotionByOpenId(openId);
		if(tbPersonalPromotion==null){
			result.put("respCode", 1);
			result.put("respInfo", "未查到该记录，请查证！！");
			return result;
		}
	 
		tbPersonalPromotion.setPersonalName(CommonUtil.trim(personalName));
		tbPersonalPromotion.setPersonalPhone(CommonUtil.trim(personalPhone));
		personalPromotionDao.saveOrUpdate(tbPersonalPromotion);
		result.put("respCode", 0);
		result.put("respInfo", "修改成功！");
		return result;
			  
	}
	
}