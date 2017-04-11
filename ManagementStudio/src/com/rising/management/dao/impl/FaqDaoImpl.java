package com.rising.management.dao.impl;

import java.util.*;
import java.io.Serializable;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Faq; 
import com.rising.management.dao.FaqDao;


@Component("faqDao")
public class FaqDaoImpl extends BaseDaoImpl implements FaqDao {
	 
	public void updateFaq(Faq faq) {
		getSession().saveOrUpdate(faq);
		getSession().flush();
	} 
	
	@Override
	public Integer getFaqListSize() {
		String hql = "select count(*) from Faq";
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Faq> getFaq(Integer start, Integer pageSize) {
		String hql = "from Faq order by rank desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<Faq> aa = (List<Faq>) query.list();
		return aa;
	}
	@SuppressWarnings("unchecked")
	@Override 
	public List<Object> findByHQL(String hql, Map<String, Object> map) {
		Query query = getSession().createQuery(hql);
		if (map != null) {
			Iterator<?> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				query.setParameter(key.toString(), val); 
			}
		} 
		return query.list();
	}  
	@SuppressWarnings("unchecked")
	@Override
	public List<Object>  findPage(String hql,int offset,int pageSize,  Map<String, Object> map){
    	Query query = this.getSession().createQuery(hql); 
    	if (map != null) {
			Iterator<?> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue(); 
				query.setParameter(key.toString(), val); 
			}
		}   
        offset=offset<0?0:offset;
        return query.setFirstResult(offset).setMaxResults(pageSize).list(); 
    }
	 
	public int findCount(String hql, Map<String, Object> map) {
		int recordCount = 0;
		List<?> list = findByHQL(hql, map);
		if (list == null || list.size() == 0) {
			return recordCount;
		}
		recordCount = ((Number) list.get(0)).intValue();
		return recordCount;
	}
	public Faq findById(Serializable id) {  
        return (Faq) this.getSession().get(Faq.class, id);  
    }  
	
}
