package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.SalesInformation;
import com.rising.management.dao.SalesInformationDao;

@Component("salesInformationDao")
public class SalesInformationDaoImpl extends BaseDaoImpl implements
		SalesInformationDao {

	@Override
	public Integer getSalesListSize(String actName, String actStartTime,
			String actEndTime) {
		String hql = "select count(*) from SalesInformation";
		Session session = getSession();
		Query query = null;
		if (actName != null) {
			hql = hql + " where actName = :actName";
			if (actStartTime != null && actEndTime == null) {
				hql = hql + " and actStartTime >= :actStartTime";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
				query.setParameter("actStartTime", actStartTime);
			} else if (actStartTime == null && actEndTime != null) {
				hql = hql + " and actEndTime >= :actEndTime";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
				query.setParameter("actEndTime", actEndTime);
			} else if (actStartTime != null && actEndTime != null) {
				hql = hql
						+ " and actStartTime >= :actStartTime and actEndTime >= :actEndTime";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
				query.setParameter("actStartTime", actStartTime);
				query.setParameter("actEndTime", actEndTime);
			} else {
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
			}
		} else if (actStartTime != null) {
			hql = hql + " where actStartTime >= :actStartTime";
			if (actEndTime != null) {
				hql = hql + " and actEndTime >= :actEndTime";
				query = session.createQuery(hql);
				query.setParameter("actStartTime", actStartTime);
				query.setParameter("actEndTime", actEndTime);
			} else {
				query = session.createQuery(hql);
				query.setParameter("actStartTime", actStartTime);
			}
		} else if (actEndTime != null) {
			hql = hql + " where actEndTime >= :actEndTime";
			query = session.createQuery(hql);
			query.setParameter("actEndTime", actEndTime);
		} else {
			query = session.createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SalesInformation> getSalesInfo(String actName,
			String actStartTime, String actEndTime, Integer start,
			Integer pageSize) {
		String hql = "from SalesInformation";
		Session session = getSession();
		Query query = null;
		if (actName != null) {
			hql = hql + " where actName = :actName";
			if (actStartTime != null && actEndTime == null) {
				hql = hql + " and actStartTime >= :actStartTime";
				hql = hql + " order by sortCode";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
				query.setParameter("actStartTime", actStartTime);
			} else if (actStartTime == null && actEndTime != null) {
				hql = hql + " and actEndTime >= :actEndTime";
				hql = hql + " order by sortCode";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
				query.setParameter("actEndTime", actEndTime);
			} else if (actStartTime != null && actEndTime != null) {
				hql = hql
						+ " and actStartTime >= :actStartTime and actEndTime <= :actEndTime";
				hql = hql + " order by sortCode";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
				query.setParameter("actStartTime", actStartTime);
				query.setParameter("actEndTime", actEndTime);
			} else {
				hql = hql + " order by sortCode";
				query = session.createQuery(hql);
				query.setParameter("actName", actName);
			}
		} else if (actStartTime != null) {
			hql = hql + " where actStartTime >= :actStartTime";
			if (actEndTime != null) {
				hql = hql + " and actEndTime >= :actEndTime";
				hql = hql + " order by sortCode";
				query = session.createQuery(hql);
				query.setParameter("actStartTime", actStartTime);
				query.setParameter("actEndTime", actEndTime);
			} else {
				hql = hql + " order by sortCode";
				query = session.createQuery(hql);
				query.setParameter("actStartTime", actStartTime);
			}
		} else if (actEndTime != null) {
			hql = hql + " where actEndTime >= :actEndTime";
			hql = hql + " order by sortCode";
			query = session.createQuery(hql);
			query.setParameter("actEndTime", actEndTime);
		} else {
			hql = hql + " order by sortCode";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<SalesInformation>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SalesInformation> getSalesInfoRmationByactName(
			String actName) {
		String hql = "from SalesInformation where actName =:actName";
		Query query = getSession().createQuery(hql);
		query.setParameter("actName", actName);

		ArrayList<SalesInformation> listmu = (ArrayList<SalesInformation>) query
				.list();
		return listmu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SalesInformation> getSalesInfoRmationAll() {
		String hql = "from SalesInformation";
		Query query = getSession().createQuery(hql);
		ArrayList<SalesInformation> am = (ArrayList<SalesInformation>) query
				.list();
		return am;
	}

	@Override
	public void addSalesInformation(SalesInformation sales) {
		getSession().save(sales);
		getSession().flush();
	}

	@Override
	public void updateSalesInformation(SalesInformation sales) {
		getSession().update(sales);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SalesInformation> getSalesInfoBySortCode(Integer sortCode) {
		String hql = "from SalesInformation where sortCode>= :sortCode";
		Query query = getSession().createQuery(hql);
		query.setParameter("sortCode", sortCode);
		ArrayList<SalesInformation> am = (ArrayList<SalesInformation>) query
				.list();
		return am;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SalesInformation> getSalesInfoByInforId(Integer inforId) {
		String hql = "from SalesInformation where inforId = :inforId";
		Query query = getSession().createQuery(hql);
		query.setParameter("inforId", inforId);
		ArrayList<SalesInformation> am = (ArrayList<SalesInformation>) query
				.list();
		return am;
	}

	@Override
	public void removeSalesInformation(ArrayList<Integer> sales) {
		String hql = "delete from SalesInformation where inforId in (:sales)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("sales", sales);
		query.executeUpdate();
	}

	@Override
	public void changeSortCode(Integer inforId, Integer sortCode) {
		String hql = "update SalesInformation set sortCode = :sortCode where inforId= :inforId";
		Query query = getSession().createQuery(hql);
		query.setParameter("sortCode", sortCode);
		query.setParameter("inforId", inforId);
		query.executeUpdate();
	}

	@Override
	public Integer getMaxSortCode() {
		String hql = "select max(a.sortCode) from SalesInformation a ";
		Query query = getSession().createQuery(hql);
		Integer max = (Integer) query.uniqueResult();
		return max;
	}

}
