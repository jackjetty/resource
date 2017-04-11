package com.detachment.wap.action;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap; 
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired; 

import com.detachment.util.CommonUtil;
import com.detachment.wap.service.LegalProcessWapService;  
import com.detachment.wap.service.LegalQueryWapService;

import org.springframework.stereotype.Controller;
 
@Controller("legalQueryWapAction")
public class LegalQueryWapAction {
	private HashMap<String,Object> result;
	private String cartype;
	private String carTypeValue;
	private String carid;
	private String carno;
	private String randValue; 
	private String index;
	private String vsfzmhm;
	private String vdabh; 
	
	
	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public HashMap<String, Object> getResult() {
		
		return result;
	} 

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	} 



	public String getCartype() {
		return cartype;
	}






	public void setCartype(String cartype) {
		this.cartype = cartype;
	}






	public String getCarTypeValue() {
		return carTypeValue;
	}






	public void setCarTypeValue(String carTypeValue) {
		this.carTypeValue = carTypeValue;
	}






	public String getCarid() {
		return carid;
	}






	public void setCarid(String carid) {
		this.carid = carid;
	}






	public String getCarno() {
		return carno;
	}






	public void setCarno(String carno) {
		this.carno = carno;
	}






	public String getRandValue() {
		return randValue;
	}






	public void setRandValue(String randValue) {
		this.randValue = randValue;
	}






	 






	public String getVsfzmhm() {
		return vsfzmhm;
	}

	public void setVsfzmhm(String vsfzmhm) {
		this.vsfzmhm = vsfzmhm;
	}

	public String getVdabh() {
		return vdabh;
	}

	public void setVdabh(String vdabh) {
		this.vdabh = vdabh;
	}













	@Autowired
	private LegalQueryWapService legalQueryWapService; 
	
	public String legalQueryPage(){ 
		Calendar cal = Calendar.getInstance(); 
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		index=CommonUtil.trim(index); 
		if(hour<12){
			 return index.equalsIgnoreCase("1")?"zjtraffic1":"zjtraffic2";
		} 
		if(hour<17){
			 return index.equalsIgnoreCase("1")?"qztraffic1":"qztraffic2";
		}  
		return "success";
	}
	public String driverIllegality(){
		result=legalQueryWapService.driverIllegality(carid,carno,cartype,carTypeValue);
		return "success";
	} 
	public String driverScore(){
		result=legalQueryWapService.driverScore(vsfzmhm,vdabh);
		return "success"; 
	}
	
	//"http://www.zjsgat.gov.cn:8080/was/Kaptcha.jpg?"+Math.floor(Math.random()*100);
	public void verifyCode() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			    byte[] by = legalQueryWapService.verifyCode(); 
		        ServletOutputStream out = response.getOutputStream(); 
				ByteArrayInputStream in = new ByteArrayInputStream(by);   
				BufferedImage image = ImageIO.read(in);  
				response.setContentType("image/jpeg"); 
				response.setContentType("jpg");  
				response.setContentType("application/octet-stream; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment;filename=Kaptcha.jpg"  );
				response.setHeader("Content_Length",new Integer(by.length).toString()); 
				OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
				toClient.write(by); // 输出数据
				toClient.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	public String checkVerifyCode(){
	   result=legalQueryWapService.checkVerifyCode(randValue) ;
	   return "success";	
	}
	
}