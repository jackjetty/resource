package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.mobilepayment.bean.QQPerMonthRecord;
import com.rising.mobilepayment.commom.DateUtil;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.service.QQPerMonthRecordService;


@Service("qQPerMonthRecordService")
public class QQPerMonthRecordServiceImpl implements QQPerMonthRecordService {
	
	@Autowired QQPerMonthRecordMapper qQPerMonthRecordMapper;

	@Override
	public ArrayList<String> getQQPerMonthHasPay() {
		DateUtil d = new DateUtil();
		HashMap<String,Date> dateMap = d.getThisMonthTime();
		ArrayList<String> as = qQPerMonthRecordMapper.getQQPerMonthHasPay(dateMap);
		return as;
	}

	@Override
	public void save(QQPerMonthRecord record) {
		qQPerMonthRecordMapper.add(record);
	}

}
