package com.detachment.pojo.vo;

import java.text.SimpleDateFormat;

import com.detachment.pojo.TbFullScode;
import com.detachment.util.CommonUtil; 
@SuppressWarnings("unused")
public class TbFullScodeVo{
	 private Integer fullScodeId;
	 private String identityCard;
	 private String fileNum;
	 private String firstStudyTime;
	 private String deadlineTime;
	 private String totalHour;
	 private String realName;
	 private String nickname;
	 private String state;
	 private String openId;
	 private String remark;
	 private String op1;
	 private String userName;
	 private String phoneNumber;
	 
	 public TbFullScodeVo(){}
	 
	 public TbFullScodeVo(TbFullScode tfs){
		 this.fullScodeId=tfs.getFullScodeId();
		 this.identityCard=tfs.getIdentityCard();
		 this.fileNum=tfs.getFileNum();
		 if(tfs.getFirstStudyTime()!=null){
			 this.firstStudyTime=new SimpleDateFormat("yyyy-MM-dd").format(tfs.getFirstStudyTime().getTime());
		 }else{
			 this.firstStudyTime="";
		 }
		 if(tfs.getDeadlineTime()!=null){
			 this.deadlineTime=new SimpleDateFormat("yyyy-MM-dd").format(tfs.getDeadlineTime().getTime());
		 }else{
			 this.deadlineTime="";
		 }
		 if(tfs.getTbWeiUser()!=null){
			 this.realName=tfs.getTbWeiUser().getRealName();
			 this.nickname=tfs.getTbWeiUser().getNickname();
		 }else{
			 this.realName="";
			 this.nickname="";
		 }
		 this.totalHour=tfs.getTotalHour().toString();
		 this.state=tfs.getState();
		 this.openId=tfs.getTbWeiUser().getOpenId();
		 this.userName=tfs.getUserName();
		 this.phoneNumber=tfs.getPhoneNumber();
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
	public String getFirstStudyTime() {
		return firstStudyTime;
	}
	public void setFirstStudyTime(String firstStudyTime) {
		this.firstStudyTime = firstStudyTime;
	}
	public String getDeadlineTime() {
		return deadlineTime;
	}
	public void setDeadlineTime(String deadlineTime) {
		this.deadlineTime = deadlineTime;
	}
	public String getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(String totalHour) {
		this.totalHour = totalHour;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	 
}