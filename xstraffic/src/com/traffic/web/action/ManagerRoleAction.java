package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.ManagerRoleService;



@Controller("managerRoleAction")
public class ManagerRoleAction {
	@Autowired
	ManagerRoleService managerRoleService;
	private String roleIds;

	private String userId;

	private HashMap<String, Object> resultMap;
	
	public String developManagerRole() {
		resultMap = managerRoleService.developManagerRole(roleIds,userId);
		return "success";
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	

}
