package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.PrizeCode;

public class PrizeCodeVo {
	private Integer id;

	private Integer prizeId;

	private String prizeName;

	private String code;

	private String effectiveTime;

	private String sended;

	private String sended2;
	
	public PrizeCodeVo(PrizeCode code,HashMap<String,Object> map){
		this.id = code.getId();
		this.code = code.getCode();
		this.prizeId = code.getPrizeId();
		this.prizeName = (String) map.get(code.getPrizeId().toString());
		this.effectiveTime = code.getEffectiveTime();
		this.sended = code.getSended();
		if("yes".equals(sended)){
			this.sended2 = "已送出";
		}else{
			this.sended2 = "未送出";
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getSended() {
		return sended;
	}

	public void setSended(String sended) {
		this.sended = sended;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getSended2() {
		return sended2;
	}

	public void setSended2(String sended2) {
		this.sended2 = sended2;
	}

}
