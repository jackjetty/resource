package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.TbOperateLogDao;
import com.traffic.pojo.TbOperateRecord;

@Repository("tbOperateLogDao")
public class TbOperateLogDaoImpl extends BaseDaoImpl<TbOperateRecord> implements TbOperateLogDao {

	@Override
	public void save(TbOperateRecord record) {
		getSession().save(record);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getOperateRecordSize(Date startDate, Date endDate) {
		String hql = "from TbOperateRecord where operateTime >= :startDate and operateTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<TbOperateRecord> tu = (ArrayList<TbOperateRecord>) query.list();
		return tu.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbOperateRecord> getOperateRecord(Date startDate,
			Date endDate, Integer start, Integer pageSize) {
		String hql = "from TbOperateRecord where operateTime >= :startDate and operateTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbOperateRecord> tu = (ArrayList<TbOperateRecord>) query.list();
		return tu;
	}

}
