package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.PhoneRound;
import com.rising.management.dao.PhoneRoundDao;
@Component("phoneRoundDao")

public class PhoneRoundDaoImpl extends BaseDaoImpl implements PhoneRoundDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PhoneRound> getPhoneRound(Integer start, Integer pageSize) {
		String hql = " from PhoneRound ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		 ArrayList<PhoneRound> pr = (ArrayList<PhoneRound>) query.list();
		return pr;
	}
	
	public Integer getPhoneRoundListSize(){
		String hql = "select count(*) from PhoneRound ";
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void updatePhoneRound(String status, Integer id) {
		String hql = " from PhoneRound ";
		Query query = null;
		if(id!=0&&status!=null&&!"".equals(status)){
			hql = hql + " where id = :id";
			query = getSession().createQuery(hql);
			query.setParameter("id",id);
			List<PhoneRound> list = query.list();
			if(list.size()>0){
				PhoneRound phoneRound =  list.get(0);
				phoneRound.setStatus(status);
				getSession().merge(phoneRound);
				getSession().flush();
			}else{
				System.out.println("反馈ID"+id+"不存在");
			}

	}
	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PhoneRound> findAll() {
		String hql = " from PhoneRound  ";
		Query query = getSession().createQuery(hql);
		ArrayList<PhoneRound> pr = (ArrayList<PhoneRound>) query.list();
		return pr;
	}
	@Override
	public void addPhoneRound(PhoneRound pr) {
		getSession().save(pr);
		getSession().flush();
		
	}
	@Override
	public void deleteById(Integer id) {
		String hql = " delete from PhoneRound where id = :id ";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}
}
