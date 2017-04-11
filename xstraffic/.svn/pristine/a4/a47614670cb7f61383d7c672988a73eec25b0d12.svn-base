package com.traffic.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.CustResDao;
import com.traffic.pojo.TbCustRes;
@Repository("custResDao")
public class CustResDaoImpl extends BaseDaoImpl<TbCustRes> implements CustResDao {

	public int getCount(String openId,Timestamp custResTime,String custResType){
		String hql="select count(*) from TbCustRes where openId=? and custResTime>=?  and custResType=? ";
		int count=this.findCount(hql, openId,custResTime,custResType);
		 
		return count;
	} 
}
