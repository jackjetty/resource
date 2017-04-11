package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.AuthenticationService;

@Controller
@RequestMapping("/authent")
public class AuthenticationAction extends BaseAction{
	
	@Autowired AuthenticationService authenticationService;
	
	@RequestMapping("/authe")
	public void autheAndRedirect(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String,String> map = getParameter(request);
		String partnerCode = map.get("PartnerCode");
		String payMethodId = map.get("PayMethodId");
		String leftInfo = map.get("LeftInfo");
		String resultGson = authenticationService.checkAuthe(partnerCode,payMethodId,leftInfo);
		writeJSONString(resultGson, response);
	}
}
