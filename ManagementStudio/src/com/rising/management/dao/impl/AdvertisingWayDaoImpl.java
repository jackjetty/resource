package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.AdvertisingWay;
import com.rising.management.dao.AdvertisingWayDao;
@Component("advertisingWayDao")
public class AdvertisingWayDaoImpl extends BaseDaoImpl implements AdvertisingWayDao{

	@Override
	public Integer getAdvertisingWayListSize(String path) {
		String hql = "select count(*) from AdvertisingWay";
		Query query = null;
		if(path==null){
				query = getSession().createQuery(hql);
		}else{
				hql = hql + " where path = :path";
				query = getSession().createQuery(hql);
				query.setParameter("path", path);
			}
		Number num = (Number) query.list().get(0);
		return num.intValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AdvertisingWay> getAdvertisingWay(String path,
			Integer start, Integer pageSize) {
		String hql = "from AdvertisingWay";
		Query query = null;
		if(path==null){
				query = getSession().createQuery(hql);
		}else{
				hql = hql + " where path = :path";
				query = getSession().createQuery(hql);
				query.setParameter("path", path);
			}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<AdvertisingWay> rs = (ArrayList<AdvertisingWay>) query.list();
		return rs;
	}

	@Override
	public void addAdvertisingWay(AdvertisingWay aw) {
		getSession().save(aw);
		getSession().flush();
		
	}

	@Override
	public void updateAdvertisingWay(AdvertisingWay aw) {
		getSession().update(aw);
		getSession().flush();
		
	}

	@Override
	public void deleteById(Integer id) {
		String hql = "delete from AdvertisingWay where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AdvertisingWay> getAdvertisingDetail() {
		Query query = getSession().createQuery("from AdvertisingWay");
		ArrayList<AdvertisingWay> ab = (ArrayList<AdvertisingWay>) query.list();
		return ab;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getAdvertisingMap() {
		HashMap<String,Object> map = new HashMap<String, Object>();
		Query query = getSession().createQuery("from AdvertisingWay");
		ArrayList<AdvertisingWay> ab = (ArrayList<AdvertisingWay>) query.list();
		for (AdvertisingWay advertisingWay : ab) {
			map.put(advertisingWay.getPath(), advertisingWay.getDetail());
		}
		return map;
	}

}
