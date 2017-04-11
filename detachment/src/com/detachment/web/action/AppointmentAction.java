package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbAppointmentType;
import com.detachment.pojo.vo.TbAppointmentVo;
import com.detachment.web.service.AppointmentService;
import com.detachment.wei.service.CustResService;

@Controller("appointmentAction")
public class AppointmentAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String openId;
	private ArrayList<TbAppointmentVo> listVo;
	private Integer appointmentId;
	private HashMap<String, Object> result;
	private ArrayList<TbAppointmentType> tbAppointmentTypeList;
	private String appointmentTypeId;
	private String appointmentState;
	private TbAppointmentVo tatvo;
	private Integer appointmentSum;
	private String appointmentStartTime;
	private String appointmentEndTime;
	private String appointmentTheme;
	private String appointmentDesc;
	private String appointmentAddress;
	private String appointmentRemark;
	private String appointmentIds;
	
	
	@Autowired
	AppointmentService appointmentService;
	@Autowired
	CustResService custResService;
	
	public String doAppointmentJsp(){
		return "success";
	}
	
	public String getAppointmentJsp(){
		listVo=appointmentService.getAppointmentJsp(pageSize, pageIndex);
		return "success";
	}
	
	public String saveAppointmentRecord(){
		result=appointmentService.saveAppointmentRecord(appointmentId, openId);
		Integer res=(Integer) result.get("codeInfo");
		if(res==1){
			TbAppointmentVo codeText=(TbAppointmentVo) result.get("codeText");
			String content="您预约了"+codeText.getAppointmentTheme()+",内容:"+codeText.getAppointmentDesc()+", 地点:"+codeText.getAppointmentAddress()+"，请准时参加";
			ErrCodeBean error=custResService.responseTextMessage(openId, content);
			if("0".equals(error.getErrcode())){
				result.put("codeInfo", 1);
			}
		}
		return "success";
	}
	
	public String doAppointmentRecordJsp(){
		return "success";
	}
	
	public String getAppointmentRecordJsp(){
		listVo=appointmentService.findAppointmentRecordJsp(openId);
		return "success";
	}
	
	public String doAppointment(){
		return "success";
	}
	
	public String getTbAppointmentTypeIdAndName() {
		result=appointmentService.getTbAppointmentTypeIdAndName();
		return "success";
	}
	
	// tinker 2014-09-12
	public String getTbAppointmentTheme() {
		result = appointmentService.getTbAppointmentTheme(appointmentTypeId);
		return "success";
	}
	
	public String getAppointment(){
		result=appointmentService.getAppointment(pageSize, pageIndex, appointmentTypeId, appointmentState);
		return "success";
	}
	
	public String getTbAppointmentById(){
		tatvo=appointmentService.getTbAppointmentById(appointmentId);
		return "success";
	}
	
	public String addAppointment(){
		result=appointmentService.addAppointment(appointmentTypeId, appointmentSum, appointmentStartTime, 
				appointmentEndTime, appointmentTheme, appointmentDesc, appointmentAddress, 
				appointmentRemark);
		return "success";
	}
	
	public String updateAppointment(){
		result=appointmentService.updateAppointment(appointmentId, appointmentTypeId, appointmentSum, 
				appointmentStartTime, appointmentEndTime, appointmentTheme, appointmentDesc, 
				appointmentAddress, appointmentRemark);
		return "success";
	}
	
	public String removeAppointment(){
		result=appointmentService.removeAppointment(appointmentIds);
		return "success";
	}
	
	public String updateAppointmentState(){
		result=appointmentService.updateAppointmentState(appointmentIds);
		return "success";
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public ArrayList<TbAppointmentVo> getListVo() {
		return listVo;
	}
	public void setListVo(ArrayList<TbAppointmentVo> listVo) {
		this.listVo = listVo;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public ArrayList<TbAppointmentType> getTbAppointmentTypeList() {
		return tbAppointmentTypeList;
	}

	public void setTbAppointmentTypeList(
			ArrayList<TbAppointmentType> tbAppointmentTypeList) {
		this.tbAppointmentTypeList = tbAppointmentTypeList;
	}

	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public String getAppointmentState() {
		return appointmentState;
	}

	public void setAppointmentState(String appointmentState) {
		this.appointmentState = appointmentState;
	}

	public TbAppointmentVo getTatvo() {
		return tatvo;
	}

	public void setTatvo(TbAppointmentVo tatvo) {
		this.tatvo = tatvo;
	}

	public Integer getAppointmentSum() {
		return appointmentSum;
	}

	public void setAppointmentSum(Integer appointmentSum) {
		this.appointmentSum = appointmentSum;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public String getAppointmentTheme() {
		return appointmentTheme;
	}

	public void setAppointmentTheme(String appointmentTheme) {
		this.appointmentTheme = appointmentTheme;
	}

	public String getAppointmentDesc() {
		return appointmentDesc;
	}

	public void setAppointmentDesc(String appointmentDesc) {
		this.appointmentDesc = appointmentDesc;
	}

	public String getAppointmentAddress() {
		return appointmentAddress;
	}

	public void setAppointmentAddress(String appointmentAddress) {
		this.appointmentAddress = appointmentAddress;
	}

	public String getAppointmentRemark() {
		return appointmentRemark;
	}

	public void setAppointmentRemark(String appointmentRemark) {
		this.appointmentRemark = appointmentRemark;
	}

	public String getAppointmentIds() {
		return appointmentIds;
	}

	public void setAppointmentIds(String appointmentIds) {
		this.appointmentIds = appointmentIds;
	}
	
	
	
}
