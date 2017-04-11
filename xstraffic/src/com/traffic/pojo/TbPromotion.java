package com.traffic.pojo;

/**
 * TbPromotion entity. @author MyEclipse Persistence Tools
 */

public class TbPromotion implements java.io.Serializable {

	// Fields

	private String promotionId;
	private String promotionName;
	private Integer sceneId;
	private Boolean valid;
	private String picUrl;

	// Constructors

	/** default constructor */
	public TbPromotion() {
	}

	/** minimal constructor */
	public TbPromotion(String promotionId) {
		this.promotionId = promotionId;
	}

	/** full constructor */
	public TbPromotion(String promotionId, String promotionName,
			Integer sceneId, Boolean valid, String picUrl) {
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.sceneId = sceneId;
		this.valid = valid;
		this.picUrl = picUrl;
	}

	// Property accessors

	public String getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionName() {
		return this.promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Integer getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}