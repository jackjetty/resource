package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.LimitToBusiness;
import com.rising.management.service.LimitToBusinessService;

@Controller("limitToBusinessAction")
public class LimitToBusinessAction {
	Log log = LogFactory.getLog(LimitToBusinessAction.class);
	
	@Autowired
	LimitToBusinessService limitToBusinessService;
	
	private HashMap<String, Object> resultMap;
	
	private String limitId;
	private String busId;
	private Integer id;
	private String ids;
	private Integer pageSize;
	private Integer pageIndex;
	
	public String doLimitToBusiness(){
		return "success";
	}
	
	public String getLimitToBusiness(){
		resultMap=limitToBusinessService.getLimitBuss(limitId, busId, pageSize, pageIndex);
		return "success";
	}
	
	public String getLimitAndBusInfo(){
		resultMap=limitToBusinessService.getLimitIdNameAndBusIdName();
		return "success";
	}
	
	public String addLimitToBus(){
		LimitToBusiness lb=new LimitToBusiness();
		lb.setBusId(Integer.parseInt(busId));
		lb.setLimitId(Integer.parseInt(limitId));
		resultMap=limitToBusinessService.addLimitToBus(lb);
		return "success";
	}
	
	public String updateLimitToBus(){
		LimitToBusiness lb=new LimitToBusiness();
		lb.setBusId(Integer.parseInt(busId));
		lb.setLimitId(Integer.parseInt(limitId));
		lb.setId(id);
		resultMap=limitToBusinessService.updateLimitToBus(lb);
		return "success";
	}
	
	public String removeLimitToBus(){
		resultMap=limitToBusinessService.removteLimitToBus(ids);
		return "success";
	}
	
	public String getLimitId() {
		return limitId;
	}

	public void setLimitId(String limitId) {
		this.limitId = limitId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
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
	
	
}
