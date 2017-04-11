package com.detachment.wap.action;
import java.util.ArrayList;
import java.util.HashMap; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 

import com.detachment.wap.service.QRWapService;

 
@Controller("qRWapAction")
public class QRWapAction { 
	private HashMap<String,Object> result; 
	@Autowired
	private QRWapService qrWapService;
	public String refresh(){
		 
		result=qrWapService.refresh();
		 
		
		
		return "success";
	}
}