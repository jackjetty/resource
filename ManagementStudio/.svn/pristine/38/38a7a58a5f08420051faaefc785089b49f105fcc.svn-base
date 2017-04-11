package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.PayLimit;
import com.rising.management.dao.PayLimitDao;

@Component("payLimitDao")
public class PayLimitDaoImpl extends BaseDaoImpl implements PayLimitDao {

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getLimitIdandLimitName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from PayLimit";
		Query query = getSession().createQuery(hql);
		ArrayList<PayLimit> pl = (ArrayList<PayLimit>) query.list();
		for (PayLimit p : pl) {
			map.put(p.getLimitId().toString(), p.getLimitName());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PayLimit> getPayLimit(String limitType, Integer start,
			Integer pageSize) {
		String hql = " from PayLimit";
		Query query = null;
		if (limitType == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " where limitType = :limitType";
			query = getSession().createQuery(hql);
			query.setParameter("limitType", limitType);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<PayLimit> pl = (ArrayList<PayLimit>) query.list();
		return pl;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PayLimit> getPayLimit() {
		String hql = "from PayLimit";
		Query query = getSession().createQuery(hql);
		ArrayList<PayLimit> pl = (ArrayList<PayLimit>) query.list();
		return pl;
	}

	@Override
	public void deleteById(Integer limitId) {
		String hql = " delete from PayLimit where limitId = :limitId";
		Query query = getSession().createQuery(hql);
		query.setParameter("limitId", limitId);
		query.executeUpdate();
	}

	@Override
	public void updatePayLimit(PayLimit p) {
		getSession().update(p);
		getSession().flush();

	}

	@Override
	public void addpayLimit(PayLimit p) {
		getSession().save(p);
		getSession().flush();

	}

	@Override
	public Integer getPayLimitListSize(String limitType) {
		String hql = " select count(*) from PayLimit";
		Query query = null;
		if (limitType == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " where limitType = :limitType";
			query = getSession().createQuery(hql);
			query.setParameter("limitType", limitType);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();

	}
}
