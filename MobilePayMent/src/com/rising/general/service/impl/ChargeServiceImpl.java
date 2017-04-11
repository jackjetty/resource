package com.rising.general.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.general.bean.Order;
import com.rising.general.bean.Product;
import com.rising.general.bean.UserInfo;
import com.rising.general.mapper.OrderIdHelperMapper;
import com.rising.general.mapper.OrderMapper;
import com.rising.general.mapper.ProductMapper;
import com.rising.general.mapper.UserInfoMapper;
import com.rising.general.service.ChargeService;

@Service
public class ChargeServiceImpl implements ChargeService {

	private String payMoneyIp;

	public String getPayMoneyIp() {
		return payMoneyIp;
	}

	@Value("#{propertiesReader[payMoneyIp]}")
	public void setPayMoneyIp(String payMoneyIp) {
		this.payMoneyIp = payMoneyIp;
	}

	Log log = LogFactory.getLog(ChargeServiceImpl.class);

	@Autowired
	OrderIdHelperMapper gOrderIdHelperMapper;

	@Autowired
	OrderMapper gOrderMapper;

	@Autowired
	ProductMapper gProductMapper;
	
	@Autowired
	UserInfoMapper gUserInfoMapper;
	
	private String phoneNumberCheckIp;
	
	public String getPhoneNumberCheckIp() {
		return phoneNumberCheckIp;
	}

	@Value("#{propertiesReader[phoneNumberCheckIp]}")
	public void setPhoneNumberCheckIp(String phoneNumberCheckIp) {
		this.phoneNumberCheckIp = phoneNumberCheckIp;
	}
	
	private String checkUserPhoneNumberParameter;

	public String getCheckUserPhoneNumberParameter() {
		return checkUserPhoneNumberParameter;
	}

	@Value("#{propertiesReader[checkUserPhoneNumberParameter]}")
	public void setCheckUserPhoneNumberParameter(
			String checkUserPhoneNumberParameter) {
		this.checkUserPhoneNumberParameter = checkUserPhoneNumberParameter;
	}

	private String generateOrderId() {
		String Time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Integer maxOrderId = gOrderIdHelperMapper.getMaxOrderId(Time);
		if (maxOrderId != null) {
			Integer nextId = gOrderIdHelperMapper.getNextId();
			String id = String.valueOf(nextId);
			for (int i = id.length(); i < 8; i++) {
				id = "0" + id;
			}
			id = Time + id;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("time", Time);
			map.put("nextId", nextId);
			gOrderIdHelperMapper.update(map);
			return id;
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			gOrderIdHelperMapper.reNewSequence();
			Integer nextId = gOrderIdHelperMapper.getNextId();
			map.put("time", Time);
			map.put("id", nextId);
			gOrderIdHelperMapper.add(map);
			String id = String.valueOf(nextId);
			for (int i = id.length(); i < 8; i++) {
				id = "0" + id;
			}
			return Time + id;
		}
	}

	@Override
	public HashMap<String, Object> apply(HashMap<String, String> map)
			throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Product product = gProductMapper.findProductById(map.get("productId"));
		if (product == null) {
			result.put("respCode", -6);
			result.put("respInfo", "扣费失败");
			return result;
		}
		Order order = new Order();
		order.setOrderId(generateOrderId());
		order.setProductId(map.get("productId"));
		order.setBusId(product.getBusId());
		order.setTargetNumber(map.get("targetNumber"));
		order.setPayMoney(Long.parseLong(map.get("cost")));
		String phoneNumber = map.get("phoneNumber");
		order.setPhoneNumber(phoneNumber);
		order.setOrderTime(new Date());
		order.setPayMethodId(Integer.parseInt(map.get("payMethodId")));
		order.setHcOrderId(map.get("order_no"));
		order.setOrderStatus("成功");
		order.setPayStatus("未支付");
		order.setSPID(product.getSPID());
		order.setSendScore(0);
		order.setOs(map.get("remark"));
		order.setClient(map.get("methodDetail"));
		order.setVersion("");
		gOrderMapper.add2(order);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (chargeToServer(payMoneyIp, map.get("phoneNumber"),
					map.get("cost"), resultMap)) {
				order.setPayReturnCode(String.valueOf(resultMap.get("result")));
				order.setPayStatus("成功");
				order.setPayTime(new Date());
				order.setSendScore(0);
				gOrderMapper.update(order);
				result = new HashMap<String, Object>();
				result.put("respCode", 0);
			} else {
				order.setPayStatus("失败");
				order.setPayTime(new Date());
				order.setPayReturnCode(String.valueOf(resultMap.get("result")));
				order.setSendScore(0);
				gOrderMapper.update(order);
				result = new HashMap<String, Object>();
				result.put("respCode", resultMap.get("result"));
				result.put("respInfo", "扣费失败");
			}
		} catch (Exception e) {
			throw e;
		}
		 
		return result;
	}

	private Boolean chargeToServer(String BaseAddress, String phoneNumber,
			String cost, HashMap<String, Object> map) {

		String parameter = "objid=50$username=16887885$payid=ransi$caller=" + phoneNumber + "$paymoney=" + cost
				+ "$";

		try {
			String result = "";
			java.net.URL httpUrl = new URL(BaseAddress);
			HttpURLConnection uc = (HttpURLConnection) httpUrl.openConnection();
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
			if(result.startsWith("0")){
				map.put("result","0");
			}else{
				map.put("result",result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if("0".equals(map.get("result"))){
			return true;
		}else
			return false;
	}
	
	 

}
