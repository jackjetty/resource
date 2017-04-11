package com.rising.management.action;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rising.management.bean.PhoneRound;
import com.rising.management.service.PhoneRoundService;

@Controller("phoneRoundAction")
public class PhoneRoundAction {
	Log log = LogFactory.getLog(PhoneRoundAction.class);
	@Autowired
	PhoneRoundService phoneRoundService;
	private String ids;
	private Integer pageSize;
	private Integer pageIndex;
	private String phoneRound;
	private String status;
	private String StatusJSON;
	private HashMap<String,Object> resultMap;
	
	public String doPhoneRound(){
		return "success";
	}
	public String getPhoneRoundInfo(){
		resultMap = phoneRoundService.getPhoneRoundService(pageSize, pageIndex);
		return "success";
	}
	public String addPhoneRound(){
		PhoneRound pr = new PhoneRound();
		pr.setPhoneRound(phoneRound);
		pr.setStatus(status);
		resultMap = phoneRoundService.addPhoneRound(pr);
		return "success";
	}
	public String removePhoneRound(){
		resultMap = phoneRoundService.deleteByIds(ids);
		return "success";
	}
	public String updatePhoneRound(){
		JSONArray array = JSONArray.fromObject(StatusJSON);
		String status = "";
		Integer id = -1;
		for(int i =0; i <array.size();i++){
			JSONObject object =  (JSONObject) array.get(i);
			status = (String) object.get("status");
			id = Integer.parseInt(object.get("id").toString());
			PhoneRound pr = new PhoneRound();
			pr.setId(id);
			pr.setStatus(status);
			resultMap = phoneRoundService.updatePhoneRound(status, id);
		}
		
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
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhoneRound() {
		return phoneRound;
	}
	public void setPhoneRound(String phoneRound) {
		this.phoneRound = phoneRound;
	}
	public String getStatusJSON() {
		return StatusJSON;
	}
	public void setStatusJSON(String statusJSON) {
		StatusJSON = statusJSON;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

}
