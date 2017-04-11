/**
 * SendShortMessageAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 用于向指定的手机号码发送指定类型的手机验证码短信
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:35:34
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
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
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.commom.SecurityCodeRandom;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.PhoneNumberCheckService;
import com.rising.mobilepayment.service.ShortMessageService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/shortmessage")
public class SendShortMessageAction extends BaseAction {

	Log log = LogFactory.getLog(SendShortMessageAction.class);

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	PhoneNumberCheckService phoneNumberCheckService;

	@Autowired
	ShortMessageService shortMessageService;

	private static String RegisterMessage = "register";

	private static String PayMessage = "pay";
	
	private static String PayWebMessage = "payweb";

	private static String FinePasswordMessage = "findPassword";

	private static String CancelQQPerMonthMessage = "cancelQQPerMonth";

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/sendShortMessage")
	public void sendShortMessage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String MessageType = map.get("MessageType");
		resultMap.put("MessageType", MessageType);
		String resultJson = null;
		String userAgent = request.getHeader("user-agent"); 
		if (phoneNumberCheckService.check(PhoneNumber, resultMap)) { 
			try {
				operateLogService.addOperateLog(PhoneNumber, "GetSmsValiCode",userAgent);
			} catch (Exception e) {
				log.error("sendShortMessage()->用户获取短信验证码时插入操作记录失败！" + e.toString());
			} 
			if(MessageType.equalsIgnoreCase(PayWebMessage)){
           	    resultJson = shortMessageService.payShortMessage(
							PhoneNumber, SecurityCodeRandom.getSecurityCode());
           	   writeJSONString(resultJson, response); 
           	   return;
            }
			
			UserInfo user = userInfoService.findUserByPhoneNumber(PhoneNumber);
			HashMap<String, Object> result = new HashMap<String, Object>();

			if (MessageType.equals(RegisterMessage)) {
				if (user != null && user.getUserStatus().equals("激活")) {
					result.put("respCode", -3);
					result.put("respInfo", "该号码已注册！");
					resultJson = new Gson().toJson(result);
				} else {
					resultJson = shortMessageService.registerShortMessage(
							PhoneNumber, SecurityCodeRandom.getSecurityCode());
				}
			} 
             if (MessageType.equals(PayMessage)) {
				if (user == null|| (user.getUserStatus()!=null && "未激活".equals(user.getUserStatus()))) {
					result.put("respCode", -1);
					result.put("respInfo", "该号码未注册！");
					resultJson = new Gson().toJson(result);
				} else {
					resultJson = shortMessageService.payShortMessage(
							PhoneNumber, SecurityCodeRandom.getSecurityCode());
				}
			} 
             
             
             
             if (MessageType.equals(FinePasswordMessage)) {
				if (user == null || (user.getUserStatus()!=null && "未激活".equals(user.getUserStatus()))) {
					result.put("respCode", -1);
					result.put("respInfo", "该号码未注册！");
					resultJson = new Gson().toJson(result);
				} else {
					resultJson = shortMessageService.findPasswordShortMessage(
							PhoneNumber, SecurityCodeRandom.getSecurityCode());
				}
			} 
             if (MessageType.equals(CancelQQPerMonthMessage)) {
				if (user == null || (user.getUserStatus()!=null && "未激活".equals(user.getUserStatus()))) {
					result.put("respCode", -1);
					result.put("respInfo", "该号码未注册！");
					resultJson = new Gson().toJson(result);
				} else {
					resultJson = shortMessageService
							.cancelQQPerMonthShortMessage(PhoneNumber,
									SecurityCodeRandom.getSecurityCode());
				}
			}
		} else {
			resultJson = new Gson().toJson(resultMap);
		}
		writeJSONString(resultJson, response);
	}
	

	@RequestMapping("/sendBindShortMessage")
	public void sendBindShortMessage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String	resultJson = shortMessageService.sendBindShortMessage(PhoneNumber,
				SecurityCodeRandom.getSecurityCode());
		writeJSONString(resultJson, response);
	}

}
