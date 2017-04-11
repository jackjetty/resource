package com.rising.mobilepayment.bean;

public class PhoneNumber {

	private String phoneNumber;

	private String cityCode;

	private String carriers;
	
	private String fromSource;
	
	private String paymethodId;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCarriers() {
		return carriers;
	}

	public void setCarriers(String carriers) {
		this.carriers = carriers;
	}

	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}

	public String getPaymethodId() {
		return paymethodId;
	}

	public void setPaymethodId(String paymethodId) {
		this.paymethodId = paymethodId;
	}
	

	 

}
