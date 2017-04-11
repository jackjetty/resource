package com.detachment.pojo;

public class TbQuestionRecord implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private String baseInfoId;
	private Integer questionId;
	private String responseValue;
	
	// Constructors
	
	/** default constructor */
	public TbQuestionRecord() {
		super();
	}

	// Property accessors
	
	public String getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(String baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}
	
}
