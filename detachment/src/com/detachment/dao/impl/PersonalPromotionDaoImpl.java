package com.detachment.dao.impl;
 
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.detachment.dao.PersonalPromotionDao; 
import com.detachment.pojo.TbPersonalPromotion; 
@Repository("personalPromotionDao")
public class PersonalPromotionDaoImpl    extends BaseDaoImpl<TbPersonalPromotion> implements  PersonalPromotionDao{
	public TbPersonalPromotion getTbPersonalPromotionByOpenId(String openId){
		String hql="from TbPersonalPromotion where tbWeiUser.openId=?";
		List<TbPersonalPromotion> list=this.findByHQL(hql, openId);
		if(list==null||list.size()==0)
			return null;
		return list.get(0);
	}
}