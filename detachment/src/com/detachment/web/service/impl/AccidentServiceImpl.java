package com.detachment.web.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.detachment.dao.DepartmentDao;
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.InfoTextDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.TipDao;
import com.detachment.dao.UserDao;
import com.detachment.json.AccidentBean;
import com.detachment.json.RespObject;
import com.detachment.pojo.TbFormalAccident;
import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.TbInfoPicId;
import com.detachment.pojo.TbInfoText;
import com.detachment.pojo.TbTip;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbAccidentJsp;
import com.detachment.pojo.vo.TbAccidentStatisticsVo;
import com.detachment.pojo.vo.TbFormalAccidentVo;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.GpsToBaiDu;
import com.detachment.web.service.AccidentService;
import com.detachment.wei.service.AttributionService;
import com.detachment.wei.thread.FileThread;
import org.apache.axis.description.OperationDesc;

@Service
public class AccidentServiceImpl implements AccidentService {

	@Autowired
	FormalAccidentDao formalAccidentDao;

	@Autowired
	InfoTextDao infoTextDao;

	@Autowired
	UserDao userDao;

	@Autowired
	DepartmentDao departmentDao;
	
	@Value("${root.dir}") 
	protected String  ROOT_DIR; 
	
	@Autowired
	private AttributionService attributionService;
	
	@Autowired
	private InfoPicDao infoPicDao;
	
	@Autowired
	ProcessDao processDao;

	@Autowired
	private TipDao tipDao;

