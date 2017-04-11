package com.detachment.wei.service.impl;
import java.util.List;
import java.util.regex.Matcher;

import java.util.regex.Pattern; 
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod; 
import org.rising.wei.bean.ErrCodeBean;
import org.rising.wei.bean.news.MsgItemNewsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.detachment.dao.HistoryDao;
import com.detachment.factory.HttpFactory;
import com.detachment.util.CommonUtil;
import com.detachment.wei.service.NetService; 
 
 
@Service("netService")
public class NetServiceImpl implements NetService{
	@Autowired
	private HistoryDao historyDao;
	
	
	@Value("${web.login.user}") 
	private  String  LOGIN_USER ; 
	@Value("${web.login.pwd}") 
	private  String  LOGIN_PWD ; 
 
	private HttpFactory http = HttpFactory.getInstance();
	private HttpClient client =http.getClient(); 
	private Cookie[] cookies;
	private String cookiestr;
    private String token;
	public boolean isLogin = false; 
	
	
	private boolean login() {
		PostMethod post = new PostMethod(LOGIN_URL); 
		boolean sign=true;
		try { 
			 post.setRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			  
			
			 this.LOGIN_USER=CommonUtil.trim(LOGIN_USER);
			 this.LOGIN_PWD=CommonUtil.trim(LOGIN_PWD); 
			//post.setRequestHeader("Referer", "https://mp.weixin.qq.com/");
			post.setRequestHeader("Referer", "https://mp.weixin.qq.com/");
			post.setRequestHeader(USER_AGENT_H, USER_AGENT);
			NameValuePair[] params = new NameValuePair[]{
					new NameValuePair("username", this.LOGIN_USER),
					new NameValuePair("pwd", DigestUtils.md5Hex(this.LOGIN_PWD
							.getBytes())), new NameValuePair("f", "json"),
							new NameValuePair("imagecode", "")};
			post.setQueryString(params);
			
			int status = client.executeMethod(post); 
			if (status == HttpStatus.SC_OK) {
				String ret = post.getResponseBodyAsString();    
				if ( ret.indexOf("ok")>=0) {
					Pattern pattern=Pattern.compile("^.+(token=)(\\d+)(\"})"); 
					Matcher matcher=pattern.matcher(ret);  
					if(matcher.find()){
						token= matcher.group(2);    
					} 
					this.cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					for (Cookie c : client.getState().getCookies()) {
						cookie.append(c.getName()).append("=")
						.append(c.getValue()).append(";");
						
					}
					this.cookiestr = cookie.toString(); 
					this.isLogin = true; 
					sign=true;
				} 
				sign=false; 
			}
		} catch (Exception e) {
			String info = "【登录失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info); 
			
			sign=false; 
		}finally{
			if(post!=null){
				post.releaseConnection();
				post=null;
			}
		}
		return sign;
	} 
	public ErrCodeBean updateHistoryNews(){ 
		 
		this.login();
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("");
		errCodeBean.setErrmsg("操作成功！！");
		
		if(!this.isLogin){
			 errCodeBean.setErrcode("1");
			 errCodeBean.setErrmsg("登录失败！！");
			 return errCodeBean;
		}  
		String   paramStr = "/cgi-bin/masssendpage?t=mass/list&action=history&begin=0&count=10&token=" + token
				 +"&lang=zh_CN"; 
		GetMethod get = new GetMethod(ROOT_URL+paramStr);
		try {  
			
			get.setRequestHeader(REFERER_H, INDEX_URL);
			get.setRequestHeader("Cookie", this.cookiestr);
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) { 
				String responseBodyAsString=get.getResponseBodyAsString(); 
				Pattern pattern=Pattern.compile("\n");
				String [] lines=pattern.split(responseBodyAsString);
				Matcher matcher;
				String objLine="";
				for(String line:lines){ 
					line=line.trim();
					pattern=Pattern.compile("^(list)(.*)(msg_item,)$");
					matcher=pattern.matcher(line); 
					if(matcher.matches()){  
						objLine=line;
						break;
					}
				}
				
				objLine=objLine.replace("\\/", "/");
				pattern=Pattern.compile(".+(\"msg_item\":)(.+)(}\\).msg_item,)");
				matcher=pattern.matcher(objLine);  
				if(matcher.find()){
					   String line= matcher.group(2);   
					   List<MsgItemNewsBean> msgList=JSON.parseArray(line, MsgItemNewsBean.class);
					   for(MsgItemNewsBean msgItemNewsBean:msgList){ 
						   historyDao.add(msgItemNewsBean);
					   } 
				}  
			}
		} catch (Exception e) { 
			errCodeBean.setErrcode("2");
			errCodeBean.setErrmsg("获取新闻失败！！");
			 
			 
		}finally{
			if(get!=null){
				get.releaseConnection();
				get=null;
			}
		}
		return errCodeBean;
		
	}

}
