package com.detachment.dao;
import java.util.ArrayList;
import java.util.Date;
import com.detachment.pojo.TbHandyPhoto; 
public interface HandyPhotoDao extends BaseDao<TbHandyPhoto> {
	public String  getNextHandyPhotoId (String prefix); 
	 

	public ArrayList<Object[]> getTbAddress(String HandyPhotoId);

	public void updateState(TbHandyPhoto tc);
	
	public void saveprocedureMessage1(String managerName, String HandyPhotoId);
	
	public void updateAccidentStateFailed(String HandyPhotoId);
	
	public Integer getHandyPhotoPic(String HandyPhotoId);
}
 