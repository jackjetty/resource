package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Product;
import com.rising.management.service.ProductService;

@Controller("productAction")
public class ProductAction {
	private String productIds;
	private String productId;
	private String productName;
	private Integer busId;
	private String merchantId;
	private String productDescribe;
	private Integer cost;
	private String SPID;
	private Integer sendScore;
	private String status;
	private Integer pageSize;
	private Integer pageIndex;

	private ArrayList<Product> ap;

	@Autowired
	ProductService productService;

	private HashMap<String, Object> resultMap;

	private ArrayList<HashMap<String, Object>> AHSO;

	public String getProduct() {
		ap = productService.getProductByBusId(busId);
		return "success";
	}

	public String getProductByStatus() {
		resultMap = productService.getProductByStatus();
		return "success";
	}

	public String getProductSimpleInfo() {
		AHSO = productService.getProductSimpleInfo();
		return "success";
	}

	public String doProduct() {
		return "success";
	}

	public String getProductInfo() {
		if (busId < 0) {
			busId = null;
		}
		resultMap = productService.getProductInfo(busId, cost, pageSize,
				pageIndex);
		return "success";
	}

	public String addProduct() {
		Product p = new Product();
		p.setProductId(productId);
		p.setProductName(productName);
		p.setBusId(busId);
		p.setMerchantId(merchantId);
		p.setProductDescribe(productDescribe);
		p.setCost(cost);
		p.setSPID(SPID);
		p.setStatus(status);
		p.setSendScore(sendScore);
		resultMap = productService.addProduct(p);
		return "success";
	}

	public String removeProduct() {
		resultMap = productService.deleteByIds(productIds);
		return "success";
	}

	public String updateProduct() {
		Product p = new Product();
		p.setProductId(productId);
		p.setProductName(productName);
		p.setBusId(busId);
		p.setMerchantId(merchantId);
		p.setProductDescribe(productDescribe);
		p.setCost(cost);
		p.setSPID(SPID);
		p.setStatus(status);
		p.setSendScore(sendScore);
		resultMap = productService.updateProduct(p);
		return "success";
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public ArrayList<Product> getAp() {
		return ap;
	}

	public void setAp(ArrayList<Product> ap) {
		this.ap = ap;
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

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

	public ArrayList<HashMap<String, Object>> getAHSO() {
		return AHSO;
	}

	public void setAHSO(ArrayList<HashMap<String, Object>> aHSO) {
		AHSO = aHSO;
	}

}
