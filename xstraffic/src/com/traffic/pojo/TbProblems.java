package com.traffic.pojo;

public class TbProblems implements java.io.Serializable{
	private Integer id;
	private String problem;
	private String resultA;
	private String resultB;
	private String resultC;
	private String resultD;
	private String answer;
	private String imageUrl;
	private String choiceType;
	
	public TbProblems(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getResultA() {
		return resultA;
	}

	public void setResultA(String resultA) {
		this.resultA = resultA;
	}

	public String getResultB() {
		return resultB;
	}

	public void setResultB(String resultB) {
		this.resultB = resultB;
	}

	public String getResultC() {
		return resultC;
	}

	public void setResultC(String resultC) {
		this.resultC = resultC;
	}

	public String getResultD() {
		return resultD;
	}

	public void setResultD(String resultD) {
		this.resultD = resultD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getChoiceType() {
		return choiceType;
	}

	public void setChoiceType(String choiceType) {
		this.choiceType = choiceType;
	}
	
	
}
