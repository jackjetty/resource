package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.QQPerMonthRecord;

@Component("qQPerMonthRecordMapper")
public interface QQPerMonthRecordMapper {

	public ArrayList<String> getQQPerMonthHasPay(HashMap<String, Date> dateMap);

	public void add(QQPerMonthRecord record);

	public Float getThisMonthPay(HashMap<String, Object> paraMap);

	public Integer getKouFeiNumber(HashMap<String, Date> map);

	public ArrayList<QQPerMonthRecord> findByPhoneNumber(String phoneNumber);

}
