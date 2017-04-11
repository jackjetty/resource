package com.rising.mobilepayment.bean;

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

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

}
