package com.traffic.wei.service.impl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  
 
import cn.rising.model.SessionModel;
import cn.rising.redis.dao.SessionRedisDao;

import com.traffic.dao.AccidentDao;
import com.traffic.dao.CarMoveDao;
import com.traffic.dao.CustResDao;
import com.traffic.dao.ProcedureDao;
import com.traffic.dao.UserDao; 
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbCarMove;
import com.traffic.pojo.TbCustRes;
import com.traffic.util.CommonUtil;
import com.traffic.util.HttpWeiUtil;
import com.traffic.util.WeiUtil;
import org.rising.wei.bean.ErrCodeBean;
import com.traffic.wei.service.CustResService;
  
  
@Service("custResService")
public class CustResServiceImpl   implements CustResService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private AccidentDao accidentDao; 
	@Autowired
	private CarMoveDao carMoveDao; 
	
	@Autowired
	private CustResDao custResDao;
	
	@Autowired
	private ProcedureDao  procedureDao;
	
	@Autowired
	private SessionRedisDao sessionRedisDao;
	
	@Value("${server.ip}") 
	protected String  SERVER_IP; 
	
	@Value("${wei.appid}") 
	private String  APPID; 
	
	@Value("${wei.appsecret}") 
	private String  APPSECRET; 
	
	@Value("${traffic.name}") 
	protected String  TRAFFIC_NAME; 
	
	@Override
	public ErrCodeBean responseTextMessage(String openId, String context){
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("回复成功");
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,context);
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
	    tbCustRes.setCustResType("FEEDBACK");
	    custResDao.saveOrUpdate(tbCustRes) ;*/
	    return errCodeBean;
	}
	
	
	public ErrCodeBean throughAccidentAudit(Map <String, Object>application,String formalAccidentId)   {
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("成功！！");
		TbAccident tbAccident=accidentDao.findById(formalAccidentId); 
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("您好，事故编号：");
		textBuffer.append(formalAccidentId);
		textBuffer.append("\n已由"+ TRAFFIC_NAME+"受理，\n"); 
		textBuffer.append("请立即撤离事故现场,并于24小时内到理赔中心处理。");
		//textBuffer.append("<a href='"+String.format(detachementClaims_Url,this.SERVER_IP)+"'>车损理赔服务点</a>处理。\n");
	   //http://mp.weixin.qq.com/s?__biz=MjM5Mzc0MzIxNA==&mid=201261623&idx=2&sn=ec3197e3e7128da3e845ed54a7ac72de&scene=1&from=singlemessage&isappinstalled=0#rd
		//textBuffer.append("<a href='"+String.format(detachementResponsibility_Url,this.SERVER_IP)+"'>点击查看定责图示</a>\n");
		/*textBuffer.append(tbDePartment.getDepartmentName()+"地址：\n");
		textBuffer.append(tbDePartment.getDepartmentAddress());
		textBuffer.append("\n电话:"+tbDePartment.getDepartmentPhone()+"\n");*/
		
		 
        String openId=tbAccident.getReportOpenId();
		
		
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
		}  
		session.setLastAccessedTime(System.currentTimeMillis());
		sessionRedisDao.update(session);
	    return errCodeBean; 
		
	}
	public ErrCodeBean failAccidentAudit(Map <String, Object>application,String formalAccidentId,String custResText,ArrayList<String> procedureList) {
		ErrCodeBean errCodeBean=new ErrCodeBean();
		errCodeBean.setErrcode("0");
		errCodeBean.setErrmsg("成功！！");
		if (procedureList==null||procedureList.size()<=0){
			errCodeBean.setErrcode("1");
			errCodeBean.setErrmsg("传递的参数有误");
			return errCodeBean;
		}
		TbAccident tbAccident=accidentDao.findById(formalAccidentId);
		String openId=tbAccident.getReportOpenId();
		try {
			HttpWeiUtil.getInstance(APPID, APPSECRET).sendCustMsg(openId,custResText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		
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
		}
		 
		session.setLastAccessedTime(System.currentTimeMillis());
		sessionRedisDao.update(session);
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
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.update(session);
			return errCodeBean;
		}
	
	 
	
}


