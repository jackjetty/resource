package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.OrganizationInfoDao;
import com.traffic.pojo.TbOrganizationInfo;

@Repository("organizationInfoDao")
public class OrganizationInfoDaoImpl extends BaseDaoImpl<TbOrganizationInfo> implements OrganizationInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public Integer getOrganizationInfoSize(String organizationName) {
		String hql="from TbOrganizationInfo ";
		Query query=null;
		if(organizationName==null){
			query = getSession().createQuery(hql);
		}else{
			hql +=" where organizationName = :organizationName";
			query = getSession().createQuery(hql);
			query.setParameter("organizationName", organizationName);
		}
		ArrayList<TbOrganizationInfo> ta = (ArrayList<TbOrganizationInfo>) query.list();
		return ta.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbOrganizationInfo> getOrganizationInfo(
			String organizationName, Integer start, Integer pageSize) {
		Query query=null;
		String hql = "from TbOrganizationInfo ";
		if(organizationName==null){
			query = getSession().createQuery(hql);
		}else{
			hql +=" where organizationName = :organizationName";
			query = getSession().createQuery(hql);
			query.setParameter("organizationName", organizationName);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbOrganizationInfo> ta = (ArrayList<TbOrganizationInfo>) query.list();
		return ta;
	}

	@Override
	public void addOrganizationInfo(TbOrganizationInfo oi) {
		getSession().save(oi);
		getSession().flush();
		
	}

	@Override
	public void updateOrganizationInfo(TbOrganizationInfo oi) {
		getSession().update(oi);
		getSession().flush();
		
	}

	@Override
	public void deleteById(int id) {
		String hql = "delete from TbOrganizationInfo where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbOrganizationInfo getOrganizationInfoById(int id) {
		String hql = "from TbOrganizationInfo where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<TbOrganizationInfo> tu = (ArrayList<TbOrganizationInfo>) query.list();
		if (tu != null) {
			return tu.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbOrganizationInfo> getMechanismInfo() {
		String hql = "from TbOrganizationInfo ";
		Query query = getSession().createQuery(hql);
		ArrayList<TbOrganizationInfo> tu = (ArrayList<TbOrganizationInfo>) query.list();
		return tu;
	}

}
