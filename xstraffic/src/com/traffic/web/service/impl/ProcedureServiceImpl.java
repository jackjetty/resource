package com.traffic.web.service.impl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ProcedureDao;
import com.traffic.pojo.TbProcedure;
import com.traffic.web.service.ProcedureService;

@Service("procedureService")
public class ProcedureServiceImpl implements ProcedureService {
	Log log = LogFactory.getLog(ProcedureServiceImpl.class);
	@Autowired ProcedureDao procedureDao;

	@Override
	public ArrayList<TbProcedure> getProcedureByAccident() {
		ArrayList<TbProcedure> atp = procedureDao.findAllByAccident();
		return atp;
	}

	@Override
	public ArrayList<TbProcedure> getProcedureByMoveCar() {
		ArrayList<TbProcedure> atp = procedureDao.findAllByMoveCar();
		return atp;
	}

}
