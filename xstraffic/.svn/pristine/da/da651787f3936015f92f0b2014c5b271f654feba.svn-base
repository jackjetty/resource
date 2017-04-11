package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.CongestService;


@Controller("congestAction")
public class CongestAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String reportPhoneNumber;
	private String congestState;
	private String congestId;
	private String managerName;
	private HashMap<String,Object> result;
	@Autowired
	CongestService congestService;
	public String  doCongest(){
		return "success";
	}
	public String  getCongest(){
		result = congestService.getCongest(startTime,endTime,reportPhoneNumber,congestState,pageSize,pageIndex);
		return "success";
	}
	public String changeCgState(){
		result = congestService.changeCgState(congestId,managerName);
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
	public String getReportPhoneNumber() {
		return reportPhoneNumber;
	}
	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}
	public String getCongestState() {
		return congestState;
	}
	public void setCongestState(String congestState) {
		this.congestState = congestState;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	public String getCongestId() {
		return congestId;
	}
	public void setCongestId(String congestId) {
		this.congestId = congestId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	
}
