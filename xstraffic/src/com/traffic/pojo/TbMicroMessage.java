package com.traffic.pojo;

public class TbMicroMessage {
	private String microId;
	private String microMeaning;
	
	public TbMicroMessage(){
		
	}

	public TbMicroMessage(String microId, String microMeaning) {
		this.microId = microId;
		this.microMeaning = microMeaning;
	}

	public String getMicroId() {
		return microId;
	}

	public void setMicroId(String microId) {
		this.microId = microId;
	}

	public String getMicroMeaning() {
		return microMeaning;
	}

	public void setMicroMeaning(String microMeaning) {
		this.microMeaning = microMeaning;
	}

}
