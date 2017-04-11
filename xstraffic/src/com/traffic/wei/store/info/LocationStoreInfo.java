package com.traffic.wei.store.info;

import java.io.Serializable;

public class  LocationStoreInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -235400770500514540L;
	private String address;
	private String locationX;
	private String locationY;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	} 
	
}