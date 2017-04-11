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
package com.rising.general.bean;

public class Business {
	private Integer BusId;
	private String Btype;
	private String MerchantId;
	private String FeeMethod;
	private String TakeOrderURL;
	private String TakeOrderInterfaceName;
	private String PayOrderURL;
	private String PayOrderInterfaceName;

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

	public String getFeeMethod() {
		return FeeMethod;
	}

	public void setFeeMethod(String feeMethod) {
		FeeMethod = feeMethod;
	}

	public String getTakeOrderURL() {
		return TakeOrderURL;
	}

	public void setTakeOrderURL(String takeOrderURL) {
		TakeOrderURL = takeOrderURL;
	}

	public String getTakeOrderInterfaceName() {
		return TakeOrderInterfaceName;
	}

	public void setTakeOrderInterfaceName(String takeOrderInterfaceName) {
		TakeOrderInterfaceName = takeOrderInterfaceName;
	}

	public String getPayOrderURL() {
		return PayOrderURL;
	}

	public void setPayOrderURL(String payOrderURL) {
		PayOrderURL = payOrderURL;
	}

	public String getPayOrderInterfaceName() {
		return PayOrderInterfaceName;
	}

	public void setPayOrderInterfaceName(String payOrderInterfaceName) {
		PayOrderInterfaceName = payOrderInterfaceName;
	}

}
