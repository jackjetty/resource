package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.rising.wei.bean.ErrCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.vo.TbHistoryVo;
import com.detachment.web.service.HistoryService;
import com.detachment.wei.service.NetService;

@Controller("historyAction")
public class HistoryAction {
	private HashMap<String, Object> result;
	private ArrayList<TbHistoryVo> list;
	private Integer pageSize;
	private Integer pageIndex;
	private String historyTypeId;
	private String ids;
	private boolean valid;
	private ErrCodeBean errCode;
	
	@Autowired
	HistoryService historyService;
	@Autowired
	NetService netService;
	
	
	
	
	public String doHistory(){
		return "success";
	}
	
	public String getTbHistory(){
		result=historyService.getTbHistory(historyTypeId, pageSize, pageIndex);
		return "success";
	}

	
	public String changeHistoryType(){
		result=historyService.changeHistoryTypeById(ids, historyTypeId);
		return "success";
	}
	public String changeValid(){
		result=historyService.changeValid(ids, valid);
		return "success";
	}
	
	public String doHistoryJsp(){
		return "success";
	}
	public String doHistoryDriving(){
		return "success";
	}
	public String doHistoryRoad(){
		return "success";
	}
	
	public String getHistoryToJsp(){
		list=historyService.getHistoryToJsp(historyTypeId, pageSize, pageIndex);
		return "success";
	}
	
	public String reloadHistory(){
		ErrCodeBean errcode=netService.updateHistoryNews();
		return "success";
	}
	
	
	
	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
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

	public String getHistoryTypeId() {
		return historyTypeId;
	}

	public void setHistoryTypeId(String historyTypeId) {
		this.historyTypeId = historyTypeId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public ArrayList<TbHistoryVo> getList() {
		return list;
	}

	public void setList(ArrayList<TbHistoryVo> list) {
		this.list = list;
	}

	public ErrCodeBean getErrCode() {
		return errCode;
	}

	public void setErrCode(ErrCodeBean errCode) {
		this.errCode = errCode;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
