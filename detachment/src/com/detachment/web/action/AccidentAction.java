package com.detachment.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.TbInfoText;
import com.detachment.pojo.vo.TbAccidentJsp;
import com.detachment.pojo.vo.TbAccidentStatisticsVo;
import com.detachment.pojo.vo.TbFormalAccidentVo;
import com.detachment.util.ExcelUtil;
import com.detachment.web.service.AccidentService;
import com.detachment.web.service.TbTipService;
import com.detachment.wei.service.CustResService;

@Controller("accidentAction")
public class AccidentAction implements ServletRequestAware,
		ServletResponseAware, ServletContextAware, ApplicationAware {

	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String reportPhoneNumber;
	private String accidentId;
	private String reporterType;
	private String nickName;
	private String departmentId;
	private String formlAccidentIds;
	private String openId;
	private String emergencyCall;
	private String formlAccidentId;
	private Integer chujingType;
	private String accidentState;

	private String policeOpnContent;
	private String claimOpnContent;
	private String managerName;
	private ArrayList<TbInfoPic> tap;

	private HashMap<String, Object> result;
	private ArrayList<TbInfoText> tav;
	ArrayList<TbAccidentJsp> list;
	private ArrayList<TbAccidentStatisticsVo> tasvo;

	private HttpServletRequest request;
	private ServletContext servletContext;
	private HttpServletResponse response;
	private Map<String, Object> application;

	private ArrayList<String> procedureList;

	private String procedureIds;

	private String custResText;
	
	private TbFormalAccidentVo   accidentVo;
	

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	@Override
	public void setServletContext(ServletContext ser) {
		this.servletContext = ser;
	}

	@Autowired
	AccidentService accidentService;

	@Autowired
	CustResService custResService;
	@Autowired
	TbTipService tbTipService;

	public String doAccident() {
		return "success";
	}

	public String getAccident() {
		result = accidentService.getAccident(startTime, endTime,accidentState,
				reportPhoneNumber, nickName, pageSize, pageIndex, emergencyCall,
				departmentId);
		return "success";
	}

	public String getTbAccidentText() {
		tav = accidentService.getTbAccidentText(accidentId);
		return "success";
	}

	public String reDevelopDepartment() {
		result = accidentService.reDevelopDepartment(departmentId,
				formlAccidentIds);
		return "success";
	}

	public String getAccidentNumber(){
		result = accidentService.getAccidentNumber();
		return "success";
	}
	
	public String getTbTipRecordNum(){
		result =tbTipService.getTbTipRecordNum("ACCIDENT", "RESUBMIT");
		return "success";
	}
	public String updateTbTipRecordNum(){
		//result =tbTipService.updateRecordNum("ACCIDENT", "RESUBMIT");
		return "success";
	}
	
	
	
	public String getaccidentJsp() {
		return "success";
	}

	public String getRecordsByOpenId() {
		list = accidentService.getAccidentJsp(openId, pageSize, pageIndex);
		return "success";
	}
	
	public String  doTest(){
		return "success";
	}

	public String getTbAddress() {
		result = accidentService.getTbAddress(accidentId);
		return "success";
	}

	public String getTbAccidentPic() {
		tap = accidentService.getTbAccidentPic(accidentId);
		return "success";
	}

	public String saveProcedureMessage() {
		result = accidentService.saveprocedureMessage(managerName, accidentId);
		Integer respCode = (Integer) result.get("respCode");
		if (respCode == 0) {
			try {
				custResService.throughAccidentAudit(application, accidentId);
				result.put("respCode", 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	public String responseWei() {
		if(chujingType==1){
			result = accidentService.responseWei(accidentId,"现场核实");
			ErrCodeBean eb=custResService.responseTextMessage(openId, custResText);
			result.put("respCode", eb.getErrcode());
		}else{
			result = accidentService.responseWei(accidentId,"待定");
			procedureList = new ArrayList<String>();
			String[] args = procedureIds.split(",");
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null && !"".equals(args[i])) {
					procedureList.add(args[i]);
				}
			}
			try {
				ErrCodeBean eb=custResService.failAccidentAudit(application, accidentId,
						custResText, procedureList);
				result.put("respCode", eb.getErrcode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return "success";
	}

	public String updateAccidentStateMalicious(){
		result = accidentService.updateAccidentStateMalicious(managerName, accidentId);
		Integer respCode = (Integer) result.get("respCode");
		if (respCode == 0) {
			try {
				custResService.maliciousAccidentAudit(application, accidentId);
				result.put("respCode", 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	
	public void toExportData() {
		try {
			accidentState=URLDecoder.decode(accidentState, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		
		ArrayList<TbFormalAccidentVo> oiv = accidentService.getAccident(
				nickName, reportPhoneNumber, emergencyCall, startTime, endTime,
				departmentId,accidentState);
		String filename = null;
		try {
			filename = new String("交通事故信息".getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filename + ".xls");
		try {
			ExcelUtil.exportClassroom(response.getOutputStream(), oiv);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewImages() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// pic为读取到图片的存储路径(数据库中存储的字段值)
		String pic;
		try {
			pic = new String(
					(request.getParameter("picPath")).getBytes("ISO-8859-1"),
					"utf-8");
			File picFile = new File(pic);
			FileInputStream is = new FileInputStream(picFile.getAbsolutePath());
			int i = is.available(); // 得到文件大小
			byte data[] = new byte[i];
			is.read(data); // 读数据
			is.close();
			response.setContentType("jpg");  
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(picFile.getName().getBytes("gb2312"), "ISO8859-1"));
			response.setHeader("Content_Length",new Integer(i).toString());
			//response.setContentType("jpg/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} catch (UnsupportedEncodingException e) {

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
		}

	}
	
	
	public String getAccidentByAccidentId(){
		accidentVo=accidentService.getAccidentByAccidentId(accidentId);
		return "success";
	}
	
	
	public String updateAccidentStateOther(){
		result = accidentService.updateAccidentStateOther(accidentState, accidentId);
		return "success";
	}
	
	public String getAccidentStatistics(){
		tasvo=accidentService.getAccidentStatistics(startTime, endTime);
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

	public String getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	public String getReporterType() {
		return reporterType;
	}

	public void setReporterType(String reporterType) {
		this.reporterType = reporterType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public ArrayList<TbInfoText> getTav() {
		return tav;
	}

	public void setTav(ArrayList<TbInfoText> tav) {
		this.tav = tav;
	}

	public ArrayList<TbAccidentJsp> getList() {
		return list;
	}

	public void setList(ArrayList<TbAccidentJsp> list) {
		this.list = list;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPoliceOpnContent() {
		return policeOpnContent;
	}

	public void setPoliceOpnContent(String policeOpnContent) {
		this.policeOpnContent = policeOpnContent;
	}

	public String getClaimOpnContent() {
		return claimOpnContent;
	}

	public void setClaimOpnContent(String claimOpnContent) {
		this.claimOpnContent = claimOpnContent;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public ArrayList<TbInfoPic> getTap() {
		return tap;
	}

	public void setTap(ArrayList<TbInfoPic> tap) {
		this.tap = tap;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getFormlAccidentIds() {
		return formlAccidentIds;
	}

	public void setFormlAccidentIds(String formlAccidentIds) {
		this.formlAccidentIds = formlAccidentIds;
	}

	public ArrayList<String> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(ArrayList<String> procedureList) {
		this.procedureList = procedureList;
	}

	public String getCustResText() {
		return custResText;
	}

	public void setCustResText(String custResText) {
		this.custResText = custResText;
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

	public String getProcedureIds() {
		return procedureIds;
	}

	public void setProcedureIds(String procedureIds) {
		this.procedureIds = procedureIds;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public String getEmergencyCall() {
		return emergencyCall;
	}

	public void setEmergencyCall(String emergencyCall) {
		this.emergencyCall = emergencyCall;
	}

	public TbFormalAccidentVo getAccidentVo() {
		return accidentVo;
	}

	public void setAccidentVo(TbFormalAccidentVo accidentVo) {
		this.accidentVo = accidentVo;
	}

	public String getFormlAccidentId() {
		return formlAccidentId;
	}

	public void setFormlAccidentId(String formlAccidentId) {
		this.formlAccidentId = formlAccidentId;
	}

	public Integer getChujingType() {
		return chujingType;
	}

	public void setChujingType(Integer chujingType) {
		this.chujingType = chujingType;
	}

	public String getAccidentState() {
		return accidentState;
	}

	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}

	public ArrayList<TbAccidentStatisticsVo> getTasvo() {
		return tasvo;
	}

	public void setTasvo(ArrayList<TbAccidentStatisticsVo> tasvo) {
		this.tasvo = tasvo;
	}
	

}
