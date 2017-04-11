package com.rising.management.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.OperateLogService;
import com.rising.management.service.OrderInfoService;
import com.rising.management.service.ProductService;
import com.rising.management.service.QQPermonthRecodeService;
import com.rising.management.service.UserCheckinService;
import com.rising.management.service.UserInfoService;
import com.rising.management.service.WinnerListService;
import com.rising.management.vo.UserInfoVo;

@Controller("statisticsAction")
public class StatisticsAction {
	private Integer pageSize;

	private Integer pageIndex;

	private String startTime;

	private String endTime;

	private String viewNewRegisterLink;

	private String creditsLink;

	private String viewNewRegisterLink2;

	private String place;

	private String busId;

	private String toTime;

	private String placeName;

	private String ways;

	private String productId;

	private String p;

	private Integer min;

	private Integer max;

	private String returnCode;

	private String phoneNumber;

	private String registerTime;

	private String lastTradeTime;

	private HashMap<String, Object> map;

	private HashMap<String, Integer> highChart;

	private HashMap<String, Object> resultMap;

	private ArrayList<HashMap<String, Object>> listMap;

	private ArrayList<HashMap<String, String>> list;

	private Integer dayNumber;

	private Boolean noOrder;

	private UserInfoVo user;

	@Autowired
	OrderInfoService orderInfoService;

	@Autowired
	ProductService productService;
	@Autowired
	OperateLogService operateLogService;

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	QQPermonthRecodeService qqPermonthRecodeService;

	@Autowired
	UserCheckinService userCheckinService;

	@Autowired
	WinnerListService winnerListService;

	// 交易信息统计 产品
	public String doStatistics() {
		Integer BusId = null;
		busId = "100";
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		listMap = orderInfoService.getStatisticsAll2(BusId, startTime, endTime);
		return "success";
	}

