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
import com.rising.mobilepayment.service.FlowService;
import com.rising.mobilepayment.service.OperateLogService;

@Controller
@RequestMapping("/flow")
public class FlowAction extends BaseAction {

	@Autowired
	FlowService flowService;

	Log log = LogFactory.getLog(FlowAction.class);

	@Autowired
	OperateLogService operateLogService;

	@RequestMapping("/query")
	public void queryBack(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String userAgent = request.getHeader("user-agent");
		String phoneNumber = map.get("phoneNumber");
		try {
			operateLogService.addOperateLog(phoneNumber, "FlowQuery",userAgent);
		} catch (Exception e) {
			log.error("flow/query()->用户查询使用流量时插入操作记录失败！" + e.toString());
		}
		String resultJson =flowService.query(map);
		writeJSONString(resultJson, response);
	}
	@RequestMapping("/recommend")
	public void recommendBack(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String userAgent = request.getHeader("user-agent");
		String phoneNumber = map.get("phoneNumber");
		try {
			operateLogService.addOperateLog(phoneNumber, "FlowRecommend",userAgent);
		} catch (Exception e) {
		}
		String resultJson =flowService.recommend(map);
		writeJSONString(resultJson, response);
	}
	@RequestMapping("/apply")
	public void applyBack(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String userAgent = request.getHeader("user-agent");
		String phoneNumber = map.get("phoneNumber");
		try {
			operateLogService.addOperateLog(phoneNumber, "FlowApply",userAgent);
		} catch (Exception e) {
		}
		String resultJson =flowService.apply(map);
		writeJSONString(resultJson, response);
	}
	@RequestMapping("/handle")
	public void handleBack(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String userAgent = request.getHeader("user-agent");
		String phoneNumber = map.get("phoneNumber");
		try {
			operateLogService.addOperateLog(phoneNumber, "FlowHandle",userAgent);
		} catch (Exception e) {
		}
		map.put("user-agent",userAgent.toLowerCase());
		String resultJson =flowService.handle(map);
		writeJSONString(resultJson, response);
	}
	@RequestMapping("/orderQuery")
	public void orderQueryBack(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String userAgent = request.getHeader("user-agent");
		String phoneNumber = map.get("phoneNumber");
		try {
			operateLogService.addOperateLog(phoneNumber, "FlowOrderQuery",userAgent);
		} catch (Exception e) {
		}
		String resultJson =flowService.orderQuery(map);
		writeJSONString(resultJson, response);
	}
}
