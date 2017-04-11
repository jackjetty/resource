package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.MicroMessageDao;
import com.traffic.pojo.TbMicroMessage;
@Repository("microMessageDao")
public class MicroMessageDaoImpl extends BaseDaoImpl<TbMicroMessage> implements MicroMessageDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMicroMessageListSize(String microId) {
		String hql = "from TbMicroMessage";
		Query  query = null;
		if(microId == null){
			query = getSession().createQuery(hql);
		}else{
			hql += " where microId = :microId"; 
			query = getSession().createQuery(hql);
			query.setParameter("microId", microId);
		}
		ArrayList<TbMicroMessage> m = (ArrayList<TbMicroMessage>) query.list();
		return m.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbMicroMessage> getMicroMessage(Integer start,
			Integer pageSize, String microId) {
		String hql = "from TbMicroMessage";
		Query  query = null;
		if(microId == null){
			query = getSession().createQuery(hql);
		}else{
			hql += " where microId = :microId"; 
			query = getSession().createQuery(hql);
			query.setParameter("microId", microId);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbMicroMessage> m = (ArrayList<TbMicroMessage>) query.list();
		return m;
	}

}
