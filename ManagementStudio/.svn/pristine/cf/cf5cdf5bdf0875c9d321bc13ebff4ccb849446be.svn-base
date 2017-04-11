package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.OperateLog;
import com.rising.management.dao.OperateLogDao;

@Component("operateLogDao")
public class OperateLogDaoImpl extends BaseDaoImpl implements OperateLogDao {

	@Override
	public Integer getNewRegisterNumber(Date startTime, Date endTime) {
		String hql = "select count(distinct a.phoneNumber) from OperateLog a,UserInfo b where a.phoneNumber = b.phoneNumber and a.operateTime >= :startTime and a.operateTime <= :endTime and a.operateType = 'UserRegister'";
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}
	
	@Override
	public Integer getNewRegisterNumber(String placeName,Date startTime, Date endTime) {
		String hql = "select count(distinct a.phoneNumber) from OperateLog a,UserInfo b where a.phoneNumber = b.phoneNumber ";
		Query query = null;
		if(placeName==null){
			if(startTime==null &&endTime==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql +" and a.operateTime >= :startTime and a.operateTime <= :endTime and a.operateType = 'UserRegister'";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}
		}else{
			if(startTime==null &&endTime==null){
				hql = hql + " and b.address in (select code from Place where name = :name)";
				query = getSession().createQuery(hql);
				query.setParameter("name", placeName);
			}else{
				hql = hql + " and a.operateTime >= :startTime and a.operateTime <= :endTime and a.operateType = 'UserRegister' and b.address in (select code from Place where name = :name)";
				query = getSession().createQuery(hql);
				query.setParameter("name", placeName);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}
			
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getOs(String operateType,String phoneNumber) {
		String hql = "from OperateLog where operateType=:operateType and phoneNumber =:phoneNumber order by operateTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("operateType", operateType);
		query.setParameter("phoneNumber", phoneNumber);
		ArrayList<OperateLog> ap = (ArrayList<OperateLog>) query.list();
		if (ap != null && ap.size() > 0) {
			return ap.get(0).getOs();
		} else
			return null;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getClient(String operateType,String phoneNumber) {
		String hql = "from OperateLog where operateType=:operateType and phoneNumber =:phoneNumber order by operateTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("operateType", operateType);
		query.setParameter("phoneNumber", phoneNumber);
		ArrayList<OperateLog> ap = (ArrayList<OperateLog>) query.list();
		if (ap != null && ap.size() > 0) {
			return ap.get(0).getClient();
		} else
			return null;
	}
}
