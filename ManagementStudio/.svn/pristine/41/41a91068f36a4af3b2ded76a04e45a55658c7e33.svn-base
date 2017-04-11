package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.Employee;

public class EmployeeVo {
	public Integer id;
	public String employeeNo;
	public String departmentId;
	public String employeeName;
	public String describe;
	
	public EmployeeVo(Employee em, HashMap<String, Object> map){
		this.id=em.getId();
		this.employeeNo=em.getEmployeeNo();
		this.departmentId=(String) map.get(em.getDepartmentId());
		this.employeeName=em.getEmployeeName();
		this.describe=em.getDescribe();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	
}
