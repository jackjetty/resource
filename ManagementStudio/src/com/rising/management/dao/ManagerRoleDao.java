package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.ManagerRole;
import com.rising.management.bean.Role;

public interface ManagerRoleDao {
	
	public ArrayList<Integer> getRoleIdByManagerId(Integer managerId);

	public void deleteManagerRole(ManagerRole role);

	public void addManagerRole(ManagerRole managerRole);

	public Role getRoleManagerId(Integer managerId);

}
