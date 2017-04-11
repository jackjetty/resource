package com.rising.management.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.PrizeCode;
import com.rising.management.service.PrizeCodeService;

@Controller("prizeCodeAction")
public class PrizeCodeAction {

	private HashMap<String, Object> resultMap;

	private String status;

	private String Ids;

	private File excel;

	private String excelFileName;

	private Integer prizeId;

	private Integer pageIndex;

	private Integer pageSize;

	@Autowired
	PrizeCodeService prizeCodeService;

	public String doPrizeCode() {
		return "success";
	}

	public String getPrizeCode() {
		resultMap = prizeCodeService.getPrizeCode(prizeId, status, pageIndex,
				pageSize);
		return "success";
	}

	public String removePrizeCode() {
		resultMap = prizeCodeService.removePrizeCode(Ids);
		return "success";
	}
	
	public String importFromFile(){
		ArrayList<PrizeCode> ap = new ArrayList<PrizeCode>();
		try {
			Workbook wbook= Workbook.getWorkbook(excel);
			Sheet sheet = wbook.getSheet(0);
			Integer rowNumber = sheet.getRows();
			Cell [] cells = sheet.getRow(0);
			if("验证码".equals(cells[0].getContents())&&"截至有效时间".equals(cells[1].getContents())){
				for(int i=1;i<rowNumber;i++){
					PrizeCode code = new PrizeCode();
					cells = sheet.getRow(i);
					code.setCode(cells[0].getContents());
					code.setEffectiveTime(cells[1].getContents());
					code.setSended("no");
					code.setPrizeId(prizeId);
					ap.add(code);
				}
				resultMap = prizeCodeService.add(ap);
			}else{
				resultMap = new HashMap<String, Object>();
				resultMap.put("respCode", -2);
				resultMap.put("respInfo", "模版格式不正确");
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return "success";
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
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

	public String getIds() {
		return Ids;
	}

	public void setIds(String ids) {
		Ids = ids;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

}
