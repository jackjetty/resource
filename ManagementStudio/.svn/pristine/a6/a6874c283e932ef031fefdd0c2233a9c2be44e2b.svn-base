package com.rising.management.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.service.WinnerListService;

@Controller("winnerListAction")
public class WinnerListAction {
	Log log = LogFactory.getLog(WinnerListAction.class);

	private String phoneNumber;

	private String place;

	@Autowired
	WinnerListService winnerListService;

	private Integer pageIndex;

	private Integer pageSize;

	private Integer id;

	private Integer prizeId;

	private Integer sortId;

	private String numbers;
	
	private String winTime;

	private HashMap<String, Object> resultMap;

	public String addWinner() {
		resultMap = winnerListService.addWinner(prizeId,sortId,numbers);
		return "success";
	}

	public String getWinnerListByPlace() {
		if ("null".equals(place)) {
			place = null;
		}
		resultMap = winnerListService.getWinnerListByPage(pageIndex, pageSize,
				place);
		return "success";
	}

	public String deleteWinnerList() {
		resultMap = winnerListService.deleteWinnerById(id);
		return "success";
	}
	
	public String doWinningInfo(){
		return "success";
	}
	
	public String getWinningInfo(){
		if(prizeId ==0){
			prizeId = null;
		}
		resultMap = winnerListService.getWinningInfo(pageSize,pageIndex,prizeId,phoneNumber,winTime);
		return "success";
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	
	public String getWinTime() {
		return winTime;
	}

	public void setWinTime(String winTime) {
		this.winTime = winTime;
	}

}
