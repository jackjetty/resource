package com.detachment.dao;
import java.util.ArrayList;

import com.detachment.pojo.TbRole;
public interface RoleDao extends BaseDao<TbRole> { 
		 public TbRole findRoleById(String roleId);
		 public ArrayList<TbRole> getAllRole();
		 public Integer getRoleSize(String roleName);

		public ArrayList<TbRole> getRole(String roleName, Integer start,
					Integer pageSize);
		public void deleteRole(String roleId);
}