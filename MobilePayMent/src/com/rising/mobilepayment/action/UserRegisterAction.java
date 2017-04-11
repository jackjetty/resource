/**
 * UserRegisterAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 用于处理用户注册
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * chapter     2013-5-24   上午9:17:07
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.action;

import java.util.Date;
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
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.PhoneNumberCheckService;
import com.rising.mobilepayment.service.SecurityCodeService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/user")
public class UserRegisterAction extends BaseAction {
	Log log = LogFactory.getLog(UserRegisterAction.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	PhoneNumberCheckService phoneNumberCheckService;

	@Autowired
	private SecurityCodeService securityCodeService;

	@Autowired
	OperateLogService operateLogService;
	
	
	
	
	

	@RequestMapping("/register")
	public void userRegister(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (phoneNumberCheckService.check(PhoneNumber, resultMap)) {
			try {
				operateLogService.addOperateLog(PhoneNumber, "UserRegister",userAgent);
			} catch (Exception e) {
				log.error("userRegister()->用户注册时插入操作记录失败！" + e.toString());
			}
			String Password = map.get("Password");
			String securityCode = map.get("SmsValiCode");
			String from = CommonUtil.trim(map.get("From"));
			String recommendCode=CommonUtil.trim(map.get("RecommendCode"));
			SecurityCode code = new SecurityCode();
			code.setCode(securityCode);
			code.setPhoneNumber(PhoneNumber);

			HashMap<String, Object> resultMap1 = new HashMap<String, Object>();
			UserInfo user = userInfoService.findUserByPhoneNumber(PhoneNumber);
			HashMap<String, Object> result;
			result = new HashMap<String, Object>();
			if (user != null && user.getUserStatus()!=null && "激活".equals(user.getUserStatus())) {
				log.error("user != null" );
				result.put("respCode", -3);
				result.put("respInfo", "该号码已注册！");
				resultJson = new Gson().toJson(result);
				
				writeJSONString(resultJson, response);
				return;
				
				
			}  
			if (!securityCodeService.isSecurityCodeValidate(code, resultMap1)) {
				log.error("密码不争取" );
				result.put("respCode", resultMap1.get("respCode"));
				result.put("respInfo", resultMap1.get("respInfo"));
				resultJson = new Gson().toJson(result);  
				writeJSONString(resultJson, response);
				return;
			}
			if(user==null){
				log.error("user==null" );
						UserInfo newUser = new UserInfo();
						newUser.setPhoneNumber(PhoneNumber);
						newUser.setPassword(Password);
						newUser.setRegisterTime(new Date()); 
						newUser.setFromWay(from);
						newUser.setAddress((String) resultMap.get("citycode"));
						newUser.setUserStatus("激活");
						newUser.setRecommendCode(recommendCode);
						newUser.setRecommendCode(""); 
						resultJson = userInfoService.addUser(newUser);
						writeJSONString(resultJson, response);
						return;
			}
			if(user!=null  ){
				log.error("user!=null！" );
						user.setPassword(Password);
						user.setRegisterTime(new Date()); 
						user.setFromWay(from);
						user.setUserStatus("激活");
						resultJson = userInfoService.updateUser2(user);
						writeJSONString(resultJson, response);
						return;
			 }
			log.error("我晕在这里" );
				 
		}
		
		
		resultJson = new Gson().toJson(resultMap); 
		writeJSONString(resultJson, response);
		 
	}
}
