package com.traffic.dao;

import java.util.ArrayList;

import com.traffic.pojo.TbManagerRole;
import com.traffic.pojo.TbRole;

public interface ManagerRoleDao {

	public ArrayList<String> getRoleIdByManagerId(String userId);

	public void addManagerRole(TbManagerRole managerRole);

	public void deleteManagerRole(TbManagerRole role);

	public TbRole getRoleRoleId(String roleId);

	public void deleteById(String idObj);

	public void deleteByRoleId(String idObj);

}
