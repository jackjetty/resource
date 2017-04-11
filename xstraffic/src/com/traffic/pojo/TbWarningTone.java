package com.traffic.pojo;

/**
 * TbWarningTone entity. @author MyEclipse Persistence Tools
 */

public class TbWarningTone implements java.io.Serializable {

	// Fields

	private Integer id;
	private String trafficIndex;
	private String voiceType;
	private String voiceRemark;
	private String voiceUrl;
	private String voiceLocalStore;
	private String voiceStatus;

	// Constructors

	/** default constructor */
	public TbWarningTone() {
	}

	
	public TbWarningTone(Integer id, String trafficIndex, String voiceType,
			String voiceRemark, String voiceUrl, String voiceLocalStore,
			String voiceStatus) {
		this.id = id;
		this.trafficIndex = trafficIndex;
		this.voiceType = voiceType;
		this.voiceRemark = voiceRemark;
		this.voiceUrl = voiceUrl;
		this.voiceLocalStore = voiceLocalStore;
		this.voiceStatus = voiceStatus;
	}


	/** minimal constructor */
	public TbWarningTone(Integer id) {
		this.id = id;
	}

	/** full constructor */

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrafficIndex() {
		return this.trafficIndex;
	}

	public void setTrafficIndex(String trafficIndex) {
		this.trafficIndex = trafficIndex;
	}

	public String getVoiceType() {
		return this.voiceType;
	}

	public void setVoiceType(String voiceType) {
		this.voiceType = voiceType;
	}

	public String getVoiceRemark() {
		return voiceRemark;
	}

	public void setVoiceRemark(String voiceRemark) {
		this.voiceRemark = voiceRemark;
	}

	public String getVoiceUrl() {
		return this.voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}

	public String getVoiceLocalStore() {
		return this.voiceLocalStore;
	}

	public void setVoiceLocalStore(String voiceLocalStore) {
		this.voiceLocalStore = voiceLocalStore;
	}

	public String getVoiceStatus() {
		return this.voiceStatus;
	}

	public void setVoiceStatus(String voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

}