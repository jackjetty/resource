package com.rising.general.bean;

public class ReturnCode {
	private Integer id;
	private String returnCode;
	private String busId;
	private String codeMeaning;
	private String show;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getCodeMeaning() {
		return codeMeaning;
	}

	public void setCodeMeaning(String codeMeaning) {
		this.codeMeaning = codeMeaning;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}
