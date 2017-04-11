package com.rising.management.action;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.PayLimit;
import com.rising.management.service.PayLimitService;



@Controller("payLimitAction")
public class PayLimitAction {
	Log log = LogFactory.getLog(PayLimitAction.class);
	private Integer pageSize;
	private Integer pageIndex;
	private String limitType;
	private String limitName;
	private Integer limit;
	private String limitIds;
	private Integer limitId;
	@Autowired PayLimitService payLimitService;
	private HashMap<String,Object> resultMap;
	public String doPayLimit(){
		return "success";
	}
	public String getPayLimit(){
		if("".equals(limitType)){
			limitType =null;
		}
		resultMap = payLimitService.getPayLimit(limitType,pageSize,pageIndex);
		return "success";
	}
	public String addPayLimit(){
		PayLimit p = new PayLimit();
		p.setLimitName(limitName);
		p.setLimitType(limitType);
		p.setLimit(limit);
		resultMap = payLimitService.addPayLimit(p);
		return "success";
	}
	public String removePayLimit(){
		resultMap = payLimitService.removePayLimit(limitIds);
		return "success";
	}
	public String updatePayLimit(){
		PayLimit p = new PayLimit();
		p.setLimitId(limitId);
		p.setLimitName(limitName);
		p.setLimitType(limitType);
		p.setLimit(limit);
		resultMap = payLimitService.updatePayLimit(p);
		return "success";
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
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getLimitIds() {
		return limitIds;
	}
	public void setLimitIds(String limitIds) {
		this.limitIds = limitIds;
	}
	public Integer getLimitId() {
		return limitId;
	}
	public void setLimitId(Integer limitId) {
		this.limitId = limitId;
	}
	
	

}
