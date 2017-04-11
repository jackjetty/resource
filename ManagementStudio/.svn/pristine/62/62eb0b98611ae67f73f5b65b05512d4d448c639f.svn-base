package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Role;
import com.rising.management.dao.ManagerRoleDao;
import com.rising.management.dao.RoleDao;
import com.rising.management.dao.RoleRightDao;
import com.rising.management.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	Log log = LogFactory.getLog(RoleServiceImpl.class);
	@Autowired
	RoleDao roleDao;
	
	@Autowired RoleRightDao roleRightDao;

	@Autowired ManagerRoleDao managerRoleDao;
	@Override
	public HashMap<String, Object> getRole(String name, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(name)) {
			name = null;
		}
		Integer listSize = roleDao.getRoleListSize(name);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<Role> ar = roleDao.getRole(name, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", ar);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> removeRole(String roleIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] ids = roleIds.split(",");
			for (String id : ids) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			roleDao.removeRole(ai);
			result.put("respCode", 0);
			result.put("respInfo", "用户角色删除成功!");
		} catch (Exception e) {
			log.error("删除用户角色时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除用户角色时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> addRole(Role r) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			roleDao.addRole(r);
			result.put("respCode",0);
			result.put("respInfo", "用户角色添加成功!");
		} catch (Exception e) {
			log.error("添加用户角色时出现异常!"+e.toString());
			result.put("respCode",-2);
			result.put("respInfo", "添加用户角色时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateRole(Role r) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			roleDao.updateRole(r);
			result.put("respCode",0);
			result.put("respInfo", "用户角色更新成功!");
		} catch (Exception e) {
			log.error("更新用户角色时出现异常!"+e.toString());
			result.put("respCode",-2);
			result.put("respInfo", "更新用户角色时出现异常!");
		}
		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAllRole(Integer managerId) {
		ArrayList<Role> ar = roleDao.getAllRole();
		ArrayList<Integer> ai = managerRoleDao.getRoleIdByManagerId(managerId);
		ArrayList<HashMap<String, Object>> ahs = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> ahs2 = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("text", "用户角色");
		for (Role role : ar) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			if("启用".equals(role.getStatus())){
				map.put("id", role.getId());
				map.put("text", role.getName());
				map.put("checked", ai.contains(role.getId()));
				ahs2.add(map);
			}else{
				continue;
			}
		}
		root.put("children",ahs2);
		ahs.add(root);
		return ahs;
	}

}
