package com.traffic.web.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojovo.TbFeedBackVo;
import com.traffic.util.ExcelUtil;
import com.traffic.web.service.FeedBackService;
import com.traffic.wei.service.CustResService;

@Controller("feedBackAction")
public class FeedBackAction {
	@Autowired
	FeedBackService feedBackService; 
	@Autowired
	CustResService custResService;
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String feedBackIds;
	private String feedOpenId;
	private Integer feedBackId;
	private String content;
	private String custResType;
	private String feedBackState;
	private HashMap<String, Object> result;
    
	public String doFeedBack() {
		return "success";
	}

	public String getFeedBack() {
		result = feedBackService.getFeedBack(startTime, endTime,feedBackState , pageSize, pageIndex);
		return "success";
	}
	public String getUserFbInfo(){
		result = feedBackService.getUserFbInfo(feedBackId);
		return "success";
	}
	
	public String addFeedCustRes(){
		result=feedBackService.addFeedCustRes(feedBackId, content);
		if("1".equals(result.get("code"))){
			ErrCodeBean eb=custResService.responseTextMessage((String)result.get("openId"), content);
			result.put("error", eb.getErrcode());
		}else{
			result.put("error", -1);
		}
		return "success";
	}
	
	 
	
	public void exportFbInfo() {
		/*Integer size = feedBackService
				.getFeedBackSize(startTime, endTime,feedBackState);*/
		ArrayList<TbFeedBackVo> oiv = feedBackService.getFeedBackVo( startTime, endTime,feedBackState);
		String filename = null;
		try {
			filename = new String("用户反馈信息".getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filename + ".xls");
		try {

			ExcelUtil.exportClassroom1(response.getOutputStream(), oiv);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	public String doFeedBackReply(){
		return "success";
	} 
	
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	

	public Integer getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getFeedBackIds() {
		return feedBackIds;
	}

	public void setFeedBackIds(String feedBackIds) {
		this.feedBackIds = feedBackIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCustResType() {
		return custResType;
	}

	public void setCustResType(String custResType) {
		this.custResType = custResType;
	}

	public String getFeedBackState() {
		return feedBackState;
	}

	public void setFeedBackState(String feedBackState) {
		this.feedBackState = feedBackState;
	} 
}
