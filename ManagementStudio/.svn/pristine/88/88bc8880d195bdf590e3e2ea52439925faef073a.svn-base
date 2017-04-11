package com.rising.management.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.AppVersion;
import com.rising.management.service.AppVersionService;

@Controller("appVersionAction")
public class AppVersionAction {
	private Integer versionCode;

	private String versionName;

	private String phoneType;

	private String couldUse;

	private String remark;

	private String appVIds;

	private Integer appVId;

	private Integer pageSize;

	private Integer pageIndex;

	private File ApkFile;

	private String ApkFileFileName;

	private String ApkSaveDir;
	
	private String APKUrl;

	private HashMap<String, Object> resultMap;

	@Autowired
	AppVersionService appVersionService;

	public String doAppVersion() {
		return "success";
	}

	public String getAppVersion() {
		resultMap = appVersionService.getAppVersionByPage(pageIndex, pageSize);
		return "success";
	}

	public String addAppVersion() {
		AppVersion a = new AppVersion();
		a.setCouldUse(couldUse);
		a.setPhoneType(phoneType);
		a.setRemark("使用");
		a.setVersionCode(versionCode);
		a.setVersionName(versionName);
		a.setPublishTime(new Date());
		a.setAPKUrl(APKUrl);
		File file = new File(ApkSaveDir);
		if (file.exists()) {
			File last = new File(ApkSaveDir + "/hbpay.apk");
			if (last.exists()) {
				String Time = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
						.format(new Date());
				last.renameTo(new File(ApkSaveDir + "/" + Time + "hbpay.apk"));
				try {
					FileUtils.copyFile(ApkFile, new File(ApkSaveDir
							+ "/hbpay.apk"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					FileUtils.copyFile(ApkFile, new File(ApkSaveDir
							+ "/hbpay.apk"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				file.mkdir();
				FileUtils.copyFile(ApkFile, new File(ApkSaveDir + "/hbpay.apk"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		resultMap = appVersionService.addAppVersion(a);
		return "success";
	}

	public String removeAppVersion() {
		resultMap = appVersionService.deleteByIds(appVIds);
		return "success";
	}

	public String updateAppVersion() {
		AppVersion a = new AppVersion();
		a.setAppVId(appVId);
		a.setCouldUse(couldUse);
		a.setPhoneType(phoneType);
		a.setVersionCode(versionCode);
		a.setVersionName(versionName);
		a.setRemark(remark);
		a.setPublishTime(new Date());
		a.setAPKUrl(APKUrl);
		resultMap = appVersionService.updateAppVersion(a);
		return "success";
	}

	public String exchangeStatus() {
		resultMap = appVersionService.exchangeStatusById(appVId);
		return "success";
	}

	public Integer getAppVId() {
		return appVId;
	}

	public void setAppVId(Integer appVId) {
		this.appVId = appVId;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getCouldUse() {
		return couldUse;
	}

	public String getAppVIds() {
		return appVIds;
	}

	public String getApkSaveDir() {
		return ApkSaveDir;
	}

	@Value("#{propertiesReader[ApkSaveDir]}")
	public void setApkSaveDir(String apkSaveDir) {
		ApkSaveDir = apkSaveDir;
	}

	public void setAppVIds(String appVIds) {
		this.appVIds = appVIds;
	}

	public void setCouldUse(String couldUse) {
		this.couldUse = couldUse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public File getApkFile() {
		return ApkFile;
	}

	public void setApkFile(File apkFile) {
		ApkFile = apkFile;
	}

	public String getApkFileFileName() {
		return ApkFileFileName;
	}

	public void setApkFileFileName(String apkFileFileName) {
		ApkFileFileName = apkFileFileName;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getAPKUrl() {
		return APKUrl;
	}

	public void setAPKUrl(String aPKUrl) {
		APKUrl = aPKUrl;
	}

}
