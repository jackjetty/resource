package com.detachment.pojo;

/**
 * TbProblems entity. @author MyEclipse Persistence Tools
 */

public class TbProblems implements java.io.Serializable {

	// Fields

	private Integer id;
	private String problem;
	private String resultA;
	private String resultB;
	private String resultC;
	private String resultD;
	private String answer;
	private String imageUrl;
	private String explain;

	// Constructors

	/** default constructor */
	public TbProblems() {
	}

	/** minimal constructor */
	public TbProblems(Integer id, String problem, String answer) {
		this.id = id;
		this.problem = problem;
		this.answer = answer;
	}

	/** full constructor */
	public TbProblems(Integer id, String problem, String resultA,
			String resultB, String resultC, String resultD, String answer,
			String imageUrl, String explain) {
		this.id = id;
		this.problem = problem;
		this.resultA = resultA;
		this.resultB = resultB;
		this.resultC = resultC;
		this.resultD = resultD;
		this.answer = answer;
		this.imageUrl = imageUrl;
		this.explain = explain;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getResultA() {
		return this.resultA;
	}

	public void setResultA(String resultA) {
		this.resultA = resultA;
	}

	public String getResultB() {
		return this.resultB;
	}

	public void setResultB(String resultB) {
		this.resultB = resultB;
	}

	public String getResultC() {
		return this.resultC;
	}

	public void setResultC(String resultC) {
		this.resultC = resultC;
	}

	public String getResultD() {
		return this.resultD;
	}

	public void setResultD(String resultD) {
		this.resultD = resultD;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getExplain() {
		return this.explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}