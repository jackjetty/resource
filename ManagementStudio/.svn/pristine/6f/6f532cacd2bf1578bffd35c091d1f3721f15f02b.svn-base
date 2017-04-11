package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.PrizeCode;
import com.rising.management.dao.PrizeCodeDao;

@Component("prizeCodeDao")
public class PrizeCodeDaoImpl extends BaseDaoImpl implements PrizeCodeDao {

	@Override
	public Integer getPrizeCodeListSize(Integer prizeId, String status) {
		String hql = "select count(*) from PrizeCode";
		Session session = getSession();
		Query query = null;
		if (prizeId == null) {
			if ("notSend".equals(status)) {
				hql = hql +  " where sended = 'no'";
			} else {
				hql = hql +  " where sended = 'yes'";
			}
			query = session.createQuery(hql);
		} else {
			if (status.equals("notSend")) {
				hql = hql + " where prizeId = :prizeId and sended = 'no'";
			} else {
				hql = hql + " where prizeId = :prizeId and sended = 'yes'";
			}
			query = session.createQuery(hql);
			query.setParameter("prizeId", prizeId);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PrizeCode> getPrizeCode(Integer prizeId, String status,
			Integer start, Integer pageSize) {
		String hql = "from PrizeCode";
		Session session = getSession();
		Query query = null;
		if (prizeId == null) {
			if (status.equals("notSend")) {
				hql = hql + " where sended = 'no'";
			} else {
				hql = hql + " where sended = 'yes'";
			}
			query = session.createQuery(hql);
		} else {
			if (status.equals("notSend")) {
				hql = hql + " where prizeId = :prizeId and sended = 'no'";
			} else {
				hql = hql + " where prizeId = :prizeId and sended = 'yes'";
			}
			query = session.createQuery(hql);
			query.setParameter("prizeId", prizeId);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<PrizeCode> ap = (ArrayList<PrizeCode>) query.list();
		return ap;
	}

	@Override
	public void removePrizeCode(ArrayList<Integer> ai) {
		String hql = "delete from PrizeCode where id in (:ai)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ai", ai);
		query.executeUpdate();

	}

	@Override
	public void add(ArrayList<PrizeCode> ap) {
		Session session = getSession();
		int i = 1;
		for (PrizeCode prizeCode : ap) {
			session.save(prizeCode);
			if(i%50==0){
				session.flush();
				session.clear();
			}
			i++;
		}
	}

}
