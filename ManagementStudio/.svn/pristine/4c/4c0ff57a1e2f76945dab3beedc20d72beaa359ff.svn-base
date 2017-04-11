package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Role;
import com.rising.management.service.RoleService;

@Controller("roleAction")
public class RoleAction {

	private Integer id;

	private String name;

	private String status;

	private String describe;

	private Integer pageSize;

	private Integer pageIndex;

	private String roleIds;
	
	private Integer managerId;
	
	private ArrayList<HashMap<String,Object>> array;

	
	private HashMap<String, Object> resultMap;

	@Autowired
	RoleService roleService;
	

	public String getRoleByPage() {
		resultMap = roleService.getRole(name, pageSize, pageIndex);
		return "success";
	}

	public String removeRole() {
		resultMap = roleService.removeRole(roleIds);
		return "success";
	}

	public String getRole(){
		array = roleService.getAllRole(managerId);
		return "success";
	}
	public String addRole() {
		Role r = new Role();
		r.setDescribe(describe);
		r.setName(name);
		r.setStatus(status);
		resultMap = roleService.addRole(r);
		return "success";
	}

	public String updateRole() {
		Role r = new Role();
		r.setId(id);
		r.setDescribe(describe);
		r.setName(name);
		r.setStatus(status);
		resultMap = roleService.updateRole(r);
		return "success";
	}

	public String doRole() {
		return "success";
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public ArrayList<HashMap<String, Object>> getArray() {
		return array;
	}

	public void setArray(ArrayList<HashMap<String, Object>> array) {
		this.array = array;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
