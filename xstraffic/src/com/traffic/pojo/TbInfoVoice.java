package com.traffic.pojo;

/**
 * TbInfoVoice entity. @author MyEclipse Persistence Tools
 */

public class TbInfoVoice implements java.io.Serializable {

	// Fields

	private TbInfoVoiceId id;
	private String voiceType;
	private String voiceInfo;
	private String voiceUrl;
	private String voiceLocalStore;

	// Constructors

	/** default constructor */
	public TbInfoVoice() {
	}

	/** minimal constructor */
	public TbInfoVoice(TbInfoVoiceId id) {
		this.id = id;
	}

	/** full constructor */
	public TbInfoVoice(TbInfoVoiceId id, String voiceType, String voiceInfo,
			String voiceUrl, String voiceLocalStore) {
		this.id = id;
		this.voiceType = voiceType;
		this.voiceInfo = voiceInfo;
		this.voiceUrl = voiceUrl;
		this.voiceLocalStore = voiceLocalStore;
	}

	// Property accessors

	public TbInfoVoiceId getId() {
		return this.id;
	}

	public void setId(TbInfoVoiceId id) {
		this.id = id;
	}

	public String getVoiceType() {
		return this.voiceType;
	}

	public void setVoiceType(String voiceType) {
		this.voiceType = voiceType;
	}

	public String getVoiceInfo() {
		return this.voiceInfo;
	}

	public void setVoiceInfo(String voiceInfo) {
		this.voiceInfo = voiceInfo;
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

}