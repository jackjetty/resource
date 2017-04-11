package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_SYS_Menu")
public class Menu {
	
	
	
	private Integer menuId;

	private String grade;

	private String text;

	private String id;

	private String link;

	private String iconCls;

	
	public Menu(){}
	public Menu(String grade, String text, String id, String link,
			String iconCls) {
		this.grade = grade;
		this.text = text;
		this.id = id;
		this.link = link;
		this.iconCls = iconCls;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "MenuIdNext")
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

}
