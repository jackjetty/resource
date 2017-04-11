package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_PayPrize")
public class PayPrize {

	private Integer id;

	private String productId;

	private String freeProductId;

	private Integer oneToMany;

	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "PayPrizeIdNext")
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
