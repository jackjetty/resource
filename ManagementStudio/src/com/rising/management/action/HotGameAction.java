package com.rising.management.action;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.HotGame;
import com.rising.management.common.FileToByteUtil;
import com.rising.management.service.HotGameService;

@Controller("hotGameAction")
public class HotGameAction {
	Log log = LogFactory.getLog(AppStartPictureAction.class);

	private Integer pageSize;
	private Integer pageIndex;
	private String packageName;
	private String title;
	private Integer star;
	private String fileSize;
	private String icoName;
	private File icoImg1;
	private File icoImg2;
	private String apkUrl;
	private Integer id;
	private String ids;

	private HashMap<String, Object> resultMap;

	@Autowired
	HotGameService hotGameService;

	public String doHotGame() {
		return "success";
	}

	public String getHotGameInfo() {
		resultMap = hotGameService.getHotGameInfo(packageName, title, pageSize,
				pageIndex);
		return "success";
	}

	public String addHotGame() {
		HotGame hg = new HotGame();
		hg.setPackageName(packageName);
		hg.setTitle(title);
		hg.setStar(star);
		hg.setFileSize(fileSize);
		hg.setIcoName(icoName);
		hg.setApkUrl(apkUrl);
		hg.setIcoImg1(FileToByteUtil.getFileToByte(icoImg1));
		hg.setIcoImg2(FileToByteUtil.getFileToByte(icoImg2));
		resultMap = hotGameService.addHotGame(hg);
		return "success";
	}

	public String updateHotGame() {
		HotGame hg = hotGameService.getHotGameById(id);
		hg.setPackageName(packageName);
		hg.setTitle(title);
		hg.setStar(star);
		hg.setFileSize(fileSize);
		hg.setIcoName(icoName);
		hg.setApkUrl(apkUrl);
		if (icoImg1 != null) {
			hg.setIcoImg1(FileToByteUtil.getFileToByte(icoImg1));
		}
		if (icoImg2 != null) {
			hg.setIcoImg1(FileToByteUtil.getFileToByte(icoImg2));
		}
		resultMap = hotGameService.updateHotGame(hg);
		return "success";
	}

	public String removeHotGame() {
		resultMap = hotGameService.removeHotGame(ids);
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getIcoName() {
		return icoName;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}

	public File getIcoImg1() {
		return icoImg1;
	}

	public void setIcoImg1(File icoImg1) {
		this.icoImg1 = icoImg1;
	}

	public File getIcoImg2() {
		return icoImg2;
	}

	public void setIcoImg2(File icoImg2) {
		this.icoImg2 = icoImg2;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
