package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.PayPrize;
import com.rising.management.dao.PayPrizeDao;

@Component("payPrizeDao")
public class PayPrizeDaoImpl extends BaseDaoImpl implements PayPrizeDao {

	@Override
	public void addPayPrize(PayPrize payPrize) {
		getSession().save(payPrize);
		getSession().flush();

	}

	@Override
	public void updatePayPrize(PayPrize payPrize) {
		getSession().update(payPrize);
		getSession().flush();
	}

	@Override
	public void removePayPrize(ArrayList<Integer> ai) {
		String hql = "delete from PayPrize where id in(:ai)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ai", ai);
		query.executeUpdate();
	}

	@Override
	public Integer getPayPrizeListSize(String productId) {
		String hql = "select count(*) from PayPrize";
		Query query = null;
		if(productId == null){
			query = getSession().createQuery(hql);
		}else{
			hql = hql + " where productId = :productId";
			query = getSession().createQuery(hql);
			query.setParameter("productId", productId);
		}		
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PayPrize> getPayPrize(String productId, Integer start,
			Integer pageSize) {
		String hql = "from PayPrize";
		Query query = null;
		if(productId == null){
			query = getSession().createQuery(hql);
		}else{
			hql = hql + " where productId = :productId";
			query = getSession().createQuery(hql);
			query.setParameter("productId", productId);
		}	
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<PayPrize> app = (ArrayList<PayPrize>) query.list();
		return app;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PayPrize findById(Integer id) {
		String hql = "from PayPrize where id = :id";
		ArrayList<PayPrize> app  =  (ArrayList<PayPrize>) getSession().createQuery(hql).setParameter("id", id).list();
		if(app!=null && app.size()>0)
		return app.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PayPrize> findByProductId(String productId) {
		String hql = "from PayPrize where productId = :productId";
		ArrayList<PayPrize> app =  (ArrayList<PayPrize>) getSession().createQuery(hql).setParameter("productId", productId).list();
		return app;
	}

}
