package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Prize;
import com.rising.management.dao.PrizeDao;


@Component("prizeDao")
public class PrizeDaoImpl extends BaseDaoImpl implements PrizeDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Prize> getPrize(Boolean hasLeft, Integer start,
			Integer pageSize) {
		String hql = "from Prize";
		if(hasLeft){
			hql = hql + " where leftAmount > 0 order by addTime desc";
		}else{
			hql = hql + " order by addTime desc";
		}
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Prize> ap = (ArrayList<Prize>) query.list();
		return ap;
	}

	@Override
	public Integer getPrizeListSize(Boolean hasLeft) {
		String hql = "select count(*) from Prize";
		if(hasLeft){
			hql = hql + " where leftAmount > 0 order by addTime desc";
		}else{
			hql = hql + " order by addTime desc";
		}
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Prize> getPrizeInfo() {
		ArrayList<Prize> ap = (ArrayList<Prize>) getSession().createQuery("from Prize where leftAmount > 0").list();
		return ap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Prize getPrizeById(Integer prizeId) {
		String hql = "from Prize where prizeId = :prizeId";
		ArrayList<Prize> ap = (ArrayList<Prize>) getSession().createQuery(hql).setParameter("prizeId", prizeId).list();
		if(ap!=null&&ap.size()>0){
			return ap.get(0);
		}else return null;
	}

	@Override
	public void leftAmountDownOne(Integer prizeId) {
		Prize p = getPrizeById(prizeId);
		if(p.getAmount() == p.getLeftAmount()){
			p.setBegin(new Date());
		}else if(p.getLeftAmount()==1){
			p.setEnd(new Date());
		}
		getSession().update(p);
		getSession().flush();
		String hql = "update Prize set leftAmount = leftAmount -1 where prizeId = :prizeId";
		Query query = getSession().createQuery(hql);
		query.setParameter("prizeId",prizeId);
		query.executeUpdate();
	}
	
	@Override
	public void savePrize(Prize p) {
		getSession().save(p);
		getSession().flush();
	}

	@Override
	public void updatePrize(Prize p) {
		getSession().update(p);
		getSession().flush();
	}

	@Override
	public void deleteProze(ArrayList<Integer> prizeIds) {
		String hql="delete from Prize where prizeId in (:prizeIds)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("prizeIds", prizeIds);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPrizeIdAndName() {
		HashMap<String,Object> map=new HashMap<String,Object>();
		String hql="from Prize";
		ArrayList<Prize> pz=(ArrayList<Prize>) getSession().createQuery(hql).list();
		for(Prize p:pz){
			map.put(p.getPrizeId().toString(), p.getName());
		}
		return map;
	}

}
