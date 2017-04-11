package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.UserBindInfoService;


@Controller
@RequestMapping("/bind")
public class UserBind  extends BaseAction{
	
	@Autowired UserBindInfoService userBindInfoService;
	
	@RequestMapping("/addInfo")
	public void bind(HttpServletRequest request,HttpServletResponse response){
		HashMap<String,String> last = changeMap(request);
		String resultJson = userBindInfoService.bindOperate(last);
		writeJSONString(resultJson, response);
	}
	
	private HashMap<String,String> changeMap(HttpServletRequest request){
		HashMap<String,String> returnMap = new HashMap<String, String>();
		returnMap.put("registerPhoneNumber",request.getParameter("bindNumber"));
		returnMap.put("bindNumber", request.getParameter("caller"));
		returnMap.put("checkTime", request.getParameter("checkTime"));
		returnMap.put("checkResult", request.getParameter("checkResult"));
		return returnMap;
	}

}
