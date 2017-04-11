package com.detachment.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TbRole entity. @author MyEclipse Persistence Tools
 */

public class TbRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private Boolean hidden;
	private Set tbRole2menus = new HashSet(0);
	private Set tbUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbRole() {
	}

	/** minimal constructor */
	public TbRole(String roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public TbRole(String roleId, String roleName, Boolean hidden,
			Set tbRole2menus, Set tbUsers) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.hidden = hidden;
		this.tbRole2menus = tbRole2menus;
		this.tbUsers = tbUsers;
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

	public Boolean getHidden() {
		return this.hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Set getTbRole2menus() {
		return this.tbRole2menus;
	}

	public void setTbRole2menus(Set tbRole2menus) {
		this.tbRole2menus = tbRole2menus;
	}

	public Set getTbUsers() {
		return this.tbUsers;
	}

	public void setTbUsers(Set tbUsers) {
		this.tbUsers = tbUsers;
	}

}