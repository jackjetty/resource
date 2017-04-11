package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Manager;
import com.rising.management.dao.ManagerDao;

@Component("managerDao")
public class ManagerDaoImpl extends BaseDaoImpl implements ManagerDao {

	@SuppressWarnings("unchecked")
	@Override
	public Manager findManagerByName(String username) {
		Session session = getSession();
		String hql = "from Manager where name = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		ArrayList<Manager> am = (ArrayList<Manager>) query.list();
		if (am.size() > 0) {
			return am.get(0);
		} else
			return null;
	}

	@Override
	public void modifyPassword(Manager m) {
		getSession().update(m);
		getSession().flush();
	}

	@Override
	public void deleteById(Integer managerId) {
		String hql = "delete from Manager where managerId = :managerId";
		Query query = getSession().createQuery(hql);
		query.setParameter("managerId", managerId);
		query.executeUpdate();
	}

	@Override
	public Integer getManagerListSize(String username) {
		String hql = "";
		Query query = null;
		if (username == null) {
			hql = "select count(*) from Manager";
			query = getSession().createQuery(hql);
		} else {
			hql = "select count(*) from Manager where name = :name";
			query = getSession().createQuery(hql);
			query.setParameter("name", username);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Manager> getManager(String username, Integer start,
			Integer pageSize) {
		String hql = "";
		Query query = null;
		if (username == null) {
			hql = "from Manager";
			query = getSession().createQuery(hql);
		} else {
			hql = "from Manager where name = :name";
			query = getSession().createQuery(hql);
			query.setParameter("name", username);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Manager> am = (ArrayList<Manager>) query.list();
		return am;
	}

	@Override
	public void addManager(Manager m) {
		getSession().save(m);
		getSession().flush();

	}

	@Override
	public void updateManager(Manager m) {
		String hql = "update Manager set name = :name,phoneNumber = :phoneNumber,email = :email where managerId = :managerId";
		Query query = getSession().createQuery(hql);
		query.setParameter("name",m.getName());
		query.setParameter("email",m.getEmail());
		query.setParameter("phoneNumber", m.getPhoneNumber());
		query.setParameter("managerId",m.getManagerId());
		query.executeUpdate();
	}

}
