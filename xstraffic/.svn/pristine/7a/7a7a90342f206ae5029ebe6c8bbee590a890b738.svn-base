package com.traffic.dao.impl;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 
import org.springframework.stereotype.Repository;  

 
import com.traffic.dao.HandyPhotoDao;
import com.traffic.pojo.TbHandyPhoto;
import com.traffic.util.CommonUtil;  
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
	
	 
}