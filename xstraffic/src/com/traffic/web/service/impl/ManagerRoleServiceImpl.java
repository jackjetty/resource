package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ManagerRoleDao;
import com.traffic.dao.RoleDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbManagerRole;
import com.traffic.web.service.ManagerRoleService;

@Service("managerRoleService")
public class ManagerRoleServiceImpl implements ManagerRoleService {
@Autowired
ManagerRoleDao managerRoleDao;
@Autowired
UserDao userDao;
@Autowired
RoleDao roleDao;
	@Override
	public HashMap<String, Object> developManagerRole(String roleIds,
			String userId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<String> roleids = managerRoleDao
				.getRoleIdByManagerId(userId);
		String[] roleId = roleIds.split(",");
		try {
			for (String id : roleId) {
				if (!"".equals(id) && !roleids.contains(id)) {
					TbManagerRole managerRole = new TbManagerRole();
					managerRole.setUserId(userId);
					managerRole.setRoleId(id);
					String roleName = roleDao.getRoleName(id);
					userDao.updateUserName(userId,id,roleName);
					managerRoleDao.addManagerRole(managerRole);
				} else {
					if (!"".equals(id)) {
						roleids.remove(roleids.indexOf(id));
					}
				}
			}
			if("".equals(roleIds)){
				userDao.updateUserName(userId,null,null);
			}
			for (int i = 0; i < roleids.size(); i++) {
				TbManagerRole role = new TbManagerRole();
				role.setUserId(userId);
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
