package com.traffic.web.action;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbProcedure;
import com.traffic.web.service.ProcedureService;


@Controller("procedureAction")
public class ProcedureAction {

	@Autowired ProcedureService procedureService;
	
	private ArrayList<TbProcedure> atp;
    private String accidentId;
    private String accidentState;
    
    
    
	public String getAccidentState() {
		return accidentState;
	}
	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}
	public String getAccidentId() {
		return accidentId;
	}
	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}
	public String getSelectCaseByAccident() {
		atp = procedureService.getProcedureByAccident();
		return "success";
	}
	public String getSelectCaseByMoveCar(){
		atp = procedureService.getProcedureByMoveCar();
		return "success";
	}
	 

	public ArrayList<TbProcedure> getAtp() {
		return atp;
	}

	public void setAtp(ArrayList<TbProcedure> atp) {
		this.atp = atp;
	}

}
