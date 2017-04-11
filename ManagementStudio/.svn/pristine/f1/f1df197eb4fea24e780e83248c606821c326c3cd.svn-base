package com.rising.management.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Manager;
import com.rising.management.service.ManagerService;

@Controller("managerAction")
public class ManagerAction {

	Log log = LogFactory.getLog(ManagerAction.class);

	private HashMap<String, Object> result;

	private Integer managerId;

	private Integer pageSize;

	private Integer pageIndex;

	private String username;

	private String password;

	private String newPassword;

	private String managerIds;

	private String captcha;

	private String phoneNumber;

	private String email;

	@Autowired
	ManagerService managerService;

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String IP = getRemortIP(request);
		result = managerService.isManagerValidate(username, password, captcha,IP);
		return "login";
	}

	private String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		} else {
			return request.getHeader("x-forwarded-for");
		}
	}

	public String doUser() {
		return "success";
	}

	public String getUser() {
		result = managerService.getManagerByPage(username, pageSize, pageIndex);
		return "success";
	}

	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("logout", 2);
		request.getSession().invalidate();
		return "success";
	}

	public String modifyPassword() {
		result = managerService.modifyPassword(password,newPassword);
		return "success";
	}

	public String deleteUser() {
		result = managerService.deleteById(managerIds);
		return "success";
	}

	public String addUser() {
		Manager m = new Manager();
		m.setName(username);
		m.setPassword(password);
		m.setEmail(email);
		m.setPhoneNumber(phoneNumber);
		result = managerService.addUser(m);
		return "success";
	}

	public String updateUser() {
		Manager M = new Manager();
		M.setManagerId(managerId);
		M.setName(username);
		M.setEmail(email);
		M.setPhoneNumber(phoneNumber);
		result = managerService.updateUser(M);
		return "success";
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getManagerIds() {
		return managerIds;
	}

	public void setManagerIds(String managerIds) {
		this.managerIds = managerIds;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
