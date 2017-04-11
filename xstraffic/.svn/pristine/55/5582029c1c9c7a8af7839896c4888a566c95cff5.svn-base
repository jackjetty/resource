package com.traffic.web.service.impl;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.traffic.dao.CustResDao;
import com.traffic.dao.DoAnswerDao;
import com.traffic.dao.FeedBackDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbCustRes;
import com.traffic.pojo.TbDoAnswer;
import com.traffic.pojo.TbFeedBack;
import com.traffic.pojo.TbUser;
import com.traffic.pojovo.TbDoAnswerVo;
import com.traffic.pojovo.TbFeedBackVo;
import com.traffic.util.CommonUtil;
import com.traffic.web.service.DoAnswerService;
import org.rising.wei.bean.ErrCodeBean;
import com.traffic.wei.service.CustResService;

@Service("doAnswerService")
public class DoAnswerServiceImpl implements DoAnswerService {
	Log log = LogFactory.getLog(DoAnswerService.class);
	@Autowired
	private DoAnswerDao doAnswerDao;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustResDao custResDao;
	
	@Autowired
	private CustResService custResService;
	
	@Override
	public HashMap<String, Object> getDoAnswer(int page, int rows,
			String startTime, String endTime, String doAnswerState) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String StrCdt=""; 
        
        startTime=CommonUtil.trim(startTime);
        endTime=CommonUtil.trim(endTime);
        doAnswerState=CommonUtil.trim(doAnswerState);
        int sumSize=0;
		int unReplySize=0; 
		int listSize=0; 
		String hql;
		//answerTime,answerOpenId,answerText,doAnswerState
		hql="select count(DISTINCT answerOpenId) from TbDoAnswer where CONVERT(varchar(100), answerTime, 23) >=? and CONVERT(varchar(100), answerTime, 23) <=?   ";
		sumSize=doAnswerDao.findCount(hql, startTime,endTime  );
		hql="select count(DISTINCT answerOpenId) from TbDoAnswer where CONVERT(varchar(100), answerTime, 23) >=? and CONVERT(varchar(100), answerTime, 23) <=?   and doAnswerState=? ";
		unReplySize=doAnswerDao.findCount(hql, startTime,endTime ,"未回复");
		listSize=sumSize;
		if(doAnswerState.equalsIgnoreCase("未回复")){
			listSize=unReplySize;
		}
		if(doAnswerState.equalsIgnoreCase("已回复")){
			listSize=sumSize-unReplySize;
		} 
		ArrayList<Object> arraylist=new ArrayList<Object> ();
		hql="select max(doAnswerId),answerOpenId,max(answerTime),min(doAnswerState) from TbDoAnswer where CONVERT(varchar(100), answerTime, 23) >=? ";
		arraylist.add(startTime);
		hql+=" and CONVERT(varchar(100), answerTime, 23) <=?   group by answerOpenId   ";
		arraylist.add(endTime);
		
		if(doAnswerState.equalsIgnoreCase("未回复")){
			 hql+="  having  min(doAnswerState)=? ";
			 arraylist.add(doAnswerState);
		}
		if(doAnswerState.equalsIgnoreCase("已回复")){
			 hql+="  having  min(doAnswerState)=? ";
			 arraylist.add(doAnswerState);
		} 
		hql+=" order by max(answerTime) desc "; 
	    List<TbDoAnswer> list=doAnswerDao.findPage(hql, (page - 1) * rows, rows, arraylist);
	    ArrayList<TbDoAnswerVo> tav = new ArrayList<TbDoAnswerVo>();
		 
		HashMap<String, Object> phoneNumber = userDao.getPhoneNumber();
		int doAnswerId;
		Timestamp   answerTime;
		String openId;
		
