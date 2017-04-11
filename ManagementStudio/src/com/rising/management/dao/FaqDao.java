package com.rising.management.dao;
import java.util.*; 
import java.io.Serializable;
import com.rising.management.bean.Faq;
public interface FaqDao {
	public void updateFaq(Faq a);
	public Integer getFaqListSize();
	public Faq findById(Serializable id);
	public List<Faq> getFaq(Integer start, Integer pageSize);
	public List<Object>  findPage(String hql,int offset,int pageSize,  Map<String, Object> map);
	public List<Object>  findByHQL(String hql,Map<String, Object> map) ;  
	public int  findCount(String hql, Map<String, Object> map);
	
}