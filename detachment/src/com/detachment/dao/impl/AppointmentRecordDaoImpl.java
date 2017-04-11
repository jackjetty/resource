package com.detachment.dao.impl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;  

import com.detachment.dao.AppointmentRecordDao; 
import com.detachment.pojo.TbAppointmentRecord; 

import java.sql.Timestamp;
import java.util.ArrayList;
@Repository("appointmentRecordDao")
public class AppointmentRecordDaoImpl    extends BaseDaoImpl<TbAppointmentRecord> implements  AppointmentRecordDao{

	

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getAppointmentRecord(Timestamp appointmentTime,
			String appointmentTypeId,Integer start, Integer pageSize) {
//		String hql="SELECT c.appointmentTypeName,a.identityCard,a.fileNum,a.appointmentTime,d.nickname FROM " +
//				"Tb_AppointmentRecord a,Tb_Appointment b,Tb_AppointmentType c,Tb_WeiUser d WHERE " +
//				"a.tbAppointment.appointmentId=b.tbAppointment.appointmentId AND b.tbAppointmentType.appointmentTypeId=c.appointmentTypeId AND " +
//				"a.tbWeiUser.openId=d.openId";
		
		String hql="SELECT c.appointmentTypeName,a.identityCard,a.fileNum,a.appointmentTime,d.nickname FROM " +
				"Tb_AppointmentRecord a,Tb_Appointment b,Tb_AppointmentType c,Tb_WeiUser d WHERE " +
				"a.appointmentId=b.appointmentId AND b.appointmentTypeId=c.appointmentTypeId AND " +
				"a.openId=d.openId";
		
		Query query = null;
		if(appointmentTime==null){
			if(appointmentTypeId==null){
				hql+=" order by  a.appointmentTime desc";
				query = getSession().createSQLQuery(hql);
			}else{
				hql+=" and c.appointmentTypeId= :appointmentTypeId order by  a.appointmentTime desc";
				query = getSession().createSQLQuery(hql);
				query.setParameter("appointmentTypeId", appointmentTypeId);
			}
		}else{
			hql+=" and a.appointmentTime >= :appointmentTime";
			if(appointmentTypeId==null){
				hql+=" order by  a.appointmentTime desc";
				query = getSession().createSQLQuery(hql);
				query.setParameter("appointmentTime", appointmentTime);
			}else{
				hql+=" and c.appointmentTypeId= :appointmentTypeId order by a.appointmentTime desc";
				query = getSession().createSQLQuery(hql);
				query.setParameter("appointmentTypeId", appointmentTypeId);
				query.setParameter("appointmentTime", appointmentTime);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Object[]> obj=(ArrayList<Object[]>) query.list();
		return obj;
	}

	@Override
	public Integer getAppointmentRecordSize(Timestamp appointmentTime,
			String appointmentTypeId, Integer start, Integer pageSize) {
//		String hql="SELECT count(*) FROM " +
//				"Tb_AppointmentRecord a,Tb_Appointment b,Tb_AppointmentType c,Tb_WeiUser d WHERE " +
//				"a.tbAppointment.appointmentId=b.tbAppointment.appointmentId AND b.tbAppointmentType.appointmentTypeId=c.appointmentTypeId AND " +
//				"a.tbWeiUser.openId=d.openId";
		
		String hql="SELECT count(*) FROM " +
				"Tb_AppointmentRecord a,Tb_Appointment b,Tb_AppointmentType c,Tb_WeiUser d WHERE " +
				"a.appointmentId=b.appointmentId AND b.appointmentTypeId=c.appointmentTypeId AND " +
				"a.openId=d.openId";
		
		Query query = null;
		if(appointmentTime==null){
			if(appointmentTypeId==null){
				query = getSession().createSQLQuery(hql);
			}else{
				hql+=" and c.appointmentTypeId= :appointmentTypeId";
				query = getSession().createSQLQuery(hql);
				query.setParameter("appointmentTypeId", appointmentTypeId);
			}
		}else{
			hql+=" and a.appointmentTime >= :appointmentTime";
			if(appointmentTypeId==null){
				query = getSession().createSQLQuery(hql);
				query.setParameter("appointmentTime", appointmentTime);
			}else{
				hql+=" and c.appointmentTypeId= :appointmentTypeId";
				query = getSession().createSQLQuery(hql);
				query.setParameter("appointmentTypeId", appointmentTypeId);
				query.setParameter("appointmentTime", appointmentTime);
			}
		}
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}
	 
}
 