package com.rising.management.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.PayPrize;
import com.rising.management.service.PayPrizeService;

@Controller("payPrizeAction")
public class PayPrizeAction {

	private Integer id;

	private String ids;

	public String productId;

	public String freeProductId;

	public Integer oneToMany;

	private String status;

	private Integer pageSize;

	private Integer pageIndex;

	private HashMap<String, Object> resultMap;

	@Autowired
	PayPrizeService payPrizeService;

	public String doPayPrize() {
		return "success";
	}

	public String getPayPrize() {
		if("".equals(productId)){
			productId = null;
		}
		resultMap = payPrizeService.getPayPrize(productId,pageSize,pageIndex);
		return "success";
	}

	public String addPayPrize() {
		PayPrize payPrize = new PayPrize();
		payPrize.setFreeProductId(freeProductId);
		payPrize.setProductId(productId);
		payPrize.setOneToMany(oneToMany);
		payPrize.setStatus("结束");
		resultMap = payPrizeService.addPayPrize(payPrize);
		return "success";
	}

	public String updatePayPrize() {
		PayPrize payPrize = new PayPrize();
		payPrize.setId(id);
		payPrize.setFreeProductId(freeProductId);
		payPrize.setProductId(productId);
		payPrize.setOneToMany(oneToMany);
		payPrize.setStatus(status);
		resultMap = payPrizeService.updatePayPrize(payPrize);
		return "success";
	}
	
	public String changePayPrizeStatus(){
		resultMap = payPrizeService.changeStatus(id);
		return "success";
	}

	public String removePayPrize() {
		resultMap = payPrizeService.removePayPrize(ids);
		return "success";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFreeProductId() {
		return freeProductId;
	}

	public void setFreeProductId(String freeProductId) {
		this.freeProductId = freeProductId;
	}

	public Integer getOneToMany() {
		return oneToMany;
	}

	public void setOneToMany(Integer oneToMany) {
		this.oneToMany = oneToMany;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
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

}
