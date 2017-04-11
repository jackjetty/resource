package com.rising.mobilepayment.service;

import java.util.ArrayList;

import com.rising.mobilepayment.bean.QQPerMonthRecord;

public interface QQPerMonthRecordService {

	public ArrayList<String> getQQPerMonthHasPay();

	public void save(QQPerMonthRecord record);

}
