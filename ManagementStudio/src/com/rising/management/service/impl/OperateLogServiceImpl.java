package com.rising.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.common.DateUtil;
import com.rising.management.dao.OperateLogDao;
import com.rising.management.dao.OrderInfoDao;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.UserCheckinDao;
import com.rising.management.dao.UserInfoDao;
import com.rising.management.dao.WinnerListDao;
import com.rising.management.service.OperateLogService;
import com.rising.management.service.PlaceService;

@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService {

	@Autowired
	OperateLogDao operateLogDao;

	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	PlaceDao placeDao;
	@Autowired
	PlaceService placeService;
	
	@Autowired OrderInfoDao orderInfoDao;
	
	@Autowired UserCheckinDao userCheckinDao;
	
	@Autowired WinnerListDao winnerListDao;

	@Override
	public HashMap<String, Object> getAppUserStatistics(String startTime,
			String endTime) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			Integer newRegister = operateLogDao.getNewRegisterNumber(startDate,
					endDate);
			Integer allRegister = userInfoDao.getAllUserNumber();
			map.put("newRegister", newRegister);
			map.put("allRegister", allRegister);
		} catch (ParseException e1) {
			map.put("respCode", -1);
			map.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAppUserStatisticsAll(
			String placeName, String startTime, String endTime) {
		Date startDate = null;
		Date endDate = null;
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		ArrayList<String> pc = placeDao.getPName();
		if (null == placeName && "".equals(placeName)
				&& "null".equals(placeName)) {
			placeName = null;
		}

		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			HashMap<String, Object> newUser = userInfoDao.getNewRegisterNum2(
					placeName, startDate, endDate);
			HashMap<String, Object> allUser = userInfoDao
					.getAllUserNumber2(placeName);
			Integer AllNewRegister = userInfoDao.getNewResNum(startDate,
					endDate);
			Integer All = userInfoDao.getAll();
			HashMap<String, Object> nc = placeDao.getCodeName(placeName);
			if (null != placeName && !"".equals(placeName)
					&& !"null".equals(placeName)) {
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				if (newUser.get(placeName) == null) {
					map1.put("pName", placeName);
					map1.put("newRegister", 0);
				} else {
					map1.put("pName", placeName);
					map1.put("newRegister", newUser.get(placeName));
				}
				if (allUser.get(placeName) == null) {
					map1.put("pName", placeName);
					map1.put("allRegister", 0);
				} else {
					map1.put("pName", placeName);
					map1.put("allRegister", allUser.get(placeName));
				}
				map1.put("code", nc.get(placeName));
				listMap.add(map1);
			} else {
				for (String pName : pc) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					if (newUser.get(pName) == null) {
						map1.put("pName", pName);
						map1.put("newRegister", 0);
					} else {
						map1.put("pName", pName);
						map1.put("newRegister", newUser.get(pName));
					}
					if (allUser.get(pName) == null) {
						map1.put("pName", pName);
						map1.put("allRegister", 0);
					} else {
						map1.put("pName", pName);
						map1.put("allRegister", allUser.get(pName));
					}
					map1.put("code", nc.get(pName));
					map1.put("AllNewRegister", AllNewRegister);
					map1.put("All", All);
					listMap.add(map1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public HashMap<String, Object> getAppUserStatisticsAll1(String placeName,
			String startTime, String endTime, String toTime) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<String> pc = placeDao.getPName();
		HashMap<String, Object> codeName = placeService.getPlaceCodeForName();
		String placeCode = null;
		if (codeName.get(placeName) != null) {
			placeCode = (String) codeName.get(placeName);
		}
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			String xAxis = "";
			String newshuju = "";
			String allshuju = "";
			Integer startYear = Integer.parseInt(startTime.split("-")[0]);
			Integer endYear = Integer.parseInt(endTime.split("-")[0]);
			int sum = userInfoDao.getSumUserInfo(placeCode, startDate);
			if ("".equals(placeName) && "0".equals(toTime)) {
				for (String pName : pc) {
					if (null != placeName && !"".equals(placeName)
							&& !"null".equals(placeName)) {
						// Integer newRegister =
						// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
						Integer newRegister = userInfoDao.getNewRegisterNum(
								placeName, startDate, endDate);
						Integer allRegister = userInfoDao
								.getAllUserNumber(placeName);
						xAxis = xAxis + pName + ",";
						newshuju = newshuju + newRegister + ",";
						allshuju = allshuju + allRegister + ",";

					} else {
						Integer newRegister = userInfoDao.getNewRegisterNum(
								pName, startDate, endDate);
						Integer allRegister = userInfoDao
								.getAllUserNumber(pName);
						xAxis = xAxis + pName + ",";
						newshuju = newshuju + newRegister + ",";
						allshuju = allshuju + allRegister + ",";

					}
				}
				String xAxis1 = xAxis.substring(0, xAxis.length() - 1);
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);
			} else if ("".equals(placeName) && "1".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					Integer newrt = null;
					if (resultMap.get(year.toString()) == null) {
						newrt = 0;
					} else {
						String newrtString = resultMap.get(year.toString())
								.toString();
						newrt = Integer.parseInt(newrtString);
					}

					sum = sum + newrt;
					xAxis = xAxis + year + ",";
					newshuju = newshuju + newrt + ",";
					allshuju = allshuju + sum + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);

			} else if ("".equals(placeName) && "2".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, toTime);
				Integer startMonth = Integer.parseInt(startTime.split("-")[1]);
				Integer endMonth = Integer.parseInt(endTime.split("-")[1]);
				if (startYear.equals(endYear)) {
					Integer months = endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						Integer month = startMonth + i;
						String month1 = "";
						if (month < 10) {
							month1 = startYear + "-0" + month;
						} else {
							month1 = startYear + "-" + month;
						}
						Integer newrt = null;
						if (resultMap.get(month1) == null) {
							newrt = 0;
						} else {
							String newrtString = resultMap.get(month1)
									.toString();
							newrt = Integer.parseInt(newrtString);
						}
						sum = sum + newrt;
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
						allshuju = allshuju + sum + ",";

					}
				}else {
					Integer months=12+endMonth-startMonth;
					for(int i=0;i<=months;i++){
						int month=startMonth+i;
						String month1="";
						if(month<10){
							month1=startYear+"-0"+month;
						}else if(month<=12&&month>=10){
							month1=startYear+"-"+month;
						}else if(month>12){
							int cha=month-12;
							if(cha<10){
								month1=endYear+"-0"+cha;
							}else{
								month1=endYear+"-"+cha;
							}
						}
						Integer newrt = null;
						if (resultMap.get(month1) == null) {
							newrt = 0;
						} else {
							String newrtString = resultMap.get(month1)
									.toString();
							newrt = Integer.parseInt(newrtString);
						}
						sum = sum + newrt;
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
						allshuju = allshuju + sum + ",";
					}

				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);

			} else if ("".equals(placeName) && "3".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, "4");
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Calendar c = Calendar.getInstance();
				c.setTime(end);
				c.add(Calendar.DAY_OF_MONTH, 1);
				end = c.getTime();
				long quto = (end.getTime() - start.getTime()) / 1000 / 60 / 60
						/ 24;
				long size = 0;
				if (quto % 7 == 0) {
					size = quto / 7;
				} else {
					size = (quto / 7) + 1;
				}
				for (int i = 1; i <= size; i++) {
					String day1 = "第" + i + "周";
					Integer sumZhou = 0;
					if (i == size) {
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day) == null) {
								sumZhou = sumZhou + 0;
							} else {
								String newrtString = resultMap.get(day)
										.toString();
								sumZhou = sumZhou
										+ Integer.parseInt(newrtString);
							}
						}
					} else {
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day) == null) {
								sumZhou = sumZhou + 0;
							} else {
								String newrtString = resultMap.get(day)
										.toString();
								sumZhou = sumZhou
										+ Integer.parseInt(newrtString);
							}

						}
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, 7);
						start = c.getTime();
					}

					sum = sum + sumZhou;
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + sumZhou + ",";
					allshuju = allshuju + sum + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);

			} else if ("".equals(placeName) && "4".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, toTime);
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long quto = (end.getTime() - start.getTime()) / 1000 / 60 / 60
						/ 24;
				Calendar c = Calendar.getInstance();
				for (int i = 0; i <= quto; i++) {
					c.setTime(start);
					c.add(Calendar.DAY_OF_MONTH, i);
					String day = new SimpleDateFormat("yyyy-MM-dd").format(c
							.getTime());
					String day1 = day.split("-")[1] + "-" + day.split("-")[2];
					Integer newrt = null;
					if (resultMap.get(day) == null) {
						newrt = 0;
					} else {
						String newrtString = resultMap.get(day).toString();
						newrt = Integer.parseInt(newrtString);
					}
					sum = sum + newrt;
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + newrt + ",";
					allshuju = allshuju + sum + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);

			} else if (placeName.length() > 1 && "0".equals(toTime)) {
				Integer newRegister = userInfoDao.getNewRegisterNum(placeName,
						startDate, endDate);
				Integer allRegister = userInfoDao.getAllUserNumber(placeName);
				xAxis = placeName + ",";
				newshuju = "[" + newRegister + "]";
				allshuju = "[" + allRegister + "]";
				map.put("xAxis", xAxis);
				map.put("newshuju", newshuju);
				map.put("allshuju", allshuju);

			} else if (placeName.length() > 1 && "1".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					Integer newrt = null;
					if (resultMap.get(year.toString()) == null) {
						newrt = 0;
					} else {
						String newrtString = resultMap.get(year.toString())
								.toString();
						newrt = Integer.parseInt(newrtString);
					}

					sum = sum + newrt;
					xAxis = xAxis + year + ",";
					newshuju = newshuju + newrt + ",";
					allshuju = allshuju + sum + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);

			} else if (placeName.length() > 1 && "2".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, toTime);
				Integer startMonth = Integer.parseInt(startTime.split("-")[1]);
				Integer endMonth = Integer.parseInt(endTime.split("-")[1]);
				if (startYear.equals(endYear)) {
					Integer months = endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						Integer month = startMonth + i;
						String month1 = "";
						if (month < 10) {
							month1 = startYear + "-0" + month;
						} else {
							month1 = startYear + "-" + month;
						}
						Integer newrt = null;
						if (resultMap.get(month1) == null) {
							newrt = 0;
						} else {
							String newrtString = resultMap.get(month1)
									.toString();
							newrt = Integer.parseInt(newrtString);
						}
						sum = sum + newrt;
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
						allshuju = allshuju + sum + ",";

					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && startMonth < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && startMonth >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						Integer newrt = null;
						if (resultMap.get(month1) == null) {
							newrt = 0;
						} else {
							String newrtString = resultMap.get(month1)
									.toString();
							newrt = Integer.parseInt(newrtString);
						}
						sum = sum + newrt;
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
						allshuju = allshuju + sum + ",";
					}

				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);

			} else if (placeName.length() > 1 && "3".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, "4");
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Calendar c = Calendar.getInstance();
				c.setTime(end);
				c.add(Calendar.DAY_OF_MONTH, 1);
				end = c.getTime();
				long quto = (end.getTime() - start.getTime()) / 1000 / 60 / 60
						/ 24;
				long size = 0;
				if (quto % 7 == 0) {
					size = quto / 7;
				} else {
					size = (quto / 7) + 1;
				}
				for (int i = 1; i <= size; i++) {
					String day1 = "第" + i + "周";
					Integer sumZhou = 0;
					if (i == size) {
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day) == null) {
								sumZhou = sumZhou + 0;
							} else {
								String newrtString = resultMap.get(day)
										.toString();
								sumZhou = sumZhou
										+ Integer.parseInt(newrtString);
							}
						}
					} else {
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day) == null) {
								sumZhou = sumZhou + 0;
							} else {
								String newrtString = resultMap.get(day)
										.toString();
								sumZhou = sumZhou
										+ Integer.parseInt(newrtString);
							}

						}
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, 7);
						start = c.getTime();
					}

					sum = sum + sumZhou;
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + sumZhou + ",";
					allshuju = allshuju + sum + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);
			} else if (placeName.length() > 1 && "4".equals(toTime)) {
				resultMap = userInfoDao.getMonthByUserInfo(placeCode,
						startDate, endDate, toTime);
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long quto = (end.getTime() - start.getTime()) / 1000 / 60 / 60
						/ 24;
				Calendar c = Calendar.getInstance();
				for (int i = 0; i <= quto; i++) {
					c.setTime(start);
					c.add(Calendar.DAY_OF_MONTH, i);
					String day = new SimpleDateFormat("yyyy-MM-dd").format(c
							.getTime());
					String day1 = day.split("-")[1] + "-" + day.split("-")[2];
					Integer newrt = null;
					if (resultMap.get(day) == null) {
						newrt = 0;
					} else {
						String newrtString = resultMap.get(day).toString();
						newrt = Integer.parseInt(newrtString);
					}
					sum = sum + newrt;
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + newrt + ",";
					allshuju = allshuju + sum + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				String allshuju1 = "["
						+ allshuju.substring(0, allshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				map.put("allshuju", allshuju1);
			}

		} catch (ParseException e1) {
			map.put("respCode", -1);
			map.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getLostUserStatistics(Integer pageSize,Integer pageIndex,Integer dayNumber,
			String phoneNumber,Boolean noOrder) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> ahm =null;
		if(!noOrder){
			if(phoneNumber!=null){
				ahm = userInfoDao.findLostUser(phoneNumber);
				if(ahm!=null && ahm.size()>0){
					ArrayList<String> phoneNumbers = getPhoneNumbers(ahm);
					ArrayList<HashMap<String, Object>> ahm2 = orderInfoDao.getDetailOrderInfo(phoneNumbers);
					ArrayList<HashMap<String,Object>> ahm3 = userCheckinDao.getUserCheckinInfo(phoneNumbers);
					ArrayList<HashMap<String,Object>> ahm4 = winnerListDao.getUserWinningInfo(phoneNumbers);
					ahm.get(0).put("checkinTime", ahm3.get(0).get("lastTime"));
					ahm.get(0).put("orderTime",ahm2.get(0).get("lastTime"));
					ahm.get(0).put("winningTime", ahm4.get(0).get("winningTime"));
					resultMap.put("listSize", 1);
				}else{
					resultMap.put("listSize", 0);
				}			
			}else{
				Date lastDay;
				if(dayNumber==null ||dayNumber == 0){
					lastDay = new Date();
				}else{
					DateUtil d = new DateUtil();
					lastDay = d.getLastDay(dayNumber);
				}
				ahm = orderInfoDao.getDetailOrderInfo2(lastDay);
				if(ahm!=null && ahm.size()>0){
					Integer listSize = ahm.size();
					Integer start = (pageIndex - 1) * pageSize;
					ArrayList<HashMap<String,Object>> ahm2 = new ArrayList<HashMap<String,Object>>();
					for(int i=0;i<pageSize && i<listSize-start;i++){
						ahm2.add(ahm.get(start+i));
					}
					ArrayList<String> phoneNumbers = getPhoneNumbers(ahm2);
					ArrayList<HashMap<String,Object>> ahm3 = userInfoDao.findLostUserInfo(phoneNumbers);
					HashMap<String,Integer> ais2 = getUserIndex(ahm3);
					ArrayList<HashMap<String,Object>> ahm4 = userCheckinDao.getUserCheckinInfo(phoneNumbers);
					HashMap<String,Integer> ais3 = getUserIndex(ahm4);
					ArrayList<HashMap<String,Object>> ahm5 = winnerListDao.getUserWinningInfo(phoneNumbers);
					HashMap<String,Integer> ais4 = getUserIndex(ahm5);
					for (HashMap<String, Object> hashMap : ahm2) {
						String phoneNumber1 = (String) hashMap.get("phoneNumber");
						hashMap.put("score",ahm3.get(ais2.get(phoneNumber1)).get("score"));
						hashMap.put("registerTime",ahm3.get(ais2.get(phoneNumber1)).get("registerTime"));
						hashMap.put("address",ahm3.get(ais2.get(phoneNumber1)).get("address"));
						hashMap.put("checkinTime",ahm4.get(ais3.get(phoneNumber1)).get("lastTime"));
						hashMap.put("winningTime",ahm5.get(ais4.get(phoneNumber1)).get("winningTime"));
					}
					ahm = ahm2;
					resultMap.put("listSize", listSize);
				}else{
					resultMap.put("listSize", 0);
				}
			}
		}else{
			ahm = userInfoDao.findLostUser2();
			if(ahm!=null && ahm.size()>0){
				Integer listSize = ahm.size();
				Integer start = (pageIndex - 1) * pageSize;
				ArrayList<HashMap<String,Object>> ahm2 = new ArrayList<HashMap<String,Object>>();
				for(int i=0;i<pageSize && i<listSize-start;i++){
					ahm2.add(ahm.get(start+i));
				}
				ArrayList<String> phoneNumbers = getPhoneNumbers(ahm2);
				ArrayList<HashMap<String,Object>> ahm3 = userCheckinDao.getUserCheckinInfo(phoneNumbers);
				HashMap<String,Integer> ais = getUserIndex(ahm3);
				ArrayList<HashMap<String,Object>> ahm4 = winnerListDao.getUserWinningInfo(phoneNumbers);
				HashMap<String,Integer> ais2 = getUserIndex(ahm4);
				for (HashMap<String, Object> hashMap : ahm2) {
					String phoneNumber1 = (String) hashMap.get("phoneNumber");
					hashMap.put("checkinTime",ahm3.get(ais.get(phoneNumber1)).get("lastTime"));
					hashMap.put("winningTime",ahm4.get(ais2.get(phoneNumber1)).get("winningTime"));
				}
				ahm = ahm2;
				resultMap.put("listSize", listSize);
			}else{
				resultMap.put("listSize", 0);
			}
		}
		
		resultMap.put("rows", ahm);
		return resultMap;
	}
	
	private HashMap<String, Integer> getUserIndex(
			ArrayList<HashMap<String, Object>> ahm) {
		HashMap<String,Integer> hsi = new HashMap<String, Integer>();
		for (int i=0;ahm!=null &&i<ahm.size();i++) {
			hsi.put(String.valueOf(ahm.get(i).get("phoneNumber")),i);
		}
		return hsi;
	}

	private ArrayList<String> getPhoneNumbers(
			ArrayList<HashMap<String, Object>> ahm) {
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		for (HashMap<String, Object> hashMap : ahm) {
			phoneNumbers.add(String.valueOf(hashMap.get("phoneNumber")));
		}
		return phoneNumbers;
	}

}
