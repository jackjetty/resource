package com.traffic.dao.impl;

import org.springframework.stereotype.Repository;  

  
import com.traffic.dao.TenderDao;
import com.traffic.pojo.TbTender; 
@Repository("tenderDao")
public class TenderDaoImpl extends BaseDaoImpl<TbTender> implements TenderDao{

}
