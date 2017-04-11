package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbRole;

public interface RoleService {

	public HashMap<String, Object> getRole(String roleName, Integer pageSize,
			Integer pageIndex);

	public HashMap<String, Object> addRole(TbRole tr);

	public HashMap<String, Object> updateRole(TbRole tr);

	public HashMap<String, Object> removeRole(String roleIds);

	public ArrayList<HashMap<String, Object>> getAllRole(String roleId);
}
