package com.detachment.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.ElePoliceDao;
import com.detachment.pojo.TbElectronicPolice;

@Repository("elePoliceDao")
public class ElePoliceDaoImpl extends BaseDaoImpl<TbElectronicPolice> implements ElePoliceDao{


	@Override
	public Integer getElePoliceSize(String eleType, String eleAddress) {
		String hql = "select count(*) from TbElectronicPolice ";
		Query query=null;
		if(eleType==null){
			if(eleAddress==null){
				query = getSession().createQuery(hql);
			}else{
				hql=hql+" where eleAddress like :eleAddress";
				query = getSession().createQuery(hql);
				query.setParameter("eleAddress", "%"+eleAddress+"%");
			}
		}else{
			hql=hql+"where eleType = :eleType ";
			if(eleAddress==null){
				query = getSession().createQuery(hql);
				query.setParameter("eleType", eleType);
			}else{
				hql=hql+" and eleAddress like :eleAddress";
				query = getSession().createQuery(hql);
				query.setParameter("eleType", eleType);
				query.setParameter("eleAddress", "%"+eleAddress+"%");
			}
		}
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbElectronicPolice> getElePolice(String eleType,
			String eleAddress, Integer start, Integer pageSize) {
		String hql = " from TbElectronicPolice ";
		Query query=null;
		if(eleType==null){
			if(eleAddress==null){
				query = getSession().createQuery(hql);
			}else{
				hql=hql+" where eleAddress like :eleAddress";
				query = getSession().createQuery(hql);
				query.setParameter("eleAddress", "%"+eleAddress+"%");
			}
		}else{
			hql=hql+"where eleType = :eleType ";
			if(eleAddress==null){
				query = getSession().createQuery(hql);
				query.setParameter("eleType", eleType);
			}else{
				hql=hql+" and eleAddress like :eleAddress";
				query = getSession().createQuery(hql);
				query.setParameter("eleType", eleType);
				query.setParameter("eleAddress", "%"+eleAddress+"%");
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbElectronicPolice> tes=(ArrayList<TbElectronicPolice>) query.list();
		return tes;
	}

}
