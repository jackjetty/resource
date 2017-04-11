package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Employee;
import com.rising.management.service.EmployeeService;


@Controller("employeeAction")
public class EmployeeAction {
	Log log = LogFactory.getLog(EmployeeAction.class);
	
	@Autowired
	EmployeeService employeeService;
	
	private HashMap<String, Object> resultMap;
	private Integer pageSize;
	private Integer pageIndex;
	private String departmentNo;
	private String employeeNo;
	private String departmentId;
	private String employeeName;
	private String describe; 
	private Integer id;
	private String employeeIds;
	
	public String getEmployee(){
		resultMap=employeeService.getEmployeeByDepartmentNo(departmentNo, pageSize, pageIndex);
		return "success";
	}
	
	public String addEmployee(){
		Employee emp=new Employee();
		emp.setDepartmentId(departmentId);
		emp.setDescribe(describe);
		emp.setEmployeeName(employeeName);
		emp.setEmployeeNo(employeeNo);
		resultMap=employeeService.addEmployee(emp);
		return "success";
	}
	
	public String updateEmployee(){
		Employee emp=new Employee();
		emp.setDepartmentId(departmentId);
		emp.setDescribe(describe);
		emp.setEmployeeName(employeeName);
		emp.setEmployeeNo(employeeNo);
		emp.setId(id);
		resultMap=employeeService.updateEmoloyee(emp);
		return "success";
	}
	
	public String removeEmployee(){
		resultMap=employeeService.removeEmployee(employeeIds);
		return "success";
	}
	
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
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

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}
	
	
}
