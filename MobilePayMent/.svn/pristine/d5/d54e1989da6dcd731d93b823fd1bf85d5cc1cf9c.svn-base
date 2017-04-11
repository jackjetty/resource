/**
 * GetAndroidHomePage.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 获取App首页的一些营销信息，以及业务类型信息，用户信息，最新App版本信息
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:26:20
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.GetAndroidHomePageService;
import com.rising.mobilepayment.service.OperateLogService;

@Controller
@RequestMapping("/homepage")
public class GetAndroidHomePageAction extends BaseAction {

	@Autowired
	OperateLogService operateLogService;
	
	@Autowired
	GetAndroidHomePageService getAndroidHomePageService;

	@RequestMapping("/getAndroidHomePage")
	public void getAndroidHomePage(HttpServletRequest request,
			HttpServletResponse response) {
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog("", "GetAndroidHomePage",userAgent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String resultJson = getAndroidHomePageService
				.getAndroidHomePageInformation();
		writeJSONString(resultJson, response);
	}

}
