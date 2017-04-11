package com.rising.management.action;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.AppStartPicture;
import com.rising.management.common.FileToByteUtil;
import com.rising.management.service.AppStartPictureService;

@Controller("appStartPictureAction")
public class AppStartPictureAction {
	Log log = LogFactory.getLog(AppStartPictureAction.class);

	private Integer pageSize;
	private Integer pageIndex;
	private String pictureName;
	private String startTime;
	private String endTime;
	private File image1;
	private File image2;
	private File image3;
	private Integer id;
	private String pictureIds;

	@Autowired
	AppStartPictureService appStartPictureService;

	private HashMap<String, Object> resultMap;

	public String doAppStartPicture() {
		return "success";
	}

	public String getAppStartPicture() {
		resultMap = appStartPictureService.getAppStartPicture(pictureName,
				startTime, endTime, pageSize, pageIndex);
		return "success";
	}

	public String addAppStartPicture() {
		AppStartPicture asp = new AppStartPicture();
		asp.setPictureName(pictureName);
		asp.setImage1(FileToByteUtil.getFileToByte(image1));
		asp.setImage2(FileToByteUtil.getFileToByte(image2));
		asp.setImage3(FileToByteUtil.getFileToByte(image3));
		asp.setOperate("结束");
		resultMap = appStartPictureService.addAppStartPicture(asp);
		return "success";
	}

	public String updateAppStartPicture() {
		AppStartPicture asp = appStartPictureService.getAppStartPictureById(id);
		asp.setPictureName(pictureName);
		asp.setStartTime(startTime);
		asp.setEndTime(endTime);
		if (image1 != null) {
			asp.setImage1(FileToByteUtil.getFileToByte(image1));
		}
		if (image2 != null) {
			asp.setImage2(FileToByteUtil.getFileToByte(image2));
		}
		if (image3 != null) {
			asp.setImage3(FileToByteUtil.getFileToByte(image3));
		}
		resultMap = appStartPictureService.updateAppStartPicture(asp);
		return "success";
	}

	public String removeAppStartPicture() {
		resultMap = appStartPictureService.removeAppStartPicture(pictureIds);
		return "success";
	}

	public String changeOperate() {
		resultMap = appStartPictureService.changeOperate(id);
		return "success";
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public File getImage1() {
		return image1;
	}

	public void setImage1(File image1) {
		this.image1 = image1;
	}

	public File getImage2() {
		return image2;
	}

	public void setImage2(File image2) {
		this.image2 = image2;
	}

	public File getImage3() {
		return image3;
	}

	public void setImage3(File image3) {
		this.image3 = image3;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPictureIds() {
		return pictureIds;
	}

	public void setPictureIds(String pictureIds) {
		this.pictureIds = pictureIds;
	}

}
