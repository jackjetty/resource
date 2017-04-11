package com.detachment.dao.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.PromotionDao;
import com.detachment.pojo.TbPromotion;
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