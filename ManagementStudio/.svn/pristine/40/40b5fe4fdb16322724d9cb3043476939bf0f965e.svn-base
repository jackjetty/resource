package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.DownloadRecord;
import com.rising.management.dao.DownloadRecordDao;

@Component("downloadRecordDao")
public class DownloadRecordDaoImpl extends BaseDaoImpl implements DownloadRecordDao{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DownloadRecord> getDownloadRecord(String fromWay,
			String downloadTime, Integer start, Integer pageSize) {
		String hql = "from DownloadRecord";
		Query query = null;
		if(downloadTime==null){
			if(fromWay==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql + " where fromWay = :fromWay";
				query = getSession().createQuery(hql);
				query.setParameter("fromWay", fromWay);
			}
		}else{
			if(fromWay==null){
				hql = hql + " where to_char(downloadTime,'yyyy-MM-dd') = :downloadTime";
				query = getSession().createQuery(hql);
				query.setParameter("downloadTime", downloadTime);
			}else{
				hql = hql + " where to_char(downloadTime,'yyyy-MM-dd') = :downloadTime and fromWay = :fromWay ";
				query = getSession().createQuery(hql);
				query.setParameter("downloadTime", downloadTime);
				query.setParameter("fromWay", fromWay);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<DownloadRecord> rs = (ArrayList<DownloadRecord>) query.list();
		return rs;
	}

	@Override
	public Integer getDownloadRecordListSize(String fromWay, String downloadTime) {
		String hql = "select count(*) from DownloadRecord";
		Query query = null;
		if(downloadTime==null){
			if(fromWay==null){
				query = getSession().createQuery(hql);
			}else{
				hql = hql + " where fromWay = :fromWay";
				query = getSession().createQuery(hql);
				query.setParameter("fromWay", fromWay);
			}
		}else{
			if(fromWay==null){
				hql = hql + " where to_char(downloadTime,'yyyy-MM-dd') = :downloadTime";
				query = getSession().createQuery(hql);
				query.setParameter("downloadTime", downloadTime);
			}else{
				hql = hql + " where to_char(downloadTime,'yyyy-MM-dd') = :downloadTime and fromWay = :fromWay ";
				query = getSession().createQuery(hql);
				query.setParameter("downloadTime", downloadTime);
				query.setParameter("fromWay", fromWay);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

}
