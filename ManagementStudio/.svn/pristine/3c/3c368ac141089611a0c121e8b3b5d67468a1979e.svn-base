package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_SYS_PAYLIMIT")
public class PayLimit {
	private Integer limitId;
	
	private String limitName;
	
	private String limitType;
	
	private Integer limit;
	
	public PayLimit(){}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "SysLimitIdNext")
	public Integer getLimitId() {
		return limitId;
	}
	public void setLimitId(Integer limitId) {
		this.limitId = limitId;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
