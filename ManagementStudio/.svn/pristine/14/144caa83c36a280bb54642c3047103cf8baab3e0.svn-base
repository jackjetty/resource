package com.rising.management.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.PublicInfo;
import com.rising.management.service.PublicInfoService;

@Controller("publicInfoAction")
public class PublicInfoAction {
	Log log = LogFactory.getLog(PublicInfoAction.class);
	
	private Integer pageSize;
	private Integer pageIndex;
	private Integer id;
	private String ids; 
	private String publicInfo;
	private String title;
	private String time;
	
	@Autowired
	PublicInfoService publicInfoService;
	
	private HashMap<String, Object> resultMap;
	
	public String doPublicInfo(){
		return "success";
	}
	
	public String getPublicInfoList(){
		resultMap=publicInfoService.getPublicInfo(pageSize, pageIndex);
		return "success";
	}

	public String addPublicInfo(){
		Date dateType=null;
		try {
			dateType = new SimpleDateFormat("yyyy-MM-dd").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PublicInfo p = new PublicInfo();
		p.setPublicInfo(publicInfo);
		p.setTitle(title);
		p.setTime(dateType);
		resultMap=publicInfoService.addPublicInfo(p);
		return "success";
	}
	
	public String updatePublicInfo(){
		Date dateType=null;
		try {
			dateType = new SimpleDateFormat("yyyy-MM-dd").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PublicInfo p = new PublicInfo();
		p.setId(id);
		p.setPublicInfo(publicInfo);
		p.setTitle(title);
		p.setTime(dateType);
		resultMap=publicInfoService.updatePublicInfo(p);
		return "success";
	}
	
	public String removePublicInfo(){
		resultMap = publicInfoService.removePublicInfo(ids);
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	

}
