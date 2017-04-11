package com.traffic.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.PublicInfoDao;
import com.traffic.pojo.TbPublicInfo;
import com.traffic.web.service.PublicInfoService;

@Service("publicInfoService")
public class PublicInfoServiceImpl implements PublicInfoService{
	Log log = LogFactory.getLog(PublicInfoServiceImpl.class);
	
	@Autowired
	PublicInfoDao publicInfoDao;
	
	@Override
	public HashMap<String, Object> getPublicInfo(String startTime, String endTime,
			String publicType, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime.toString() + " 00:00:00");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime.toString() + " 23:59:59");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if("".equals(publicType)){
			publicType=null;
		}
		Integer listSize = publicInfoDao.getPublicInfoSize(startDate, endDate,publicType);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbPublicInfo> ta=publicInfoDao.getPublicInfo(startDate, endDate, publicType, start, pageSize);
		for(TbPublicInfo pb : ta){
			String content=pb.getPublicInfo().replace("<", "&lt;");
			content=content.replace(">", "&gt;");
			pb.setPublicInfo(content);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ta);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public Integer getPublicInfoSize(String startTime, String endTime, String publicType) {
		Date startDate = null;
		Date endDate = null;
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime.toString() + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime.toString() + " 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(publicType)){
			publicType=null;
		}
		Integer listSize =publicInfoDao.getPublicInfoSize(startDate, endDate, publicType);
		return listSize;
	}

	@Override
	public HashMap<String, Object> addPublicInfo(TbPublicInfo pb) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			publicInfoDao.addPublicInfo(pb);
			resultMap.put("respInfo", "公告添加成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("添加公告时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加公告时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updatePublicInfo(TbPublicInfo pb) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			publicInfoDao.updatePublicInfo(pb);
			resultMap.put("respInfo", "修改公告信息成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("修改公告信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "修改公告信息时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> removePublicInfo(String ids) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		int lastId=0;
		try {
			for(String id : idArray){
				if(!"".equals(id)){
					lastId = Integer.parseInt(id);
					publicInfoDao.deleteById(lastId);
					resultMap.put("respInfo", "删除用户信息成功!");
				}
			}
			
		} catch (Exception e) {
			log.error("删除id为"+lastId+"的公告信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除公告信息时出现异常!");
		}
		return resultMap;
	}

	@Override
	public TbPublicInfo getPublicInfoById(int id) {
		
		return publicInfoDao.getPublicInfoById(id);
	}

	@Override
	public HashMap<String, Object> getCodeInfo(int id) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbPublicInfo pb=publicInfoDao.getPublicInfoById(id);
		resultMap.put("title", pb.getTitle());
		resultMap.put("time", new SimpleDateFormat("yyyy-MM-dd").format(pb.getPublicTime()));
		resultMap.put("content", replaceBlank(pb.getPublicInfo()));
		return resultMap;
	}
	
	public static String replaceBlank(String str) {
		        String dest = "";
		        if (str!=null) {
		            Pattern p = Pattern.compile("\t|\r|\n");
		            Matcher m = p.matcher(str);
		            dest = m.replaceAll("");
		        }
		        return dest;
	}

	@Override
	public HashMap<String, Object> publicInfoStatus(int id) {
		TbPublicInfo pb=publicInfoDao.getPublicInfoById(id);
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		try{
			if(pb==null){
				resultMap.put("respCode", -1);
				resultMap.put("respInfo","此公告已被删除,请刷新后再操作");
			}else if("启用".equals(pb.getStatus())){
				pb.setStatus("禁用");
				publicInfoDao.updatePublicInfo(pb);
				resultMap.put("respInfo", "禁用公告信息成功！");
				resultMap.put("respCode", 0);
			}else{
				pb.setStatus("启用");
				publicInfoDao.updatePublicInfo(pb);
				resultMap.put("respInfo", "启用公告信息成功！");
				resultMap.put("respCode", 0);
			}
		} catch (Exception e) {
			log.error("修改公告状态时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -2);
			resultMap.put("respInfo", "修改公告状态时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> publicInfoUpOrDown(int thisId,int thisIndex,int nextId,int nextIndex) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		try{
			TbPublicInfo pb=publicInfoDao.getPublicInfoById(thisId);
			TbPublicInfo nextPb=publicInfoDao.getPublicInfoById(nextId);
			if(pb!=null && nextPb!=null){
				pb.setPublicIndex(nextIndex);
				nextPb.setPublicIndex(thisIndex);
				publicInfoDao.updatePublicInfo(pb);
				publicInfoDao.updatePublicInfo(nextPb);
				resultMap.put("respInfo", "移动成功");
				resultMap.put("respCode", 0);
			}else{
				resultMap.put("respInfo", "移动不成功，请稍后再试");
				resultMap.put("respCode", 1);
			}
		}catch (Exception e) {
			log.error("移动信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -2);
			resultMap.put("respInfo", "移动信息时出现异常");
		}
		
		
		return resultMap;
	}

	@Override
	public void toSetPublicIndex(TbPublicInfo pb) {
		publicInfoDao.updatePublicInfo(pb);
		
	}

	@Override
	public ArrayList<TbPublicInfo> getAllCodeJsp(String publicType,
			Integer pageSize, Integer pageIndex) {
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbPublicInfo> ta=publicInfoDao.getAllCodeJsp(publicType, start, pageSize);

		return ta;
	}

	@Override
	public Integer getAllCodeJspSize(String publicType) {
		Integer listSize =publicInfoDao.getAllCodeJspSize(publicType);
		return listSize;
	}

	@Override
	public HashMap<String, Object> getPublicInfoWB(Integer pageSize,
			Integer pageIndex) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		Integer listSize = publicInfoDao.findCount("select count(*) from TbPublicInfo where publicType = 'WB'");
		Integer start = (pageIndex - 1) * pageSize;
		//order by publicTime desc
		ArrayList<TbPublicInfo> ta=(ArrayList<TbPublicInfo>) publicInfoDao.findPage("from TbPublicInfo where publicType = 'WB' order by publicTime desc", start, pageSize);
		for(TbPublicInfo pb : ta){
			String content=pb.getPublicInfo().replaceAll("width=\"200\"","class=\"contentPic\"")
					.replaceAll("<img alt=\"\" src=\"/xstraffic","<br /><img alt=\"\" src=\"/xstraffic")
					.replaceAll("<img class=\"contentPic\"","<br /><img class=\"contentPic\"")
					.replaceAll("<img src=\"/xstraffic","<br /><img src=\"/xstraffic");
			pb.setPublicInfo(content);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ta);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	
	

}
