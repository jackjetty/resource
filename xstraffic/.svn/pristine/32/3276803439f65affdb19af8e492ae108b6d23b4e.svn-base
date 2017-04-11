package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.PromotionService;

@Controller("publicStatisticsAction")
@Scope("prototype")
public class PublicStatisticsAction {
	private Integer pageSize;
	private Integer pageIndex;
	private HashMap<String, Object> result;
	
	@Autowired
	PromotionService promotionService;
	
	public String doPublicStatistics(){
		return "success";
	}
	public String getPublicStatistics(){
		result=promotionService.getPromotion(pageSize, pageIndex);
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

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	
	
	
	
}
