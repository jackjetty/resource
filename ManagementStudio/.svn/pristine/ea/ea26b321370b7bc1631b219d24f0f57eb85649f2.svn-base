package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_HOTGAME")
public class HotGame {
	private Integer id;
	private String packageName;
	private String title;
	private Integer star;
	private String fileSize;
	private String icoName;
	private byte[] icoImg1;
	private byte[] icoImg2;
	private String apkUrl;
	private String versionCode;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "HotGameIdNext")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getIcoName() {
		return icoName;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public byte[] getIcoImg1() {
		return icoImg1;
	}

	public void setIcoImg1(byte[] icoImg1) {
		this.icoImg1 = icoImg1;
	}

	public byte[] getIcoImg2() {
		return icoImg2;
	}

	public void setIcoImg2(byte[] icoImg2) {
		this.icoImg2 = icoImg2;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

}
