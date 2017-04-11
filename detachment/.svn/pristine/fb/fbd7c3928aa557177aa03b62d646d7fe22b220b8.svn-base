package com.detachment.web.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.FullScodeDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbFullScode;
import com.detachment.pojo.vo.TbFullScodeVo;
import com.detachment.util.CommonUtil;
import com.detachment.web.service.FullScodeService;
import com.detachment.wei.wsdd.ClientWSDD;

@Service
public class FullScodeServiceImpl implements FullScodeService{
	
	@Autowired
	FullScodeDao fullScodeDao; 
	
	@Autowired
	WeiUserDao weiUserDao;
	@Override
	public TbFullScodeVo getTbFullScodeByIdCard(String identityCard) {
		TbFullScode tfs=fullScodeDao.findById(identityCard);
		TbFullScodeVo tfsvo=new TbFullScodeVo(tfs);
		return tfsvo;
	}

	@Override
	public HashMap<String, Object> saveTbFulScode(TbFullScode tfs) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		fullScodeDao.save(tfs);
		result.put("code", 0);
		return result;
	}

	@Override
	public HashMap<String, Object> toFullScodeLogin(String identityCard,
			String fileNum,String openId,String userName,String phoneNumber) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		TbFullScode tfs=fullScodeDao.findByIdentityCardAndHour(identityCard);
		
		
		if(tfs!=null){
			tfs.setFileNum(fileNum);
			tfs.setPhoneNumber(phoneNumber);
			tfs.setUserName(userName);
			tfs.setTbWeiUser(weiUserDao.findById(openId));
			if(fileNum.equals(tfs.getFileNum())){
				if(tfs.getDeadlineTime()!=null && tfs.getDeadlineTime().getTime()<new Date().getTime()){
					tfs.setTotalHour(0);
					fullScodeDao.update(tfs);
				}
				result.put("code", 0);
				return result;
			}else{
				result.put("code", 1);
				result.put("codeInfo", "档案号验证失败!");
				return result;
			}
		} 
		ClientWSDD cw=new ClientWSDD();
		ErrCodeBean ecb=cw.logonFullScore(identityCard, fileNum);
		Date date = new Date();
		if("0".equals(ecb.getErrcode())){
			TbFullScode tf=new TbFullScode();
			tf.setIdentityCard(identityCard);
			tf.setFileNum(fileNum);
			tf.setTbWeiUser(weiUserDao.findById(openId));
			tf.setUserName(userName);
			tf.setPhoneNumber(phoneNumber);
			tf.setTotalHour(0);
			tf.setState("学习中");
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			gc.add(3, 2);
			tf.setFirstStudyTime(new Timestamp(date.getTime()));
			tf.setDeadlineTime(new Timestamp(gc.getTime().getTime()));
			fullScodeDao.save(tf);
			result.put("code", 0);
		}else{
			result.put("code", 1);
			result.put("codeInfo", ecb.getErrmsg());
		} 
		return result;
	}

	@Override
	public HashMap<String, Object> saveFirstStudyTime(String identityCard, String openId) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		TbFullScode tfs=fullScodeDao.findByIdentityCardAndHour(identityCard);
		 
		if(tfs!=null ){
			if(tfs.getTbWeiUser()==null&&openId!=null){
				tfs.setTbWeiUser(weiUserDao.findById(openId));
			}
			
			Date date = new Date();
			if(tfs.getFirstStudyTime()==null){
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(date);
				gc.add(3, 2);
				tfs.setFirstStudyTime(new Timestamp(date.getTime()));
				tfs.setDeadlineTime(new Timestamp(gc.getTime().getTime()));
				tfs.setTotalHour(1);
				tfs.setState("学习中");
				fullScodeDao.update(tfs);
				result.put("code", 0);
				result.put("hours", 1);
			}else{
				if(tfs.getDeadlineTime().getTime()<date.getTime()){
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(date);
					gc.add(3, 2);
					tfs.setFirstStudyTime(new Timestamp(date.getTime()));
					tfs.setDeadlineTime(new Timestamp(gc.getTime().getTime()));
					tfs.setTotalHour(1);
					tfs.setState("学习中");
					fullScodeDao.update(tfs);
					result.put("code", 0);
					result.put("hours", 1);
				}else{
					Integer hours=tfs.getTotalHour()+1;
					if(hours==12){
						tfs.setTotalHour(hours);
						fullScodeDao.update(tfs);
						result.put("code", 1);
						result.put("hours", hours);
					}else{
						tfs.setTotalHour(hours);
						fullScodeDao.update(tfs);
						result.put("code", 0);
						result.put("hours", hours);
					}
					
				}
			}
		}else{
			result.put("code", -1);
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> getTotalHours(String identityCard) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		TbFullScode tbFullScode=fullScodeDao.findByIdentityCardAndHour(identityCard);
		if(tbFullScode!=null){
			Integer hours=tbFullScode.getTotalHour();
			if(hours==null){
				hours=0;
			}
			result.put("hours",hours);
		}
		return result;
	}
	
	public  HashMap<String,Object>  getFullScode(String identityCard,String fileNum,String totalHour,String state, int page, int rows){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String StrCdt=""; 
        ArrayList arraylist=new ArrayList();
         
		if( identityCard.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" (  identityCard like ? ) ";
            arraylist.add("%"+identityCard+"%");  
        }
		if( fileNum.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" (  fileNum like ? ) ";
            arraylist.add("%"+fileNum+"%");  
        }
		if(CommonUtil.isInteger(totalHour)){
			StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" (  totalHour >= ? ) ";
            arraylist.add(  Integer.parseInt(totalHour));  
		}
		if( state.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" (  state like ? ) ";
            arraylist.add("%"+state+"%");  
        }
		String sql;
        int IntTotal;  
        sql="select count(*)  from TbFullScode "+ StrCdt ; 
        IntTotal=fullScodeDao.findCount(sql, arraylist);
        String StrOrder=" order by firstStudyTime";
        String hql; 
        hql="   from  TbFullScode    "+StrCdt   + StrOrder;  
    	List list;
    	list=fullScodeDao.findPage(hql,(page - 1) * rows, rows,arraylist); 
    	List<TbFullScodeVo> jslist=new ArrayList<TbFullScodeVo> ();
    	TbFullScodeVo tbFullScodeVo;
    	TbFullScode tbFullScode;
    	ListIterator iterator;
        for(iterator = list.listIterator(); iterator.hasNext();){
        	tbFullScode=(TbFullScode)iterator.next();
        	tbFullScodeVo=fullScodeDao.toTbFullScodeVo(tbFullScode); 
        	jslist.add(tbFullScodeVo); 
        }
        resultMap.put("total", IntTotal);
        resultMap.put("rows",  jslist); 
		return resultMap;
		
		
	}
	public  HashMap<String,Object>  deductFullScode(Integer fullScodeId){
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		
			TbFullScode tbFullScode=fullScodeDao.findById(fullScodeId);
			if(tbFullScode==null){
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "不存在该记录！！");
				return resultMap;
			}else{
				if(tbFullScode.getTotalHour()==null||tbFullScode.getTotalHour().intValue()<12){
					resultMap.put("respCode", -1);
					resultMap.put("respInfo", "累计学时还未到达抵扣标准！！");
				}else{
					tbFullScode.setState("抵扣成功");
					tbFullScode.setRemark("抵扣完成");
					tbFullScode.setTotalHour(tbFullScode.getTotalHour().intValue()-12);
					fullScodeDao.saveOrUpdate(tbFullScode);
					resultMap.put("respCode", 0);
					resultMap.put("respInfo", "抵扣成功！！");
				}
				
			}
			
		return resultMap;
		
	}

	@Override
	public HashMap<String, Object> AutomaticLogin(String openId) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		TbFullScode tfs=fullScodeDao.findByOpenIdLast(openId);
		if(tfs!=null){
			if("学习中".equals(tfs.getState()) && tfs.getTotalHour()<12){
				result.put("respCode", 0);
				result.put("idCard", tfs.getIdentityCard());
			}else if("预约中".equals(tfs.getState())&& tfs.getTotalHour()==12){
				result.put("respCode", 1);
			}else if("已预约".equals(tfs.getState())&& tfs.getTotalHour()==12){
				result.put("respCode", 2);
			}else if("学习中".equals(tfs.getState())&& tfs.getTotalHour()==12){
				result.put("respCode", 3);
			}else if("抵扣成功".equals(tfs.getState())){
				result.put("respCode", -1);
			}	
		}else{
			result.put("respCode", -1);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> getPeopleInfoByIdentityCard(
			String identityCard) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		TbFullScode tfs=fullScodeDao.findByIdentityCardAndHour(identityCard);
		if(tfs!=null){
			TbFullScodeVo tfsvo=new TbFullScodeVo(tfs);
			result.put("respCode", 0);
			result.put("respInfo", tfsvo);
		}else{
			result.put("respCode", 1);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateFucScodeInfo(String userName,
			String phoneNumber, String identityCard) {
		HashMap<String,Object> result=new HashMap<String,Object>();
		TbFullScode tfs=fullScodeDao.findByIdentityCardAndHour(identityCard);
		if(tfs!=null){
			tfs.setUserName(userName);
			tfs.setPhoneNumber(phoneNumber);
			fullScodeDao.update(tfs);
			result.put("respCode", 0);
		}else{
			result.put("respCode", 1);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateFullScodeState(Integer fullScodeId) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		TbFullScode tbFullScode=fullScodeDao.findById(fullScodeId);
		if(tbFullScode==null){
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "不存在该记录！！");
			return resultMap;
		}else{
			if(tbFullScode.getTotalHour()==null||tbFullScode.getTotalHour().intValue()<12){
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "累计学时还未到达抵扣标准！！");
			}else{
				tbFullScode.setState("预约中");
				fullScodeDao.saveOrUpdate(tbFullScode);
				resultMap.put("respCode", 0);
				resultMap.put("respInfo", tbFullScode.getTbWeiUser().getOpenId());
			}
			
		}
		
		return resultMap;
	} 
	
}
