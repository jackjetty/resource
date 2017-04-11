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
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.QQPerMonthStatus;
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.mapper.QQPerMonthStatusMapper;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.OrderInfoService;
import com.rising.mobilepayment.service.SecurityCodeService;

@Controller
@RequestMapping("/charge")
public class TakeOrderAction extends BaseAction {
	Log log = LogFactory.getLog(TakeOrderAction.class);

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	OrderInfoService orderInfoService;

	@Autowired
	SecurityCodeService securityCodeService;

	@Autowired
	QQPerMonthStatusMapper qQPerMonthStatusMapper;

	@RequestMapping("/takeOrder")
	public void takeOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String SmsValiCode = map.get("SmsValiCode");
		SecurityCode code = new SecurityCode();
		code.setCode(SmsValiCode);
		code.setPhoneNumber(PhoneNumber);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		if (!securityCodeService.isSecurityCodeValidate(code, resultMap)) {
			resultJson = new Gson().toJson(resultMap);
		} else {
			try {
				operateLogService.addOperateLog(PhoneNumber, "TakeOrder",userAgent);
			} catch (Exception e) {
				log.error("takeOrder()->用户下单时插入操作记录失败！" + e.toString());
			}
			try {
				map.put("user-agent",userAgent.toLowerCase());
				resultJson = orderInfoService.takeOrder(map);
			} catch (RuntimeException e) {
				e.printStackTrace();
				log.error("takeOrder()->" + e.toString());
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("respCode", -6);
				result.put("respInfo", "下单失败");
				resultJson = new Gson().toJson(result);
			}
		}
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/takeQQPerMonthOrder")
	public void takeQQPerMonthOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String SmsValiCode = map.get("SmsValiCode");
		SecurityCode code = new SecurityCode();
		code.setCode(SmsValiCode);
		code.setPhoneNumber(PhoneNumber);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		if (!securityCodeService.isSecurityCodeValidate(code, resultMap)) {
			resultJson = new Gson().toJson(resultMap);
		} else {
			try {
				operateLogService.addOperateLog(PhoneNumber,
						"TakeQQPerMonthOrder",userAgent);
			} catch (Exception e) {
				log.error("takeQQPerMonthOrder()->用户下单时插入操作记录失败！"
						+ e.toString());
			}
			try {
				map.put("user-agent",userAgent.toLowerCase());
				resultJson = orderInfoService.takeOrderQQPerMonth(map);
			} catch (RuntimeException e) {
				e.printStackTrace();
				log.error("takeOrder()->" + e.toString());
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("respCode", -6);
				result.put("respInfo", "下单失败");
				resultJson = new Gson().toJson(result);
			}
		}
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/takeQQVIPOrder")
	public void takeQQVIPOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String SmsValiCode = map.get("SmsValiCode");
		SecurityCode code = new SecurityCode();
		code.setCode(SmsValiCode);
		code.setPhoneNumber(PhoneNumber);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		if (!securityCodeService.isSecurityCodeValidate(code, resultMap)) {
			resultJson = new Gson().toJson(resultMap);
		} else {
			try {
				operateLogService.addOperateLog(PhoneNumber, "TakeQQVIPOrder",userAgent);
			} catch (Exception e) {
				log.error("TakeQQVIPOrder()->用户下单时插入操作记录失败！" + e.toString());
			}

			try {
				map.put("user-agent",userAgent.toLowerCase());
				resultJson = orderInfoService.takeQQVIPOrder(map);
			} catch (RuntimeException e) {
				e.printStackTrace();
				log.error("takeOrder()->" + e.toString());
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("respCode", -6);
				result.put("respInfo", "下单失败");
				resultJson = new Gson().toJson(result);
			}
		}
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/cancelQQPerMonthOrder")
	public void cancelQQPerMonthOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber,
					"CancelQQPerMonthOrder",userAgent);
		} catch (Exception e) {
			log.error("cancelQQPerMonthOrder()->用户取消Q币包月时插入操作记录失败！"
					+ e.toString());
		}
		try {
			resultJson = orderInfoService.cancelQQPerMonthOrder(map);
			checkQQPerMonthOrder(PhoneNumber);
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error("cancelQQPerMonthOrder()->" + e.toString());
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -6);
			result.put("respInfo", "取消Q币包月失败！");
			resultJson = new Gson().toJson(result);
		}

		writeJSONString(resultJson, response);
	}

	@RequestMapping("/checkQQPerMonthOrder")
	public void checkQQPerMonthOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService
					.addOperateLog(PhoneNumber, "CheckQQPerMonthOrder",userAgent);
		} catch (Exception e) {
			log.error("CheckQQPerMonthOrder()->用户查询Q币包月时插入操作记录失败！"
					+ e.toString());
		}
		try {
			resultMap = orderInfoService.checkPerMonth(PhoneNumber);
			resultMap.put("phonenumber", PhoneNumber);
			ArrayList<QQPerMonthStatus> aqpms = qQPerMonthStatusMapper
					.findByPhoneNumber(PhoneNumber);
			if (aqpms != null && aqpms.size() > 0) {
				if("0".equals(resultMap.get("result"))){
					QQPerMonthStatus qqPerMonthStatus = aqpms.get(0);
					qqPerMonthStatus.setApplyDate((String) resultMap.get("applydate"));
					qqPerMonthStatus.setMonth((String) resultMap.get("month"));
					qqPerMonthStatus.setCancelDate((String) resultMap.get("canceldate"));
					qqPerMonthStatus.setFee((String) resultMap.get("fee"));
					qqPerMonthStatus.setQqNumber((String) resultMap.get("qqnum"));
					qqPerMonthStatus.setRegDate((String) resultMap.get("regdate"));
					qqPerMonthStatus.setStatus((String) resultMap.get("status"));
					qqPerMonthStatus.setUpdateDate((String) resultMap.get("updatedate"));
					qQPerMonthStatusMapper.updateQQPerMonthStatus(qqPerMonthStatus);
				}
			} else {
				ArrayList<OrderInfo> aoi = orderInfoService.findQQPerMonthOrderByPhoneNumber(PhoneNumber);
				if(aoi!=null && aoi.size()>0){
					if("0".equals(map.get("result"))){
						QQPerMonthStatus qqPerMonthStatus = new QQPerMonthStatus();
						qqPerMonthStatus.setPhoneNumber(PhoneNumber);
						qqPerMonthStatus.setApplyDate((String) map.get("applydate"));
						qqPerMonthStatus.setMonth((String) map.get("month"));
						qqPerMonthStatus.setCancelDate((String) map.get("canceldate"));
						qqPerMonthStatus.setFee((String) map.get("fee"));
						qqPerMonthStatus.setQqNumber((String) map.get("qqnum"));
						qqPerMonthStatus.setRegDate((String) map.get("regdate"));
						qqPerMonthStatus.setStatus((String) map.get("status"));
						qqPerMonthStatus.setUpdateDate((String) map.get("updatedate"));
						qQPerMonthStatusMapper.addQQPerMonthStatus(qqPerMonthStatus);
					}
				}
			}
			HashMap<String, Object> result = getResult(resultMap);
			resultJson = new Gson().toJson(result);
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error("CheckQQPerMonthOrder()->" + e.toString());
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -6);
			result.put("respInfo", "查询Q币包月失败！");
			resultJson = new Gson().toJson(result);
		}

		writeJSONString(resultJson, response);
	}

	public void checkQQPerMonthOrder(String phoneNumber) {
		HashMap<String, Object> map = orderInfoService
				.checkPerMonth(phoneNumber);
		ArrayList<QQPerMonthStatus> aqpms = qQPerMonthStatusMapper
				.findByPhoneNumber(phoneNumber);
		if (aqpms != null && aqpms.size() > 0) {
			if("0".equals(map.get("result"))){
				QQPerMonthStatus qqPerMonthStatus = aqpms.get(0);
				qqPerMonthStatus.setApplyDate((String) map.get("applydate"));
				qqPerMonthStatus.setMonth((String) map.get("month"));
				qqPerMonthStatus.setCancelDate((String) map.get("canceldate"));
				qqPerMonthStatus.setFee((String) map.get("fee"));
				qqPerMonthStatus.setQqNumber((String) map.get("qqnum"));
				qqPerMonthStatus.setRegDate((String) map.get("regdate"));
				qqPerMonthStatus.setStatus((String) map.get("status"));
				qqPerMonthStatus.setUpdateDate((String) map.get("updatedate"));
				qQPerMonthStatusMapper.updateQQPerMonthStatus(qqPerMonthStatus);
			}
		} else {
			ArrayList<OrderInfo> aoi = orderInfoService.findQQPerMonthOrderByPhoneNumber(phoneNumber);
			if(aoi!=null && aoi.size()>0){
				if("0".equals(map.get("result"))){
					QQPerMonthStatus qqPerMonthStatus = new QQPerMonthStatus();
					qqPerMonthStatus.setPhoneNumber(phoneNumber);
					qqPerMonthStatus.setApplyDate((String) map.get("applydate"));
					qqPerMonthStatus.setMonth((String) map.get("month"));
					qqPerMonthStatus.setCancelDate((String) map.get("canceldate"));
					qqPerMonthStatus.setFee((String) map.get("fee"));
					qqPerMonthStatus.setQqNumber((String) map.get("qqnum"));
					qqPerMonthStatus.setRegDate((String) map.get("regdate"));
					qqPerMonthStatus.setStatus((String) map.get("status"));
					qqPerMonthStatus.setUpdateDate((String) map.get("updatedate"));
					qQPerMonthStatusMapper.addQQPerMonthStatus(qqPerMonthStatus);
				}
			}
			
		}
	}
	
	 
	@RequestMapping("/checkQQPerMonthOrderWithSecurityCode")
	public void checkQQPerMonthOrderWithSecurityCode(
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String SmsValiCode = map.get("SmsValiCode");
		SecurityCode code = new SecurityCode();
		code.setCode(SmsValiCode);
		code.setPhoneNumber(PhoneNumber);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = null;
		String userAgent = request.getHeader("user-agent");
		if (!securityCodeService.isSecurityCodeValidate(code, resultMap)) {
			resultJson = new Gson().toJson(resultMap);
		} else {
			try {
				operateLogService.addOperateLog(PhoneNumber,
						"CheckQQPerMonthOrder",userAgent);
			} catch (Exception e) {
				log.error("CheckQQPerMonthOrder()->用户查询Q币包月时插入操作记录失败！"
						+ e.toString());
			}
			try {
				resultMap = orderInfoService.checkPerMonth(PhoneNumber);
				resultMap.put("phonenumber", PhoneNumber);
				HashMap<String, Object> result = getResult(resultMap);
				resultJson = new Gson().toJson(result);
			} catch (RuntimeException e) {
				e.printStackTrace();
				log.error("CheckQQPerMonthOrder()->" + e.toString());
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("respCode", -6);
				result.put("respInfo", "查询Q币包月失败！");
				resultJson = new Gson().toJson(result);
			}
		}
		writeJSONString(resultJson, response);
	}

	private String getStauts(Integer status) {
		switch (status) {
		case 0:
			return "正在申请中";
		case 1:
			return "已开通";
		case 2:
			return "已取消";
		case 3:
			return "已停止";
		case 4:
			return "已暂停";
		}
		return null;
	}

	private HashMap<String, Object> getResult(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (map.get("result") == null || map.get("result").equals("99")) {
			result.put("respCode", -20);
			result.put("respInfo", "Q币包月信息查询失败！");
		} else if (map.get("result").equals("1")) {
			result.put("respCode", -20);
			result.put("respInfo", "您还没有申请过Q币包月服务！");
		} else if (map.get("result").equals("0")) {
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("PhoneNumber", map.get("phonenumber"));
			String QQNum = (String) map.get("qqnum");
			QQNum = QQNum.replace('z', ' ');
			QQNum = QQNum.trim();
			result.put("TargetNumber", QQNum);
			result.put("Cost", map.get("fee"));
			Float fee = Float.parseFloat((String) map.get("fee"));
			float f = (float) (fee * 0.93);
			result.put("PayMoney", f);
			result.put("ApplyTime", map.get("applydate"));
			result.put("NowStatus",
					getStauts(Integer.parseInt((String) map.get("status"))));
			String status = (String) map.get("status");
			if (status != null) {
				if (status.equals("2")) {
					result.put("CancelTime", map.get("canceldate"));
				} else {
					result.put("CancelTime", "");
				}

			}
			String month = new SimpleDateFormat("yyyyMM").format(new Date());
			if (map.get("month") != null && !map.get("month").equals("")
					&& map.get("month").equals(month)) {
				result.put("thisMonth", "扣费成功");
			} else if (map.get("month") == null
					&& map.get("status").equals("1")) {
				result.put("thisMonth", "尚未扣费");
			} else if (map.get("month") == null
					&& map.get("status").equals("4")) {
				result.put("thisMonth", "扣费失败");
			} else {
				result.put("thisMonth", "扣费失败");
			}
			String phoneNumber = (String) map.get("phonenumber");
			ArrayList<QQPerMonthStatus> aqpms = qQPerMonthStatusMapper
					.findByPhoneNumber(phoneNumber);
			if (aqpms != null && aqpms.size() > 0) {
				QQPerMonthStatus qqPerMonthStatus = aqpms.get(0);
				qqPerMonthStatus.setApplyDate((String) map.get("applydate"));
				qqPerMonthStatus.setMonth((String) map.get("month"));
				qqPerMonthStatus.setCancelDate((String) map.get("canceldate"));
				qqPerMonthStatus.setFee((String) map.get("fee"));
				qqPerMonthStatus.setQqNumber((String) map.get("qqnum"));
				qqPerMonthStatus.setRegDate((String) map.get("regdate"));
				qqPerMonthStatus.setStatus((String) map.get("status"));
				qqPerMonthStatus.setUpdateDate((String) map.get("updatedate"));
				qQPerMonthStatusMapper.updateQQPerMonthStatus(qqPerMonthStatus);
			} else {
				ArrayList<OrderInfo> aoi = orderInfoService.findQQPerMonthOrderByPhoneNumber(phoneNumber);
				if(aoi!=null && aoi.size()>0){
					if("0".equals(map.get("result"))){
						QQPerMonthStatus qqPerMonthStatus = new QQPerMonthStatus();
						qqPerMonthStatus.setPhoneNumber(phoneNumber);
						qqPerMonthStatus.setApplyDate((String) map.get("applydate"));
						qqPerMonthStatus.setMonth((String) map.get("month"));
						qqPerMonthStatus.setCancelDate((String) map.get("canceldate"));
						qqPerMonthStatus.setFee((String) map.get("fee"));
						qqPerMonthStatus.setQqNumber((String) map.get("qqnum"));
						qqPerMonthStatus.setRegDate((String) map.get("regdate"));
						qqPerMonthStatus.setStatus((String) map.get("status"));
						qqPerMonthStatus.setUpdateDate((String) map.get("updatedate"));
						qQPerMonthStatusMapper.addQQPerMonthStatus(qqPerMonthStatus);
					}
				}
			}
		}
		return result;
	}

}
