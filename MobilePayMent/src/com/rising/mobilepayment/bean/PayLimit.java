package com.rising.mobilepayment.bean;

public class PayLimit {
	private Integer limitId;
	private String limitName;
	private String limitType;
	private Integer limit;

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
