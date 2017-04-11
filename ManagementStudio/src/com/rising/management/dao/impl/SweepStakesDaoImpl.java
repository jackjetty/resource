package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.SweepStakes;
import com.rising.management.dao.SweepStakesDao;

@Component("sweepStakesDao")
public class SweepStakesDaoImpl extends BaseDaoImpl implements SweepStakesDao{
	
	@Override
	public Integer getSweepStakesSize(Date startTime, Date endTime,
			String eventName) {
		String hql = "select count(*) from SweepStakes";
		Session session = getSession();
		Query query = null;
		if (eventName != null) {
			hql = hql + " where eventName = :eventName";
			if (startTime != null && endTime==null) {
				hql = hql + " and startTime >= :startTime";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
				query.setParameter("startTime", startTime);
			} else if(startTime == null && endTime!=null){
				hql = hql + " and endTime >= :endTime";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
				query.setParameter("endTime", endTime);
			} else if(startTime!=null && endTime!=null){
				hql = hql + " and startTime >= :startTime and endTime >= :endTime";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
			}
		} else if (startTime != null) {
			hql = hql + " where startTime >= :startTime";
			if(endTime!=null){
				hql=hql+" and endTime >= :endTime";
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
			}
		} else if(endTime!=null){
			hql = hql + " where endTime >= :endTime";
			query = session.createQuery(hql);
			query.setParameter("endTime", endTime);
		}else{
			query = session.createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SweepStakes> getSweepStakes(Date startTime, Date endTime,
			String eventName, Integer start, Integer pageSize) {
		String hql = "from SweepStakes";
		Session session = getSession();
		Query query = null;
		if (eventName != null) {
			hql = hql + " where eventName = :eventName";
			if (startTime != null && endTime==null) {
				hql = hql + " and startTime >= :startTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
				query.setParameter("startTime", startTime);
			} else if(startTime == null && endTime!=null){
				hql = hql + " and endTime >= :endTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
				query.setParameter("endTime", endTime);
			} else if(startTime!=null && endTime!=null){
				hql = hql + " and startTime >= :startTime and endTime <= :endTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("eventName", eventName);
			}
		} else if (startTime != null) {
			hql = hql + " where startTime >= :startTime";
			if(endTime!=null){
				hql=hql+" and endTime >= :endTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
			}
		} else if(endTime!=null){
			hql = hql + " where endTime >= :endTime";
			hql=hql+" order by id";
			query = session.createQuery(hql);
			query.setParameter("endTime", endTime);
		}else{
			hql=hql+" order by id";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<SweepStakes>) query.list();
	}

	@Override
	public void addSweepStakes(SweepStakes sp) {
		getSession().save(sp);
		getSession().flush();
	}

	@Override
	public void updateSweepStakes(SweepStakes sp) {
		getSession().update(sp);
		getSession().flush();
	}

	@Override
	public void removeSweepStakes(ArrayList<Integer> ids) {
		String hql="delete from SweepStakes where id in (:ids)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getEventNameAndId() {
		HashMap<String,Object> map=new HashMap<String,Object>();
		String hql="from SweepStakes";
		ArrayList<SweepStakes> ss=(ArrayList<SweepStakes>) getSession().createQuery(hql).list();
		for(SweepStakes s:ss){
			map.put(s.getId().toString(), s.getEventName());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SweepStakes> getSweepStakesInfo() {
		String hql="from SweepStakes";
		ArrayList<SweepStakes> ss=(ArrayList<SweepStakes>) getSession().createQuery(hql).list();
		return ss;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SweepStakes getSweepStakesById(Integer id) {
		String hql="from SweepStakes where id = :id";
		Query query  = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<SweepStakes> ap= (ArrayList<SweepStakes>) query.list();
		if(ap.size()>0){
			return ap.get(0);
		}else{
			return null;
		}
	}

}
