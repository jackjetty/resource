package com.detachment.wei.bean.store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import com.detachment.wei.bean.store.info.LocationStoreInfo;
import com.detachment.wei.bean.store.info.PictureStoreInfo;
import com.detachment.wei.bean.store.info.VoiceStoreInfo;
public class CarMoveStoreBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5442177775004074731L;
	private String carMoveId;
	
	private String reportPhoneNumber; 
	private String carNumber; 
	private String carType;
	private LocationStoreInfo locationInfo;
	private ArrayList <PictureStoreInfo> pictureInfoList;
	
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarMoveId() {
		return carMoveId;
	}
	public void setCarMoveId(String carMoveId) {
		this.carMoveId = carMoveId;
	}
	public String getReportPhoneNumber() {
		return reportPhoneNumber;
	}
	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	 
	public LocationStoreInfo getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(LocationStoreInfo locationInfo) {
		this.locationInfo = locationInfo;
	}
	public ArrayList<PictureStoreInfo> getPictureInfoList() {
		return pictureInfoList;
	}
	public void setPictureInfoList(ArrayList<PictureStoreInfo> pictureInfoList) {
		this.pictureInfoList = pictureInfoList;
	}  
}