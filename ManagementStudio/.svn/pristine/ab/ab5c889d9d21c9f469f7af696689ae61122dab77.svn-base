package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.PayPrize;

public class PayPrizeVo {

	private Integer id;

	private String productId;

	private String productName;

	private String freeProductId;

	private String freeProductName;

	private Integer oneToMany;

	private String status;
	
	public PayPrizeVo(PayPrize payPrize,HashMap<String,Object> map1,HashMap<String,Object> map2){
		this.freeProductId = payPrize.getFreeProductId();
		this.freeProductName = (String) map1.get(payPrize.getFreeProductId());
		this.id = payPrize.getId();
		this.oneToMany = payPrize.getOneToMany();
		this.productId = payPrize.getProductId();
		this.productName = (String) map2.get(payPrize.getProductId());
		this.status = payPrize.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setPruductName(String productName) {
		this.productName = productName;
	}

	public String getFreeProductId() {
		return freeProductId;
	}

	public void setFreeProductId(String freeProductId) {
		this.freeProductId = freeProductId;
	}

	public String getFreeProductName() {
		return freeProductName;
	}

	public void setFreeProductName(String freeProductName) {
		this.freeProductName = freeProductName;
	}

	public Integer getOneToMany() {
		return oneToMany;
	}

	public void setOneToMany(Integer oneToMany) {
		this.oneToMany = oneToMany;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
