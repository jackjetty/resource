package com.detachment.web.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbHtml;
import com.detachment.pojo.TbHtmlType;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbHtmlVo;
import com.detachment.web.service.TbHtmlService;
import com.detachment.web.service.TbHtmlTypeService;
import com.opensymphony.xwork2.ActionContext;

@Controller("contentMangeAction")
public class ContentMangeAction {
	private HashMap<String, Object> result;
	private ArrayList<TbHtmlType> list;
	private ArrayList<TbHtmlVo> htmlvoList;
	private TbHtmlVo htmlvo;
	
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String htmlType;
	private String htmlContent;
	private String htmlTitle;
	private String htmlDes;
	private Integer htmlId;
	private String ids;
	private String htmlTypeId;
	
	@Autowired
	TbHtmlTypeService tbHtmlTypeService;
	@Autowired
	TbHtmlService tbHtmlService;
	
	public String doContentManage(){
		return "success";
	}
	
	
	
	public String getHtmlTypeIdName(){
		result=tbHtmlTypeService.getTbHtmlTypeIdName();
		return "success";
	}

	public String getTbHtmls(){
		result=tbHtmlService.getTbHtml(startTime, endTime, htmlType, pageSize, pageIndex);
		return "success";
	}
	
	public String addHtml(){
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();
		TbHtml th=new TbHtml();
		th.setHtmlContent(htmlContent.replaceAll("/detachment/web/", ""));
		th.setHtmlDes(htmlDes);
		th.setHtmlTitle(htmlTitle);
		th.setHtmlVip(false);
		th.setPublishTime(new Timestamp(date.getTime()));
		th.setValid(true);
		th.setTbHtmlType(tbHtmlTypeService.getHtmlTypeById(htmlType));
		TbUser user= (TbUser) ActionContext.getContext().getSession().get("User");
		th.setTbUser(user);
		result=tbHtmlService.addTbHtml(th);
		return "success";
	}
	
	public String getUpdateHtml(){
		return "success";
	}
	public String getAddHtml(){
		return "success";
	}
	
	public String getHtmlByHtmlId(){
		htmlvo=tbHtmlService.getTbHtmlByHtmlId(htmlId);
		return "success";
	}
	
	public String updateHtml(){
		TbHtml th=tbHtmlService.getTbHtmlById(htmlId);
		th.setHtmlContent(htmlContent);
		th.setHtmlTitle(htmlTitle);
		th.setHtmlDes(htmlDes);
		th.setTbHtmlType(tbHtmlTypeService.getHtmlTypeById(htmlType));
		result=tbHtmlService.updateHtml(th);
		return "success";
	}
	
	public String getHtmlInfo(){
		return "success";
	}
	
	public String removeHtml(){
		result=tbHtmlService.removeHtml(ids);
		return "success";
	}
	
	public String doTbHtmlJsp(){
		return "success";
	}
	
	public String getTbHtmlJsp(){
		htmlvoList=tbHtmlService.getTbHtmlJsp(htmlTypeId, pageSize, pageIndex);
		return "success";
	}
	
	public String doTbHtmlInfoJsp(){
		return "success";
	}
	
	
	

	
	
	public HashMap<String, Object> getResult() {
		return result;
	}



	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}



	public ArrayList<TbHtmlType> getList() {
		return list;
	}



	public void setList(ArrayList<TbHtmlType> list) {
		this.list = list;
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



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getHtmlType() {
		return htmlType;
	}



	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}



	public String getHtmlContent() {
		return htmlContent;
	}



	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}



	public String getHtmlTitle() {
		return htmlTitle;
	}



	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}



	public String getHtmlDes() {
		return htmlDes;
	}



	public void setHtmlDes(String htmlDes) {
		this.htmlDes = htmlDes;
	}



	public Integer getHtmlId() {
		return htmlId;
	}



	public void setHtmlId(Integer htmlId) {
		this.htmlId = htmlId;
	}



	public TbHtmlVo getHtmlvo() {
		return htmlvo;
	}



	public void setHtmlvo(TbHtmlVo htmlvo) {
		this.htmlvo = htmlvo;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	public ArrayList<TbHtmlVo> getHtmlvoList() {
		return htmlvoList;
	}



	public void setHtmlvoList(ArrayList<TbHtmlVo> htmlvoList) {
		this.htmlvoList = htmlvoList;
	}



	public String getHtmlTypeId() {
		return htmlTypeId;
	}



	public void setHtmlTypeId(String htmlTypeId) {
		this.htmlTypeId = htmlTypeId;
	}
	
	
	
	
	
	
	
	
}
