package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.PublicInfo;


public class PublicInfoVo {
	private Integer id;
	private String publicInfo;
	private String title;
	private String time;
	
	public PublicInfoVo(PublicInfo p){
		this.id = p.getId();
		this.publicInfo = p.getPublicInfo().replace("<p>","").replace("</p>", "");
		this.title = p.getTitle();
		this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getTime());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPublicInfo() {
		return publicInfo;
	}
	public void setPublicInfo(String publicInfo) {
		this.publicInfo = publicInfo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
