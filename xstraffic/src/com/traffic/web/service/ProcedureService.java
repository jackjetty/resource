package com.traffic.web.service;

import java.util.ArrayList;

import com.traffic.pojo.TbProcedure;


public interface ProcedureService {

	public ArrayList<TbProcedure> getProcedureByAccident();

	public ArrayList<TbProcedure> getProcedureByMoveCar();

}
