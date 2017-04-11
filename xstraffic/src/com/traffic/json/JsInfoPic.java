package com.traffic.json;
import java.io.Serializable;
public class JsInfoPic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5981141809252766005L;
	private String processId;
	private String recordNo;
	private int picIndex;
	private int rotateDegree;
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public int getPicIndex() {
		return picIndex;
	}
	public void setPicIndex(int picIndex) {
		this.picIndex = picIndex;
	}
	public int getRotateDegree() {
		return rotateDegree;
	}
	public void setRotateDegree(int rotateDegree) {
		this.rotateDegree = rotateDegree;
	}
	
	
}