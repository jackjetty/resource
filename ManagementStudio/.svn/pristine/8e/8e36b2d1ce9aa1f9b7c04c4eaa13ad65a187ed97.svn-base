package com.rising.management.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_AppVersion")
public class AppVersion {

	private Integer AppVId;
	private Integer VersionCode;
	private String VersionName;
	private String PhoneType;
	private String CouldUse;
	private Date PublishTime;
	private String Remark;
	private String APKUrl;
	private String Forbidden;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "AppVIdNext")
	public Integer getAppVId() {
		return AppVId;
	}

	public void setAppVId(Integer appVId) {
		AppVId = appVId;
	}

	public Integer getVersionCode() {
		return VersionCode;
	}

	public void setVersionCode(Integer versionCode) {
		VersionCode = versionCode;
	}

	public String getVersionName() {
		return VersionName;
	}

	public void setVersionName(String versionName) {
		VersionName = versionName;
	}

	public String getPhoneType() {
		return PhoneType;
	}

	public void setPhoneType(String phoneType) {
		PhoneType = phoneType;
	}

	public String getCouldUse() {
		return CouldUse;
	}

	public void setCouldUse(String couldUse) {
		CouldUse = couldUse;
	}

	public Date getPublishTime() {
		return PublishTime;
	}

	public void setPublishTime(Date publishTime) {
		PublishTime = publishTime;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getAPKUrl() {
		return APKUrl;
	}

	public void setAPKUrl(String aPKUrl) {
		APKUrl = aPKUrl;
	}

	public String getForbidden() {
		return Forbidden;
	}

	public void setForbidden(String forbidden) {
		Forbidden = forbidden;
	}

}
