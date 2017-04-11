package com.detachment.dao;
import java.util.ArrayList;
import java.util.Date;

import com.detachment.pojo.TbPersonalPromotion; 

public interface  PersonalPromotionDao extends BaseDao<TbPersonalPromotion>{
	public TbPersonalPromotion getTbPersonalPromotionByOpenId(String openId);
}