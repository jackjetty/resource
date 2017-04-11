package com.rising.management.filter;

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
			Object currentUser = session.getAttribute("Manager");
			// 当前会话用户为空而且不是请求登录，退出登录，欢迎页面和根目录则退回到应用的根目录,以URI的形式出现
			 if (currentUser == null
					&& !requestUri.endsWith(httpServletRequest.getContextPath()
							+ "/") && !requestUri.endsWith("/index.html")
					&& !requestUri.endsWith("/getCaptchaImage.action")
					&& !requestUri.endsWith("/logout.action")
					&& !requestUri.endsWith("/login.action") 
					&& !requestUri.endsWith(".gif")
					&& !requestUri.endsWith(".jpg")
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
