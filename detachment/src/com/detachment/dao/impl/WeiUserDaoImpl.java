package com.detachment.dao.impl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbWeiUser;
@Repository("weiUserDao")
public class WeiUserDaoImpl    extends BaseDaoImpl<TbWeiUser> implements  WeiUserDao{
	@Override
	public void saveOrUpdate(TbWeiUser tbWeiUser) { 
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();  
		if(tbWeiUser.getCreateTime()==null){
			tbWeiUser.setCreateTime (new Timestamp(date.getTime()));  
		}
   	 
        this.getSession().saveOrUpdate(tbWeiUser);  
        this.getSession().flush();
    } 
 
}