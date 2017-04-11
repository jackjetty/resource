package com.rising.management.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.rising.management.bean.DownloadRecord;

public class DownloadRecordVo {
	private Integer id;
	private String fromWay;
	private String fromWayDetail;
	private String downloadTime;
	private String ipAddress;

	public DownloadRecordVo(DownloadRecord dr,HashMap<String,Object> map) {
		this.id = dr.getId();
		this.fromWay = dr.getFromWay();
		this.fromWayDetail = (String) map.get(dr.getFromWay());
		this.downloadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(dr.getDownloadTime());
		this.ipAddress = dr.getIpAddress();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromWay() {
		return fromWay;
	}

	public void setFromWay(String fromWay) {
		this.fromWay = fromWay;
	}

	public String getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFromWayDetail() {
		return fromWayDetail;
	}

	public void setFromWayDetail(String fromWayDetail) {
		this.fromWayDetail = fromWayDetail;
	}

}
