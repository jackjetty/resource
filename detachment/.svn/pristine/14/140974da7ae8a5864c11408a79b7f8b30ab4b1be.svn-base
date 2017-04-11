package cn.rising.redis.dao.impl; 
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.rising.base.RedisGeneratorDao;
import cn.rising.model.SessionModel;
import cn.rising.redis.dao.SessionRedisDao;
import cn.rising.util.SerializeUtil;

 
 


/**
 * 参看博客 <br>
 * -------------------------------------------------------------------------------
 *  http://blog.csdn.net/java2000_wl/article/details/8543203
 * -------------------------------------------------------------------------------
 */

@Repository(value="sessionRedisDao")
public class SessionRedisDaoImpl extends RedisGeneratorDao<String,SessionModel> implements SessionRedisDao{
	public static String PREFIX_KEY = "session:";  
  
  /**
   * 添加对象
   */
  @Override
  public boolean add(final SessionModel sessionModel) {  
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
      public Boolean doInRedis(RedisConnection connection)  
          throws DataAccessException {  
        RedisSerializer<String> serializer = getRedisSerializer();   
        byte[] key  = serializer.serialize(PREFIX_KEY+sessionModel.getOpenId());  
        
        
        byte[] name = SerializeUtil.serialize(sessionModel); 
        return connection.setNX(key, name);  
      }  
    });  
    return result;  
  }  

  /**
   * 添加集合
   */
  @Override
  public boolean add(final List<SessionModel> list) {
    Assert.notEmpty(list);  
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
      public Boolean doInRedis(RedisConnection connection)  
          throws DataAccessException {  
        RedisSerializer<String> serializer = getRedisSerializer();   
        for (SessionModel sessionModel : list) {  
          byte[] key  = serializer.serialize(PREFIX_KEY+sessionModel.getOpenId());  
          byte[] name = SerializeUtil.serialize(sessionModel );  
          connection.setNX(key, name);  
        }  
        return true;  
      }  
    }, false, true);  
    return result; 
  }  
  
  /**
   * 删除对象 ,依赖key
   */
  public void delete(String key) {  
    List<String> list = new ArrayList<String>();  
    list.add(PREFIX_KEY+key);  
    delete(list);  
  }  
  
  /**
   * 删除集合 ,依赖key集合
   */
  public void delete(List<String> keys) {  
    redisTemplate.delete(PREFIX_KEY+keys);  
  }  
  
  /**
   * 修改对象 
   */
  public boolean update(final SessionModel sessionModel) {  
    String key =  sessionModel.getOpenId();  
    if (get(key) == null) {  
      throw new NullPointerException("数据行不存在, key = " + key);  
    }  
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
      public Boolean doInRedis(RedisConnection connection)  
          throws DataAccessException {  
        RedisSerializer<String> serializer = getRedisSerializer();  
        byte[] key  = serializer.serialize(PREFIX_KEY+ sessionModel.getOpenId());  
        byte[] name = SerializeUtil.serialize(sessionModel);  
        connection.set(key, name);  
        return true;  
      }  
    });  
    return result;  
  }  
  
  /**
   * 根据key获取对象
   */
  public SessionModel get(final String keyId) {  
	  SessionModel result = redisTemplate.execute(new RedisCallback<SessionModel>() {  
      public SessionModel doInRedis(RedisConnection connection)  
          throws DataAccessException {  
        RedisSerializer<String> serializer = getRedisSerializer();  
        byte[] key = serializer.serialize(PREFIX_KEY+keyId);  
        byte[] value = connection.get(key);
        
        if (value == null) {  
          return null;  
        }  
         return (SessionModel)SerializeUtil.unserialize(value);
        //return new Member(keyId, objectsTranscoder.deserialize(value));  
      }  
    });  
    return result;  
  }  

}