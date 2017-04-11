package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.AppVersion;
import com.rising.management.dao.AppVersionDao;

@Component("appVersionDao")
public class AppVersionDaoImpl extends BaseDaoImpl implements AppVersionDao {

	@Override
	public Integer getAppVersionListSize() {
		String hql = "select count(*) from AppVersion";
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppVersion> getAppVersion(Integer start, Integer pageSize) {
		String hql = "from AppVersion order by versionCode desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<AppVersion> aa = (ArrayList<AppVersion>) query.list();
		return aa;
	}

	@Override
	public void addAppVersion(AppVersion a) {
		getSession().save(a);
		getSession().flush();
	}

	@Override
	public void deleteById(int id) {
		String hql = "delete from AppVersion where AppVId = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void updateAppVersion(AppVersion a) {
		getSession().update(a);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AppVersion getAppVersionById(Integer appVId) {
		String hql = "from AppVersion where appVId = :appVId";
		Query query = getSession().createQuery(hql);
		query.setParameter("appVId", appVId);
		ArrayList<AppVersion> aa = (ArrayList<AppVersion>) query.list();
		if (aa.size() > 0) {
			return aa.get(0);
		} else
			return null;
	}

}
