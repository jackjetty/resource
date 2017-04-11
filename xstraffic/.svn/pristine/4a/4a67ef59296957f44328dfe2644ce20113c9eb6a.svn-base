package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.BakPicDao;
import com.traffic.pojo.TbBakPic;
@Repository("bakPicDao")
public class BakPicDaoImpl extends BaseDaoImpl<TbBakPic> implements BakPicDao{

	@SuppressWarnings("unchecked")
	@Override
	public Integer getBakPicSize(Date startTime, Date endTime) {
		String hql = "from TbBakPic where picTime >= :startTime and picTime <= :endTime order by picTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		ArrayList<TbBakPic> p = (ArrayList<TbBakPic>) query.list();
		return p.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbBakPic> getBakPic(Date startTime, Date endTime,
			Integer start, Integer pageSize) {
		String hql = "from TbBakPic where picTime >= :startTime and picTime <= :endTime order by picTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbBakPic> p = (ArrayList<TbBakPic>) query.list();
		return p;
	}

	@Override
	public void deleteBakPic(ArrayList<Integer> ids) {
		String hql = "delete from TbBakPic where picId in (:ids)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("ids",ids);
		query.executeUpdate();
	}

	
	@Override
	public TbBakPic findByUrl(String picUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
