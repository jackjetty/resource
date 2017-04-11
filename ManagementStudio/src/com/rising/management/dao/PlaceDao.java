package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Place;

public interface PlaceDao {

	public ArrayList<Place> getPlaceInfo();

	public HashMap<String, Object> getPlaceName();
	public ArrayList<Place> getPlaceCodeName();
	public ArrayList<String> getPName();

	public String getCodeByName(String placeName);
	
	public HashMap<String, Object> getCodeName(String placeName);
	
	public HashMap<String, Object> getPlaceCodeForName();
	
	public HashMap<String,Object> getPlaceCodeAndName();

	public String getPlaceNameByCode(String address);
}
