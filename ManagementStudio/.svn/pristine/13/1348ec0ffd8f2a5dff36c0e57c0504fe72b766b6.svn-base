package com.rising.management.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.PrizeShortMessage;
import com.rising.management.service.PrizeShortMessageService;

@Controller("prizeShortMessageAction")
public class PrizeShortMessageAction {

	private Integer id;

	private String Ids;

	private Integer pageIndex;

	private Integer pageSize;

	private Integer prizeId;

	private String place;

	private String content;

	private HashMap<String, Object> resultMap;

	@Autowired
	PrizeShortMessageService prizeShortMessageService;

	public String doPrizeShortMessage() {
		return "success";
	}

	public String getPrizeShortMessage() {
		resultMap = prizeShortMessageService.getPrizeShortMessage(pageIndex,
				pageSize, prizeId, place);
		return "success";
	}

	public String addPrizeShortMessage() {
		PrizeShortMessage prizeShortMessage = new PrizeShortMessage();
		prizeShortMessage.setContent(content);
		prizeShortMessage.setPlace(place);
		prizeShortMessage.setPrizeId(prizeId);
		resultMap = prizeShortMessageService
				.addPrizeShortMessage(prizeShortMessage);
		return "success";
	}
	
	public String updatePrizeShortMessage() {
		PrizeShortMessage prizeShortMessage = new PrizeShortMessage();
		prizeShortMessage.setContent(content);
		prizeShortMessage.setId(id);
		prizeShortMessage.setPlace(place);
		prizeShortMessage.setPrizeId(prizeId);
		resultMap = prizeShortMessageService
				.updatePrizeShortMessage(prizeShortMessage);
		return "success";
	}

	public String removePrizeShortMessage() {
		resultMap = prizeShortMessageService.removePrizeShortMessage(Ids);
		return "success";
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return Ids;
	}

	public void setIds(String ids) {
		Ids = ids;
	}

}
