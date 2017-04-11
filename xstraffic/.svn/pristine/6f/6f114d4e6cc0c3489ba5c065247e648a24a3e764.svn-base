package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.StudyInfoDao;
import com.traffic.pojo.TbStudyInfo;

@Repository("studyInfoDao")
public class StudyInfoDaoImpl extends BaseDaoImpl<TbStudyInfo> implements StudyInfoDao{

	@Override
	public void addStudyInfo(TbStudyInfo pb) {
		getSession().save(pb);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getStudyInfoSize(String studyNumber, String idCard,Integer score) {
		String hql=" from TbStudyInfo ";
		Query query=null;
		if(score!=null){
			if(score==1){
				hql+=" where score > 90 ";
				if(studyNumber!=null){
					hql+=" and studyNumber = :studyNumber ";
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
						query.setParameter("idCard",idCard);
						
					}else{
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
						
					}
				}else{
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("idCard",idCard);
						
					}else{
						query = getSession().createQuery(hql);
						
					}
				}
			}else{
				hql+=" where score < 90 ";
				if(studyNumber!=null){
					hql+=" and studyNumber = :studyNumber ";
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
						query.setParameter("idCard",idCard);
						
					}else{
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
						
					}
				}else{
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("idCard",idCard);
						
					}else{
						query = getSession().createQuery(hql);
						
					}
				}
			}
			
		}else{
			if(studyNumber!=null){
				hql+=" where studyNumber = :studyNumber ";
				if(idCard!=null){
					hql+=" and idCard = :idCard ";
					query = getSession().createQuery(hql);
					query.setParameter("studyNumber",studyNumber);
					query.setParameter("idCard",idCard);
				}else{
					query = getSession().createQuery(hql);
					query.setParameter("studyNumber",studyNumber);
				}
			}else{
				if(idCard!=null){
					hql+=" where idCard = :idCard ";
					query = getSession().createQuery(hql);
					query.setParameter("idCard",idCard);
				}else{
					query = getSession().createQuery(hql);
				}
			}
		}
		
		ArrayList<TbStudyInfo> ta = (ArrayList<TbStudyInfo>) query.list();
		return ta.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbStudyInfo> getStudyInfo(String studyNumber, String idCard,Integer score,
			Integer start, Integer pageSize) {
		String hql=" from TbStudyInfo ";
		Query query=null;
		if(score!=null){
			if(score==1){
				hql+=" where score >90 ";
				if(studyNumber!=null){
					hql+=" and studyNumber = :studyNumber ";
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
						query.setParameter("idCard",idCard);
					}else{
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
					}
				}else{
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("idCard",idCard);
					}else{
						query = getSession().createQuery(hql);
					}
				}
			}else if(score==2){
				hql+=" where score <90 ";
				if(studyNumber!=null){
					hql+=" and studyNumber = :studyNumber ";
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
						query.setParameter("idCard",idCard);
					}else{
						query = getSession().createQuery(hql);
						query.setParameter("studyNumber",studyNumber);
					}
				}else{
					if(idCard!=null){
						hql+=" and idCard = :idCard ";
						query = getSession().createQuery(hql);
						query.setParameter("idCard",idCard);
					}else{
						query = getSession().createQuery(hql);
					}
				}
			}
			
		}else{
			if(studyNumber!=null){
				hql+=" where studyNumber = :studyNumber ";
				if(idCard!=null){
					hql+=" and idCard = :idCard ";
					query = getSession().createQuery(hql);
					query.setParameter("studyNumber",studyNumber);
					query.setParameter("idCard",idCard);
				}else{
					query = getSession().createQuery(hql);
					query.setParameter("studyNumber",studyNumber);
				}
			}else{
				if(idCard!=null){
					hql+=" where idCard = :idCard ";
					query = getSession().createQuery(hql);
					query.setParameter("idCard",idCard);
				}else{
					query = getSession().createQuery(hql);
				}
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbStudyInfo> ta = (ArrayList<TbStudyInfo>) query.list();
		return ta;
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbStudyInfo> getStudyInfoJsp(String idCard,String openId, Integer start,
			Integer pageSize) {
		String hql=" from TbStudyInfo where idCard = :idCard and openId = :openId";
		Query query = getSession().createQuery(hql);
		query.setParameter("idCard",idCard);
		query.setParameter("openId",openId);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbStudyInfo> ta = (ArrayList<TbStudyInfo>) query.list();
		return ta;
	}

}
