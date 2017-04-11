package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.RoleRightService;


@Controller("roleRightAction")
public class RoleRightAction {
	private String roleId;

	private String menuIds;

	private HashMap<String, Object> resultMap;

	@Autowired
	RoleRightService roleRightService;

	public String developRoleRight() {
		resultMap = roleRightService.developRoleRight(roleId, menuIds);
		return "success";
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}