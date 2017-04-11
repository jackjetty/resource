package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.PromotionDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbPromotion;
import com.traffic.pojovo.TbPromotionVo;
import com.traffic.web.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	PromotionDao promotionDao;
	
	@Autowired
	UserDao weiUserDao;
	
	@Override
	public HashMap<String, Object> getPromotion(Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize =promotionDao.getPromotionSize();
		Integer start = (pageIndex - 1) * pageSize;
		
		HashMap<String, Integer> orgPpNumMap=new HashMap<String, Integer>();
		//orgPpNumMap.put("PT001",  6302);
		orgPpNumMap.put("PT002",  -793);
		/*orgPpNumMap.put("PT003",  4032);
		orgPpNumMap.put("PT004",  5248);
		orgPpNumMap.put("PT005",  3111);
		orgPpNumMap.put("PT006",  16756);
		orgPpNumMap.put("PT007",  21582); 
		orgPpNumMap.put("PT008",  7705); 
		orgPpNumMap.put("PT009",  2034); */ 
		ArrayList<TbPromotion> tps=promotionDao.getPromotion(start, pageSize);
		ArrayList<TbPromotionVo> tpsVo=new ArrayList<TbPromotionVo>();
		
		int num=0;
		int allNum=0;
		int orgPpNum=0;
		for(TbPromotion tp:tps){
//			if(tp.getSceneId()==8){
//				num=weiUserDao.findCount("select count(*) from TbUser where(sceneId =?) or (sceneId >=?)", tp.getSceneId(),100 );
//			}
//			else{
//				num=weiUserDao.findCount("select count(*) from TbUser where sceneId = ?", tp.getSceneId() );
//			}
			num=weiUserDao.findCount("select count(*) from TbWeiUser where sceneId = ?", tp.getSceneId() );
			
			TbPromotionVo tpv=new TbPromotionVo(tp,num);
			
			if(orgPpNumMap.get(tp.getPromotionId())==null){
				orgPpNum=0;
			}
			else{
				orgPpNum=orgPpNumMap.get(tp.getPromotionId());
			}
			tpv.setOrgPpNum(orgPpNum);
			tpv.setPeopleNum(tpv.getPeopleNum()-orgPpNum); 
			
			allNum+=tpv.getPeopleNum();
			tpsVo.add(tpv);
		}
		TbPromotionVo tpvv=new TbPromotionVo();
		tpvv.setPeopleNum(allNum);
		tpvv.setPromotionName("总计");
		tpvv.setSceneId(100);
		tpsVo.add(tpvv);
		Collections.sort(tpsVo, new Comparator<TbPromotionVo>() {
            public int compare(TbPromotionVo arg0, TbPromotionVo arg1) {
                return arg0.getSceneId().compareTo(arg1.getSceneId());
            }
        });
		resultMap.put("listSize", listSize);
		resultMap.put("rows",tpsVo);
		return resultMap;
	}

}
