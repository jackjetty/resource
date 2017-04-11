package com.traffic.pojo;

/**
 * TbInfoPicId entity. @author MyEclipse Persistence Tools
 */

public class TbInfoPicId implements java.io.Serializable {

	// Fields

	private String processId;
	private String recordNo;
	private Integer picIndex;

	// Constructors

	/** default constructor */
	public TbInfoPicId() {
	}

	/** full constructor */
	public TbInfoPicId(String processId, String recordNo, Integer picIndex) {
		this.processId = processId;
		this.recordNo = recordNo;
		this.picIndex = picIndex;
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

	public Integer getPicIndex() {
		return this.picIndex;
	}

	public void setPicIndex(Integer picIndex) {
		this.picIndex = picIndex;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbInfoPicId))
			return false;
		TbInfoPicId castOther = (TbInfoPicId) other;

		return ((this.getProcessId() == castOther.getProcessId()) || (this
				.getProcessId() != null
				&& castOther.getProcessId() != null && this.getProcessId()
				.equals(castOther.getProcessId())))
				&& ((this.getRecordNo() == castOther.getRecordNo()) || (this
						.getRecordNo() != null
						&& castOther.getRecordNo() != null && this
						.getRecordNo().equals(castOther.getRecordNo())))
				&& ((this.getPicIndex() == castOther.getPicIndex()) || (this
						.getPicIndex() != null
						&& castOther.getPicIndex() != null && this
						.getPicIndex().equals(castOther.getPicIndex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProcessId() == null ? 0 : this.getProcessId().hashCode());
		result = 37 * result
				+ (getRecordNo() == null ? 0 : this.getRecordNo().hashCode());
		result = 37 * result
				+ (getPicIndex() == null ? 0 : this.getPicIndex().hashCode());
		return result;
	}

}