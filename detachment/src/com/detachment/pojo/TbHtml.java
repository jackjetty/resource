package com.detachment.pojo;

import java.sql.Timestamp;

/**
 * TbHtml entity. @author MyEclipse Persistence Tools
 */

public class TbHtml implements java.io.Serializable {

	// Fields

	private Integer htmlId;
	private TbHtmlType tbHtmlType;
	private TbUser tbUser;
	private String htmlTitle;
	private String htmlDes;
	private String htmlContent;
	private Boolean valid;
	private Timestamp publishTime;
	private Boolean htmlVip;

	// Constructors

	/** default constructor */
	public TbHtml() {
	}

	/** minimal constructor */
	public TbHtml(Integer htmlId) {
		this.htmlId = htmlId;
	}

	/** full constructor */
	public TbHtml(Integer htmlId, TbHtmlType tbHtmlType, TbUser tbUser,
			String htmlTitle, String htmlDes, String htmlContent,
			Boolean valid, Timestamp publishTime, Boolean htmlVip) {
		this.htmlId = htmlId;
		this.tbHtmlType = tbHtmlType;
		this.tbUser = tbUser;
		this.htmlTitle = htmlTitle;
		this.htmlDes = htmlDes;
		this.htmlContent = htmlContent;
		this.valid = valid;
		this.publishTime = publishTime;
		this.htmlVip = htmlVip;
	}

	// Property accessors

	public Integer getHtmlId() {
		return this.htmlId;
	}

	public void setHtmlId(Integer htmlId) {
		this.htmlId = htmlId;
	}

	public TbHtmlType getTbHtmlType() {
		return this.tbHtmlType;
	}

	public void setTbHtmlType(TbHtmlType tbHtmlType) {
		this.tbHtmlType = tbHtmlType;
	}

	public TbUser getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public String getHtmlTitle() {
		return this.htmlTitle;
	}

	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}

	public String getHtmlDes() {
		return this.htmlDes;
	}

	public void setHtmlDes(String htmlDes) {
		this.htmlDes = htmlDes;
	}

	public String getHtmlContent() {
		return this.htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Boolean getHtmlVip() {
		return this.htmlVip;
	}

	public void setHtmlVip(Boolean htmlVip) {
		this.htmlVip = htmlVip;
	}

}