package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




import com.rising.management.bean.PhoneMessage;
import com.rising.management.dao.PhoneMessageDao;

@Component("phoneMessageDao")
public class PhoneMessageDaoImpl extends BaseDaoImpl implements PhoneMessageDao{
	
	@Override
	public Integer getPhoneMessageSize(String use, String describe) {
		String hql = "select count(*) from PhoneMessage";
		Session session = getSession();
		Query query = null;
		if (use != null) {
			hql = hql + " where use = :use";
			if (describe != null) {
				hql = hql + " and describe = :describe";
				query = session.createQuery(hql);
				query.setParameter("use", use);
				query.setParameter("describe", describe);
			} else {
				query = session.createQuery(hql);
				query.setParameter("use", use);
			}
		} else if (describe != null) {
			hql = hql + " where describe = :describe";
			query = session.createQuery(hql);
			query.setParameter("describe", describe);
		} else {
			query = session.createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PhoneMessage> getPhoneMessage(String use, String describe,
			Integer start, Integer pageSize) {
		String hql = "from PhoneMessage";
		Session session = getSession();
		Query query = null;
		if (use != null) {
			hql = hql + " where use = :use";
			if (describe != null) {
				hql = hql + " and describe = :describe order by id";
				query = session.createQuery(hql);
				query.setParameter("use", use);
				query.setParameter("describe", describe);
			} else {
				hql = hql + " order by id";
				query = session.createQuery(hql);
				query.setParameter("use", use);
			}
		} else if (describe != null) {
			hql = hql + " where describe = :describe";
			hql = hql + " order by id";
			query = session.createQuery(hql);
			query.setParameter("describe", describe);
		} else {
			hql = hql + " order by id";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<PhoneMessage>) query.list();
	}

	@Override
	public void addPhoneMessage(PhoneMessage pm) {
		getSession().save(pm);
		getSession().flush();
		
	}

	@Override
	public void updatePhoneMessage(PhoneMessage pm) {
		getSession().update(pm);
		getSession().flush();
	}

	@Override
	public void removePhoneMessage(ArrayList<Integer> pms) {
		String hql = "delete from PhoneMessage where id in (:pms)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("pms",pms);
		query.executeUpdate();
		
	}

}
