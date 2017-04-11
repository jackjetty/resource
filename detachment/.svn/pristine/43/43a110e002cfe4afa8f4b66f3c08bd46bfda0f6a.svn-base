package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.DepartmentDao;
import com.detachment.dao.PlaceAreaDao;
import com.detachment.dao.PlaceDao;
import com.detachment.pojo.TbPlace;
import com.detachment.pojo.TbPlaceArea;
import com.detachment.util.GpsToBaiDu;
import com.detachment.util.PlaceCheck;
import com.detachment.web.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

	Log log = LogFactory.getLog(PlaceServiceImpl.class);

	@Autowired
	PlaceDao placeDao;

	@Autowired
	PlaceAreaDao placeAreaDao;

	@Autowired
	DepartmentDao departmentDao;

	@Override
	public HashMap<String, Object> getPlace(String placeName,
			Integer pageIndex, Integer pageSize) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = placeDao.getPlaceListSize(placeName);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbPlace> atp = placeDao.getPlace(placeName, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", atp);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addPlace(TbPlace p) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			placeDao.save(p);
			map.put("respCode", 0);
			map.put("respInfo", "地区信息添加成功！");
		} catch (Exception e) {
			log.error(e);
			map.put("respCode", -1);
			map.put("respInfo", "地区信息添加失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> updatePlace(TbPlace p) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			placeDao.update(p);
			map.put("respCode", 0);
			map.put("respInfo", "地区信息更新成功！");
		} catch (Exception e) {
			log.error(e);
			map.put("respCode", -1);
			map.put("respInfo", "地区信息更新失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> removePlace(String placeIds) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (placeIds == null || "".equals(placeIds)) {
			map.put("respCode", -1);
			map.put("respInfo", "没有正确传递需要删除地区信息的id");
		} else {
			String[] args = placeIds.split(",");
			try {
				placeDao.deleteByIds(args);
				map.put("respCode", 0);
				map.put("respInfo", "地区信息删除成功！");
			} catch (Exception e) {
				log.error(e);
				map.put("respCode", -1);
				map.put("respInfo", "地区信息删除失败！");
			}
		}

		return map;
	}

	@Override
	public HashMap<String, Object> addPlaceAreadDetail(
			ArrayList<TbPlaceArea> atpa) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer placeId = atpa.get(0).getPlaceId();
		Integer number = placeAreaDao.getNumberByPlaceId(placeId);
		if (number != null && number > 0) {
			placeAreaDao.deleteByPlaceId(placeId);
		}
		try {
			for (TbPlaceArea tbPlaceArea : atpa) {
				placeAreaDao.save(tbPlaceArea);
			}
			map.put("respCode", 0);
			map.put("respInfo", "地区信息更新成功！");
		} catch (Exception e) {
			log.error(e);
			map.put("respCode", -1);
			map.put("respInfo", "地区信息更新失败！");
		}
		return map;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getPlaceMap() {
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
		ArrayList<TbPlace> atp = placeDao.getPlace();
		HashMap<String, Object> map = null;
		for (TbPlace tbPlace : atp) {
			map = new HashMap<String, Object>();
			map.put("value", tbPlace.getPlaceId());
			map.put("text", tbPlace.getPlaceName());
			ahso.add(map);
		}
		return ahso;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String checkTheAccidentPlace(Double longitude, Double latitude) {
		String info = GpsToBaiDu.ToBaiduAddress(longitude, latitude);
		
		longitude = Double.parseDouble(info.split("==")[0]);
		latitude = Double.parseDouble(info.split("==")[1]);
		HashMap<String, Object> areaData = (HashMap<String, Object>) ServletActionContext.getServletContext()
				.getAttribute("AreaData");
		if (areaData == null) {
			areaData = placeAreaDao.getAreaData();
			ServletActionContext.getServletContext().setAttribute("AreaData", areaData);
		}
		Set<String> keys = areaData.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			HashMap<String, Object> tempMap = (HashMap<String, Object>) areaData
					.get(key);
			ArrayList<Double> latitudes = (ArrayList<Double>) tempMap
					.get("latitude");
			ArrayList<Double> longitudes = (ArrayList<Double>) tempMap
					.get("longitude");
			if (PlaceCheck.isPointInPolygon(longitude, latitude, longitudes,
					latitudes)) {
				String departmentId = departmentDao.getDepartmentIdByRegion(key);
				return departmentId;
			} else {
				continue;
			}
		}
		return null;
	}

	@Override
	public HashMap<String, Object> getPlaceMapTest() {
		return	placeAreaDao.getAreaDataTest();
	}

	@Override
	public void setPlaceMapClick(TbPlaceArea tba) {
		 placeAreaDao.setAreaClick(tba);
	}

}
