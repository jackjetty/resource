/**
 * UserChangePassword.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 用于修改用户的登录密码
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:22:18
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
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.PhoneNumberCheckService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/user")
public class UserChangePasswordAction extends BaseAction {
	Log log = LogFactory.getLog(UserChangePasswordAction.class);
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	PhoneNumberCheckService phoneNumberCheckService;

	@Autowired
	OperateLogService operateLogService;

	@RequestMapping("/changePassword")
	public void userChangePassword(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson;
		String userAgent = request.getHeader("user-agent");
		if (phoneNumberCheckService.check(PhoneNumber, resultMap)) {
			try {
				operateLogService.addOperateLog(PhoneNumber,
						"UserChangePassword",userAgent);
			} catch (Exception e) {
				log.error("userRegister()->用户修改密码时插入操作记录失败！" + e.toString());
			}
			String NewPassword = map.get("NewPassword");
			UserInfo user = new UserInfo();
			user.setPhoneNumber(PhoneNumber);
			user.setPassword(NewPassword);
			resultJson = userInfoService.changePassword(user);
		} else {
			resultJson = new Gson().toJson(resultMap);
		}
		writeJSONString(resultJson, response);

	}
}
