/**
 * UserLoginAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 用于处理用户登录
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:20:13
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

import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.PhoneNumberCheckService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/user")
public class UserLoginAction extends BaseAction {
	Log log = LogFactory.getLog(UserLoginAction.class);

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	PhoneNumberCheckService phoneNumberCheckService;

	@RequestMapping("/login")
	public void userLogin(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String userAgent = request.getHeader("user-agent");
		String resultJson;
		try {
			operateLogService.addOperateLog(PhoneNumber, "UserLogin",userAgent);
		} catch (Exception e) {
			log.error("userLogin()->用户登录时插入操作记录失败！" + e.toString());
		}
		String Password = map.get("Password");
		UserInfo user = new UserInfo(PhoneNumber, Password);
		resultJson = userInfoService.isUserValidate(user,userAgent);
		writeJSONString(resultJson, response);
	}
}
