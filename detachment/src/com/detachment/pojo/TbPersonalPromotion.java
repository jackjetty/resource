package com.detachment.pojo;

/**
 * TbPersonalPromotion entity. @author MyEclipse Persistence Tools
 */

public class TbPersonalPromotion implements java.io.Serializable {

	// Fields

	private Integer personalSceneId;
	private TbWeiUser tbWeiUser;
	private String personalPhone;
	private String personalName;
	private Integer promotionSum;
	 

	// Constructors

	/** default constructor */
	public TbPersonalPromotion() {
	}

	/** minimal constructor */
	public TbPersonalPromotion(Integer personalSceneId) {
		this.personalSceneId = personalSceneId;
	}

	/** full constructor */
	public TbPersonalPromotion(Integer personalSceneId, TbWeiUser tbWeiUser,
			String personalPhone, String personalName, Integer promotionSum ) {
		this.personalSceneId = personalSceneId;
		this.tbWeiUser = tbWeiUser;
		this.personalPhone = personalPhone;
		this.personalName = personalName;
		this.promotionSum = promotionSum;
	 
	}

	// Property accessors

	public Integer getPersonalSceneId() {
		return this.personalSceneId;
	}

	public void setPersonalSceneId(Integer personalSceneId) {
		this.personalSceneId = personalSceneId;
	}

	public TbWeiUser getTbWeiUser() {
		return this.tbWeiUser;
	}

	public void setTbWeiUser(TbWeiUser tbWeiUser) {
		this.tbWeiUser = tbWeiUser;
	}

	public String getPersonalPhone() {
		return this.personalPhone;
	}

	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}

	public String getPersonalName() {
		return this.personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public Integer getPromotionSum() {
		return this.promotionSum;
	}

	public void setPromotionSum(Integer promotionSum) {
		this.promotionSum = promotionSum;
	}

	 

}