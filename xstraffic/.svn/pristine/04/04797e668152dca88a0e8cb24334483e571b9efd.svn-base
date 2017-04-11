package com.traffic.pojo;

/**
 * TbInfoTextId entity. @author MyEclipse Persistence Tools
 */

public class TbInfoTextId implements java.io.Serializable {

	// Fields 
	private String processId;
	private String recordNo;
	private Integer textIndex; 
	// Constructors

	/** default constructor */
	public TbInfoTextId() {
	}

	/** full constructor */
	public TbInfoTextId(String processId, String recordNo, Integer textIndex) {
		this.processId = processId;
		this.recordNo = recordNo;
		this.textIndex = textIndex;
	}

	// Property accessors

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRecordNo() {
		return this.recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public Integer getTextIndex() {
		return this.textIndex;
	}

	public void setTextIndex(Integer textIndex) {
		this.textIndex = textIndex;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbInfoTextId))
			return false;
		TbInfoTextId castOther = (TbInfoTextId) other;

		return ((this.getProcessId() == castOther.getProcessId()) || (this
				.getProcessId() != null
				&& castOther.getProcessId() != null && this.getProcessId()
				.equals(castOther.getProcessId())))
				&& ((this.getRecordNo() == castOther.getRecordNo()) || (this
						.getRecordNo() != null
						&& castOther.getRecordNo() != null && this
						.getRecordNo().equals(castOther.getRecordNo())))
				&& ((this.getTextIndex() == castOther.getTextIndex()) || (this
						.getTextIndex() != null
						&& castOther.getTextIndex() != null && this
						.getTextIndex().equals(castOther.getTextIndex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProcessId() == null ? 0 : this.getProcessId().hashCode());
		result = 37 * result
				+ (getRecordNo() == null ? 0 : this.getRecordNo().hashCode());
		result = 37 * result
				+ (getTextIndex() == null ? 0 : this.getTextIndex().hashCode());
		return result;
	}

}