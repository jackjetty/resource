package com.rising.management.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RS_SALESINFORMATION")
public class SalesInformation {
	private Integer inforId;
	private Integer busId;
	private String productId;
	private String merchantId;
	private String actName;
	private String actTopic;
	private String actContent;
	private String actStartTime;
	private String actEndTime;
	private Float disCount;
	private String sendCost;
	private String open;
	private Integer sortCode;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private String imgName;
	

	public SalesInformation(){}
	
	public Integer getBusId() {
		return busId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "InforIdNext")
	public Integer getInforId() {
		return inforId;
	}
	public void setInforId(Integer inforId) {
		this.inforId = inforId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActTopic() {
		return actTopic;
	}
	public void setActTopic(String actTopic) {
		this.actTopic = actTopic;
	}
	public String getActContent() {
		return actContent;
	}
	public void setActContent(String actContent) {
		this.actContent = actContent;
	}
	public String getActStartTime() {
		return actStartTime;
	}
	public void setActStartTime(String actStartTime) {
		this.actStartTime = actStartTime;
	}
	public String getActEndTime() {
		return actEndTime;
	}
	public void setActEndTime(String actEndTime) {
		this.actEndTime = actEndTime;
	}
	public Float getDisCount() {
		return disCount;
	}
	public void setDisCount(Float disCount) {
		this.disCount = disCount;
	}
	public String getSendCost() {
		return sendCost;
	}
	public void setSendCost(String sendCost) {
		this.sendCost = sendCost;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Integer getSortCode() {
		return sortCode;
	}
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}
	
	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
	
}






























