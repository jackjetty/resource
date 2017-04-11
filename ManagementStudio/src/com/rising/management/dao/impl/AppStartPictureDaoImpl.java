package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.AppStartPicture;
import com.rising.management.dao.AppStartPictureDao;

@Component("appStartPictureDao")
public class AppStartPictureDaoImpl extends BaseDaoImpl implements AppStartPictureDao{
	
	@Override
	public Integer getAppStartPictureSize(String pictureName, String startTime,
			String endTime) {
		String hql = "select count(*) from AppStartPicture";
		Session session = getSession();
		Query query = null;
		if (pictureName != null) {
			hql = hql + " where pictureName = :pictureName";
			if (startTime != null && endTime==null) {
				hql = hql + " and startTime >= :startTime";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
				query.setParameter("startTime", startTime);
			} else if(startTime == null && endTime!=null){
				hql = hql + " and endTime >= :endTime";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
				query.setParameter("endTime", endTime);
			} else if(startTime!=null && endTime!=null){
				hql = hql + " and startTime >= :startTime and endTime <= :endTime";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
			}
		} else if (startTime != null) {
			hql = hql + " where startTime >= :startTime";
			if(endTime!=null){
				hql=hql+" and endTime <= :endTime";
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
			}
		} else if(endTime!=null){
			hql = hql + " where endTime <= :endTime";
			query = session.createQuery(hql);
			query.setParameter("endTime", endTime);
		}else{
			query = session.createQuery(hql);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppStartPicture> getAppStartPicture(String pictureName,
			String startTime, String endTime, Integer start, Integer pageSize) {
		String hql = "from AppStartPicture";
		Session session = getSession();
		Query query = null;
		if (pictureName != null) {
			hql = hql + " where pictureName = :pictureName";
			if (startTime != null && endTime==null) {
				hql = hql + " and startTime >= :startTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
				query.setParameter("startTime", startTime);
			} else if(startTime == null && endTime!=null){
				hql = hql + " and endTime <= :endTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
				query.setParameter("endTime", endTime);
			} else if(startTime!=null && endTime!=null){
				hql = hql + " and startTime >= :startTime and endTime <= :endTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("pictureName", pictureName);
			}
		} else if (startTime != null) {
			hql = hql + " where startTime >= :startTime";
			if(endTime!=null){
				hql=hql+" and endTime <= :endTime";
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}else{
				hql=hql+" order by id";
				query = session.createQuery(hql);
				query.setParameter("startTime", startTime);
			}
		} else if(endTime!=null){
			hql = hql + " where endTime <= :endTime";
			hql=hql+" order by id";
			query = session.createQuery(hql);
			query.setParameter("endTime", endTime);
		}else{
			hql=hql+" order by id";
			query = session.createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return (ArrayList<AppStartPicture>) query.list();
	}

	@Override
	public void addAppStartPicture(AppStartPicture asp) {
		getSession().save(asp);
		getSession().flush();
	}

	@Override
	public void updateAppStartPicture(AppStartPicture asp) {
		getSession().update(asp);
		getSession().flush();
	}

	@Override
	public void removeAppStartPicture(ArrayList<Integer> ids) {
		String hql="delete from AppStartPicture where id in (:ids)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AppStartPicture getAppStartPictureById(Integer id) {
		String hql="from AppStartPicture where id = :id";
		Query query  = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<AppStartPicture> ap= (ArrayList<AppStartPicture>) query.list();
		if(ap.size()>0){
			return ap.get(0);
		}else{
			return null;
		}
	}

}
