package com.detachment.web.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.ProcedureDao;
import com.detachment.pojo.TbProcedure;
import com.detachment.web.service.ProcedureService;

@Service
public class ProcedureServiceImpl implements ProcedureService {
	
	@Autowired ProcedureDao procedureDao;

	@Override
	public ArrayList<TbProcedure> getProcedure() {
		ArrayList<TbProcedure> atp = procedureDao.findAll();
		return atp;
	}

	@Override
	public ArrayList<TbProcedure> getProcedureByProcessId(String processId) {
		ArrayList<TbProcedure> atp = procedureDao.findTbProcedureByProcessId(processId);
		return atp;
	}

}