		for (Iterator iterator = list.listIterator(); iterator.hasNext(); ) {
	         Object[] arrObject = (Object[]) iterator.next();
	         doAnswerId = (Integer) arrObject[0];
	         openId=(String)arrObject[1];
	         answerTime=(Timestamp)arrObject[2]; 
	         doAnswerState=(String)arrObject[3]; 
	         TbDoAnswerVo order = new TbDoAnswerVo( doAnswerDao.findById(doAnswerId), phoneNumber);
	         if(doAnswerState.equalsIgnoreCase("未回复")){
	        	 order.setOp1("回复");
	         }
	         else{
	        	 order.setOp1("详情");
	         }
	         
			 tav.add(order); 
	    }  
		resultMap.put("total", listSize);
		resultMap.put("rows",tav); 
		return resultMap; 
	}
	public HashMap<String, Object>  getUserDaInfo(int doAnswerId){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbDoAnswer tbDoAnswer=doAnswerDao.findById(doAnswerId);
		String openId=tbDoAnswer.getAnswerOpenId(); 
		//answerTime,answerOpenId,answerText,doAnswerState
		String hql=" from TbDoAnswer where answerOpenId=? order by answerTime ";
		
		List<TbDoAnswer> doAnswerList = doAnswerDao.findByHQL(hql, openId); 
		//custResTime,custResContent,openId,custResponser,custResType
		hql="from TbCustRes where openId = ? and custResType=? order by custResTime ";
		List<TbCustRes> custResList=  custResDao.findByHQL(hql,openId,"DOANSWER" ); 
		ArrayList<TbDoAnswerVo> doAnswerVoList = new ArrayList<TbDoAnswerVo>();
		
		TbDoAnswerVo tbDoAnswerVo;
		String answerTime;
		String answerText;
		for(TbDoAnswer doAnswer:doAnswerList){
			answerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(doAnswer.getAnswerTime());
			answerText =doAnswer.getAnswerText();  
			tbDoAnswerVo = new TbDoAnswerVo(answerTime,answerText,"1");
			doAnswerVoList.add(tbDoAnswerVo);
		}
		for(TbCustRes custRes:custResList){
			answerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(custRes.getCustResTime());
			answerText =custRes.getCustResContent(); 
			tbDoAnswerVo = new TbDoAnswerVo(answerTime,answerText,"2");
			//nickName
			
			tbDoAnswerVo.setNickName(custRes.getTbUser()==null?"":CommonUtil.trim(custRes.getTbUser().getUserName()));
			doAnswerVoList.add(tbDoAnswerVo);
		}
		Collections.sort(doAnswerVoList, new Comparator<TbDoAnswerVo>() {
	        @Override
	        public int compare(TbDoAnswerVo o1, TbDoAnswerVo o2) {
	        		 
	                return  o1.getAnswerTime().compareTo(o2.getAnswerTime());
	        	}
			}); 
		resultMap.put("rows", doAnswerVoList);
		return resultMap;
		
	}
	
	public List<TbDoAnswerVo> getFeedBackVoList(String startTime,String endTime,String doAnswerState){
		 List<TbDoAnswerVo> doAnswerVoList=new ArrayList<TbDoAnswerVo>();
		 String hql="from TbDoAnswer where CONVERT(varchar(100), answerTime, 23) >=?  and CONVERT(varchar(100), answerTime, 23) <=?  ";
		 ArrayList<Object> arrayList=new ArrayList<Object>();
		 arrayList.add(startTime);
		 arrayList.add(endTime);
		 
		 if(doAnswerState.equalsIgnoreCase("未回复")){
        	 hql+=" and doAnswerState =? ";
        	 arrayList.add(doAnswerState);
         }
		 if(doAnswerState.equalsIgnoreCase("已回复")){
        	 hql+=" and doAnswerState =? ";
        	 arrayList.add(doAnswerState);
         }
		 hql+=" order by answerTime";
		 List<TbDoAnswer> doAnswerList=doAnswerDao.findByHQL(hql, arrayList);
		 TbDoAnswerVo tbDoAnswerVo;
		 
		 HashMap<String, Object> phoneNumber = userDao.getPhoneNumber();
		 for(TbDoAnswer tbDoAnswer:doAnswerList){
			 tbDoAnswerVo = new TbDoAnswerVo( tbDoAnswer ,phoneNumber);
			 doAnswerVoList.add(tbDoAnswerVo);
		 }
		 return doAnswerVoList;
		 
	}
	public HashMap<String, Object> addAnswerCustRes(int doAnswerId,String content){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbDoAnswer tbDoAnswer=doAnswerDao.findById(doAnswerId);
		String openId=tbDoAnswer.getAnswerOpenId();
		ErrCodeBean errCodeBen=custResService.responseTextMessage(openId, content); 
		if(!errCodeBen.getErrcode().equalsIgnoreCase("0")){
			resultMap.put("respCode", -1);
			resultMap.put("respInfo",  errCodeBen.getErrmsg());
			return resultMap; 
		}
		TbUser tbUser=(TbUser) ActionContext.getContext().getSession().get("Manager");  
		Date date=new Date();
		
		TbCustRes tbCustRes=new TbCustRes();
		tbCustRes.setCustResContent(content);
		tbCustRes.setCustResType("DOANSWER");
		tbCustRes.setOpenId(openId);
		tbCustRes.setCustResTime(new Timestamp(date.getTime())); 
		tbCustRes.setTbUser(tbUser);
		custResDao.save(tbCustRes); 
		String hql=" from  TbDoAnswer where (answerOpenId=?) and  (doAnswerState=?)  ";
		List<TbDoAnswer> doAnswerList=doAnswerDao.findByHQL(hql,openId,"未回复"  );
		for(TbDoAnswer doAnswer:doAnswerList){
			doAnswer.setDoAnswerState ("已回复"); 
			doAnswerDao.saveOrUpdate(doAnswer);
		}  
		resultMap.put("respCode", 0);
		resultMap.put("respInfo",  "");
		resultMap.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		return resultMap;
	}
}