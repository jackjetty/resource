package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.ManagerRole;
import com.rising.management.bean.Role;
import com.rising.management.dao.ManagerRoleDao;

@Component("managerRoleDao")
public class ManagerRoleDaoImpl extends BaseDaoImpl implements ManagerRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getRoleIdByManagerId(Integer managerId) {
		String hql = "from ManagerRole where managerId = :managerId";
		Query query = getSession().createQuery(hql);
		query.setParameter("managerId", managerId);
		ArrayList<ManagerRole> amr = (ArrayList<ManagerRole>) query.list();
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (ManagerRole managerRole : amr) {
			ai.add(managerRole.getRoleId());
		}
		return ai;
	}

	@Override
	public void deleteManagerRole(ManagerRole role) {
		String hql = "delete from ManagerRole where roleId =:roleId and managerId =:managerId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", role.getRoleId());
		query.setParameter("managerId", role.getManagerId());
		query.executeUpdate();
	}

	@Override
	public void addManagerRole(ManagerRole managerRole) {
		getSession().save(managerRole);
		getSession().flush();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Role getRoleManagerId(Integer managerId) {
		String hql = "select new Role(a.name) from Role a,ManagerRole b where b.managerId = :managerId and b.roleId = a.id";
		Query query = getSession().createQuery(hql);
		query.setParameter("managerId", managerId);
		ArrayList<Role> ar = (ArrayList<Role>) query.list();
		if (ar.size() > 0) {
			return ar.get(0);
		} else {
			return null;
		}
	}

}
