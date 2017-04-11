package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.PhoneMessage;
import com.rising.management.service.PhoneMessageService;

@Controller("phoneMessageAction")
public class PhoneMessageAction {
	Log log = LogFactory.getLog(PhoneMessageAction.class);
	
	private Integer id;
	private String use;
	private String content;
	private String describe;
	private Integer pageSize;
	private Integer pageIndex;
	private String ids;
	
	
	@Autowired
	PhoneMessageService phoneMessageService;
	
	private HashMap<String, Object> resultMap;
	
	
	public String doPhoneMessage(){
		return "success";
	}
	
	public String getPhoneMessage(){
		resultMap=phoneMessageService.getPhoneMessage(use, describe, pageSize, pageIndex);
		
		return "success";
	}
	
	public String addPhoneMessage(){
		PhoneMessage pm=new PhoneMessage();
		pm.setUse(use);
		pm.setContent(content);
		pm.setDescribe(describe);
		resultMap=phoneMessageService.addPhoneMessage(pm);
		return "success";
	}
	
	public String updatePhoneMessage(){
		PhoneMessage pm=new PhoneMessage();
		pm.setId(id);
		pm.setUse(use);
		pm.setContent(content);
		pm.setDescribe(describe);
		resultMap=phoneMessageService.updatePhoneMessage(pm);
		return "success";
	}
	
	public String removePhoneMessage(){
		resultMap=phoneMessageService.removePhoneMessage(ids);
		return "success";
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
}
