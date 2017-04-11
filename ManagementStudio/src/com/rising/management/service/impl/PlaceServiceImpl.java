package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Place;
import com.rising.management.dao.PlaceDao;
import com.rising.management.service.PlaceService;

@Service("placeService")
public class PlaceServiceImpl implements PlaceService{
	Log log = LogFactory.getLog(PlaceServiceImpl.class);
	
	@Autowired
	PlaceDao placeDao;
	
	
	@Override
	public HashMap<String, Object> getPlaceName() {
		HashMap<String, Object> map =placeDao.getPlaceName();
		return map;
	}


	@Override
	public HashMap<String, Object> getPalceCodeName() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Place> ps= placeDao.getPlaceCodeName();
		StringBuilder code=new StringBuilder();
		StringBuilder name=new StringBuilder();
		for(Place p:ps){
			code.append(p.getCode()+",");
			name.append(p.getName()+",");
		}
		result.put("code", code.toString());
		result.put("name", name.toString());
		return result;
	}
	
	@Override
	public ArrayList<Place> getPlaceInfo() {
		ArrayList<Place> ap = placeDao.getPlaceInfo();
		return ap;
	}


	@Override
	public ArrayList<String> getPName() {
		ArrayList<String> ps= placeDao.getPName();
		return ps;
	}


	@Override
	public HashMap<String, Object> getPlaceCodeForName() {
		HashMap<String, Object> map=placeDao.getPlaceCodeForName();
		return map;
	}

}