	public String doStatisticsInfoByPlace() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		place = "571";
		listMap = orderInfoService.getStatisticsInfoByPlace(startTime, endTime,
				place);
		return "success";
	}
	
	public String doStatisticsInfoByPlace2() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		place = "571";
		listMap = orderInfoService.getStatisticsInfoByPlace2(startTime, endTime,
				place);
		return "success";
	}

	public String getStatisticsInfoByPlace() {
		listMap = orderInfoService.getStatisticsInfoByPlace(startTime, endTime,
				place);
		return "success";
	}
	
	public String getStatisticsInfoByPlace2() {
		listMap = orderInfoService.getStatisticsInfoByPlace2(startTime, endTime,
				place);
		return "success";
	}

	// 第二版地区模式
	public String doStatisticsByPlaceAll() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		place = "571";
		Integer BusId = null;
		busId = "100";
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		resultMap = orderInfoService.getStatisticsAll(startTime, endTime,
				place, BusId);

		return "success";
	}

	// 图形模式
	public String doStatisticsByHighCharts() {
		return "success";
	}

	// 图形模式查询
	public String getStatisticsByHighCharts() {
		if ("null".equals(productId)) {
			productId = "";
		}
		Integer BusId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		if ("".equals(ways)) {
			ways = null;
		}
		listMap = orderInfoService.getStatisticsAllByHighCharts(productId,
				ways, BusId, startTime, endTime);
		return "success";

	}

	// 第二版地区模式查询
	public String getStatisticsByPlaceAll() {
		Integer BusId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		resultMap = orderInfoService.getStatisticsAll(startTime, endTime,
				place, BusId);
		return "success";
	}

	// 第一版交易统计查询
	public String getStatistics() {
		resultMap = orderInfoService.getStatistics(startTime, endTime);
		return "success";
	}

	public String findAll() {
		resultMap = productService.findAll();
		return "success";
	}

	// 新注册用户统计
	public String doAppUserStatistics() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		resultMap = operateLogService.getAppUserStatistics(startTime, endTime);
		viewNewRegisterLink = "viewNewRegister.action?startTime=" + startTime
				+ "&endTime=" + endTime;
		return "success";
	}

	// Q币统计
	public String doQQStatistics() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		toTime = "3";
		return "success";
	}

	// Q币统计查询
	public String getQQStatistics() {
		resultMap = qqPermonthRecodeService.getQQStatistics(startTime, endTime,toTime);
		return "success";
	}

	// 新注册用户查询
	public String getAppUserStatistics() {
		resultMap = operateLogService.getAppUserStatistics(startTime, endTime);
		return "success";
	}

	// 交易统计（业务）
	public String doStatisticsAll() {
		Integer BusId = null;
		busId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		listMap = orderInfoService.getStatisticsAllByWays(BusId, startTime,
				endTime);
		return "success";
	}
	
	public String doStatisticsAll2() {
		Integer BusId = null;
		busId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		listMap = orderInfoService.getStatisticsAllByWays2(BusId, startTime,
				endTime);
		return "success";
	}

	// 按产品查询交易统计 （业务）
	public String getStatisticsAllByProduct() {
		Integer BusId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		listMap = orderInfoService.getStatisticsAll2(BusId, startTime, endTime);
		return "success";
	}

	public String getStatisticsAll() {
		Integer BusId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}

		listMap = orderInfoService.getStatisticsAllByWays(BusId, startTime,
				endTime);
		return "success";
	}

	public String getStatisticsAll2() {
		Integer BusId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}
		listMap = orderInfoService.getStatisticsAll2(BusId, startTime, endTime);
		return "success";
	}
	
	public String getStatisticsAll3() {
		Integer BusId = null;
		if (null != busId && !"".equals(busId) && !"null".equals(busId)) {
			BusId = Integer.parseInt(busId);
		}

		listMap = orderInfoService.getStatisticsAllByWays2(BusId, startTime,
				endTime);
		return "success";
	}


	public String doAppUserStatisticsAll() {
		placeName = null;
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		listMap = operateLogService.getAppUserStatisticsAll(placeName,
				startTime, endTime);
		viewNewRegisterLink = "viewNewRegister.action?startTime=" + startTime
				+ "&endTime=" + endTime;
		return "success";
	}

	public String getAppUserStatisticsAll() {
		listMap = operateLogService.getAppUserStatisticsAll(placeName,
				startTime, endTime);
		viewNewRegisterLink = "viewNewRegister.action?startTime=" + startTime
				+ "&endTime=" + endTime;
		return "success";
	}

	public String doUserScore() {
		listMap = userInfoService.doUserScoreStatistics();
		creditsLink = "creditsNumbers.action";
		return "success";
	}

	public String creditsNumbers() {
		return "success";
	}

	public String viewNewRegister() {
		return "success";
	}

	public String getCreditsNumbers() {
		resultMap = userInfoService.getCreditsNumbers(phoneNumber,
				registerTime, lastTradeTime, pageSize, pageIndex, min, max);
		return "success";
	}

	public String getNewUser() {
		resultMap = userInfoService.getNewUser(phoneNumber, registerTime,
				lastTradeTime, pageSize, pageIndex, startTime, endTime, p);
		return "success";
	}

	// 交易统计（返回码）
	public String doStatisticsByCode() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		returnCode = "";
		list = orderInfoService.getStatisticsByCode(startTime, endTime,
				returnCode);
		return "success";
	}

	public String getStatisticsByCode() {
		list = orderInfoService.getStatisticsByCode(startTime, endTime,
				returnCode);
		return "success";
	}

	public String doLostUser() {
		return "success";
	}

	public String getLostUser() {
		if ("".equals(phoneNumber)) {
			phoneNumber = null;
		}
		resultMap = operateLogService.getLostUserStatistics(pageSize,
				pageIndex, dayNumber, phoneNumber, noOrder);
		return "success";
	}

	public String viewUserDetail() {
		user = userInfoService.getUserInfo(phoneNumber);
		return "success";
	}

	public String getUserOrderInfo() {
		resultMap = orderInfoService.getOrderInfoByPhoneNumber(pageSize,
				pageIndex, phoneNumber);
		return "success";
	}

	public String getUserCheckInInfo() {
		resultMap = userCheckinService.getCheckInInfoByPhoneNumber(pageSize,
				pageIndex, phoneNumber);
		return "success";
	}

	public String getUserWinningInfo() {
		resultMap = winnerListService.getWinningInfoByPhoneNumber(pageSize,
				pageIndex, phoneNumber);
		return "success";
	}

	public String getUserQQPerMonthInfo() {
		resultMap = qqPermonthRecodeService.getUserQQPerMonthRecord(pageSize,
				pageIndex, phoneNumber);
		return "success";
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getViewNewRegisterLink() {
		return viewNewRegisterLink;
	}

	public void setViewNewRegisterLink(String viewNewRegisterLink) {
		this.viewNewRegisterLink = viewNewRegisterLink;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public HashMap<String, Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getViewNewRegisterLink2() {
		return viewNewRegisterLink2;
	}

	public void setViewNewRegisterLink2(String viewNewRegisterLink2) {
		this.viewNewRegisterLink2 = viewNewRegisterLink2;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getWays() {
		return ways;
	}

	public void setWays(String ways) {
		this.ways = ways;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public HashMap<String, Integer> getHighChart() {
		return highChart;
	}

	public void setHighChart(HashMap<String, Integer> highChart) {
		this.highChart = highChart;
	}

	public ArrayList<HashMap<String, Object>> getListMap() {
		return listMap;
	}

	public void setListMap(ArrayList<HashMap<String, Object>> listMap) {
		this.listMap = listMap;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public ArrayList<HashMap<String, String>> getList() {
		return list;
	}

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public String getCreditsLink() {
		return creditsLink;
	}

	public void setCreditsLink(String creditsLink) {
		this.creditsLink = creditsLink;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Boolean getNoOrder() {
		return noOrder;
	}

	public void setNoOrder(Boolean noOrder) {
		this.noOrder = noOrder;
	}

	public UserInfoVo getUser() {
		return user;
	}

	public void setUser(UserInfoVo user) {
		this.user = user;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

}
