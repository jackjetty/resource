package cn.rising.model;
 
import java.util.HashMap;
 
import java.util.Map;    

import cn.rising.base.BaseModel;
 
public class SessionModel extends BaseModel { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6556407013643087809L;
	private  String openId;
	private  Long lastAccessedTime;
	public   Map<String, Object> map= new HashMap<String, Object>();
	
	public Long getLastAccessedTime() {
		return lastAccessedTime;
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setLastAccessedTime(Long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	public   void setAttribute(String key ,Object object){
		map.put(key, object); 
	}
	public   Object getAttribute(String key){
		return map.get(key);
	} 
	
}