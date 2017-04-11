package com.detachment.dto;

import java.io.Serializable;

import com.detachment.pojo.TbPromotion;

public class PromotionDto implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 694430552679129276L;
	private String promotionId;
	private String promotionName;
	private Integer sceneId;
	private String picUrl;
	private Integer orgPpNum;
	private Integer peopleNum;
	
	
	public PromotionDto(){}
	
	public PromotionDto(TbPromotion tp,Integer num){
		this.promotionId=tp.getPromotionId();
		this.promotionName=tp.getPromotionName();
		this.sceneId=tp.getSceneId();
		this.picUrl=tp.getPicUrl();
		this.peopleNum=num;
	}
	
	
	
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	
	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public Integer getOrgPpNum() {
		return orgPpNum;
	}

	public void setOrgPpNum(Integer orgPpNum) {
		this.orgPpNum = orgPpNum;
	}
	
	
}
