package com.detachment.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.web.service.HistoryTypeService;

@Controller("historyTypeAction")
public class HistoryTypeAction {
	private HashMap<String, Object> result;
	
	
	@Autowired
	HistoryTypeService historyTypeService;
	
	public String getHistoryTypeIdName(){
		result=historyTypeService.getHistoryTypeIdName();
		return "success";
	}

	
	
	
	
	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	
	
	
	
	
	
	
}
