package com.traffic.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbRole;

public interface RoleDao {

	public Integer getUserSize(String roleName);

	public ArrayList<TbRole> getUser(String roleName, Integer start,
			Integer pageSize);

	public ArrayList<TbRole> getAll();

	public void addUser(TbRole tr);

	public void updateUser(TbRole tr);

	public void deleteById(String idObj);

	public ArrayList<TbRole> getAllRole();

	public ArrayList<String> getRoleIdByManagerId(String roleId);

	public String getRoleName(String id);

	public HashMap<String, Object> getRoleNameById();

}
