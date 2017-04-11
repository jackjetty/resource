package com.detachment.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.detachment.dao.ProcedureDao;
import com.detachment.pojo.TbProcedure;
import com.detachment.pojo.TbProcedureId;
import com.detachment.util.CommonUtil;
 

@Repository
public class ProcedureDaoImpl extends BaseDaoImpl<TbProcedure> implements ProcedureDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProcedure> findAll() {
		String hql = "from TbProcedure where processId = 'ACCIDENT' order by showOrder";
		ArrayList<TbProcedure> atp = (ArrayList<TbProcedure>) getSession().createQuery(hql).list();
		return atp;
	}
	public String getProcedureTip(String processId,String procedureId){
		if(processId==null||procedureId==null)
			 return "";
		String procedureTip="";
		TbProcedureId tbProcedureId=new TbProcedureId();
		tbProcedureId.setProcedureId(procedureId);
		tbProcedureId.setProcessId(processId);
		TbProcedure tbProcedure=this.findById(tbProcedureId);
		if(tbProcedure!=null){
			procedureTip=CommonUtil.trim(tbProcedure.getProcedureTip());
		}
		return procedureTip;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProcedure> findTbProcedureByProcessId(String processId) {
		String hql = "from TbProcedure where processId = :processId order by showOrder";
		ArrayList<TbProcedure> atp = (ArrayList<TbProcedure>) getSession().createQuery(hql)
				.setParameter("processId", processId).list();
		return atp;
	}


}
