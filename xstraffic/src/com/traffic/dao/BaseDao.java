package com.traffic.dao; 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import org.hibernate.Session; 
public interface BaseDao<T> {
	public Session getSession();
	public T findById(Serializable id); 
	public void save(T entity);   
	public void saveOrUpdate(T entity);
    public void update(T entity);   
    public void delete(T entity);
    public List<T> findByHQL(String hql, Object... params);  
    public List<T> findByHQL(String hql, ArrayList<Object> arraylist);  
    public List<T> findPage(String hql,int offset,int pageSize, Object... params);
    public List<T> findPage(String hql,int offset,int pageSize, ArrayList<Object> arraylist);
    public int findCount(String hql, Object... params);
    public int findCount(String hql, ArrayList<Object> arraylist);
    public List<T> findBySQL(String sql, Object... params); 
    public List<T> findBySQL(String sql, ArrayList<Object> arraylist);
    
    
}
