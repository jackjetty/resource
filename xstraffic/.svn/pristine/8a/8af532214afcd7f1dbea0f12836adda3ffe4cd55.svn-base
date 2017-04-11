package com.traffic.web.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbUser;
import com.traffic.web.service.UserService;

@Controller("userAction")
public class UserAction {
	Log log = LogFactory.getLog(UserAction.class);
	private Integer pageSize;
	private Integer pageIndex;
	private String userId;
	private String userPassword;
	private String openId;
	private String userName; 
	private String roleId;
	private String dirvingLicense;
	private String identityCard;
	private String userIds;
	private String status;
	private String oldUserId;
	private String remark;
	private String phoneNumber;
	private ArrayList<String> list;
	private HashMap<String, Object> result;
	@Autowired
	UserService userService;

	public String doUser() {
		return "success";
	}

	public String getUser() {
		result = userService.getUser(userId, pageSize, pageIndex);
		return "success";
	}

	public String addUser() {
		TbUser tu = new TbUser();
		tu.setUserId(userId);
		tu.setUserPassword(userPassword);
		tu.setUserName(userName);
		tu.setPhoneNumber(phoneNumber);
		tu.setRoleId(roleId);
		tu.setRemark(remark);
		tu.setJoinTime(new Timestamp(new Date().getTime()));
		tu.setOpenId(openId); 
		tu.setIdentityCard(identityCard);
		tu.setDirvingLicense(dirvingLicense);
		tu.setStatus("正常");
		result = userService.addUser(tu);
		return "success";
	}

	public String updateUser() {
		TbUser tu = new TbUser();
		 
		if("".equals(remark)){
			remark = null;
		}
		if("".equals(userName)){
			userName = null;
		}
		if("".equals(phoneNumber)){
			phoneNumber = null;
		}
		tu.setUserId(userId);
		tu.setDirvingLicense(dirvingLicense);
		tu.setIdentityCard(identityCard); 
		tu.setOpenId(openId);
		tu.setUserName(userName);
		tu.setPhoneNumber(phoneNumber);
		tu.setRemark(remark);
		result = userService.updateUser(tu,oldUserId);
		return "success";
	}
	public String getAllUserId(){
		list = userService.getAllUserId();
		return "success";
	}

	public String deleteUser() {
		result = userService.deleteUser(userIds);
		return "success";
	}
	public String getFirst() {
		result = userService.getFirst(userIds);
		return "success";
	}
	public String exchangeStatus(){
		result = userService.exchangeStatus(userId);
		return "success";
	}
	public String exchangeStatus1(){
		result = userService.exchangeStatus1(userId);
		return "success";
	}
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	 

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDirvingLicense() {
		return dirvingLicense;
	}

	public void setDirvingLicense(String dirvingLicense) {
		this.dirvingLicense = dirvingLicense;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	

}
