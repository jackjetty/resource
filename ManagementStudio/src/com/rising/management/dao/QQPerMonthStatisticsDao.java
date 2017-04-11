package com.rising.management.dao;

import java.util.ArrayList;
import java.util.Date;

import com.rising.management.bean.QQPerMonthStatistics;

public interface QQPerMonthStatisticsDao {

	public ArrayList<QQPerMonthStatistics> getRecord(Date startDate, Date endDate,
			String type);

}
