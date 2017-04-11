package com.traffic.dao;

import java.util.ArrayList;

import com.traffic.pojo.TbRoleRight;

public interface RoleRightDao {

	public ArrayList<TbRoleRight> getByRoleId(String roleId);

	public ArrayList<Integer> getMenuIdsByRoleId(String roleId);

	public void addRoleRight(TbRoleRight r);

	public void deleteRoleRight(TbRoleRight r);

	public void deleteByRoleId(String idObj);

}
