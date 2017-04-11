package com.rising.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.QQPerMonthStatistics;
import com.rising.management.bean.QQPermonthRecode;
import com.rising.management.dao.OrderInfoDao;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.ProductDao;
import com.rising.management.dao.QQPerMonthStatisticsDao;
import com.rising.management.dao.QQPermonthRecodeDao;
import com.rising.management.dao.QQPermonthStatusDao;
import com.rising.management.service.QQPermonthRecodeService;
import com.rising.management.vo.QQPermonthRecodeVo;

@Service("qqPermonthRecodeService")
public class QQPermonthRecodeServiceImpl implements QQPermonthRecodeService {

	@Autowired
	QQPermonthRecodeDao qqPermonthRecodeDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	OrderInfoDao orderInfoDao;

	@Autowired
	QQPermonthStatusDao qQPermonthStatusDao;
	
	@Autowired QQPerMonthStatisticsDao qQPerMonthStatisticDao;

	@Autowired
	PlaceDao placeDao;

	@Override
	public HashMap<String, Object> getQQPermonthRecode(String phoneNumber,
			String startTime, String endTime, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime + " 00:00:00");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime + " 23:59:59");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}

		Integer listSize = qqPermonthRecodeDao.getQQPermonthRecodeListSize(
				phoneNumber, startDate, endDate);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<QQPermonthRecodeVo> prv = new ArrayList<QQPermonthRecodeVo>();
		ArrayList<QQPermonthRecode> pr = qqPermonthRecodeDao
				.getQQPermonthRecode(phoneNumber, startDate, endDate, pageSize,
						start);
		for (QQPermonthRecode p : pr) {
			prv.add(new QQPermonthRecodeVo(p));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", prv);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getQQStatistics(String startTime,
			String endTime,String toTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		ArrayList<QQPerMonthStatistics> aqp = null;
		ArrayList<Integer> newshuju1 = new ArrayList<Integer>();
		ArrayList<Integer> newshuju2 = new ArrayList<Integer>();
		ArrayList<Integer> newshuju3 = new ArrayList<Integer>();
		String xAxis = "";
		try {
			if ("".equals(startTime)) {
				startDate = null;
			} else {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime + " 00:00:00");
			}
			if ("".equals(endTime)) {
				endDate = null;
			} else {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime + " 23:59:59");
			}
			if("3".equals(toTime)){
				 aqp = qQPerMonthStatisticDao.getRecord(startDate,endDate,"WEEKENDCHECK");
			}else if("2".equals(toTime)){
				 aqp = qQPerMonthStatisticDao.getRecord(startDate,endDate,"MONTHENDCHECK");
			}else if("1".equals(toTime)){
				 aqp = qQPerMonthStatisticDao.getRecord(startDate,endDate,"YEARENDCHECK");
			}
			if(aqp!=null){
				for (QQPerMonthStatistics qqPerMonthStatistic : aqp) {
					newshuju1.add(qqPerMonthStatistic.getKaiTongNumber());
					newshuju2.add(qqPerMonthStatistic.getQuXiaoNumber());
					newshuju3.add(qqPerMonthStatistic.getKouFeiNumber());
					xAxis = xAxis + new SimpleDateFormat("yyyy-MM-dd").format(qqPerMonthStatistic.getStatisticsTime())+",";
				}
				map.put("newshuju1", newshuju1);
				map.put("newshuju2", newshuju2);
				map.put("newshuju3", newshuju3);
				map.put("xAxis", xAxis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getUserQQPerMonthRecord(Integer pageSize,
			Integer pageIndex, String phoneNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = qqPermonthRecodeDao
				.getUserQQPermonthRecodeListSize(phoneNumber);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<QQPermonthRecodeVo> prv = new ArrayList<QQPermonthRecodeVo>();
		ArrayList<QQPermonthRecode> pr = qqPermonthRecodeDao
				.getUserQQPermonthRecode(phoneNumber, pageSize, start);
		for (QQPermonthRecode p : pr) {
			prv.add(new QQPermonthRecodeVo(p));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", prv);
		return resultMap;
	}

}
