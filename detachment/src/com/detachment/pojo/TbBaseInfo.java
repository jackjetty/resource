package com.detachment.pojo;

import java.util.Date;

public class TbBaseInfo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private String baseInfoId;
	private Integer schoolId;
	private Integer gender;
	private String ageGroup;
	private Integer hasDriverLicense;
	private Integer everDelieverChildren;
	private String mainDelieverChildren;
	private String delieverChildrenType;
	private Integer childrenAlone;
	private Integer schoolTrafficOrder;
	private String trafficIllegalProblem;
	private String suggestion;
	private Date finishTime;
	private String state;
	
	// Constructors
	
	/** default constructor */
	public TbBaseInfo() {
		super();
	}

	// Property accessors
	
	public String getBaseInfoId() {
		return baseInfoId;
	}
	
	public void setBaseInfoId(String baseInfoId) {
		this.baseInfoId = baseInfoId;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}
	
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	public Integer getGender() {
		return gender;
	}
	
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public String getAgeGroup() {
		return ageGroup;
	}
	
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	public Integer getHasDriverLicense() {
		return hasDriverLicense;
	}
	
	public void setHasDriverLicense(Integer hasDriverLicense) {
		this.hasDriverLicense = hasDriverLicense;
	}
	
	public Integer getEverDelieverChildren() {
		return everDelieverChildren;
	}
	
	public void setEverDelieverChildren(Integer everDelieverChildren) {
		this.everDelieverChildren = everDelieverChildren;
	}
	
	public String getMainDelieverChildren() {
		return mainDelieverChildren;
	}
	
	public void setMainDelieverChildren(String mainDelieverChildren) {
		this.mainDelieverChildren = mainDelieverChildren;
	}
	
	public String getDelieverChildrenType() {
		return delieverChildrenType;
	}
	
	public void setDelieverChildrenType(String delieverChildrenType) {
		this.delieverChildrenType = delieverChildrenType;
	}
	
	public Integer getChildrenAlone() {
		return childrenAlone;
	}
	
	public void setChildrenAlone(Integer childrenAlone) {
		this.childrenAlone = childrenAlone;
	}
	
	public Integer getSchoolTrafficOrder() {
		return schoolTrafficOrder;
	}
	
	public void setSchoolTrafficOrder(Integer schoolTrafficOrder) {
		this.schoolTrafficOrder = schoolTrafficOrder;
	}
	
	public String getTrafficIllegalProblem() {
		return trafficIllegalProblem;
	}
	
	public void setTrafficIllegalProblem(String trafficIllegalProblem) {
		this.trafficIllegalProblem = trafficIllegalProblem;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	
	
	
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	public Date getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}