package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.LimitToBusiness;

public class LimitToBusinessVo {
	private Integer id;
	private String limitName;
	private String busName;
	
	public LimitToBusinessVo(LimitToBusiness lb,HashMap<String,Object> map,HashMap<String,Object> map1){
		this.id=lb.getId();
		this.limitName=(String) map.get(lb.getLimitId().toString());
		this.busName=(String) map1.get(lb.getBusId().toString());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLimitName() {
		return limitName;
	}

	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}
	
	
	
}
