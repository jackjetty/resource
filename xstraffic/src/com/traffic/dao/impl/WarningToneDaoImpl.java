package com.traffic.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.WarningToneDao;
import com.traffic.pojo.TbWarningTone;

@Repository("warningToneDao")
public class WarningToneDaoImpl extends BaseDaoImpl<TbWarningTone> implements WarningToneDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getWarningToneListSize(String trafficIndex,
			String voiceStatus) {
		String hql = "from TbWarningTone";
		Query query = null;
		if(trafficIndex == null){
			if(voiceStatus==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql + " where voiceStatus = :voiceStatus";
				query = getSession().createQuery(hql);
				query.setParameter("voiceStatus", voiceStatus);
			}
		}else{
				if(voiceStatus==null){
					hql = hql + " where trafficIndex = :trafficIndex";
					query = getSession().createQuery(hql);
					query.setParameter("trafficIndex", trafficIndex);
				}else{
					hql = hql + " where voiceStatus = :voiceStatus and trafficIndex = :trafficIndex";
					query = getSession().createQuery(hql);
					query.setParameter("voiceStatus", voiceStatus);
					query.setParameter("trafficIndex", trafficIndex);
				}
		}
		ArrayList<TbWarningTone> tt = (ArrayList<TbWarningTone>) query.list();
		return tt.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbWarningTone> getWarningTone(Integer start,
			Integer pageSize, String trafficIndex, String voiceStatus) {
		String hql = "from TbWarningTone";
		Query query = null;
		if(trafficIndex == null){
			if(voiceStatus==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql + " where voiceStatus = :voiceStatus";
				query = getSession().createQuery(hql);
				query.setParameter("voiceStatus", voiceStatus);
			}
		}else{
				if(voiceStatus==null){
					hql = hql + " where trafficIndex = :trafficIndex";
					query = getSession().createQuery(hql);
					query.setParameter("trafficIndex", trafficIndex);
				}else{
					hql = hql + " where voiceStatus = :voiceStatus and trafficIndex = :trafficIndex";
					query = getSession().createQuery(hql);
					query.setParameter("voiceStatus", voiceStatus);
					query.setParameter("trafficIndex", trafficIndex);
				}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbWarningTone> tt = (ArrayList<TbWarningTone>) query.list();
		return tt;
	}

	@Override
	public void addWarningTone(TbWarningTone tt) {
		getSession().save(tt);
		getSession().flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbWarningTone> getWT(String voiceStatus) {
		String hql = "from TbWarningTone where voiceStatus = :voiceStatus";
		Query query = getSession().createQuery(hql);
		query.setParameter("voiceStatus", voiceStatus);
		ArrayList<TbWarningTone> tt = (ArrayList<TbWarningTone>) query.list();
		return tt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbWarningTone getWTById(Integer id) {
		String hql = "from TbWarningTone where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<TbWarningTone> tt = (ArrayList<TbWarningTone>) query.list();
		return tt.get(0);
	}

	@Override
	public void updateVoiceStatus(TbWarningTone t) {
		getSession().update(t);
		getSession().flush();
		
	}

	@Override
	public void removeWarningTone(ArrayList<Integer> ids) {
		String hql = "delete from TbWarningTone where id in (:ids)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("ids",ids);
		query.executeUpdate();
	}

	@Override
	public void updateWarningTone(TbWarningTone tt) {
		getSession().update(tt);
		getSession().flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getAllLs() {
		String hql = "select voiceLocalStore from TbWarningTone";
		Query query = getSession().createQuery(hql);
		ArrayList<String> ls = (ArrayList<String>) query.list();
		return ls;
	}


}
