package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Place;

public interface PlaceService {
	public HashMap<String, Object> getPlaceName();
	public HashMap<String, Object> getPalceCodeName();
	public ArrayList<Place> getPlaceInfo();
	public ArrayList<String> getPName();
	public HashMap<String, Object> getPlaceCodeForName();
}
