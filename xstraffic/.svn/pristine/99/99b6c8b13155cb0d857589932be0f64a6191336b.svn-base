package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.traffic.dao.MenuDao;
import com.traffic.pojo.TbMenu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<TbMenu> implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbMenu> getAllMenu() {
		String hql = "from TbMenu";
		Query query  = getSession().createQuery(hql);
		ArrayList<TbMenu> am = (ArrayList<TbMenu>) query.list();
		return am;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbMenu> getMenuByUserId(String userId) {
		String hql = "from TbMenu a where a.id in (" +
				"select b.menuId from TbRoleRight b,TbManagerRole c where c.userId = :userId and c.roleId = b.roleId)";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		ArrayList<TbMenu> am = (ArrayList<TbMenu>) query.list();
		return am;
	}

}
