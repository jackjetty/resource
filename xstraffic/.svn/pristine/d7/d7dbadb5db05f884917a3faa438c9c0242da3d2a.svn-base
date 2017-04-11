package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ManagerRoleDao;
import com.traffic.dao.RoleDao;
import com.traffic.dao.RoleRightDao;
import com.traffic.pojo.TbRole;
import com.traffic.web.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	Log log = LogFactory.getLog(RoleServiceImpl.class);
	@Autowired
	RoleDao roleDao;
	@Autowired
	ManagerRoleDao managerRoleDao;
	@Autowired
	RoleRightDao roleRightDao;
	@Override
	public HashMap<String, Object> getRole(String roleName, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(roleName)) {
			roleName = null;
		}
		Integer listSize = roleDao.getUserSize(roleName);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbRole> tr = roleDao.getUser(roleName, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tr);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> addRole(TbRole tr) {
		ArrayList<String> gui = new ArrayList<String>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArrayList<TbRole> ui = roleDao.getAll();
			for (int i = 0; i < ui.size(); i++) {
				gui.add(ui.get(i).getRoleId());
			}
			if (gui.contains(tr.getRoleId())) {
				resultMap.put("respInfo", "角色编号已存在");
			} else {
				roleDao.addUser(tr);
				resultMap.put("respCode", 0);
				resultMap.put("respInfo", "添加新角色成功");
			}

		} catch (Exception e) {
			log.error("添加新角色时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新角色时出现异常");
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> updateRole(TbRole tr) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			roleDao.updateUser(tr);
			resultMap.put("respInfo", "更新角色信息成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("修改角色信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "修改角色信息时出现异常");
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> removeRole(String roleIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = roleIds.split(",");
		String lastId = "";
		try {
			for(String idObj : idArray){
				lastId = idObj;
				if(!"".equals(idObj)){
					managerRoleDao.deleteByRoleId(idObj);
					roleRightDao.deleteByRoleId(idObj);
					roleDao.deleteById(idObj); 
					resultMap.put("respInfo", "删除角色信息成功!");
				}
			}
			
		} catch (Exception e) {
			log.error("删除角色编号为"+lastId+"的角色信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除角色信息时出现异常!");
		}
		return resultMap;
	}
	@Override
	public ArrayList<HashMap<String, Object>> getAllRole(String roleId) {
		ArrayList<TbRole> ar = roleDao.getAllRole();
		ArrayList<String> ai = roleDao.getRoleIdByManagerId(roleId);
		ArrayList<HashMap<String, Object>> ahs = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> ahs2 = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("text", "用户角色");
		for (TbRole role : ar) {
			HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("id", role.getRoleId());
				map.put("text", role.getRoleName());
				map.put("checked", ai.contains(role.getRoleId()));
				ahs2.add(map);
		}
		root.put("children",ahs2);
		ahs.add(root);
		return ahs;
	}

}
