package com.traffic.wei.store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import com.traffic.wei.store.info.LocationStoreInfo;
import com.traffic.wei.store.info.PictureStoreInfo;
import com.traffic.wei.store.info.VoiceStoreInfo;
public class CarMoveStoreBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5442177775004074731L;
	private String carMoveId;
	private String reportPhoneNumber; 
	private String carNumber; 
	private LocationStoreInfo locationInfo;
	private ArrayList <PictureStoreInfo> pictureInfoList;
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