package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.QQPerMonthStatistics;
import com.rising.management.dao.QQPerMonthStatisticsDao;

@Component("qQPerMonthStatistic")
public class QQPerMonthStatisticsDaoImpl extends BaseDaoImpl implements QQPerMonthStatisticsDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<QQPerMonthStatistics> getRecord(Date startDate,
			Date endDate, String type) {
		String hql = "from QQPerMonthStatistics where statisticsType = :type and statisticsTime >= :startDate and statisticsTime <= :endDate order by statisticsTime asc";
		Query query = getSession().createQuery(hql);
		query.setParameter("type", type);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<QQPerMonthStatistics> AQP = (ArrayList<QQPerMonthStatistics>) query.list();
		return AQP;
	}

}
