package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.CongestDao;
import com.traffic.pojo.TbCongest;
@Repository("congestDao")
public class CongestDaoImpl extends BaseDaoImpl<TbCongest> implements CongestDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCongestList(Date startTime, Date endTime,
			String reportPhoneNumber, String congestState) {
		String hql = "from TbCongest";
		Query query = null;
		if(reportPhoneNumber==null){
			if(congestState==null){
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and congestState = :congestState order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
				query.setParameter("congestState", congestState);
			}
		}else{
			if(congestState==null){
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
				query.setParameter("reportPhoneNumber", reportPhoneNumber);
			}else{
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime  and reportPhoneNumber = :reportPhoneNumber and congestState = :congestState order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
				query.setParameter("reportPhoneNumber", reportPhoneNumber);
				query.setParameter("congestState", congestState);
			}
			
		}
		ArrayList<TbCongest> ta = (ArrayList<TbCongest>) query.list();
		return ta.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbCongest> getCongest(Date startTime, Date endTime,
			String reportPhoneNumber, Integer start, Integer pageSize,
			String congestState) {
		String hql = "from TbCongest";
		Query query = null;
		if(reportPhoneNumber==null){
			if(congestState==null){
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and congestState = :congestState order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
				query.setParameter("congestState", congestState);
			}
		}else{
			if(congestState==null){
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
				query.setParameter("reportPhoneNumber", reportPhoneNumber);
			}else{
				hql = hql +" where reportTime >=:startTime and reportTime <=:endTime  and reportPhoneNumber = :reportPhoneNumber and congestState = :congestState order by reportTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
				query.setParameter("reportPhoneNumber", reportPhoneNumber);
				query.setParameter("congestState", congestState);
			}
			
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbCongest> ta = (ArrayList<TbCongest>) query.list();
		return ta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbCongest getCongestById(String congestId) {
		String hql = "from TbCongest where congestId = :congestId";
		Query query = getSession().createQuery(hql);
		query.setParameter("congestId", congestId);
		ArrayList<TbCongest> tb = (ArrayList<TbCongest>) query.list();
		return tb.get(0);
	}

	@Override
	public void changeCgState(TbCongest tc) {
		getSession().update(tc);
		getSession().flush();
		
	}

	

	@Override
	public String getNextCongestId(String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
