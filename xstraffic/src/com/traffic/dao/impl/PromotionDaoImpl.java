package com.traffic.dao.impl;
import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.PromotionDao;
import com.traffic.pojo.TbPromotion;
@Repository("promotionDao")
public class PromotionDaoImpl    extends BaseDaoImpl<TbPromotion> implements  PromotionDao{


	@Override
	public Integer getPromotionSize() {
		String hql="select count(*) from TbPromotion ";
		Query query = getSession().createQuery(hql);
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbPromotion> getPromotion(Integer start, Integer pageSize) {
		String hql=" from TbPromotion ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbPromotion> tps=(ArrayList<TbPromotion>) query.list();
		return tps;
	}

	
 
	 
}