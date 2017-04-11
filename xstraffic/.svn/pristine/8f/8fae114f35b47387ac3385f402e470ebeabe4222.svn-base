package com.traffic.pojo;

/**
 * TbAccidentOpnId entity. @author MyEclipse Persistence Tools
 */

public class TbAccidentOpnId implements java.io.Serializable {

	// Fields

	private String accidentId;
	private String opnType;

	// Constructors

	/** default constructor */
	public TbAccidentOpnId() {
	}

	/** full constructor */
	public TbAccidentOpnId(String accidentId, String opnType) {
		this.accidentId = accidentId;
		this.opnType = opnType;
	}

	// Property accessors

	public String getAccidentId() {
		return this.accidentId;
	}

	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	public String getOpnType() {
		return this.opnType;
	}

	public void setOpnType(String opnType) {
		this.opnType = opnType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbAccidentOpnId))
			return false;
		TbAccidentOpnId castOther = (TbAccidentOpnId) other;

		return ((this.getAccidentId() == castOther.getAccidentId()) || (this
				.getAccidentId() != null
				&& castOther.getAccidentId() != null && this.getAccidentId()
				.equals(castOther.getAccidentId())))
				&& ((this.getOpnType() == castOther.getOpnType()) || (this
						.getOpnType() != null
						&& castOther.getOpnType() != null && this.getOpnType()
						.equals(castOther.getOpnType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAccidentId() == null ? 0 : this.getAccidentId()
						.hashCode());
		result = 37 * result
				+ (getOpnType() == null ? 0 : this.getOpnType().hashCode());
		return result;
	}

}