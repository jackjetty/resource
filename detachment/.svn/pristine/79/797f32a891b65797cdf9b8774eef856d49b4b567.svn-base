package com.detachment.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.InfoTextDao;
import com.detachment.pojo.TbInfoText;
@Repository("infoTextDao")
public class InfoTextDaoImpl    extends BaseDaoImpl<TbInfoText> implements  InfoTextDao{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbInfoText> getTextInfoByFormlAccidentId(String accidentId) {
		String hql = "from TbInfoText where id.recordNo = :recordNo order by textTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("recordNo", accidentId);
		ArrayList<TbInfoText> m = (ArrayList<TbInfoText>) query
				.list();
		return m;
	}
	public int getNextTextIndex(String processId,String recordNo){
		//processId,recordNo,textIndex,textTime,textMessage
		String hql="select max(id.textIndex) from TbInfoText where id.processId=? and id.recordNo=?";
		List list=this.findByHQL(hql, processId,recordNo);
		if(list==null||list.size()==0)
			 return 1;
		if(list.get(0)==null)
			 return 1; 
		return ((Number) list.get(0)).intValue()+1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getTextInfoByHandyPhotoId(String accidentId) {
		String hql = "from TbInfoText where id.recordNo = :recordNo order by textTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("recordNo", accidentId);
		ArrayList<TbInfoText> mm = (ArrayList<TbInfoText>) query
				.list();
		String text="";
		for(TbInfoText m:mm){
			text+=","+m.getTextMessage();
		}
		if(!"".equals(text)){
			text=text.substring(1, text.length());
		}
		return text;
	}
	 
}