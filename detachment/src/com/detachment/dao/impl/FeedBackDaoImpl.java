package com.detachment.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.FeedBackDao;
import com.detachment.pojo.TbFeedBack;
import com.detachment.pojo.vo.TbFeedBackVo;

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
	public List<Object[]> getFeedBack(Date startTime, Date endTime,String recordType, Integer start, Integer pageSize) {
		String sql = "select a.feedBackId,a.feedTime,a.feedOpenId,CONVERT(varchar(8000),a.feedText),a.recordType,a.feedBackState,b.phoneNumber,b.nickname from Tb_FeedBack a left join Tb_WeiUser b on (a.feedOpenId = b.openId) where feedTime between :startTime and :endTime and recordType = :recordType order by feedTime desc";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("recordType", recordType);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<Object[]> objectsList = query.list();
		return objectsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbFeedBackVo getFeedBackById(Integer feedBackId) {
		String sql = "select a.feedBackId,a.feedTime,a.feedOpenId,CONVERT(varchar(8000),a.feedText),a.recordType,a.feedBackState,b.phoneNumber,b.nickname from Tb_FeedBack a left join Tb_WeiUser b on (a.feedOpenId = b.openId) where feedBackId = :feedBackId";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("feedBackId", feedBackId);
		List<Object[]> objList = query.list();
		Object[] objects = objList.get(0);
		TbFeedBackVo feedBackVo = new TbFeedBackVo(objects);
		return feedBackVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getUserFbInfo(Integer feedBackId) {
		String hql = "select feedTime,feedText from TbFeedBack where feedOpenId in (select feedOpenId from TbFeedBack where feedBackId = :feedBackId and recordType='READ') and recordType='READ' order by feedTime asc";
		Query query = getSession().createQuery(hql);
		query.setParameter("feedBackId", feedBackId);
		ArrayList<Object[]> fb = (ArrayList<Object[]>) query.list();
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
