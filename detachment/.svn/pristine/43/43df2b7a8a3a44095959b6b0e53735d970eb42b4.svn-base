package com.detachment.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerLoginFilter implements Filter {
	private FilterConfig filterConfig;
	private String forwardPath;
	private boolean ignore;

	@Override
	public void destroy() {

	} 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (ignore != true) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			String requestUri = httpServletRequest.getRequestURI();
			// 通过检查session中的变量，过虑请求
			HttpSession session = httpServletRequest.getSession();
			Object currentUser = session.getAttribute("User");
			if(requestUri.indexOf("/wei/")>=0
					||requestUri.indexOf("/wap/")>=0
					||requestUri.indexOf("/accident/")>=0
					||requestUri.indexOf("/handyPhoto/")>=0
					||requestUri.indexOf("/doAppointmentJsp.action")>=0
							||requestUri.indexOf("/appaccidentaction.action")>=0
							||requestUri.indexOf("/appaccidentUpdate.action")>=0
					||requestUri.indexOf("/doAppointmentRecordJsp.action")>=0){
				
				chain.doFilter(request, response);
				return;
			}
			
			
			// 当前会话用户为空而且不是请求登录，退出登录，欢迎页面和根目录则退回到应用的根目录,以URI的形式出现
			 if (currentUser == null
					&& !requestUri.endsWith(httpServletRequest.getContextPath()
							+ "/") && !requestUri.endsWith("/index.html")
					&& !requestUri.endsWith("/getCaptchaImage.action")
					&& !requestUri.endsWith("/logout.action")
					&& !requestUri.endsWith("/login.action")
					&& !requestUri.endsWith("/getaccidentJsp.action")
					&& !requestUri.endsWith("/getRecordsByOpenId.action")
					&& !requestUri.endsWith("/doWeiFaSearch.action")
					&& !requestUri.endsWith("/getCLresult.action")
					&& !requestUri.endsWith("/getWFresult.action")
					&& !requestUri.endsWith("/getElePoliceJsp.action")
					&& !requestUri.endsWith("/getElePolices.action")
					//&& !requestUri.endsWith("/saveProblem.action")
					&& !requestUri.endsWith("/getTbHtmlJsp.action")
					&& !requestUri.endsWith("/doTbHtmlJsp.action")
					&& !requestUri.endsWith("/doTbHtmlInfoJsp.action")
					&& !requestUri.endsWith("/getHtmlByHtmlId.action")
					&& !requestUri.endsWith("/viewImages.action")
					&& !requestUri.endsWith("/doHistoryJsp.action")
					&& !requestUri.endsWith("/doHistoryDriving.action")
					&& !requestUri.endsWith("/doHistoryRoad.action")
					&& !requestUri.endsWith("/getHistoryToJsp.action")
					&& !requestUri.endsWith("/doOnlineStudy.action")
					&& !requestUri.endsWith("/getProblemJsp.action")
					&& !requestUri.endsWith("/doRealTraffic.action")
					&& !requestUri.endsWith("/getRealTraffic.action")
					&& !requestUri.endsWith("/doRealTrafficInfo.action")
					&& !requestUri.endsWith(".html")
					&& !requestUri.endsWith("/doFullScodeLogin.action")
					&& !requestUri.endsWith("/doFucScode.action")
					&& !requestUri.endsWith("/toFucScodeLogin.action")
					&& !requestUri.endsWith("/saveFirstStudyTime.action")
					&& !requestUri.endsWith("/getTotalHours.action")
					&& !requestUri.endsWith("/toAutomaticLogin.action")
					&& !requestUri.endsWith("/saveAppointmentRecord.action")
					&& !requestUri.endsWith("/doAppointmentJsp.action")
					&& !requestUri.endsWith("/getAppointmentJsp.action")
					&& !requestUri.endsWith("/getAppointmentRecordJsp.action")
					&& !requestUri.endsWith("/doAppointmentRecordJsp.action")
					&& !requestUri.endsWith("/getPeopleInfoByIdentityCard.action")
					&& !requestUri.endsWith("/doPeopleInformation.action")
					&& !requestUri.endsWith("/updateFucScodeInfo.action")
					// tinker 2014-10-09 调查问卷
					&& !requestUri.endsWith("/doExamine.action")
					&& !requestUri.endsWith("/doExamine2.action")
					&& !requestUri.endsWith("/doExamine3.action")
					&& !requestUri.endsWith("/submitExamine.action")
					// tinker 2014-10-11 地图
					&& !requestUri.endsWith("/doMapSearch.action")
					&& !requestUri.endsWith("/getMonitorList.action")
					
					&& !requestUri.endsWith(".svg")
					&& !requestUri.endsWith(".gif")
					&& !requestUri.endsWith(".jpg")
					&& !requestUri.endsWith(".png")
					&& !requestUri.endsWith(".js")
					&& !requestUri.endsWith(".css")) {
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/");
				return;
			} 
			chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
		} 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.forwardPath = filterConfig.getInitParameter("forwardPath");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public String getForwardPath() {
		return forwardPath;
	}

	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

}


