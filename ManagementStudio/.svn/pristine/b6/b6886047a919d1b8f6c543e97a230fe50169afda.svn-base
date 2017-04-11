package com.rising.management.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;

import com.rising.management.bean.OperateRecord;
import com.rising.management.common.GetApplicationContext;
import com.rising.management.service.BaseService;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		Integer aa = (Integer) event.getSession().getAttribute("logout");
		String username = (String) event.getSession().getAttribute(
				"managerUser");
		ApplicationContext applicationContext = GetApplicationContext
				.getApplicationContext();
		BaseService Operate = (BaseService) applicationContext
				.getBean("baseService");
		if (aa != null && aa.equals(2)) {
			OperateRecord record = new OperateRecord();
			record.setManager(username);
			record.setOperateType("用户退出");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户：" + username + "主动退出,退出成功");
			if (username != null) {
				Operate.saveOperateLogging(record);
			}
		} else {
			OperateRecord record = new OperateRecord();
			record.setManager(username);
			record.setOperateType("用户退出");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户：" + username + "超时退出,退出成功");
			if (username != null) {
				Operate.saveOperateLogging(record);
			}
		}

	}

}
