package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.TakingPhotosDao;
import com.traffic.pojo.TbTakingPhotos;

@Repository("takingPhotosDao")
public class TakingPhotosDaoImpl extends BaseDaoImpl<TbTakingPhotos> implements TakingPhotosDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getTakingPhotosSize(Date startTime, Date endTime,
			String reportPhoneNumber, String nickName, String handyPhotoState) {
		String hql = "from TbTakingPhotos";
		Query query = null;
		if(handyPhotoState==null){
			if (nickName == null) {
				if (reportPhoneNumber == null) {
					hql = hql + " where reportTime >= :startTime and reportTime <= :endTime order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}
			}else{
				if (reportPhoneNumber == null) {
					hql = hql
							+ " where  reportTime >= :startTime and reportTime <= :endTime and reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
				} else {
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);

			} 
				
			}
		}else{
			if (nickName == null) {
				if (reportPhoneNumber == null) {
					hql = hql + " where reportTime >= :startTime and reportTime <= :endTime and handyPhotoState = :handyPhotoState order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("handyPhotoState", handyPhotoState);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and handyPhotoState = :handyPhotoState order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
					query.setParameter("handyPhotoState", handyPhotoState);
				}
			}else{
				if (reportPhoneNumber == null) {
					hql = hql
							+ " where  reportTime >= :startTime and reportTime <= :endTime and and handyPhotoState = :handyPhotoState reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
					query.setParameter("handyPhotoState", handyPhotoState);
				} else {
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and handyPhotoState = :handyPhotoState and reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
					query.setParameter("handyPhotoState", handyPhotoState);

			} 
				
			}
		}
		ArrayList<TbTakingPhotos> tp = (ArrayList<TbTakingPhotos>) query.list();
		return tp.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbTakingPhotos> getMoveCar(Date startTime, Date endTime,
			String reportPhoneNumber, Integer start, Integer pageSize,String nickName,String handyPhotoState) {
		String hql = "from TbTakingPhotos ";
		Query query = null;
		if(handyPhotoState==null){
			if (nickName == null) {
				if (reportPhoneNumber == null) {
					hql = hql + " where reportTime >= :startTime and reportTime <= :endTime order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}
			}else{
				if (reportPhoneNumber == null) {
					hql = hql
							+ " where  reportTime >= :startTime and reportTime <= :endTime and reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
				} else {
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);

			} 
				
			}
		}else{
			if (nickName == null) {
				if (reportPhoneNumber == null) {
					hql = hql + " where reportTime >= :startTime and reportTime <= :endTime and handyPhotoState = :handyPhotoState order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("handyPhotoState", handyPhotoState);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and handyPhotoState = :handyPhotoState order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
					query.setParameter("handyPhotoState", handyPhotoState);
				}
			}else{
				if (reportPhoneNumber == null) {
					hql = hql
							+ " where  reportTime >= :startTime and reportTime <= :endTime and and handyPhotoState = :handyPhotoState reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
					query.setParameter("handyPhotoState", handyPhotoState);
				} else {
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and handyPhotoState = :handyPhotoState and reportOpenId in (select openId from TbUser where nickName = :nickName ) order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("nickName", nickName);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
					query.setParameter("handyPhotoState", handyPhotoState);

			} 
				
			}
		}
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        ArrayList<TbTakingPhotos> tp = (ArrayList<TbTakingPhotos>) query.list();
		return tp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getTbAddress(String takingPhotosId) {
		String hql = "select locationX,locationY,address from TbTakingPhotos where takingPhotosId = :takingPhotosId";
		Query query = getSession().createQuery(hql);
		query.setParameter("takingPhotosId", takingPhotosId);
		ArrayList<Object[]> addr = (ArrayList<Object[]>) query.list();
		return addr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbTakingPhotos getTpById(String takingPhotosId) {
		String hql = "from TbTakingPhotos where takingPhotosId = :takingPhotosId";
		Query query = getSession().createQuery(hql);
		query.setParameter("takingPhotosId", takingPhotosId);
		ArrayList<TbTakingPhotos> addr = (ArrayList<TbTakingPhotos>) query
				.list();
		return addr.get(0);
	}

	@Override
	public void updateState(TbTakingPhotos tc) {
		getSession().update(tc);
		getSession().flush();

	}

}
