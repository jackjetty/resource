package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.traffic.dao.ManagerDao;
import com.traffic.pojo.TbUser;
@Repository("managerDao")
public class ManagerDaoImpl extends BaseDaoImpl<TbUser> implements ManagerDao {
	@SuppressWarnings("unchecked")
	@Override
	public TbUser findManagerByName(String username) {
		Session session = getSession();
		String hql = "from TbUser where userId = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		ArrayList<TbUser> am = (ArrayList<TbUser>) query.list();
		if (am.size() > 0) {
			return am.get(0);
		} else{
			return null;
		}
	}

	@Override
	public void modifyPassword(TbUser m) {
		getSession().update(m);
		getSession().flush();
	}

}
