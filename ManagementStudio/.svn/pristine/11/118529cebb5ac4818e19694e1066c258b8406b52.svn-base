package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.RoleRight;
import com.rising.management.dao.RoleRightDao;
import com.rising.management.service.RoleRightService;


@Service("roleRightService")
public class RoleRightServiceImpl implements RoleRightService {
	Log log = LogFactory.getLog(RoleRightServiceImpl.class);

	@Autowired
	RoleRightDao roleRightDao;

	@Override
	public HashMap<String, Object> developRoleRight(Integer roleId,
			String menuIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Integer> menuids = roleRightDao.getMenuIdsByRoleId(roleId);
		String[] menuId = menuIds.split(",");
		try{
			for (String id : menuId) {
				if(!"".equals(id) && !menuids.contains(Integer.parseInt(id))){
					RoleRight r = new RoleRight();
					r.setMenuId(Integer.parseInt(id));
					r.setRoleId(roleId);
					roleRightDao.addRoleRight(r);
				}else{
					if(!"".equals(id)){
						menuids.remove(menuids.indexOf(Integer.parseInt(id)));
					}
				}
			}
			for(int i=0;i<menuids.size();i++){
				RoleRight r = new RoleRight();
				r.setMenuId(menuids.get(i));
				r.setRoleId(roleId);
				roleRightDao.deleteRoleRight(r);
			}		
			result.put("respInfo","修改用户角色权限成功！");
		}catch(Exception e){
			e.printStackTrace();
			result.put("respInfo", "修改用户角色权限失败！");
		}
		
		return result;
	}

	@Override
	public void deleteRoleRightByMenuId(Integer menuId) {
		roleRightDao.deleteRoleRightByMenuId(menuId);
	}


}
