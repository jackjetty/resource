package com.detachment.web.service.impl;

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
import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.detachment.dao.CustResDao;
import com.detachment.dao.FeedBackDao;
import com.detachment.dao.UserDao;
import com.detachment.json.JsFeedBack;
import com.detachment.pojo.TbCustRes;
import com.detachment.pojo.TbFeedBack;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbFeedBackVo;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.web.service.FeedBackService; 
import com.detachment.wei.service.CustResService;
 

@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	private Log log = LogFactory.getLog(FeedBackServiceImpl.class);
	@Autowired
	private FeedBackDao feedBackDao;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustResDao custResDao;
	
	@Autowired
	private CustResService custResService;
	
	
	@Override
	public HashMap<String, Object> feedBackPage(int pageIndex,String dateFrom,String dateEnd) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		 
        pageIndex=pageIndex<=0?1:pageIndex; 
        
		int pageSize=20;
		dateFrom=CommonUtil.trim(dateFrom);
		dateEnd=CommonUtil.trim(dateEnd);
		dateFrom=dateFrom.equals("")?CommonUtil.getBeforeWeekDateStrForm():dateFrom;
		dateEnd=dateEnd.equals("")?CommonUtil.getCurrentDateStrForm():dateEnd;
		
		String hql=" select count(*) from TbFeedBack as ta, TbWeiUser as tu   "; 
		String StrOrder=""; 
		String StrCdt=""; 
		 
		StrCdt=" where   (ta.feedOpenId=tu.openId)    ";
		
        ArrayList arraylist=new ArrayList(); 
        StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        StrCdt+="    (CONVERT(varchar(100),  ta.feedTime, 23) between ? and ? ) ";
        arraylist.add(dateFrom); 
        arraylist.add(dateEnd); 
        int recordSum=feedBackDao.findCount(hql+StrCdt, arraylist); 
        int pageSum=recordSum%pageSize==0?recordSum/pageSize:recordSum/pageSize+1;
        
        pageIndex=pageIndex>pageSum?pageSum:pageIndex; 
        pageIndex=pageIndex>=1?pageIndex:1;
        pageSum=pageSum<1?1:pageSum; 
        result.put("recordSum", recordSum);
        result.put("pageIndex", pageIndex);
        result.put("pageSum", pageSum);
        result.put("dateFrom", dateFrom);
        result.put("dateEnd", dateEnd); 
        StrOrder=" order by ta.feedTime desc"; 
        
        //feedBackId,feedTime,feedOpenId,feedText,recordType,feedBackState
        //openId,subscribe,nickname,sex,city,country,province,language,headimgurl,subscribe_time,phoneNumber,realName,weiUserTypeId,remark
        hql="select ta.feedBackId,ta.feedOpenId,ta.feedText,ta.feedTime,ta.feedBackState " +
        		",tu.nickname " +
        		"  from TbFeedBack as ta, TbWeiUser as tu    ";
        List mResult=feedBackDao.findPage(hql+StrCdt+StrOrder, (pageIndex - 1) * pageSize, pageSize, arraylist);
        
        Object[] rows;
        
        
        JsFeedBack jsFeedBack;
		List<JsFeedBack> jsFeedBackList=new ArrayList<JsFeedBack>();
		String feedText;
		for (Iterator iter = mResult.iterator(); iter.hasNext();) {
			  rows=(Object[]) iter.next();
			  jsFeedBack=new JsFeedBack();
			  jsFeedBack.setFeedBackId(CommonUtil.trim(rows[0]));
			  jsFeedBack.setFeedOpenId(CommonUtil.trim(rows[1]));
			  feedText=CommonUtil.trim(rows[2]);
			  if(feedText.length()>10){
				  feedText=feedText.substring(0, 10);
			  }
			  jsFeedBack.setFeedText(feedText);
			  jsFeedBack.setFeedTime(CommonUtil.getDateForm(rows[3],"yyyy-MM-dd HH:mm:ss"));
			  jsFeedBack.setFeedBackState(CommonUtil.trim(rows[4]));
			  jsFeedBack.setNickname(CommonUtil.trim(rows[5])); 
			  jsFeedBackList.add(jsFeedBack);   
		} 
        
		result.put("jsFeedBackList", jsFeedBackList);  
		return result;
	}
	
	public HashMap<String, Object> feedBackChatPage(int feedBackId){
		HashMap<String, Object> result = new HashMap<String, Object>();
		TbFeedBack tbFeedBack=feedBackDao.findById(feedBackId);
		String feedOpenId=CommonUtil.trim(tbFeedBack.getFeedOpenId());
		String hql="select ta.feedBackId,ta.feedOpenId,ta.feedText,ta.feedTime,ta.feedBackState " +
        		",tu.nickname " +
        		"  from TbFeedBack as ta, TbWeiUser as tu    ";
		String StrOrder=""; 
		String StrCdt=""; 
		 
		StrCdt=" where   (ta.feedOpenId=tu.openId)    ";
		
        ArrayList arraylist=new ArrayList(); 
        StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        StrCdt+="      (ta.feedOpenId= ? ) ";
        arraylist.add(feedOpenId);
        
        
        StrOrder=" order by ta.feedTime desc"; 
        List mResult=feedBackDao.findByHQL(hql+StrCdt+StrOrder, arraylist);
        Object[] rows; 
        JsFeedBack jsFeedBack;
        
        
        
		List<JsFeedBack> jsFeedBackList=new ArrayList<JsFeedBack>();
		 
		for (Iterator iter = mResult.iterator(); iter.hasNext();) {
			  rows=(Object[]) iter.next();
			  jsFeedBack=new JsFeedBack();
			  jsFeedBack.setFeedBackId(CommonUtil.trim(rows[0]));
			  jsFeedBack.setFeedOpenId(CommonUtil.trim(rows[1]));
			   
			  jsFeedBack.setFeedText(CommonUtil.trim(rows[2]));
			  jsFeedBack.setFeedTime(CommonUtil.getDateForm(rows[3],"yyyy-MM-dd HH:mm:ss"));
			  jsFeedBack.setFeedBackState(CommonUtil.trim(rows[4]));
			  jsFeedBack.setNickname(CommonUtil.trim(rows[5])); 
			  jsFeedBackList.add(jsFeedBack);   
		} 
		 
		//processId,recordNo,custResTime,custResContent,openId,custResponser,custResType
		List<TbCustRes> custResList= custResDao.findByHQL("from TbCustRes where openId = ? and custResType=? ", feedOpenId,"FEEDBACK");
		for(TbCustRes tbCustRes:custResList){
			
			jsFeedBack=new JsFeedBack();
			jsFeedBack.setFeedBackId(CommonUtil.trim(tbCustRes.getCustResId()));
			jsFeedBack.setFeedOpenId("");
			
			jsFeedBack.setFeedText(CommonUtil.trim(tbCustRes.getCustResContent()));
			jsFeedBack.setFeedTime(CommonUtil.getDateForm(tbCustRes.getCustResTime(),"yyyy-MM-dd HH:mm:ss"));
			jsFeedBack.setNickname("");
			jsFeedBackList.add(jsFeedBack);  
		} 
		
		
		Collections.sort(jsFeedBackList, new Comparator<JsFeedBack>() {
	        @Override
	        public int compare(JsFeedBack o1, JsFeedBack o2) {  
	                return  -1*o1.getFeedTime().compareTo(o2.getFeedTime());
	        	}
		 }); 
       
		
		
		result.put("feedOpenId", feedOpenId);  
		result.put("jsFeedBackList", jsFeedBackList);  
		return result;
		
		
	}
	@Override
	public Integer getFeedBackSize(String startTime, String endTime,String recordType) {
		Date startDate = null;
		Date endDate = null;
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime.toString() + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime.toString() + " 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Integer listSize = feedBackDao.getFeedBackListSize(startDate,endDate,recordType);
		return listSize;
	}
	@Override
	public ArrayList<TbFeedBackVo> getFeedBackVo(Integer pageSize, Integer pageIndex, String startTime, String endTime,String recordType) {
		Date startDate = null;
		Date endDate = null;
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime.toString() + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime.toString() + " 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Integer start = (pageIndex - 1) * pageSize;
		List<Object[]> feedBackList = feedBackDao.getFeedBack(startDate,endDate,recordType,start, pageSize);
		ArrayList<TbFeedBackVo> feedBackVoList = new ArrayList<TbFeedBackVo>();

		for (Object[] objects : feedBackList) {
			TbFeedBackVo feedBackVo = new TbFeedBackVo(objects);
			feedBackVoList.add(feedBackVo);
		}
		
		return feedBackVoList;
	}
	@Override
	public TbFeedBackVo getFeedBackById(Integer feedBackId) {
		TbFeedBackVo feedBackVo = feedBackDao.getFeedBackById(feedBackId);
		return feedBackVo;
	}
	@Override
	public HashMap<String, Object> getUserFbInfo(Integer feedBackId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Object[]> fb = feedBackDao.getUserFbInfo(feedBackId);
		ArrayList<TbFeedBackVo> tfbv = new ArrayList<TbFeedBackVo>();
		for(int i=0;i<fb.size();i++){
			String feedTime = new SimpleDateFormat("yyyy-MM-dd").format(fb.get(i)[0]);
			String feedInfo = String.valueOf(fb.get(i)[1]);
			TbFeedBackVo fbv = new TbFeedBackVo(feedTime,feedInfo);
			tfbv.add(fbv);
		}
		resultMap.put("rows", tfbv);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> getUserFbInfoReply(Integer feedBackId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbFeedBack tf=feedBackDao.findById(feedBackId);
		ArrayList<TbFeedBack> tfb=feedBackDao.getFeedBackByOpenIdType(tf.getFeedOpenId());
		ArrayList<TbCustRes> tcr=(ArrayList<TbCustRes>) custResDao.findByHQL("from TbCustRes where openId = ? and custResType='FEEDBACK' ", tf.getFeedOpenId());
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
	public HashMap<String, Object> feedBackCustRes(String  feedOpenId,String content) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
	 
		ErrCodeBean errCodeBean=custResService.responseTextMessage(feedOpenId, content);
		resultMap.put("respCode", errCodeBean.getErrcode());
		resultMap.put("respInfo", errCodeBean.getErrmsg());
		if(!errCodeBean.getErrcode().equalsIgnoreCase("0")){
			resultMap.put("respCode",1);
			return resultMap;
		}
		resultMap.put("respCode",0); 
		TbUser tbUser=(TbUser) ActionContext.getContext().getSession().get("Manager");
        Date date=new Date(); 
		TbCustRes tbCustRes=new TbCustRes();
		tbCustRes.setRecordNo(feedOpenId);
		tbCustRes.setProcessId(Constant.FEEDBACK);
		tbCustRes.setCustResContent(content);
		tbCustRes.setCustResType("FEEDBACK");
		tbCustRes.setOpenId(feedOpenId);
		tbCustRes.setCustResTime(new Timestamp(date.getTime())); 
		tbCustRes.setTbUser(tbUser);
		custResDao.save(tbCustRes);
		String hql="from TbFeedBack where feedOpenId=? and feedBackState=? ";
		List<TbFeedBack> feedBackList=feedBackDao.findByHQL(hql, feedOpenId,"未回复");
		for(TbFeedBack tbFeedBack:feedBackList){
			tbFeedBack.setFeedBackState("已回复");
			feedBackDao.saveOrUpdate(tbFeedBack);			
		}  
		resultMap.put("custResTime", CommonUtil.getDateForm(tbCustRes.getCustResTime(),"yyyy-MM-dd HH:mm:ss"));
		return resultMap;
	}

}
