package com.traffic.pojo;

/**
 * TbRole entity. @author MyEclipse Persistence Tools
 */

public class TbRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String status;

	// Constructors

	/** default constructor */
	public TbRole() {
	}

	/** minimal constructor */
	public TbRole(String roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public TbRole(String roleId, String roleName,String status) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.status=status;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}