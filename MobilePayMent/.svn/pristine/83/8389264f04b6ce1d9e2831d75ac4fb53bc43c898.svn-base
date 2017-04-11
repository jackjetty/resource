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

import com.google.gson.Gson;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.commom.PageObject;
import com.rising.mobilepayment.service.FaqService;
import com.rising.mobilepayment.service.OperateLogService;

@Controller
@RequestMapping("/faq")
public class FaqAction extends BaseAction {
	
	Log log = LogFactory.getLog(FaqAction.class);
	
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
	FaqService faqService;

	@RequestMapping("/get")
	public void getFaq(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		HashMap<String, String> map = getParameter(request);
		Integer PageIndex = Integer.parseInt(map.get("PageIndex"));
		PageObject page = new PageObject();
		page.setPageIndex(Integer.valueOf(PageIndex));
		page.setListSize(listSize);
		String resultJson = "";
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog("", "GetFaq",
					userAgent);
			resultJson = faqService.getFaq(page);
		} catch (Exception e) {
			log.error("getFaq()->用户获取Faq时插入操作记录失败！" + e.toString());
			resultMap.put("respCode", -204);
			resultMap.put("respInfo", "数据库插入数据异常");
			resultJson = new Gson().toJson(resultMap);
		}
		writeJSONString(resultJson, response);
	}
}
