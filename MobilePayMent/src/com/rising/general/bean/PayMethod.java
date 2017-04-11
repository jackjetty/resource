package com.rising.general.bean;

public class PayMethod {

	private Integer payMethodId;
	private String payMethodDetail;
	private String remark;
	private String merchantId;
	private String ipBind;
	private String bindIp;
	private String perOrder;

	public Integer getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(Integer payMethodId) {
		this.payMethodId = payMethodId;
	}

	public String getPayMethodDetail() {
		return payMethodDetail;
	}

	public void setPayMethodDetail(String payMethodDetail) {
		this.payMethodDetail = payMethodDetail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getIpBind() {
		return ipBind;
	}

	public void setIpBind(String ipBind) {
		this.ipBind = ipBind;
	}

	public String getBindIp() {
		return bindIp;
	}

	public void setBindIp(String bindIp) {
		this.bindIp = bindIp;
	}

	public String getPerOrder() {
		return perOrder;
	}

	public void setPerOrder(String perOrder) {
		this.perOrder = perOrder;
	}

}
