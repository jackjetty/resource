package com.detachment.wei.service;
import java.util.ArrayList;
import java.util.Map;

import org.rising.wei.bean.BaseBean;
import org.rising.wei.bean.ErrCodeBean;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LinkReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.ScanEventReqBean;
import org.rising.wei.bean.req.ScribeEventReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.rising.wei.bean.req.VideoReqBean;
import org.rising.wei.bean.req.VoiceReqBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; 

import cn.rising.model.SessionModel;

public interface CustResService {
	public ErrCodeBean throughAccidentAudit(Map <String, Object>application,String formalAccidentId)  ;
	public ErrCodeBean failAccidentAudit(Map <String, Object>application,String formalAccidentId,String custResText,ArrayList<String> procedureList)  ;
    public ErrCodeBean maliciousAccidentAudit(Map <String, Object>application,String formalAccidentId ) ;
    public ErrCodeBean responseTextMessage(String openId, String context);
    
    public ErrCodeBean throughCarMoveAudit(Map <String, Object>application,String formalCarMoveId)  ;
	public ErrCodeBean failCarMoveAudit(Map <String, Object>application,String formalCarMoveId,String custResText,ArrayList<String> procedureList)  ; 
	
	public ErrCodeBean throughHandyPhotoAudit(Map <String, Object>application,String formalHandyPhotoId)  ;
	public ErrCodeBean failHandyPhotoAudit(Map <String, Object>application,String formalHandyPhotoId,String custResText,ArrayList<String> procedureList)  ; 
    
}