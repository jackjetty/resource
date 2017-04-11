package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.vo.TbDepartmentVo;
import com.detachment.web.service.DepartmentService;

@Controller("departmentAction")
public class DepartmentAction {

	private ArrayList<TbDepartmentVo> atd;
	private HashMap<String,Object> result;
	private Integer pageSize;
	private Integer pageIndex;
	private String parentId;
	private String departmentId;
	private String departmentName;
	private String region;
	private String departmentPhone;
	private String departmentAddress;
	private String remark;
	private String departmentIds;
	
	

	@Autowired
	DepartmentService departmentService;

	public String getDepartmentMap() {
		atd = departmentService.getAllDepartment();
		return "success";
	}
	
	public String doDepartment(){
		return "success";
	}
	
	public String getAllDepartment(){
		result=departmentService.getAllDepartments(pageSize, pageIndex);
		return "success";
	}
	public String getDepartmentIdAndName(){
		result=departmentService.getDepartmentIDName();
		return "success";
	}
	public String addDepartment(){
		TbDepartment td=new TbDepartment();
		td.setDepartmentAddress(departmentAddress);
		td.setDepartmentId(departmentId);
		td.setDepartmentName(departmentName);
		td.setDepartmentPhone(departmentPhone);
		td.setParentId(parentId);
		td.setRegion(region);
		td.setRemark(remark);
		result=departmentService.addDepartment(td);
		return "success";
	}
	public String updateDepartment(){
		TbDepartment td=departmentService.getTbDepartmentById(departmentId);
		td.setDepartmentAddress(departmentAddress);
		td.setDepartmentName(departmentName);
		td.setDepartmentPhone(departmentPhone);
		td.setParentId(parentId);
		td.setRegion(region);
		td.setRemark(remark);
		result=departmentService.updateDepartment(td);
		return "success";
	}
	public String removeDepartment(){
		result=departmentService.removeDepartment(departmentIds);
		return "success";
	}
	

	public ArrayList<TbDepartmentVo> getAtd() {
		return atd;
	}

	public void setAtd(ArrayList<TbDepartmentVo> atd) {
		this.atd = atd;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartmentPhone() {
		return departmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	public String getDepartmentAddress() {
		return departmentAddress;
	}

	public void setDepartmentAddress(String departmentAddress) {
		this.departmentAddress = departmentAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	

}
