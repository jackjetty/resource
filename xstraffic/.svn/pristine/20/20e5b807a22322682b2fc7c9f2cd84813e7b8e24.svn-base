package com.traffic.pojo;

/**
 * TbInfoVoiceId entity. @author MyEclipse Persistence Tools
 */

public class TbInfoVoiceId implements java.io.Serializable {

	// Fields

	private String processId;
	private String recordNo;
	private Integer voiceIndex;

	// Constructors

	/** default constructor */
	public TbInfoVoiceId() {
	}

	/** full constructor */
	public TbInfoVoiceId(String processId, String recordNo, Integer voiceIndex) {
		this.processId = processId;
		this.recordNo = recordNo;
		this.voiceIndex = voiceIndex;
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

	public Integer getVoiceIndex() {
		return this.voiceIndex;
	}

	public void setVoiceIndex(Integer voiceIndex) {
		this.voiceIndex = voiceIndex;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbInfoVoiceId))
			return false;
		TbInfoVoiceId castOther = (TbInfoVoiceId) other;

		return ((this.getProcessId() == castOther.getProcessId()) || (this
				.getProcessId() != null
				&& castOther.getProcessId() != null && this.getProcessId()
				.equals(castOther.getProcessId())))
				&& ((this.getRecordNo() == castOther.getRecordNo()) || (this
						.getRecordNo() != null
						&& castOther.getRecordNo() != null && this
						.getRecordNo().equals(castOther.getRecordNo())))
				&& ((this.getVoiceIndex() == castOther.getVoiceIndex()) || (this
						.getVoiceIndex() != null
						&& castOther.getVoiceIndex() != null && this
						.getVoiceIndex().equals(castOther.getVoiceIndex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProcessId() == null ? 0 : this.getProcessId().hashCode());
		result = 37 * result
				+ (getRecordNo() == null ? 0 : this.getRecordNo().hashCode());
		result = 37
				* result
				+ (getVoiceIndex() == null ? 0 : this.getVoiceIndex()
						.hashCode());
		return result;
	}

}