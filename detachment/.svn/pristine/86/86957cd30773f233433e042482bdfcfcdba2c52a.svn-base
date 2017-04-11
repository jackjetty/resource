package com.detachment.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.rising.wei.bean.BaseBean;
import org.rising.wei.bean.res.NewsResBean;
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;

import cn.rising.base.RedisGeneratorDao;
import cn.rising.model.SessionModel;

import com.detachment.wei.service.ProcessService; 
  
public class WeiUtil{
	public static BaseBean getTextMessage(BaseBean inMsg,String message){ 
		TextResBean localResBean=new TextResBean();
		localResBean.setCreateTime( inMsg.getCreateTime());
		localResBean.setFromUserName( inMsg.getToUserName());
		localResBean.setToUserName( inMsg.getFromUserName());
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_TEXT);
		localResBean.setContent(message);  
		return localResBean; 
	} 
	
	public static BaseBean getNewsMessage(BaseBean inMsg,ArrayList<ItemResObject> articles){ 
		NewsResBean localResBean=new NewsResBean();
		localResBean.setCreateTime(inMsg.getCreateTime());
		localResBean.setFromUserName(inMsg.getToUserName());
		localResBean.setToUserName(inMsg.getFromUserName()); 
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_NEWS);
		ItemResObject itemResObject; 
		localResBean.setArticles(articles) ;
		localResBean.setArticleCount(new Integer(articles.size()).toString());
		return localResBean;   
		
	}
	 
}