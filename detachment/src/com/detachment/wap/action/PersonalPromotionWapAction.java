package com.detachment.wap.action;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
 

import com.detachment.pojo.TbProblems;
import com.detachment.util.CommonUtil;
import com.detachment.wap.service.PersonalPromotionWapService;
import com.detachment.web.service.AccidentService;
import com.detachment.web.service.FullScodeService;
import com.detachment.web.service.TbProblemsService;
import org.springframework.stereotype.Controller;

@Controller("personalPromotionWapAction")
public class PersonalPromotionWapAction {
	private String openId;
	private HashMap<String,Object> result; 
	private String personalName;
	private String personalPhone;
	@Autowired
	private PersonalPromotionWapService personalPromotionWapService; 
	public String promote(){
		result=personalPromotionWapService.getPersonalPromotionInfo(openId);
		return "success";
	}
	public String updatePersonalInfo(){
		result=personalPromotionWapService.updatePersonalInfo(openId,personalName,personalPhone);
		return "success";
	}
	public String getPromotionDetail(){
		return "success";
	}
	
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	public String getPersonalName() {
		return personalName;
	}
	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	public String getPersonalPhone() {
		return personalPhone;
	}
	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}
	
}