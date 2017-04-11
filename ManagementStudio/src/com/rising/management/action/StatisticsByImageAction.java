package com.rising.management.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rising.management.service.OperateLogService;
import com.rising.management.service.OrderInfoService;
import com.rising.management.service.PlaceService;
import com.rising.management.service.UserCheckinService;
import com.rising.management.service.UserInfoService;

@Controller("statisticsByImageAction")
public class StatisticsByImageAction {
	Log log = LogFactory.getLog(StatisticsByImageAction.class);

	private String startTime;

	private String endTime;

	private String placeName;

	private HashMap<String, Object> resultMap;

	private HashMap<String, Object> resultMap1;

	private String type;

	private String toTime;

	@Autowired
	OperateLogService operateLogService;
	@Autowired
	PlaceService placeService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	UserCheckinService userCheckinService;

	public String doAppUserStatisticsByImage() {
		return "success";
	}

	public String getAllAppUserInfo() {
		resultMap = operateLogService.getAppUserStatisticsAll1(placeName,
				startTime, endTime, toTime);
		return "success";
	}

	public String doStatisticsByRecharge() {
		toTime = "0";
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}

	public String getStatisticsByRecharge() {
		resultMap = orderInfoService.getStatisticsByRecharge(placeName,
				startTime, endTime, toTime);
		return "success";
	}
	
	public String doStatisticsByRecharge2() {
		toTime = "0";
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}

	public String getStatisticsByRecharge2() {
		resultMap = orderInfoService.getStatisticsByRecharge2(placeName,
				startTime, endTime, toTime);
		return "success";
	}

	public String doUserActivity() {
		toTime = "0";
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}

	public String getUserActivity() {
		resultMap = orderInfoService.getUserActivity(placeName, startTime,
				endTime, toTime);
		return "success";
	}

	public String doUserCheckinByImage() {
		toTime = "0";
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}

	public String getUserCheckinByImage() {
		resultMap = userCheckinService.getUserCheckinByImage(placeName,
				startTime, endTime, toTime);
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

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<String, Object> getResultMap1() {
		return resultMap1;
	}

	public void setResultMap1(HashMap<String, Object> resultMap1) {
		this.resultMap1 = resultMap1;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

}
