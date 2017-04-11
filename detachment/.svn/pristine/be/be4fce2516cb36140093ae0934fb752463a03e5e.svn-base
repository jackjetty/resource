package com.detachment.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.detachment.pojo.TbAppointmentRecord;   
public interface AppointmentRecordDao extends BaseDao<TbAppointmentRecord> {
	
	public Integer getAppointmentRecordSize(Timestamp appointmentTime,String appointmentTypeId,
			Integer start, Integer pageSize);
	
	public ArrayList<Object[]> getAppointmentRecord(Timestamp appointmentTime,String appointmentTypeId,
			Integer start, Integer pageSize);
}