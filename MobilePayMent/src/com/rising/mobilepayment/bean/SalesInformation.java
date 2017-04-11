/**
 * SalesInformation.java
 * com.rising.mobilepayment.bean
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-27   上午9:05:07
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.bean;

public class SalesInformation {
	private Integer InforId;
	private Integer BusId;
	private String ProductId;
	private String MerchantId;
	private String ActContent;
	private String ImgName;
	private byte[] Image1;
	private byte[] Image2;
	private byte[] Image3;
	private byte[] Image4;
	private byte[] Image5;
	private byte[] Image6;
	private String ActName;
	private String ActTopic;
	private String ActStartTime;
	private String ActEndTime;
	private Float Discount;
	private String SendCost;
	private Integer SortCode;
	private String Url;
	private String Open;
	private String WholeUrl;
	
	public String getWholeUrl() {
		return WholeUrl;
	}

	public void setWholeUrl(String wholeUrl) {
		WholeUrl = wholeUrl;
	}

	public Integer getInforId() {
		return InforId;
	}

	public void setInforId(Integer inforId) {
		InforId = inforId;
	}

	public Integer getBusId() {
		return BusId;
	}

	public void setBusId(Integer busId) {
		BusId = busId;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getMerchantId() {
		return MerchantId;
	}

	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}

	public String getActContent() {
		return ActContent;
	}

	public void setActContent(String actContent) {
		ActContent = actContent;
	}

	public String getImgName() {
		return ImgName;
	}

	public void setImgName(String imgName) {
		ImgName = imgName;
	}

	public byte[] getImage4() {
		return Image4;
	}

	public void setImage4(byte[] image4) {
		Image4 = image4;
	}

	public byte[] getImage1() {
		return Image1;
	}

	public void setImage1(byte[] image1) {
		Image1 = image1;
	}

	public byte[] getImage2() {
		return Image2;
	}

	public void setImage2(byte[] image2) {
		Image2 = image2;
	}

	public byte[] getImage3() {
		return Image3;
	}

	public void setImage3(byte[] image3) {
		Image3 = image3;
	}

	public String getActName() {
		return ActName;
	}

	public void setActName(String actName) {
		ActName = actName;
	}

	public String getActTopic() {
		return ActTopic;
	}

	public void setActTopic(String actTopic) {
		ActTopic = actTopic;
	}

	public String getActStartTime() {
		return ActStartTime;
	}

	public void setActStartTime(String actStartTime) {
		ActStartTime = actStartTime;
	}

	public String getActEndTime() {
		return ActEndTime;
	}

	public void setActEndTime(String actEndTime) {
		ActEndTime = actEndTime;
	}

	public Float getDiscount() {
		return Discount;
	}

	public void setDiscount(Float discount) {
		Discount = discount;
	}

	public String getSendCost() {
		return SendCost;
	}

	public void setSendCost(String sendCost) {
		SendCost = sendCost;
	}

	public Integer getSortCode() {
		return SortCode;
	}

	public void setSortCode(Integer sortCode) {
		SortCode = sortCode;
	}

	public byte[] getImage5() {
		return Image5;
	}

	public void setImage5(byte[] image5) {
		Image5 = image5;
	}

	public byte[] getImage6() {
		return Image6;
	}

	public void setImage6(byte[] image6) {
		Image6 = image6;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getOpen() {
		return Open;
	}

	public void setOpen(String open) {
		Open = open;
	}

}
