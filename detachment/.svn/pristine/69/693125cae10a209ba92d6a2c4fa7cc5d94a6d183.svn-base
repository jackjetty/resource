package com.detachment.web.action;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbProcedure;
import com.detachment.web.service.ProcedureService;

@Controller("procedureAction")
public class ProcedureAction {

	@Autowired ProcedureService procedureService;
	
	private ArrayList<TbProcedure> atp;

	public String getSelectCase() {
		atp = procedureService.getProcedure();
		return "success";
	}
	public String getSelectCaseByMoveCar(){
		atp = procedureService.getProcedureByProcessId("CARMOVE");
		return "success";
	}
	public String getSelectCaseByHandyPhoto(){
		atp = procedureService.getProcedureByProcessId("HANDYPHOTO");
		return "success";
	}
	
	
	
	public ArrayList<TbProcedure> getAtp() {
		return atp;
	}

	public void setAtp(ArrayList<TbProcedure> atp) {
		this.atp = atp;
	}

}
