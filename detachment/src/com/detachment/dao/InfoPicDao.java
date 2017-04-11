package com.detachment.dao;
import com.detachment.pojo.TbInfoPic;
public interface InfoPicDao extends BaseDao<TbInfoPic> { 
	public int getNextPicIndex(String processId,String recordNo);
}