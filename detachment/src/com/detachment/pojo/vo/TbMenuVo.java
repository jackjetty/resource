package com.detachment.pojo.vo;

import com.detachment.pojo.TbMenu;

public class TbMenuVo {
	private String menuId;
	private String grade;
	private String text;
	private String link;
	private String iconcls;
	
	public TbMenuVo(TbMenu tm){
		this.menuId=tm.getMenuId();
		this.grade=tm.getGrade();
		this.text=tm.getText();
		this.link=tm.getLink();
		this.iconcls=tm.getIconcls();
	}
	
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	
	
	
}
