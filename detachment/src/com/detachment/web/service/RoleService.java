package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbRole;

public interface RoleService {
	public ArrayList<HashMap<String, Object>> getAllRole(String userId);
	
	public HashMap<String,Object> getRoleIdAndName();
	
	public HashMap<String,Object> getRoles(String roleName, Integer pageSize,
			Integer pageIndex);
	
	public HashMap<String, Object> developRoleRight(String roleId,
			String menuIds);
	
	public HashMap<String,Object> addRole(TbRole tr);
	
	public HashMap<String,Object> updateRole(String roleId,String roleName);
	
	public HashMap<String,Object> removeRole(String roleIds);
}
