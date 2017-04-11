package com.detachment.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TbAppointmentType entity. @author MyEclipse Persistence Tools
 */

public class TbAppointmentType implements java.io.Serializable {

	// Fields

	private String appointmentTypeId;
	private String appointmentTypeName;
	private Set tbAppointments = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbAppointmentType() {
	}

	/** minimal constructor */
	public TbAppointmentType(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	/** full constructor */
	public TbAppointmentType(String appointmentTypeId,
			String appointmentTypeName, Set tbAppointments) {
		this.appointmentTypeId = appointmentTypeId;
		this.appointmentTypeName = appointmentTypeName;
		this.tbAppointments = tbAppointments;
	}

	// Property accessors

	public String getAppointmentTypeId() {
		return this.appointmentTypeId;
	}

	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public String getAppointmentTypeName() {
		return this.appointmentTypeName;
	}

	public void setAppointmentTypeName(String appointmentTypeName) {
		this.appointmentTypeName = appointmentTypeName;
	}

	public Set getTbAppointments() {
		return this.tbAppointments;
	}

	public void setTbAppointments(Set tbAppointments) {
		this.tbAppointments = tbAppointments;
	}

}