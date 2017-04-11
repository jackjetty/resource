package com.traffic.dao.impl;
import org.rising.wei.bean.news.MsgItemNewsBean;
import org.rising.wei.bean.news.MultiItemNewsBean;
import org.springframework.stereotype.Repository;   

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 

 
import com.traffic.dao.OrationRecordDao;
import com.traffic.pojo.TbOrationRecord;   
import com.traffic.util.CommonUtil; 
@Repository("orationRecordDao")
public class OrationRecordDaoImpl    extends BaseDaoImpl<TbOrationRecord> implements  OrationRecordDao{ 
	public List<TbOrationRecord> getValidOrationRecord(String itemId){ 
		//itemId,title,description,picUrl,url,valid,beginDate,endDate,publisher,publishTime,showOrder,remark
		String hql="  from TbOrationRecord where   itemId=? and  valid=? and (CONVERT(varchar(100), beginDate, 23)<=?) and (CONVERT(varchar(100), endDate, 23)>=?)  order by showOrder desc";
		String dateForm=CommonUtil.getDateForm(); 
		 
		List  list=this.findPage(hql,0,10,itemId,true,dateForm ,dateForm  );
		return list;  
	}
	public void add(MsgItemNewsBean msgItemNewsBean){
		if(msgItemNewsBean.getType()!=9) return;
		TbOrationRecord tbOrationRecord=new TbOrationRecord();
		tbOrationRecord.setOrationId(Integer.toString(msgItemNewsBean.getId())) ;
		 
		tbOrationRecord.setItemId("NEWS"); 
		if(findById(Integer.toString(msgItemNewsBean.getId()))!=null)
			   return; 
		Calendar cal = Calendar.getInstance(); 
		Date date=cal.getTime(); 
		tbOrationRecord.setBeginDate(new Timestamp(date.getTime()));
		cal.add(Calendar.YEAR, 5);
		date=cal.getTime(); 
		tbOrationRecord.setEndDate(new Timestamp(date.getTime())); 
		tbOrationRecord.setDescription(msgItemNewsBean.getDesc()); 
		List<MultiItemNewsBean> list=msgItemNewsBean.getMulti_item();
		if(list!=null&& list.size()>0){
			tbOrationRecord.setPicUrl(list.get(0).getCover());
		}
		tbOrationRecord.setShowOrder(msgItemNewsBean.getId());
		tbOrationRecord.setTitle(msgItemNewsBean.getTitle());
		tbOrationRecord.setUrl(msgItemNewsBean.getContent_url());
		tbOrationRecord.setValid(true);  
		this.saveOrUpdate(tbOrationRecord); 
		
	}
	
}