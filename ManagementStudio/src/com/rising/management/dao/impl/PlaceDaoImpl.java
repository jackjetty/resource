package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Place;
import com.rising.management.dao.PlaceDao;

@Component("placeDao")
public class PlaceDaoImpl extends BaseDaoImpl implements PlaceDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPlaceName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from Place";
		Query query = getSession().createQuery(hql);
		ArrayList<Place> ap = (ArrayList<Place>) query.list();
		for (Place p : ap) {
			map.put(p.getCode(), p.getName());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Place> getPlaceCodeName() {
		String hql = "from Place";
		Query query = getSession().createQuery(hql);
		ArrayList<Place> ap = (ArrayList<Place>) query.list();
		return ap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Place> getPlaceInfo() {
		String hql = "from Place";
		Query query = getSession().createQuery(hql);
		ArrayList<Place> ap = (ArrayList<Place>) query.list();
		return ap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getPName() {
		String hql = " from Place " ;
		Query query = getSession().createQuery(hql);
		ArrayList<Place> pc = (ArrayList<Place>) query.list();
		ArrayList<String> p = new ArrayList<String>();
		for (int i = 0; i < pc.size(); i++) {
			p.add(pc.get(i).getName());
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCodeByName(String placeName) {
		String hql = "from Place where name = :name";
		Query query = getSession().createQuery(hql);
		query.setParameter("name", placeName);
		ArrayList<Place> pc = (ArrayList<Place>) query.list();
		String code = pc.get(0).getCode();
		return code;
	}
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPlaceCodeForName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from Place";
		Query query = getSession().createQuery(hql);
		ArrayList<Place> ap = (ArrayList<Place>) query.list();
		for (Place p : ap) {
			map.put(p.getName(),p.getCode());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPlaceCodeAndName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from Place";
		Query query = getSession().createQuery(hql);
		ArrayList<Place> ap = (ArrayList<Place>) query.list();
		for (Place p : ap) {
			map.put(p.getCode(), p.getName());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPlaceNameByCode(String address) {
		String hql = "select name from Place where code = :address";
		Query query = getSession().createQuery(hql);
		query.setParameter("address", address);
		ArrayList<String> p = (ArrayList<String>) query.list();
		String name = p.get(0);
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getCodeName(String placeName) {
		Query query = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(placeName==null||"null".equals(placeName)){
			String hql = "select name,code from Place";
			query = getSession().createQuery(hql);
		}else{
			String hql = "select name,code from Place where name = :palceName";
			query = getSession().createQuery(hql);
			query.setParameter("palceName", placeName);
		}
		ArrayList<Object[]> nv = (ArrayList<Object[]>) query.list();
		for(Object[]obj: nv){
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}

	
}
