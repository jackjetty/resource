package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.DownloadRecordService;

@Controller("downloadRecordAction")
public class DownloadRecordAction {
	Log log = LogFactory.getLog(DownloadRecordAction.class); 
	
	private Integer pageSize;
	private Integer pageIndex;
	
	private String downloadTime;
	private String fromWay;
	
	private HashMap<String, Object> resultMap;
	
	@Autowired
	DownloadRecordService downloadRecordService;
	
	public String doDownloadRecord(){
		return "success";
	}
	
	public String getDownloadRecord(){
		resultMap=downloadRecordService.getDownloadRecord(fromWay, downloadTime, pageSize, pageIndex);
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
	public String getDownloadTime() {
		return downloadTime;
	}
	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}
	public String getFromWay() {
		return fromWay;
	}
	public void setFromWay(String fromWay) {
		this.fromWay = fromWay;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
}