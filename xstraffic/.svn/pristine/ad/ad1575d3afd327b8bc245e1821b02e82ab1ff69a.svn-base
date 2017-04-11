package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.RoleRightDao;
import com.traffic.pojo.TbRoleRight;
@Repository("roleRightDao")
public class RoleRightDaoImpl extends BaseDaoImpl<TbRoleRight> implements RoleRightDao{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbRoleRight> getByRoleId(String roleId) {
		String hql = "from TbRoleRight where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		ArrayList<TbRoleRight> ar = (ArrayList<TbRoleRight>) query.list();
		return ar;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getMenuIdsByRoleId(String roleId) {
		String hql = "from TbRoleRight where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ArrayList<TbRoleRight> ar = (ArrayList<TbRoleRight>) query.list();
		for (TbRoleRight roleRight : ar) {
			ai.add(roleRight.getMenuId());
		}
		return ai;
	}

	@Override
	public void addRoleRight(TbRoleRight r) {
		getSession().save(r);
		getSession().flush();
		
	}

	@Override
	public void deleteRoleRight(TbRoleRight r) {
		String hql = "delete from TbRoleRight where roleId = :roleId and menuId = :menuId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", r.getRoleId());
		query.setParameter("menuId", r.getMenuId());
		query.executeUpdate();
		
	}

	@Override
	public void deleteByRoleId(String roleId) {
		String hql = "delete from TbRoleRight where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.executeUpdate();
		
	}

}
