package com.detachment.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.TbHtmlDao;
import com.detachment.pojo.TbHtml;

@Repository("tbHtmlDao")
public class TbHtmlDaoImpl extends BaseDaoImpl<TbHtml> implements TbHtmlDao{

	@Override
	public Integer getTbHtmlSize(Date startTime, Date endTime, String htmlType) {
		String hql="select count(*) from TbHtml where publishTime >= :startTime and publishTime <= :endTime ";
		Query query=null;
		if(htmlType==null){
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
		}else{
			hql +=" and htmlTypeId=:htmlType ";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			query.setParameter("htmlType", htmlType);
		}
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbHtml> getTbHtml(Date startTime, Date endTime,
			String htmlType, Integer start, Integer pageSize) {
		String hql=" from TbHtml where publishTime >= :startTime and publishTime <= :endTime ";
		Query query=null;
		if(htmlType==null){
			hql +=" order by publishTime desc";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
		}else{
			hql +=" and htmlTypeId=:htmlType order by publishTime desc";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			query.setParameter("htmlType", htmlType);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbHtml> th=(ArrayList<TbHtml>) query.list();
		return th;
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbHtml> getTbHtmlJsp(String htmlType, Integer start,
			Integer pageSize) {
		String hql=" from TbHtml where valid=true";
		Query query=null;
		if(htmlType==null){
			hql +=" order by publishTime desc";
			query = getSession().createQuery(hql);
		}else{
			hql +=" and htmlTypeId=:htmlType order by publishTime desc";
			query = getSession().createQuery(hql);
			query.setParameter("htmlType", htmlType);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbHtml> th=(ArrayList<TbHtml>) query.list();
		return th;
	}

}
