package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.RoleRight;
import com.rising.management.dao.RoleRightDao;

@Component("roleRightDao")
public class RoleRightDaoImpl extends BaseDaoImpl implements RoleRightDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RoleRight> getByRoleId(Integer roleId) {
		String hql = "from RoleRight where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		ArrayList<RoleRight> ar = (ArrayList<RoleRight>) query.list();
		return ar;
	}

	@Override
	public void addRoleRight(RoleRight r) {
		getSession().save(r);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getMenuIdsByRoleId(Integer roleId) {
		String hql = "from RoleRight where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ArrayList<RoleRight> ar = (ArrayList<RoleRight>) query.list();

		for (RoleRight roleRight : ar) {
			ai.add(roleRight.getMenuId());
		}
		return ai;
	}

	@Override
	public void deleteRoleRight(RoleRight r) {
		String hql = "delete from RoleRight where roleId = :roleId and menuId = :menuId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", r.getRoleId());
		query.setParameter("menuId", r.getMenuId());
		query.executeUpdate();
	}

	@Override
	public void deleteRoleRightByMenuId(Integer MenuId) {
		String hql="delete from RoleRight where menuId = :menuId";
		Query query = getSession().createQuery(hql);
		query.setParameter("menuId", MenuId);
		query.executeUpdate();
	}

}
