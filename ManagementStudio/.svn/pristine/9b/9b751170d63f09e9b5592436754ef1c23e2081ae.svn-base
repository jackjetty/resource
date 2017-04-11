package com.rising.management.action;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rising.management.bean.BackInfo;
import com.rising.management.service.BackInfoService;
@Controller("feedBackAction")
public class FeedBackAction {
	private String myObj;
	private String phoneNumber;
	private String email;
	private String procedureMessage;
	private Integer fbid;
	private Integer pageSize;
	private Integer pageIndex;
	private HashMap<String, Object> resultMap;
	@Autowired
	BackInfoService backinfoService;
	public String selectFeedBack(){
		return "success";
	}
	
	public String searchBackInfo(){
		resultMap = backinfoService.findByPhoneNumber(email,phoneNumber, pageSize, pageIndex);
		return "success";
	}
	public String modifyStatus(){
		JSONArray array = JSONArray.fromObject(myObj);
		String status = "";
		Integer fbid = -1;
		for(int i =0; i <array.size();i++){
			JSONObject object =  (JSONObject) array.get(i);
			status = (String) object.get("status");
			fbid = Integer.parseInt(object.get("fbid").toString());
			BackInfo b = new BackInfo();
			b.setFbid(fbid);
			b.setStatus(status);
			resultMap = backinfoService.modifyStatus(status, fbid);
		}
		
		return "success";
	}
	
	public String saveProcedureMessage(){
		resultMap = backinfoService.saveprocedureMessage(procedureMessage, fbid);
		return "success";
	}
	
	
	
	public String getMyObj() {
		return myObj;
	}

	public void setMyObj(String myObj) {
		this.myObj = myObj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProcedureMessage() {
		return procedureMessage;
	}

	public void setProcedureMessage(String procedureMessage) {
		this.procedureMessage = procedureMessage;
	}

	public Integer getFbid() {
		return fbid;
	}

	public void setFbid(Integer fbid) {
		this.fbid = fbid;
	}
	
}
