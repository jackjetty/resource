package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.FeedBackService;
import com.rising.mobilepayment.service.OperateLogService;

@Controller
@RequestMapping("/feedBack")
public class FeedBackAction extends BaseAction {

	@Autowired
	FeedBackService feedBackService;

	Log log = LogFactory.getLog(FeedBackAction.class);

	@Autowired
	OperateLogService operateLogService;

	@RequestMapping("/record")
	public void userFeedBack(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "FeedBack",userAgent);
		} catch (Exception e) {
			log.error("userFeedBack()->用户反馈信息时插入操作记录失败！" + e.toString());
		}
		String resultJson = feedBackService.record(map);
		writeJSONString(resultJson, response);
	}
}
