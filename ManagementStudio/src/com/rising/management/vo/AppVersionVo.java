 package com.rising.management.vo;

import java.text.SimpleDateFormat;

import com.rising.management.bean.AppVersion;

public class AppVersionVo {

	private Integer AppVId;
	private Integer VersionCode;
	private String VersionName;
	private String PhoneType;
	private String CouldUse;
	private String PublishTime;
	private String Remark;
	private String APKUrl;

	public AppVersionVo(AppVersion app) {
		this.AppVId = app.getAppVId();
		this.VersionCode = app.getVersionCode();
		this.VersionName = app.getVersionName();
		this.CouldUse = app.getCouldUse();
		this.PublishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(app.getPublishTime());
		this.PhoneType = app.getPhoneType();
		this.Remark = app.getRemark();
		this.APKUrl=app.getAPKUrl();
	}

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

	public String getPublishTime() {
		return PublishTime;
	}

	public void setPublishTime(String publishTime) {
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

}
