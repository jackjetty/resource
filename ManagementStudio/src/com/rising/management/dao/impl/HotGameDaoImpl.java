package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.HotGame;
import com.rising.management.dao.HotGameDao;

@Component("hotGameDao")
public class HotGameDaoImpl extends BaseDaoImpl implements HotGameDao{
	
	@Override
	public Integer getHotGameInfoSize(String packageName, String title) {
		String hql = "select count(*) from HotGame";
		Session session = getSession();
		Query query = null;
		if (packageName != null) {
			hql = hql + " where packageName = :packageName";
			if (title != null) {
				hql = hql + " and title = :title";
				query = session.createQuery(hql);
				query.setParameter("packageName", packageName);
				query.setParameter("title", title);
			} else {
				query = session.createQuery(hql);
				query.setParameter("packageName", packageName);
			}
		} else if (title != null) {
			hql = hql + " where title = :title";
			query = session.createQuery(hql);
			query.setParameter("title", title);
		} else {
			query = session.createQuery(hql);
		}

		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HotGame> getHotGameInfo(String packageName, String title,
			Integer start, Integer pageSize) {
		String hql = "from HotGame";
		Session session = getSession();
		Query query = null;
		if (packageName != null) {
			hql = hql + " where packageName = :packageName";
			if (title != null) {
				hql = hql + " and title = :title";
				query = session.createQuery(hql);
				query.setParameter("packageName", packageName);
				query.setParameter("title", title);
			} else {
				query = session.createQuery(hql);
				query.setParameter("packageName", packageName);
			}
		} else if (title != null) {
			hql = hql + " where title = :title";
			query = session.createQuery(hql);
			query.setParameter("title", title);
		} else {
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<HotGame>) query.list();
	}

	@Override
	public void addHotGame(HotGame hg) {
		getSession().save(hg);
		getSession().flush();
	}

	@Override
	public void updateHotGame(HotGame hg) {
		getSession().update(hg);
		getSession().flush();
	}

	@Override
	public void removeHotGame(ArrayList<Integer> ids) {
		String hql = "delete from HotGame where id in (:ids)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("ids",ids);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HotGame getHotGameById(Integer id) {
		String hql = "from HotGame where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<HotGame> aa = (ArrayList<HotGame>) query.list();
		if (aa.size() > 0) {
			return aa.get(0);
		} else
			return null;
	}
	
}
