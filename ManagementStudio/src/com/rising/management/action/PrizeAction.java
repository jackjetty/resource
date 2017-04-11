package com.rising.management.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Prize;
import com.rising.management.common.FileToByteUtil;
import com.rising.management.service.PrizeService;

@Controller("prizeAction")
public class PrizeAction {

	private Integer pageSize;

	private Integer pageIndex;

	private Boolean hasLeft;
	
	private String name;
	
	private Integer amount;
	
	private String sendWay;
	
	private Integer sendScore;
	
	private String imgName;
	
	private File img1;
	
	private File img2;
	
	private String describe;
	
	private String begin;
	
	private String end;
	
	private Integer prizeId;
	
	private String addTime;
	
	private Integer leftAmount;
	
	private String prizeIds;

	private String startTime;

	private String endTime;
	
	private HashMap<String, Object> resultMap;

	private ArrayList<Prize> ap;

	@Autowired
	PrizeService prizeService;

	public String doPrize() {
		return "success";
	}

	public String getPrize() {
		resultMap = prizeService.getPrizeByPage(hasLeft, pageSize, pageIndex);
		return "success";
	}

	public String getPrizeInfo() {
		ap = prizeService.getPrizeInfo();
		return "success";
	}

	public String doAppUserPrize() {
		startTime = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "success";
	}
	
	public String addPrize(){
		Prize p=new Prize();
		p.setName(name);
		p.setAmount(amount);
		p.setLeftAmount(leftAmount);
		p.setSendWay(sendWay);
		p.setSendScore(sendScore);
		p.setAddTime(new Date());
		p.setImgName(imgName);
		if(img1!=null){
			p.setImg1(FileToByteUtil.getFileToByte(img1));
		}
		if(img2!=null){
			p.setImg2(FileToByteUtil.getFileToByte(img2));
		}
		p.setDescribe(describe);
		resultMap = prizeService.savePrize(p);
		return "success";
	}
	
	public String updatePrize(){
		Prize p=new Prize();
		p.setName(name);
		p.setAmount(amount);
		p.setLeftAmount(leftAmount);
		p.setSendWay(sendWay);
		p.setSendScore(sendScore);
		p.setImgName(imgName);
		p.setPrizeId(prizeId);
		try {
			p.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(addTime));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		p.setDescribe(describe);
		if(!"".equals(begin)){
			try {
				p.setBegin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!"".equals(end)){
			try {
				p.setEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(img1!=null){
			p.setImg1(FileToByteUtil.getFileToByte(img1));
		}
		if(img2!=null){
			p.setImg2(FileToByteUtil.getFileToByte(img2));
		}
		resultMap = prizeService.updatePrize(p);
		return "success";
	}
	
	public String deletePrize(){
		resultMap = prizeService.deletePrize(prizeIds);
		return "success";
	}

	public String getPrizeInfoById() {
		resultMap = prizeService.getPrizeById(prizeId);
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

	public Boolean getHasLeft() {
		return hasLeft;
	}

	public void setHasLeft(Boolean hasLeft) {
		this.hasLeft = hasLeft;
	}


	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ArrayList<Prize> getAp() {
		return ap;
	}

	public void setAp(ArrayList<Prize> ap) {
		this.ap = ap;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}

	public String getPrizeIds() {
		return prizeIds;
	}

	public void setPrizeIds(String prizeIds) {
		this.prizeIds = prizeIds;
	}
	
	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public File getImg1() {
		return img1;
	}

	public void setImg1(File img1) {
		this.img1 = img1;
	}

	public File getImg2() {
		return img2;
	}

	public void setImg2(File img2) {
		this.img2 = img2;
	}

	
}
