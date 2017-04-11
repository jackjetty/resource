package com.detachment.web.action;

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
import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.vo.TbHandyPhotoVo;
import com.detachment.web.service.HandyPhotoService;
import com.detachment.wei.service.CustResService;


@Controller("handyPhotoAction")
public class HandyPhotoAction implements ServletRequestAware, ServletResponseAware, ServletContextAware,ApplicationAware{

	private String handyPhotoId;
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String reportPhoneNumber;
	private String nickName;
	private String managerName;
	private String handyPhotoState;
	private HashMap<String, Object> result;
	private TbHandyPhotoVo tbHandyPhotoVo;
	
	private ArrayList<String> procedureList;
	private String procedureIds;
	private String custResText;
	private HttpServletRequest request; 
    private ServletContext servletContext; 
    private HttpServletResponse response; 

    private Map <String, Object> application; 
	
	
    @Autowired
	CustResService custResService;
	@Autowired
	HandyPhotoService handyPhotoService;

	public String doHandyPhoto() {
		return "success";
	}

	public String getHandyPhoto() {
		result = handyPhotoService.getHandyPhoto(startTime, endTime,
				reportPhoneNumber, nickName, pageSize, pageIndex,handyPhotoState);
		return "success";
	}

	public String getTpAddress() {
		result = handyPhotoService.getTbAddress(handyPhotoId);
		return "success";
	}

	public String updateState() {
		result = handyPhotoService.updateState(handyPhotoId, managerName);
		return "success";
	}
	
	public String getHandyPhotoByTakId(){
		
		tbHandyPhotoVo=handyPhotoService.getHandyPhotoById(handyPhotoId);
		return "success";
	}
	
	public String saveProcedureMessageHandyPhoto(){
			try {
				ErrCodeBean ecb=custResService.throughHandyPhotoAudit(application, handyPhotoId);
				if("0".equals(ecb.getErrcode())){
					result=handyPhotoService.saveProcedureMessageHandyPhoto(managerName, handyPhotoId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return "success";
	}
	public String responseWeiHandyPhoto(){
		procedureList = new ArrayList<String>();
		String[] args = procedureIds.split(",");
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null && !"".equals(args[i])) {
				procedureList.add(args[i]);
			}
		}
		try {
			ErrCodeBean ecb=custResService.failHandyPhotoAudit(application, handyPhotoId, custResText, procedureList);
			if("0".equals(ecb.getErrcode())){
				result=handyPhotoService.responseWeiHandyPhoto(handyPhotoId);
			}
		} catch (Exception e) {
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

	public String getHandyPhotoState() {
		return handyPhotoState;
	}

	public void setHandyPhotoState(String handyPhotoState) {
		this.handyPhotoState = handyPhotoState;
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

	public String getHandyPhotoId() {
		return handyPhotoId;
	}

	public void setHandyPhotoId(String handyPhotoId) {
		this.handyPhotoId = handyPhotoId;
	}

	 

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public TbHandyPhotoVo getTbHandyPhotoVo() {
		return tbHandyPhotoVo;
	}

	public void setTbHandyPhotoVo(TbHandyPhotoVo tbHandyPhotoVo) {
		this.tbHandyPhotoVo = tbHandyPhotoVo;
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
