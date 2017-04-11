/**
 * QueryTradeSimpleAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 根据手机号获得对应用户的交易记录，订单信息
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.commom.PageObject;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.OrderInfoService;

@Controller
@RequestMapping("/trade")
public class QueryTradeSimpleAction extends BaseAction {
	
	Log log = LogFactory.getLog(QueryTradeSimpleAction.class);
	
	@Autowired
	OperateLogService operateLogService;

	private Integer listSize;

	@Autowired
	OrderInfoService orderInfoService;

	public Integer getListSize() {
		return listSize;
	}

	@Value("#{propertiesReader[listSize]}")
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	@RequestMapping("/queryTradeSimple")
	public void queryTradeSimple(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(map.get("PhoneNumber"), "QueryTradeInfo",userAgent);
		} catch (Exception e) {
			log.error("queryTradeSimple->用户获取充值记录时插入操作记录失败！" + e.toString());
		}
		String PageIndex = map.get("PageIndex");
		map.remove("PageIndex");
		PageObject page = new PageObject();
		page.setPageIndex(Integer.valueOf(PageIndex));
		page.setListSize(listSize);
		String resultJson = orderInfoService.queryOrderInfoByPage2(map, page);
		writeJSONString(resultJson, response);
	}
}
