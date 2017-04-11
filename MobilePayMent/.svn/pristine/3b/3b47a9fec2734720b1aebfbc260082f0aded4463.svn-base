/**
 * TakeChargeAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 用于客户进行下单支付 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:38:10
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
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.OrderInfoService;

@Controller
@RequestMapping("/charge")
public class TakeChargeAction extends BaseAction {
	Log log = LogFactory.getLog(TakeChargeAction.class);

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	OrderInfoService orderInfoService;

	@RequestMapping("/takeCharge")
	public void charge(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "TakeChage",userAgent);
		} catch (Exception e) {
			log.error("charge()->用户支付时插入操作记录失败！" + e.toString());
		}
		String resultJson = null;
		try {
			resultJson = orderInfoService.takeCharge(map);
		} catch (RuntimeException e) {
			log.error("charge()->" + e.toString());
			e.printStackTrace();
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -7);
			result.put("respInfo", "充值失败！");
			resultJson = new Gson().toJson(result);
		}
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/takeQQPerMonthCharge")
	public void takeQQPerMonthcharge(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "TakeQQPerMonthChage",userAgent);
		} catch (Exception e) {
			log.error("takeQQPerMonthcharge()->用户支付时插入操作记录失败！" + e.toString());
		}
		String resultJson = null;
		try {
			resultJson = orderInfoService.takeQQPerMonthCharge(map);
		} catch (RuntimeException e) {
			log.error("takeQQPerMonthcharge()->" + e.toString());
			e.printStackTrace();
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -7);
			result.put("respInfo", "充值失败！");
			resultJson = new Gson().toJson(result);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/takeQQVIPCharge")
	public void takeQQVIPcharge(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "TakeQQVIPChage",userAgent);
		} catch (Exception e) {
			log.error("takeQQVIPcharge()->用户支付时插入操作记录失败！" + e.toString());
		}
		String resultJson = null;
		try {
			resultJson = orderInfoService.takeQQVIPCharge(map);
		} catch (RuntimeException e) {
			log.error("takeQQVIPcharge()->" + e.toString());
			e.printStackTrace();
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -7);
			result.put("respInfo", "充值失败！");
			resultJson = new Gson().toJson(result);
		}
		writeJSONString(resultJson, response);
	}
}