package com.detachment.wap.service;
import java.util.ArrayList;
import java.util.HashMap;

 
public interface PersonalPromotionWapService {
	public HashMap<String,Object> getPersonalPromotionInfo(String openId);
	
	public HashMap<String,Object> updatePersonalInfo(String openId,String personalName,String personalPhone);
	
}