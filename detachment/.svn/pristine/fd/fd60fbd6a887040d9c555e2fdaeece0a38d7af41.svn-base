package com.detachment.dao.impl;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 

import org.hibernate.Query;
import org.springframework.stereotype.Repository;  

 
import com.detachment.dao.HandyPhotoDao;
import com.detachment.pojo.TbHandyPhoto;
import com.detachment.util.CommonUtil;  
@Repository("handyPhotoDao")
public class HandyPhotoDaoImpl    extends BaseDaoImpl<TbHandyPhoto> implements  HandyPhotoDao{
	@SuppressWarnings("unchecked")
	@Override
	public String  getNextHandyPhotoId (String prefix) { 
		String nextHandyPhotoId=prefix+CommonUtil.getDateForm("yyMMdd");
		int currentNum=0; 
		String hql = "select max(handyPhotoId) from TbHandyPhoto where handyPhotoId like ? ";
		List list=this.findByHQL(hql, nextHandyPhotoId+"%"); 
		if(list.size()>0&&list.get(0)!=null){
			String maxHandyPhotoId= list.get(0).toString();
			currentNum=CommonUtil.getCurrentNum(maxHandyPhotoId,nextHandyPhotoId);
		}
		currentNum++;
		DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        aDecimalFormat.applyPattern("0000");
        return nextHandyPhotoId+aDecimalFormat.format(currentNum);  
	}
	
	
	
	 
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getTbAddress(String HandyPhotoId) {
		String hql = "select locationX,locationY,address from TbHandyPhoto where HandyPhotoId = :HandyPhotoId";
		Query query = getSession().createQuery(hql);
		query.setParameter("HandyPhotoId", HandyPhotoId);
		ArrayList<Object[]> addr = (ArrayList<Object[]>) query.list();
		return addr;
	}



	@Override
	public void updateState(TbHandyPhoto tc) {
		getSession().update(tc);
		getSession().flush();

	}


	@Override
	public void saveprocedureMessage1(String managerName, String HandyPhotoId) {
		String hql = "update TbHandyPhoto set accepter = :managerName,handyPhotoState = '审核通过',acceptTime= :acceptTime where HandyPhotoId = :HandyPhotoId";
		Query query =getSession().createQuery(hql);
		query.setParameter("managerName", managerName)
				.setParameter("HandyPhotoId", HandyPhotoId)
				.setParameter("acceptTime", new Date()).executeUpdate();
		getSession().flush();
	}

	@Override
	public void updateAccidentStateFailed(String HandyPhotoId) {
		String hql = "update TbHandyPhoto set handyPhotoState = '待定' where HandyPhotoId =:HandyPhotoId";
		getSession().createQuery(hql).setParameter("HandyPhotoId", HandyPhotoId)
				.executeUpdate();
		getSession().flush();
	}






	@Override
	public Integer getHandyPhotoPic(String HandyPhotoId) {
		String hql = "select count(*) from TbInfoPic where processId = 'HANDYPHOTO' and recordNo = :HandyPhotoId";
		Query query = getSession().createQuery(hql);
		query.setParameter("HandyPhotoId", HandyPhotoId);
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}
	
	
	 
}