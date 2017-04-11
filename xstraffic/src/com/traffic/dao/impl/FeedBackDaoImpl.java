package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.FeedBackDao;
import com.traffic.pojo.TbFeedBack;

@Repository("feedBackDao")
public class FeedBackDaoImpl extends BaseDaoImpl<TbFeedBack> implements FeedBackDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getFeedBackListSize(Date startTime, Date endTime,String recordType) {
		String hql = "from TbFeedBack where feedTime >= :startTime and feedTime <= :endTime and recordType = :recordType order by feedTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("recordType", recordType);
		ArrayList<TbFeedBack> ta = (ArrayList<TbFeedBack>) query.list();
		return ta.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbFeedBack> getFeedBack(Date startTime, Date endTime,String recordType,
			Integer start, Integer pageSize) {
		String hql = "from TbFeedBack where feedTime >= :startTime and feedTime <= :endTime and recordType = :recordType order by feedTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("recordType", recordType);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbFeedBack> ta = (ArrayList<TbFeedBack>) query.list();
		return ta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbFeedBack getFeedBackById(Integer feedBackId) {
		String hql = "from TbFeedBack where feedBackId = :feedBackId";
		Query query = getSession().createQuery(hql);
		query.setParameter("feedBackId", feedBackId);
		ArrayList<TbFeedBack> ta = (ArrayList<TbFeedBack>) query.list();
		return ta.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbFeedBack> getUserFbInfo(String  feedOpenId) {
		String hql="from TbFeedBack where feedOpenId = :feedOpenId and recordType='READ'"; 
		Query query = getSession().createQuery(hql);
		query.setParameter("feedOpenId", feedOpenId);
		ArrayList<TbFeedBack> fb = (ArrayList<TbFeedBack>) query.list();
		return fb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbFeedBack> getFeedBackByOpenIdType(String feedOpenId) {
		String hql="from TbFeedBack where feedOpenId = :feedOpenId and recordType='REPLY'";
		Query query = getSession().createQuery(hql);
		query.setParameter("feedOpenId", feedOpenId);
		ArrayList<TbFeedBack> tfb=(ArrayList<TbFeedBack>) query.list();
		return tfb;
	}

}
