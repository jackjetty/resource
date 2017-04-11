package com.traffic.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 


import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.traffic.pojo.TbInfoPic;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbInfoTextVo;
import com.traffic.util.CommonUtil;
import com.traffic.util.ExcelUtil;
import com.traffic.web.service.AccidentService;
import com.traffic.web.service.TbTipService;
import org.rising.wei.bean.ErrCodeBean;
import com.traffic.wei.service.CustResService;

@Controller("accidentAction")
public class AccidentAction implements ServletRequestAware, ServletResponseAware, ServletContextAware,ApplicationAware{
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String reportPhoneNumber;
	private String accidentId;
	private String reporterType;
	private String nickName;
	private String policeOpnContent;
	private String claimOpnContent;
	private String managerName;
	private HashMap<String, Object> result;
	private ArrayList<TbInfoPic> tap;
	private ArrayList<TbInfoTextVo> tav;
	
	private ArrayList<String> procedureList;
	private String procedureIds;
	private String custResText;
	private TbAccidentVo accidentVo;
	
	// Report输出流
	public InputStream reportStream;
	
	private HttpServletRequest request; 
    private ServletContext servletContext; 
    private HttpServletResponse response; 

    private Map <String, Object> application; 
	
    // 输出日期
    private String startDate;
    private String endDate;
	
	@Autowired
	private AccidentService accidentService;
	@Autowired
	private CustResService custResService;
	@Autowired
	private TbTipService tbTipService;

	public String doAccident() {
		startDate = CommonUtil.getBeforeWeekDateStrForm();
		endDate = CommonUtil.getCurrentDateStrForm();
		return "success";
	}
	
	public String dgAccidentAuditFail(){
		return "success"; 
	}
	
	public String accidentDetail(){ 
		result = accidentService.accidentDetail(accidentId);
		return "success";
	}
	

	public String getAccident() {
		result = accidentService.getAccident(startTime, endTime,
				reportPhoneNumber, nickName, pageSize, pageIndex, reporterType,
				accidentId);
		return "success";
	}

	public String getTbNumber() {
		result = accidentService.getTbNumber();
		return "success";
	}

	public String getTbAccidentText() {
		tav = accidentService.getTbAccidentText(accidentId);
		return "success";
	}

	public String saveProcedureMessage() {
		if ("".equals(policeOpnContent)) {
			policeOpnContent = null;
		}
		if ("".equals(claimOpnContent)) {
			claimOpnContent = null;
		}
		result = accidentService.saveprocedureMessage(managerName,
				policeOpnContent, claimOpnContent, accidentId);
		return "success";
	}

	public String getTbAccidentPic() {
		tap = accidentService.getTbAccidentPic(accidentId);
		return "success";
	}

	public String getAccidentNumber() {
		result = accidentService.getAccidentNumber();
		return "success";
	}

	public String getTbAddress() {
		result = accidentService.getTbAddress(accidentId);
		return "success";
	}

	public void viewImages()   {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// pic为读取到图片的存储路径(数据库中存储的字段值)
		
		FileInputStream is=null;
		OutputStream outputStream=null;
		
		try{
			String pic = new String(
					(request.getParameter("picPath")).getBytes("ISO-8859-1"),
					"utf-8");
			File picFile = new File(pic);
			is = new FileInputStream(picFile.getAbsolutePath());
			response.setContentType("jpg");  
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(picFile.getName().getBytes("gb2312"), "ISO8859-1"));
			
			
			final int MAX_BYTE = 6000;
			long streamTotal = 0;  //接受流的容量
			int streamNum = 0;  //流需要分开的数量
			int leave = 0;  //文件剩下的字符数
			byte[] inOutb;  //byte数组接受文件的数据
			//通过available方法取得流的最大字符数
			streamTotal = is.available();
			//取得流文件需要分开的数量
			streamNum = (int)Math.floor(streamTotal/MAX_BYTE);
			//分开文件之后,剩余的数量
			leave = (int)streamTotal % MAX_BYTE;
			
			
			response.setHeader("Content_Length",new Long(streamTotal).toString());
			outputStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			
			//文件的容量大于60Mb时进入循环
			if (streamNum > 0) {
					for(int i = 0; i < streamNum; ++i){
					inOutb = new byte[MAX_BYTE];
					//读入流,保存在byte数组
					is.read(inOutb, 0, MAX_BYTE);
					outputStream.write(inOutb);  //写出流
					//outputStream.flush();  //更新写出的结果
			      }
			}
			
			
			//写出剩下的流数据
			inOutb = new byte[leave];
			is.read(inOutb, 0, leave);
			outputStream.write(inOutb);
			outputStream.flush();
			 
			
			
			//int i = is.available(); // 得到文件大小
			//byte data[] = new byte[i];
			//is.read(data); // 读数据
			
			//outputStream.write(data); // 输出数据
			//outputStream.flush();
			
		}catch(Exception ex){
			//ex.printStackTrace();
		}
		finally{
			if(is!=null){
				try{
					is.close();
					 
				}catch(Exception ex){
				}
				is=null;
			}
			if(outputStream!=null){
				try{
					outputStream.close(); 
					 
					
				}catch(Exception ex){
				}
				
				outputStream=null;
			}
			
		}
		
		
		
		 
		
		

	}

	public String saveProcedureMessage1(){
		result=new HashMap<String, Object>();
		ErrCodeBean errCodeBean=custResService.throughAccidentAudit(application, accidentId);
		if(!errCodeBean.getErrcode().equalsIgnoreCase("0")){
			result.put("respCode", new Integer(errCodeBean.getErrcode()).intValue());
			result.put("respInfo", errCodeBean.getErrmsg() );
			return "success"; 
		}
		
		result = accidentService.saveprocedureMessage1(  accidentId);
		return "success";
	}
	
	// !tinker 2014-09-12
	public String responseWei() {
		procedureList = new ArrayList<String>();
		String[] args = procedureIds.split(",");
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null && !"".equals(args[i])) {
				procedureList.add(args[i]);
			}
		}
		 
		ErrCodeBean errCodeBean = custResService.failAccidentAudit(application, accidentId, custResText, procedureList);
		if (errCodeBean.getErrcode().equals("0")) {
			result = accidentService.responseWei(accidentId);
		}
		return "success";
	}
	
	
	public String getAccidentByAccidentId(){
		result=accidentService.getAccidentByAccidentId(accidentId);
		return "success";
	}
	
	public String getTbTipRecordNum(){
		result =tbTipService.getTbTipRecordNum("FORMALACCIDENT", "RESUBMIT");
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

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public ArrayList<TbInfoPic> getTap() {
		return tap;
	}

	public void setTap(ArrayList<TbInfoPic> tap) {
		this.tap = tap;
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

	public InputStream getReportStream() {
		return reportStream;
	}

	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
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

	public ArrayList<TbInfoTextVo> getTav() {
		return tav;
	}

	public void setTav(ArrayList<TbInfoTextVo> tav) {
		this.tav = tav;
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

	public TbAccidentVo getAccidentVo() {
		return accidentVo;
	}

	public void setAccidentVo(TbAccidentVo accidentVo) {
		this.accidentVo = accidentVo;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public Map<String, Object> getApplication() {
		return application;
	} 
    
    
}
