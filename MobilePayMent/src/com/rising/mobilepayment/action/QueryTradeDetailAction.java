/**
 * QueryTradeDetailAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 根据订单号获得订单的详细信息
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:41:23
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.OrderInfoService;

@Controller
@RequestMapping("/trade")
public class QueryTradeDetailAction extends BaseAction {
	
	@Autowired
	OperateLogService operateLogService;

	@Autowired
	private OrderInfoService orderInfoService;

	@RequestMapping("/queryTradeDetail")
	public void queryTradeDetail(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String OrderId = map.get("OrderId");
		String resultJson = orderInfoService.findOrderInfoById(OrderId);
		writeJSONString(resultJson, response);
	}
}
