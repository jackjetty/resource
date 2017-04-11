package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(StatisticKey.class)
@Table(name = "RS_QQPERMONTHSTATISTICS")
public class QQPerMonthStatistics {

	@Id
	private Date statisticsTime;

	@Id
	private String statisticsType;

	private Integer kaiTongNumber;

	private Integer quXiaoNumber;

	private Integer kouFeiNumber;

	public Date getStatisticsTime() {
		return statisticsTime;
	}

	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	public String getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	public Integer getKaiTongNumber() {
		return kaiTongNumber;
	}

	public void setKaiTongNumber(Integer kaiTongNumber) {
		this.kaiTongNumber = kaiTongNumber;
	}

	public Integer getQuXiaoNumber() {
		return quXiaoNumber;
	}

	public void setQuXiaoNumber(Integer quXiaoNumber) {
		this.quXiaoNumber = quXiaoNumber;
	}

	public Integer getKouFeiNumber() {
		return kouFeiNumber;
	}

	public void setKouFeiNumber(Integer kouFeiNumber) {
		this.kouFeiNumber = kouFeiNumber;
	}

}
