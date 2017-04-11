package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbUserVo;
import com.detachment.web.service.UserService;


@Controller("userAction")
public class UserAction {
	
	private HashMap<String, Object> result;
	private ArrayList<String> list;

	private String userId;
	private String password;
	private String captcha;
	private String newPassword;
	private Integer pageSize;
	private Integer pageIndex;
	private String roleId;
	private String departmentId;
	private String phoneNumber;
	private String userName;
	private String oldUserId;
	private String userIds;
	
	@Autowired
	UserService userService;
	
	public String login(){
		try {
			result=userService.isManagerValidate(userId, password, captcha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	
	
	
	public String addUser(){
		TbUser user=new TbUser();
		user.setUserId(userId);
		user.setPassword(password);
		user.setUserName(userName);
		user.setPhoneNumber(phoneNumber);
		result=userService.addUser(user,departmentId,roleId);
		return "success";
	}

	public String toMain(){
		return "toMain";
	}
	
	public String modifyPassword() throws Exception{
		result=userService.modifyPassword(password, newPassword);
		return "success";
	}
	
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession(false) != null){
			request.getSession(false).setAttribute("logout", 2);
			request.getSession(false).removeAttribute("User");  
			request.getSession(false).invalidate();
		}
		return "success";
	}
	public String doUser(){
		return "success";
	}

	public String getAllUser(){
		result=userService.getUser(userId, pageSize, pageIndex);
		return "success";
	}
	public String changeRoleIdByUserId(){
		result=userService.changeRoleIdByUserId(userId, roleId);
		return "success";
	}
	public String getAllUserId(){
		list=userService.getAllUserId();
		return "success";
	}
	
	public String updateUser(){
		TbUser user=userService.getUserByUserId(oldUserId);
		user.setUserId(userId);
		user.setPhoneNumber(phoneNumber);
		user.setUserName(userName);
		result=userService.updateUser(user, departmentId, roleId, oldUserId);
		return "success";
	}
	
	public String deleteUser(){
		result=userService.deleteUser(userIds);
		return "success";
	}
	
	public String getFirst(){
		result=userService.getFirst(userIds);
		return "success";
	}
	
	public HashMap<String, Object> getResult() {
		return result;
	}



	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
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



	public String getRoleId() {
		return roleId;
	}



	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}



	public String getDepartmentId() {
		return departmentId;
	}



	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public ArrayList<String> getList() {
		return list;
	}



	public void setList(ArrayList<String> list) {
		this.list = list;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getOldUserId() {
		return oldUserId;
	}



	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}



	public String getUserIds() {
		return userIds;
	}



	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	
	
	
}
