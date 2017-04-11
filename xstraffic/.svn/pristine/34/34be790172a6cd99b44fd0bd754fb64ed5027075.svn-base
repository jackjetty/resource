package com.traffic.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbInfoPic;
import com.traffic.pojovo.TbCarMoveVo;
import com.traffic.web.service.MoveCarService;
import com.traffic.wei.service.CustResService;

@Controller("moveCarAction")
public class MoveCarAction implements ServletRequestAware, ServletResponseAware, ServletContextAware,ApplicationAware{
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String reportPhoneNumber;
	private String carNumber;
	private String carMoveState;
	private String carMoveId;
	private String remark;
	private String managerName;
	private HashMap<String, Object> result;
	private ArrayList<TbInfoPic> tap;
	private TbCarMoveVo moveCarVo;
	

	private HttpServletRequest request; 
    private ServletContext servletContext; 
    private HttpServletResponse response; 
    private Map <String, Object>application; 
    
	private ArrayList<String> procedureList;
	private String procedureIds;
	private String custResText;
	
	
	@Autowired
	MoveCarService moveCarService;
	@Autowired
	CustResService custResService;

	public String doMoveCar() {
		return "success";
	}

	public String getCarMove() {
		result = moveCarService.getCarMove(startTime, endTime, reportPhoneNumber,
				carNumber, carMoveState, pageSize, pageIndex);
		return "success";
	}
	public String getCarMovePic(){
		tap = moveCarService.getCarMovePic(carMoveId);
		return "success";
		
	}
	public String getMoveCarAddress(){
		result = moveCarService.getMoveCarAddress(carMoveId);
		return "success";
	}
	public String updateCarMoveState(){
		result = moveCarService.exchangeStatus(carMoveId,managerName);
		return "success";
	}
	
	public String saveFailInfo(){
		result = moveCarService.saveFailInfo(carMoveId,managerName,remark);
		return "success";
	}
	
	public String getMoveCarByCarMoveId(){
		moveCarVo=moveCarService.getCarMoveByCarMoveId(carMoveId);
		return "success";
	}
	public String saveProcedureMessageMove(){
		result =moveCarService.saveprocedureMessage1(managerName, carMoveId);
		Integer respCode = (Integer) result.get("respCode");
		if (respCode == 0) {
			try {
				custResService.throughCarMoveAudit(application, carMoveId);
				result.put("respCode", 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	public String responseWeiMoveCar(){
		result = moveCarService.responseWei(carMoveId);
		procedureList = new ArrayList<String>();
		String[] args = procedureIds.split(",");
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null && !"".equals(args[i])) {
				procedureList.add(args[i]);
			}
		}
		try {
			custResService.failCarMoveAudit(application, carMoveId, custResText, procedureList);
			result.put("respCode", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getReportPhoneNumber() {
		return reportPhoneNumber;
	}

	public void setReportPhoneNumber(String reportPhoneNumber) {
		this.reportPhoneNumber = reportPhoneNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarMoveState() {
		return carMoveState;
	}

	public void setCarMoveState(String carMoveState) {
		this.carMoveState = carMoveState;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getCarMoveId() {
		return carMoveId;
	}

	public void setCarMoveId(String carMoveId) {
		this.carMoveId = carMoveId;
	}

	public ArrayList<TbInfoPic> getTap() {
		return tap;
	}

	public void setTap(ArrayList<TbInfoPic> tap) {
		this.tap = tap;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public TbCarMoveVo getMoveCarVo() {
		return moveCarVo;
	}

	public void setMoveCarVo(TbCarMoveVo moveCarVo) {
		this.moveCarVo = moveCarVo;
	}
	
	@Override 
    public void setApplication(Map<String, Object> application) { 
        this.application= application; 
    }
    @Override 
    public void setServletRequest(HttpServletRequest req) { 
        this.request=req; 
    } 
    @Override 
    public void setServletResponse(HttpServletResponse res) { 
        this.response=res; 
    } 
    @Override 
    public void setServletContext(ServletContext ser) { 
        this.servletContext=ser; 
    }

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public ArrayList<String> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(ArrayList<String> procedureList) {
		this.procedureList = procedureList;
	}

	public String getProcedureIds() {
		return procedureIds;
	}

	public void setProcedureIds(String procedureIds) {
		this.procedureIds = procedureIds;
	}

	public String getCustResText() {
		return custResText;
	}

	public void setCustResText(String custResText) {
		this.custResText = custResText;
	} 

	
	

}
