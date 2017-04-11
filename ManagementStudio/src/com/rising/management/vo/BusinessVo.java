package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.Business;

public class BusinessVo {
	private Integer busId;
	private String merchantId;
	private String btype;
	public BusinessVo(Business b,HashMap<String,String> map){
		this.busId=b.getBusId();
		this.merchantId=map.get(b.getMerchantId());
		this.btype=b.getBtype();
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
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	

}
