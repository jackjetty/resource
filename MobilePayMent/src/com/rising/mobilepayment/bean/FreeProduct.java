package com.rising.mobilepayment.bean;

public class FreeProduct {
	private String freeProductId;
	private String productName;
	private Integer busId;
	private String merchantId;
	private String productDescribe;
	private Integer cost;
	private String SPID;
	private String status;
	private String interfaceAddress;
	private String interfaceParameter;

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
