package com.rising.mobilepayment.bean;
import java.util.Date;
public class QQService{
	private String serviceCode;
	private String serviceName;
	private String iconUrl;
	private String openAid;
	private String busId;
	private Integer orderNum;
	private Integer valid;
	private String payTip;
	private String unitMoney;
	private String unitPrice;
	private String remark;
	
	
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getOpenAid() {
		return openAid;
	}
	public void setOpenAid(String openAid) {
		this.openAid = openAid;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getPayTip() {
		return payTip;
	}
	public void setPayTip(String payTip) {
		this.payTip = payTip;
	}
	public String getUnitMoney() {
		return unitMoney;
	}
	public void setUnitMoney(String unitMoney) {
		this.unitMoney = unitMoney;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}