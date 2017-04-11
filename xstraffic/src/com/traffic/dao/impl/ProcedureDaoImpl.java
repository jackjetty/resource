package com.traffic.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.traffic.dao.ProcedureDao;
import com.traffic.pojo.TbProcedure;
import com.traffic.pojo.TbProcedureId;
import com.traffic.util.CommonUtil;


@Repository("procedureDao")
public class ProcedureDaoImpl extends BaseDaoImpl<TbProcedure> implements ProcedureDao {

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
	public ArrayList<TbProcedure> findAllByAccident() {
		String hql = "from TbProcedure where processId ='ACCIDENT' order by showOrder";
		ArrayList<TbProcedure> atp = (ArrayList<TbProcedure>) getSession().createQuery(hql).list();
		return atp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProcedure> findAllByMoveCar() {
		String hql = "from TbProcedure where processId ='CARMOVE' order by showOrder";
		ArrayList<TbProcedure> atp = (ArrayList<TbProcedure>) getSession().createQuery(hql).list();
		return atp;
	}
}
