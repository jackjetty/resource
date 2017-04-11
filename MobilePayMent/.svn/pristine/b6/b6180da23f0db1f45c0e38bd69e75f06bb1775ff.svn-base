/**
 * SecurityCodeAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 根据手机号验证手机短信验证码是否有效
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:43:24
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
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.SecurityCodeService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/securityCode")
public class SecurityCodeAction extends BaseAction {
	
	Log log = LogFactory.getLog(SecurityCodeAction.class);
	
	@Autowired
	OperateLogService operateLogService;

	@Autowired
	SecurityCodeService securityCodeService;

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/isValidate")
	public void isSecurityCodeValidate(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> reqmap = getParameter(request);
		String PhoneNumber = reqmap.get("PhoneNumber");
		String CheckCode = reqmap.get("SmsValiCode");
		UserInfo user = userInfoService.findUserByPhoneNumber(PhoneNumber);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String resultJson;
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "CheckSmsValiCode",userAgent);
		} catch (Exception e) {
			log.error("isSecurityCodeValidate()->检验验证码时插入操作记录失败！" + e.toString());
		}
		if (user != null) {
			SecurityCode code = new SecurityCode();
			code.setCode(CheckCode);
			code.setPhoneNumber(PhoneNumber);
			HashMap<String, Object> result = new HashMap<String, Object>();
			if (securityCodeService.isSecurityCodeValidate(code, result)) {
				map.put("respCode", 0);
				map.put("respInfo", "短信验证码正确！");
			} else {
				map.put("respCode", result.get("respCode"));
				map.put("respInfo", result.get("respInfo"));
			}
			resultJson = new Gson().toJson(map);
		} else {
			map.put("respCode", -1);
			map.put("respInfo", "该号码未注册！");
			resultJson = new Gson().toJson(map);
		} 
		writeJSONString(resultJson, response);
	}

}
