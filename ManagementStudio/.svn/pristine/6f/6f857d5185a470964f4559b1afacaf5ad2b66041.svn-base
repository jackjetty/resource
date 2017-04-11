package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_OrderInfo")
public class OrderInfo {
	private String orderId;
	private String phoneNumber;
	private String busId;
	private String productId;
	private String targetNumber;
	private Integer payMethodId;
	private String orderStatus;
	private Date orderTime;
	private String payStatus;
	private Date payTime;
	private String payReturnCode;
	private String SPID;
	private float payMoney;
	private float sendScore;
	private String hcorderId;
	private String os;
	private String client;
	private String version;

	public OrderInfo() {

	}

	public OrderInfo(String phoneNumber, String productId, String targetNumber,
			String payStatus, Date orderTime, String payReturnCode,
			float payMoney, float sendScore,String os,String client,String version) {
		this.phoneNumber = phoneNumber;
		this.productId = productId;
		this.targetNumber = targetNumber;
		this.payStatus = payStatus;
		this.orderTime = orderTime;
		this.payReturnCode = payReturnCode;
		this.payMoney = payMoney;
		this.sendScore = sendScore;
		this.os = os;
		this.client = client;
		this.version = version;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "InforIdNEXT")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}

	public Integer getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(Integer payMethodId) {
		this.payMethodId = payMethodId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayReturnCode() {
		return payReturnCode;
	}

	public void setPayReturnCode(String payReturnCode) {
		this.payReturnCode = payReturnCode;
	}

	public String getSPID() {
		return SPID;
	}

	public void setSPID(String sPID) {
		SPID = sPID;
	}

	public float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}

	public float getSendScore() {
		return sendScore;
	}

	public void setSendScore(float sendScore) {
		this.sendScore = sendScore;
	}

	public String getHcorderId() {
		return hcorderId;
	}

	public void setHcorderId(String hcorderId) {
		this.hcorderId = hcorderId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
