/**
 * Business.java
 * com.rising.mobilepayment.bean
 * 工程：MobilePayMent
 * 功能： 用于管理业务种类信息
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-27   上午9:02:11
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.bean;

public class Business {
	private Integer BusId;
	private String Btype;
	private String MerchantId;

	public Integer getBusId() {
		return BusId;
	}

	public void setBusId(Integer busId) {
		BusId = busId;
	}

	public String getBtype() {
		return Btype;
	}

	public void setBtype(String btype) {
		Btype = btype;
	}

	public String getMerchantId() {
		return MerchantId;
	}

	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}

}
