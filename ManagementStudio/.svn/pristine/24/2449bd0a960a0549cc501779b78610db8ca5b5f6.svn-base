package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.ScoreRule;
import com.rising.management.dao.ScoreRuleDao;

@Component("scoreRuleDao")
public class ScoreRuleDaoImpl extends BaseDaoImpl implements ScoreRuleDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ScoreRule> getScoreRule(Date startDate, Date endDate,
			Integer start, Integer pageSize) {
		String hql = "from ScoreRule";
		Query query = null;
		if(startDate==null){
			if(endDate==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql + " where endTime <= :endTime";
				query = getSession().createQuery(hql);
				query.setParameter("endTime", endDate);
			}
		}else{
			if(endDate==null){
				hql = hql + " where startTime >= :startTime";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startDate);
			}else{
				hql = hql + " where startTime >= :startTime and endTime <= :endTime ";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startDate);
				query.setParameter("endTime", endDate);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<ScoreRule> rs = (ArrayList<ScoreRule>) query.list();
		return rs;
	}

	@Override
	public Integer getScoreRuleListSize(Date startDate, Date endDate) {
		String hql = "select count(*) from ScoreRule";
		Query query = null;
		if(startDate==null){
			if(endDate==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql + " where endTime <= :endTime";
				query = getSession().createQuery(hql);
				query.setParameter("endTime", endDate);
			}
		}else{
			if(endDate==null){
				hql = hql + " where startTime >= :startTime";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startDate);
			}else{
				hql = hql + " where startTime >= :startTime and endTime <= :endTime ";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startDate);
				query.setParameter("endTime", endDate);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ScoreRule> findAll() {
		String hql = "from ScoreRule";
		Query query = getSession().createQuery(hql);
		ArrayList<ScoreRule> sr = (ArrayList<ScoreRule>) query.list();
		return sr;
	}

	@Override
	public void addScoreRule(ScoreRule sr) {
		getSession().save(sr);
		getSession().flush();
		
	}

	@Override
	public void deleteById(Integer ruleId) {
		String hql = " delete from ScoreRule where ruleId = :ruleId ";
		Query query = getSession().createQuery(hql);
		query.setParameter("ruleId", ruleId);
		query.executeUpdate();
		
		
	}

	@Override
	public void updateScoreRule(ScoreRule sr) {
		getSession().update(sr);
		getSession().flush();
		
	}

}
