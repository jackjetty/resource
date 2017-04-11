package com.rising.management.action;


import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rising.management.service.OrderInfoService;


@Controller("orderInfoAction")
public class OrderInfoAction {

	private Integer pageSize;

	private Integer pageIndex;

	private String phoneNumber;

	private String busId;

	private String productId;

	private Boolean success;

	private Boolean failed;

	private String startTime;

	private String endTime;
	
	private String payReturnCode;
	
	private String targetNumber;
	
	private String os;
	private String client;

	private HashMap<String, Object> resultMap;

	@Autowired
	OrderInfoService orderInfoService;

	public String doOrderInfo() {
		return "success";
	}

	public String getOrderInfo() {
		Integer BusId = null;
		if(!"null".equals(busId)){
			BusId = Integer.parseInt(busId);
		}
		if("null".equals(productId)){
			productId = "";
		}
		resultMap = orderInfoService.getOrderInfo(pageSize, pageIndex,
				phoneNumber, BusId, productId, success, failed, startTime,
				endTime,payReturnCode,targetNumber,os,client);
		return "success";
	}
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
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

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Boolean getFailed() {
		return failed;
	}

	public void setFailed(Boolean failed) {
		this.failed = failed;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getPayReturnCode() {
		return payReturnCode;
	}

	public void setPayReturnCode(String payReturnCode) {
		this.payReturnCode = payReturnCode;
	}

	public String getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
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
	
	
}
