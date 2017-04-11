package com.detachment.dao.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.FullScodeDao;
import com.detachment.pojo.TbFullScode;
import com.detachment.pojo.TbPersonalPromotion;
import com.detachment.pojo.TbWeiUser;
import com.detachment.pojo.vo.TbFullScodeVo;
import com.detachment.util.CommonUtil;
@Repository("fullScodeDao")
public class FullScodeDaoImpl extends BaseDaoImpl<TbFullScode> implements FullScodeDao{
	public TbFullScodeVo toTbFullScodeVo(TbFullScode tbFullScode){
		TbFullScodeVo tbFullScodeVo=new TbFullScodeVo();
		tbFullScodeVo.setFullScodeId(tbFullScode.getFullScodeId());
		tbFullScodeVo.setDeadlineTime(CommonUtil.doDateForm(tbFullScode.getDeadlineTime()));
		tbFullScodeVo.setFileNum(CommonUtil.trim(tbFullScode.getFileNum()));
		tbFullScodeVo.setFirstStudyTime(CommonUtil.doDateForm(tbFullScode.getFirstStudyTime()));
		tbFullScodeVo.setIdentityCard(CommonUtil.trim(tbFullScode.getIdentityCard()));
		tbFullScodeVo.setOp1("op1");
		if(tbFullScode.getTbWeiUser()!=null){
			TbWeiUser tbWeiUser=tbFullScode.getTbWeiUser();
			tbFullScodeVo.setRealName(CommonUtil.trim(tbWeiUser.getRealName()));
			tbFullScodeVo.setNickname(CommonUtil.trim(tbWeiUser.getNickname()));
			tbFullScodeVo.setOpenId(CommonUtil.trim(tbWeiUser.getOpenId()));
		}else{
			tbFullScodeVo.setRealName("");
			tbFullScodeVo.setNickname("");
			tbFullScodeVo.setOpenId("");
		}
		tbFullScodeVo.setPhoneNumber(CommonUtil.trim(tbFullScode.getPhoneNumber()));
		tbFullScodeVo.setUserName(CommonUtil.trim(tbFullScode.getUserName()));
		tbFullScodeVo.setRemark(CommonUtil.trim(tbFullScode.getRemark()));
		tbFullScodeVo.setState(CommonUtil.trim(tbFullScode.getState()));
		if(tbFullScode.getTotalHour()==null){
			tbFullScodeVo.setTotalHour("");
		}
		else{
			tbFullScodeVo.setTotalHour(String.valueOf(tbFullScode.getTotalHour()));
		}
		
		return tbFullScodeVo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public TbFullScode findByIdentityCardAndHour(String identityCard) {
		String hql="from TbFullScode where identityCard = :identityCard and state='学习中' and totalHour < 12";
		Query query = getSession().createQuery(hql);
		query.setParameter("identityCard", identityCard);
		ArrayList<TbFullScode> list=(ArrayList<TbFullScode>) query.list();
		if(list==null||list.size()==0)
			return null;
		return list.get(0);
	}


	@SuppressWarnings("unchecked")
	@Override
	public TbFullScode findByOpenIdLast(String openId) {
		String hql="from TbFullScode where fullScodeId = ( select MAX(fullScodeId) from TbFullScode where tbWeiUser.openId = :openId)";
		Query query = getSession().createQuery(hql);
		query.setParameter("openId", openId);
		ArrayList<TbFullScode> list=(ArrayList<TbFullScode>) query.list();
		if(list==null||list.size()==0)
			return null;
		return list.get(0);
	}
}