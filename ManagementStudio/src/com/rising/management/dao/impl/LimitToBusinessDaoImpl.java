package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.LimitToBusiness;
import com.rising.management.dao.LimitToBusinessDao;

@Component("limitToBusinessDao")
public class LimitToBusinessDaoImpl extends BaseDaoImpl implements
		LimitToBusinessDao {

	@Override
	public Integer getLimitBusSize(Integer limitId, Integer busId) {
		String hql = "select count(*) from LimitToBusiness";
		Session session = getSession();
		Query query = null;
		if (limitId != null) {
			hql = hql + " where limitId = :limitId";
			if (busId != null) {
				hql = hql + " and busId = :busId";
				query = session.createQuery(hql);
				query.setParameter("limitId", limitId);
				query.setParameter("busId", busId);
			} else {
				query = session.createQuery(hql);
				query.setParameter("limitId", limitId);
			}
		} else if (busId != null) {
			hql = hql + " where busId = :busId";
			query = session.createQuery(hql);
			query.setParameter("busId", busId);
		} else {
			query = session.createQuery(hql);
		}

		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LimitToBusiness> getLimitBuss(Integer limitId,
			Integer busId, Integer start, Integer pageSize) {
		String hql = "from LimitToBusiness";
		Session session = getSession();
		Query query = null;
		if (limitId != null) {
			hql = hql + " where limitId = :limitId";
			if (busId != null) {
				hql = hql + " and busId = :busId order by id";
				query = session.createQuery(hql);
				query.setParameter("limitId", limitId);
				query.setParameter("busId", busId);
			} else {
				hql = hql + " order by id";
				query = session.createQuery(hql);
				query.setParameter("limitId", limitId);
			}
		} else if (busId != null) {
			hql = hql + " where busId = :busId";
			hql = hql + " order by id";
			query = session.createQuery(hql);
			query.setParameter("busId", busId);
		} else {
			hql = hql + " order by id";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<LimitToBusiness>) query.list();
	}

	@Override
	public void addLimitToBus(LimitToBusiness lb) {
		getSession().save(lb);
		getSession().flush();

	}

	@Override
	public void updateLimitToBus(LimitToBusiness lb) {
		getSession().update(lb);
		getSession().flush();

	}

	@Override
	public void removeLimitToBus(ArrayList<Integer> ids) {
		String hql = "delete from LimitToBusiness where id in (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();

	}

}
