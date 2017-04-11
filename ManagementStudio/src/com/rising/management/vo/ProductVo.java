package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.Product;

public class ProductVo {
	private String productId;
	private String productName;
	private String busId;
	private String merchantId;
	private String productDescribe;
	private Integer cost;
	private String SPID;
	private String status;
	private Integer sendScore;
	public ProductVo(Product p , HashMap<Integer,String> hashMap,HashMap<String,String> map){
		this.productId = p.getProductId();
		this.productName = p.getProductName();
		this.busId = hashMap.get(p.getBusId());
		this.merchantId = map.get(p.getMerchantId());
		this.productDescribe = p.getProductDescribe();
		this.cost = p.getCost();
		this.SPID = p.getSPID();
		this.status = p.getStatus();
		this.sendScore = p.getSendScore();
		
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getSPID() {
		return SPID;
	}
	public void setSPID(String sPID) {
		SPID = sPID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public Integer getSendScore() {
		return sendScore;
	}
	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}
	

	
	

}
