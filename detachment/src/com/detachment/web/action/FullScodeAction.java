package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbProblems;
import com.detachment.util.CommonUtil;
import com.detachment.web.service.FullScodeService;
import com.detachment.web.service.TbProblemsService;
import com.detachment.wei.service.CustResService;

@Controller("fullScodeAction")
public class FullScodeAction {
	private String openId;
	private HashMap<String,Object> result;
	private ArrayList<TbProblems> listProblem;
	private String identityCard;
	private String fileNum;
	private String userName;
	private String phoneNumber;
	private String state;
	
	private String totalHour;
	private int page;
	private int rows;
	private Integer fullScodeId;
	
	
	
	
	public String getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(String totalHour) {
		this.totalHour = totalHour;
	}
	 

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}


	@Autowired
	TbProblemsService tbProblemsService;
	@Autowired
	FullScodeService fullScodeService;
	@Autowired
	CustResService custResService;
	
	public String doFullScodeLogin(){
		if("".equals(openId) || openId==null){
			return "success";
		}else{
			result = fullScodeService.AutomaticLogin(openId);
			Integer res= (Integer) result.get("respCode");
			if(res==0){
				identityCard=(String) result.get("idCard");
				return "toStudy";
			}else if(res==1){
				return "doAppointmentJsp";
			}else if(res==2){
				return "doAppointmentRecordJsp";
			}else if(res==3){
				return "appointmentAudit";
			}else{
				return "success";
			}
		}
		
		
	}
	public String webFullScode() {
		return "success";
	} 
	public String doFucScode(){
		return "success";
	}
	public String toFucScodeLogin(){
		result=fullScodeService.toFullScodeLogin(identityCard, fileNum,openId,userName,phoneNumber);
		return "success";
	}
	
	public String saveFirstStudyTime(){
		result=fullScodeService.saveFirstStudyTime(identityCard,openId);
		return "success";
	}
	public String getTotalHours(){
		result=fullScodeService.getTotalHours(identityCard);
		return "success";
	}
	
	public String getFullScode(){
		result = fullScodeService.getFullScode(CommonUtil.trim(identityCard),CommonUtil.trim(fileNum),CommonUtil.trim(totalHour),CommonUtil.trim(state), page, rows);
		return "success";
	}
	
	public String deductFullScode(){
		result = fullScodeService.deductFullScode(fullScodeId);
		return "success";
	}
	
	public String toAutomaticLogin(){
		result = fullScodeService.AutomaticLogin(openId);
		return "success";
	}
	
	public String doPeopleInformation(){
		return "success";
	}
	public String getPeopleInfoByIdentityCard(){
		result = fullScodeService.getPeopleInfoByIdentityCard(identityCard);
		return "success";
	}
	public String updateFucScodeInfo(){
		result = fullScodeService.updateFucScodeInfo(userName, phoneNumber, identityCard);
		return "success";
	}
	
	public String updateFullScodeState(){
		result=fullScodeService.updateFullScodeState(fullScodeId);
		Integer res=(Integer) result.get("respCode");
		if(res==0){
			String openIdInfo=(String) result.get("respInfo");
			String href="http://114.215.238.212/detachment/web/doAppointmentJsp.action?openId="+openIdInfo;
			String content="您好，您的满分练习已达到12学时，并且已经审核通过，您可以点击<a href='"+href+"'>这里</a>进行预约";
			ErrCodeBean error=custResService.responseTextMessage(openIdInfo, content);
			if("0".equals(error.getErrcode())){
				result.put("respCode", 0);
			}
		}
		return "success";
	}
	
	

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public ArrayList<TbProblems> getListProblem() {
		return listProblem;
	}

	public void setListProblem(ArrayList<TbProblems> listProblem) {
		this.listProblem = listProblem;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getFullScodeId() {
		return fullScodeId;
	}
	public void setFullScodeId(Integer fullScodeId) {
		this.fullScodeId = fullScodeId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	
}
