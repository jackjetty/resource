package com.traffic.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbRole;
import com.traffic.web.service.RoleService;

@Controller("roleAction")
public class RoleAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String roleName;
	private String roleId;
	private String roleIds;
	private HashMap<String, Object> result;
	private ArrayList<HashMap<String, Object>> array;
	@Autowired
	RoleService roleService;

	public String doRole() {
		return "success";
	}

	public String getRole() {
		result = roleService.getRole(roleName, pageSize, pageIndex);
		return "success";
	}

	public String addRole() {
		TbRole tr = new TbRole();
		tr.setRoleId(roleId);
		tr.setRoleName(roleName);
		result = roleService.addRole(tr);
		return "success";
	}

	public String updateRole() {
		TbRole tr = new TbRole();
		tr.setRoleId(roleId);
		tr.setRoleName(roleName);
		result = roleService.updateRole(tr);
		return "success";
	}

	public String removeRole() {
		result = roleService.removeRole(roleIds);
		return "success";
	}
	
	public String getAllRole(){
		array = roleService.getAllRole(roleId);
		return "success";
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public ArrayList<HashMap<String, Object>> getArray() {
		return array;
	}

	public void setArray(ArrayList<HashMap<String, Object>> array) {
		this.array = array;
	}

}
