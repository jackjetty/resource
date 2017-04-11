package com.rising.mobilepayment.bean;

public class PayPrize {

	private Integer id;

	private String productId;

	private String freeProductId;

	private String status;

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

	public String getFreeProductId() {
		return freeProductId;
	}

	public void setFreeProductId(String freeProductId) {
		this.freeProductId = freeProductId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
