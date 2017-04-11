package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.PublicInfo;
import com.rising.management.dao.PublicInfoDao;

@Component("publicInfoDao")
public class PublicInfoDaoImpl extends BaseDaoImpl implements PublicInfoDao {

	@Override
	public Integer getPublicInfoListSize() {
		String hql = "select count(*) from PublicInfo";
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PublicInfo> getPublicInfo(Integer start, Integer pageSize) {
		String hql = "from PublicInfo order by time desc";
		Query query = getSession().createQuery(hql);
		ArrayList<PublicInfo> rs = (ArrayList<PublicInfo>) query.list();
		return rs;
	}

	@Override
	public void addPublicInfo(PublicInfo p) {
		getSession().save(p);
		getSession().flush();
		
	}

	@Override
	public void updatePublicInfo(PublicInfo p) {
		getSession().update(p);
		getSession().flush();
		
	}

	@Override
	public void removePublicInfo(Integer id) {
		String hql = "delete from PublicInfo where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

}
