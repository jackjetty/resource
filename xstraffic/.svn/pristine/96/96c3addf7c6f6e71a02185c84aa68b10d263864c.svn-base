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
import com.traffic.dao.FeedBackDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbCustRes;
import com.traffic.pojo.TbDoAnswer;
import com.traffic.pojo.TbFeedBack;
import com.traffic.pojo.TbUser;
import com.traffic.pojovo.TbFeedBackVo;
import com.traffic.util.CommonUtil;
import com.traffic.web.service.FeedBackService;

@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	Log log = LogFactory.getLog(FeedBackServiceImpl.class);
	@Autowired
	FeedBackDao feedBackDao;
	
	@Autowired
	UserDao userDao;
	@Autowired
	CustResDao custResDao;
	
	
	@Override
	public HashMap<String, Object> getFeedBack(String startTime,
			String endTime, String feedBackState, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		 
		feedBackState=CommonUtil.trim(feedBackState);
		startTime=CommonUtil.trim(startTime);
		endTime=CommonUtil.trim(endTime); 
		Integer sumSize=0;
		Integer unReplySize=0; 
		Integer listSize=0; 
		
		String hql;
		hql="select count(DISTINCT feedOpenId) from TbFeedBack where CONVERT(varchar(100), feedTime, 23) >=? and CONVERT(varchar(100), feedTime, 23) <=?   ";
		sumSize=feedBackDao.findCount(hql, startTime,endTime  );
		hql="select count(DISTINCT feedOpenId) from TbFeedBack where CONVERT(varchar(100), feedTime, 23) >=? and CONVERT(varchar(100), feedTime, 23) <=?   and feedBackState=? ";
		unReplySize=feedBackDao.findCount(hql, startTime,endTime ,"未回复");
		
		listSize=sumSize;
		if(feedBackState.equalsIgnoreCase("未回复")){
			listSize=unReplySize;
		}
		if(feedBackState.equalsIgnoreCase("已回复")){
			listSize=sumSize-unReplySize;
		} 
		
		ArrayList<Object> arraylist =new ArrayList<Object>();
		hql="select max(feedBackId),feedOpenId,max(feedTime),min(feedBackState) from TbFeedBack where CONVERT(varchar(100), feedTime, 23) >=? ";
		arraylist.add(startTime);
		hql+=" and CONVERT(varchar(100), feedTime, 23) <=?  group by feedOpenId  ";
		arraylist.add(endTime);
		 
		if(feedBackState.equalsIgnoreCase("未回复")){
			 hql+="  having  min(feedBackState)=? ";
			 arraylist.add(feedBackState);
		}
		if(feedBackState.equalsIgnoreCase("已回复")){
			 hql+="  having  min(feedBackState)=? ";
			 arraylist.add(feedBackState);
		} 
		hql+="  order by max(feedTime) desc "; 
	    List list=feedBackDao.findPage(hql, (pageIndex - 1) * pageSize, pageSize, arraylist);
	     
		ArrayList<TbFeedBackVo> tav = new ArrayList<TbFeedBackVo>();
	 
		HashMap<String, Object> phoneNumber = userDao.getPhoneNumber();
		int feedbackId;
		Timestamp   feedTime;
		String openId;
		String custResType="FEEDBACK";
		
		for (Iterator iterator = list.listIterator(); iterator.hasNext(); ) {
	         Object[] rows = (Object[]) iterator.next();
	         feedbackId = (Integer) rows[0];
	         openId=(String)rows[1];
	         feedTime=(Timestamp)rows[2]; 
	         feedBackState=(String)rows[3]; 
	         TbFeedBackVo order = new TbFeedBackVo( feedBackDao.findById(feedbackId), phoneNumber);
	         if(feedBackState.equalsIgnoreCase("未回复")){
	        	 order.setOp1("回复");
	         }
	         else{
	        	 order.setOp1("详情");
	         }
	         /* 
	         if(custResDao.getCount(openId, feedTime, custResType)>0){
	        	 order.setOp1("详情");
	         }
	         else{
	        	 order.setOp1("回复");
	         } */
			 tav.add(order); 
	    }  
		resultMap.put("listSize", listSize);
		resultMap.put("rows",tav);
		resultMap.put("respCode", 0);
		return resultMap;
	}
	@Override
	public Integer getFeedBackSize(String startTime, String endTime,String feedBackState ) {
		String hql=" from TbFeedBack where CONVERT(varchar(100), feedTime, 23) >=?  " ;
		ArrayList<Object> arraylist =new ArrayList<Object>();
		arraylist.add(startTime);
		hql+=" and CONVERT(varchar(100), feedTime, 23) <=?  ";
		arraylist.add(endTime);
		if(feedBackState.equalsIgnoreCase("未回复")){
			 hql+="  and   feedBackState=? ";
			 arraylist.add(feedBackState);
		}
		if(feedBackState.equalsIgnoreCase("回复")){
			 hql+="  and   feedBackState=? ";
			 arraylist.add(feedBackState);
		}
		hql+=" order by  feedTime "; 
		Integer listSize = feedBackDao.findCount(hql, arraylist);
		return listSize;
	}
	@Override
	public ArrayList<TbFeedBackVo> getFeedBackVo( String startTime, String endTime,String feedBackState) {
		String hql=" from TbFeedBack where CONVERT(varchar(100), feedTime, 23) >=?  " ;
		ArrayList<Object> arraylist =new ArrayList<Object>();
		arraylist.add(startTime);
		hql+=" and CONVERT(varchar(100), feedTime, 23) <=?  ";
		arraylist.add(endTime);
		if(feedBackState.equalsIgnoreCase("未回复")){
			 hql+="  and   feedBackState=? ";
			 arraylist.add(feedBackState);
		}
		if(feedBackState.equalsIgnoreCase("回复")){
			 hql+="  and   feedBackState=? ";
			 arraylist.add(feedBackState);
		} 
		hql+=" order by feedTime";
		List<TbFeedBack> ta = feedBackDao.findByHQL(hql, arraylist); 
		ArrayList<TbFeedBackVo> tav = new ArrayList<TbFeedBackVo>();
		 
		HashMap<String, Object> phoneNumber = userDao.getPhoneNumber();
		for (int i = 0; ta != null && i < ta.size(); i++) {
			TbFeedBackVo order = new TbFeedBackVo(ta.get(i), phoneNumber);
			tav.add(order);
		}
		return tav;
	}
	@Override
	public TbFeedBackVo getFeedBackById(Integer feedBackId) {
		TbFeedBack tfb = feedBackDao.getFeedBackById(feedBackId);
	 
		HashMap<String, Object> phoneNumber = userDao.getPhoneNumber();
		TbFeedBackVo tdv = new TbFeedBackVo(tfb, phoneNumber);
		return tdv;
	}
	@Override
	public HashMap<String, Object> getUserFbInfo(Integer feedBackId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbFeedBack tf=feedBackDao.findById(feedBackId);
		String openId=tf.getFeedOpenId(); 
		
        String hql=" from TbFeedBack where feedOpenId=? order by feedTime ";
		
		List<TbFeedBack> tfb = feedBackDao.findByHQL(hql, openId);  
		ArrayList<TbCustRes> tcr=(ArrayList<TbCustRes>) custResDao.findByHQL("from TbCustRes where openId = ? and custResType=? ",openId,"FEEDBACK" ); 
		ArrayList<TbFeedBackVo> tfbv = new ArrayList<TbFeedBackVo>();
		for(TbFeedBack t:tfb){
			String feedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getFeedTime());
			String feedInfo =t.getFeedText();
			String feedTyp= "1";
			TbFeedBackVo fbv = new TbFeedBackVo(feedTime,feedInfo,feedTyp);
			tfbv.add(fbv);
		}
		for(TbCustRes tc:tcr){
			String feedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tc.getCustResTime());
			String feedInfo =tc.getCustResContent();
			String feedTyp= "2";
			TbFeedBackVo fbv = new TbFeedBackVo(feedTime,feedInfo,feedTyp); 
			fbv.setNickName(tc.getTbUser()==null?"":CommonUtil.trim(tc.getTbUser().getUserName()));
			tfbv.add(fbv);
		}
		Collections.sort(tfbv, new Comparator<TbFeedBackVo>() {
	        @Override
	        public int compare(TbFeedBackVo o1, TbFeedBackVo o2) {
	        		Date a1=null;
					try {
						a1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o1.getFeedTime());
					} catch (ParseException e) {}
	        		Date a2=null;
					try {
						a2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o2.getFeedTime());
					} catch (ParseException e) {}
	                if (a1.getTime() > a2.getTime()) {
	                        return 1;
	                } else if (a1.getTime() < a2.getTime()) {
	                        return -1;

	                }
	                return 0;
	        	}
			});
		
		resultMap.put("rows", tfbv);
		return resultMap;
	}
	 
	@Override
	public HashMap<String, Object> addFeedCustRes(Integer feedBackId, String content ) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbFeedBack tf=feedBackDao.findById(feedBackId);
		Date date=new Date();
		TbUser tu=(TbUser) ActionContext.getContext().getSession().get("Manager");
		TbCustRes tcr=new TbCustRes();
		tcr.setCustResContent(content);
		tcr.setCustResType("FEEDBACK");
		tcr.setOpenId(tf.getFeedOpenId());
		tcr.setCustResTime(new Timestamp(date.getTime())); 
		tcr.setTbUser(tu);
		custResDao.save(tcr); 
		String hql=" from  TbFeedBack where (feedOpenId=?) and  (feedBackState=?)  ";
		List<TbFeedBack> feedBackList=feedBackDao.findByHQL(hql, tf.getFeedOpenId(),"未回复"  );
		for(TbFeedBack tbFeedBack:feedBackList){
			tbFeedBack.setFeedBackState("已回复"); 
			feedBackDao.saveOrUpdate(tbFeedBack);
		} 
		resultMap.put("openId", tf.getFeedOpenId());
		resultMap.put("code", "1");
		resultMap.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		return resultMap;
	}

}
