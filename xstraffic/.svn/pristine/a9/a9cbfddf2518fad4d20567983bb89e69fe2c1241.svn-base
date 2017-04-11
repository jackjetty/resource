package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.RoleRightDao;
import com.traffic.pojo.TbRoleRight;
import com.traffic.web.service.RoleRightService;

@Service("roleRightService")
public class RoleRightServiceImpl implements RoleRightService {
@Autowired
RoleRightDao roleRightDao;
	@Override
	public HashMap<String, Object> developRoleRight(String roleId,
			String menuIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Integer> menuids = roleRightDao.getMenuIdsByRoleId(roleId);
		String[] menuId = menuIds.split(",");
		try{
			for (String id : menuId) {
				if(!"".equals(id) && !menuids.contains(Integer.parseInt(id))){
					TbRoleRight r = new TbRoleRight();
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
				TbRoleRight r = new TbRoleRight();
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

}
