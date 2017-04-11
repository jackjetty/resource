package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbPlace;
import com.detachment.pojo.TbPlaceArea;

public interface PlaceService {

	public HashMap<String, Object> getPlace(String placeName, Integer pageIndex,
			Integer pageSize);

	public HashMap<String, Object> addPlace(TbPlace p);

	public HashMap<String, Object> updatePlace(TbPlace p);

	public HashMap<String, Object> removePlace(String placeIds);

	public HashMap<String, Object> addPlaceAreadDetail(ArrayList<TbPlaceArea> atpa);

	public ArrayList<HashMap<String, Object>> getPlaceMap();
	
	public String checkTheAccidentPlace(Double longitude,Double latitude);

	public HashMap<String, Object> getPlaceMapTest();
	
	public void setPlaceMapClick(TbPlaceArea tba);
}
