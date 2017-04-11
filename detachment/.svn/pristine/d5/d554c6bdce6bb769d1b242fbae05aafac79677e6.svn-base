package com.detachment.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.detachment.dao.PlaceAreaDao;
import com.detachment.math.PointMath;
import com.detachment.pojo.TbPlaceArea;
import com.detachment.util.CommonUtil;

@Repository
public class PlaceAreaDaoImpl extends BaseDaoImpl<TbPlaceArea> implements PlaceAreaDao {


	@Override
	public Integer getNumberByPlaceId(Integer placeId) {
		String hql = "select count(*) from TbPlaceArea where placeId = :placeId";
		Number n = (Number) getSession().createQuery(hql).setParameter("placeId",placeId).list().get(0);
		return n.intValue();
	}

	@Override
	public void deleteByPlaceId(Integer placeId) {
		String hql = "delete from TbPlaceArea where placeId = :placeId";
		getSession().createQuery(hql).setParameter("placeId",placeId).executeUpdate();
		getSession().flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,Object> getAreaData() {
		String hql = "select a.placeName as placeName,b.latitude as latitude,b.longitude as longitude from TbPlace a,TbPlaceArea b where a.placeId = b.placeId order by b.infoId asc";
		ArrayList<HashMap<String,String>> ap = (ArrayList<HashMap<String, String>>) getSession().createQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		String lastRecord = "";
		HashMap<String,Object>resultMap = new HashMap<String, Object>();
		HashMap<String,Object> tempMap = null;
		HashMap<String,String> map = null;
		ArrayList<Double> latitudes = null;
		ArrayList<Double> longitudes = null;
		while (ap!=null && ap.size()>0) {
			lastRecord = ap.get(0).get("placeName");
			latitudes = new ArrayList<Double>();
			longitudes = new ArrayList<Double>();
			for(int i=0;i<ap.size();){
				map = ap.get(i);
				if(lastRecord.equals(map.get("placeName"))){
					latitudes.add(Double.parseDouble(map.get("latitude")));
					longitudes.add(Double.parseDouble(map.get("longitude")));
					ap.remove(i);
					if(ap.size()==0){
						tempMap = new HashMap<String,Object>();
						tempMap.put("latitude", latitudes);
						tempMap.put("longitude", longitudes);
						resultMap.put(lastRecord, tempMap);
					}
				}else{
					tempMap = new HashMap<String, Object>();
					tempMap.put("latitude", latitudes);
					tempMap.put("longitude", longitudes);
					resultMap.put(lastRecord, tempMap);
					break;
				}
				
			}
		}
		
		return resultMap;
	}
	
    public List <PointMath>  getPointLine (int placeId ){
    	String hql=" from TbPlaceArea where placeId=?     order by  infoId asc   "; 
    	List <TbPlaceArea> list= this.findByHQL(hql, placeId); 
    	if(list==null||list.size()==0){
    		return null;
    	}
    	List<PointMath> pointLine = new ArrayList<PointMath>();
    	PointMath pointMath;
    	for(TbPlaceArea tbPlaceArea:list){
    		if(!CommonUtil.isNumeric(CommonUtil.trim(tbPlaceArea.getLatitude())))
    			 continue;
    		if(!CommonUtil.isNumeric(CommonUtil.trim(tbPlaceArea.getLongitude())))
   			     continue;
    		pointMath=new PointMath();
    		pointMath.setPointX(new Float(CommonUtil.trim(tbPlaceArea.getLatitude())).floatValue());
    		pointMath.setPointY(new Float(CommonUtil.trim(tbPlaceArea.getLongitude())).floatValue());
    		pointLine.add(pointMath);
    		
    	} 
    	return pointLine; 
    	
    }
	 
	@Override
	public HashMap<String, Object> getAreaDataTest() {
		HashMap<String,Object> hm=new HashMap<String, Object>();
		String hql = "from TbPlaceArea";
		ArrayList<TbPlaceArea> awu = (ArrayList<TbPlaceArea>)getSession().createQuery(hql).list();
		hm.put("list", awu);
		return hm;
	}


	@Override
	public void setAreaClick(TbPlaceArea tba) {
		getSession().save(tba);
		getSession().flush();
	}
	
	 

}
