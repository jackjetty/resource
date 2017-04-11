package com.traffic.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbOrganizationInfo;
import com.traffic.web.service.OrganizationInfoService;

@Controller("organizationInfoAction")
public class OrganizationInfoAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String organizationName;
	private String alarmPhone;
	private String complaintPhone;
	private String address;
	private Integer id;
	private String ids;
	
	private HashMap<String, Object> result;
	
	private ArrayList<TbOrganizationInfo> re;
	
	
	@Autowired
	OrganizationInfoService organizationInfoService;
	
	
	
	
	public String doOrganizationInfo(){
		return "success";
	}
	
	public String getOrganizationInfo(){
		result=organizationInfoService.getOrganizationInfo(organizationName, pageSize, pageIndex);
		return "success";
	}
	
	public String addOrganizationInfo(){
		TbOrganizationInfo oi=new TbOrganizationInfo();
		oi.setOrganizationName(organizationName);
		oi.setAddress(address);
		oi.setAlarmPhone(alarmPhone);
		oi.setComplaintPhone(complaintPhone);
		result=organizationInfoService.addOrganizationInfo(oi);
		return "success";
	}
	
	public String updateOrganizationInfo(){
		TbOrganizationInfo oi=organizationInfoService.getOrganizationInfoById(id);
		oi.setAddress(address);
		oi.setAlarmPhone(alarmPhone);
		oi.setComplaintPhone(complaintPhone);
		oi.setOrganizationName(organizationName);
		result=organizationInfoService.updateOrganizationInfo(oi);
		return "success";
	}
	
	public String removeOrganizationInfo(){
		result=organizationInfoService.removeOrganizationInfo(ids);
		return "success";
	}
	
	public String doMechanismInfo(){
		return "success";
	}
	
	public String getMechanismInfo(){
		re=organizationInfoService.getMechanismInfo();
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
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getAlarmPhone() {
		return alarmPhone;
	}

	public void setAlarmPhone(String alarmPhone) {
		this.alarmPhone = alarmPhone;
	}

	public String getComplaintPhone() {
		return complaintPhone;
	}

	public void setComplaintPhone(String complaintPhone) {
		this.complaintPhone = complaintPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public ArrayList<TbOrganizationInfo> getRe() {
		return re;
	}

	public void setRe(ArrayList<TbOrganizationInfo> re) {
		this.re = re;
	}
	
	
	
	
}
