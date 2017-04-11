package com.detachment.web.service;

import java.util.HashMap;


public interface AppointmentRecordService {
	public HashMap<String,Object> getAppointmentRecord(String appointmentTime, String appointmentTheme, String appointmentTypeId,  int rows, int page);
}
