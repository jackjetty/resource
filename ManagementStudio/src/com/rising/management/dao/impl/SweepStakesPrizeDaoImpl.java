package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.SweepStakesPrize;
import com.rising.management.dao.SweepStakesPrizeDao;

@Component("sweepStakesPrizeDao")
public class SweepStakesPrizeDaoImpl extends BaseDaoImpl implements
		SweepStakesPrizeDao {

	@Override
	public Integer getSweepStakesPrizeSize(Integer activityId, Integer prizeId) {
		String hql = "select count(*) from SweepStakesPrize";
		Session session = getSession();
		Query query = null;
		if (activityId != null) {
			hql = hql + " where activityId = :activityId";
			if (prizeId != null) {
				hql = hql + " and prizeId = :prizeId";
				query = session.createQuery(hql);
				query.setParameter("activityId", activityId);
				query.setParameter("prizeId", prizeId);
			} else {
				query = session.createQuery(hql);
				query.setParameter("activityId", activityId);
			}
		} else if (prizeId != null) {
			hql = hql + " where prizeId = :prizeId";
			query = session.createQuery(hql);
			query.setParameter("prizeId", prizeId);
		} else {
			query = session.createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SweepStakesPrize> getSweepStakesPrize(Integer activityId,
			Integer prizeId, Integer start, Integer pageSize) {
		String hql = "from SweepStakesPrize";
		Session session = getSession();
		Query query = null;
		if (activityId != null) {
			hql = hql + " where activityId = :activityId";
			if (prizeId != null) {
				hql = hql + " and prizeId = :prizeId order by activityId";
				query = session.createQuery(hql);
				query.setParameter("activityId", activityId);
				query.setParameter("prizeId", prizeId);
			} else {
				hql = hql + " order by id";
				query = session.createQuery(hql);
				query.setParameter("activityId", activityId);
			}
		} else if (prizeId != null) {
			hql = hql + " where prizeId = :prizeId";
			hql = hql + " order by activityId";
			query = session.createQuery(hql);
			query.setParameter("prizeId", prizeId);
		} else {
			hql = hql + " order by activityId";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<SweepStakesPrize>) query.list();
	}

	@Override
	public void addSweepStakesPrize(SweepStakesPrize sp) {
		getSession().save(sp);
		getSession().flush();
	}

	@Override
	public void updateSweepStakesPrize(SweepStakesPrize sp) {
		getSession().merge(sp);
		getSession().flush();
	}

	@Override
	public void removeSweepStakesPrize(ArrayList<Integer> ids) {
		String hql = "delete from SweepStakesPrize where id in (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer SumToPrizeNumber(Integer prizeId) {
		String sql = "select sum(prizenumber) as AllNumbers from RS_SWEEPSTAKESPRIZE where prizeid= :prizeId";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("prizeId", prizeId);
		ArrayList<Object> ao = (ArrayList<Object>) query.list();
		Integer AllNumbers = null;
		if (ao.get(0) == null) {
			return 0;
		} else {
			String AllNumbers1 = ao.get(0).toString();
			AllNumbers = Integer.parseInt(AllNumbers1);
			return AllNumbers;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public SweepStakesPrize getSweepStakesPrizeById(Integer id) {
		String hql = "from SweepStakesPrize where id = :id";
		ArrayList<SweepStakesPrize> ap = (ArrayList<SweepStakesPrize>) getSession()
				.createQuery(hql).setParameter("id", id).list();
		if (ap != null && ap.size() > 0) {
			return ap.get(0);
		} else
			return null;
	}

}
