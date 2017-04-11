package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.SecurityCodeService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/user")
public class UserInfoAction  extends BaseAction {
	
	Log log = LogFactory.getLog(UserLoginAction.class);

	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	SecurityCodeService securityCodeService;
	
	@RequestMapping("/getUserInfo")
	public void userLogin(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String OpenId = map.get("OpenId");
		String resultJson = userInfoService.findByOpenId(OpenId);
		writeJSONString(resultJson, response);
	}
	
	@RequestMapping("/checkThePhoneNumberHasBind")
	public void checkThePhoneNumberHasBind(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String phoneNumber = map.get("PhoneNumber");
		UserInfo user  = userInfoService.findUserByPhoneNumber(phoneNumber);
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		if(user!=null&&user.getOpenId()!=null&&!"".equals(user.getOpenId())&&!"default".equals(user.getOpenId())){
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "该号码已经绑定了另一个微信号，请换手机号码绑定！");
		}else{
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "ok");
			}
		writeJSONString(new Gson().toJson(resultMap), response);
	}

	@RequestMapping("/bind")
	public void bind(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String phoneNumber = map.get("PhoneNumber");
		String openId = map.get("OpenId");
		String checkCode = map.get("CheckCode");
		SecurityCode code = new SecurityCode();
		code.setCode(checkCode);
		code.setPhoneNumber(phoneNumber);
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		if(securityCodeService.isSecurityCodeValidate2(code, resultMap)){
			resultMap = userInfoService.bind(phoneNumber,openId);
		}
		writeJSONString(new Gson().toJson(resultMap), response);
	}
	
	@RequestMapping("/disBind")
	public void disBind(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String phoneNumber = map.get("PhoneNumber");
		String openId = map.get("OpenId");
		HashMap<String,Object> resultMap =  userInfoService.disBind(phoneNumber,openId);
		writeJSONString(new Gson().toJson(resultMap), response);
	}

}
