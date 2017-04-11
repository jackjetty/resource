package com.rising.management.service.impl;
import java.util.*; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Faq; 
import com.rising.management.dao.FaqDao; 
import com.rising.management.service.FaqService; 
import com.rising.management.vo.FaqVo;
import com.rising.management.common.RequestUtil;  
@Service("faqService")
public class FaqServiceImpl implements FaqService {
Log log = LogFactory.getLog(FaqServiceImpl.class);
	
	@Autowired  FaqDao faqDao;

	@Override
	public HashMap<String, Object> getFaqByPage(Integer pageIndex,
			Integer pageSize) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = faqDao.getFaqListSize();
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<FaqVo> aav = new ArrayList<FaqVo>();
		List<Faq> am = faqDao
				.getFaq(start, pageSize);
		for (Faq faq : am) {
			aav.add(new FaqVo(faq));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", aav);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> getPageDate(Integer pageIndex, Integer pageSize,String order,String sort, Faq cdtFaq) { 
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		order = RequestUtil.doInit(order);
        sort = RequestUtil.doInit(sort);
        String StrOrder="";
        if(order.length()>0 && sort.length()>0){
            StrOrder=" order by "+sort+" "+order;
        } 
        Map<String, Object> cdtMap= new HashMap<String, Object>();
        String cdtTitle=RequestUtil.doInit(cdtFaq.getTitle());
        String cdtContent=RequestUtil.doInit(cdtFaq.getContent());
        String cdtShow=RequestUtil.doInit(cdtFaq.getShow());
        String StrCdt=""; 
        if(!cdtTitle.equalsIgnoreCase("")){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        	StrCdt+=" (title like:title) ";
        	cdtMap.put("title", "%"+cdtTitle+"%");
        }
        if(!cdtContent.equalsIgnoreCase("")){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        	StrCdt+=" (content like:content) ";
        	cdtMap.put("content", "%"+cdtContent+"%");
        }
        if(!cdtShow.equalsIgnoreCase("")){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        	StrCdt+=" (show =:show) ";
        	cdtMap.put("show",  cdtShow );
        }
        String hql=" select count(*)    from Faq ";
        hql+=StrCdt;
        Integer total = faqDao.findCount(hql, cdtMap);
        
        hql=" from Faq";
        hql+=StrCdt;
        hql+=StrOrder;
        List<Object> list;
        list=faqDao.findPage(hql,(pageIndex - 1) * pageSize, pageSize,cdtMap); 
        ArrayList<FaqVo> faqVoList = new ArrayList<FaqVo>(); 
        Faq faq; 
		for (Object object : list) {
			faq=(Faq)object;
			faqVoList.add(new FaqVo(faq));
		}
		resultMap.put("total", total);
		resultMap.put("rows", faqVoList);
		return resultMap;
	}
	public HashMap<String, Object> replaceFaqRand(Integer curRowId,Integer repRowId){
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		if(curRowId==null||curRowId==0||repRowId==null||repRowId==0){
			resultMap.put("success", false);
			resultMap.put("info", "数据未完善！！！");
			return resultMap;
		}
		Faq curFaq=faqDao.findById(curRowId);
		Faq repFaq=faqDao.findById(repRowId);
		if(curFaq==null||repFaq==null){
			resultMap.put("success", false);
			resultMap.put("info", "记录未存在！！");
			return resultMap;
		}
		String midRank,curRank,repRank;
		curRank=RequestUtil.doInit(curFaq.getRank());
		repRank=RequestUtil.doInit(repFaq.getRank());
		midRank=curRank;
		curFaq.setRank(repRank);
		repFaq.setRank(midRank);
		faqDao.updateFaq(curFaq);
		faqDao.updateFaq(repFaq);
		resultMap.put("success", true);
		resultMap.put("info",  "操作成功！！");
		return resultMap; 
	}
	public HashMap<String, Object> getFaqById(Integer id){
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		if(id==null||id==0){
			resultMap.put("success", false);
			resultMap.put("info", "数据未完善！！！");
			return resultMap;
		}
		Faq faq=faqDao.findById(id);
		if(faq==null){
			resultMap.put("success", false);
			resultMap.put("info", "记录未存在！！");
			return resultMap;
		}
		resultMap.put("success", true);
		resultMap.put("faq",  new FaqVo(faq));
		return resultMap;
	}
	public HashMap<String, Object> updateFaq(Faq faq){
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		Integer id=faq.getId();
		Faq objFaq=null; 
		if(id!=null && id==0){
			id=null; 
		}    
		if(id!=null){
			objFaq=faqDao.findById(id);
		} 
		if(objFaq==null){
			objFaq= new Faq();
			this.rankFaq();
			objFaq.setRank("99999");
		}
		objFaq.setContent(RequestUtil.doInit(faq.getContent())); 
		objFaq.setRemark(RequestUtil.doInit(faq.getRemark()));
		objFaq.setShow(RequestUtil.doInit(faq.getShow()).equals("显示")?"显示":"隐藏");
		objFaq.setTitle(RequestUtil.doInit(faq.getTitle()));
		objFaq.setType(RequestUtil.doInit(faq.getType()));
		faqDao.updateFaq(objFaq);
		resultMap.put("success", true);
		resultMap.put("info",  "保存成功！！");
		return resultMap;
	}
	private void rankFaq(){
		String hql="  from Faq order by rank asc ";
		Map<String, Object> cdtMap= new HashMap<String, Object>();
		List<Object> list;
		list=faqDao.findByHQL(hql, cdtMap);
		Faq faq; 
		long i=0;
		for (Object object : list) { 
			faq=(Faq)object;
			i++;
			faq.setRank(RequestUtil.CastString(i, "00000"));
			faqDao.updateFaq(faq);
		}
		return;
		
	}
}