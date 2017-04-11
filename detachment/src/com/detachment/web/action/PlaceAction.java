package com.detachment.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbPlace;
import com.detachment.pojo.TbPlaceArea;
import com.detachment.web.service.PlaceService;

@Controller("placeAction")
public class PlaceAction {

	private String placeName;

	private Integer placeId;

	private String placeIds;

	private String remark;

	private Integer pageIndex;

	private Integer pageSize;

	private File uploadFile;

	private String uploadFileFileName;

	@Autowired
	PlaceService placeService;

	private HashMap<String, Object> resultMap;

	private ArrayList<HashMap<String, Object>> ahso;
	
	private TbPlaceArea tbpa;

	public String placeTest() {
		resultMap=placeService.getPlaceMapTest();
		return "success";
	}
	public String placeClick() {
		placeService.setPlaceMapClick(tbpa);
		return "success";
	}
	
	public String doPlace() {
		return "success";
	}
	
	public String getAllPlace() {
		if ("".equals(placeName)) {
			placeName = null;
		}
		resultMap = placeService.getPlace(placeName, pageIndex, pageSize);
		return "success";
	}

	public String getPlaceMap() {
		ahso = placeService.getPlaceMap();
		return "success";
	}

	public String addPlace() {
		TbPlace p = new TbPlace();
		p.setPlaceName(placeName);
		p.setRemark(remark);
		resultMap = placeService.addPlace(p);
		return "success";
	}

	public String updatePlace() {
		TbPlace p = new TbPlace();
		p.setPlaceId(placeId);
		p.setPlaceName(placeName);
		p.setRemark(remark);
		resultMap = placeService.updatePlace(p);
		return "success";
	}

	public String removePlace() {
		resultMap = placeService.removePlace(placeIds);
		return "success";
	}

	public String uploadPlaceArea() {
		if (uploadFileFileName != null && uploadFileFileName.endsWith(".xls")) {
			ArrayList<TbPlaceArea> atpa = null;
			try {
				atpa = getDataFromExcelFile(placeId,
						uploadFile);
				if (atpa == null) {
					resultMap = new HashMap<String, Object>();
					resultMap.put("respCode", -2);
					resultMap.put("respInfo", "文件内无数据！");
				} else if (atpa.size() == 0) {
					resultMap = new HashMap<String, Object>();
					resultMap.put("respCode", -3);
					resultMap.put("respInfo", "读取数据遇到问题！");
				} else {
					resultMap = placeService.addPlaceAreadDetail(atpa);
				}
			
			} catch (Exception e) {
				resultMap.put("respCode", -4);
				resultMap.put("respInfo", "excel文件中数据格式错误");
			}
		} else {
			resultMap = new HashMap<String, Object>();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "文件仅允许xls格式");
		}
		return "success";
	}

	private ArrayList<TbPlaceArea> getDataFromExcelFile(Integer placeId,
			File uploadFile) throws Exception {
		ArrayList<TbPlaceArea> atpa = new ArrayList<TbPlaceArea>();
		FileInputStream fis = new FileInputStream(uploadFile);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <= 1) {
			return null;
		} else {
			Integer cloumnsNumber = sheet.getRow(0).getPhysicalNumberOfCells();
			String[] cloumns = new String[cloumnsNumber];
			HSSFRow row = sheet.getRow(0);
			try{
				for (int i = 0; i < cloumnsNumber; i++) {
					HSSFCell cell = row.getCell(i);
					String cellValue = cell.getStringCellValue();
					cloumns[i] = cellValue;
				}
				for (int i = 1; i < rows; i++) {
					row = sheet.getRow(i);
					// 行不为空­
					if (row != null) {
						// 获取到Excel文件中的所有的列
						TbPlaceArea area = new TbPlaceArea();
						HSSFCell cell = row.getCell(0);
						area.setLatitude(String.valueOf(cell.getNumericCellValue()));
						cell = row.getCell(1);
						area.setLongitude(String.valueOf(cell.getNumericCellValue()));
						area.setPlaceId(placeId);
						atpa.add(area);
					}
				}
			}catch(Exception e){
				throw e;
			}
			
		}

		return atpa;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getPlaceIds() {
		return placeIds;
	}

	public void setPlaceIds(String placeIds) {
		this.placeIds = placeIds;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public ArrayList<HashMap<String, Object>> getAhso() {
		return ahso;
	}

	public void setAhso(ArrayList<HashMap<String, Object>> ahso) {
		this.ahso = ahso;
	}
	public TbPlaceArea getTbpa() {
		return tbpa;
	}
	public void setTbpa(TbPlaceArea tbpa) {
		this.tbpa = tbpa;
	}

}
