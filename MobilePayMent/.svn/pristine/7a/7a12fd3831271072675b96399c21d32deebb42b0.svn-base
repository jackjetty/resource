package com.rising.mobilepayment.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.OperateLog;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.UserInfoService;

@Controller
@RequestMapping("/check")
public class CheckAction extends BaseAction {

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	MessageMapper messageMapper;

	Log log = LogFactory.getLog(CheckAction.class);

	@RequestMapping("/in")
	public void checkIn(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		String resultJson = "";
		String userAgent = request.getHeader("user-agent");
		try {
			ArrayList<OperateLog> log = operateLogService
					.findCheckInLog(PhoneNumber);
			if (log == null || log.size() == 0) {
				operateLogService.addOperateLog(PhoneNumber, "CheckIn",
						userAgent);
				resultJson = userInfoService.checkIn(PhoneNumber);
			} else {
				resultMap.put("respCode", -15);
				String responseText = messageMapper
						.getMessageContentByUse("CheckInFailed");
				resultMap.put("respInfo", responseText);
				resultJson = new Gson().toJson(resultMap);
			}

		} catch (Exception e) {
			log.error("checkIn()->用户签到时插入操作记录失败！" + e.toString());
			resultMap.put("respCode", -204);
			resultMap.put("respInfo", "数据库插入数据异常");
			resultJson = new Gson().toJson(resultMap);
		}
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/getLeftMoney")
	public void getLeftMoney(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = "";
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "GetLeftMoney",
					userAgent);
			resultJson = userInfoService.getLeftMoney(PhoneNumber);
		} catch (Exception e) {
			log.error("getLeftMoney()->用户查询可用本金时插入操作记录失败！" + e.toString());
			resultMap.put("respCode", -204);
			resultMap.put("respInfo", "数据库插入数据异常");
			resultJson = new Gson().toJson(resultMap);
		}
		writeJSONString(resultJson, response);
	}

	 

	 

	 

}
