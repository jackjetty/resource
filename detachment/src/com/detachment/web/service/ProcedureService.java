package com.detachment.web.service;

import java.util.ArrayList;

import com.detachment.pojo.TbProcedure;

public interface ProcedureService {

	public ArrayList<TbProcedure> getProcedure();
	
	public ArrayList<TbProcedure> getProcedureByProcessId(String processId);
}
