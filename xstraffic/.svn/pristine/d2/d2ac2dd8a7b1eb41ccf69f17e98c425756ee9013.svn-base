package com.traffic.wap.service.impl;

 
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rising.wei.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.traffic.dao.AccidentDao;
import com.traffic.dao.ElectorDao;
import com.traffic.dao.InfoPicDao;
import com.traffic.dao.InfoTextDao;
import com.traffic.dao.MoveCarDao;
import com.traffic.dao.TenderDao;
import com.traffic.dao.UserDao;
import com.traffic.dao.VoteDao;
import com.traffic.dao.WeiUserDao;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbElector;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoText;
import com.traffic.pojo.TbRole;
import com.traffic.pojo.TbTender;
import com.traffic.pojo.TbUser;
import com.traffic.pojo.TbVote;
import com.traffic.pojo.TbWeiUser;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbInfoTextVo;
import com.traffic.util.CommonUtil;
import com.traffic.util.Constant;
import com.traffic.util.DecriptUtil;
import com.traffic.util.GpsToBaiDu;
import com.traffic.util.HttpWeiUtil;
import com.traffic.util.UUIDGenerator;
import com.traffic.wap.service.VoteWapService;
import com.traffic.web.service.AccidentService;
import com.traffic.web.service.impl.AccidentServiceImpl;
 

@Service("voteWapService")
public class VoteWapServiceImpl implements VoteWapService {

	
	Log log = LogFactory.getLog(VoteWapServiceImpl.class);
	@Autowired
	private VoteDao voteDao;
	
	@Autowired
	private ElectorDao electorDao;
	
	@Autowired
	private WeiUserDao weiUserDao; 
	
	@Autowired
	private TenderDao tenderDao;
	
	@Value("${wei.appid}") 
	private String  APPID; 
	
