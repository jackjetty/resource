/**
 * ReturnCode.java
 * com.rising.mobilepayment.bean
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-27   上午9:24:24
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.bean;

public class ReturnCode {
	private String ReturnCode;
	private Integer BusId;
	private String CodeMeaning;
	private String Show;

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

	public Integer getBusId() {
		return BusId;
	}

	public void setBusId(Integer busId) {
		BusId = busId;
	}

	public String getCodeMeaning() {
		return CodeMeaning;
	}

	public void setCodeMeaning(String codeMeaning) {
		CodeMeaning = codeMeaning;
	}

	public String getShow() {
		return Show;
	}

	public void setShow(String show) {
		Show = show;
	}

}
