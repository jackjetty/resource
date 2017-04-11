package com.detachment.pojo.vo;

public class TbAccidentStatisticsVo {
	private String departmentName;
	private Integer pass;
	private Integer evil;
	private Integer report;
	private Integer pending;
	private Integer check;
	private Integer other;
	private Integer total;
	
	public TbAccidentStatisticsVo(){}
	
	public TbAccidentStatisticsVo(String departmentName, Integer pass,
			Integer evil, Integer report, Integer pending, Integer check,
			Integer other, Integer total) {
		this.departmentName = departmentName;
		this.pass = pass;
		this.evil = evil;
		this.report = report;
		this.pending = pending;
		this.check = check;
		this.other = other;
		this.total = total;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

	public Integer getEvil() {
		return evil;
	}

	public void setEvil(Integer evil) {
		this.evil = evil;
	}

	public Integer getReport() {
		return report;
	}

	public void setReport(Integer report) {
		this.report = report;
	}

	public Integer getPending() {
		return pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public Integer getOther() {
		return other;
	}

	public void setOther(Integer other) {
		this.other = other;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
}
