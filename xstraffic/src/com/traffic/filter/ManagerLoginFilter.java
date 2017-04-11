package com.traffic.filter;

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

import com.traffic.util.CommonUtil;




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
			Object currentUser = session.getAttribute("Manager");
			
			if(requestUri.indexOf("/wei/")>=0
					||requestUri.indexOf("/accident/")>=0
					||requestUri.indexOf("/wap/")>=0){
				
				
				 if(  requestUri.indexOf("/wap/voteHome")>=0){
					 if(CommonUtil.trim(httpServletRequest.getQueryString()).indexOf("code")<0){
						   httpServletResponse.sendRedirect(httpServletRequest
									.getContextPath() + "/");
							return;
					 }
					System.out.println(CommonUtil.getDateForm("yyyy-MM-dd  HH:mm:ss") +"开始了啊openId"+ CommonUtil.getInstance().getRemoteHost( httpServletRequest));
 
					chain.doFilter(request, response);
					return;
				      
				  } 
				
				  if(session.getAttribute("openId")==null && requestUri.indexOf("/wap/vote")>=0){
					  httpServletResponse.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadab35d48d9e4266&redirect_uri=http%3a%2f%2fwww.weiximen.com%2fxstraffic%2fwap%2fvoteHome.action%3fvoteId%3dbd2389ac6d3f4c14b30d9b56fbbe3f8b&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
				      System.out.println("未认证哦");
					  return; 
				  }
					  
					  
				 
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
					&& !requestUri.endsWith("/getWfinfo.action")
					&& !requestUri.endsWith("/doWeiFaSearch.action")
					&& !requestUri.endsWith("/getWFresult.action")
					&& !requestUri.endsWith("/getCLresult.action")
					&& !requestUri.endsWith("/getWfinfo.action")
					&& !requestUri.endsWith("/getCodeJsp.action")
					&& !requestUri.endsWith("/getAllCodeJsp.action")
					&& !requestUri.endsWith("/getCodeInfoJsp.action")
					&& !requestUri.endsWith("/getCodeInfoById.action")
					&& !requestUri.endsWith("/doMechanismInfo.action")
					&& !requestUri.endsWith("/getMechanismInfo.action")
					&& !requestUri.endsWith(".html")
					&& !requestUri.endsWith("/doRealTrafficWBJsp.action")
					&& !requestUri.endsWith("/getPublicInfoWB.action")
					&& !requestUri.endsWith("/trafficWbPic.action")
					
					
					//在线考试
					&& !requestUri.endsWith("/getOnlineStudy.action")
					&& !requestUri.endsWith("/getProblemJsp.action")
					&& !requestUri.endsWith("/getStudyPhone.action")
					&& !requestUri.endsWith("/saveStudyInfo.action")
					&& !requestUri.endsWith("/studyImages.action")
					&& !requestUri.endsWith("/getPhoneNumber.action")
					&& !requestUri.endsWith("/doStudyHistory.action")
					&& !requestUri.endsWith("/getStudyInfoJsp.action")
					&& !requestUri.endsWith("/viewImages.action")
					
					//投票活动
					&& !requestUri.endsWith("/getVoteList.action")
					
					&& !requestUri.endsWith(".jpeg")
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


