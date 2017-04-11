package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Merchant;
import com.rising.management.service.MerchantService;

@Controller("merchantAction")
public class MerchantAction {
	Log log = LogFactory.getLog(MerchantAction.class);
	
	@Autowired
	MerchantService merchantService;
	private Integer pageSize;
	private Integer pageIndex;
	private String merchantId;
	private String merchantName;
	private String merchantIds;
	private ArrayList<Merchant> m;
	private HashMap<String, Object> resultMap;

	
	public String doMerchant(){
		return "success";
	}
	
	public String getMerchantType(){
		m=merchantService.getMerchantType();
		return "success";
	}

	public String getMerchant(){
		
		resultMap=merchantService.getMerchant();
		return "success";
	}
	public String getMerchantInfo(){
		resultMap=merchantService.getMerchantInfo(pageSize,pageIndex);
		return "success";
	}
	public String addMerchant(){
		Merchant m = new Merchant();
		m.setMerchantId(merchantId);
		m.setMerchantName(merchantName);
		resultMap = merchantService.addMerchant(m);
		return "success";
	}
	public String updateMerchant(){
		Merchant m = new Merchant();
		m.setMerchantId(merchantId);
		m.setMerchantName(merchantName);
		resultMap = merchantService.updateMerchant(m);
		return "success";
	}
	public String removeMerchant(){
		resultMap = merchantService.deleteByIds(merchantIds);
		return "success";	}
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
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantIds() {
		return merchantIds;
	}
	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
	}
	public ArrayList<Merchant> getM() {
		return m;
	}

	public void setM(ArrayList<Merchant> m) {
		this.m = m;
	}

}











