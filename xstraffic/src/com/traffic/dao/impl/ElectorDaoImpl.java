package com.traffic.dao.impl;

import org.springframework.stereotype.Repository;  

  
import com.traffic.dao.ElectorDao;
import com.traffic.pojo.TbElector;
@Repository("electorDao")
public class ElectorDaoImpl extends BaseDaoImpl<TbElector> implements  ElectorDao{

}
