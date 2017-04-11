package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.detachment.pojo.TbHtml;


public class TbHtmlVo {
	private Integer htmlId;
	private String htmlTypeName;
	private String userName;
	private String htmlTitle;
	private String htmlDes;
	private String htmlContent;
	private String publishTime;
	
	public TbHtmlVo(){}
	
	public TbHtmlVo(TbHtml th,HashMap<String,Object> map){
		this.htmlId = th.getHtmlId();
		this.htmlTypeName =(String) map.get(th.getTbHtmlType().getHtmlTypeId());
		this.userName = th.getTbUser().getUserId();
		this.htmlTitle = th.getHtmlTitle();
		this.htmlDes = th.getHtmlDes();
		this.htmlContent = th.getHtmlContent();
		this.publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(th.getPublishTime()) ;
	}
	
	
	

	public Integer getHtmlId() {
		return htmlId;
	}
	public void setHtmlId(Integer htmlId) {
		this.htmlId = htmlId;
	}
	public String getHtmlTypeName() {
		return htmlTypeName;
	}
	public void setHtmlTypeName(String htmlTypeName) {
		htmlTypeName = htmlTypeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	
	
}
