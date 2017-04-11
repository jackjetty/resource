package com.traffic.web.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.AccidentService;
import com.traffic.web.service.ManagerService;


@Controller("managerAction")
public class ManagerAction {
	Log log = LogFactory.getLog(ManagerAction.class);

	private HashMap<String, Object> result;

	private String username;

	private String password;

	private String captcha;

	private String newPassword;

	@Autowired
	ManagerService managerService;
	@Autowired
	AccidentService accidentService;

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String IP = getRemortIP(request);
		result = managerService.isManagerValidate(username, password, captcha,
				IP);
		return "login";
	}

	private String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		} else {
			return request.getHeader("x-forwarded-for");
		}
	}

	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession(false) != null){
			request.getSession(false).setAttribute("logout", 2);
			request.getSession(false).removeAttribute("Manager");  
			request.getSession(false).invalidate();
			
		}
		
		return "success";
	}

	public String modifyPassword() {
		result = managerService.modifyPassword(password, newPassword);
		return "success";
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
