/**
 * GetSalesInformation.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 获取促销信息的详细信息 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:29:46
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

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.SalesInformationService;

@Controller
@RequestMapping("/salesinformation")
public class GetSalesInformationAction extends BaseAction {
	
	Log log = LogFactory.getLog(GetSalesInformationAction.class);
	
	@Autowired
	OperateLogService operateLogService;

	@Autowired
	SalesInformationService salesInformationService;

	@RequestMapping("/getSalesInformation")
	public void getSalesInformation(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String userAgent = request.getHeader("user-agent");
		 
		
		
		try {
			operateLogService.addOperateLog("", "GetSalesInformation",userAgent);
		} catch (Exception e) {
			log.error("getSalesInformation()->用户获取抽奖信息时插入操作记录失败！" + e.toString());
		}
		Integer pageIndex = Integer.parseInt(map.get("PageIndex"));
		Integer pageSize = Integer.parseInt(map.get("PageSize"));
		String resultJson = salesInformationService.getValiSalesInfo2(pageIndex,pageSize);
		writeJSONString(resultJson, response);
	}
}
