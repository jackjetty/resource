package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.TakingPhotosService;


@Controller("takingPhotosAction")
public class TakingPhotosAction {

	private String takingPhotosId;
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String takingPhoneNumber;
	private String nickName;
	private String managerName;
	private String handyPhotoState;
	private HashMap<String, Object> result;
	@Autowired
	TakingPhotosService takingPhotosService;

	public String doTakingPhotos() {
		return "success";
	}

	public String getTakingPhotos() {
		result = takingPhotosService.getTakingPhotos(startTime, endTime,
				takingPhoneNumber, nickName, pageSize, pageIndex,handyPhotoState);
		return "success";
	}

	public String getTpAddress() {
		result = takingPhotosService.getTbAddress(takingPhotosId);
		return "success";
	}

	public String updateState() {
		result = takingPhotosService.updateState(takingPhotosId, managerName);
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

	public String getHandyPhotoState() {
		return handyPhotoState;
	}

	public void setHandyPhotoState(String handyPhotoState) {
		this.handyPhotoState = handyPhotoState;
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

	public String getTakingPhoneNumber() {
		return takingPhoneNumber;
	}

	public String getTakingPhotosId() {
		return takingPhotosId;
	}

	public void setTakingPhotosId(String takingPhotosId) {
		this.takingPhotosId = takingPhotosId;
	}

	public void setTakingPhoneNumber(String takingPhoneNumber) {
		this.takingPhoneNumber = takingPhoneNumber;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}
