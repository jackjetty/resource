package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbRole;
import com.detachment.web.service.RoleService;


@Controller("roleAction")
public class RoleAction {
	private String userId;
	private ArrayList<HashMap<String, Object>> array;
	private HashMap<String, Object> result;
	private String roleName;
	private Integer pageSize;
	private Integer pageIndex;
	private String roleId;
	private String menuIds;
	private String roleIds;
	
	@Autowired
	RoleService roleService;
	
	
	public String getAllRole(){
		array=roleService.getAllRole(userId);
		return "success";
	}
	public String getRoleIdAndName(){
		result=roleService.getRoleIdAndName();
		return "success";
	}
	
	public String doRole(){
		return "success";
	}

	public String getRole(){
		result=roleService.getRoles(roleName, pageSize, pageIndex);
		return "success";
	}
	
	public String developRoleRight(){
		result=roleService.developRoleRight(roleId, menuIds);
		return "success";
	}
	public String addRole(){
		TbRole tr=new TbRole();
		tr.setRoleId(roleId);
		tr.setRoleName(roleName);
		result=roleService.addRole(tr);
		return "success";
	}
	public String updateRole(){
		result=roleService.updateRole(roleId,roleName);
		return "success";
	}
	public String removeRole(){
		result=roleService.removeRole(roleIds);
		return "success";
	}
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public ArrayList<HashMap<String, Object>> getArray() {
		return array;
	}


	public void setArray(ArrayList<HashMap<String, Object>> array) {
		this.array = array;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
	
	
	
}
