package com.rising.mobilepayment.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.commom.MD5;
import com.rising.mobilepayment.mapper.SecurityCodeMapper;
import com.rising.mobilepayment.service.ShortMessageService;

@Service("shortMessageService")
public class ShortMessageServiceImpl implements ShortMessageService {
	Log log = LogFactory.getLog(ShortMessageServiceImpl.class);

	private Integer smsCodeTimeLimit;

	public Integer getSmsCodeTimeLimit() {
		return smsCodeTimeLimit;
	}

	@Value("#{propertiesReader[smsCodeTimeLimit]}")
	public void setSmsCodeTimeLimit(Integer smsCodeTimeLimit) {
		this.smsCodeTimeLimit = smsCodeTimeLimit;
	}
	
	
	private Integer smsCodeTimeLimit2;

	public Integer getSmsCodeTimeLimit2() {
		return smsCodeTimeLimit2;
	}

	@Value("#{propertiesReader[smsCodeTimeLimit2]}")
	public void setSmsCodeTimeLimit2(Integer smsCodeTimeLimit2) {
		this.smsCodeTimeLimit2 = smsCodeTimeLimit2;
	}

	@Autowired
	SecurityCodeMapper securityCodeMapper;

	private String registerMessageParameter;

	public String getRegisterMessageParameter() {
		return registerMessageParameter;
	}

	@Value("#{propertiesReader[registerMessageParameter]}")
	public void setRegisterMessageParameter(String registerMessageParameter) {
		this.registerMessageParameter = registerMessageParameter;
	}

	private String payMessageParameter;

	public String getPayMessageParameter() {
		return payMessageParameter;
	}

	@Value("#{propertiesReader[payMessageParameter]}")
	public void setPayMessageParameter(String payMessageParameter) {
		this.payMessageParameter = payMessageParameter;
	}

	private String findPasswordMessageParameter;

	public String getFindPasswordMessageParameter() {
		return findPasswordMessageParameter;
	}

	@Value("#{propertiesReader[findPasswordMessageParameter]}")
	public void setFindPasswordMessageParameter(
			String findPasswordMessageParameter) {
		this.findPasswordMessageParameter = findPasswordMessageParameter;
	}

	private String shortMessageIp;

	public String getShortMessageIp() {
		return shortMessageIp;
	}

	@Value("#{propertiesReader[shortMessageIp]}")
	public void setShortMessageIp(String shortMessageIp) {
		this.shortMessageIp = shortMessageIp;
	}

	private String cancelQQPerMonthParameter;

	public String getCancelQQPerMonthParameter() {
		return cancelQQPerMonthParameter;
	}
	@Value("#{propertiesReader[cancelQQPerMonthParameter]}")
	public void setCancelQQPerMonthParameter(String cancelQQPerMonthParameter) {
		this.cancelQQPerMonthParameter = cancelQQPerMonthParameter;
	}

	// 传入参数phoneNumber为发送短信的目标手机号码
	@Override
	public String registerShortMessage(String phoneNumber, String checkCode) {

		String url = shortMessageIp;
		String parameter = registerMessageParameter + "caller=" + phoneNumber
				+ "$username=" + checkCode + "$";
		HashMap<String, Object> result = new HashMap<String, Object>();
		Boolean flag = false;
		try {
			flag = sendShortMessage(url, parameter);
		} catch (Exception e) {
			log.error("registerShortMessage()->发送注册验证码短信请求出错!" + e.toString());
			flag = false;
		}
		if (flag) {
			if (saveSecurityCode(phoneNumber, checkCode)) {
				result.put("respCode", 0);
				result.put("respInfo", "");
				return new Gson().toJson(result);
			} else {
				result.put("respCode", -5);
				result.put("respInfo", "短信验证码发送失败！");
				return new Gson().toJson(result);
			}

		} else {
			result.put("respCode", -5);
			result.put("respInfo", "短信验证码发送失败！");
			return new Gson().toJson(result);
		}
	}

	@Override
	public String payShortMessage(String phoneNumber, String checkCode) {
		String url = shortMessageIp;
		String parameter = payMessageParameter + "caller=" + phoneNumber
				+ "$username=" + checkCode + "$";
		HashMap<String, Object> result = new HashMap<String, Object>();
		Boolean flag = false;
		try {
			flag = sendShortMessage(url, parameter);
		} catch (Exception e) {
			log.error("payShortMessage()->发送支付验证码短信请求出错!" + e.toString());
			flag = false;
		}
		if (flag) {
			if (saveSecurityCode(phoneNumber, checkCode)) {
				result.put("respCode", 0);
				result.put("respInfo", "");
				return new Gson().toJson(result);
			} else {
				result.put("respCode", -5);
				result.put("respInfo", "短信验证码发送失败！");
				return new Gson().toJson(result);
			}

		} else {
			result.put("respCode", -5);
			result.put("respInfo", "短信验证码发送失败！");
			return new Gson().toJson(result);
		}
	}

