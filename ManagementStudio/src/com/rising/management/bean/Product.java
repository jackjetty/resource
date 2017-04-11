package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RS_Product")
public class Product {

	private String productId;
	private String productName;
	private Integer busId;
	private String merchantId;
	private String productDescribe;
	private Integer cost;
	private String SPID;
	private String status;
	private Integer sendScore;

	@Id
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
	
	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

}
