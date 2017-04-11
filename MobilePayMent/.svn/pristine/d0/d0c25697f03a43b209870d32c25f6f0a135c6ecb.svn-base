package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.commom.PageObject;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.PublicInfoService;

@Controller
@RequestMapping("/publicInfo")
public class PublicInfoAction extends BaseAction {
	
	Log log = LogFactory.getLog(QueryTradeSimpleAction.class);
	
	@Autowired
	OperateLogService operateLogService;

	private Integer listSize;

	public Integer getListSize() {
		return listSize;
	}

	@Value("#{propertiesReader[listSize]}")
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	@Autowired
	PublicInfoService publicInfoService;

	@RequestMapping("/get")
	public void get(HttpServletRequest request, HttpServletResponse response) {
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog("", "GetPublicInfo",userAgent);
		} catch (Exception e) {
			log.error("get()->用户获取公告时插入操作记录失败！" + e.toString());
		}
		HashMap<String, String> map = getParameter(request);
		Integer PageIndex = Integer.parseInt(map.get("PageIndex"));
		PageObject page = new PageObject();
		page.setPageIndex(Integer.valueOf(PageIndex));
		page.setListSize(listSize);
		String resultJson = publicInfoService.getPublicInfo(page);
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/getMessage")
	public void getMessage(HttpServletRequest request,
			HttpServletResponse response) {
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog("", "GetMessage",userAgent);
		} catch (Exception e) {
			log.error("getMessage()->用户获取消息时插入操作记录失败！" + e.toString());
		}
		HashMap<String, String> map = getParameter(request);
		String phoneNumber = map.get("PhoneNumber");
		Integer pageSize = Integer.parseInt(map.get("PageSize"));
		Integer pageIndex = Integer.parseInt(map.get("PageIndex"));
		String resultJson = publicInfoService.getMessage(phoneNumber,pageSize,pageIndex);
		writeJSONString(resultJson, response);
	}

//	@RequestMapping("/setPublicInfoReaded")
//	public void setPublicInfoReaded(HttpServletRequest request,
//			HttpServletResponse response) {
//		HashMap<String, String> map = getParameter(request);
//		String phoneNumber = map.get("PhoneNumber");
//		Integer maxId = Integer.parseInt(map.get("MaxId"));
//		String resultJson = publicInfoService.setPublicInfoReaded(phoneNumber,
//				maxId);
//		writeJSONString(resultJson, response);
//	}

//	@RequestMapping("/setAnswerFeedBackReaded")
//	public void setAnswerFeedBackReaded(HttpServletRequest request,
//			HttpServletResponse response) {
//		HashMap<String, String> map = getParameter(request);
//		String phoneNumber = map.get("PhoneNumber");
//		Integer maxId = Integer.parseInt(map.get("MaxId"));
//		String resultJson = publicInfoService.setAnswerFeedBaceReaded(
//				phoneNumber, maxId);
//		writeJSONString(resultJson, response);
//	}
	
//	@RequestMapping("/getMoreMessage")
//	public void getMoreMessage(HttpServletRequest request,
//			HttpServletResponse response) {
//		HashMap<String, String> map = getParameter(request);
//		String phoneNumber = map.get("PhoneNumber");
//		Integer PageIndex = Integer.parseInt(map.get("PageIndex"));
//		String messageType= map.get("MessageType");
//		Integer pageSize = Integer.parseInt(map.get("PageSize"));
//		PageObject page = new PageObject();
//		page.setPageIndex(Integer.valueOf(PageIndex));
//		page.setListSize(pageSize);
//		String resultJson = publicInfoService.getMessageByType(phoneNumber,page,messageType);
//		writeJSONString(resultJson, response);
//	}

}
