package com.detachment.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.detachment.dao.TipDao;
import com.detachment.pojo.TbTip;
@Repository("tipDao")
public class TipDaoImpl    extends BaseDaoImpl<TbTip> implements  TipDao{

	

	@Override
	public void updateRecordNum(String processId, String tipType,
			String departmentId) {
		String hql = "update TbTip set recordNum = 0 where processId =:processId and tipType = :tipType and departmentId = :departmentId";
		getSession().createQuery(hql).setParameter("processId", processId).setParameter("tipType", tipType)
		.setParameter("departmentId", departmentId).executeUpdate();
		getSession().flush();
		
	}
	 
}