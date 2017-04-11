package com.detachment.web.action;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbElectronicPolice;
import com.detachment.web.service.ElePoliceService;

@Controller("elePoliceAction")
public class ElePoliceAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String eleType;
	private String eleAddress;
	ArrayList<TbElectronicPolice> list;
	
	@Autowired
	ElePoliceService elePoliceService;
	
	public String getElePoliceJsp(){
		return "success";
	}
	
	public String getElePolices(){
		list=elePoliceService.getElePolices(eleType, eleAddress, pageSize, pageIndex);
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
	public String getEleType() {
		return eleType;
	}
	public void setEleType(String eleType) {
		this.eleType = eleType;
	}
	public String getEleAddress() {
		return eleAddress;
	}
	public void setEleAddress(String eleAddress) {
		this.eleAddress = eleAddress;
	}

	public ArrayList<TbElectronicPolice> getList() {
		return list;
	}

	public void setList(ArrayList<TbElectronicPolice> list) {
		this.list = list;
	}
	
	
	
	
	
}
