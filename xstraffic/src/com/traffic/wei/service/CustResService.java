package com.traffic.wei.service;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;  

import org.rising.wei.bean.ErrCodeBean;
public interface CustResService {
	public ErrCodeBean responseTextMessage(String openId, String context);
	public ErrCodeBean throughAccidentAudit(Map <String, Object>application,String formalAccidentId)  ;
	public ErrCodeBean failAccidentAudit(Map <String, Object>application,String formalAccidentId,String custResText,ArrayList<String> procedureList)  ; 
	public ErrCodeBean throughCarMoveAudit(Map <String, Object>application,String formalCarMoveId)  ;
	public ErrCodeBean failCarMoveAudit(Map <String, Object>application,String formalCarMoveId,String custResText,ArrayList<String> procedureList)  ; 
}