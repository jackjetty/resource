package com.detachment.web.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.vo.TbFeedBackVo;
import com.detachment.util.ExcelUtil;
import com.detachment.web.service.FeedBackService;
import com.detachment.wei.service.CustResService;

@Controller("feedBackAction")
public class FeedBackAction {
	@Autowired
	private FeedBackService feedBackService;
	
	
	 
	private int pageIndex;
	private String dateFrom;
	private String dateEnd; 
	private String feedOpenId;
	private int feedBackId;
	private String content;
	private HashMap<String, Object> result; 
	public String feedBackPage(){
		
		result=feedBackService.feedBackPage(pageIndex, dateFrom,dateEnd);
		return "success"; 
	}

	 
	
	public String feedBackCustRes(){
		
		result=feedBackService.feedBackCustRes(feedOpenId, content); 
		return "success";
	}
	
	
	public String getUserFbInfoReply(){
		result=feedBackService.getUserFbInfoReply(feedBackId);
		return "success";
	}
	
	 
	
	
	 
	public String feedBackChatPage(){
		
		result=feedBackService.feedBackChatPage(feedBackId);
		
		return "success";
	}
	
	
	
	
	
	
	
	
	 
	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	 

	public Integer getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}

	 
	

	public String getFeedOpenId() {
		return feedOpenId;
	}

	public void setFeedOpenId(String feedOpenId) {
		this.feedOpenId = feedOpenId;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getDateFrom() {
		return dateFrom;
	}



	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}



	public String getDateEnd() {
		return dateEnd;
	}



	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}



	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}



	public void setFeedBackId(int feedBackId) {
		this.feedBackId = feedBackId;
	}
    

}
