package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.BackInfo;
import com.rising.management.dao.BackInfoDao;
@Component("backinfoDao")
public class BackInfoDaoImpl extends BaseDaoImpl implements BackInfoDao {
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BackInfo> findByPhoneNumber(String email,String phoneNumber, Integer start,
			Integer pageSize) {
		String hql = "from BackInfo";
		Query query = null;
		if (phoneNumber != null) {
			if(email !=null){
				hql = hql + " where phoneNumber = :phoneNumber and email = :email order by fbtime desc";
				query = getSession().createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("email", email);
			}else{
				hql = hql + " where phoneNumber = :phoneNumber order by fbtime desc";
				query = getSession().createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
			}
			
		} else {
			if(email != null){
				hql = hql + " where  email = :email order by fbtime desc";
				query = getSession().createQuery(hql);
				query.setParameter("email", email);
			}else{
				hql = hql + " order by fbtime desc";
				query = getSession().createQuery(hql);
			}
			
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<BackInfo> backinfo = (ArrayList<BackInfo>) query.list();
		return backinfo;
	}
	
	public Integer getBackInfoListSize(String email,String phoneNumber) {
			String hql = "select count(*) from BackInfo";
			Query query = null;
			if (phoneNumber != null) {
				if(email !=null){
					hql = hql + " where phoneNumber = :phoneNumber and email = :email";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
					query.setParameter("email", email);
				}else{
					hql = hql + " where phoneNumber = :phoneNumber";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
				}
			} else {
				if(email !=null){
					hql = hql + " where  email = :email";
					query = getSession().createQuery(hql);
					query.setParameter("email", email);
				}else{
					query = getSession().createQuery(hql);
				}
			}
			Number num = (Number) query.list().get(0);
			return num.intValue();
		}
	
	@SuppressWarnings("unchecked")
	public void modifyStatus(String status, Integer fbid) {
		String hql="from BackInfo";
		Query query = null;
		if(fbid!=0&&status!=null&&!"".equals(status)){
			hql = hql + " where fbid = :fbid";
			query = getSession().createQuery(hql);
			query.setParameter("fbid",fbid);
			List<BackInfo> list = query.list();
			if(list.size()>0){
				BackInfo backinfo =  list.get(0);
				backinfo.setStatus(status);
				getSession().merge(backinfo);
				getSession().flush();
			}else{
				System.out.println("反馈ID"+fbid+"不存在");
			}

	}
	
	}
	@SuppressWarnings("unchecked")
	@Override
	public void saveprocedureMessage(String procedureMessage, Integer fbid) {
		String hql="from BackInfo where fbid = :fbid";
		Query query = getSession().createQuery(hql);
		query.setParameter("fbid",fbid);
		List<BackInfo> list = query.list();
		if(list.size()>0){
			if(!"".equals(procedureMessage)){
				BackInfo backinfo =  list.get(0);
				backinfo.setProcedureMessage(procedureMessage);
				backinfo.setStatus("已处理");
				getSession().merge(backinfo);
				getSession().flush();
			}else{
				BackInfo backinfo =  list.get(0);
				backinfo.setProcedureMessage(procedureMessage);
				getSession().merge(backinfo);
				getSession().flush();
			}
			
		}else{
			System.out.println("反馈ID"+fbid+"不存在");
		}
		
	}
}
	
