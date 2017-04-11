package com.detachment.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.web.service.MonitorService;

@Controller
public class MonitorAction {
	// input
	private String monitorType;
	private String position;
	private float bigLat;
	private float smallLat;
	private float bigLng;
	private float smallLng;
	
	// output
	private HashMap<String, Object> result;
	
	// service
	@Autowired
	private MonitorService monitorService;
	
	// tinker 2014-10-13
	public String getMonitorList() {
		result = monitorService.getMonitorList(monitorType, position, bigLat, smallLat, bigLng, smallLng);
		return "success";
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public float getBigLat() {
		return bigLat;
	}

	public void setBigLat(float bigLat) {
		this.bigLat = bigLat;
	}

	public float getSmallLat() {
		return smallLat;
	}

	public void setSmallLat(float smallLat) {
		this.smallLat = smallLat;
	}

	public float getBigLng() {
		return bigLng;
	}

	public void setBigLng(float bigLng) {
		this.bigLng = bigLng;
	}

	public float getSmallLng() {
		return smallLng;
	}

	public void setSmallLng(float smallLng) {
		this.smallLng = smallLng;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
}
