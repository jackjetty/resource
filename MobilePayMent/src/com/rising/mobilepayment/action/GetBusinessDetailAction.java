/**
 * GetBusinessDetail.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 获取指定业务种类下的各种产品信息 如Q币的各种币值
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:32:38
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.ProductService;

@Controller
@RequestMapping("/business")
public class GetBusinessDetailAction extends BaseAction {
	
	@Autowired
	OperateLogService operateLogService;

	@Autowired
	ProductService productService;

	@RequestMapping("/getBusinessDetail")
	public void getBusinessDetail(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String BusId = map.get("BusId");
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog("", "GetBusinessDetail",userAgent);
		} catch (Exception e) {
		}
		String resultJson = productService.findProductsByBusId(BusId);
		writeJSONString(resultJson, response);
	}

}
