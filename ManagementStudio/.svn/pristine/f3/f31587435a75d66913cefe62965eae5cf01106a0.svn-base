package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.AppUserInfo;
import com.rising.management.dao.AppUserInfoDao;

@Component("appUserInfoDao")
public class AppUserInfoDaoImpl extends BaseDaoImpl implements AppUserInfoDao{
	
	@Override
	public Integer getAppUserInfoSize(String phoneNumber, String address) {
		String hql = "select count(*) from AppUserInfo";
		Session session = getSession();
		Query query = null;
		if (phoneNumber != null) {
			hql = hql + " where phoneNumber = :phoneNumber";
			if (address != null) {
				hql = hql + " and address = :address";
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("address", address);
			} else {
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
			}
		} else if (address != null) {
			hql = hql + " where address = :address";
			query = session.createQuery(hql);
			query.setParameter("address", address);
		} else {
			query = session.createQuery(hql);
		}

		Number num = (Number) query.list().get(0);
		return num.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppUserInfo> getAppUserInfo(String phoneNumber,
			String address, Integer start, Integer pageSize) {
		String hql = "from AppUserInfo";
		Session session = getSession();
		Query query = null;
		if (phoneNumber != null) {
			hql = hql + " where phoneNumber = :phoneNumber";
			if (address != null) {
				hql = hql + " and address = :address order by registerTime desc";
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("address", address);
			} else {
				hql = hql + " order by registerTime desc";
				query = session.createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
			}
		} else if (address != null) {
			hql = hql + " where address = :address";
			hql = hql + " order by registerTime desc";
			query = session.createQuery(hql);
			query.setParameter("address", address);
		} else {
			hql = hql + " order by registerTime desc";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<AppUserInfo>) query.list();
	}

	@Override
	public void addAppUserInfo(AppUserInfo au) {
		getSession().save(au);
		getSession().flush();
		
	}

	@Override
	public void updateAppUserInfo(AppUserInfo au) {
		getSession().update(au);
		getSession().flush();
		
	}

	@Override
	public void removeAppUserInfo(ArrayList<Integer> aus) {
		String hql = "delete from AppUserInfo where userId in (:aus)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("aus",aus);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getNoOrderAppUser(String phoneNumber,String address,
			Integer start,Integer pageSize) {
		String sql="SELECT PHONENUMBER,REGISTERTIME,ALLSCORE,ADDRESS FROM RS_USERINFO where PHONENUMBER NOT IN " +
				"(SELECT PHONENUMBER FROM RS_ORDERINFO GROUP BY PHONENUMBER)";
		SQLQuery query = null;
		Session session = getSession();
		query = session.createSQLQuery(sql);
		if(phoneNumber!=null){
			sql=sql+" AND PHONENUMBER= :phoneNumber";
			if(address!=null){
				sql=sql+" AND ADDRESS= :address order by REGISTERTIME desc";
				query = session.createSQLQuery(sql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("address", address);
			}else {
				sql=sql+" order by REGISTERTIME desc";
				query = session.createSQLQuery(sql);
				query.setParameter("phoneNumber", phoneNumber);
			}
		}else {
			if(address!=null){
				sql=sql+" AND ADDRESS= :address order by REGISTERTIME desc";
				query = session.createSQLQuery(sql);
				query.setParameter("address", address);
			}else {
				sql=sql+" order by REGISTERTIME desc";
				query = session.createSQLQuery(sql);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Object[]> ap=(ArrayList<Object[]>) query.list();
		return ap;
	}
	
	@Override
	public Integer getNoOrderAppUserSize(String phoneNumber,String address) {
		String sql="SELECT count(*) FROM RS_USERINFO where PHONENUMBER NOT IN " +
				"(SELECT PHONENUMBER FROM RS_ORDERINFO GROUP BY PHONENUMBER)";
		SQLQuery query = null;
		Session session = getSession();
		if(phoneNumber!=null){
			sql=sql+" AND PHONENUMBER = :phoneNumber";
			if(address!=null){
				sql=sql+" AND ADDRESS = :address";
				query = session.createSQLQuery(sql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("address", address);
			}else {
				query = session.createSQLQuery(sql);
				query.setParameter("phoneNumber", phoneNumber);
			}
		}else {
			if(address!=null){
				sql=sql+" AND ADDRESS = :address";
				query = session.createSQLQuery(sql);
				query.setParameter("address", address);
			}else {
				query = session.createSQLQuery(sql);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

}