	@Value("${wei.appsecret}") 
	private String  APPSECRET; 
	
	
	
	
	//没问题
	@Override
	public HashMap<String, Object> voteWapPage(String code,String voteId,String url) {
		
		// TODO Auto-generated method stub 
		code=CommonUtil.trim(code);
		voteId=CommonUtil.trim(voteId);
		String oAuthAccessToken,openId="";
		
		
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		openId=CommonUtil.trim(session.get("openId"));
		  
		 
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(openId.equals("") && (!code.equals(""))  ){
			
			
			oAuthAccessToken=HttpWeiUtil.getInstance(APPID, APPSECRET).getOAuthAccessToken(code);
			if(oAuthAccessToken.equals("")){
				oAuthAccessToken=HttpWeiUtil.getInstance(APPID, APPSECRET).getOAuthAccessToken(code);
			}
			if(StringUtils.isNotEmpty(oAuthAccessToken)){
				// = JSON.parseObject(json,);
				JSONObject obj = JSONObject.parseObject(oAuthAccessToken);
				 
				if(!CommonUtil.trim(obj.getString("openid")).equals("")){
					openId=CommonUtil.trim(obj.getString("openid"));
					session.put("openId", openId);   
				}
				    
			}
		} 
		openId=CommonUtil.trim(openId);
		resultMap.put("subscribe", false); 
		
		if(!openId.equals("")){
			TbWeiUser tbWeiUser=weiUserDao.findById(openId);
			if(tbWeiUser==null){ 
				tbWeiUser=new TbWeiUser();
				tbWeiUser.setOpenId(openId); 
			}
			if(CommonUtil.trim(tbWeiUser.getSubscribeTime()).equalsIgnoreCase("")){
				try{
					UserBean userBean= HttpWeiUtil.getInstance(APPID, APPSECRET).getUserBean(  openId);
					
					if(userBean.getSubscribe()==1){
						resultMap.put("subscribe", true);
						tbWeiUser.setCity(userBean.getCity());
						tbWeiUser.setCountry(userBean.getCountry());
						tbWeiUser.setHeadimgurl(userBean.getHeadimgurl());
						tbWeiUser.setLanguage(userBean.getLanguage());
						tbWeiUser.setNickname(userBean.getNickname());
						tbWeiUser.setProvince(userBean.getProvince());
						tbWeiUser.setSex(userBean.getSex());
						tbWeiUser.setSubscribe(userBean.getSubscribe()); 
					}
					
					if(userBean.getSubscribe_time()!=null)
					       tbWeiUser.setSubscribeTime(Long.toString(userBean.getSubscribe_time())); 
					weiUserDao.saveOrUpdate(tbWeiUser);
					
				}catch(Exception ex){
					
				}
			}else{
				if(tbWeiUser.getSubscribe()==1){
					resultMap.put("subscribe", true);
				}
			}
			
			
			
		} 
		
		String WxDebug="true";
		String WxAppId=APPID;
		String WxNoncestr="Wm3WZYTPz0wzccnW";
		String WxTimestamp=new Long(System.currentTimeMillis()/1000).toString();
		//String WxUrl="http://www.weiximen.com/xstraffic/wap/voteHome.action?voteId=bd2389ac6d3f4c14b30d9b56fbbe3f8b";
		//WxUrl+="&code="+code+"&state=1";
		url=url.replace("218.75.83.141", "www.weiximen.com");
		String WxUrl=url; 
		resultMap.put("WxDebug", WxDebug);
		resultMap.put("WxAppId", WxAppId);
		resultMap.put("WxTimestamp", WxTimestamp);
		resultMap.put("WxNoncestr", WxNoncestr);
		
		String unDecriptText="jsapi_ticket=" +HttpWeiUtil.getInstance(APPID, APPSECRET ).getJsTicket()+
				"&noncestr=" +WxNoncestr+
				"&timestamp=" +WxTimestamp+
				"&url="+WxUrl;
		String WxSignature=DecriptUtil.SHA1(unDecriptText);
		resultMap.put("WxSignature", WxSignature);
		
	 
	
		
		
		TbVote tbVote=voteDao.findById(voteId);
		resultMap.put("success", true);
		if(tbVote==null){
			
			resultMap.put("success", false);
			return resultMap;
		}
		
		
		
		
		
		
		
		resultMap.put("tbVote", tbVote);
		return resultMap;
	}
	//没问题
	@Override
	public HashMap<String, Object> voteWapElectors(String voteId,String url) {
		
		// TODO Auto-generated method stub 
		voteId=CommonUtil.trim(voteId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String StrOrder="";  
		String StrCdt=""; 
		String hql = "   from  TbElector    ";
		
		StrOrder=" order by number asc ";
		
		ArrayList arraylist=new ArrayList();
		
		StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
	    StrCdt+=" ( voteId = ? ) ";
	    arraylist.add(voteId);
		List<TbElector> electors =  electorDao.findByHQL(hql+StrCdt+StrOrder, arraylist);
		
		 
		
		String WxDebug="true";
		String WxAppId=APPID;
		String WxNoncestr="Wm3WZYTPz0wzccnW";
		String WxTimestamp=new Long(System.currentTimeMillis()/1000).toString();
		
		url=url.replace("218.75.83.141", "www.weiximen.com");
		String WxUrl=url; 
		resultMap.put("WxDebug", WxDebug);
		resultMap.put("WxAppId", WxAppId);
		resultMap.put("WxTimestamp", WxTimestamp);
		resultMap.put("WxNoncestr", WxNoncestr);
		
		String unDecriptText="jsapi_ticket=" +HttpWeiUtil.getInstance(APPID, APPSECRET ).getJsTicket()+
				"&noncestr=" +WxNoncestr+
				"&timestamp=" +WxTimestamp+
				"&url="+WxUrl;
		String WxSignature=DecriptUtil.SHA1(unDecriptText);
		resultMap.put("WxSignature", WxSignature);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String openId = (String) session.get("openId"); 
		openId=CommonUtil.trim(openId);
		 
		  
        hql="select count(*)  from  TbTender  where ( voteId = ? )  and  ( openId = ? )  and  ( convert(varchar(100),createTime ,23)  = ? )  "   ;
        Integer listSize = tenderDao.findCount(hql, voteId,openId,CommonUtil.getDateForm());
        if(listSize>0){//当天已投过票了
        	resultMap.put("hadVote", true);
        }else{
        	resultMap.put("hadVote", false);
        }
        TbVote vote = voteDao.findById(voteId);
        Timestamp endTime = vote.getEndTime();
		Timestamp currTime =new Timestamp(new Date().getTime());
		resultMap.put("overdue", false); 
		
		if(endTime.getTime()<currTime.getTime()){ 
			resultMap.put("hadVote", true); 
			resultMap.put("overdue", true); 
		} 
		
		resultMap.put("success", true);
		if(electors==null){
			
			resultMap.put("success", false);
			return resultMap;
		}
		resultMap.put("electors", electors);
		
		 
		
		
		return resultMap;
	}
	//没问题
	@Override
	public HashMap<String, Object> voteWapTopElectors(String voteId,String url) {
		
		// TODO Auto-generated method stub 
		voteId=CommonUtil.trim(voteId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String StrOrder="";  
		String StrCdt=""; 
		String hql = "   from  TbElector    ";
		
		StrOrder=" order by votes desc ";
		
		ArrayList arraylist=new ArrayList();
		
		StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
	    StrCdt+=" ( voteId = ? ) ";
	    arraylist.add(voteId);
		ArrayList<TbElector> electors = (ArrayList<TbElector>) electorDao.findByHQL(hql+StrCdt+StrOrder, arraylist);
		String WxDebug="true";
		String WxAppId=APPID;
		String WxNoncestr="Wm3WZYTPz0wzccnW";
		String WxTimestamp=new Long(System.currentTimeMillis()/1000).toString();
		
		url=url.replace("218.75.83.141", "www.weiximen.com");
		String WxUrl=url; 
		resultMap.put("WxDebug", WxDebug);
		resultMap.put("WxAppId", WxAppId);
		resultMap.put("WxTimestamp", WxTimestamp);
		resultMap.put("WxNoncestr", WxNoncestr);
		
		String unDecriptText="jsapi_ticket=" +HttpWeiUtil.getInstance(APPID, APPSECRET ).getJsTicket()+
				"&noncestr=" +WxNoncestr+
				"&timestamp=" +WxTimestamp+
				"&url="+WxUrl;
		String WxSignature=DecriptUtil.SHA1(unDecriptText);
		resultMap.put("WxSignature", WxSignature);
		resultMap.put("success", true);
		if(electors==null){
			
			resultMap.put("success", false);
			return resultMap;
		}
		resultMap.put("electors", electors);
		resultMap.put("voteId", voteId);
		return resultMap;
	}
	//没问题
	@Override
	public HashMap<String, Object> voteWapElectoDetail(String id,String url) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbElector elector = electorDao.findById(id);
		String WxDebug="true";
		String WxAppId=APPID;
		String WxNoncestr="Wm3WZYTPz0wzccnW";
		String WxTimestamp=new Long(System.currentTimeMillis()/1000).toString();
		
		url=url.replace("218.75.83.141", "www.weiximen.com");
		String WxUrl=url; 
		resultMap.put("WxDebug", WxDebug);
		resultMap.put("WxAppId", WxAppId);
		resultMap.put("WxTimestamp", WxTimestamp);
		resultMap.put("WxNoncestr", WxNoncestr);
		
		String unDecriptText="jsapi_ticket=" +HttpWeiUtil.getInstance(APPID, APPSECRET ).getJsTicket()+
				"&noncestr=" +WxNoncestr+
				"&timestamp=" +WxTimestamp+
				"&url="+WxUrl;
		String WxSignature=DecriptUtil.SHA1(unDecriptText);
		resultMap.put("WxSignature", WxSignature);
		resultMap.put("success", true);
		if(elector==null){
			
			resultMap.put("success", false);
			return resultMap;
		}
		resultMap.put("elector", elector);
		return resultMap;
	}

	
	@Override
	public HashMap<String, Object> voteWapDoVote(String voteId,String electorStr) {
		
		// TODO Auto-generated method stub
		Map<String, Object> session = ActionContext.getContext().getSession();
		String openId = (String) session.get("openId");
		voteId=CommonUtil.trim(voteId);
		openId=CommonUtil.trim(openId);
		electorStr=CommonUtil.trim(electorStr);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		JSONArray jsonArray = JSONArray.parseArray(electorStr);
		TbVote vote = voteDao.findById(voteId);
		if(vote==null){
			resultMap.put("success", false);
			resultMap.put("msg", "投票已结束");
			return resultMap;
		}
		Timestamp endTime = vote.getEndTime();
		Timestamp timestamp =new Timestamp(new Date().getTime());
		if(endTime.getTime()<timestamp.getTime()){
			resultMap.put("success", false);
			resultMap.put("msg", "投票已结束");
			return resultMap;
		}
		ArrayList arraylistT=new ArrayList();
		 
		 
		String StrCdtT=""; 
		String hqlT = "";
		StrCdtT=StrCdtT.length()==0?" where  ":StrCdtT+" and ";
		StrCdtT+=" ( voteId = ? ) ";
		arraylistT.add( voteId);
		StrCdtT=StrCdtT.length()==0?" where  ":StrCdtT+" and ";
		StrCdtT+=" ( openId = ? ) ";
		arraylistT.add( openId);
	    StrCdtT=StrCdtT.length()==0?" where  ":StrCdtT+" and ";
	    StrCdtT+=" ( convert(varchar(100),createTime ,23)  = ? ) "; 
	    arraylistT.add( CommonUtil.getDateForm());
         
        
        hqlT="select count(*)  from  TbTender    "+StrCdtT  ;
        Integer listSize = tenderDao.findCount(hqlT, arraylistT);
        if(listSize>0){//当天已投过票了
        	resultMap.put("success", false);
        	resultMap.put("msg", "已经投过票了，请明天再来");
        	return resultMap;
        }
        TbTender tender;
        String hql;
        TbElector elector ;
		try{
			for(int i=0;i<jsonArray.size(); i++){
				tender= new TbTender();
				JSONObject jsonJ = jsonArray.getJSONObject(i);
				String electorId = jsonJ.getString("electorId");
				String electorName = jsonJ.getString("electorName");
				tender.setId(UUIDGenerator.getUUID());
				tender.setVoteId(voteId);
				tender.setElectorId(electorId);
				tender.setElectorName(electorName);
				tender.setOpenId(openId);
				tender.setCreateTime(new Timestamp(new Date().getTime()));
				hql = "   from  TbElector  where ( voteId = ?)  and ( number = ? ) ";
				
				 
				elector = electorDao.findByHQL(hql , voteId,Integer.parseInt(electorId)).get(0);
				elector.setVotes(elector.getVotes()+1);
				elector.setPercent(new Double(12.0d));
				tenderDao.saveOrUpdate(tender);
				electorDao.saveOrUpdate(elector);
				
			}
			vote.setCount(vote.getCount()+jsonArray.size());
			voteDao.saveOrUpdate(vote);
			//百分比的就先不要计算了
			/*
			String StrOrder="";  
			String StrCdt=""; 
			hql = "   from  TbElector    ";
			ArrayList arraylist=new ArrayList();
			StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
		    StrCdt+=" ( voteId = ? ) ";
		    arraylist.add(voteId);
			ArrayList<TbElector> electors = (ArrayList<TbElector>) electorDao.findByHQL(hql+StrCdt+StrOrder, arraylist);
			for(TbElector e : electors){
				float percent = (float)e.getVotes()/vote.getCount();
				DecimalFormat df = new DecimalFormat("0.0000");
				String s = df.format(percent);
				e.setPercent(Double.parseDouble(s)*100);
			}*/
			resultMap.put("success", true);
			resultMap.put("msg", "投票成功，请明天再来");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "投票失败");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> voteWapCheckTender(String voteId) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String openId = (String) session.get("openId");
		voteId=CommonUtil.trim(voteId);
		openId=CommonUtil.trim(openId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String StrCdtT=""; 
		String hqlT = "";
		 
		ArrayList arraylistT=new ArrayList();
		 
		StrCdtT=StrCdtT.length()==0?" where  ":StrCdtT+" and ";
		StrCdtT+=" ( voteId = ? ) ";
		arraylistT.add( voteId);
		StrCdtT=StrCdtT.length()==0?" where  ":StrCdtT+" and ";
		StrCdtT+=" ( openId = ? ) ";
		arraylistT.add( openId);
		StrCdtT=StrCdtT.length()==0?" where  ":StrCdtT+" and ";
		StrCdtT+=" ( convert(varchar(100),createTime ,23)  = ? ) "; 
		arraylistT.add( CommonUtil.getDateForm());
        
        hqlT="select count(*)  from  TbTender    "+StrCdtT  ;
        Integer listSize = tenderDao.findCount(hqlT, arraylistT);
        if(listSize>0){//当天已投过票了
        	resultMap.put("success", true);
        }else{
        	resultMap.put("success", false);
        }
		return resultMap;
	}

	
 
 
}
	
	
	
 