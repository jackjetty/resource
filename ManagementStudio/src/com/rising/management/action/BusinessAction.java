package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Business;
import com.rising.management.service.BusinessService;

@Controller("businessAction")
public class BusinessAction {

	private ArrayList<Business> ab;
	private HashMap<String,Object> resultMap;
	private Integer busId;
	private String merchantId;
	private String btype;
	private String busIds;
	private Integer pageSize;
	private Integer pageIndex;
	

	@Autowired BusinessService businessService;

	public String getBusinessType() {
		ab = businessService.getBusiness();
		return "success";
	}
	public String doBusiness(){
		return "success";
	}
	public String getBusinessInfo(){
		if(busId < 0){
			busId = null;
		}
		resultMap = businessService.getBusinessInfo(busId,pageSize,pageIndex);
		return "success";
	}
	public String addBusiness(){
		Business b = new Business();
		b.setBusId(busId);
		b.setMerchantId(merchantId);
		b.setBtype(btype);
		resultMap = businessService.addBusiness(b);
		return "success";
	}
	public String removeBusiness(){
		resultMap = businessService.removeBusiness(busIds);
		return "success";
	}
	public String updateBusiness(){
		Business b = new Business();
		b.setBusId(busId);
		b.setMerchantId(merchantId);
		b.setBtype(btype);
		resultMap = businessService.updateBusiness(b);
		return "success";
	}
	
	
	public String getBusAndBtype(){
		resultMap=businessService.getBusAndBtype();
		return "success";
	}
	

	public ArrayList<Business> getAb() {
		return ab;
	}

	public void setAb(ArrayList<Business> ab) {
		this.ab = ab;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
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
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getBusIds() {
		return busIds;
	}
	public void setBusIds(String busIds) {
		this.busIds = busIds;
	}
	
	
	
}
