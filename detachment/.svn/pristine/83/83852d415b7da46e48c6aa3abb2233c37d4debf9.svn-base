package com.detachment.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.web.service.AppointmentRecordService;

@Controller("appointmentRecordAction")
public class AppointmentRecordAction {
 
	private HashMap<String, Object> result;
	private String appointmentTypeId;
	private String appointmentTime;
	private String appointmentTheme;
	
	private int page;
	private int rows;
	
	@Autowired
	AppointmentRecordService appointmentRecordService;
	
	public String doAppointmentRecord(){
		return "success";
	}
	
	public String getAppointmentRecord(){
		System.out.println("rows"+rows);
		System.out.println("page"+page);
		result = appointmentRecordService.getAppointmentRecord(appointmentTime, appointmentTheme, appointmentTypeId, rows, page);
		return "success";
	}
	
	 

	 
	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}


	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}


	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}


	public String getAppointmentTime() {
		return appointmentTime;
	}


	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}


	public String getAppointmentTheme() {
		return appointmentTheme;
	}


	public void setAppointmentTheme(String appointmentTheme) {
		this.appointmentTheme = appointmentTheme;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	 
}
