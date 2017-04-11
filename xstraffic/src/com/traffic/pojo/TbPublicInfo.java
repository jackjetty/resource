package com.traffic.pojo;

import java.util.Date;

public class TbPublicInfo implements java.io.Serializable {
	private Integer id;
	private String title;
	private String publicInfo;
	private Date publicTime;
	private String status;
	private String massObject;
	private String publicType;
	private Integer publicIndex;
	private String description;

	public TbPublicInfo() {
	};

	/** minimal constructor */
	public TbPublicInfo(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublicInfo() {
		return publicInfo;
	}

	public void setPublicInfo(String publicInfo) {
		this.publicInfo = publicInfo;
	}

	public Date getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMassObject() {
		return massObject;
	}

	public void setMassObject(String massObject) {
		this.massObject = massObject;
	}

	public String getPublicType() {
		return publicType;
	}

	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}

	public Integer getPublicIndex() {
		return publicIndex;
	}

	public void setPublicIndex(Integer publicIndex) {
		this.publicIndex = publicIndex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
