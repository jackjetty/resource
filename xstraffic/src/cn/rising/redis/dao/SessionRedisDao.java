package cn.rising.redis.dao;
import java.util.List;

import cn.rising.model.SessionModel;

 

 

public interface SessionRedisDao {
	public boolean add(final  SessionModel sessionModel) ;
	public boolean add(final List<SessionModel> list);
	public void delete(String key);
	public void delete(List<String> keys);
	public boolean update(final SessionModel sessionModel) ;
	public SessionModel get(final String keyId);
     
}