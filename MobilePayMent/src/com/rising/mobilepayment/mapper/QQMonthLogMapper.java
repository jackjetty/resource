package com.rising.mobilepayment.mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.QQMonthLog;

@Component("qqMonthLogMapper")
public interface QQMonthLogMapper {
	public QQMonthLog findById(String monthLogId);
	
	public QQMonthLog findByPQM(String phoneNumber,String qqNumber,String targetMonth);
	
	public List<QQMonthLog> getUnCheckList(HashMap<String, Object> map );
	
	public List<QQMonthLog> getUnSendMsgList(HashMap<String, Object> map );
	
	public List<QQMonthLog> getListByPQM(String phoneNumber,String qqNumber,String targetMonth);
	
	
	public void add(QQMonthLog qqMonthLog);
	
	public void update(QQMonthLog qqMonthLog);
	
	
	public void monthInit(String monthStrForm);
	
	
	public Integer getCountByMonth(HashMap<String, Object> map);
	
	
}