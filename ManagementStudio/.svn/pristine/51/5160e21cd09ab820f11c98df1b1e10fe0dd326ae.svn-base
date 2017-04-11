package com.rising.management.vo;

import java.util.HashMap;

import com.rising.management.bean.PrizeShortMessage;

public class PrizeShortMessageVo {

	private Integer id;

	private Integer prizeId;

	private String prizeName;

	private String place;

	private String placeName;

	private String content;

	public PrizeShortMessageVo(PrizeShortMessage prizeShortMessage,
			HashMap<String, Object> map, HashMap<String, Object> placeMap) {
		this.id = prizeShortMessage.getId();
		this.prizeId = prizeShortMessage.getPrizeId();
		this.prizeName = (String) map.get(prizeShortMessage.getPrizeId().toString());
		this.place = prizeShortMessage.getPlace();
		this.placeName = (String) placeMap.get(this.place);
		this.content = prizeShortMessage.getContent();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

}
