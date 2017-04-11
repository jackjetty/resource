package com.traffic.pojovo;

import com.traffic.pojo.TbWarningTone;

public class TbWarningToneVo {
	private Integer id;
	private String trafficIndex;
	private String voiceType;
	private String voiceInfo;
	private String voiceUrl;
	private String voiceLocalStore;
	private String voiceStatus;
	public TbWarningToneVo(TbWarningTone tt){
		this.id = tt.getId();
		if("RA".equals(tt.getTrafficIndex())){
			this.trafficIndex = "交通事故查询";
		}
		if("CM".equals(tt.getTrafficIndex())){
			this.trafficIndex = "自助移车查询";
		}
		this.voiceType = tt.getVoiceType();
		this.voiceInfo = tt.getVoiceRemark();
		this.voiceUrl = tt.getVoiceUrl();
		this.voiceLocalStore = tt.getVoiceLocalStore();
		this.voiceStatus = tt.getVoiceStatus();
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrafficIndex() {
		return trafficIndex;
	}

	public void setTrafficIndex(String trafficIndex) {
		this.trafficIndex = trafficIndex;
	}

	public String getVoiceType() {
		return voiceType;
	}

	public void setVoiceType(String voiceType) {
		this.voiceType = voiceType;
	}

	public String getVoiceInfo() {
		return voiceInfo;
	}

	public void setVoiceInfo(String voiceInfo) {
		this.voiceInfo = voiceInfo;
	}

	public String getVoiceUrl() {
		return voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}

	public String getVoiceLocalStore() {
		return voiceLocalStore;
	}

	public void setVoiceLocalStore(String voiceLocalStore) {
		this.voiceLocalStore = voiceLocalStore;
	}

	public String getVoiceStatus() {
		return voiceStatus;
	}

	public void setVoiceStatus(String voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

}
