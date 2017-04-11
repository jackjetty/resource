package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.OperateRecordService;


@Controller("operateRecordAction")
public class OperateRecordAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private HashMap<String, Object> resultMap;
	@Autowired
	OperateRecordService operateRecordService;

	public String doOperateRecord() {
		return "success";
	}

	public String getOperateRecord() {
		resultMap = operateRecordService.getOperateRecord(startTime, endTime,
				pageIndex, pageSize);
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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
