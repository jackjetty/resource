package com.detachment.dao;

import java.util.ArrayList;

import com.detachment.pojo.TbProcedure;

public interface ProcedureDao extends BaseDao<TbProcedure> {

	public ArrayList<TbProcedure> findAll();
	
	public ArrayList<TbProcedure> findTbProcedureByProcessId(String processId);
	public String getProcedureTip(String processId,String procedureId);

}
