package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Role;
import com.rising.management.dao.RoleDao;

@Component("roleDao")
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@Override
	public Integer getRoleListSize(String name) {
		String hql = "select count(*) from Role";
		Query query = null;
		if (name != null) {
			hql = hql + " where name = :name";
			query = getSession().createQuery(hql);
			query.setParameter("name", name);
		} else {
			query = getSession().createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Role> getRole(String name, Integer start, Integer pageSize) {
		String hql = "from Role";
		Query query = null;
		if (name != null) {
			hql = hql + " where name = :name";
			query = getSession().createQuery(hql);
			query.setParameter("name", name);
		} else {
			query = getSession().createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Role> ar = (ArrayList<Role>) query.list();
		return ar;
	}

	@Override
	public void removeRole(ArrayList<Integer> ai) {
		String hql = "delete from Role where id in (:ai)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ai", ai);
		query.executeUpdate();
	}

	@Override
	public void addRole(Role r) {
		getSession().save(r);
		getSession().flush();
	}

	@Override
	public void updateRole(Role r) {
		getSession().update(r);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Role> getAllRole() {
		String hql = "from Role";
		Query query = getSession().createQuery(hql);
		ArrayList<Role> ar = (ArrayList<Role>) query.list();
		return ar;
	}

}
