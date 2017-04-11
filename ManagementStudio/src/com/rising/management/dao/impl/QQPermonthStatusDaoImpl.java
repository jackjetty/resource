package com.rising.management.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.QQPerMonthStatus;
import com.rising.management.dao.QQPermonthStatusDao;

@Component("qQPermonthStatusDao")
public class QQPermonthStatusDaoImpl extends BaseDaoImpl implements
		QQPermonthStatusDao {

	@Override
	public Integer getQQPermonthStatusListSize(String phoneNumber, String status) {
		Session session = getSession();
		Query query = null;
		String hql = "select count(*) from QQPerMonthStatus";
		if (phoneNumber == null) {
			if ("1".equals(status) || "2".equals(status)) {
				hql = hql + "1 where status = :status";
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				System.out.println(hql);
				query = session.createQuery(hql);
				query.setParameter("status", status);
			} else if ("3".equals(status) || "4".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				if ("3".equals(status)) {
					hql = hql + "1 where month = :month";
				} else if ("4".equals(status)) {
					hql = hql + "1 where month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("month", month);
			} else if ("11".equals(status) || "22".equals(status)) {
				status = status.substring(0, 1);
				hql = hql + "2 where status = :status";
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				query = session.createQuery(hql);
				query.setParameter("status", status);
			} else if ("33".equals(status) || "44".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				status = status.substring(0, 1);
				if ("3".equals(status)) {
					hql = hql + "2 where month = :month";
				} else if ("4".equals(status)) {
					hql = hql + "2 where month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("month", month);
			}
		} else {
			if ("1".equals(status) || "2".equals(status)) {
				hql = hql
						+ "1 where phoneNumber like concat(:phoneNumber,'%') and status = :status";
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("status", status);
			} else if ("3".equals(status) || "4".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				hql = hql
						+ "1 where phoneNumber like concat(:phoneNumber,'%') and";
				if ("3".equals(status)) {
					hql = hql + " month = :month";
				} else if ("4".equals(status)) {
					hql = hql + " month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("month", month);
			} else if ("11".equals(status) || "22".equals(status)) {
				hql = hql
						+ "2 where phoneNumber like concat(:phoneNumber,'%') and status = :status";
				status = status.substring(0, 1);
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("status", status);
			} else if ("33".equals(status) || "44".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				hql = hql
						+ "2 where phoneNumber like concat(:phoneNumber,'%') and";
				status = status.substring(0, 1);
				if ("3".equals(status)) {
					hql = hql + " month = :month";
				} else if ("4".equals(status)) {
					hql = hql + " month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("month", month);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<QQPerMonthStatus> getQQPermonthStatus(String phoneNumber,
			String status, Integer pageSize, Integer start) {
		Session session = getSession();
		Query query = null;
		String hql = "from QQPerMonthStatus";
		if (phoneNumber == null) {
			if ("1".equals(status) || "2".equals(status)) {
				hql = hql + "1 where status = :status";
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				System.out.println(hql);
				query = session.createQuery(hql);
				query.setParameter("status", status);
			} else if ("3".equals(status) || "4".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				if ("3".equals(status)) {
					hql = hql + "1 where month = :month";
				} else if ("4".equals(status)) {
					hql = hql + "1 where month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("month", month);
			} else if ("11".equals(status) || "22".equals(status)) {
				status = status.substring(0, 1);
				hql = hql + "2 where status = :status";
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				query = session.createQuery(hql);
				query.setParameter("status", status);
			} else if ("33".equals(status) || "44".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				status = status.substring(0, 1);
				if ("3".equals(status)) {
					hql = hql + "2 where month = :month";
				} else if ("4".equals(status)) {
					hql = hql + "2 where month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("month", month);
			}
		} else {
			if ("1".equals(status) || "2".equals(status)) {
				hql = hql
						+ "1 where phoneNumber like concat(:phoneNumber,'%') and status = :status";
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("status", status);
			} else if ("3".equals(status) || "4".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				hql = hql
						+ "1 where phoneNumber like concat(:phoneNumber,'%') and";
				if ("3".equals(status)) {
					hql = hql + " month = :month";
				} else if ("4".equals(status)) {
					hql = hql + " month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("month", month);
			} else if ("11".equals(status) || "22".equals(status)) {
				hql = hql
						+ "2 where phoneNumber like concat(:phoneNumber,'%') and status = :status";
				status = status.substring(0, 1);
				if ("1".equals(status)) {
					hql = hql + " order by applyDate desc";
				} else if ("2".equals(status)) {
					hql = hql + " order by cancelDate desc";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("status", status);
			} else if ("33".equals(status) || "44".equals(status)) {
				String month = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				hql = hql
						+ "2 where phoneNumber like concat(:phoneNumber,'%') and";
				status = status.substring(0, 1);
				if ("3".equals(status)) {
					hql = hql + " month = :month";
				} else if ("4".equals(status)) {
					hql = hql + " month != :month or month is null";
				}
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("month", month);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<QQPerMonthStatus> aq = (ArrayList<QQPerMonthStatus>) query
				.list();
		return aq;
	}

}
