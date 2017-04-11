package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.PromotionDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.dto.PromotionDto;
import com.detachment.pojo.TbPromotion;
import com.detachment.util.CommonUtil;
import com.detachment.web.service.PromotionService; 

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDao promotionDao;

	@Autowired
	WeiUserDao weiUserDao;

	@Override
	public HashMap<String, Object> getPromotion(Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		
		String hql="from TbPromotion where valid=? order by sceneId";
		List<TbPromotion> tbPromotionList=promotionDao.findByHQL(hql, true);
		int sceneId=0;
		
		Calendar cal = Calendar.getInstance();
		int dayNum=cal.get(Calendar.DAY_OF_MONTH);
		Calendar orgCal,endCal;
		  
		String orgDateForm,endDateForm;
		int monOffset=0;
        if(dayNum<=2){
        	monOffset=-1;
		}
		
        orgCal = Calendar.getInstance();   
        orgCal.add(Calendar.MONTH, monOffset);
        orgCal.set(Calendar.DAY_OF_MONTH,1);
        orgCal.set(Calendar.HOUR_OF_DAY,0);
        orgCal.set(Calendar.MINUTE,0);
        orgCal.set(Calendar.SECOND,0);
        orgDateForm=CommonUtil.doDateForm( orgCal.getTime())  ;
        
        endCal= Calendar.getInstance();   
        endCal.add(Calendar.MONTH, monOffset);
        endCal.set(Calendar.DAY_OF_MONTH,endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCal.set(Calendar.HOUR_OF_DAY,0);
        endCal.set(Calendar.MINUTE,0);
        endCal.set(Calendar.SECOND,0);
        endDateForm=CommonUtil.doDateForm( endCal.getTime())  ;
        
        int orgNum=0,endNum=0,sumNum=0;
        
        List<PromotionDto> promotionDtoList = new ArrayList<PromotionDto>();
        PromotionDto promotionDto;
		for(TbPromotion tbPromotion:tbPromotionList){
			sceneId=tbPromotion.getSceneId();
			promotionDto=new PromotionDto(tbPromotion,0);
			
			if ( sceneId == 8) {
				orgNum = weiUserDao
						.findCount(
								"select count(*) from TbWeiUser where ((sceneId =?) or (sceneId >=?)) and convert(varchar(100), createTime,23)<? ",
								sceneId, 100,orgDateForm);
			} else {
				orgNum = weiUserDao.findCount(
						"select count(*) from TbWeiUser where (sceneId = ?) and convert(varchar(100), createTime,23)<? ",
						sceneId,orgDateForm);
			}
			promotionDto.setOrgPpNum(orgNum);
			
			if ( sceneId == 8) {
				endNum = weiUserDao
						.findCount(
								"select count(*) from TbWeiUser where ((sceneId =?) or (sceneId >=?)) and convert(varchar(100), createTime,23)<=? ",
								sceneId, 100,endDateForm);
			} else {
				endNum = weiUserDao.findCount(
						"select count(*) from TbWeiUser where (sceneId = ?) and convert(varchar(100), createTime,23)<=? ",
						sceneId,endDateForm);
			}
			promotionDto.setPeopleNum(endNum-orgNum);
			sumNum+=endNum-orgNum;
			promotionDtoList.add(promotionDto);
			
		}
		
		promotionDto = new PromotionDto();
		promotionDto.setPeopleNum(sumNum);
		promotionDto.setPromotionName("总计");
		promotionDto.setSceneId(100);
		promotionDtoList.add(promotionDto);
		resultMap.put("listSize", promotionDtoList.size());
		resultMap.put("rows", promotionDtoList);
		return resultMap;
		
		
		
		
		
		/*
		
		Integer listSize = promotionDao.getPromotionSize();
		Integer start = (pageIndex - 1) * pageSize;

		HashMap<String, Integer> orgPpNumMap = new HashMap<String, Integer>();
		orgPpNumMap.put("PT001", Integer.valueOf(20087));
	    orgPpNumMap.put("PT002", Integer.valueOf(5147));
	    orgPpNumMap.put("PT003", Integer.valueOf(4842));
	    orgPpNumMap.put("PT004", Integer.valueOf(8437));
	    orgPpNumMap.put("PT005", Integer.valueOf(4219));
	    orgPpNumMap.put("PT006", Integer.valueOf(26890));
	    orgPpNumMap.put("PT007", Integer.valueOf(25771));
	    orgPpNumMap.put("PT008", Integer.valueOf(13430));
	    orgPpNumMap.put("PT009", Integer.valueOf(6668));
	    orgPpNumMap.put("PT010", Integer.valueOf(170));
	    orgPpNumMap.put("PT011", Integer.valueOf(107));

	    orgPpNumMap.put("DP001", Integer.valueOf(552));
	    orgPpNumMap.put("DP002", Integer.valueOf(642));
	    orgPpNumMap.put("DP003", Integer.valueOf(292));
	    orgPpNumMap.put("DP004", Integer.valueOf(3251));
	    orgPpNumMap.put("DP005", Integer.valueOf(651));
	    orgPpNumMap.put("DP006", Integer.valueOf(2228));
	    orgPpNumMap.put("DP007", Integer.valueOf(557));
	    orgPpNumMap.put("DP008", Integer.valueOf(636));

	    orgPpNumMap.put("SP001", Integer.valueOf(1437));
	    orgPpNumMap.put("SP002", Integer.valueOf(2134));
	    orgPpNumMap.put("SP003", Integer.valueOf(4641));
	    orgPpNumMap.put("SP004", Integer.valueOf(732));

	    orgPpNumMap.put("PJ002_1", Integer.valueOf(361));
	    orgPpNumMap.put("PJ002_2", Integer.valueOf(72));
	    orgPpNumMap.put("PJ002_3", Integer.valueOf(129));
	    orgPpNumMap.put("PJ002_4", Integer.valueOf(64));
	    orgPpNumMap.put("PJ002_5", Integer.valueOf(57));
	    orgPpNumMap.put("PJ002_6", Integer.valueOf(36));
	    orgPpNumMap.put("PJ002_7", Integer.valueOf(4));
	    orgPpNumMap.put("PJ002_8", Integer.valueOf(0));
	    orgPpNumMap.put("PJ002_9", Integer.valueOf(0));
	    orgPpNumMap.put("PJ002_10", Integer.valueOf(6));
	    orgPpNumMap.put("PJ002_11", Integer.valueOf(0));
	    orgPpNumMap.put("PJ002_12", Integer.valueOf(12));
	    orgPpNumMap.put("PJ002_13", Integer.valueOf(0));
	    orgPpNumMap.put("PJ002_14", Integer.valueOf(290));
	    orgPpNumMap.put("PJ002_15", Integer.valueOf(13));

	    orgPpNumMap.put("PT003_1", Integer.valueOf(810));
	    orgPpNumMap.put("PT003_2", Integer.valueOf(149));
	    orgPpNumMap.put("PT003_3", Integer.valueOf(484));
	    orgPpNumMap.put("PT003_4", Integer.valueOf(114));

	    orgPpNumMap.put("PT001_1", Integer.valueOf(2058));
	    orgPpNumMap.put("PT001_2", Integer.valueOf(840));
	    orgPpNumMap.put("PT001_3", Integer.valueOf(743));
	    orgPpNumMap.put("PT001_4", Integer.valueOf(1598));
	    orgPpNumMap.put("PT001_5", Integer.valueOf(1444));
	    orgPpNumMap.put("PT001_6", Integer.valueOf(1766));

	    orgPpNumMap.put("GX001", Integer.valueOf(435));
	    orgPpNumMap.put("GX002", Integer.valueOf(4));
	    orgPpNumMap.put("GX003", Integer.valueOf(30));
	    orgPpNumMap.put("GX004", Integer.valueOf(22));
	    orgPpNumMap.put("GX005", Integer.valueOf(16));
	    orgPpNumMap.put("GX006", Integer.valueOf(0));
		
		

		ArrayList<TbPromotion> tps = promotionDao.getPromotion(start, pageSize);
		ArrayList<PromotionDto> tpsVo = new ArrayList<PromotionDto>();

		int num = 0;
		int allNum = 0;
		int orgPpNum = 0;
		for (TbPromotion tp : tps) {
			if (tp.getSceneId() == 8) {
				num = weiUserDao
						.findCount(
								"select count(*) from TbWeiUser where(sceneId =?) or (sceneId >=?)",
								tp.getSceneId(), 100);
			} else {
				num = weiUserDao.findCount(
						"select count(*) from TbWeiUser where sceneId = ?",
						tp.getSceneId());
			}

			PromotionDto tpv = new PromotionDto(tp, num);

			if (orgPpNumMap.get(tp.getPromotionId()) == null) {
				orgPpNum = 0;
			} else {
				orgPpNum = orgPpNumMap.get(tp.getPromotionId());
			}
			tpv.setOrgPpNum(orgPpNum);
			tpv.setPeopleNum(tpv.getPeopleNum() - orgPpNum);

			allNum += tpv.getPeopleNum();
			tpsVo.add(tpv);
		}*/
		
		/*Collections.sort(tpsVo, new Comparator<PromotionDto>() {
			public int compare(PromotionDto arg0, PromotionDto arg1) {
				return arg0.getSceneId().compareTo(arg1.getSceneId());
			}
		});*/
		
	}

}
