package com.rising.mobilepayment.action;
 

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
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.PhoneNumberCheckService;
import com.rising.mobilepayment.service.SecurityCodeService;
import com.rising.mobilepayment.service.UserRebateService;

@Controller
@RequestMapping("/user")
public class UserRebateAction extends BaseAction {
	Log log = LogFactory.getLog(UserRebateAction.class);

	@Autowired
	private UserRebateService userRebateService;

	 

	@Autowired
	private SecurityCodeService securityCodeService;

	 

	@RequestMapping("/rebate")
	public void userRebate(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String phoneNumber = map.get("PhoneNumber");
		String busId = map.get("BusId");
		String targetNumber = map.get("TargetNumber");
		String userAgent = request.getHeader("user-agent"); 
		
		String resultJson = null; 
		HashMap<String, Object> result=userRebateService.getRebateList(phoneNumber, busId, targetNumber); 
		resultJson = new Gson().toJson(result);
		 
		writeJSONString(resultJson, response);
	}
}
