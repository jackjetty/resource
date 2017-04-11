package com.traffic.web.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.util.CommonUtil;
import com.traffic.web.service.ReportService;
@Controller("reportAction")  
public class ReportAction implements ServletRequestAware, ServletResponseAware, ServletContextAware,ApplicationAware{ 
	@Autowired
	private ReportService reportService;
	
	
	private HttpServletRequest request; 
    private ServletContext servletContext; 
    private HttpServletResponse response;  
    private Map <String, Object> application;
    private String accidentId;
    private String arrPicIndex;
    
    private String pdfPath;
    private String exportType;
    
    
    
	 
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public String getArrPicIndex() {
		return arrPicIndex;
	}
	public void setArrPicIndex(String arrPicIndex) {
		this.arrPicIndex = arrPicIndex;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
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
	
	
	public String getAccidentId() {
		return accidentId;
	}
	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}
	 
	public String previewReport(){
		
		return "success";
	}
	public String formalAccidentReport(){
		
		String arrayPicIndex[]=CommonUtil.trim(arrPicIndex).split(",");  
	 
		File reportFile=new File(servletContext.getRealPath("rpt/FormalAccidentReport.jasper")); 
		Map parameters=new HashMap(); 
		parameters.put("accidentId", accidentId); 
		
		InputStream inputStream=null;
		try {
			inputStream = new FileInputStream(reportFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		JRDataSource dataSource = new JRBeanCollectionDataSource(reportService.generateFormalAccidentCollection(accidentId, arrayPicIndex));   
        if(exportType.equalsIgnoreCase("pdf")){
        	try {  
	           	JasperRunManager.runReportToPdfFile(reportFile.getPath(),parameters,dataSource);
	           	pdfPath="/rpt/FormalAccidentReport.pdf";
	   		} catch (Exception e) { 
	   			e.printStackTrace();
	   		}
        }
        if(exportType.equalsIgnoreCase("xls")){
        	try {  
        		
        		   JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),parameters,dataSource); 
        		   // excel输出
        		   ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        		  
        		    JRXlsExporter exporter = new JRXlsExporter();  
        		   
        		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
        		    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE); // 删除记录最下面的空行
        		    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);// 删除多余的ColumnHeader
        		    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);// 显示边框
        		    exporter.exportReport();
        		   
        		    byte[] bytes = oStream.toByteArray();
        		   
        		    if(bytes != null && bytes.length > 0) {
		        		    response.reset();
		        		    response.setContentType("application/vnd.ms-excel");
		        		    response.setContentLength(bytes.length);
		        		    response.setHeader("Content-Disposition", "attachment;filename=" + new String("FormalAccidentReport.xls".getBytes("gb2312"), "ISO8859-1"));
		        		    ServletOutputStream ouputStream = response.getOutputStream();
		        		    ouputStream.write(bytes,0,bytes.length);
		        		    ouputStream.flush();
		        		    ouputStream.close();
        		    }
        		    return null;
        		 
	   		} catch (Exception e) { 
	   			e.printStackTrace();
	   		}
        }
        if(exportType.equalsIgnoreCase("html")){
        	try { 
           	 //填充数据   
           	 JasperRunManager.runReportToHtmlFile(reportFile.getPath(), parameters,dataSource);
            
   		   } catch (Exception e) { 
   			e.printStackTrace();
   		    }
        } 
		//JasperExportUtil.export(reportService.generateFormalAccidentCollection(accidentId, arrayPicIndex), exportType,inputStream , request, response);
		
		
        //<result name="success">/rpt/FormalAccidentReport.html</result>
		 return "success";
		 
	}
	 
}
