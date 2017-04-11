package com.rising.management.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Business;
import com.rising.management.bean.OrderInfo;
import com.rising.management.bean.Product;
import com.rising.management.bean.UserInfo;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.OrderInfoDao;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.ProductDao;
import com.rising.management.dao.QQPermonthRecodeDao;
import com.rising.management.dao.ReturnCodeDao;
import com.rising.management.dao.UserInfoDao;
import com.rising.management.dao.WinnerListDao;
import com.rising.management.service.OrderInfoService;
import com.rising.management.service.PlaceService;
import com.rising.management.vo.OrderInfoVo;
import com.rising.management.vo.RandomDraw;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	OrderInfoDao orderInfoDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	QQPermonthRecodeDao qqPermonthRecodeDao;

	@Autowired
	PlaceDao placeDao;

	@Autowired
	PlaceService placeService;

	@Autowired
	BusinessDao businessDao;

	@Autowired
	UserInfoDao userInfoDao;

	@Autowired
	WinnerListDao winnerListDao;
	@Autowired
	ReturnCodeDao returnCodeDao;

	@Override
	public HashMap<String, Object> getOrderInfo(Integer pageSize,
			Integer pageIndex, String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed,
			String startTime, String endTime, String payReturnCode,
			String targetNumber, String os, String client) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		if ("".equals(targetNumber)) {
			targetNumber = null;
		}
		if ("".equals(productId)) {
			productId = null;
		}
		if ("".equals(payReturnCode)) {
			payReturnCode = null;
		}
		if ("".equals(os)) {
			os = null;
		}
		if ("".equals(client)) {
			client = null;
		}
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
		Integer listSize = orderInfoDao.getOrderInfoListSize(phoneNumber,
				busId, productId, success, failed, startDate, endDate,
				payReturnCode, targetNumber, os, client);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<OrderInfo> am = orderInfoDao.getOrderInfo(phoneNumber, busId,
				productId, success, failed, start, pageSize, startDate,
				endDate, payReturnCode, targetNumber, os, client);
		ArrayList<OrderInfoVo> aov = new ArrayList<OrderInfoVo>();
		HashMap<String, Object> productMap = productDao.getProductMap();
		for (int i = 0; am != null && i < am.size(); i++) {
			OrderInfoVo order = new OrderInfoVo(am.get(i), productMap);
			aov.add(order);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", aov);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getStatistics(String startTime,
			String endTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		ArrayList<String> ap = productDao.getProductIds();
		ArrayList<Business> ab = businessDao.getBusiness();
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			for (String id : ap) {
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				Integer paySuccess = orderInfoDao.getPaySuccess(id, startDate,
						endDate);
				resultMap.put("paySuccess", paySuccess);
				Integer payFailed = orderInfoDao.getPayFailed(id, startDate,
						endDate);
				resultMap.put("payFailed", payFailed);
				Integer allPay = paySuccess + payFailed;
				resultMap.put("allPay", allPay);
				double allPayMoney = orderInfoDao.getAllPayMoney(id, startDate,
						endDate);
				resultMap.put("allPayMoney", allPayMoney);
				Integer allPayPhoneNumbers = orderInfoDao
						.getAllPayPhoneNumbers(id, startDate, endDate);
				resultMap.put("allPayPhoneNumbers", allPayPhoneNumbers);
				Integer paySuccessPhoneNumbers = orderInfoDao
						.getPaySuccessPhoneNumbers(id, startDate, endDate);
				resultMap.put("paySuccessPhoneNumbers", paySuccessPhoneNumbers);
				Integer payFailedPhoneNumbers = orderInfoDao
						.getPayFailedPhoneNumbers(id, startDate, endDate);
				resultMap.put("payFailedPhoneNumbers", payFailedPhoneNumbers);
				map.put(id, resultMap);
			}

			HashMap<String, Object> map1 = orderInfoDao
					.getAllPayMoneyByBusiness(ab, startDate, endDate);
			Double AllQQ = (Double) map1.get("Q币");
			Double AllVB = (Double) map1.get("V币");
			Double AllTM = (Double) map1.get("淘米币");
			Double All = AllQQ + AllVB + AllTM;
			map.put("AllQQ", AllQQ);
			map.put("AllVB", AllVB);
			map.put("AllTM", AllTM);
			map.put("All", All);
		} catch (ParseException e1) {
			map.put("respCode", -1);
			map.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Integer getOrderInfoSize(String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed,

			String startTime, String endTime, String payReturnCode,
			String targetNumber, String os, String client) {

		Date startDate = null;
		Date endDate = null;
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		if ("".equals(targetNumber)) {
			targetNumber = null;
		}
		if ("".equals(productId)) {
			productId = null;
		}
		if ("".equals(payReturnCode)) {
			payReturnCode = null;
		}
		if ("".equals(os)) {
			os = null;
		}
		if ("".equals(client)) {
			client = null;
		}
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime + " 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		Integer listSize = orderInfoDao.getOrderInfoListSize(phoneNumber,
				busId, productId, success, failed, startDate, endDate,
				payReturnCode, targetNumber, os, client);
		return listSize;
	}

	@Override
	public ArrayList<OrderInfoVo> getOrderInfoVo(Integer pageSize,
			Integer pageIndex, String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed,
			String startTime, String endTime, String payReturnCode,
			String targetNumber, String os, String client) {

		Date startDate = null;
		Date endDate = null;
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		if ("".equals(targetNumber)) {
			targetNumber = null;
		}
		if ("".equals(productId)) {
			productId = null;
		}
		if ("".equals(payReturnCode)) {
			payReturnCode = null;
		}
		if ("".equals(os)) {
			os = null;
		}
		if ("".equals(client)) {
			client = null;
		}
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime + " 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Integer start = (pageIndex - 1) * pageSize;

		ArrayList<OrderInfo> am = orderInfoDao.getOrderInfo(phoneNumber, busId,
				productId, success, failed, start, pageSize, startDate,
				endDate, payReturnCode, targetNumber, os, client);
		ArrayList<OrderInfoVo> aov = new ArrayList<OrderInfoVo>();
		HashMap<String, Object> productMap = productDao.getProductMap();
		HashMap<String, String> phonePlaceMap = userInfoDao.getUserPlace();
		for (int i = 0; am != null && i < am.size(); i++) {
			OrderInfoVo order = new OrderInfoVo(am.get(i), productMap);
			order.setPlace((String) phonePlaceMap.get(order.getPhoneNumber()));
			aov.add(order);
		}
		return aov;
	}

	@Override
	public HashMap<String, Object> getStatisticsAll(String startTime,
			String endTime, String place, Integer busId) {
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		ArrayList<String> ap = productDao.getProductIds(busId);
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			for (String id : ap) {
				HashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
				ArrayList<String> pd = productDao.getProductDescribe(id);
				for (int i = 0; i < pd.size(); i++) {
					String productDescribe = pd.get(i);
					resultMap.put("productDescribe", productDescribe);
					Integer paySuccess = orderInfoDao.getPaySuccess(id,
							startDate, endDate, place);
					resultMap.put("paySuccess", paySuccess);
					Integer payFailed = orderInfoDao.getPayFailed(id,
							startDate, endDate, place);
					resultMap.put("payFailed", payFailed);
					Integer allPay = paySuccess + payFailed;
					resultMap.put("allPay", allPay);
					double allPayMoney = orderInfoDao.getAllPayMoney(id,
							startDate, endDate, place);
					resultMap.put("allPayMoney", allPayMoney);
					Integer allPayPhoneNumbers = orderInfoDao
							.getAllPayPhoneNumbers(id, startDate, endDate,
									place);
					resultMap.put("allPayPhoneNumbers", allPayPhoneNumbers);
					Integer paySuccessPhoneNumbers = orderInfoDao
							.getPaySuccessPhoneNumbers(id, startDate, endDate,
									place);
					resultMap.put("paySuccessPhoneNumbers",
							paySuccessPhoneNumbers);
					Integer payFailedPhoneNumbers = orderInfoDao
							.getPayFailedPhoneNumbers(id, startDate, endDate,
									place);
					resultMap.put("payFailedPhoneNumbers",
							payFailedPhoneNumbers);
					double all = orderInfoDao.getAll(id, startDate, endDate,
							place);
					resultMap.put("all", all);
					Integer number = productDao.getNumberBystatus();
					resultMap.put("number", number);
					map.put(id, resultMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public ArrayList<RandomDraw> getOrderInfoByTen(Integer prizeId,
			String startTime, String endTime, Integer payMoneyMin,
			Integer payMoneyMax, Integer scoreMin, Integer scoreMax,
			String place) {
		ArrayList<UserInfo> au = null;
		ArrayList<Object[]> am = null;
		if (payMoneyMin != null || payMoneyMax != null) {
			Date startDate = null;
			Date endDate = null;
			if ("".equals(startTime)) {
				startDate = null;
			} else {
				try {
					startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(startTime + " 00:00:00");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if ("".equals(endTime)) {
				endDate = null;
			} else {
				try {
					endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(endTime + " 23:59:59");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			am = orderInfoDao.getOrderInfoByTen(startDate, endDate,
					payMoneyMin, payMoneyMax);
		}
		if (scoreMin != null || scoreMax != null) {
			au = userInfoDao.getUserByScoreRound(scoreMin, scoreMax);
		}
		ArrayList<String> as = winnerListDao
				.getWinnerPhoneNumberByPrize(prizeId);
		ArrayList<RandomDraw> rd = new ArrayList<RandomDraw>();
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		if (am != null) {
			for (Object[] obj : am) {
				RandomDraw d = new RandomDraw();
				d.setPhoneNumber(obj[0].toString());
				d.setPayMoney(Integer.parseInt(obj[1].toString()));
				rd.add(d);
				phoneNumbers.add(obj[0].toString());
			}
		}
		if (am != null && au != null) {
			for (UserInfo userInfo : au) {
				if (phoneNumbers.contains(userInfo.getPhoneNumber())) {
					for (RandomDraw randomDraw : rd) {
						if (randomDraw.getPhoneNumber().equals(
								userInfo.getPhoneNumber())) {
							randomDraw.setAllScore(userInfo.getAllScore());
							break;
						}
					}
				}
			}
			for (int i = 0; rd.size() > 0 && i < rd.size();) {
				Float score = rd.get(i).getAllScore();
				if (score == 0.0) {
					rd.remove(i);
				} else {
					i++;
				}
			}
		} else if (am == null && au != null) {
			for (UserInfo userInfo : au) {
				RandomDraw d = new RandomDraw();
				d.setPhoneNumber(userInfo.getPhoneNumber());
				d.setAllScore(userInfo.getAllScore());
				rd.add(d);
				phoneNumbers.add(userInfo.getPhoneNumber());
			}
		} else if (am == null && au == null) {
			return rd;
		}
		if (as != null) {
			for (String phoneNumber : as) {
				for (int i = 0; i < rd.size();) {
					if (phoneNumber.equals(rd.get(i).getPhoneNumber())) {
						rd.remove(i);
					} else {
						i++;
					}
				}
			}
		}
		if (place != null && rd.size() > 0) {
			for (int i = 0; i < rd.size();) {
				UserInfo user = userInfoDao.findUser(
						rd.get(i).getPhoneNumber(), place);
				if (user == null) {
					rd.remove(i);
				} else {
					i++;
				}
			}
		}
		return rd;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getStatisticsAll(Integer busId,
			String startTime, String endTime) {
		Date startDate = null;
		Date endDate = null;
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();

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
			ArrayList<String> bt = businessDao.getBtype(busId);
			HashMap<String, Object> ps = orderInfoDao.getPaySuccess2(busId,
					startDate, endDate);
			Integer AllPaySuccess = orderInfoDao.getAllPaySuccess2(startDate,
					endDate);
			HashMap<String, Object> pf = orderInfoDao.getPayFailed2(busId,
					startDate, endDate);
			Integer AllPayFail = orderInfoDao.getPayFailed2(startDate, endDate);
			HashMap<String, Object> p = orderInfoDao.getAllPay2(busId,
					startDate, endDate);
			Integer AllPay = orderInfoDao.getAllPay2(startDate, endDate);
			HashMap<String, Object> pm = orderInfoDao.getAllPayMoney2(busId,
					startDate, endDate);
			double AllPayMoney = orderInfoDao.getAllPayMoney2(startDate,
					endDate);
			HashMap<String, Object> psn = orderInfoDao
					.getPaySuccessPhoneNumbers2(busId, startDate, endDate);
			Integer AllSuccessNumbers = orderInfoDao
					.getAllPaySuccessPhoneNumbers2(startDate, endDate);
			HashMap<String, Object> pfn = orderInfoDao
					.getPayFailedPhoneNumbers2(busId, startDate, endDate);
			Integer AllFailNumbers = orderInfoDao.getAllPayFailedPhoneNumbers2(
					startDate, endDate);
			HashMap<String, Object> pn = orderInfoDao.getAllPayPhoneNumbers2(
					busId, startDate, endDate);
			Integer AllNumbers = orderInfoDao.getAllPhoneNumbers2(startDate,
					endDate);
			for (String BusId : bt) {
				HashMap<String, Object> map = new LinkedHashMap<String, Object>();
				if (ps.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("paySuccess", 0);
				} else {
					map.put("BusId", BusId);
					map.put("paySuccess", ps.get(BusId));
				}
				if (pf.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("payFailed", 0);
				} else {
					map.put("BusId", BusId);
					map.put("payFailed", pf.get(BusId));
				}
				if (p.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("allPay", 0);
				} else {
					map.put("BusId", BusId);
					map.put("allPay", p.get(BusId));
				}
				if (pm.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("allPayMoney", 0);
				} else {
					map.put("BusId", BusId);
					map.put("allPayMoney", pm.get(BusId));
				}
				if (psn.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("paySuccessPhoneNumbers", 0);
				} else {
					map.put("BusId", BusId);
					map.put("paySuccessPhoneNumbers", psn.get(BusId));
				}
				if (pfn.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("payFailedPhoneNumbers", 0);
				} else {
					map.put("BusId", BusId);
					map.put("payFailedPhoneNumbers", pfn.get(BusId));
				}
				if (pn.get(BusId) == null) {
					map.put("BusId", BusId);
					map.put("allPayPhoneNumbers", 0);
				} else {
					map.put("BusId", BusId);
					map.put("allPayPhoneNumbers", pn.get(BusId));
				}
				map.put("AllPaySuccess", AllPaySuccess);
				map.put("AllPayFail", AllPayFail);
				map.put("AllPay", AllPay);
				map.put("AllPayMoney", AllPayMoney);
				map.put("AllSuccessNumbers", AllSuccessNumbers);
				map.put("AllFailNumbers", AllFailNumbers);
				map.put("AllNumbers", AllNumbers);
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	public ArrayList<String> getProductIds() {
		ArrayList<String> ap = productDao.getProductIds();
		return ap;

	}

	public ArrayList<String> getProductIds(Integer busId) {
		ArrayList<Product> p = productDao.findProductByBusId(busId);
		ArrayList<String> pId = new ArrayList<String>();
		for (int i = 0; i < p.size(); i++) {
			pId.add(p.get(i).getProductId());
		}
		return pId;
	}

	public ArrayList<HashMap<String, Object>> getStatisticsAllByHighCharts(
			String productId, String ways, Integer busId, String startTime,
			String endTime) {
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		Date startDate = null;
		Date endDate = null;
		try {
			if ("".equals(productId)) {
				productId = null;
			}
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

			if (ways.equals("请选择")) {
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				String productDescribe = productDao
						.getProductDescribe2(productId);
				map1.put("productDescribe", productDescribe);
				Integer paySuccess = orderInfoDao.getPaySuccess2(productId,
						busId, startDate, endDate);
				map1.put("paySuccess", paySuccess);
				Integer payFailed = orderInfoDao.getPayFailed2(productId,
						busId, startDate, endDate);
				map1.put("payFailed", payFailed);
				Integer allPay = paySuccess + payFailed;
				map1.put("allPay", allPay);
				double allPayMoney = orderInfoDao.getAllPayMoney2(productId,
						busId, startDate, endDate);
				map1.put("allPayMoney", allPayMoney);
				Integer allPayPhoneNumbers = orderInfoDao
						.getAllPayPhoneNumbers2(productId, busId, startDate,
								endDate);
				map1.put("allPayPhoneNumbers", allPayPhoneNumbers);
				Integer paySuccessPhoneNumbers = orderInfoDao
						.getPaySuccessPhoneNumbers2(productId, busId,
								startDate, endDate);
				map1.put("paySuccessPhoneNumbers", paySuccessPhoneNumbers);
				Integer payFailedPhoneNumbers = orderInfoDao
						.getPayFailedPhoneNumbers2(productId, busId, startDate,
								endDate);
				map1.put("payFailedPhoneNumbers", payFailedPhoneNumbers);
				listMap.add(map1);

			}
			if (("按年").equals(ways)) {
				String start = new SimpleDateFormat("yyyy").format(startDate);
				String end = new SimpleDateFormat("yyyy").format(endDate);
				Integer first = Integer.parseInt(start);
				Integer last = Integer.parseInt(end);
				String productDescribe = productDao
						.getProductDescribe2(productId);
				HashMap<String, Object> ps = orderInfoDao.getPaySuccessByYear(
						productId, busId, start, end);
				HashMap<String, Object> pf = orderInfoDao.getPayFailedByYear(
						productId, busId, start, end);
				HashMap<String, Object> pa = orderInfoDao.getPayAllByYear(
						productId, busId, start, end);
				HashMap<String, Object> pm = orderInfoDao.getAllPayMoneyByYear(
						productId, busId, start, end);
				HashMap<String, Object> pn = orderInfoDao
						.getAllPayPhoneNumbersByYear(productId, busId, start,
								end);
				HashMap<String, Object> pp = orderInfoDao
						.getPaySuccessPhoneNumbersByYear(productId, busId,
								start, end);
				HashMap<String, Object> p = orderInfoDao
						.getPayFailedPhoneNumbersByYear(productId, busId,
								start, end);
				for (Integer i = first; i <= last; i++) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("productDescribe", productDescribe);
					String ob = i + "";
					if (ps.get(ob) == null) {
						map1.put("year", ob);
						map1.put("paySuccess", 0);
					} else {
						map1.put("year", ob);
						map1.put("paySuccess", ps.get(ob));
					}
					if (pf.get(ob) == null) {
						map1.put("year", ob);
						map1.put("payFailed", 0);
					} else {
						map1.put("year", ob);
						map1.put("payFailed", pf.get(ob));
					}
					if (pa.get(ob) == null) {
						map1.put("year", ob);
						map1.put("allPay", 0);
					} else {
						map1.put("year", ob);
						map1.put("allPay", pa.get(ob));
					}
					if (pm.get(ob) == null) {
						map1.put("year", ob);
						map1.put("allPayMoney", 0);
					} else {
						map1.put("year", ob);
						map1.put("allPayMoney", pm.get(ob));
					}
					if (pn.get(ob) == null) {
						map1.put("year", ob);
						map1.put("allPayPhoneNumbers", 0);
					} else {
						map1.put("year", ob);
						map1.put("allPayPhoneNumbers", pn.get(ob));
					}
					if (pp.get(ob) == null) {
						map1.put("year", ob);
						map1.put("paySuccessPhoneNumbers", 0);
					} else {
						map1.put("year", ob);
						map1.put("paySuccessPhoneNumbers", pp.get(ob));
					}
					if (p.get(ob) == null) {
						map1.put("year", ob);
						map1.put("payFailedPhoneNumbers", 0);
					} else {
						map1.put("year", ob);
						map1.put("payFailedPhoneNumbers", p.get(ob));
					}
					listMap.add(map1);
				}

			}
			if (("按月").equals(ways)) {
				String start = new SimpleDateFormat("yyyy-MM")
						.format(startDate);
				String end = new SimpleDateFormat("yyyy-MM").format(endDate);
				String[] sta = start.split("-");
				String[] en = end.split("-");
				String staYear = sta[0];
				String enYear = en[0];
				String staMonth = sta[1];
				String enMonth = en[1];
				Integer startMonth = Integer.parseInt(staMonth);
				Integer endMonth = Integer.parseInt(enMonth);
				String productDescribe = productDao
						.getProductDescribe2(productId);
				HashMap<String, Object> ps = orderInfoDao.getPaySuccessByMonth(
						productId, busId, start, end);
				HashMap<String, Object> pf = orderInfoDao.getPayFailedByMonth(
						productId, busId, start, end);
				HashMap<String, Object> pa = orderInfoDao.getPayAllByMonth(
						productId, busId, start, end);
				HashMap<String, Object> pm = orderInfoDao
						.getAllPayMoneyByMonth(productId, busId, start, end);
				HashMap<String, Object> pn = orderInfoDao
						.getAllPayPhoneNumbersByMonth(productId, busId, start,
								end);
				HashMap<String, Object> pp = orderInfoDao
						.getPaySuccessPhoneNumbersByMonth(productId, busId,
								start, end);
				HashMap<String, Object> p = orderInfoDao
						.getPayFailedPhoneNumbersByMonth(productId, busId,
								start, end);
				if (staYear.equals(enYear)) {
					for (Integer i = startMonth; i <= endMonth; i++) {
						HashMap<String, Object> map1 = new HashMap<String, Object>();
						map1.put("productDescribe", productDescribe);
						String ob = "";
						if (i < 10) {
							ob = staYear + "-0" + i;
						} else {
							ob = staYear + "-" + i;
						}
						if (ps.get(ob) == null) {
							map1.put("year", ob);
							map1.put("paySuccess", 0);
						} else {
							map1.put("year", ob);
							map1.put("paySuccess", ps.get(ob));
						}
						if (pf.get(ob) == null) {
							map1.put("year", ob);
							map1.put("payFailed", 0);
						} else {
							map1.put("year", ob);
							map1.put("payFailed", pf.get(ob));
						}
						if (pa.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPay", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPay", pa.get(ob));
						}
						if (pm.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPayMoney", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPayMoney", pm.get(ob));
						}
						if (pn.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPayPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPayPhoneNumbers", pn.get(ob));
						}
						if (pp.get(ob) == null) {
							map1.put("year", ob);
							map1.put("paySuccessPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("paySuccessPhoneNumbers", pp.get(ob));
						}
						if (p.get(ob) == null) {
							map1.put("year", ob);
							map1.put("payFailedPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("payFailedPhoneNumbers", p.get(ob));
						}
						listMap.add(map1);

					}

				} else {
					Integer endMonth2 = endMonth + 12;
					for (Integer i = startMonth; i <= endMonth2; i++) {
						HashMap<String, Object> map1 = new HashMap<String, Object>();
						map1.put("productDescribe", productDescribe);
						String ob = "";
						if (i > 12) {
							int j = i - 12;
							if (j < 10) {
								ob = enYear + "-0" + j;
							} else {
								ob = enYear + "-" + j;
							}
						} else {
							if (i < 10) {
								ob = staYear + "-0" + i;
							} else {
								ob = staYear + "-" + i;
							}
						}

						if (ps.get(ob) == null) {
							map1.put("year", ob);
							map1.put("paySuccess", 0);
						} else {
							map1.put("year", ob);
							map1.put("paySuccess", ps.get(ob));
						}
						if (pf.get(ob) == null) {
							map1.put("year", ob);
							map1.put("payFailed", 0);
						} else {
							map1.put("year", ob);
							map1.put("payFailed", pf.get(ob));
						}
						if (pa.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPay", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPay", pa.get(ob));
						}
						if (pm.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPayMoney", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPayMoney", pm.get(ob));
						}
						if (pn.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPayPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPayPhoneNumbers", pn.get(ob));
						}
						if (pp.get(ob) == null) {
							map1.put("year", ob);
							map1.put("paySuccessPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("paySuccessPhoneNumbers", pp.get(ob));
						}
						if (p.get(ob) == null) {
							map1.put("year", ob);
							map1.put("payFailedPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("payFailedPhoneNumbers", p.get(ob));
						}
						listMap.add(map1);

					}

				}

			}
			if (("按周").equals(ways)) {
				String start = new SimpleDateFormat("yyyy-MM-dd")
						.format(startDate);
				String end = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
				HashMap<String, Object> ps = orderInfoDao.getPaySuccessByWeek(
						productId, busId, start, end);
				HashMap<String, Object> pf = orderInfoDao.getPayFailedByWeek(
						productId, busId, start, end);
				HashMap<String, Object> pa = orderInfoDao.getPayAllByWeek(
						productId, busId, start, end);
				HashMap<String, Object> pm = orderInfoDao.getAllPayMoneyByWeek(
						productId, busId, start, end);
				HashMap<String, Object> pn = orderInfoDao
						.getAllPayPhoneNumbersByWeek(productId, busId, start,
								end);
				HashMap<String, Object> pp = orderInfoDao
						.getPaySuccessPhoneNumbersByweek(productId, busId,
								start, end);
				HashMap<String, Object> p = orderInfoDao
						.getPayFailedPhoneNumbersByWeek(productId, busId,
								start, end);
				Calendar c = Calendar.getInstance();
				c.setTime(startDate);
				Integer startWeek = c.get(Calendar.WEEK_OF_YEAR);
				c.setTime(endDate);
				Integer endWeek = c.get(Calendar.WEEK_OF_YEAR);
				for (int i = startWeek; i <= endWeek; i++) {
					String day1 = "第" + i + "周";
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					String ob = i + "";
					if (ps.get(ob) == null) {
						map1.put("year", day1);
						map1.put("paySuccess", 0);
					} else {
						map1.put("year", day1);
						map1.put("paySuccess", ps.get(ob));
					}
					if (pf.get(ob) == null) {
						map1.put("year", day1);
						map1.put("payFailed", 0);
					} else {
						map1.put("year", day1);
						map1.put("payFailed", pf.get(ob));
					}
					if (pa.get(ob) == null) {
						map1.put("year", day1);
						map1.put("allPay", 0);
					} else {
						map1.put("year", day1);
						map1.put("allPay", pa.get(ob));
					}
					if (pm.get(ob) == null) {
						map1.put("year", day1);
						map1.put("allPayMoney", 0);
					} else {
						map1.put("year", day1);
						map1.put("allPayMoney", pm.get(ob));
					}
					if (pn.get(ob) == null) {
						map1.put("year", day1);
						map1.put("allPayPhoneNumbers", 0);
					} else {
						map1.put("year", day1);
						map1.put("allPayPhoneNumbers", pn.get(ob));
					}
					if (pp.get(ob) == null) {
						map1.put("year", day1);
						map1.put("paySuccessPhoneNumbers", 0);
					} else {
						map1.put("year", day1);
						map1.put("paySuccessPhoneNumbers", pp.get(ob));
					}
					if (p.get(ob) == null) {
						map1.put("year", day1);
						map1.put("payFailedPhoneNumbers", 0);
					} else {
						map1.put("year", day1);
						map1.put("payFailedPhoneNumbers", p.get(ob));
					}
					listMap.add(map1);

				}
			}
			if (("按日").equals(ways)) {
				String start = new SimpleDateFormat("yyyy-MM-dd")
						.format(startDate);
				String end = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
				String[] sta = start.split("-");
				String[] en = end.split("-");
				String staYear = sta[0];
				String enYear = en[0];
				String staMonth = sta[1];
				String enMonth = en[1];
				String staDay = sta[2];
				String enDay = en[2];
				Integer startDay = Integer.parseInt(staDay);
				Integer endDay = Integer.parseInt(enDay);
				Integer startYear = Integer.parseInt(staYear);
				String productDescribe = productDao
						.getProductDescribe2(productId);
				HashMap<String, Object> ps = orderInfoDao.getPaySuccessByDay(
						productId, busId, start, end);
				HashMap<String, Object> pf = orderInfoDao.getPayFailedByDay(
						productId, busId, start, end);
				HashMap<String, Object> pa = orderInfoDao.getPayAllByDay(
						productId, busId, start, end);
				HashMap<String, Object> pm = orderInfoDao.getAllPayMoneyByDay(
						productId, busId, start, end);
				HashMap<String, Object> pn = orderInfoDao
						.getAllPayPhoneNumbersByDay(productId, busId, start,
								end);
				HashMap<String, Object> pp = orderInfoDao
						.getPaySuccessPhoneNumbersByDay(productId, busId,
								start, end);
				HashMap<String, Object> p = orderInfoDao
						.getPayFailedPhoneNumbersByDay(productId, busId, start,
								end);
				if (staMonth.equals(enMonth)) {
					for (Integer i = startDay; i <= endDay; i++) {
						HashMap<String, Object> map1 = new HashMap<String, Object>();
						map1.put("productDescribe", productDescribe);
						String ob = "";
						if (i < 10) {
							ob = staYear + "-" + staMonth + "-0" + i;
						} else {
							ob = staYear + "-" + staMonth + "-" + i;
						}
						if (ps.get(ob) == null) {
							map1.put("year", ob);
							map1.put("paySuccess", 0);
						} else {
							map1.put("year", ob);
							map1.put("paySuccess", ps.get(ob));
						}
						if (pf.get(ob) == null) {
							map1.put("year", ob);
							map1.put("payFailed", 0);
						} else {
							map1.put("year", ob);
							map1.put("payFailed", pf.get(ob));
						}
						if (pa.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPay", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPay", pa.get(ob));
						}
						if (pm.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPayMoney", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPayMoney", pm.get(ob));
						}
						if (pn.get(ob) == null) {
							map1.put("year", ob);
							map1.put("allPayPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("allPayPhoneNumbers", pn.get(ob));
						}
						if (pp.get(ob) == null) {
							map1.put("year", ob);
							map1.put("paySuccessPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("paySuccessPhoneNumbers", pp.get(ob));
						}
						if (p.get(ob) == null) {
							map1.put("year", ob);
							map1.put("payFailedPhoneNumbers", 0);
						} else {
							map1.put("year", ob);
							map1.put("payFailedPhoneNumbers", p.get(ob));
						}
						listMap.add(map1);

					}

				} else {
					Integer endDay2 = null;
					if (staMonth.equals("01") || staMonth.equals("03")
							|| staMonth.equals("05") || staMonth.equals("07")
							|| staMonth.equals("08") || staMonth.equals("10")) {
						endDay2 = endDay + 31;
						for (Integer i = startDay; i <= endDay2; i++) {
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("productDescribe", productDescribe);
							String ob = "";
							if (i > 31) {
								int j = i - 31;
								if (j < 10) {
									ob = staYear + "-" + enMonth + "-0" + j;
								} else {
									ob = staYear + "-" + enMonth + "-" + j;
								}
							} else {
								if (i < 10) {
									ob = staYear + "-" + staMonth + "-0" + i;
								} else {
									ob = staYear + "-" + staMonth + "-" + i;
								}
							}

							if (ps.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccess", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccess", ps.get(ob));
							}
							if (pf.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailed", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailed", pf.get(ob));
							}
							if (pa.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPay", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPay", pa.get(ob));
							}
							if (pm.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayMoney", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayMoney", pm.get(ob));
							}
							if (pn.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", pn.get(ob));
							}
							if (pp.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", pp.get(ob));
							}
							if (p.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", p.get(ob));
							}
							listMap.add(map1);

						}

					}
					if (staMonth.equals("04") || staMonth.equals("06")
							|| staMonth.equals("09") || staMonth.equals("11")) {
						endDay2 = endDay + 30;
						for (Integer i = startDay; i <= endDay2; i++) {
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("productDescribe", productDescribe);
							String ob = "";
							if (i > 30) {
								int j = i - 30;
								if (j < 10) {
									ob = staYear + "-" + enMonth + "-0" + j;
								} else {
									ob = staYear + "-" + enMonth + "-" + j;
								}
							} else {
								if (i < 10) {
									ob = staYear + "-" + staMonth + "-0" + i;
								} else {
									ob = staYear + "-" + staMonth + "-" + i;
								}
							}

							if (ps.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccess", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccess", ps.get(ob));
							}
							if (pf.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailed", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailed", pf.get(ob));
							}
							if (pa.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPay", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPay", pa.get(ob));
							}
							if (pm.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayMoney", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayMoney", pm.get(ob));
							}
							if (pn.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", pn.get(ob));
							}
							if (pp.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", pp.get(ob));
							}
							if (p.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", p.get(ob));
							}
							listMap.add(map1);

						}

					}
					if (startYear % 4 == 0 && startYear % 100 != 0
							&& staMonth.equals("02")) {
						endDay2 = endDay + 29;
						for (Integer i = startDay; i <= endDay2; i++) {
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("productDescribe", productDescribe);
							String ob = "";
							if (i > 29) {
								int j = i - 29;
								if (j < 10) {
									ob = staYear + "-" + enMonth + "-0" + j;
								} else {
									ob = staYear + "-" + enMonth + "-" + j;
								}
							} else {
								if (i < 10) {
									ob = staYear + "-" + staMonth + "-0" + i;
								} else {
									ob = staYear + "-" + staMonth + "-" + i;
								}
							}

							if (ps.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccess", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccess", ps.get(ob));
							}
							if (pf.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailed", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailed", pf.get(ob));
							}
							if (pa.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPay", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPay", pa.get(ob));
							}
							if (pm.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayMoney", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayMoney", pm.get(ob));
							}
							if (pn.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", pn.get(ob));
							}
							if (pp.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", pp.get(ob));
							}
							if (p.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", p.get(ob));
							}
							listMap.add(map1);

						}
					}
					if (startYear % 4 == 0 && startYear % 100 != 0
							&& staMonth.equals("02")) {
						endDay2 = endDay + 28;
						for (Integer i = startDay; i <= endDay2; i++) {
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("productDescribe", productDescribe);
							String ob = "";
							if (i > 28) {
								int j = i - 28;
								if (j < 10) {
									ob = staYear + "-" + enMonth + "-0" + j;
								} else {
									ob = staYear + "-" + enMonth + "-" + j;
								}
							} else {
								if (i < 10) {
									ob = staYear + "-" + staMonth + "-0" + i;
								} else {
									ob = staYear + "-" + staMonth + "-" + i;
								}
							}

							if (ps.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccess", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccess", ps.get(ob));
							}
							if (pf.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailed", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailed", pf.get(ob));
							}
							if (pa.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPay", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPay", pa.get(ob));
							}
							if (pm.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayMoney", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayMoney", pm.get(ob));
							}
							if (pn.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", pn.get(ob));
							}
							if (pp.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", pp.get(ob));
							}
							if (p.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", p.get(ob));
							}
							listMap.add(map1);

						}
					}
					if (staMonth.equals("12")) {
						endDay2 = endDay + 31;
						for (Integer i = startDay; i <= endDay2; i++) {
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("productDescribe", productDescribe);
							String ob = "";
							if (i > 31) {
								int j = i - 28;
								if (j < 10) {
									ob = enYear + "-" + enMonth + "-0" + j;
								} else {
									ob = enYear + "-" + enMonth + "-" + j;
								}
							} else {
								if (i < 10) {
									ob = staYear + "-" + staMonth + "-0" + i;
								} else {
									ob = staYear + "-" + staMonth + "-" + i;
								}
							}

							if (ps.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccess", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccess", ps.get(ob));
							}
							if (pf.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailed", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailed", pf.get(ob));
							}
							if (pa.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPay", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPay", pa.get(ob));
							}
							if (pm.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayMoney", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayMoney", pm.get(ob));
							}
							if (pn.get(ob) == null) {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("allPayPhoneNumbers", pn.get(ob));
							}
							if (pp.get(ob) == null) {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("paySuccessPhoneNumbers", pp.get(ob));
							}
							if (p.get(ob) == null) {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", 0);
							} else {
								map1.put("year", ob);
								map1.put("payFailedPhoneNumbers", p.get(ob));
							}
							listMap.add(map1);

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getStatisticsAll2(Integer busId,
			String startTime, String endTime) {
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			ArrayList<String> pd = productDao.getProductDescribeByBusId(busId);
			Integer number = productDao.getNumber(busId);
			if (busId != 103) {
				HashMap<String, Object> ps = orderInfoDao.getPaySuccess(busId,
						startDate, endDate);
				HashMap<String, Object> pf = orderInfoDao.getPayFailed(busId,
						startDate, endDate);
				HashMap<String, Object> pa = orderInfoDao.getAllPay(busId,
						startDate, endDate);
				HashMap<String, Object> pm = orderInfoDao.getAllPayMoney(busId,
						startDate, endDate);
				HashMap<String, Object> pp = orderInfoDao
						.getAllPayPhoneNumbers(busId, startDate, endDate);
				HashMap<String, Object> psn = orderInfoDao
						.getPaySuccessPhoneNumbers(busId, startDate, endDate);
				HashMap<String, Object> pfn = orderInfoDao
						.getPayFailedPhoneNumbers(busId, startDate, endDate);
				double all = orderInfoDao.getAllPayMoneyByBusId(busId,
						startDate, endDate);
				for (String productDescribe : pd) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					if (ps.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccess", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccess", ps.get(productDescribe));
					}
					if (pf.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailed", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailed", pf.get(productDescribe));
					}
					if (pa.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("allPay", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("allPay", pa.get(productDescribe));
					}
					if (pm.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayMoney", 0.0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayMoney", pm.get(productDescribe));
					}
					if (pp.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayPhoneNumbers", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayPhoneNumbers", pp.get(productDescribe));
					}
					if (psn.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccessPhoneNumbers", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccessPhoneNumbers",
								psn.get(productDescribe));
					}
					if (pfn.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailedPhoneNumbers", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailedPhoneNumbers",
								pfn.get(productDescribe));
					}
					map1.put("all", all);
					map1.put("number", number);
					listMap.add(map1);
				}
			} else {
				HashMap<String, Object> ps = qqPermonthRecodeDao.getPaySuccess(
						startDate, endDate);
				HashMap<String, Object> pf = qqPermonthRecodeDao.getPayFailed(
						startDate, endDate);
				HashMap<String, Object> pa = qqPermonthRecodeDao.getAllPay(
						startDate, endDate);
				HashMap<String, Object> pm = qqPermonthRecodeDao
						.getAllPayMoney(startDate, endDate);
				HashMap<String, Object> pp = qqPermonthRecodeDao
						.getAllPayPhoneNumbers(startDate, endDate);
				HashMap<String, Object> psn = qqPermonthRecodeDao
						.getPaySuccessPhoneNumbers(startDate, endDate);
				HashMap<String, Object> pfn = qqPermonthRecodeDao
						.getPayFailedPhoneNumbers(startDate, endDate);
				double all = qqPermonthRecodeDao.getAllPayMoneyByBusId(
						startDate, endDate);
				for (String productDescribe : pd) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					if (ps.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccess", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccess", ps.get(productDescribe));
					}
					if (pf.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailed", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailed", pf.get(productDescribe));
					}
					if (pa.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("allPay", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("allPay", pa.get(productDescribe));
					}
					if (pm.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayMoney", 0.0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayMoney", pm.get(productDescribe));
					}
					if (pp.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayPhoneNumbers", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("allPayPhoneNumbers", pp.get(productDescribe));
					}
					if (psn.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccessPhoneNumbers", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("paySuccessPhoneNumbers",
								psn.get(productDescribe));
					}
					if (pfn.get(productDescribe) == null) {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailedPhoneNumbers", 0);
					} else {
						map1.put("productDescribe", productDescribe);
						map1.put("payFailedPhoneNumbers",
								pfn.get(productDescribe));
					}
					map1.put("all", all);
					map1.put("number", number);
					listMap.add(map1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getStatisticsAllByWays(
			Integer busId, String startTime, String endTime) {
		Date startDate = null;
		Date endDate = null;
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();

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
			ArrayList<String> bt = businessDao.getBtype(busId);
			HashMap<String, Object> bf = businessDao.getBtypeFeemethod(busId);
			Integer AllNumbers1 = 0;
			Integer AllNumbers2 = 0;
			Integer AllFailNumbers1 = 0;
			Integer AllFailNumbers2 = 0;
			Integer AllSuccessNumbers1 = 0;
			Integer AllSuccessNumbers2 = 0;
			double AllPayMoney1 = 0.0;
			double AllPayMoney2 = 0.0;
			Integer AllPay1 = 0;
			Integer AllPay2 = 0;
			Integer AllPayFail1 = 0;
			Integer AllPayFail2 = 0;
			Integer AllPaySuccess1 = 0;
			Integer AllPaySuccess2 = 0;
			HashMap<String, Integer> qqPermonthRecodePhoneNumbers = qqPermonthRecodeDao
					.getPayPhoneNumber(startDate, endDate);
			AllNumbers1 = qqPermonthRecodePhoneNumbers.get("All") == null ? 0
					: qqPermonthRecodePhoneNumbers.get("All");
			AllFailNumbers1 = qqPermonthRecodePhoneNumbers.get("扣费失败") == null ? 0
					: qqPermonthRecodePhoneNumbers.get("扣费失败");
			AllSuccessNumbers1 = qqPermonthRecodePhoneNumbers.get("扣费成功") == null ? 0
					: qqPermonthRecodePhoneNumbers.get("扣费成功");
			AllPayMoney1 = qqPermonthRecodeDao.getPayMoneyAll(startDate,
					endDate);
			HashMap<String, Integer> qqPermonthRecodePayNumbers = qqPermonthRecodeDao
					.getAllPayNumbers(startDate, endDate);
			AllPay1 = qqPermonthRecodePayNumbers.get("All") == null ? 0
					: qqPermonthRecodePayNumbers.get("All");
			AllPayFail1 = qqPermonthRecodePayNumbers.get("扣费失败") == null ? 0
					: qqPermonthRecodePayNumbers.get("扣费失败");
			AllPaySuccess1 = qqPermonthRecodePayNumbers.get("扣费成功") == null ? 0
					: qqPermonthRecodePayNumbers.get("扣费成功");
			HashMap<String, Object> pn = null;
			HashMap<String, Object> pfn = null;
			HashMap<String, Object> psn = null;
			HashMap<String, Object> pm = null;
			HashMap<String, Object> p = null;
			HashMap<String, Object> pf = null;
			HashMap<String, Object> ps = null;
			ps = orderInfoDao.getPaySuccess2(busId, startDate, endDate);
			AllPaySuccess2 = orderInfoDao.getAllPaySuccess2(startDate, endDate);
			pf = orderInfoDao.getPayFailed2(busId, startDate, endDate);
			AllPayFail2 = orderInfoDao.getPayFailed2(startDate, endDate);
			p = orderInfoDao.getAllPay2(busId, startDate, endDate);
			AllPay2 = orderInfoDao.getAllPay2(startDate, endDate);
			pm = orderInfoDao.getAllPayMoney2(busId, startDate, endDate);
			AllPayMoney2 = orderInfoDao.getAllPayMoney2(startDate, endDate);
			psn = orderInfoDao.getPaySuccessPhoneNumbers2(busId, startDate,
					endDate);
			AllSuccessNumbers2 = orderInfoDao.getAllPaySuccessPhoneNumbers2(
					startDate, endDate);
			pfn = orderInfoDao.getPayFailedPhoneNumbers2(busId, startDate,
					endDate);
			AllFailNumbers2 = orderInfoDao.getAllPayFailedPhoneNumbers2(
					startDate, endDate);
			pn = orderInfoDao.getAllPayPhoneNumbers2(busId, startDate, endDate);
			AllNumbers2 = orderInfoDao.getAllPhoneNumbers2(startDate, endDate);
			Integer AllNumbers = AllNumbers1 + AllNumbers2;
			Integer AllFailNumbers = AllFailNumbers1 + AllFailNumbers2;
			Integer AllSuccessNumbers = AllSuccessNumbers1 + AllSuccessNumbers2;
			double AllPayMoney = add(AllPayMoney1, AllPayMoney2);
			Integer AllPay = AllPay1 + AllPay2;
			Integer AllPayFail = AllPayFail1 + AllPayFail2;
			Integer AllPaySuccess = AllPaySuccess1 + AllPaySuccess2;
			for (String BusId : bt) {
				HashMap<String, Object> map = new LinkedHashMap<String, Object>();
				if (("不定时").equals(bf.get(BusId))) {
					map.put("BusId", BusId);
					map.put("allPayPhoneNumbers", AllNumbers1);
					map.put("payFailedPhoneNumbers", AllFailNumbers1);
					map.put("paySuccessPhoneNumbers", AllSuccessNumbers1);
					map.put("allPayMoney", AllPayMoney1);
					map.put("allPay", AllPay1);
					map.put("payFailed", AllPayFail1);
					map.put("paySuccess", AllPaySuccess1);
				} else {
					if (ps.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("paySuccess", 0);
					} else {
						map.put("BusId", BusId);
						map.put("paySuccess", ps.get(BusId));
					}
					if (pf.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("payFailed", 0);
					} else {
						map.put("BusId", BusId);
						map.put("payFailed", pf.get(BusId));
					}
					if (p.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPay", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPay", p.get(BusId));
					}
					if (pm.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPayMoney", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPayMoney", pm.get(BusId));
					}
					if (psn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("paySuccessPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("paySuccessPhoneNumbers", psn.get(BusId));
					}
					if (pfn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("payFailedPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("payFailedPhoneNumbers", pfn.get(BusId));
					}
					if (pn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", pn.get(BusId));
					}
				}
				map.put("AllPaySuccess", AllPaySuccess);
				map.put("AllPayFail", AllPayFail);
				map.put("AllPay", AllPay);
				map.put("AllPayMoney", AllPayMoney);
				map.put("AllSuccessNumbers", AllSuccessNumbers);
				map.put("AllFailNumbers", AllFailNumbers);
				map.put("AllNumbers", AllNumbers);
				listMap.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getStatisticsInfoByPlace(
			String startTime, String endTime, String place) {
		Date startDate = null;
		Date endDate = null;
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();

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
			ArrayList<String> bt = businessDao.getBtype();
			HashMap<String, Object> btf = businessDao
					.getBtypeFeemethodByplace();
			Integer AllPaySuccess1 = 0;
			Integer AllPaySuccess2 = 0;
			Integer AllPayFail1 = 0;
			Integer AllPayFail2 = 0;
			Integer AllPay1 = 0;
			Integer AllPay2 = 0;
			double AllPayMoney1 = 0.0;
			double AllPayMoney2 = 0.0;
			Integer AllSuccessNumbers1 = 0;
			Integer AllSuccessNumbers2 = 0;
			Integer AllFailNumbers1 = 0;
			Integer AllFailNumbers2 = 0;
			Integer AllNumbers1 = 0;
			Integer AllNumbers2 = 0;
			HashMap<String, Object> ps = orderInfoDao.getPaySuccessByPlace(
					startDate, endDate, place);
			AllPaySuccess2 = orderInfoDao.getPaySuccessAllByPlace(startDate,
					endDate, place);
			AllPaySuccess1 = qqPermonthRecodeDao.getPaySuccess(startDate,
					endDate, place);
			HashMap<String, Object> pf = orderInfoDao.getPayFailedByPlace(
					startDate, endDate, place);
			AllPayFail2 = orderInfoDao.getPayFailedAllByPlace(startDate,
					endDate, place);
			AllPayFail1 = qqPermonthRecodeDao.getPayFail(startDate, endDate,
					place);
			HashMap<String, Object> pa = orderInfoDao.getAllPayByPlace(
					startDate, endDate, place);
			AllPay2 = orderInfoDao.getPayAllByPlace(startDate, endDate, place);
			AllPay1 = qqPermonthRecodeDao.getAllPay(startDate, endDate, place);
			HashMap<String, Object> pm = orderInfoDao.getAllPayMoneyByPlace(
					startDate, endDate, place);
			AllPayMoney2 = orderInfoDao.getPayMoneyAllByPlace(startDate,
					endDate, place);
			AllPayMoney1 = qqPermonthRecodeDao.getPayMoneyByPalce(startDate,
					endDate, place);
			HashMap<String, Object> psn = orderInfoDao
					.getPaySuccessPhoneNumbersByPlace(startDate, endDate, place);
			AllSuccessNumbers2 = orderInfoDao
					.getPaySuccessPhoneNumbersAllByPlace(startDate, endDate,
							place);
			AllSuccessNumbers1 = qqPermonthRecodeDao.getSuccessNumbers(
					startDate, endDate, place);
			HashMap<String, Object> pfn = orderInfoDao
					.getPayFailedPhoneNumbersByPlace(startDate, endDate, place);
			AllFailNumbers2 = orderInfoDao.getPayFailedPhoneNumbersAllByPlace(
					startDate, endDate, place);
			AllFailNumbers1 = qqPermonthRecodeDao.getFailNumbers(startDate,
					endDate, place);
			HashMap<String, Object> pn = orderInfoDao
					.getAllPayPhoneNumbersByPlace(startDate, endDate, place);
			AllNumbers2 = orderInfoDao.getPhoneNumbersAllByPlace(startDate,
					endDate, place);
			AllNumbers1 = qqPermonthRecodeDao.getAllNumbers(startDate, endDate,
					place);
			Integer AllPaySuccess = AllPaySuccess1 + AllPaySuccess2;
			Integer AllPayFail = AllPayFail1 + AllPayFail2;
			Integer AllPay = AllPay1 + AllPay2;
			double AllPayMoney = add(AllPayMoney1, AllPayMoney2);
			Integer AllSuccessNumbers = AllSuccessNumbers1 + AllSuccessNumbers2;
			Integer AllFailNumbers = AllFailNumbers1 + AllFailNumbers2;
			Integer AllNumbers = AllNumbers1 + AllNumbers2;
			for (String BusId : bt) {
				HashMap<String, Object> map = new LinkedHashMap<String, Object>();
				if (("不定时").equals(btf.get(BusId))) {
					map.put("BusId", BusId);
					map.put("paySuccess", AllPaySuccess1);
					map.put("payFailed", AllPayFail1);
					map.put("allPay", AllPay1);
					map.put("allPayMoney", AllPayMoney1);
					map.put("paySuccessPhoneNumbers", AllSuccessNumbers1);
					map.put("payFailedPhoneNumbers", AllFailNumbers1);
					map.put("allPayPhoneNumbers", AllNumbers1);
				} else {
					if (ps.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("paySuccess", 0);
					} else {
						map.put("BusId", BusId);
						map.put("paySuccess", ps.get(BusId));
					}
					if (pf.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("payFailed", 0);
					} else {
						map.put("BusId", BusId);
						map.put("payFailed", pf.get(BusId));
					}
					if (pa.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPay", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPay", pa.get(BusId));
					}
					if (pm.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPayMoney", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPayMoney", pm.get(BusId));
					}
					if (psn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("paySuccessPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("paySuccessPhoneNumbers", psn.get(BusId));
					}
					if (pfn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("payFailedPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("payFailedPhoneNumbers", pfn.get(BusId));
					}
					if (pn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", pn.get(BusId));
					}
				}
				map.put("AllPaySuccess", AllPaySuccess);
				map.put("AllPayFail", AllPayFail);
				map.put("AllPay", AllPay);
				map.put("AllPayMoney", AllPayMoney);
				map.put("AllSuccessNumbers", AllSuccessNumbers);
				map.put("AllFailNumbers", AllFailNumbers);
				map.put("AllNumbers", AllNumbers);
				listMap.add(map);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public HashMap<String, Object> getStatisticsByRecharge(String placeName,
			String startTime, String endTime, String toTime) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 获得地区名
		ArrayList<String> pc = placeDao.getPName();
		// 获得地区名和地区号
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
			Integer startYear = Integer.parseInt(startTime.split("-")[0]);
			Integer endYear = Integer.parseInt(endTime.split("-")[0]);
			ArrayList<String> bt = businessDao.getBtype();
			HashMap<String, Object> btf = businessDao
					.getBtypeFeemethodByplace();
			if ("".equals(placeName) && "0".equals(toTime)) {
				for (String pName : pc) {
					if (null != placeName && !"".equals(placeName)
							&& !"null".equals(placeName)) {
						double payMoney1 = 0.0;
						double payMoney2 = 0.0;
						// Integer newRegister =
						// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								payMoney1 = qqPermonthRecodeDao
										.getPayMoneyByTime(startDate, endDate,
												placeName);
							} else {
								payMoney2 = orderInfoDao
										.getPayMoneyByPlaceName(placeName,
												startDate, endDate);
							}
						}
						double payMoney = add(payMoney1, payMoney2);
						xAxis = xAxis + pName + ",";
						newshuju = newshuju + payMoney + ",";
					} else {
						double payMoney1 = 0.0;
						double payMoney2 = 0.0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								payMoney1 = qqPermonthRecodeDao
										.getPayMoneyByTime(startDate, endDate,
												pName);
							} else {
								payMoney2 = orderInfoDao
										.getPayMoneyByPlaceName(pName,
												startDate, endDate);
							}
						}

						double payMoney = add(payMoney1, payMoney2);
						xAxis = xAxis + pName + ",";
						newshuju = newshuju + payMoney + ",";

					}

				}
				String xAxis1 = xAxis.substring(0, xAxis.length() - 1);
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if ("".equals(placeName) && "1".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					double newrt1 = 0.0;
					double newrt2 = 0.0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(year.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result
										.get(year.toString()).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(year.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										year.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + year + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if ("".equals(placeName) && "2".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
						double newrt1 = 0.0;
						double newrt2 = 0.0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";

					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && month < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && month >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						double newrt1 = 0;
						double newrt2 = 0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
					}

				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if ("".equals(placeName) && "3".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, "4");
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double sumZhou = 0.0;
					if (i == size) {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}
						}

					} else {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}

						}

						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, 7);
						start = c.getTime();
					}

					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + sumZhou + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if ("".equals(placeName) && "4".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(day.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(day.toString())
										.toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(day.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if ("".equals(placeName) && "5".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				for (int i = 0; i <= 24; i++) {
					String str = null;
					if (i < 10) {
						str = "0" + i;
					} else {
						str = i + "";
					}
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(str) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(str).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(str) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(str)
										.toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + str + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "0".equals(toTime)) {
				// Integer newRegister =
				// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
				double payMoney1 = 0.0;
				double payMoney2 = 0.0;
				for (String BusId : bt) {
					if (("不定时").equals(btf.get(BusId))) {
						payMoney1 = qqPermonthRecodeDao.getPayMoneyByTime(
								startDate, endDate, placeName);
					} else {
						payMoney2 = orderInfoDao.getPayMoneyByPlaceCode(
								placeCode, startDate, endDate);
					}
				}
				double payMoney = add(payMoney1, payMoney2);
				xAxis = placeName + ",";
				newshuju = "[" + payMoney + "]";
				map.put("xAxis", xAxis);
				map.put("newshuju", newshuju);

			} else if (placeName.length() > 1 && "1".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					double newrt1 = 0.0;
					double newrt2 = 0.0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(year.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result
										.get(year.toString()).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(year.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										year.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + year + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "2".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
						double newrt1 = 0.0;
						double newrt2 = 0.0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";

					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && month < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && month >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						double newrt1 = 0;
						double newrt2 = 0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
					}

				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if (placeName.length() > 1 && "3".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, "4");
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double sumZhou = 0.0;
					if (i == size) {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}
						}
					} else {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}

						}
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, 7);
						start = c.getTime();
					}

					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + sumZhou + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "4".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(day.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(day.toString())
										.toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(day.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "5".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				for (int i = 0; i <= 24; i++) {
					String str = null;
					if (i < 10) {
						str = "0" + i;
					} else {
						str = i + "";
					}
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(str) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(str).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(str) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(str)
										.toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + str + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

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
	public HashMap<String, Object> getUserActivity(String placeName,
			String startTime, String endTime, String toTime) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultSuccess = new HashMap<String, Object>();
		HashMap<String, Object> resultFail = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 获得地区名
		ArrayList<String> pc = placeDao.getPName();
		// 获得地区名和地区号
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
			String successOrderNumbers = "";
			String failOrderNumbers = "";
			String AllOrderNumbers = "";
			Integer startYear = Integer.parseInt(startTime.split("-")[0]);
			Integer endYear = Integer.parseInt(endTime.split("-")[0]);
			if ("".equals(placeName) && "0".equals(toTime)) {
				for (String pName : pc) {
					if (null != placeName && !"".equals(placeName)
							&& !"null".equals(placeName)) {
						Integer successData = orderInfoDao
								.getSuccessOrderNumbers(placeName, startDate,
										endDate);
						Integer failData = orderInfoDao.getFailOrderNumbers(
								placeName, startDate, endDate);
						Integer AllData = orderInfoDao.getAllOrderNumbers(
								placeName, startDate, endDate);
						xAxis = xAxis + pName + ",";
						successOrderNumbers = successOrderNumbers + successData
								+ ",";
						failOrderNumbers = failOrderNumbers + failData + ",";
						AllOrderNumbers = AllOrderNumbers + AllData + ",";
					} else {

						Integer successData = orderInfoDao
								.getSuccessOrderNumbers(pName, startDate,
										endDate);
						Integer failData = orderInfoDao.getFailOrderNumbers(
								pName, startDate, endDate);
						Integer AllData = orderInfoDao.getAllOrderNumbers(
								pName, startDate, endDate);
						xAxis = xAxis + pName + ",";
						successOrderNumbers = successOrderNumbers + successData
								+ ",";
						failOrderNumbers = failOrderNumbers + failData + ",";
						AllOrderNumbers = AllOrderNumbers + AllData + ",";
					}

				}
				String xAxis1 = xAxis.substring(0, xAxis.length() - 1);
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if ("".equals(placeName) && "1".equals(toTime)) {
				resultSuccess = orderInfoDao.getSuccessOrderNumberByTime(null,
						startDate, endDate, toTime);
				resultFail = orderInfoDao.getFailOrderNumberByTime(null,
						startDate, endDate, toTime);
				result = orderInfoDao.getAllOrderNumberByTime(null, startDate,
						endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					Integer successData = 0;
					Integer failData = 0;
					Integer AllData = 0;
					if (resultSuccess.get(year.toString()) == null) {
						successData = 0;
					} else {
						String successDataString = resultSuccess.get(
								year.toString()).toString();
						successData = Integer.parseInt(successDataString);
					}
					if (resultFail.get(year.toString()) == null) {
						failData = 0;
					} else {
						String failDataString = resultFail.get(year.toString())
								.toString();
						failData = Integer.parseInt(failDataString);
					}
					if (result.get(year.toString()) == null) {
						AllData = 0;
					} else {
						String AllDataString = result.get(year.toString())
								.toString();
						AllData = Integer.parseInt(AllDataString);
					}
					xAxis = xAxis + year + ",";
					successOrderNumbers = successOrderNumbers + successData
							+ ",";
					failOrderNumbers = failOrderNumbers + failData + ",";
					AllOrderNumbers = AllOrderNumbers + AllData + ",";
				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if ("".equals(placeName) && "2".equals(toTime)) {
				resultSuccess = orderInfoDao.getSuccessOrderNumberByTime(null,
						startDate, endDate, toTime);
				resultFail = orderInfoDao.getFailOrderNumberByTime(null,
						startDate, endDate, toTime);
				result = orderInfoDao.getAllOrderNumberByTime(null, startDate,
						endDate, toTime);
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
						Integer successData = 0;
						Integer failData = 0;
						Integer AllData = 0;
						if (resultSuccess.get(month1.toString()) == null) {
							successData = 0;
						} else {
							String successDataString = resultSuccess.get(
									month1.toString()).toString();
							successData = Integer.parseInt(successDataString);
						}
						if (resultFail.get(month1.toString()) == null) {
							failData = 0;
						} else {
							String failDataString = resultFail.get(
									month1.toString()).toString();
							failData = Integer.parseInt(failDataString);
						}
						if (result.get(month1.toString()) == null) {
							AllData = 0;
						} else {
							String AllDataString = result
									.get(month1.toString()).toString();
							AllData = Integer.parseInt(AllDataString);
						}
						xAxis = xAxis + month1 + ",";
						successOrderNumbers = successOrderNumbers + successData
								+ ",";
						failOrderNumbers = failOrderNumbers + failData + ",";
						AllOrderNumbers = AllOrderNumbers + AllData + ",";

					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && month < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && month >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						Integer successData = 0;
						Integer failData = 0;
						Integer AllData = 0;
						if (resultSuccess.get(month1.toString()) == null) {
							successData = 0;
						} else {
							String successDataString = resultSuccess.get(
									month1.toString()).toString();
							successData = Integer.parseInt(successDataString);
						}
						if (resultFail.get(month1.toString()) == null) {
							failData = 0;
						} else {
							String failDataString = resultFail.get(
									month1.toString()).toString();
							failData = Integer.parseInt(failDataString);
						}
						if (result.get(month1.toString()) == null) {
							AllData = 0;
						} else {
							String AllDataString = result
									.get(month1.toString()).toString();
							AllData = Integer.parseInt(AllDataString);
						}
						xAxis = xAxis + month1 + ",";
						successOrderNumbers = successOrderNumbers + successData
								+ ",";
						failOrderNumbers = failOrderNumbers + failData + ",";
						AllOrderNumbers = AllOrderNumbers + AllData + ",";
					}

				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if ("".equals(placeName) && "3".equals(toTime)) {
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
					Integer successData = 0;
					Integer failData = 0;
					Integer AllData = 0;
					Date startD = null;
					Date endD = null;
					if (i == size) {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i - 1) * 7);
						startD = c.getTime();
						int mod = (int) (quto % 7);
						c.add(Calendar.DAY_OF_MONTH, mod);
						endD = c.getTime();
					} else {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i - 1) * 7);
						startD = c.getTime();
						c.add(Calendar.DAY_OF_MONTH, i * 7);
						endD = c.getTime();
					}
					successData = orderInfoDao.getSuccessOrderNumberByTime3(
							startD, endD);
					failData = orderInfoDao.getFailOrderNumberByTime3(startD,
							endD);
					AllData = orderInfoDao.getAllOrderNumberByTime3(startD,
							endD);
					xAxis = xAxis + day1 + ",";
					successOrderNumbers = successOrderNumbers + successData
							+ ",";
					failOrderNumbers = failOrderNumbers + failData + ",";
					AllOrderNumbers = AllOrderNumbers + AllData + ",";
				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if ("".equals(placeName) && "4".equals(toTime)) {
				resultSuccess = orderInfoDao.getSuccessOrderNumberByTime(null,
						startDate, endDate, toTime);
				resultFail = orderInfoDao.getFailOrderNumberByTime(null,
						startDate, endDate, toTime);
				result = orderInfoDao.getAllOrderNumberByTime(null, startDate,
						endDate, toTime);
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
					Integer successData = 0;
					Integer failData = 0;
					Integer AllData = 0;
					if (resultSuccess.get(day.toString()) == null) {
						successData = successData + 0;
					} else {
						String successDataString = resultSuccess.get(
								day.toString()).toString();
						successData = successData
								+ Integer.parseInt(successDataString);
					}
					if (resultFail.get(day.toString()) == null) {
						failData = failData + 0;
					} else {
						String failDataString = resultFail.get(day.toString())
								.toString();
						failData = failData + Integer.parseInt(failDataString);
					}
					if (result.get(day.toString()) == null) {
						AllData = AllData + 0;
					} else {
						String AllDataString = result.get(day.toString())
								.toString();
						AllData = AllData + Integer.parseInt(AllDataString);
					}
					xAxis = xAxis + day1 + ",";
					successOrderNumbers = successOrderNumbers + successData
							+ ",";
					failOrderNumbers = failOrderNumbers + failData + ",";
					AllOrderNumbers = AllOrderNumbers + AllData + ",";
				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if (placeName.length() > 1 && "0".equals(toTime)) {
				// Integer newRegister =
				// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
				Integer successData = orderInfoDao.getSuccessOrderNumbers(
						placeName, startDate, endDate);
				Integer failData = orderInfoDao.getFailOrderNumbers(placeName,
						startDate, endDate);
				Integer AllData = orderInfoDao.getAllOrderNumbers(placeName,
						startDate, endDate);
				// Integer newRegister =
				// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
				xAxis = placeName + ",";
				successOrderNumbers = "[" + successData + "]";
				failOrderNumbers = "[" + failData + "]";
				AllOrderNumbers = "[" + AllData + "]";
				map.put("xAxis", xAxis);
				map.put("successOrderNumbers", successOrderNumbers);
				map.put("failOrderNumbers", failOrderNumbers);
				map.put("AllOrderNumbers", AllOrderNumbers);

			} else if (placeName.length() > 1 && "1".equals(toTime)) {
				resultSuccess = orderInfoDao.getSuccessOrderNumberByTime(
						placeCode, startDate, endDate, toTime);
				resultFail = orderInfoDao.getFailOrderNumberByTime(placeCode,
						startDate, endDate, toTime);
				result = orderInfoDao.getAllOrderNumberByTime(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					Integer successData = 0;
					Integer failData = 0;
					Integer AllData = 0;
					if (resultSuccess.get(year.toString()) == null) {
						successData = 0;
					} else {
						String successDataString = resultSuccess.get(
								year.toString()).toString();
						successData = Integer.parseInt(successDataString);
					}
					if (resultFail.get(year.toString()) == null) {
						failData = 0;
					} else {
						String failDataString = resultFail.get(year.toString())
								.toString();
						failData = Integer.parseInt(failDataString);
					}
					if (result.get(year.toString()) == null) {
						AllData = 0;
					} else {
						String AllDataString = result.get(year.toString())
								.toString();
						AllData = Integer.parseInt(AllDataString);
					}
					xAxis = xAxis + year + ",";
					successOrderNumbers = successOrderNumbers + successData
							+ ",";
					failOrderNumbers = failOrderNumbers + failData + ",";
					AllOrderNumbers = AllOrderNumbers + AllData + ",";
				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if (placeName.length() > 1 && "2".equals(toTime)) {
				resultSuccess = orderInfoDao.getSuccessOrderNumberByTime(
						placeCode, startDate, endDate, toTime);
				resultFail = orderInfoDao.getFailOrderNumberByTime(placeCode,
						startDate, endDate, toTime);
				result = orderInfoDao.getAllOrderNumberByTime(placeCode,
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
						Integer successData = 0;
						Integer failData = 0;
						Integer AllData = 0;
						if (resultSuccess.get(month1.toString()) == null) {
							successData = 0;
						} else {
							String successDataString = resultSuccess.get(
									month1.toString()).toString();
							successData = Integer.parseInt(successDataString);
						}
						if (resultFail.get(month1.toString()) == null) {
							failData = 0;
						} else {
							String failDataString = resultFail.get(
									month1.toString()).toString();
							failData = Integer.parseInt(failDataString);
						}
						if (result.get(month1.toString()) == null) {
							AllData = 0;
						} else {
							String AllDataString = result
									.get(month1.toString()).toString();
							AllData = Integer.parseInt(AllDataString);
						}
						xAxis = xAxis + month1 + ",";
						successOrderNumbers = successOrderNumbers + successData
								+ ",";
						failOrderNumbers = failOrderNumbers + failData + ",";
						AllOrderNumbers = AllOrderNumbers + AllData + ",";
					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && month < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && month >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						Integer successData = 0;
						Integer failData = 0;
						Integer AllData = 0;
						if (resultSuccess.get(month1.toString()) == null) {
							successData = 0;
						} else {
							String successDataString = resultSuccess.get(
									month1.toString()).toString();
							successData = Integer.parseInt(successDataString);
						}
						if (resultFail.get(month1.toString()) == null) {
							failData = 0;
						} else {
							String failDataString = resultFail.get(
									month1.toString()).toString();
							failData = Integer.parseInt(failDataString);
						}
						if (result.get(month1.toString()) == null) {
							AllData = 0;
						} else {
							String AllDataString = result
									.get(month1.toString()).toString();
							AllData = Integer.parseInt(AllDataString);
						}
						xAxis = xAxis + month1 + ",";
						successOrderNumbers = successOrderNumbers + successData
								+ ",";
						failOrderNumbers = failOrderNumbers + failData + ",";
						AllOrderNumbers = AllOrderNumbers + AllData + ",";
					}

				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if (placeName.length() > 1 && "3".equals(toTime)) {
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
					Integer successData = 0;
					Integer failData = 0;
					Integer AllData = 0;
					Date startD = null;
					Date endD = null;
					if (i == size) {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i - 1) * 7);
						startD = c.getTime();
						int mod = (int) (quto % 7);
						c.add(Calendar.DAY_OF_MONTH, mod);
						endD = c.getTime();
					} else {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i - 1) * 7);
						startD = c.getTime();
						c.add(Calendar.DAY_OF_MONTH, i * 7);
						endD = c.getTime();
					}
					successData = orderInfoDao.getSuccessOrderNumberByTime2(
							placeCode, startD, endD);
					failData = orderInfoDao.getFailOrderNumberByTime2(
							placeCode, startD, endD);
					AllData = orderInfoDao.getAllOrderNumberByTime2(placeCode,
							startD, endD);
					xAxis = xAxis + day1 + ",";
					successOrderNumbers = successOrderNumbers + successData
							+ ",";
					failOrderNumbers = failOrderNumbers + failData + ",";
					AllOrderNumbers = AllOrderNumbers + AllData + ",";
				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
			} else if (placeName.length() > 1 && "4".equals(toTime)) {
				resultSuccess = orderInfoDao.getSuccessOrderNumberByTime(
						placeCode, startDate, endDate, toTime);
				resultFail = orderInfoDao.getFailOrderNumberByTime(placeCode,
						startDate, endDate, toTime);
				result = orderInfoDao.getAllOrderNumberByTime(placeCode,
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
					Integer successData = 0;
					Integer failData = 0;
					Integer AllData = 0;
					if (resultSuccess.get(day.toString()) == null) {
						successData = successData + 0;
					} else {
						String successDataString = resultSuccess.get(
								day.toString()).toString();
						successData = successData
								+ Integer.parseInt(successDataString);
					}
					if (resultFail.get(day.toString()) == null) {
						failData = failData + 0;
					} else {
						String failDataString = resultFail.get(day.toString())
								.toString();
						failData = failData + Integer.parseInt(failDataString);
					}
					if (result.get(day.toString()) == null) {
						AllData = AllData + 0;
					} else {
						String AllDataString = result.get(day.toString())
								.toString();
						AllData = AllData + Integer.parseInt(AllDataString);
					}
					xAxis = xAxis + day1 + ",";
					successOrderNumbers = successOrderNumbers + successData
							+ ",";
					failOrderNumbers = failOrderNumbers + failData + ",";
					AllOrderNumbers = AllOrderNumbers + AllData + ",";
				}
				String xAxis1 = xAxis;
				String successOrderNumbers1 = "["
						+ successOrderNumbers.substring(0,
								successOrderNumbers.length() - 1) + "]";
				String failOrderNumbers1 = "["
						+ failOrderNumbers.substring(0,
								failOrderNumbers.length() - 1) + "]";
				String AllOrderNumbers1 = "["
						+ AllOrderNumbers.substring(0,
								AllOrderNumbers.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("successOrderNumbers", successOrderNumbers1);
				map.put("failOrderNumbers", failOrderNumbers1);
				map.put("AllOrderNumbers", AllOrderNumbers1);
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
	public ArrayList<HashMap<String, String>> getStatisticsByCode(
			String startTime, String endTime, String returnCode) {
		ArrayList<HashMap<String, String>> listMap = new ArrayList<HashMap<String, String>>();
		Date startDate = null;
		Date endDate = null;
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
			if ("".equals(returnCode)) {
				returnCode = null;
			}
			ArrayList<Object[]> fn = orderInfoDao.getfailNumbers(startDate,
					endDate, returnCode);
			Integer AllfailNumbers = orderInfoDao.getAllfailNumbers(startDate,
					endDate);
			double AllloseMoney = orderInfoDao.getAllloseMoney(startDate,
					endDate);

			for (Object[] obj : fn) {
				HashMap<String, String> map1 = new HashMap<String, String>();
				String failReturnCode = (String) obj[0];
				Integer failNumbers = Integer.parseInt(obj[1].toString());
				Double loseMoney = Double.parseDouble(obj[2].toString());
				if (failReturnCode == null) {
					failReturnCode = "";
				}
				String codeMeaning = returnCodeDao
						.getMeannByCode(failReturnCode);
				String show = returnCodeDao.getShowByCode(failReturnCode);
				String AllfailNumbers1 = "";
				String failNumbers1 = failNumbers + "";
				String loseMoney1 = loseMoney + "";
				AllfailNumbers1 = AllfailNumbers + "";
				String AllloseMoney1 = AllloseMoney + "";
				map1.put("returnCode", failReturnCode);
				map1.put("failNumbers", failNumbers1);
				map1.put("loseMoney", loseMoney1);
				map1.put("codeMeaning", codeMeaning);
				map1.put("show", show);
				map1.put("AllfailNumbers", AllfailNumbers1);
				map1.put("AllloseMoney", AllloseMoney1);
				listMap.add(map1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public HashMap<String, Object> getOrderInfoByPhoneNumber(Integer pageSize,
			Integer pageIndex, String phoneNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = orderInfoDao.getOrderInfoListSize2(phoneNumber);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<OrderInfo> am = orderInfoDao.getOrderInfo2(phoneNumber,
				start, pageSize);
		ArrayList<OrderInfoVo> aov = new ArrayList<OrderInfoVo>();
		HashMap<String, Object> productMap = productDao.getProductMap();
		for (int i = 0; am != null && i < am.size(); i++) {
			OrderInfoVo order = new OrderInfoVo(am.get(i), productMap);
			aov.add(order);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", aov);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	@Override
	public ArrayList<HashMap<String, Object>> getStatisticsAllByWays2(
			Integer busId, String startTime, String endTime) {
		Date startDate = null;
		Date endDate = null;
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
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
			if ( busId!=null && 1031==busId) {
				Integer AllNumbers = 0;
				Integer AllFailNumbers = 0;
				Integer AllSuccessNumbers = 0;
				double AllPayMoney = 0.0;
				Integer AllPay = 0;
				Integer AllPayFail = 0;
				Integer AllPaySuccess = 0;
				HashMap<String, Integer> qqPermonthRecodePhoneNumbers = qqPermonthRecodeDao
						.getPayPhoneNumber2(startDate, endDate);
				AllNumbers = qqPermonthRecodePhoneNumbers.get("All") == null ? 0
						: qqPermonthRecodePhoneNumbers.get("All");
				AllFailNumbers = qqPermonthRecodePhoneNumbers.get("扣费失败") == null ? 0
						: qqPermonthRecodePhoneNumbers.get("扣费失败");
				AllSuccessNumbers = qqPermonthRecodePhoneNumbers.get("扣费成功") == null ? 0
						: qqPermonthRecodePhoneNumbers.get("扣费成功");
				AllPayMoney = qqPermonthRecodeDao.getPayMoneyAll2(startDate,
						endDate);
				HashMap<String, Integer> qqPermonthRecodePayNumbers = qqPermonthRecodeDao
						.getAllPayNumbers2(startDate, endDate);
				AllPay = qqPermonthRecodePayNumbers.get("All") == null ? 0
						: qqPermonthRecodePayNumbers.get("All");
				AllPayFail = qqPermonthRecodePayNumbers.get("扣费失败") == null ? 0
						: qqPermonthRecodePayNumbers.get("扣费失败");
				AllPaySuccess = qqPermonthRecodePayNumbers.get("扣费成功") == null ? 0
						: qqPermonthRecodePayNumbers.get("扣费成功");
				HashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("BusId", "Q币包月");
				map.put("allPayPhoneNumbers", AllNumbers);
				map.put("payFailedPhoneNumbers", AllFailNumbers);
				map.put("paySuccessPhoneNumbers", AllSuccessNumbers);
				map.put("allPayMoney", AllPayMoney);
				map.put("allPay", AllPay);
				map.put("payFailed", AllPayFail);
				map.put("paySuccess", AllPaySuccess);
				listMap.add(map);
			} else {
				ArrayList<String> bt = businessDao.getBtype2(busId);
				HashMap<String, Object> bf = businessDao
						.getBtypeFeemethod(busId);
				Integer AllNumbers1 = 0;
				Integer AllNumbers2 = 0;
				Integer AllFailNumbers1 = 0;
				Integer AllFailNumbers2 = 0;
				Integer AllSuccessNumbers1 = 0;
				Integer AllSuccessNumbers2 = 0;
				double AllPayMoney1 = 0.0;
				double AllPayMoney2 = 0.0;
				Integer AllPay1 = 0;
				Integer AllPay2 = 0;
				Integer AllPayFail1 = 0;
				Integer AllPayFail2 = 0;
				Integer AllPaySuccess1 = 0;
				Integer AllPaySuccess2 = 0;
				HashMap<String, Integer> qqPermonthRecodePhoneNumbers = qqPermonthRecodeDao
						.getPayPhoneNumber(startDate, endDate);
				AllNumbers1 = qqPermonthRecodePhoneNumbers.get("All") == null ? 0
						: qqPermonthRecodePhoneNumbers.get("All");
				AllFailNumbers1 = qqPermonthRecodePhoneNumbers.get("扣费失败") == null ? 0
						: qqPermonthRecodePhoneNumbers.get("扣费失败");
				AllSuccessNumbers1 = qqPermonthRecodePhoneNumbers.get("扣费成功") == null ? 0
						: qqPermonthRecodePhoneNumbers.get("扣费成功");
				AllPayMoney1 = qqPermonthRecodeDao.getPayMoneyAll(startDate,
						endDate);
				HashMap<String, Integer> qqPermonthRecodePayNumbers = qqPermonthRecodeDao
						.getAllPayNumbers(startDate, endDate);
				AllPay1 = qqPermonthRecodePayNumbers.get("All") == null ? 0
						: qqPermonthRecodePayNumbers.get("All");
				AllPayFail1 = qqPermonthRecodePayNumbers.get("扣费失败") == null ? 0
						: qqPermonthRecodePayNumbers.get("扣费失败");
				AllPaySuccess1 = qqPermonthRecodePayNumbers.get("扣费成功") == null ? 0
						: qqPermonthRecodePayNumbers.get("扣费成功");
				HashMap<String, Object> pn = null;
				HashMap<String, Object> pfn = null;
				HashMap<String, Object> psn = null;
				HashMap<String, Object> pm = null;
				HashMap<String, Object> p = null;
				HashMap<String, Object> pf = null;
				HashMap<String, Object> ps = null;
				ps = orderInfoDao.getPaySuccess3(busId, startDate, endDate);
				AllPaySuccess2 = orderInfoDao.getAllPaySuccess3(startDate,
						endDate);
				pf = orderInfoDao.getPayFailed3(busId, startDate, endDate);
				AllPayFail2 = orderInfoDao.getPayFailed3(startDate, endDate);
				p = orderInfoDao.getAllPay3(busId, startDate, endDate);
				AllPay2 = orderInfoDao.getAllPay3(startDate, endDate);
				pm = orderInfoDao.getAllPayMoney3(busId, startDate, endDate);
				AllPayMoney2 = orderInfoDao.getAllPayMoney3(startDate, endDate);
				psn = orderInfoDao.getPaySuccessPhoneNumbers3(busId, startDate,
						endDate);
				AllSuccessNumbers2 = orderInfoDao
						.getAllPaySuccessPhoneNumbers3(startDate, endDate);
				pfn = orderInfoDao.getPayFailedPhoneNumbers3(busId, startDate,
						endDate);
				AllFailNumbers2 = orderInfoDao.getAllPayFailedPhoneNumbers2(
						startDate, endDate);
				pn = orderInfoDao.getAllPayPhoneNumbers3(busId, startDate,
						endDate);
				AllNumbers2 = orderInfoDao.getAllPhoneNumbers3(startDate,
						endDate);
				Integer AllNumbers = AllNumbers1 + AllNumbers2;
				Integer AllFailNumbers = AllFailNumbers1 + AllFailNumbers2;
				Integer AllSuccessNumbers = AllSuccessNumbers1
						+ AllSuccessNumbers2;
				double AllPayMoney = add(AllPayMoney1, AllPayMoney2);
				Integer AllPay = AllPay1 + AllPay2;
				Integer AllPayFail = AllPayFail1 + AllPayFail2;
				Integer AllPaySuccess = AllPaySuccess1 + AllPaySuccess2;
				for (String BusId : bt) {
					HashMap<String, Object> map = new LinkedHashMap<String, Object>();
					if (("不定时").equals(bf.get(BusId))) {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", AllNumbers1);
						map.put("payFailedPhoneNumbers", AllFailNumbers1);
						map.put("paySuccessPhoneNumbers", AllSuccessNumbers1);
						map.put("allPayMoney", AllPayMoney1);
						map.put("allPay", AllPay1);
						map.put("payFailed", AllPayFail1);
						map.put("paySuccess", AllPaySuccess1);
					} else {
						if (ps.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("paySuccess", 0);
						} else {
							map.put("BusId", BusId);
							map.put("paySuccess", ps.get(BusId));
						}
						if (pf.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("payFailed", 0);
						} else {
							map.put("BusId", BusId);
							map.put("payFailed", pf.get(BusId));
						}
						if (p.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("allPay", 0);
						} else {
							map.put("BusId", BusId);
							map.put("allPay", p.get(BusId));
						}
						if (pm.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("allPayMoney", 0);
						} else {
							map.put("BusId", BusId);
							map.put("allPayMoney", pm.get(BusId));
						}
						if (psn.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("paySuccessPhoneNumbers", 0);
						} else {
							map.put("BusId", BusId);
							map.put("paySuccessPhoneNumbers", psn.get(BusId));
						}
						if (pfn.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("payFailedPhoneNumbers", 0);
						} else {
							map.put("BusId", BusId);
							map.put("payFailedPhoneNumbers", pfn.get(BusId));
						}
						if (pn.get(BusId) == null) {
							map.put("BusId", BusId);
							map.put("allPayPhoneNumbers", 0);
						} else {
							map.put("BusId", BusId);
							map.put("allPayPhoneNumbers", pn.get(BusId));
						}
					}
					map.put("AllPaySuccess", AllPaySuccess);
					map.put("AllPayFail", AllPayFail);
					map.put("AllPay", AllPay);
					map.put("AllPayMoney", AllPayMoney);
					map.put("AllSuccessNumbers", AllSuccessNumbers);
					map.put("AllFailNumbers", AllFailNumbers);
					map.put("AllNumbers", AllNumbers);
					listMap.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;

	}

	@Override
	public ArrayList<HashMap<String, Object>> getStatisticsInfoByPlace2(
			String startTime, String endTime, String place) {
		Date startDate = null;
		Date endDate = null;
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();

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
			ArrayList<String> bt = businessDao.getBtype2(null);
			HashMap<String, Object> btf = businessDao
					.getBtypeFeemethodByplace();
			Integer AllPaySuccess1 = 0;
			Integer AllPaySuccess2 = 0;
			Integer AllPayFail1 = 0;
			Integer AllPayFail2 = 0;
			Integer AllPay1 = 0;
			Integer AllPay2 = 0;
			double AllPayMoney1 = 0.0;
			double AllPayMoney2 = 0.0;
			Integer AllSuccessNumbers1 = 0;
			Integer AllSuccessNumbers2 = 0;
			Integer AllFailNumbers1 = 0;
			Integer AllFailNumbers2 = 0;
			Integer AllNumbers1 = 0;
			Integer AllNumbers2 = 0;
			HashMap<String, Object> ps = orderInfoDao.getPaySuccessByPlace2(
					startDate, endDate, place);
			AllPaySuccess2 = orderInfoDao.getPaySuccessAllByPlace(startDate,
					endDate, place);
			AllPaySuccess1 = qqPermonthRecodeDao.getPaySuccess(startDate,
					endDate, place);
			HashMap<String, Object> pf = orderInfoDao.getPayFailedByPlace2(
					startDate, endDate, place);
			AllPayFail2 = orderInfoDao.getPayFailedAllByPlace2(startDate,
					endDate, place);
			AllPayFail1 = qqPermonthRecodeDao.getPayFail(startDate, endDate,
					place);
			HashMap<String, Object> pa = orderInfoDao.getAllPayByPlace2(
					startDate, endDate, place);
			AllPay2 = orderInfoDao.getPayAllByPlace2(startDate, endDate, place);
			AllPay1 = qqPermonthRecodeDao.getAllPay(startDate, endDate, place);
			HashMap<String, Object> pm = orderInfoDao.getAllPayMoneyByPlace(
					startDate, endDate, place);
			AllPayMoney2 = orderInfoDao.getPayMoneyAllByPlace2(startDate,
					endDate, place);
			AllPayMoney1 = qqPermonthRecodeDao.getPayMoneyByPalce(startDate,
					endDate, place);
			HashMap<String, Object> psn = orderInfoDao
					.getPaySuccessPhoneNumbersByPlace2(startDate, endDate,
							place);
			AllSuccessNumbers2 = orderInfoDao
					.getPaySuccessPhoneNumbersAllByPlace2(startDate, endDate,
							place);
			AllSuccessNumbers1 = qqPermonthRecodeDao.getSuccessNumbers(
					startDate, endDate, place);
			HashMap<String, Object> pfn = orderInfoDao
					.getPayFailedPhoneNumbersByPlace2(startDate, endDate, place);
			AllFailNumbers2 = orderInfoDao.getPayFailedPhoneNumbersAllByPlace2(
					startDate, endDate, place);
			AllFailNumbers1 = qqPermonthRecodeDao.getFailNumbers(startDate,
					endDate, place);
			HashMap<String, Object> pn = orderInfoDao
					.getAllPayPhoneNumbersByPlace2(startDate, endDate, place);
			AllNumbers2 = orderInfoDao.getPhoneNumbersAllByPlace2(startDate,
					endDate, place);
			AllNumbers1 = qqPermonthRecodeDao.getAllNumbers(startDate, endDate,
					place);
			Integer AllPaySuccess = AllPaySuccess1 + AllPaySuccess2;
			Integer AllPayFail = AllPayFail1 + AllPayFail2;
			Integer AllPay = AllPay1 + AllPay2;
			double AllPayMoney = add(AllPayMoney1, AllPayMoney2);
			Integer AllSuccessNumbers = AllSuccessNumbers1 + AllSuccessNumbers2;
			Integer AllFailNumbers = AllFailNumbers1 + AllFailNumbers2;
			Integer AllNumbers = AllNumbers1 + AllNumbers2;
			for (String BusId : bt) {
				HashMap<String, Object> map = new LinkedHashMap<String, Object>();
				if (("不定时").equals(btf.get(BusId))) {
					map.put("BusId", BusId);
					map.put("paySuccess", AllPaySuccess1);
					map.put("payFailed", AllPayFail1);
					map.put("allPay", AllPay1);
					map.put("allPayMoney", AllPayMoney1);
					map.put("paySuccessPhoneNumbers", AllSuccessNumbers1);
					map.put("payFailedPhoneNumbers", AllFailNumbers1);
					map.put("allPayPhoneNumbers", AllNumbers1);
				} else {
					if (ps.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("paySuccess", 0);
					} else {
						map.put("BusId", BusId);
						map.put("paySuccess", ps.get(BusId));
					}
					if (pf.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("payFailed", 0);
					} else {
						map.put("BusId", BusId);
						map.put("payFailed", pf.get(BusId));
					}
					if (pa.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPay", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPay", pa.get(BusId));
					}
					if (pm.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPayMoney", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPayMoney", pm.get(BusId));
					}
					if (psn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("paySuccessPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("paySuccessPhoneNumbers", psn.get(BusId));
					}
					if (pfn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("payFailedPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("payFailedPhoneNumbers", pfn.get(BusId));
					}
					if (pn.get(BusId) == null) {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", 0);
					} else {
						map.put("BusId", BusId);
						map.put("allPayPhoneNumbers", pn.get(BusId));
					}
				}
				map.put("AllPaySuccess", AllPaySuccess);
				map.put("AllPayFail", AllPayFail);
				map.put("AllPay", AllPay);
				map.put("AllPayMoney", AllPayMoney);
				map.put("AllSuccessNumbers", AllSuccessNumbers);
				map.put("AllFailNumbers", AllFailNumbers);
				map.put("AllNumbers", AllNumbers);
				listMap.add(map);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public HashMap<String, Object> getStatisticsByRecharge2(String placeName,
			String startTime, String endTime, String toTime) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 获得地区名
		ArrayList<String> pc = placeDao.getPName();
		// 获得地区名和地区号
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
			Integer startYear = Integer.parseInt(startTime.split("-")[0]);
			Integer endYear = Integer.parseInt(endTime.split("-")[0]);
			ArrayList<String> bt = businessDao.getBtype();
			HashMap<String, Object> btf = businessDao
					.getBtypeFeemethodByplace();
			if ("".equals(placeName) && "0".equals(toTime)) {
				for (String pName : pc) {
					if (null != placeName && !"".equals(placeName)
							&& !"null".equals(placeName)) {
						double payMoney1 = 0.0;
						double payMoney2 = 0.0;
						// Integer newRegister =
						// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								payMoney1 = qqPermonthRecodeDao
										.getPayMoneyByTime(startDate, endDate,
												placeName);
							} else {
								payMoney2 = orderInfoDao
										.getPayMoneyByPlaceName2(placeName,
												startDate, endDate);
							}
						}
						double payMoney = add(payMoney1, payMoney2);
						xAxis = xAxis + pName + ",";
						newshuju = newshuju + payMoney + ",";
					} else {
						double payMoney1 = 0.0;
						double payMoney2 = 0.0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								payMoney1 = qqPermonthRecodeDao
										.getPayMoneyByTime(startDate, endDate,
												pName);
							} else {
								payMoney2 = orderInfoDao
										.getPayMoneyByPlaceName2(pName,
												startDate, endDate);
							}
						}

						double payMoney = add(payMoney1, payMoney2);
						xAxis = xAxis + pName + ",";
						newshuju = newshuju + payMoney + ",";

					}

				}
				String xAxis1 = xAxis.substring(0, xAxis.length() - 1);
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if ("".equals(placeName) && "1".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					double newrt1 = 0.0;
					double newrt2 = 0.0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(year.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result
										.get(year.toString()).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(year.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										year.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + year + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if ("".equals(placeName) && "2".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
						double newrt1 = 0.0;
						double newrt2 = 0.0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";

					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && month < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && month >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						double newrt1 = 0;
						double newrt2 = 0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
					}

				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if ("".equals(placeName) && "3".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, "4");
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double sumZhou = 0.0;
					if (i == size) {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}
						}

					} else {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}

						}

						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, 7);
						start = c.getTime();
					}

					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + sumZhou + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if ("".equals(placeName) && "4".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(day.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(day.toString())
										.toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(day.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if ("".equals(placeName) && "5".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				for (int i = 0; i <= 24; i++) {
					String str = null;
					if (i < 10) {
						str = "0" + i;
					} else {
						str = i + "";
					}
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(str) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(str).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(str) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(str)
										.toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + str + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "0".equals(toTime)) {
				// Integer newRegister =
				// operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
				double payMoney1 = 0.0;
				double payMoney2 = 0.0;
				for (String BusId : bt) {
					if (("不定时").equals(btf.get(BusId))) {
						payMoney1 = qqPermonthRecodeDao.getPayMoneyByTime(
								startDate, endDate, placeName);
					} else {
						payMoney2 = orderInfoDao.getPayMoneyByPlaceCode2(
								placeCode, startDate, endDate);
					}
				}
				double payMoney = add(payMoney1, payMoney2);
				xAxis = placeName + ",";
				newshuju = "[" + payMoney + "]";
				map.put("xAxis", xAxis);
				map.put("newshuju", newshuju);

			} else if (placeName.length() > 1 && "1".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				Integer y = endYear - startYear;
				for (int i = 0; i <= y; i++) {
					Integer year = Integer.parseInt(startTime.split("-")[0])
							+ i;
					double newrt1 = 0.0;
					double newrt2 = 0.0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(year.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result
										.get(year.toString()).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(year.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										year.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + year + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "2".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
						double newrt1 = 0.0;
						double newrt2 = 0.0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";

					}
				} else {
					Integer months = 12 + endMonth - startMonth;
					for (int i = 0; i <= months; i++) {
						int month = startMonth + i;
						String month1 = "";
						if (month < 12 && month < 10) {
							month1 = startYear + "-0" + month;
						} else if (month <= 12 && month >= 10) {
							month1 = startYear + "-" + month;
						} else if (month > 12) {
							int cha = month - 12;
							if (cha < 10) {
								month1 = endYear + "-0" + cha;
							} else {
								month1 = endYear + "-" + cha;
							}
						}
						double newrt1 = 0;
						double newrt2 = 0;
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								if (result.get(month1.toString()) == null) {
									newrt1 = 0.0;
								} else {
									String newrtString = result.get(
											month1.toString()).toString();
									newrt1 = Double.parseDouble(newrtString);
								}
							} else {
								if (resultMap.get(month1.toString()) == null) {
									newrt2 = 0.0;
								} else {
									String newrtString = resultMap.get(
											month1.toString()).toString();
									newrt2 = Double.parseDouble(newrtString);
								}
							}
						}
						double newrt = add(newrt1, newrt2);
						xAxis = xAxis + month1 + ",";
						newshuju = newshuju + newrt + ",";
					}

				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			} else if (placeName.length() > 1 && "3".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, "4");
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double sumZhou = 0.0;
					if (i == size) {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}
						}
					} else {
						for (String BusId : bt) {
							if (("不定时").equals(btf.get(BusId))) {
								for (int j = 0; j < 7; j++) {
									c.setTime(start);
									c.add(Calendar.DAY_OF_MONTH, j);
									String day = new SimpleDateFormat(
											"yyyy-MM-dd").format(c.getTime());
									if (result.get(day.toString()) == null) {
										sumZhou = add(sumZhou, 0.0);
									} else {
										String newrtString = result.get(
												day.toString()).toString();
										sumZhou = add(sumZhou,
												Double.parseDouble(newrtString));
									}
								}
							}
						}
						for (int j = 0; j < 7; j++) {
							c.setTime(start);
							c.add(Calendar.DAY_OF_MONTH, j);
							String day = new SimpleDateFormat("yyyy-MM-dd")
									.format(c.getTime());
							if (resultMap.get(day.toString()) == null) {
								sumZhou = add(sumZhou, 0.0);
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								sumZhou = add(sumZhou,
										Double.parseDouble(newrtString));
							}

						}
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, 7);
						start = c.getTime();
					}

					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + sumZhou + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "4".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
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
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(day.toString()) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(day.toString())
										.toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(day.toString()) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(
										day.toString()).toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + day1 + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			} else if (placeName.length() > 1 && "5".equals(toTime)) {
				resultMap = orderInfoDao.getPayMoneyByTime2(placeCode,
						startDate, endDate, toTime);
				result = qqPermonthRecodeDao.getPayMoneyByTimeRound(placeCode,
						startDate, endDate, toTime);
				for (int i = 0; i <= 24; i++) {
					String str = null;
					if (i < 10) {
						str = "0" + i;
					} else {
						str = i + "";
					}
					double newrt1 = 0;
					double newrt2 = 0;
					for (String BusId : bt) {
						if (("不定时").equals(btf.get(BusId))) {
							if (result.get(str) == null) {
								newrt1 = 0.0;
							} else {
								String newrtString = result.get(str).toString();
								newrt1 = Double.parseDouble(newrtString);
							}
						} else {
							if (resultMap.get(str) == null) {
								newrt2 = 0.0;
							} else {
								String newrtString = resultMap.get(str)
										.toString();
								newrt2 = Double.parseDouble(newrtString);
							}
						}
					}
					double newrt = add(newrt1, newrt2);
					xAxis = xAxis + str + ",";
					newshuju = newshuju + newrt + ",";
				}
				String xAxis1 = xAxis;
				String newshuju1 = "["
						+ newshuju.substring(0, newshuju.length() - 1) + "]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);

			}

		} catch (ParseException e1) {
			map.put("respCode", -1);
			map.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
