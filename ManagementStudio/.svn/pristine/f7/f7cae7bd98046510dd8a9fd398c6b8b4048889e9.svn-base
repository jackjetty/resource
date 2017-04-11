package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.OperateRecord;
import com.rising.management.dao.SysOperateLogDao;

@Component("sysOperateLogDao")
public class SysOperateLogDaoImpl extends BaseDaoImpl implements
		SysOperateLogDao {

	@Override
	public void save(OperateRecord record) {
		getSession().save(record);
		getSession().flush();
		
	}

	@Override
	public Integer getSysOperateLogListSize(Date startTime, Date endTime) {
		String hql = "select count(*) ";
		Query query = null;
		if (startTime != null) {
			hql = hql + "from OperateRecord where operateTime >= :startTime";
			if(endTime!=null){
				hql = hql + " and operateTime <= :endTime order by operateTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql = hql + " order by operateTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
			}
		} else if(endTime!=null){
			hql = hql + "from OperateRecord where operateTime <= :endTime order by operateTime desc";
			query = getSession().createQuery(hql);
			query.setParameter("endTime", startTime);
		}else{
			hql = hql + "from OperateRecord order by operateTime desc";
			query = getSession().createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<OperateRecord> getSysOperateLog(Date startTime,
			Date endTime, Integer start, Integer pageSize) {
		String hql = "";
		Query query = null;
		if (startTime != null) {
			hql = "from OperateRecord where operateTime >= :startTime";
			if(endTime!=null){
				hql = hql + " and operateTime <= :endTime order by operateTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql = hql + " order by operateTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
			}
		} else if(endTime!=null){
			hql = "from OperateRecord where operateTime <= :endTime order by operateTime desc";
			query = getSession().createQuery(hql);
			query.setParameter("endTime", startTime);
		}else{
			hql = "from OperateRecord order by operateTime desc";
			query = getSession().createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<OperateRecord> aor = (ArrayList<OperateRecord>) query.list();
		return aor;
	}
}
