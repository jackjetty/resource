package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Business;
import com.rising.management.dao.BusinessDao;

@Component("businessDao")
public class BusinessDaoImpl extends BaseDaoImpl implements BusinessDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Business> getBusiness() {
		Query query = getSession().createQuery("from Business");
		ArrayList<Business> ab = (ArrayList<Business>) query.list();
		return ab;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Business> getBusinessInfo(Integer busId, Integer start,
			Integer pageSize) {
		String hql = "from Business";
		Query query = null;
		if(busId==null){
			query = getSession().createQuery(hql);
		}else{
			hql = hql+"  where busId = :busId";
			query = getSession().createQuery(hql);
			query.setParameter("busId", busId);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Business> b = (ArrayList<Business>) query.list();
		return b;
	}

	@Override
	public Integer getBusinessInfoListSize(Integer busId) {
		String hql = "select count(*) from Business";
		Query query = null;
		if(busId==null){
			query = getSession().createQuery(hql);
		}else{
			hql = hql+"  where busId = :busId";
			query = getSession().createQuery(hql);
			query.setParameter("busId", busId);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public void deleteById(Integer lastId) {
		String hql = " delete from Business where busId = :busId ";
		Query query = getSession().createQuery(hql);
		query.setParameter("busId", lastId);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Business> findAll() {
		String hql = "from Business";
		Query query = getSession().createQuery(hql);
		ArrayList<Business> b = (ArrayList<Business>) query.list();
		return b;
	}

	@Override
	public void addBusiness(Business b) {
		getSession().save(b);
		getSession().flush();
		
	}

	@Override
	public void updateProduct(Business b) {
		getSession().update(b);
		getSession().flush();
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getBusIdAndBusName() {
		HashMap<String,Object> map=new HashMap<String,Object>();
		String hql="from Business";
		Query query = getSession().createQuery(hql);
		
		ArrayList<Business> bs = (ArrayList<Business>) query.list();
		for(Business b:bs){
			map.put(b.getBusId().toString(), b.getBtype());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getBtype(Integer busId) {
		String hql="select distinct btype from Business ";
		Query query = null;
		if(busId==null){ 
			query = getSession().createQuery(hql);
		}else{
			hql = hql + " where  busId = :busId";
			query = getSession().createQuery(hql);
			query.setInteger("busId", busId);
		}
		ArrayList<String> bt = (ArrayList<String>) query.list();
		return bt;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getBtypeFeemethod(Integer busId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Query query = null;
		if(busId==null){
			String hql="select distinct btype, min(feeMethod) from Business group by btype";
			query = getSession().createQuery(hql);
		}else{
			String hql="select distinct btype, min(feeMethod) from Business where  busId = :busId group by btype";
			query = getSession().createQuery(hql);
			query.setInteger("busId", busId);
		}
		ArrayList<Object[]> bt = (ArrayList<Object[]>) query.list();
		for(Object[] obj:bt){
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getBtypeFeemethodByplace() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql="select distinct btype, min(feeMethod) from Business group by btype";
		Query query = getSession().createQuery(hql);
		ArrayList<Object[]> bt = (ArrayList<Object[]>) query.list();
		for(Object[] obj:bt){
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getBtype() {
		String hql="select distinct btype from Business ";
		Query query = getSession().createQuery(hql);
		ArrayList<String> bt = (ArrayList<String>) query.list();
		return bt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getBtype2(Integer busId) {
		String hql="select distinct btype from Business ";
		Query query = null;
		if(busId==null){
			hql = hql + "where busId != 106"; 
			query = getSession().createQuery(hql);
		}else{
			hql = hql + " where  busId = :busId";
			query = getSession().createQuery(hql);
			query.setInteger("busId", busId);
		}
		ArrayList<String> bt = (ArrayList<String>) query.list();
		return bt;
	}


	



}
