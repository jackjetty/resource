package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_LIMITTOBUSINESS")
public class LimitToBusiness {
	private Integer id;
	private Integer limitId;
	private Integer busId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "LimitToBusinessIdNext")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLimitId() {
		return limitId;
	}
	public void setLimitId(Integer limitId) {
		this.limitId = limitId;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	
	
}
