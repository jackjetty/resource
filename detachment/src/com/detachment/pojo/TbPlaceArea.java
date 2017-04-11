package com.detachment.pojo;

public class TbPlaceArea implements java.io.Serializable {

	private static final long serialVersionUID = 9094684453600509581L;

	private Integer infoId;

	private Integer placeId;

	private String latitude;

	private String longitude;
	
	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
