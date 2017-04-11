package com.rising.mobilepayment.bean;

import java.util.Date;

public class AppStartPicture {

	private Integer Id;

	private String PictureName;

	private Date StartTime;

	private Date EndTime;

	private byte[] Image1;
	
	private byte [] Image2;
	
	private byte[] Image3;

	private String Operate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getPictureName() {
		return PictureName;
	}

	public void setPictureName(String pictureName) {
		PictureName = pictureName;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public void setImage1(byte[] image1) {
		Image1 = image1;
	}

	public byte[] getImage2() {
		return Image2;
	}

	public void setImage2(byte[] image2) {
		Image2 = image2;
	}

	public byte[] getImage3() {
		return Image3;
	}

	public void setImage3(byte[] image3) {
		Image3 = image3;
	}

	public byte[] getImage1() {
		return Image1;
	}

	public String getOperate() {
		return Operate;
	}

	public void setOperate(String operate) {
		Operate = operate;
	}

}
