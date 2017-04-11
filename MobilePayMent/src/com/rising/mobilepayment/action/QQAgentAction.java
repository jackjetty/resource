package com.rising.mobilepayment.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.OperateLog;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.QQAgentService;

@Controller
@RequestMapping("/qqAgent")
public class QQAgentAction extends BaseAction {
	@Autowired
	private OperateLogService operateLogService; 
	private Log log = LogFactory.getLog(QQAgentAction.class);
	
	
	@Autowired
	private QQAgentService qqAgentService;
	
	@RequestMapping("/qqServiceConfig")
	public void qqServiceConfig(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String phoneNumber =CommonUtil.trim(  map.get("PhoneNumber"));
		String busId = CommonUtil.trim( map.get("BusId"));
		String targetNumber =CommonUtil.trim( map.get("TargetNumber"));
		
		String gateCode =CommonUtil.trim( map.get("GateCode")); 
		
		String resultJson = null; 
		HashMap<String, Object> result=qqAgentService.getQQServiceConfig (gateCode,phoneNumber, busId, targetNumber); 
		resultJson = new Gson().toJson(result); 
		writeJSONString(resultJson, response);
	}
	
	
	@RequestMapping("/qqGameConfig")
	public void qqGameConfig(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String phoneNumber =CommonUtil.trim(  map.get("PhoneNumber"));
		String gameCode = CommonUtil.trim( map.get("GameCode"));
		String targetNumber =CommonUtil.trim( map.get("TargetNumber")); 
		String gateCode =CommonUtil.trim( map.get("GateCode"));  
		String resultJson = null; 
		HashMap<String, Object> result=qqAgentService.getQQGameConfig (gateCode,phoneNumber, gameCode, targetNumber); 
		
		resultJson = new Gson().toJson(result); 
		writeJSONString(resultJson, response);
	}
	
	
	@RequestMapping("/qqPayNotice")
	public void qqPayNotice(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String resultJson = null; 
		HashMap<String, Object> result=qqAgentService.qqPayNotice (map); 
		resultJson = new Gson().toJson(result);  
		writeJSONString(resultJson, response);
	}
	
	
	@RequestMapping("/qqRefundNotice")
	public void qqRefundNotice(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String resultJson = null; 
		HashMap<String, Object> result=qqAgentService.qqRefundNotice (map); 
		resultJson = new Gson().toJson(result);  
		writeJSONString(resultJson, response);
	}
	
	@RequestMapping("/qqUnsubmitRefund")
	public void qqUnsubmitRefund(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request); 
		String resultJson = null; 
		HashMap<String, Object> result=qqAgentService.qqUnsubmitRefund(map); 
		resultJson = new Gson().toJson(result);  
		writeJSONString(resultJson, response);
	}
	
	
	
}