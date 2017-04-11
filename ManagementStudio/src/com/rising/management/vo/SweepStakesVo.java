package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.SweepStakes;

public class SweepStakesVo {
	private Integer id;
	private String startTime;
	private String endTime;
	private String deductScore;
	private String eventName;
	private Integer times;
	private String status;
	
	public SweepStakesVo(SweepStakes sp){
		this.id=sp.getId();
		if (sp.getStartTime() != null) {
			this.startTime = new SimpleDateFormat("yyyy-MM-dd").format(sp
					.getStartTime());
		} else {
			this.startTime = "";
		}
		if (sp.getEndTime() != null) {
			this.endTime = new SimpleDateFormat("yyyy-MM-dd").format(sp
					.getEndTime());
		} else {
			this.endTime = "";
		}
		this.deductScore=sp.getDeductScore();
		this.eventName=sp.getEventName();
		this.times=sp.getTimes();
		this.status=sp.getStatus();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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


	public String getDeductScore() {
		return deductScore;
	}
	public void setDeductScore(String deductScore) {
		this.deductScore = deductScore;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
