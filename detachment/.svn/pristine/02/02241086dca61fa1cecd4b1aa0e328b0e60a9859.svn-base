
package com.detachment.wei.service.impl;
import java.io.File; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rising.wei.bean.ErrCodeBean;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.rising.wei.bean.res.NewsResBean;
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;  
  
 


import cn.rising.model.SessionModel;
import cn.rising.redis.dao.SessionRedisDao;

import com.detachment.dao.CarMoveDao;
import com.detachment.dao.CustResDao;
import com.detachment.dao.DepartmentDao;
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.HandyPhotoDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbCustRes;
import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.TbFormalAccident;
import com.detachment.pojo.TbUser;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.WeiUtil;
import com.detachment.wei.process.AccidentProcess;
import com.detachment.wei.process.FormalAccidentProcess;
import com.detachment.wei.process.TestAccidentProcess;
import com.detachment.wei.service.CustResService; 
import com.detachment.dao.ProcedureDao;
import com.detachment.pojo.TbCarMove;
import com.detachment.pojo.TbHandyPhoto;
import com.opensymphony.xwork2.ActionContext;
 
 
@Scope("prototype") 
@Service("custResService")
public class CustResServiceImpl   implements CustResService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private FormalAccidentDao formalAccidentDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private CustResDao custResDao;
	
	@Autowired
	private CarMoveDao carMoveDao;
	
	
	@Autowired
	private HandyPhotoDao handyPhotoDao;
	
	@Autowired
	private ProcedureDao procedureDao; 
	
	@Value("${server.ip}") 
	protected String  SERVER_IP; 
	
	@Value("${wei.appid}") 
	private String  APPID; 
	
	@Value("${wei.appsecret}") 
	private String  APPSECRET; 
	
	@Value("${wei.detachment.claims.url}") 
	private String  detachementClaims_Url; 
	
	
	@Value("${wei.detachment.responsibility.url}") 
	private String  detachementResponsibility_Url; 
	
	@Autowired
	private SessionRedisDao sessionRedisDao;
	
	@Override
	public ErrCodeBean throughAccidentAudit(Map <String, Object>application,String formalAccidentId)   {
		// TODO Auto-generated method stub
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("回复成功");
		
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(formalAccidentId);
		String departmentId=tbFormalAccident.getDepartmentId();
		TbDepartment tbDePartment=departmentDao.findById(departmentId);
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("您好，事故编号：\n");
		textBuffer.append(formalAccidentId);
		textBuffer.append("\n已由"+tbDePartment.getDepartmentName()+"受理，\n"); 
		textBuffer.append("请双方立即撤离现场,并于24小时内到就近");
		textBuffer.append("<a href='"+String.format(detachementClaims_Url,this.SERVER_IP)+"'>车损理赔服务点</a>处理。\n");
	   //http://mp.weixin.qq.com/s?__biz=MjM5Mzc0MzIxNA==&mid=201261623&idx=2&sn=ec3197e3e7128da3e845ed54a7ac72de&scene=1&from=singlemessage&isappinstalled=0#rd
		textBuffer.append("<a href='"+String.format(detachementResponsibility_Url,this.SERVER_IP)+"'>点击查看定责图示</a>\n");
		/*textBuffer.append(tbDePartment.getDepartmentName()+"地址：\n");
		textBuffer.append(tbDePartment.getDepartmentAddress());
		textBuffer.append("\n电话:"+tbDePartment.getDepartmentPhone()+"\n");*/
		
		textBuffer.append("如需出警请回复D");
		String openId=tbFormalAccident.getReportOpenId();
		
		SessionModel session=sessionRedisDao.get(openId);
		if( session ==null){
			session=new SessionModel();
			session.setOpenId(openId);
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.add(session);
		} 
		 
		  
		session.setAttribute("AUDIT", "true");  
		session.setAttribute("AUDITPROCESSID", "ACCIDENT");
		session.setAttribute("auditId", formalAccidentId);  
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId, textBuffer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("回复失败");
			return errCodeBean;
		}
		 
	    
	    
	    
	    this.addCustResLog(  openId, textBuffer.toString(), formalAccidentId,  Constant.ACCIDENT,  "ACCIDENTAUDITTHROUGH");
	   
	    session.setLastAccessedTime(System.currentTimeMillis());
	    sessionRedisDao.update(session);
	    return  errCodeBean;
		
	}
	public ErrCodeBean failAccidentAudit(Map <String, Object>application,String formalAccidentId,String custResText,ArrayList<String> procedureList)  {
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("回复成功");
		if (procedureList==null||procedureList.size()<=0){
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("传递的参数有误");
			return errCodeBean; 
		}
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(formalAccidentId);
		String openId=tbFormalAccident.getReportOpenId();
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("回复失败");
			return errCodeBean;
		}
		
		TbCustRes tbCustRes=new  TbCustRes();
	    tbCustRes.setCustResContent(custResText);
	    Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();    
	    tbCustRes.setCustResTime(new Timestamp(date.getTime()));
	    tbCustRes.setOpenId(openId);
	    custResDao.saveOrUpdate(tbCustRes) ;
		
	    SessionModel session=sessionRedisDao.get(openId);
		if( session ==null){
			session=new SessionModel();
			session.setOpenId(openId);
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.add(session);
		} 
		
		
		session.setAttribute("AUDIT", "true");
		session.setAttribute("AUDITPROCESSID", "ACCIDENT");
		session.setAttribute("auditIndex", 0);  
		session.setAttribute("auditId", formalAccidentId);  
		session.setAttribute("procedureList", procedureList); 
		custResText=CommonUtil.trim(procedureDao.getProcedureTip("ACCIDENT", procedureList.get(0)))   ;
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("回复失败");
			return errCodeBean;
		}
		this.addCustResLog(  openId, custResText, formalAccidentId,  Constant.ACCIDENT,  "ACCIDENTAUDITFAIL");
	    session.setLastAccessedTime(System.currentTimeMillis());
	    sessionRedisDao.update(session);
	    return errCodeBean; 
	}
	
	
	
	public ErrCodeBean maliciousAccidentAudit(Map <String, Object>application,String formalAccidentId  )  {
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("回复成功");
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(formalAccidentId);
		String departmentId=tbFormalAccident.getDepartmentId();
		//TbDepartment tbDePartment=departmentDao.findById(departmentId);
		StringBuffer textBuffer=new StringBuffer("");
		/*textBuffer.append("您好，您上报的信息已由"+tbDePartment.getDepartmentName());
		textBuffer.append("定义为恶意上报\n");
		 
		textBuffer.append("对通过微信报假事故或恶意使用微信，滋扰公安机关工作秩序等行为，公安机关将按照《中华人民共和国治安管理处罚法》");
		textBuffer.append("有关规定，依法严肃处罚，对构成犯罪的还将依法追究刑事责任。\n");
		textBuffer.append("如有异议请致电:"+tbDePartment.getDepartmentPhone());*/
		textBuffer.append("您好，请您立刻停止恶意滋扰行为，否则将被依法追究法律责任。");
		  
		String openId=tbFormalAccident.getReportOpenId();
		
		SessionModel session=sessionRedisDao.get(openId);
		if( session ==null){
			session=new SessionModel();
			session.setOpenId(openId);
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.add(session);
		} 
		 
		session.setAttribute("AUDIT", null);
		session.setAttribute("AUDITPROCESSID", null);
		session.setAttribute("auditIndex", null); 
		session.setAttribute("auditId", null); 
		session.setAttribute("procedureList", null); 
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId, textBuffer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("回复失败");
			return errCodeBean;
		}
		this.addCustResLog(  openId, textBuffer.toString(), formalAccidentId,  Constant.ACCIDENT,  "ACCIDENTMALICIOUS");
	    session.setLastAccessedTime(System.currentTimeMillis());
	    sessionRedisDao.update(session);
	    return errCodeBean;
	}
	public ErrCodeBean responseTextMessage(String openId, String context){
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("回复成功");
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("回复失败");
			return errCodeBean;
		}
		/*
		TbCustRes tbCustRes=new  TbCustRes();
	    tbCustRes.setCustResContent(context);
	    Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();    
	    tbCustRes.setCustResTime(new Timestamp(date.getTime()));
	    tbCustRes.setOpenId(openId);
	    custResDao.saveOrUpdate(tbCustRes) ;*/
	    return errCodeBean;
	} 
	
	   //自助移车
		public ErrCodeBean throughCarMoveAudit(Map <String, Object>application,String formalCarMoveId) {
			ErrCodeBean errCodeBean=new ErrCodeBean();
			errCodeBean.setErrcode("0");
			errCodeBean.setErrmsg("成功！！");
			TbCarMove tbCarMove=carMoveDao.findById(formalCarMoveId); 
			StringBuffer textBuffer=new StringBuffer("");
			textBuffer.append("您好，受理编号：");
			textBuffer.append(formalCarMoveId);
			textBuffer.append("\n我们已通知车主，请耐心等候。");   
			String openId=tbCarMove.getReportOpenId();
			
			SessionModel session=sessionRedisDao.get(openId);
			if( session ==null){
				session=new SessionModel();
				session.setOpenId(openId);
				session.setLastAccessedTime(System.currentTimeMillis());
				sessionRedisDao.add(session);
			} 
			 
			session.setAttribute("AUDIT", null);
			session.setAttribute("AUDITPROCESSID", null);
			session.setAttribute("auditIndex", null); 
			session.setAttribute("auditId", null); 
			session.setAttribute("procedureList", null);
			try {
				HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId, textBuffer.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("回复失败");
				return errCodeBean;
			} 
			this.addCustResLog(  openId, textBuffer.toString(), formalCarMoveId,  Constant.CARMOVE,  "CARMOVEAUDITTHROUGH");
			 
		    session.setLastAccessedTime(System.currentTimeMillis());
		    sessionRedisDao.update(session);
		    return errCodeBean;
		}
		public ErrCodeBean failCarMoveAudit(Map <String, Object>application,String formalCarMoveId,String custResText,ArrayList<String> procedureList)  {
			ErrCodeBean errCodeBean=new ErrCodeBean();
			errCodeBean.setErrcode("0");
			errCodeBean.setErrmsg("成功！！");
			if (procedureList==null||procedureList.size()<=0){
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("传递的参数有误");
				return errCodeBean;
			}
			TbCarMove tbCarMove=carMoveDao.findById(formalCarMoveId); 
			String openId=tbCarMove.getReportOpenId();
			try {
				HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("回复失败");
				return errCodeBean;
			} 
			SessionModel session=sessionRedisDao.get(openId);
			if( session ==null){
				session=new SessionModel();
				session.setOpenId(openId);
				session.setLastAccessedTime(System.currentTimeMillis());
				sessionRedisDao.add(session);
			} 
			
			session.setAttribute("AUDIT", "true");
			session.setAttribute("AUDITPROCESSID", "CARMOVE"); 
			session.setAttribute("auditIndex", 0); 
			session.setAttribute("auditId", formalCarMoveId); 
			session.setAttribute("procedureList", procedureList);  
			custResText=CommonUtil.trim(procedureDao.getProcedureTip("CARMOVE", procedureList.get(0)))   ;
			try {
				HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("回复失败");
				return errCodeBean;
			} 
			this.addCustResLog(  openId, custResText, formalCarMoveId,  Constant.CARMOVE,  "CARMOVEAUDITFAIL"); 
		    session.setLastAccessedTime(System.currentTimeMillis());
		    sessionRedisDao.update(session);
			return errCodeBean;
		}
		//违法随手拍
		public ErrCodeBean throughHandyPhotoAudit(Map <String, Object>application,String formalHandyPhotoId)  {
			ErrCodeBean errCodeBean=new ErrCodeBean();
			errCodeBean.setErrcode("0");
			errCodeBean.setErrmsg("成功！！");
			TbHandyPhoto tbHandyPhoto=handyPhotoDao.findById(formalHandyPhotoId); 
			StringBuffer textBuffer=new StringBuffer("");
			textBuffer.append("您好，您提交的信息我们已经受理，受理编号：");
			textBuffer.append(formalHandyPhotoId);
			textBuffer.append("\n感谢您的支持！！");   
			String openId=tbHandyPhoto.getReportOpenId(); 
			SessionModel session=sessionRedisDao.get(openId);
			if( session ==null){
				session=new SessionModel();
				session.setOpenId(openId);
				session.setLastAccessedTime(System.currentTimeMillis());
				sessionRedisDao.add(session);
			} 
			
			session.setAttribute("AUDIT", null);
			session.setAttribute("AUDITPROCESSID", null);
			session.setAttribute("auditIndex", null); 
			session.setAttribute("auditId", null); 
			session.setAttribute("procedureList", null); 
			try {
				HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId, textBuffer.toString());
			} catch (Exception e) { 
				e.printStackTrace();
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("回复失败");
				return errCodeBean;
			} 
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.update(session);
		    return errCodeBean; 
		}
		public ErrCodeBean failHandyPhotoAudit(Map <String, Object>application,String formalHandyPhotoId,String custResText,ArrayList<String> procedureList) {
			ErrCodeBean errCodeBean=new ErrCodeBean();
			errCodeBean.setErrcode("0");
			errCodeBean.setErrmsg("成功！！");
			if (procedureList==null||procedureList.size()<=0){
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("传递的参数有误");
				return errCodeBean;
			}
			TbHandyPhoto tbHandyPhoto=handyPhotoDao.findById(formalHandyPhotoId); 
			String openId=tbHandyPhoto.getReportOpenId();
			try {
				HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
			} catch (Exception e) { 
				e.printStackTrace();
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("回复失败");
				return errCodeBean;
			} 
			SessionModel session=sessionRedisDao.get(openId);
			if( session ==null){
				session=new SessionModel();
				session.setOpenId(openId);
				session.setLastAccessedTime(System.currentTimeMillis());
				sessionRedisDao.add(session);
			} 
			 
			session.setAttribute("AUDIT", "true");
			session.setAttribute("AUDITPROCESSID", "HANDYPHOTO"); 
			session.setAttribute("auditIndex", 0); 
			session.setAttribute("auditId", formalHandyPhotoId); 
			session.setAttribute("procedureList", procedureList);  
			custResText=CommonUtil.trim(procedureDao.getProcedureTip("HANDYPHOTO", procedureList.get(0)))   ; 
			try {
				HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
			} catch (Exception e) { 
				e.printStackTrace();
				errCodeBean.setErrcode("1");
				errCodeBean.setErrmsg("回复失败");
				return errCodeBean;
			} 
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.update(session);
			return errCodeBean;
		}
		
		private void addCustResLog(String openId,String custResText,String recordNo,String processId,String custResType){
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TbUser tbUser =  (TbUser) session.get("Manager"); 
			TbCustRes tbCustRes=new  TbCustRes();
		    tbCustRes.setCustResContent(custResText);
		    Calendar cal = Calendar.getInstance(); 
	        Date date=cal.getTime();    
		    tbCustRes.setCustResTime(new Timestamp(date.getTime()));
		    tbCustRes.setOpenId(openId);
		    tbCustRes.setRecordNo(recordNo); 
		    tbCustRes.setProcessId(processId);
		    tbCustRes.setCustResType(custResType);
		    tbCustRes.setTbUser(tbUser);
		    custResDao.saveOrUpdate(tbCustRes) ; 
			
		}
	
}