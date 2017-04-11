package com.detachment.dao.impl;
import org.springframework.stereotype.Repository;  

import com.detachment.dao.AppointmentTypeDao; 
import com.detachment.pojo.TbAppointmentType; 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
@Repository("appointmentTypeDao")
public class AppointmentTypeDaoImpl    extends BaseDaoImpl<TbAppointmentType> implements  AppointmentTypeDao{
	 
}