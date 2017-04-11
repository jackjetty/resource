package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.ManagerRoleDao;
import com.traffic.pojo.TbManagerRole;
import com.traffic.pojo.TbRole;

@Repository("managerRoleDao")
public class ManagerRoleDaoImpl extends BaseDaoImpl<TbManagerRole> implements ManagerRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getRoleIdByManagerId(String userId) {
		String hql = "from TbManagerRole where userId = :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		ArrayList<TbManagerRole> amr = (ArrayList<TbManagerRole>) query.list();
		ArrayList<String> ai = new ArrayList<String>();
		for (TbManagerRole managerRole : amr) {
			ai.add(managerRole.getRoleId());
		}
		return ai;
	}

	@Override
	public void addManagerRole(TbManagerRole managerRole) {
		getSession().save(managerRole);
		getSession().flush();
		
	}

	@Override
	public void deleteManagerRole(TbManagerRole role) {
		String hql = "delete from TbManagerRole where roleId =:roleId and userId =:userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", role.getRoleId());
		query.setParameter("userId", role.getUserId());
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbRole getRoleRoleId(String userId) {
		String hql = "select new TbRole(a.roleId, a.roleName, a.status) from TbRole a,TbManagerRole b where b.userId = :userId and b.roleId = a.roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		ArrayList<TbRole> ar = (ArrayList<TbRole>) query.list();
		if (ar.size() > 0) {
			return ar.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(String userId) {
		String hql = "delete from TbManagerRole where userId = :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.executeUpdate();
		
	}

	@Override
	public void deleteByRoleId(String roleId) {
		String hql = "delete from TbManagerRole where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.executeUpdate();
		
	}

}
