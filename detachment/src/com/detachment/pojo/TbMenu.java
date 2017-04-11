package com.detachment.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TbMenu entity. @author MyEclipse Persistence Tools
 */

public class TbMenu implements java.io.Serializable {

	// Fields

	private String menuId;
	private String grade;
	private String text;
	private String link;
	private String iconcls;
	private Set tbRole2menus = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbMenu() {
	}

	/** minimal constructor */
	public TbMenu(String menuId) {
		this.menuId = menuId;
	}

	/** full constructor */
	public TbMenu(String menuId, String grade, String text, String link,
			String iconcls, Set tbRole2menus) {
		this.menuId = menuId;
		this.grade = grade;
		this.text = text;
		this.link = link;
		this.iconcls = iconcls;
		this.tbRole2menus = tbRole2menus;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public Set getTbRole2menus() {
		return this.tbRole2menus;
	}

	public void setTbRole2menus(Set tbRole2menus) {
		this.tbRole2menus = tbRole2menus;
	}

}