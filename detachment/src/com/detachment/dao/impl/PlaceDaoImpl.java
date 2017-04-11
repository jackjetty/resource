package com.detachment.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.PlaceDao;
import com.detachment.pojo.TbPlace;

@Repository
public class PlaceDaoImpl extends BaseDaoImpl<TbPlace> implements PlaceDao {

	@Override
	public void deleteByIds(String[] args) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (String placeId : args) {
			if (!"".equals(placeId)) {
				ai.add(Integer.parseInt(placeId));
			}
		}
		String hql = "delete from TbPlace where placeId in :placeIds";
		getSession().createQuery(hql).setParameterList("placeIds", ai)
				.executeUpdate();
		getSession().flush();

	}

	@Override
	public Integer getPlaceListSize(String placeName) {
		String hql = "select count(*) from TbPlace";
		Query query = null;
		if (placeName == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " where placeName = :placeName";
			query = getSession().createQuery(hql);
			query.setParameter("placeName", placeName);
		}
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbPlace> getPlace(String placeName, Integer start,
			Integer pageSize) {
		String hql = "from TbPlace";
		Query query = null;
		if (placeName == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " where placeName = :placeName";
			query = getSession().createQuery(hql);
			query.setParameter("placeName", placeName);
		}
		ArrayList<TbPlace> atp = (ArrayList<TbPlace>) query.list();
		return atp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbPlace> getPlace() {
		String hql = "from TbPlace";
		ArrayList<TbPlace>atp = (ArrayList<TbPlace>) getSession().createQuery(hql).list();
		return atp;
	}

}
