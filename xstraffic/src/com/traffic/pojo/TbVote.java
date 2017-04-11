package com.traffic.pojo;

import java.sql.Timestamp;

/**
 * TbVote entity. @author MyEclipse Persistence Tools
 */

public class TbVote implements java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String image;
	private String rule;
	private Timestamp createTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private String status;
	private String user;
	private Integer count;
	private Integer type;
	private String content;

	// Constructors

	/** default constructor */
	public TbVote() {
	}

	/** minimal constructor */
	public TbVote(String id, Integer count, Integer type) {
		this.id = id;
		this.count = count;
		this.type = type;
	}

	/** full constructor */
	public TbVote(String id, String title, String image, String rule,
			Timestamp createTime, Timestamp startTime, Timestamp endTime,
			String status, String user, Integer count, Integer type,
			String content) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.rule = rule;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.user = user;
		this.count = count;
		this.type = type;
		this.content = content;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}