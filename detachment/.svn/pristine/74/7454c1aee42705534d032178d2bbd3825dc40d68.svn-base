package com.detachment.pojo.vo;

import java.util.HashMap;

import com.detachment.pojo.TbHistory;


public class TbHistoryVo {
	private String historyId;
	private String historyTypeName;
	private String historyTitle;
	private String historyDes;
	private String valid;
	private String picUrl;
	private String objUrl;
	private Integer showOrder;
	private String remark;
	
	
	public TbHistoryVo(){}
	
	
	
	public TbHistoryVo(TbHistory th,HashMap<String,Object> map) {
		this.historyId = th.getHistoryId();
		if(th.getTbHistoryType()!=null){
			this.historyTypeName = (String) map.get(th.getTbHistoryType().getHistoryTypeId());
		}else{
			this.historyTypeName ="";
		}
		this.historyTitle = th.getHistoryTitle();
		this.historyDes = th.getHistoryDes();
		this.picUrl = th.getPicUrl();
		this.objUrl = th.getObjUrl();
		this.showOrder = th.getShowOrder();
		this.remark = th.getRemark();
		if(th.getValid()==true){
			this.valid="启用";
		}else{
			this.valid="禁用";
		}
		
	}
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public String getHistoryTypeName() {
		return historyTypeName;
	}
	public void setHistoryTypeName(String historyTypeName) {
		this.historyTypeName = historyTypeName;
	}
	public String getHistoryTitle() {
		return historyTitle;
	}
	public void setHistoryTitle(String historyTitle) {
		this.historyTitle = historyTitle;
	}
	public String getHistoryDes() {
		return historyDes;
	}
	public void setHistoryDes(String historyDes) {
		this.historyDes = historyDes;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getObjUrl() {
		return objUrl;
	}
	public void setObjUrl(String objUrl) {
		this.objUrl = objUrl;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getValid() {
		return valid;
	}



	public void setValid(String valid) {
		this.valid = valid;
	}
	
	
	
	
}
