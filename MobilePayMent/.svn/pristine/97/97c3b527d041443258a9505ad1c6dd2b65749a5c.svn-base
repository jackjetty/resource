package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.QQPerMonthStatus;


@Component("qQPerMonthStatusMapper2")
public interface QQPerMonthStatusMapper2 {

	public void addQQPerMonthStatus(QQPerMonthStatus status);
	
	public void updateQQPerMonthStatus(QQPerMonthStatus status);

	public ArrayList<QQPerMonthStatus> findByPhoneNumber(String phoneNumber);

	public Integer getQuXiaoNumber();

	public Integer getKaiTongNumber();

	public ArrayList<QQPerMonthStatus> getKaiTongButPayFailed(String thisMonth);
}
