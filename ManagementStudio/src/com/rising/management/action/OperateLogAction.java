package com.rising.management.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.SysOperateLogService;

@Controller("operateLogAction")
public class OperateLogAction {

	private String startTime;

	private String endTime;

	private Integer pageSize;

	private Integer pageIndex;

	private HashMap<String, Object> resultMap;
	
	@Autowired SysOperateLogService sysOperateLogService;

	public String doOperateLog() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}

	public String getOperateLog() {
		resultMap = sysOperateLogService.getSysOperateLogByPage(startTime,endTime,pageIndex,pageSize);
		return "success";
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

}
