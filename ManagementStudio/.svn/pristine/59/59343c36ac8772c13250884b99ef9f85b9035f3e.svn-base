package com.rising.management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RS_APPSTARTPICTURE")
public class AppStartPicture {
	public Integer id;
	public String pictureName;
	public String startTime;
	public String endTime;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private String operate;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdNext")
	@SequenceGenerator(name = "IdNext", sequenceName = "AppStartPictureIdNext")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}
