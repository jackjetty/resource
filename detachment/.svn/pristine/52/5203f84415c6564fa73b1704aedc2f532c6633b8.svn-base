package com.detachment.pojo.vo;

import java.util.HashMap;

import com.detachment.pojo.TbDepartment;

public class TbDepartmentVo {

	private String departmentId;
	private String region;
	private String departmentName;
	
	private String parentId;
	private String departmentPhone;
	private String departmentAddress;
	private String remark;
	private String parentName;
	
	public TbDepartmentVo(TbDepartment department) {
		this.departmentId = department.getDepartmentId();
		this.departmentName = department.getDepartmentName();
		this.parentId = department.getParentId();
	}
	
	public TbDepartmentVo(TbDepartment department,HashMap<String,Object> map){
		this.departmentId = department.getDepartmentId();
		this.departmentName = department.getDepartmentName();
		this.parentId = department.getParentId();
		this.region=department.getRegion();
		this.departmentPhone=department.getDepartmentPhone();
		this.departmentAddress=department.getDepartmentAddress();
		this.remark=department.getRemark();
		if(department.getParentId()==null){
			this.parentName="";
		}else{
			this.parentName=(String) map.get(department.getParentId());
		}
		
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
