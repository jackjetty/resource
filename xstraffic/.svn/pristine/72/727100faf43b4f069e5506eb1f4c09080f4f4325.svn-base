package com.traffic.wap.action; 
import java.util.ArrayList;
import java.util.HashMap; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 

import com.traffic.wap.service.QRWapService;
@Controller("qRWapAction")
public class QRWapAction { 
	private HashMap<String,Object> result; 
	@Autowired
	private QRWapService qrWapService;
	
	
	private String eleId;
	
	
	 
	public String getEleId() {
		return eleId;
	}
	public void setEleId(String eleId) {
		this.eleId = eleId;
	}
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	public String qrCode(){ 
		return "success";
	}
	public String test(){
		
		for(int i=1;i<=45;i++){
			result=qrWapService.test(new Integer (i).toString());
		}
		
		
		return "success";
	}
	
	
   public String win(){
		
		 
	    result=qrWapService.win();
	 
		
		
		return "success";
	}
	
	
	public String refresh(){
		 
		result=qrWapService.refresh();
		 
		
		
		return "success";
	}
	public String account(){
		for(int i=1;i<=45;i++){
			result=qrWapService.account(new Integer (i).toString());
		}  
		return "success";
	}
	
}