	@Override
	public HashMap<String, Object> getAccident(String startTime,
			String endTime, String accidentState,String reportPhoneNumber, String nickName,
			Integer pageSize, Integer pageIndex, String emergencyCall,
			String departmentId) {
		ArrayList<TbFormalAccidentVo> atav = new ArrayList<TbFormalAccidentVo>();
		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
				.getAttribute("managerUser");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(accidentState)) {
			accidentState = null;
		}
		if ("".equals(reportPhoneNumber)) {
			reportPhoneNumber = null;
		}
		if ("".equals(nickName)) {
			nickName = null;
		}
		if ("".equals(emergencyCall)) {
			emergencyCall = null;
		}
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime.toString() + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					.toString() + " 23:59:59");
		} catch (ParseException e) {

		}
		Integer listSize = null;
		ArrayList<TbFormalAccident> ta = null;
		Integer start = (pageIndex - 1) * pageSize;
		u = userDao.findById(u.getUserId());
		String parentId = u.getTbDepartment().getParentId();
		HashMap<String, String> map = departmentDao.getDepartmentMap();
		if (parentId == null || "".equals(parentId)) {
			if (departmentId.equals(u.getTbDepartment().getDepartmentId())) {
				listSize = formalAccidentDao.getAccidentListSize(startDate,
						endDate,accidentState, reportPhoneNumber, nickName, emergencyCall,
						null);
				ta = formalAccidentDao.getAccident(startDate, endDate,accidentState,
						reportPhoneNumber, nickName, start, pageSize,
						emergencyCall, null);
			} else {
				listSize = formalAccidentDao.getAccidentListSize(startDate,
						endDate,accidentState, reportPhoneNumber, nickName, emergencyCall,
						departmentId);
				ta = formalAccidentDao.getAccident(startDate, endDate,accidentState,
						reportPhoneNumber, nickName, start, pageSize,
						emergencyCall, departmentId);
			}

		} else {
			listSize = formalAccidentDao.getAccidentListSize(startDate,
					endDate, accidentState,reportPhoneNumber, nickName, emergencyCall, u
							.getTbDepartment().getDepartmentId());
			ta = formalAccidentDao.getAccident(startDate, endDate,accidentState,
					reportPhoneNumber, nickName, start, pageSize, emergencyCall,
					u.getTbDepartment().getDepartmentId());
		}
		for (TbFormalAccident tbFormalAccident : ta) {
			TbFormalAccidentVo vo = new TbFormalAccidentVo();
			vo.setAccepter(tbFormalAccident.getAccepter());
			vo.setAccidentState(tbFormalAccident.getAccidentState());
			vo.setAddress(tbFormalAccident.getAddress());
			vo.setDepartment(map.get(tbFormalAccident.getDepartmentId()));
			vo.setDepartmentId(tbFormalAccident.getDepartmentId());
			vo.setFormlAccidentId(tbFormalAccident.getFormlAccidentId());
			vo.setNickname(tbFormalAccident.getNickname());
			vo.setRealName(tbFormalAccident.getRealName());
			vo.setReporterType(tbFormalAccident.getReporterType());
			vo.setReportTimeString(tbFormalAccident.getReportTimeString());
			vo.setReportPhoneNumber(tbFormalAccident.getReportPhoneNumber());
			vo.setEmergencyCall(tbFormalAccident.getEmergencyCall());
			atav.add(vo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", atav);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public ArrayList<TbInfoText> getTbAccidentText(String accidentId) {
		ArrayList<TbInfoText> ta = infoTextDao
				.getTextInfoByFormlAccidentId(accidentId);
		return ta;
	}

	@Override
	public ArrayList<TbAccidentJsp> getAccidentJsp(String openId,
			Integer pageSize, Integer pageIndex) {
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbFormalAccident> ta = formalAccidentDao.getAccidentJsp(
				openId, start, pageSize);
		if (ta != null) {
			ArrayList<TbAccidentJsp> taj = new ArrayList<TbAccidentJsp>();
			for (TbFormalAccident t : ta) {
				TbAccidentJsp tj = new TbAccidentJsp(t);
				taj.add(tj);
			}
			return taj;
		} else {
			return null;
		}

	}

	@Override
	public HashMap<String, Object> getTbAddress(String accidentId) {
		ArrayList<Object[]> addr = formalAccidentDao.getTbAddress(accidentId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		double locationY = Double.parseDouble(addr.get(0)[0].toString());
		double locationX = Double.parseDouble(addr.get(0)[1].toString());
		String[] xy = GpsToBaiDu.ToBaiduAddress(locationX, locationY).split(
				"==");
		resultMap.put("locationX", xy[0]);
		resultMap.put("locationY", xy[1]);
		resultMap.put("address", addr.get(0)[2]);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> saveprocedureMessage(String managerName,
			String accidentId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			formalAccidentDao.saveprocedureMessage(managerName, accidentId);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "保存审核结果成功！");
		} catch (Exception e) {
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "保存审核结果时发生异常！");
		}
		return resultMap;
	}

	@Override
	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId) {
		return formalAccidentDao.getTbAccidentPic(accidentId);
	}

	@Override
	public ArrayList<TbFormalAccidentVo> getAccident(String nickName,
			String reportPhoneNumber, String emergencyCall, String startTime,
			String endTime, String departmentId,String accidentState) {
		ArrayList<TbFormalAccidentVo> atav = new ArrayList<TbFormalAccidentVo>();
		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
				.getAttribute("managerUser");
		Date startDate = null;
		Date endDate = null;
		if("".equals(accidentState)){
			accidentState=null;
		}
		if ("".equals(reportPhoneNumber)) {
			reportPhoneNumber = null;
		}
		if ("".equals(nickName)) {
			nickName = null;
		}
		if ("".equals(emergencyCall)) {
			emergencyCall = null;
		}
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime.toString() + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					.toString() + " 23:59:59");
		} catch (ParseException e) {

		}
		ArrayList<TbFormalAccident> ta = null;
		u = userDao.findById(u.getUserId());
		String parentId = u.getTbDepartment().getParentId();
		HashMap<String, String> map = departmentDao.getDepartmentMap();
		if (parentId == null|| "".equals(parentId)) {
			if (departmentId.equals(u.getTbDepartment().getDepartmentId())) {
				ta = formalAccidentDao.getAccident(startDate, endDate,
						reportPhoneNumber, nickName, emergencyCall, null,accidentState);
			} else {
				ta = formalAccidentDao
						.getAccident(startDate, endDate, reportPhoneNumber,
								nickName, emergencyCall, departmentId,accidentState);
			}

		} else {
			ta = formalAccidentDao.getAccident(startDate, endDate,
					reportPhoneNumber, nickName, emergencyCall, u
							.getTbDepartment().getDepartmentId(),accidentState);
		}
		for (TbFormalAccident tbFormalAccident : ta) {
			TbFormalAccidentVo vo = new TbFormalAccidentVo();
			vo.setAccepter(tbFormalAccident.getAccepter());
			vo.setAccidentState(tbFormalAccident.getAccidentState());
			vo.setAddress(tbFormalAccident.getAddress());
			vo.setDepartment(map.get(tbFormalAccident.getDepartmentId()));
			vo.setDepartmentId(tbFormalAccident.getDepartmentId());
			vo.setFormlAccidentId(tbFormalAccident.getFormlAccidentId());
			vo.setNickname(tbFormalAccident.getNickname());
			vo.setRealName(tbFormalAccident.getRealName());
			vo.setReporterType(tbFormalAccident.getReporterType());
			vo.setReportTimeString(tbFormalAccident.getReportTimeString());
			vo.setReportPhoneNumber(tbFormalAccident.getReportPhoneNumber());
			vo.setEmergencyCall(tbFormalAccident.getEmergencyCall());
			vo.setLocationX(tbFormalAccident.getLocationX());
			vo.setLocationY(tbFormalAccident.getLocationY());
			atav.add(vo);
		}
		return atav;
	}

	@Override
	public HashMap<String, Object> reDevelopDepartment(String departmentId,
			String formlAccidentIds) {
		String[] args = formlAccidentIds.split(",");
		ArrayList<String> as = new ArrayList<String>();
		for (String formlAccidentId : args) {
			if (formlAccidentId != null && !"".equals(formlAccidentId)) {
				as.add(formlAccidentId);
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			formalAccidentDao.updateDepartment(departmentId, as);
			map.put("respCode", 0);
			map.put("respInfo", "事故转发成功！");
		} catch (Exception e) {
			map.put("respCode", 0);
			map.put("respInfo", "事故转发失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> responseWei(String accidentId,String state) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			formalAccidentDao.updateAccidentStateFailed(accidentId,state);
			map.put("respCode", 0);
			map.put("respInfo", "处理结果发送成功！");
		} catch (Exception e) {
			map.put("respCode", -1);
			map.put("respInfo", "处理结果发送失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getAccidentNumber() {
		HashMap<String,Object> result = new HashMap<String, Object>();
		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
				.getAttribute("managerUser");
		u = userDao.findById(u.getUserId());
		String parentId = u.getTbDepartment().getParentId();
		String departmentId = null;
		if (parentId == null ||"".equals(parentId)) {
			departmentId = null;
		}else{
			departmentId = u.getTbDepartment().getDepartmentId();
		}
		Integer x = formalAccidentDao.getListSizeByDepartmentId(departmentId);
		Integer x1=formalAccidentDao.getListSizeChuJingByDepartmentId(departmentId);
		result.put("respCode", 0);
		result.put("listSize", x);
		result.put("listSizeCJ", x1);
		return result;
	}

	@Override
	public TbFormalAccidentVo getAccidentByAccidentId(String accidentId) {
		TbFormalAccident tbFormalAccident=formalAccidentDao.getAccidentByAccidentId(accidentId);
		HashMap<String, String> map = departmentDao.getDepartmentMap();
		TbFormalAccidentVo vo = new TbFormalAccidentVo();
		vo.setAccepter(tbFormalAccident.getAccepter());
		vo.setAccidentState(tbFormalAccident.getAccidentState());
		vo.setAddress(tbFormalAccident.getAddress());
		vo.setDepartment(map.get(tbFormalAccident.getDepartmentId()));
		vo.setDepartmentId(tbFormalAccident.getDepartmentId());
		vo.setFormlAccidentId(tbFormalAccident.getFormlAccidentId());
		vo.setNickname(tbFormalAccident.getNickname());
		vo.setRealName(tbFormalAccident.getRealName());
		vo.setReporterType(tbFormalAccident.getReporterType());
		vo.setReportTimeString(tbFormalAccident.getReportTimeString());
		vo.setReportPhoneNumber(tbFormalAccident.getReportPhoneNumber());
		vo.setEmergencyCall(tbFormalAccident.getEmergencyCall());
		return vo;
	}

	@Override
	public HashMap<String, Object> updateAccidentStateMalicious(
			String managerName, String accidentId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			formalAccidentDao.updateAccidentStateMalicious(managerName, accidentId);
			map.put("respCode", 0);
			map.put("respInfo", "处理结果发送成功！");
		} catch (Exception e) {
			map.put("respCode", -1);
			map.put("respInfo", "处理结果发送失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> updateAccidentStateOther(
			String accidentState, String accidentId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			formalAccidentDao.updateAccidentStateFailed(accidentId, accidentState);
			map.put("respCode", 0);
			map.put("respInfo", "归类成功!");
		} catch (Exception e) {
			map.put("respCode", -1);
			map.put("respInfo", "归类失败!");
		}
		return map;
	}

	@Override
	public ArrayList<TbAccidentStatisticsVo> getAccidentStatistics(
			String startTime, String endTime) {
		String[] departmentIds={"DP002","DP003","DP004","DP005","DP006"};
		String[] departmentNames={"袍江交警大队","镜湖交警大队","东区交警大队","城区交警大队","滨海交警大队"};
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime.toString() + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					.toString() + " 23:59:59");
		} catch (ParseException e) {

		}
		ArrayList<TbAccidentStatisticsVo> tasvos=new ArrayList<TbAccidentStatisticsVo>();
		String hql="from TbFormalAccident where reportTime >=? and reportTime <=? and departmentId = ?";
		Integer Allpass=0,Allevil=0,Allreport=0,Allpending=0,Allcheck=0,Allother=0,Alltotal=0;
		for(int i=0;i<departmentIds.length;i++){
			ArrayList<TbFormalAccident> ata=(ArrayList<TbFormalAccident>) formalAccidentDao.
					findByHQL(hql, startDate,endDate,departmentIds[i]);
			Integer pass=0,evil=0,report=0,pending=0,check=0,other=0,total=0;
			for(TbFormalAccident ta:ata){
				if("审核通过".equals(ta.getAccidentState())){
					pass++;
				}else if("恶意上报".equals(ta.getAccidentState())){
					evil++;
				}else if("上报".equals(ta.getAccidentState())){
					report++;
				}else if("待定".equals(ta.getAccidentState())){
					pending++;
				}else if("现场核实".equals(ta.getAccidentState())){
					check++;
				}else if("其他".equals(ta.getAccidentState())){
					other++;
				}
			}
			Allpass+=pass;Allevil+=evil;Allreport+=report;Allpending+=pending;Allcheck+=check;Allother+=other;
			total=pass+evil+report+pending+check+other;
			Alltotal+=total;
			TbAccidentStatisticsVo tasvo=new TbAccidentStatisticsVo(departmentNames[i],pass,evil,report,pending,check,other,total);
			tasvos.add(tasvo);
		}
		TbAccidentStatisticsVo tasvoo=new TbAccidentStatisticsVo("总计",Allpass,Allevil,Allreport,Allpending,Allcheck,Allother,Alltotal);
		tasvos.add(tasvoo);
		return tasvos;
	}
	
	@Override
	public RespObject saveTbFormalAccident(AccidentBean bean) {
		TbFormalAccident tbFormalAccident=new TbFormalAccident();
		RespObject resp=new RespObject();
		resp.setRespCode(0);
		resp.setRespInfo("保存成功");
		if(!CommonUtil.isMobilePhone(bean.getReportPhoneNumber())){
			resp.setRespCode(1);
			resp.setRespInfo("您输入的手机号有误，请重新输入！");
			return resp;
		}
		if(!CommonUtil.isNumeric(bean.getLocationX())||!CommonUtil.isNumeric(bean.getLocationY())){
			resp.setRespCode(1);
			resp.setRespInfo("位置格式不正确");
			return resp;
		}
		Date date = new Date();
		String formalAccidentId=formalAccidentDao.getNextFormalAccidentId(processDao.getPrefix(Constant.ACCIDENT));
		tbFormalAccident.setFormlAccidentId(formalAccidentId);
		tbFormalAccident.setAddress(bean.getAddress());
		tbFormalAccident.setLocationX(bean.getLocationX());
		tbFormalAccident.setLocationY(bean.getLocationY());
		tbFormalAccident.setReportPhoneNumber(bean.getReportPhoneNumber());
		tbFormalAccident.setReportTime(date);
		tbFormalAccident.setReportOpenId("o4M14jj_gJV3VO61gY5q4OccfzDM");
		String dpid =attributionService.getAttributionDepartment(bean.getLocationX(),bean.getLocationY());
		tbFormalAccident.setDepartmentId(dpid);
		tbFormalAccident.setAccidentState("上报");
		tbFormalAccident.setReporterType("APP");
		
		int picIndex=0;
		TbInfoPic tbInfoPic;
		TbInfoPicId tbInfoPicId;
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=tbFormalAccident.getFormlAccidentId()+File.separator; 
		 for(String picUrls:bean.getPicUrl()) {
			 picIndex++;  
			 tbInfoPicId= new TbInfoPicId();
			 tbInfoPicId.setProcessId(Constant.ACCIDENT);
			 tbInfoPicId.setRecordNo(formalAccidentId); 
			 tbInfoPicId.setPicIndex(picIndex);
			 tbInfoPic=new TbInfoPic();
			 tbInfoPic.setId(tbInfoPicId);
			 tbInfoPic.setPicLocalStore(toPathDir +UUID.randomUUID().toString() +".jpg");
			 new FileThread(picUrls,tbInfoPic.getPicLocalStore()).start(); 
			 	infoPicDao.saveOrUpdate(tbInfoPic); 
			} 
			formalAccidentDao.save(tbFormalAccident);
			resp.setAccidentId(formalAccidentId);
			return resp;
	}

	@Override
	public RespObject updateTbFormalAccident(AccidentBean bean) {
		TbFormalAccident tbFormalAccident;
		RespObject resp=new RespObject();
		resp.setRespCode(0);
		resp.setRespInfo("更新成功");
		if(bean.getAccidentid()==null){
			resp.setRespCode(1);
			resp.setRespInfo("事故id为空！");
			return resp;
		}
		tbFormalAccident=formalAccidentDao.findById(bean.getAccidentid());
		if(tbFormalAccident==null){
			resp.setRespCode(1);
			resp.setRespInfo("该事故不存在或已删除！");
			return resp;
		}
		if(!CommonUtil.isMobilePhone(bean.getReportPhoneNumber())){
			resp.setRespCode(1);
			resp.setRespInfo("您输入的手机号有误，请重新输入！");
			return resp;
		}
		if(!CommonUtil.isNumeric(bean.getLocationX())||!CommonUtil.isNumeric(bean.getLocationY())){
			resp.setRespCode(1);
			resp.setRespInfo("位置格式不正确");
			return resp;
		}
		tbFormalAccident.setFormlAccidentId(bean.getAccidentid());
		tbFormalAccident.setAddress(bean.getAddress());
		tbFormalAccident.setLocationX(bean.getLocationX());
		tbFormalAccident.setLocationY(bean.getLocationY());
		tbFormalAccident.setReportPhoneNumber(bean.getReportPhoneNumber());
		tbFormalAccident.setAccidentState("上报");
		
		int picIndex=0;
		TbInfoPic tbInfoPic;
		TbInfoPicId tbInfoPicId;
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=tbFormalAccident.getFormlAccidentId()+File.separator; 
		 for(String picUrls:bean.getPicUrl()) {
			 picIndex++;  
			 tbInfoPicId= new TbInfoPicId();
			 tbInfoPicId.setProcessId(Constant.ACCIDENT);
			 tbInfoPicId.setRecordNo(bean.getAccidentid()); 
			 tbInfoPicId.setPicIndex(picIndex);
			 tbInfoPic=new TbInfoPic();
			 tbInfoPic.setId(tbInfoPicId);
			 tbInfoPic.setPicLocalStore(toPathDir +UUID.randomUUID().toString() +".jpg");
			 new FileThread(picUrls,tbInfoPic.getPicLocalStore()).start(); 
			 	infoPicDao.saveOrUpdate(tbInfoPic); 
			} 
			formalAccidentDao.saveOrUpdate(tbFormalAccident);
			resp.setAccidentId(bean.getAccidentid());
			
			TbTip tbTip=new TbTip();  
			tbTip.setProcessId(Constant.ACCIDENT);
			tbTip.setDepartmentId(tbFormalAccident.getDepartmentId());
			tbTip.setTipType("RESUBMIT");
			tbTip.setRecordNo(tbFormalAccident.getFormlAccidentId());
			tbTip.setHaveTip(false); 
			Calendar cal = Calendar.getInstance(); 
	        tbTip.setLastTime(new Timestamp(cal.getTime().getTime())); 
			tipDao.saveOrUpdate(tbTip); 
			
			return resp;
	}

}
