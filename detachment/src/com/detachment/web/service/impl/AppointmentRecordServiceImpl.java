package com.detachment.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.AppointmentRecordDao;
import com.detachment.pojo.TbAppointmentRecord;
import com.detachment.pojo.vo.TbAppointmentRecordVo;
import com.detachment.web.service.AppointmentRecordService;

@Service
public class AppointmentRecordServiceImpl implements AppointmentRecordService {

	@Autowired
	AppointmentRecordDao appointmentRecordDao;
	
	
	@Override
	public HashMap<String, Object> getAppointmentRecord(String appointmentTime, String appointmentTheme, String appointmentTypeId, int rows, int page) {
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		System.out.println("rows:"+rows);
		System.out.println("page:"+page);
		String hql = "SELECT count(*) "
				+ "FROM TbAppointmentRecord a,TbAppointment b,TbAppointmentType c,TbWeiUser d "
				+ "WHERE a.tbAppointment.appointmentId=b.appointmentId AND b.tbAppointmentType.appointmentTypeId=c.appointmentTypeId AND a.tbWeiUser.openId=d.openId";
		String hql2 = "SELECT a "
				+ "FROM TbAppointmentRecord a,TbAppointment b,TbAppointmentType c,TbWeiUser d "
				+ "WHERE a.tbAppointment.appointmentId=b.appointmentId AND b.tbAppointmentType.appointmentTypeId=c.appointmentTypeId AND a.tbWeiUser.openId=d.openId";
		ArrayList<Object> paramList = new ArrayList<Object>();
		try {
			Timestamp time=null;
			if(appointmentTime != null && !"".equals(appointmentTime)){
				time = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(appointmentTime.toString() + " 00:00:00").getTime());
			}
			if("".equals(appointmentTypeId)){
				appointmentTypeId = null;
			}
			if("".equals(appointmentTheme)){
				appointmentTheme = null;
			}
			
			String condition = "";
			
			if (time != null) {
				condition += " and a.appointmentTime=?";
				paramList.add(time);
			}
			if (appointmentTheme != null) {
				condition += " and b.appointmentTheme=?";
				paramList.add(appointmentTheme);
			}
			if (appointmentTypeId != null) {
				condition += " and c.appointmentTypeId=?";
				paramList.add(appointmentTypeId);
			}
			
			hql += condition;
			hql2 += condition;
			
			int start = (page - 1) * rows;
			int total = appointmentRecordDao.findCount(hql, paramList);
			List<TbAppointmentRecord> appointmentRecordList = appointmentRecordDao.findPage(hql2, start, rows, paramList);
			ArrayList<TbAppointmentRecordVo> appointmentRecordVoList = new ArrayList<TbAppointmentRecordVo>();
			for(TbAppointmentRecord appointmentRecord : appointmentRecordList){
				TbAppointmentRecordVo appointmentRecordVo = new TbAppointmentRecordVo(appointmentRecord);
				appointmentRecordVoList.add(appointmentRecordVo);
			}
			resultMap.put("total", total);
			resultMap.put("rows", appointmentRecordVoList);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
