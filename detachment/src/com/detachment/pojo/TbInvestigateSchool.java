package com.detachment.pojo;

public class TbInvestigateSchool implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private Integer schoolId;
	private String schoolName;
	
	// Constructors
	
	/** default constructor */
	public TbInvestigateSchool() {
		super();
	}

	// Property accessors
	
	public Integer getSchoolId() {
		return schoolId;
	}
	
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
