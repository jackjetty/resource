package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.MicroMessageService;


@Controller("microMessageAction")
public class MicroMessageAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String microId;
	private HashMap<String, Object> result;
	@Autowired
	MicroMessageService microMessageService; 
	public String doMicroMessage(){
		return "success";
	}
	
	public String getMicroMessage(){
		result = microMessageService.getMicroMessage(pageSize,pageIndex,microId);
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


	public String getMicroId() {
		return microId;
	}

	public void setMicroId(String microId) {
		this.microId = microId;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	
	

}
