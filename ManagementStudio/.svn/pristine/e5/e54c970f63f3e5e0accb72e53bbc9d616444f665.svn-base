package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Department;
import com.rising.management.service.DepartmentService;
import com.rising.management.vo.TreeDepartment;

@Controller("departmentTreeAction")
public class DepartmentTreeAction {
	Log log = LogFactory.getLog(DepartmentTreeAction.class);
	
	@Autowired
	DepartmentService departmentService;
	
	
	private HashMap<String, Object> resultMap;
	
	private ArrayList<TreeDepartment> tree;
	
	private String departmentName;
	private String superId;
	private String describe;
	private String departmentNo;
	private Integer id;
	
	public String doCompanyTree(){
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public String getAllDepartment(){
		resultMap =departmentService.getAllDepartment();
		tree=(ArrayList<TreeDepartment>) resultMap.get("TreeDepartment");
		if (tree == null) {
			return "failed";
		} else
			return "success";
	}
	public String getDepartmentNameAndNo(){
		resultMap=departmentService.getDepartmentNameAndNo();
		return "success";
	}
	
	public String getDepartmentByName(){
		resultMap=departmentService.getDepartmentByName(departmentName);
		return "success";
	}
	public String addDepartment(){
		Department dt=new Department();
		dt.setDepartmentName(departmentName);
		dt.setDepartmentNo(departmentNo);
		dt.setSuperId(superId);
		dt.setDescribe(describe);
		resultMap=departmentService.addDepartment(dt);
		return "success";
	}
	
	public String updateDepartment(){
		Department dt=new Department();
		dt.setId(id);
		dt.setDepartmentName(departmentName);
		dt.setDepartmentNo(departmentNo);
		dt.setSuperId(superId);
		dt.setDescribe(describe);
		resultMap=departmentService.updateDepartment(dt);
		return "success";
	}
	
	public String removeDepartment(){
		resultMap=departmentService.removeDepartment(departmentNo);
		return "success";
	}
	

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ArrayList<TreeDepartment> getTree() {
		return tree;
	}

	public void setTree(ArrayList<TreeDepartment> tree) {
		this.tree = tree;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	


	
}
