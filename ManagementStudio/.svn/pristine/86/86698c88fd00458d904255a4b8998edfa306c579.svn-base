package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.FreeProduct;

public class FreeProductVo {
	private String freeProductId;
	private String productName;
	private Integer busId;
	private String busDetail;
	private String merchantId;
	private String merchantDetail;
	private String productDescribe;
	private Integer cost;
	private String SPID;
	private String status;
	private String interfaceAddress;
	private String interfaceParameter;

	public FreeProductVo(FreeProduct freeProduct, HashMap<String, Object> map1,
			HashMap<String, Object> map2) {
		this.freeProductId =  freeProduct.getFreeProductId();
		this.busId = freeProduct.getBusId();
		this.busDetail = (String) map1.get(String.valueOf(freeProduct.getBusId()));
		this.cost = freeProduct.getCost();
		this.interfaceAddress = freeProduct.getInterfaceAddress();
		this.interfaceParameter = freeProduct.getInterfaceParameter();
		this.merchantDetail = (String) map2.get(String.valueOf(freeProduct.getMerchantId()));
		this.merchantId = freeProduct.getMerchantId();
		this.productDescribe = freeProduct.getProductDescribe();
		this.productName = freeProduct.getProductName();
		this.SPID = freeProduct.getSPID();
		this.status = freeProduct.getStatus();
	}

	public String getFreeProductId() {
		return freeProductId;
	}

	public void setFreeProductId(String freeProductId) {
		this.freeProductId = freeProductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getBusDetail() {
		return busDetail;
	}

	public void setBusDetail(String busDetail) {
		this.busDetail = busDetail;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantDetail() {
		return merchantDetail;
	}

	public void setMerchantDetail(String merchantDetail) {
		this.merchantDetail = merchantDetail;
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

	public String getInterfaceAddress() {
		return interfaceAddress;
	}

	public void setInterfaceAddress(String interfaceAddress) {
		this.interfaceAddress = interfaceAddress;
	}

	public String getInterfaceParameter() {
		return interfaceParameter;
	}

	public void setInterfaceParameter(String interfaceParameter) {
		this.interfaceParameter = interfaceParameter;
	}

}
