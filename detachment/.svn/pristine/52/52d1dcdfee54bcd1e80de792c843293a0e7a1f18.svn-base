package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.vo.TbAppointmentVo;

public interface AppointmentService {
	public ArrayList<TbAppointmentVo> getAppointmentJsp(Integer pageSize, Integer pageIndex);
	
	public HashMap<String,Object> saveAppointmentRecord(Integer appointmentId,String openId);
	
	public ArrayList<TbAppointmentVo> findAppointmentRecordJsp(String openId);
	
	public HashMap<String,Object> getTbAppointmentTypeIdAndName();
	
	public HashMap<String,Object> getAppointment(Integer pageSize, Integer pageIndex,
			String appointmentTypeId,String appointmentState);
	
	public TbAppointmentVo getTbAppointmentById(Integer appointmentId);
	
	public HashMap<String,Object> addAppointment(String appointmentTypeId,Integer appointmentSum,
			String appointmentStartTime,String appointmentEndTime,String appointmentTheme,
			String appointmentDesc,String appointmentAddress,String appointmentRemark);
	
	public HashMap<String,Object> updateAppointment(Integer appointmentId,String appointmentTypeId,
			Integer appointmentSum,String appointmentStartTime,String appointmentEndTime,
			String appointmentTheme,String appointmentDesc,String appointmentAddress,
			String appointmentRemark);
	
	public HashMap<String,Object> removeAppointment(String appointmentIds);
	public HashMap<String,Object> updateAppointmentState(String appointmentIds);

	public HashMap<String, Object> getTbAppointmentTheme(String appointmentTypeId);
}
