package com.traffic.web.action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojovo.TbDoAnswerVo;
import com.traffic.util.CommonUtil;
import com.traffic.util.ExcelUtil;
import com.traffic.web.service.DoAnswerService;

@Controller("doAnswerAction")
public class DoAnswerAction {
	private String startTime;
	private String endTime;
	private String doAnswerState;
	private int doAnswerId;
	private int page;
	private int rows;
	private String content;
	private HashMap<String,Object> result;
	
	
	@Autowired
	private DoAnswerService doAnswerService;
	
	public String doAnswer() {
		Calendar cal = Calendar.getInstance();  
        endTime=CommonUtil.getDateForm(cal.getTime());
        cal.set(Calendar.DATE, -15);
        startTime=CommonUtil.getDateForm(cal.getTime());
		return "success";
	}
	public String getDoAnswer(){
		result = doAnswerService.getDoAnswer(page, rows,startTime,endTime,doAnswerState);
		
		return "success";
	}
	public String getUserDaInfo(){
		result = doAnswerService.getUserDaInfo(doAnswerId);
		return "success";
	}
    public void exportDaInfo(){
    	List<TbDoAnswerVo> doAnswerList = doAnswerService.getFeedBackVoList( startTime, endTime,doAnswerState);
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
		response.addHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
		try {

			ExcelUtil.exportDoAnswer(response.getOutputStream(), doAnswerList);

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public String addAnswerCustRes(){
    	result=doAnswerService.addAnswerCustRes(doAnswerId, content); 
		return "success";
    }

	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	 
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	public String getDoAnswerState() {
		return doAnswerState;
	}
	public void setDoAnswerState(String doAnswerState) {
		this.doAnswerState = doAnswerState;
	}
	public int getDoAnswerId() {
		return doAnswerId;
	}
	public void setDoAnswerId(int doAnswerId) {
		this.doAnswerId = doAnswerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}