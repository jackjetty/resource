package com.detachment.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TbDepartment entity. @author MyEclipse Persistence Tools
 */

public class TbDepartment implements java.io.Serializable {

	// Fields

	private String departmentId;
	private String region;
	private String departmentName;
	private String parentId;
	private String departmentPhone;
	private String departmentAddress;
	private String remark;
	private Set tbFormalAccidents = new HashSet(0);
	private Set tbUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbDepartment() {
	}

	/** minimal constructor */
	public TbDepartment(String departmentId) {
		this.departmentId = departmentId;
	}

	/** full constructor */
	public TbDepartment(String departmentId, String region,
			String departmentName, String parentId, String departmentPhone,
			String departmentAddress, String remark, Set tbFormalAccidents,
			Set tbUsers) {
		this.departmentId = departmentId;
		this.region = region;
		this.departmentName = departmentName;
		this.parentId = parentId;
		this.departmentPhone = departmentPhone;
		this.departmentAddress = departmentAddress;
		this.remark = remark;
		this.tbFormalAccidents = tbFormalAccidents;
		this.tbUsers = tbUsers;
	}

	// Property accessors

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDepartmentPhone() {
		return this.departmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	public String getDepartmentAddress() {
		return this.departmentAddress;
	}

	public void setDepartmentAddress(String departmentAddress) {
		this.departmentAddress = departmentAddress;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getTbFormalAccidents() {
		return this.tbFormalAccidents;
	}

	public void setTbFormalAccidents(Set tbFormalAccidents) {
		this.tbFormalAccidents = tbFormalAccidents;
	}

	public Set getTbUsers() {
		return this.tbUsers;
	}

	public void setTbUsers(Set tbUsers) {
		this.tbUsers = tbUsers;
	}

}