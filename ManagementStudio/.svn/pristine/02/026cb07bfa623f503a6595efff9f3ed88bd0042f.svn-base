package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.FreeProduct;
import com.rising.management.service.FreeProductService;

@Controller("freeProductAction")
public class FreeProductAction {

	private String productIds;

	private String productId;

	private String productName;

	private Integer busId;

	private String merchantId;

	private String productDescribe;

	private Integer cost;

	private String SPID;

	private String status;

	private String interfaceParameter;

	private String interfaceAddress;

	private Integer pageSize;

	private Integer pageIndex;

	@Autowired
	FreeProductService freeProductService;

	private HashMap<String, Object> resultMap;

	private ArrayList<HashMap<String, Object>> AHSO;

	public String doFreeProduct() {
		return "success";
	}

	public String getFreeProduct() {
		resultMap = freeProductService.getFreeProduct(busId, cost, pageSize,
				pageIndex);
		return "success";
	}

	public String getFreeProductMap() {
		AHSO = freeProductService.getFreeProductMap();
		return "success";
	}

	public String addFreeProduct() {
		FreeProduct fp = new FreeProduct();
		fp.setFreeProductId(productId);
		fp.setProductName(productName);
		fp.setBusId(busId);
		fp.setMerchantId(merchantId);
		fp.setProductDescribe(productDescribe);
		fp.setCost(cost);
		fp.setSPID(SPID);
		fp.setStatus(status);
		fp.setInterfaceAddress(interfaceAddress);
		fp.setInterfaceParameter(interfaceParameter);
		resultMap = freeProductService.addFreeProduct(fp);
		return "success";
	}

	public String updateFreeProduct() {
		FreeProduct fp = new FreeProduct();
		fp.setFreeProductId(productId);
		fp.setProductName(productName);
		fp.setBusId(busId);
		fp.setMerchantId(merchantId);
		fp.setProductDescribe(productDescribe);
		fp.setCost(cost);
		fp.setSPID(SPID);
		fp.setStatus(status);
		fp.setInterfaceAddress(interfaceAddress);
		fp.setInterfaceParameter(interfaceParameter);
		resultMap = freeProductService.updateFreeProduct(fp);
		return "success";
	}

	public String removeFreeProduct() {
		resultMap = freeProductService.removeFreeProduct(productIds);
		return "success";
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getSPID() {
		return SPID;
	}

	public void setSPID(String sPID) {
		SPID = sPID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInterfaceParameter() {
		return interfaceParameter;
	}

	public void setInterfaceParameter(String interfaceParameter) {
		this.interfaceParameter = interfaceParameter;
	}

	public String getInterfaceAddress() {
		return interfaceAddress;
	}

	public void setInterfaceAddress(String interfaceAddress) {
		this.interfaceAddress = interfaceAddress;
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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ArrayList<HashMap<String, Object>> getAHSO() {
		return AHSO;
	}

	public void setAHSO(ArrayList<HashMap<String, Object>> aHSO) {
		AHSO = aHSO;
	}

}
