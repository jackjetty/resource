package com.traffic.web.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.InfoPicService;

@Controller("infoPicAction")  
public class InfoPicAction {

	@Autowired
	private InfoPicService infoPicService;

	private HashMap<String, Object> result;
	private String jsArrInfoPic;
	private String redirectType;
	private String accidentId;
	private String arrPicIndex;
	private String pdfPath;
	private String exportType;

	public String rotatePic() {
		result = infoPicService.rotatePic(jsArrInfoPic);
		return redirectType == null ? "success" : redirectType;
	}
	
	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getJsArrInfoPic() {
		return jsArrInfoPic;
	}

	public void setJsArrInfoPic(String jsArrInfoPic) {
		this.jsArrInfoPic = jsArrInfoPic;
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}

	public String getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	public String getArrPicIndex() {
		return arrPicIndex;
	}

	public void setArrPicIndex(String arrPicIndex) {
		this.arrPicIndex = arrPicIndex;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
}