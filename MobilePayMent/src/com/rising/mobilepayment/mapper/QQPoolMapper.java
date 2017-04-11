 
package com.rising.mobilepayment.mapper; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
 

import com.rising.mobilepayment.bean.QQPool; 

@Component("qqPoolMapper")
public interface QQPoolMapper {
	public List<QQPool> getUnCheckList(HashMap<String, Object> map );
	
	public void update(QQPool qqPool);
	
	public List<QQPool>  getPayQQPoolList(HashMap<String, Object> map);
	
	
	public Integer getQBRestQuota(HashMap<String, Object> map);
	
}