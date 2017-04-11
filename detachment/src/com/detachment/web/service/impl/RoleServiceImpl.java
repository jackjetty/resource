package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.DepartmentDao;
import com.detachment.dao.MenuDao;
import com.detachment.dao.Role2menuDao;
import com.detachment.dao.RoleDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.TbMenu;
import com.detachment.pojo.TbRole;
import com.detachment.pojo.TbRole2menu;
import com.detachment.pojo.TbRole2menuId;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbRole2MenuVo;
import com.detachment.pojo.vo.TbRoleVo;
import com.detachment.web.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
Log log = LogFactory.getLog(RoleServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	Role2menuDao role2menuDao;
	@Autowired
	MenuDao menuDao;
	@Override
	public ArrayList<HashMap<String, Object>> getAllRole(String userId) {
		ArrayList<TbRole> ar = roleDao.getAllRole();
		ArrayList<String> ai = userDao.getRoleIdByUserId(userId);
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
	@Override
	public HashMap<String, Object> getRoleIdAndName() {
		HashMap<String,Object> map = new HashMap<String, Object>();
		ArrayList<TbRole> tr=roleDao.getAllRole();
		ArrayList<TbDepartment> td=departmentDao.getAllDepartment();
		String roleIds="";
		String roleNames="";
		String departmentids="";
		String departmentNames="";
		for(TbRole t:tr){
			roleIds+=t.getRoleId()+",";
			roleNames+=t.getRoleName()+",";
		}
		for(TbDepartment d:td){
			departmentids+=d.getDepartmentId()+",";
			departmentNames+=d.getDepartmentName()+",";
		}
		map.put("roleId", roleIds);
		map.put("roleName", roleNames);
		map.put("departmentId", departmentids);
		map.put("departmentName", departmentNames);
		return map;
	}
	@Override
	public HashMap<String, Object> getRoles(String roleName, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(roleName)) {
			roleName = null;
		}
		Integer listSize = roleDao.getRoleSize(roleName);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbRole> tr = roleDao.getRole(roleName, start, pageSize);
		ArrayList<TbRoleVo> trVo=new ArrayList<TbRoleVo>();
		for(TbRole t:tr){
			TbRoleVo trv=new TbRoleVo(t);
			trVo.add(trv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", trVo);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> developRoleRight(String roleId,
			String menuIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<TbRole2MenuVo> ar=role2menuDao.getByRoleId(roleId);
		ArrayList<String> menuids=new ArrayList<String>();
		for(TbRole2MenuVo a:ar){
			menuids.add(a.getMenuId());
		}
		String[] menuId = menuIds.split(",");
		try{
			TbRole tr=roleDao.findById(roleId);
			for (String id : menuId) {
				if(!"".equals(id) && !menuids.contains(id)){
					TbRole2menu r = new TbRole2menu();
					TbMenu tm=menuDao.findById(id);
					TbRole2menuId tt=new TbRole2menuId(tr,tm);
					r.setId(tt);
					r.setValid(true);
					role2menuDao.save(r);
				}else{
					if(!"".equals(id)){
						menuids.remove(menuids.indexOf(id));
					}
				}
			}
			for(int i=0;i<menuids.size();i++){
				role2menuDao.deleteTbRole2Menu(roleId, menuids.get(i));
			}		
			result.put("respInfo","修改用户角色权限成功！");
		}catch(Exception e){
			e.printStackTrace();
			result.put("respInfo", "修改用户角色权限失败！");
		}
		
		return result;
	}
	@Override
	public HashMap<String, Object> addRole(TbRole tr) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			TbRole t= roleDao.findById(tr.getRoleId());
			if(t!=null){
				resultMap.put("respCode", 1);
				resultMap.put("respInfo", "角色编号已存在");
			}else {
				roleDao.save(tr);
				resultMap.put("respCode", 0);
				resultMap.put("respInfo", "添加新角色成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新角色时出现异常");
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> updateRole(String roleId,String roleName) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			TbRole tr=roleDao.findById(roleId);
			tr.setRoleName(roleName);
			roleDao.update(tr);
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
		try {
			for(String idObj : idArray){
				if(!"".equals(idObj)){
					ArrayList<TbUser> tu=userDao.getUserByRoleId(idObj);
					if(tu.size()!=0){
						resultMap.put("respInfo", "此角色已被用户使用");
						resultMap.put("respCode", "1");
					}else{
						role2menuDao.deleteTbRole2MenuByRoleId(idObj);
						roleDao.deleteRole(idObj);
						resultMap.put("respInfo", "删除角色信息成功!");
						resultMap.put("respCode", "0");
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "删除角色信息时出现异常!");
		}
		return resultMap;
	}
}
