package com.traffic.wei.service;
 
import org.rising.wei.bean.ErrCodeBean;

public interface NetService {
	public final static String LOGIN_URL = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	public final static String USER_AGENT_H = "User-Agent";
	public final static String REFERER_H = "Referer";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8";
	public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanage";
	public final static String ROOT_URL="https://mp.weixin.qq.com";
	
	 
	public ErrCodeBean initNews();
	
}