	private boolean saveSecurityCode(String PhoneNumber, String checkCode) {
		try {
			SecurityCode code = new SecurityCode();
			code.setPhoneNumber(PhoneNumber);
			code.setCode(MD5.encryptByMD5With16Bit(checkCode));
			code.setInvalidTime(getDeadTime());
			SecurityCode code1 = securityCodeMapper
					.findCodeByPhoneNumber(PhoneNumber);
			if (code1 != null) {
				securityCodeMapper.update(code);
			} else {
				securityCodeMapper.addSecurityCode(code);
			}
			return true;
		} catch (Exception e) {
			log.error("保存验证码失败！");
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean saveSecurityCode2(String PhoneNumber, String checkCode) {
		try {
			SecurityCode code = new SecurityCode();
			code.setPhoneNumber(PhoneNumber);
			code.setCode(MD5.encryptByMD5With16Bit(checkCode));
			code.setInvalidTime(getDeadTime2());
			SecurityCode code1 = securityCodeMapper
					.findCodeByPhoneNumber(PhoneNumber);
			if (code1 != null) {
				securityCodeMapper.update(code);
			} else {
				securityCodeMapper.addSecurityCode(code);
			}
			return true;
		} catch (Exception e) {
			log.error("保存验证码失败！");
			e.printStackTrace();
			return false;
		}
	}

	private Date getDeadTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + smsCodeTimeLimit);
		return c.getTime();

	}
	
	private Date getDeadTime2() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + smsCodeTimeLimit);
		return c.getTime();

	}

	private boolean sendShortMessage(String url, String parameter)
			throws Exception {
		String result = "";
		java.net.URL httpUrl = new URL(url);
		URLConnection uc = (URLConnection) httpUrl.openConnection();
		uc.setDoOutput(true);
		uc.setDoInput(true);
		PrintWriter out = new PrintWriter(uc.getOutputStream());
		out.write(parameter);
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				uc.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		in.close();
		HashMap<String, String> map = new HashMap<String, String>();
		result = result.replace("$", ",");
		String[] params = result.split(",");
		String key = "";
		String value = "";
		for (String a : params) {
			key = a.split("=")[0];
			try {
				value = a.split("=")[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				value = "";
			}
			map.put(key, value);
		}
		if ("0".equals(map.get("result"))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String findPasswordShortMessage(String phoneNumber, String checkCode) {
		String url = shortMessageIp;
		String parameter = findPasswordMessageParameter + "caller="
				+ phoneNumber + "$username=" + checkCode + "$";
		HashMap<String, Object> result = new HashMap<String, Object>();
		Boolean flag = false;
		try {
			flag = sendShortMessage(url, parameter);
		} catch (Exception e) {
			log.error("findPasswordShortMessage()->发送找回密码验证码短信请求出错!"
					+ e.toString());
			flag = false;
		}
		if (flag) {
			if (saveSecurityCode(phoneNumber, checkCode)) {
				result.put("respCode", 0);
				result.put("respInfo", "");
				return new Gson().toJson(result);
			} else {
				result.put("respCode", -5);
				result.put("respInfo", "短信验证码发送失败！");
				return new Gson().toJson(result);
			}
		} else {
			result.put("respCode", -5);
			result.put("respInfo", "短信验证码发送失败！");
			return new Gson().toJson(result);
		}
	}

	@Override
	public String cancelQQPerMonthShortMessage(String phoneNumber,
			String securityCode) {
		String url = shortMessageIp;
		String parameter = cancelQQPerMonthParameter + "caller="
				+ phoneNumber + "$username=" + securityCode + "$";
		HashMap<String, Object> result = new HashMap<String, Object>();
		Boolean flag = false;
		try {
			flag = sendShortMessage(url, parameter);
		} catch (Exception e) {
			log.error("findPasswordShortMessage()->发送取消Q币包月验证码短信请求出错!"
					+ e.toString());
			flag = false;
		}
		if (flag) {
			if (saveSecurityCode(phoneNumber, securityCode)) {
				result.put("respCode", 0);
				result.put("respInfo", "");
				return new Gson().toJson(result);
			} else {
				result.put("respCode", -5);
				result.put("respInfo", "短信验证码发送失败！");
				return new Gson().toJson(result);
			}
		} else {
			result.put("respCode", -5);
			result.put("respInfo", "短信验证码发送失败！");
			return new Gson().toJson(result);
		}
	}

	@Override
	public String sendBindShortMessage(String phoneNumber, String securityCode) {
		String url = shortMessageIp;
		String parameter = payMessageParameter + "caller=" + phoneNumber
				+ "$username=" + securityCode + "$";
		HashMap<String, Object> result = new HashMap<String, Object>();
		Boolean flag = false;
		try {
			flag = sendShortMessage(url, parameter);
		} catch (Exception e) {
			log.error("payShortMessage()->发送支付验证码短信请求出错!" + e.toString());
			flag = false;
		}
		if (flag) {
			if (saveSecurityCode2(phoneNumber, securityCode)) {
				result.put("respCode", 0);
				result.put("respInfo", "");
				return new Gson().toJson(result);
			} else {
				result.put("respCode", -5);
				result.put("respInfo", "短信验证码发送失败！");
				return new Gson().toJson(result);
			}

		} else {
			result.put("respCode", -5);
			result.put("respInfo", "短信验证码发送失败！");
			return new Gson().toJson(result);
		}
	}

}
