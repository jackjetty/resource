package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.AdvertisingWay;
import com.rising.management.service.AdvertisingWayService;

@Controller("advertisingWayAction")
public class AdvertisingWayAction {
    Log log = LogFactory.getLog(AdvertisingWayAction.class); 
	
	private Integer pageSize;
	private Integer pageIndex;
	
	private Integer id;
	
	private String ids;
	
	private String path;
	
	private String detail;
	
	private ArrayList<AdvertisingWay> ab;

	private HashMap<String, Object> resultMap;
	
	@Autowired
	AdvertisingWayService advertisingWayService;
	
	public String doAdvertisingWay(){
		return "success";
	}
	
	public String getAdvertisingWay(){
		resultMap=advertisingWayService.getAdvertisingWay(path, pageSize, pageIndex);
		return "success";
	}

	public String addAdvertisingWay(){
		AdvertisingWay aw = new AdvertisingWay();
		aw.setPath(path);
		aw.setDetail(detail);
		resultMap=advertisingWayService.addAdvertisingWay(aw);
		return "success";
	}
	
	public String updateAdvertisingWay(){
		AdvertisingWay aw = new AdvertisingWay();
		if("null".equals(path)){
			path = null;
		}
		aw.setId(id);
		aw.setPath(path);
		aw.setDetail(detail);
		resultMap=advertisingWayService.updateAdvertisingWay(aw);
		return "success";
	}
	
	public String removeAdvertisingWay(){
		resultMap = advertisingWayService.removeAdvertisingWay(ids);
		return "success";
	}
	
	public String getAdvertisingDetail(){
		ab = advertisingWayService.getAdvertisingDetail();
		return "success";
	}

	public ArrayList<AdvertisingWay> getAb() {
		return ab;
	}

	public void setAb(ArrayList<AdvertisingWay> ab) {
		this.ab = ab;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}