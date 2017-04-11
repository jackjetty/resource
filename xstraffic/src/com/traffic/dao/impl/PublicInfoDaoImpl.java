package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.PublicInfoDao;
import com.traffic.pojo.TbPublicInfo;
@Repository("publicInfoDao")
public class PublicInfoDaoImpl extends BaseDaoImpl<TbPublicInfo> implements PublicInfoDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getPublicInfoSize(Date startTime, Date endTime,String publicType) {
		String hql="from TbPublicInfo where publicTime >= :startTime and publicTime <= :endTime and publicType <> 'WB'";
		Query query=null;
		if(publicType==null){
			hql +=" order by publicIndex desc";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
		}else{
			hql +=" and publicType=:publicType order by publicIndex desc";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			query.setParameter("publicType", publicType);
		}
		ArrayList<TbPublicInfo> ta = (ArrayList<TbPublicInfo>) query.list();
		return ta.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbPublicInfo> getPublicInfo(Date startTime, Date endTime,String publicType,
			Integer start, Integer pageSize) {
		Query query=null;
		String hql = "from TbPublicInfo where publicTime >= :startTime and publicTime <= :endTime and publicType <> 'WB'";
		if(publicType==null){
			hql+=" order by publicIndex desc";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
		}else{
			hql+=" and publicType=:publicType order by publicIndex desc";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			query.setParameter("publicType", publicType);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbPublicInfo> ta = (ArrayList<TbPublicInfo>) query.list();
		return ta;
	}

	@Override
	public void addPublicInfo(TbPublicInfo pb) {
		getSession().save(pb);
		getSession().flush();
		
	}

	@Override
	public void updatePublicInfo(TbPublicInfo pb) {
		getSession().update(pb);
		getSession().flush();
		
	}

	@Override
	public void deleteById(int id) {
		String hql = "delete from TbPublicInfo where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbPublicInfo getPublicInfoById(int id) {
		String hql = "from TbPublicInfo where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<TbPublicInfo> tu = (ArrayList<TbPublicInfo>) query.list();
		if (tu != null) {
			return tu.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbPublicInfo> getAllCodeJsp(String publicType,Integer start, Integer pageSize) {
		String hql="from TbPublicInfo where publicType=:publicType  and status='启用'  order by publicIndex desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("publicType", publicType);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbPublicInfo> ta = (ArrayList<TbPublicInfo>) query.list();
		return ta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAllCodeJspSize(String publicType) {
		String hql="from TbPublicInfo where publicType=:publicType  and status='启用'  order by publicIndex desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("publicType", publicType);
		ArrayList<TbPublicInfo> ta = (ArrayList<TbPublicInfo>) query.list();
		return ta.size();
	}

	
}
