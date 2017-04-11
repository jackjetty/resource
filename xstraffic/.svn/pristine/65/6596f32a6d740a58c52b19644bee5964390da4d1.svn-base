package com.traffic.pojo;

/**
 * TbProcedureId entity. @author MyEclipse Persistence Tools
 */

public class TbProcedureId implements java.io.Serializable {

	// Fields

	private String processId;
	private String procedureId;

	// Constructors

	/** default constructor */
	public TbProcedureId() {
	}

	/** full constructor */
	public TbProcedureId(String processId, String procedureId) {
		this.processId = processId;
		this.procedureId = procedureId;
	}

	// Property accessors

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcedureId() {
		return this.procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbProcedureId))
			return false;
		TbProcedureId castOther = (TbProcedureId) other;

		return ((this.getProcessId() == castOther.getProcessId()) || (this
				.getProcessId() != null
				&& castOther.getProcessId() != null && this.getProcessId()
				.equals(castOther.getProcessId())))
				&& ((this.getProcedureId() == castOther.getProcedureId()) || (this
						.getProcedureId() != null
						&& castOther.getProcedureId() != null && this
						.getProcedureId().equals(castOther.getProcedureId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProcessId() == null ? 0 : this.getProcessId().hashCode());
		result = 37
				* result
				+ (getProcedureId() == null ? 0 : this.getProcedureId()
						.hashCode());
		return result;
	}

}