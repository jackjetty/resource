package com.detachment.dao.impl;
import org.springframework.stereotype.Repository;  

import com.detachment.dao.AppointmentDao; 
import com.detachment.pojo.TbAppointment; 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
@Repository("appointmentDao")
public class AppointmentDaoImpl    extends BaseDaoImpl<TbAppointment> implements  AppointmentDao{
	 
}