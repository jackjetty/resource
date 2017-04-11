package com.detachment.dao.impl;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List; 

import org.hibernate.Query;
import org.springframework.stereotype.Repository;  
import com.detachment.dao.CarMoveDao;
import com.detachment.pojo.TbCarMove;
import com.detachment.pojo.TbInfoPic;
import com.detachment.util.CommonUtil;  
@Repository("carMoveDao")
public class CarMoveDaoImpl    extends BaseDaoImpl<TbCarMove> implements  CarMoveDao{
	@SuppressWarnings("unchecked")
	@Override
	public String  getNextCarMoveId (String prefix) { 
		String nextCarMoveId=prefix+CommonUtil.getDateForm("yyMMdd");
		int currentNum=0; 
		String hql = "select max(carMoveId) from TbCarMove where carMoveId like ? ";
		List list=this.findByHQL(hql, nextCarMoveId+"%"); 
		if(list.size()>0&&list.get(0)!=null){
			String maxCarMoveId= list.get(0).toString();
			currentNum=CommonUtil.getCurrentNum(maxCarMoveId,nextCarMoveId);
		}
		currentNum++;
		DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        aDecimalFormat.applyPattern("0000");
        return nextCarMoveId+aDecimalFormat.format(currentNum);  
	} 
	public boolean isLimitReport(int interval,String carType ,String carNumber){ 
		//int interval=Integer.parseInt(ProcessService.CARMOVE_LIMITTIME); 
		String hql="select count(*) from TbCarMove where   carNumber=? and reportTime>=? and (carType is null or carType=?)and (carMoveState is null or carMoveState=?)";
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MINUTE, -1*interval);
		Date date=cal.getTime();  
		List list=this.findByHQL(hql, carNumber,new Timestamp(date.getTime()),carType,"已通知"); 
		if(list==null||list.size()==0)
			 return false;
		if(list.get(0)==null)
			 return false; 
		if(((Number) list.get(0)).intValue()>0)
			 return true;
		return false;   
	}
	public int  getDayReportCount(String openId){
		String hql="select count(*) from TbCarMove where  (reportOpenId=?) and  (CONVERT(varchar(100), reportTime, 23)=?)   and (carMoveState is null or carMoveState=?)";
		 
		List list=this.findByHQL(hql, openId,CommonUtil.getDateForm(),  "已通知"); 
		if(list==null||list.size()==0)
			 return 0;
		if(list.get(0)==null)
			 return 0; 
		if(((Number) list.get(0)).intValue()>0)
			 return ((Number) list.get(0)).intValue();
		return 0;
	}

	
	 
 

	 

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId) {
		Query query = getSession().createQuery(
				"from TbInfoPic where recordNo = :recordNo");
		query.setParameter("recordNo", carMoveId);
		ArrayList<TbInfoPic> tap = (ArrayList<TbInfoPic>) query
				.list();
		return tap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getMoveCarAddress(String carMoveId) {
		String hql = "select locationX,locationY,address from TbCarMove where carMoveId = :carMoveId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carMoveId", carMoveId);
		ArrayList<Object[]> addr = (ArrayList<Object[]>) query.list();
		return addr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbCarMove getMoveCarById(String carMoveId) {
		String hql = "from TbCarMove where carMoveId = :carMoveId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carMoveId", carMoveId);
		ArrayList<TbCarMove> addr = (ArrayList<TbCarMove>) query.list();
		return addr.get(0);
	}

	@Override
	public void exchangeStatus(TbCarMove tc) {
		getSession().update(tc);
		getSession().flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMcNumber() {
		String id = "CM";
		String hql = "from TbCarMove where carMoveId like :carMoveId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carMoveId", "%"+id+"%");
		List<TbCarMove> list = query.list();
		return list.size();
	}


	@Override
	public void updateMoveCarStateSuccess(String managerName, String carMoveId) {
		String hql = "update TbCarMove set accepter = :managerName,carMoveState = '审核通过' where carMoveId = :carMoveId";
		Query query =getSession().createQuery(hql);
		query.setParameter("managerName", managerName)
				.setParameter("carMoveId", carMoveId).executeUpdate();
		getSession().flush();
		
	}

	@Override
	public void updateMoveCarStateFail(String carMoveId) {
		String hql = "update TbCarMove set carMoveState = '待定' where carMoveId =:carMoveId";
		getSession().createQuery(hql).setParameter("carMoveId", carMoveId)
				.executeUpdate();
		getSession().flush();
		
	}
	
	 
}