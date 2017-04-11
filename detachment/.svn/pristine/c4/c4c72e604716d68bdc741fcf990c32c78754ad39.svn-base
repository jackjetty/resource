package com.detachment.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 
import org.hibernate.Query;
import org.rising.wei.bean.news.MsgItemNewsBean;
import org.rising.wei.bean.news.MultiItemNewsBean;
import org.springframework.stereotype.Repository; 
import com.detachment.dao.HistoryDao;
import com.detachment.pojo.TbHistory;
@Repository("historyDao")
public class HistoryDaoImpl extends BaseDaoImpl<TbHistory> implements HistoryDao{



	@Override
	public Integer getHistorySize(String historyTypeId) {
		String hql="select count(*) from TbHistory ";
		Query query=null;
		if(historyTypeId==null){
			query = getSession().createQuery(hql);
		}else{
			hql +=" where historyTypeId=:historyTypeId";
			query = getSession().createQuery(hql);
			query.setParameter("historyTypeId", historyTypeId);
		}
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbHistory> getTbHistory(String historyTypeId,
			Integer start, Integer pageSize) {
		String hql=" from TbHistory ";
		Query query=null;
		if(historyTypeId==null){
			hql+=" order by showOrder desc";
			query = getSession().createQuery(hql);
		}else{
			hql +=" where historyTypeId=:historyTypeId order by showOrder desc";
			query = getSession().createQuery(hql);
			query.setParameter("historyTypeId", historyTypeId);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbHistory> ths=(ArrayList<TbHistory>) query.list();
		return ths;
	}

	

	@Override
	public void changeHistoryTypeById(String historyId, String historyTypeId) {
		String hql = "update TbHistory set historyTypeId = :historyTypeId where historyId = :historyId";
		getSession().createQuery(hql)
				.setParameter("historyId", historyId)
				.setParameter("historyTypeId", historyTypeId).executeUpdate();
		getSession().flush();
		
	}



	@Override
	public void changeValid(String historyId, boolean valid) {
		String hql = "update TbHistory set valid = :valid where historyId = :historyId";
		getSession().createQuery(hql)
				.setParameter("historyId", historyId)
				.setParameter("valid", valid).executeUpdate();
		getSession().flush();
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbHistory> getHistoryToJsp(String historyTypeId,
			Integer start, Integer pageSize) {
		Query query=null;
		String hql="from TbHistory where historyTypeId=:historyTypeId order by showOrder desc";
		query = getSession().createQuery(hql);
		query.setParameter("historyTypeId", historyTypeId);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbHistory> ths=(ArrayList<TbHistory>) query.list();
		return ths;
	} 
	public void add(MsgItemNewsBean msgItemNewsBean){
		if(msgItemNewsBean.getType()!=9) return;
		List<MultiItemNewsBean> list=msgItemNewsBean.getMulti_item();
		if(list==null ||list.size()==0){
			return; 
		}
		for(MultiItemNewsBean multiItemNewsBean:list){
			if(findById(Integer.toString(multiItemNewsBean.getFile_id()))!=null)
				   continue; 
			TbHistory tbHistory=new TbHistory();
			tbHistory.setHistoryId(Integer.toString(multiItemNewsBean.getFile_id()));
			tbHistory.setHistoryDes(multiItemNewsBean.getDigest());
			tbHistory.setHistoryTitle(multiItemNewsBean.getTitle());
			tbHistory.setObjUrl(multiItemNewsBean.getContent_url());
			tbHistory.setPicUrl(multiItemNewsBean.getCover());
			tbHistory.setShowOrder(multiItemNewsBean.getFile_id());
			tbHistory.setValid(true); 
			this.saveOrUpdate(tbHistory); 
			
		}
	}

}











