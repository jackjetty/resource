package com.detachment.dao.impl;
import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.RoleDao;
import com.detachment.pojo.TbRole;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<TbRole> implements  RoleDao{


	@Override
	public TbRole findRoleById(String roleId) {
		TbRole role=findById(roleId);
		return role;
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbRole> getAllRole() {
		String hql = "from TbRole";
		Query query = getSession().createQuery(hql);
		ArrayList<TbRole> ar = (ArrayList<TbRole>) query.list();
		return ar;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getRoleSize(String roleName) {
		String hql = "from TbRole ";
		Query query = null;
		if (roleName != null) {
			hql = hql + " where roleName = :roleName";
			query = getSession().createQuery(hql);
			query.setParameter("roleName", roleName);
		} else {
			query = getSession().createQuery(hql);
		}
		ArrayList<TbRole> tu = (ArrayList<TbRole>) query.list();
		return tu.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbRole> getRole(String roleName, Integer start,
			Integer pageSize) {
		String hql = "from TbRole";
		Query query = null;
		if (roleName != null) {
			hql = hql + " where roleName = :roleName";
			query = getSession().createQuery(hql);
			query.setParameter("roleName", roleName);
		} else {
			query = getSession().createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbRole> tu = (ArrayList<TbRole>) query.list();
		return tu;
	}


	


	@Override
	public void deleteRole(String roleId) {
		String hql = "delete from TbRole where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.executeUpdate();
		
	}
	
	
	 
}