package com.rising.management.dao.impl;

import java.util.ArrayList;
import org.hibernate.Query;

import org.springframework.stereotype.Component;
import com.rising.management.bean.ReturnCode;
import com.rising.management.dao.ReturnCodeDao;

@Component("returnCodeDao")
public class ReturnCodeDaoImpl extends BaseDaoImpl implements ReturnCodeDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getReturnCodeListSize(Integer busId, String returnCode,
			Boolean hasShow) {
		Query query = getQuery(returnCode, busId, hasShow);
		ArrayList<ReturnCode> rc = (ArrayList<ReturnCode>) query.list();
		return rc.size();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ReturnCode> getReturnCode(String returnCode,
			Integer busId, Boolean hasShow, Integer start, Integer pageSize) {
		Query query = getQuery(returnCode, busId, hasShow);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<ReturnCode> rc = (ArrayList<ReturnCode>) query.list();
		return rc;

	}

	private Query getQuery(String returnCode, Integer busId, Boolean hasShow) {
		String hql = "from ReturnCode";
		Query query = null;
		if (busId != null) {
			if (returnCode != null) {
				if (hasShow) {
					hql = hql
							+ " where busId = :busId and show is not null and returnCode = :returnCode order by returnCode";
				} else {
					hql = hql
							+ " where busId = :busId and returnCode = :returnCode order by returnCode";
				}
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
				query.setParameter("returnCode", returnCode);
			} else {
				if (hasShow) {
					hql = hql
							+ " where busId = :busId and show is not null order by returnCode";
				} else {
					hql = hql + " where busId = :busId order by returnCode";
				}
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
			}

		} else {
			if (returnCode != null) {
				if (hasShow) {
					hql = hql
							+ " where show is not null and returnCode = :returnCode order by returnCode ";
				} else {
					hql = hql
							+ " where returnCode = :returnCode order by returnCode ";
				}
				query = getSession().createQuery(hql);
				query.setParameter("returnCode", returnCode);
			} else {
				if (hasShow) {
					hql = hql + " where show is not null order by returnCode ";
				} else {
					hql = hql + " order by returnCode ";
				}
				query = getSession().createQuery(hql);
			}

		}
		return query;
	}

	@Override
	public void addReturnCode(ReturnCode r) {
		getSession().save(r);
		getSession().flush();

	}

	@Override
	public void deleteById(int rcId) {
		String hql = "delete from ReturnCode where id = :rcId";
		Query query = getSession().createQuery(hql);
		query.setParameter("rcId", rcId);
		query.executeUpdate();

	}

	@Override
	public void updateReturnCode(ReturnCode r) {
		getSession().update(r);
		getSession().flush();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ReturnCode getReturnCodeById(Integer rcId) {
		String hql = "from ReturnCode where rcId = :rcId";
		Query query = getSession().createQuery(hql);
		query.setParameter("rcId", rcId);
		ArrayList<ReturnCode> rc = (ArrayList<ReturnCode>) query.list();
		if (rc.size() > 0) {
			return rc.get(0);
		} else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getMeannByCode(String returnCode) {
		String hql = "select codeMeaning from ReturnCode where returnCode = :returnCode";
		Query query = getSession().createQuery(hql);
		query.setParameter("returnCode", returnCode);
		ArrayList<String> c = (ArrayList<String>) query.list();
		if(c.size()==0){
			return null;
		}else{
			String cm = c.get(0); 
			return cm;
		}
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getShowByCode(String returnCode) {
		String hql = "select show from ReturnCode where returnCode = :returnCode";
		Query query = getSession().createQuery(hql);
		query.setParameter("returnCode", returnCode);
		ArrayList<String> c = (ArrayList<String>) query.list();
		if(c.size()==0){
			return null;
		}else{
			String cm = c.get(0); 
			return cm;
		}
	}

	
}