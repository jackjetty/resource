package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.ManagerRole;
import com.rising.management.dao.ManagerRoleDao;
import com.rising.management.service.ManagerRoleService;


@Service("managerRoleService")
public class ManagerRoleServiceImpl implements ManagerRoleService {

	@Autowired
	ManagerRoleDao managerRoleDao;

	@Override
	public HashMap<String, Object> developManagerRole(String roleIds,
			Integer managerId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Integer> roleids = managerRoleDao
				.getRoleIdByManagerId(managerId);
		String[] roleId = roleIds.split(",");
		try {
			for (String id : roleId) {
				if (!"".equals(id) && !roleids.contains(Integer.parseInt(id))) {
					ManagerRole managerRole = new ManagerRole();
					managerRole.setManagerId(managerId);
					managerRole.setRoleId(Integer.parseInt(id));
					managerRoleDao.addManagerRole(managerRole);
				} else {
					if (!"".equals(id)) {
						roleids.remove(roleids.indexOf(Integer.parseInt(id)));
					}
				}
			}
			for (int i = 0; i < roleids.size(); i++) {
				ManagerRole role = new ManagerRole();
				role.setManagerId(managerId);
				role.setRoleId(roleids.get(i));
				managerRoleDao.deleteManagerRole(role);
			}
			result.put("respInfo", "修改用户角色成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("respInfo", "修改用户角色失败！");
		}
		return result;
	}
}
