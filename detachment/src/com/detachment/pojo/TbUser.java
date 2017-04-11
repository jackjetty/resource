package com.detachment.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */

public class TbUser implements java.io.Serializable {

	// Fields

	private String userId;
	private TbDepartment tbDepartment;
	private TbRole tbRole;
	private String password;
	private String userName;
	private String phoneNumber;
	private Set tbHtmls = new HashSet(0);
	private Set tbWeiUsers = new HashSet(0);
	private Set tbFormalAccidents = new HashSet(0);
	private Set tbCustReses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbUser() {
	}

	/** minimal constructor */
	public TbUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public TbUser(String userId, TbDepartment tbDepartment, TbRole tbRole,
			String password, String userName, String phoneNumber, Set tbHtmls,
			Set tbWeiUsers, Set tbFormalAccidents, Set tbCustReses) {
		this.userId = userId;
		this.tbDepartment = tbDepartment;
		this.tbRole = tbRole;
		this.password = password;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.tbHtmls = tbHtmls;
		this.tbWeiUsers = tbWeiUsers;
		this.tbFormalAccidents = tbFormalAccidents;
		this.tbCustReses = tbCustReses;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public TbDepartment getTbDepartment() {
		return this.tbDepartment;
	}

	public void setTbDepartment(TbDepartment tbDepartment) {
		this.tbDepartment = tbDepartment;
	}

	public TbRole getTbRole() {
		return this.tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set getTbHtmls() {
		return this.tbHtmls;
	}

	public void setTbHtmls(Set tbHtmls) {
		this.tbHtmls = tbHtmls;
	}

	public Set getTbWeiUsers() {
		return this.tbWeiUsers;
	}

	public void setTbWeiUsers(Set tbWeiUsers) {
		this.tbWeiUsers = tbWeiUsers;
	}

	public Set getTbFormalAccidents() {
		return this.tbFormalAccidents;
	}

	public void setTbFormalAccidents(Set tbFormalAccidents) {
		this.tbFormalAccidents = tbFormalAccidents;
	}

	public Set getTbCustReses() {
		return this.tbCustReses;
	}

	public void setTbCustReses(Set tbCustReses) {
		this.tbCustReses = tbCustReses;
	}

}