package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;





import com.rising.management.bean.Merchant;
import com.rising.management.dao.MerchantDao;

@Component("merchantDao")
public class MerchantDaoImpl  extends BaseDaoImpl implements MerchantDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Merchant> getMerchant() {
		Query query = getSession().createQuery("from Merchant");
		ArrayList<Merchant> mc = (ArrayList<Merchant>) query.list();
		return mc;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Merchant> getMerchantInfo(Integer start, Integer pageSize) {
		String hql = " from Merchant ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Merchant> m = (ArrayList<Merchant>) query.list();
		return m;
	}
	
	@Override
	public Integer getMerchantInfoListSize() {
		String hql = " select count(*) from Merchant ";
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Merchant> findAll() {
		String hql = " from Merchant  ";
		Query query = getSession().createQuery(hql);
		ArrayList<Merchant> m = (ArrayList<Merchant>) query.list();
		return m;
	}


	@Override
	public void addMerchant(Merchant m) {
		getSession().save(m);
		getSession().flush();
		
	}


	@Override
	public void updateMerchant(Merchant m) {
		getSession().update(m);
		getSession().flush();
		
	}


	@Override
	public void deleteById(String merchantId) {
		String hql = " delete from Merchant where merchantId = :merchantId ";
		Query query = getSession().createQuery(hql);
		query.setParameter("merchantId", merchantId);
		query.executeUpdate();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Merchant> getMerchantType() {
		Query query = getSession().createQuery("from Merchant");
		ArrayList<Merchant> m = (ArrayList<Merchant>) query.list();
		return m;
	}


	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getMerchantInfo() {
		Query query = getSession().createQuery("from Merchant");
		ArrayList<Merchant> m = (ArrayList<Merchant>) query.list();
		HashMap<String,Object> map = new HashMap<String, Object>();
		for (Merchant merchant : m) {
			map.put(merchant.getMerchantId(), merchant.getMerchantName());
		}
		return map;
	}

}
