package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.RoleDao;
import com.traffic.pojo.TbManagerRole;
import com.traffic.pojo.TbRole;
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<TbRole> implements RoleDao{

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUserSize(String roleName) {
		String hql = "from TbRole where roleId <> 'G01' and roleId <> 'R10'";
		Query query = null;
		if (roleName != null) {
			hql = hql + " and roleName = :roleName";
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
	public ArrayList<TbRole> getUser(String roleName, Integer start,
			Integer pageSize) {
		String hql = "from TbRole where roleId <> 'G01' and roleId <> 'R10'";
		Query query = null;
		if (roleName != null) {
			hql = hql + " and roleName = :roleName";
			query = getSession().createQuery(hql);
			query.setParameter("roleName", roleName);
		} else {
			query = getSession().createQuery(hql);
		}
		ArrayList<TbRole> tu = (ArrayList<TbRole>) query.list();
		return tu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbRole> getAll() {
		String hql = "from TbRole";
		Query query = getSession().createQuery(hql);
		ArrayList<TbRole> tu = (ArrayList<TbRole>) query.list();
		return tu;
	}

	@Override
	public void addUser(TbRole tr) {
		getSession().save(tr);
		getSession().flush();
	}

	@Override
	public void updateUser(TbRole tr) {
		getSession().update(tr);
		getSession().flush();
	}

	@Override
	public void deleteById(String roleId) {
		String hql = "delete from TbRole where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbRole> getAllRole() {
		String hql = "from TbRole where roleId <> 'G01' and roleId <> 'R10'";
		Query query = getSession().createQuery(hql);
		ArrayList<TbRole> ar = (ArrayList<TbRole>) query.list();
		return ar;
	}

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
	public String getRoleName(String roleId) {
		String hql ="select roleName from TbRole where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		String roleName = (String) query.list().get(0);
		return roleName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getRoleNameById() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from TbRole";
		Query query = getSession().createQuery(hql);
		ArrayList<TbRole> m = (ArrayList<TbRole>) query.list();
		for (TbRole ta : m) {
			map.put(ta.getRoleId(), ta.getRoleName());
		}
		return map;
	}

}
