package com.rising.mobilepayment.job;

import java.util.Date;
import java.util.HashMap;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.rising.mobilepayment.bean.QQPerMonthStatistics;
import com.rising.mobilepayment.commom.DateUtil;
import com.rising.mobilepayment.commom.GetApplicationContext;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.mapper.QQPerMonthStatisticsMapper;
import com.rising.mobilepayment.mapper.QQPerMonthStatusMapper;

public class QQPerMonthStatisticsJob3 extends QuartzJobBean {

	private static String type = "YEARENDCHECK";

	private QQPerMonthStatisticsMapper qQPerMonthStatisticsMapper;

	private QQPerMonthStatusMapper qQPerMonthStatusMapper;

	private QQPerMonthRecordMapper qQPerMonthRecordMapper;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		ApplicationContext applicationContext = GetApplicationContext
				.getApplicationContext();
		qQPerMonthStatisticsMapper = (QQPerMonthStatisticsMapper) applicationContext
				.getBean("qQPerMonthStatisticsMapper");
		qQPerMonthStatusMapper = (QQPerMonthStatusMapper) applicationContext
				.getBean("qQPerMonthStatusMapper");
		qQPerMonthRecordMapper = (QQPerMonthRecordMapper) applicationContext
				.getBean("qQPerMonthRecordMapper");
		DateUtil d = new DateUtil();
		HashMap<String,Date> map = d.getThisYearTime();
		Integer kaitongNumber = qQPerMonthStatusMapper.getKaiTongNumber();
		Integer quxiaoNumber = qQPerMonthStatusMapper.getQuXiaoNumber();
		Integer koufeiNumber = qQPerMonthRecordMapper.getKouFeiNumber(map);
		QQPerMonthStatistics statistics = new QQPerMonthStatistics();
		statistics.setStatisticsTime(new Date());
		statistics.setStatisticsType(type);
		statistics.setKaiTongNumber(kaitongNumber);
		statistics.setKouFeiNumber(koufeiNumber);
		statistics.setQuXiaoNumber(quxiaoNumber);
		qQPerMonthStatisticsMapper.addStatisticsRecord(statistics);

	}

}
