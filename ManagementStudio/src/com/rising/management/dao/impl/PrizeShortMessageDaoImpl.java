package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.PrizeShortMessage;
import com.rising.management.dao.PrizeShortMessageDao;

@Component("prizeShortMessageDao")
public class PrizeShortMessageDaoImpl extends BaseDaoImpl implements PrizeShortMessageDao {

	@Override
	public Integer getPrizeShortMessageListSize(Integer prizeId, String place) {
		String hql = "select count(*) from PrizeShortMessage";
		Session session = getSession();
		Query query = null;
		if(prizeId==null){
			if(place!=null &&!"".equals(place)){
				hql = hql + " where place = :place";
				query = session.createQuery(hql);
				query.setParameter("place", place);
			}else{
				query = session.createQuery(hql);
			}
		}else{
			hql = hql + " where prizeId = :prizeId";
			if(place!=null &&!"".equals(place)){
				hql = hql + " and place = :place";
				query = session.createQuery(hql);
				query.setParameter("prizeId", prizeId);
				query.setParameter("place", place);
			}else{
				query = session.createQuery(hql);
				query.setParameter("prizeId", prizeId);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PrizeShortMessage> getPrizeShortMessage(Integer prizeId,
			String place, Integer start, Integer pageSize) {
		String hql = "from PrizeShortMessage";
		Session session = getSession();
		Query query = null;
		if(prizeId==null){
			if(place!=null &&!"".equals(place)){
				hql = hql + " where place = :place";
				query = session.createQuery(hql);
				query.setParameter("place", place);
			}else{
				query = session.createQuery(hql);
			}
		}else{
			hql = hql + " where prizeId = :prizeId";
			if(place!=null &&!"".equals(place)){
				hql = hql + " and place = :place";
				query = session.createQuery(hql);
				query.setParameter("prizeId", prizeId);
				query.setParameter("place", place);
			}else{
				query = session.createQuery(hql);
				query.setParameter("prizeId", prizeId);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<PrizeShortMessage> apsm = (ArrayList<PrizeShortMessage>) query.list();
		return apsm;
	}

	@Override
	public void add(PrizeShortMessage prizeShortMessage) {
		getSession().save(prizeShortMessage);
		getSession().flush();
		
	}

	@Override
	public void update(PrizeShortMessage prizeShortMessage) {
		getSession().update(prizeShortMessage);
		getSession().flush();
	}

	@Override
	public void removePrizeShortMessage(ArrayList<Integer> ai) {
		String hql = "delete from PrizeShortMessage where id in (:ai)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ai", ai);
		query.executeUpdate();
	}

}
