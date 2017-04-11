package com.traffic.web.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ElectorDao;
import com.traffic.dao.TenderDao;
import com.traffic.pojo.TbElector;
import com.traffic.pojo.TbTender;
import com.traffic.pojo.TbVote;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbTenderVo;
import com.traffic.util.CommonUtil;
import com.traffic.util.UUIDGenerator;
import com.traffic.web.service.ElectorService;
import com.traffic.web.service.TenderService;



@Service("tenderService")
public class TenderServiceImpl implements TenderService {
	
	@Autowired
	TenderDao tenderDao;

	@Override
	public HashMap<String, Object> getTenderByPage(String voteId,String name,String openId,String nickName,String startTime,String endTime,
			Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String hql=" select count(*) from TbTender a,TbWeiUser b,TbElector c      ";
		String StrOrder="";  
		String StrCdt="";
		StrCdt = " where   (a.openId=b.openId and a.voteId=c.voteId and a.electorId=c.number) ";
		ArrayList arraylist=new ArrayList();
		if( voteId.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( a.voteId = ? ) ";
            arraylist.add(voteId);  
        }
		if( name.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( c.name like ? ) ";
            arraylist.add("%"+name+"%");  
        }
		if( openId.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( a.openId like ? ) ";
            arraylist.add("%"+openId+"%");  
        }
		if( nickName.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( b.nickname like ? ) ";
            arraylist.add("%"+nickName+"%");  
        }
		if( startTime.length()>0){
			Timestamp startTime1 = Timestamp.valueOf(startTime+" 00:00:00");
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( a.createTime >= ? ) ";
            arraylist.add(startTime1);  
        }
		if( endTime.length()>0){
			Timestamp endTime1 = Timestamp.valueOf(endTime+" 23:59:59");
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( a.createTime <= ? ) ";
            arraylist.add(endTime1);  
        }
		Integer listSize = tenderDao.findCount(hql + StrCdt + StrOrder, arraylist);
		
		StrOrder=" order by createTime desc ";
	       
        hql="select c.name,b.headimgurl,a.openId,b.nickname,a.createTime,b.city,b.subscribeTime from TbTender a,TbWeiUser b,TbElector c    "+StrCdt   + StrOrder;  
        List mResult=  tenderDao.findPage(hql,(pageIndex - 1) * pageSize, pageSize,arraylist); 
    	
        Object[] rows;
        TbTenderVo tenderVo;
        List<TbTenderVo> tenderVoList = new ArrayList<TbTenderVo>();
        
        for (Iterator iter = mResult.iterator(); iter.hasNext();) {
			rows = (Object[]) iter.next();
			tenderVo = new TbTenderVo();
			tenderVo.setName(CommonUtil.trim(rows[0]));
			tenderVo.setHeadimgurl(CommonUtil.trim(rows[1]));
			tenderVo.setOpenId(CommonUtil.trim(rows[2]));
			tenderVo.setNickName(CommonUtil.trim(rows[3]));
			tenderVo.setCreateTime(CommonUtil.getDateForm(rows[4],
					"yyyy-MM-dd HH:mm:ss"));
			tenderVo.setCity(CommonUtil.trim(rows[5]));
			if(rows[6]!=null&&!"".equals(rows[6])){
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
				String time=sdf.format(new Date(Long.parseLong(rows[6].toString())*1000L));
				tenderVo.setSubscribeTime(time);
			}else{
				tenderVo.setSubscribeTime("");
			}
			

			tenderVoList.add(tenderVo);
        }
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tenderVoList);
		
		return resultMap;
	}


	
}

