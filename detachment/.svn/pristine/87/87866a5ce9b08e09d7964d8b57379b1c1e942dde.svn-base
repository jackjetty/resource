package com.detachment.web.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.detachment.json.AccidentBean;
import com.detachment.json.RespObject;
import com.detachment.web.service.AccidentService;
import com.opensymphony.xwork2.ActionSupport;
@Controller("appAccidentAction")
@Scope("prototype")
public class AppAccidentAction extends ActionSupport {
	
//	private TbFormalAccident tbFormalAccident;
	private String data;
	
	private RespObject resp;
	


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	@Autowired
	private AccidentService accidentService;
	
	
	public String saveTbFormalAccident(){
//		System.out.println(data);
		AccidentBean bean=(AccidentBean) JSON.parseObject(data, AccidentBean.class);
		resp=accidentService.saveTbFormalAccident(bean);   
		return "success"; 
	}
	
	public String appaccidentUpdate(){
		AccidentBean bean=(AccidentBean) JSON.parseObject(data, AccidentBean.class);
		resp=accidentService.updateTbFormalAccident(bean);   
		return "success"; 
	}


	public RespObject getResp() {
		return resp;
	}


	public void setResp(RespObject resp) {
		this.resp = resp;
	}
	
}
