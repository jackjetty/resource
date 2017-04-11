package com.rising.management.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.ReturnCode;
import com.rising.management.service.ReturnCodeService;

@Controller("returnCodeAction")
public class ReturnCodeAction {

	private String ids;

	private Integer id;

	private Integer pageSize;

	private Integer pageIndex;

	private Integer busId;

	private String returnCode;

	private String codeMeaning;

	private String show;

	private Boolean hasShow;

	private HashMap<String, Object> resultMap;

	@Autowired
	ReturnCodeService returnCodeService;

	public String doReturnCode() {
		return "success";
	}

	public String doReturnCodeInfo() {
		return "success";
	}

	public String getReturnCodeInfo() {

		if (busId < 0) {
			busId = null;
		}
		resultMap = returnCodeService.getReturnCode(pageSize, pageIndex, busId,
				returnCode, hasShow);
		return "success";
	}

	public String addReturnCode() {
		ReturnCode r = new ReturnCode();
		r.setBusId(busId);
		r.setCodeMeaning(codeMeaning);
		r.setReturnCode(returnCode);
		r.setShow(show);
		resultMap = returnCodeService.addReturnCode(r);
		return "success";

	}

	public String removeReturnCode() {
		resultMap = returnCodeService.deleteByIds(ids);
		return "success";

	}

	public String updateReturnCode() {
		ReturnCode r = new ReturnCode();
		r.setId(id);
		r.setBusId(busId);
		r.setCodeMeaning(codeMeaning);
		r.setReturnCode(returnCode);
		r.setShow(show);
		resultMap = returnCodeService.updateReturnCode(r);
		return "success";
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getReturnCode() {
		return returnCode;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getCodeMeaning() {
		return codeMeaning;
	}

	public void setCodeMeaning(String codeMeaning) {
		this.codeMeaning = codeMeaning;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public Boolean getHasShow() {
		return hasShow;
	}

	public void setHasShow(Boolean hasShow) {
		this.hasShow = hasShow;
	}

}
