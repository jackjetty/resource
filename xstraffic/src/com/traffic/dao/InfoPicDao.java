package com.traffic.dao;
import com.traffic.dao.BaseDao;
import com.traffic.pojo.TbInfoPic;
public interface InfoPicDao extends BaseDao<TbInfoPic> {
	public int getNextPicIndex(String processId,String recordNo);
}