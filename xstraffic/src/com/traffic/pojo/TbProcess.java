package com.traffic.pojo;

/**
 * TbProcess entity. @author MyEclipse Persistence Tools
 */

public class TbProcess implements java.io.Serializable {

	// Fields

	private String processId;
	private String processName;
	private Boolean using;
	private String prefix;
	private String remark;

	// Constructors

	/** default constructor */
	public TbProcess() {
	}

	/** minimal constructor */
	public TbProcess(String processId) {
		this.processId = processId;
	}

	/** full constructor */
	public TbProcess(String processId, String processName, Boolean using,
			String prefix, String remark) {
		this.processId = processId;
		this.processName = processName;
		this.using = using;
		this.prefix = prefix;
		this.remark = remark;
	}

	// Property accessors

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Boolean getUsing() {
		return this.using;
	}

	public void setUsing(Boolean using) {
		this.using = using;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}