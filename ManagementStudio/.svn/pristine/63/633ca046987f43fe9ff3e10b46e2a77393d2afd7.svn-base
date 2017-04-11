package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.ReturnCode;

public class ReturnCodeVo {
	private Integer id;
	private String bType;
	private String returnCode;
	private String codeMeaning;
	private String show;

	public ReturnCodeVo(ReturnCode code, HashMap<Integer, String> map) {
		this.id = code.getId();
		this.bType = map.get(code.getBusId());
		this.codeMeaning = code.getCodeMeaning();
		this.returnCode = code.getReturnCode();
		this.show = code.getShow();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
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
