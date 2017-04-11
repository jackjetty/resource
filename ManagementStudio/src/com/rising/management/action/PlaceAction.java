package com.rising.management.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Place;
import com.rising.management.service.PlaceService;

@Controller("placeAction")
public class PlaceAction {

	@Autowired
	PlaceService placeService;

	private ArrayList<Place> ap;
	
	private HashMap<String, Object> resultMap;
	
	private String startTime;
	private String endTime;
	private String action;
	
	public String getPlaceInfo() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		action="getImageToJsp.action";
		ap = placeService.getPlaceInfo();
		return "success";
	}
	public String getPlaceCodeName(){
		resultMap=placeService.getPalceCodeName();
		return "success";
	}
	
	
	public ArrayList<Place> getAp() {
		return ap;
	}

	public void setAp(ArrayList<Place> ap) {
		this.ap = ap;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
