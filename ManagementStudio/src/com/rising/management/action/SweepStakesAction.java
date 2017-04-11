package com.rising.management.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.SweepStakes;
import com.rising.management.service.SweepStakesService;

@Controller("sweepStakesAction")
public class SweepStakesAction {
	Log log = LogFactory.getLog(SweepStakesAction.class);
	
	private Integer pageSize;
	private Integer pageIndex;
	
	private String startTime;
	private String endTime;
	private String eventName;
	private String deductScore;
	private Integer id;
	private String ids;
	private Integer times;
	private String status;
	
	private HashMap<String, Object> resultMap;
	
	@Autowired
	SweepStakesService sweepStakesService;
	
	public String doSweepStakes(){
		return "success";
	}
	
	public String getSweepStakes(){
		resultMap=sweepStakesService.getSweepStakes(startTime, endTime, eventName, pageSize, pageIndex);
		return "success";
	}

	public String addSweepStakes(){
		SweepStakes sp=new SweepStakes();
		sp.setEventName(eventName);
		sp.setDeductScore(deductScore);
		sp.setStatus("结束");
		sp.setTimes(times);
		resultMap=sweepStakesService.addSweepStakes(sp);
		return "success";
	}
	
	public String updateSweepStakes(){
		SweepStakes sp=new SweepStakes();
		sp.setEventName(eventName);
		if(!"".equals(startTime)){
			try {
				sp.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else{
			sp.setStartTime(null);
		}
		if(!"".equals(endTime)){
			try {
				sp.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else{
			sp.setEndTime(null);
		}
		
		
		sp.setDeductScore(deductScore);
		sp.setId(id);
		sp.setTimes(times);
		sp.setStatus(status);
		resultMap=sweepStakesService.updateSweepStakes(sp);
		return "success";
	}
	
	public String removeSweepStakes(){
		resultMap=sweepStakesService.removeSweepStakes(ids);
		return "success";
	}
	
	public String changeStatus(){
		resultMap=sweepStakesService.changeStatus(id);
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(String deductScore) {
		this.deductScore = deductScore;
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

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
