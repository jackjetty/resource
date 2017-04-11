package com.rising.mobilepayment.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OrderInfoService;

@Controller
@RequestMapping("/telephoneFee")
public class TelephoneFeeAction extends BaseAction {
	
	Log log = LogFactory.getLog(TelephoneFeeAction.class);

	@Autowired
	OrderInfoService orderInfoService;

	@RequestMapping("/record")
	public void record(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultJson = "";
		try {
			log.error(mapToString(map));
			resultJson = orderInfoService.recordTelephoneFee(map);
		} catch (Exception e) {
			resultMap.put("ret_code", "1111");
			resultMap.put("ret_msg", "交易失败");
			e.printStackTrace();
			new Gson().toJson(resultMap);
		}
		writeJSONString2(resultJson, response);
	}
	
	public String mapToString(HashMap<String, String> map) {
		String parameter = "";
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			parameter = parameter + key + "=" + value + "&";
		}
		return parameter;
	}

}
