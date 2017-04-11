package com.rising.management.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.ManagerRoleService;

@Controller("managerRoleAction")
public class ManagerRoleAction {

	@Autowired
	ManagerRoleService managerRoleService;

	private String roleIds;

	private Integer managerId;

	private HashMap<String, Object> resultMap;

	public String developManagerRole() {
		resultMap = managerRoleService.developManagerRole(roleIds,managerId);
		return "success";
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
