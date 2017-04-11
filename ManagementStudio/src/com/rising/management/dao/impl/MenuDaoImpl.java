package com.rising.management.dao.impl;

import java.util.ArrayList;



import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Menu;
import com.rising.management.dao.MenuDao;

@Component("menuDao")
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getMenuByManagerId(Integer managerId) {

		String hql = "from Menu a where a.menuId in (" +
				"select b.menuId from RoleRight b,ManagerRole c where c.managerId = :managerId and c.roleId = b.roleId)";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("managerId", managerId);
		ArrayList<Menu> am = (ArrayList<Menu>) query.list();
		return am;
	}

	@Override
	public Integer getMenuListSize(String id, String text) {
		String hql = "select count(*) from Menu";
		Session session = getSession();
		Query query = null;
		if (id != null) {
			hql = hql + " where id = :id";
			if (text != null) {
				hql = hql + " and text = :text";
				query = session.createQuery(hql);
				query.setParameter("id", id);
				query.setParameter("text", text);
			} else {
				query = session.createQuery(hql);
				query.setParameter("id", id);
			}
		} else if (text != null) {
			hql = hql + " where text = :text";
			query = session.createQuery(hql);
			query.setParameter("text", text);
		} else {
			query = session.createQuery(hql);
		}

		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getMenu(String id, String text, Integer start,
			Integer pageSize) {
		String hql = "from Menu";
		Session session = getSession();
		Query query = null;
		if (id != null) {
			hql = hql + " where id = :id";
			if (text != null) {
				hql = hql + " and text = :text order by id";
				query = session.createQuery(hql);
				query.setParameter("id", id);
				query.setParameter("text", text);
			} else {
				hql = hql + " order by id";
				query = session.createQuery(hql);
				query.setParameter("id", id);
			}
		} else if (text != null) {
			hql = hql + " where text = :text";
			hql = hql + " order by id";
			query = session.createQuery(hql);
			query.setParameter("text", text);
		} else {
			hql = hql + " order by id";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<Menu>) query.list();
	}

	@Override
	public void addMenu(Menu m) {
		getSession().save(m);
		getSession().flush();
	}

	@Override
	public void updateMenu(Menu m) {
		getSession().merge(m);
		//getSession().update(m);
		getSession().flush();
	}

	@Override
	public void removeMenu(ArrayList<Integer> ai) {
		String hql = "delete from Menu where menuId in (:ai)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("ai",ai);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getAllMenu() {
		String hql = "from Menu";
		Query query  = getSession().createQuery(hql);
		ArrayList<Menu> am = (ArrayList<Menu>) query.list();
		return am;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getMenuById(String id) {
		String hql="from Menu where id =:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<Menu> listmu=(ArrayList<Menu>) query.list();
		return listmu;
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getIdAndText() {
		String hql = "from Menu";
		Query query  = getSession().createQuery(hql);
		ArrayList<Menu> am = (ArrayList<Menu>) query.list();
		
		return am;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getMenuByText(String text) {
		String hql="from Menu where text =:text";
		Query query=getSession().createQuery(hql);
		query.setParameter("text", text);
		ArrayList<Menu> listmu=(ArrayList<Menu>) query.list();
		return listmu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Menu> getMenuLikeId(String id) {
		id=id+"%";
		String hql="from Menu where id like (:id)";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<Menu> listmu=(ArrayList<Menu>) query.list();
		return listmu;
	}

}



