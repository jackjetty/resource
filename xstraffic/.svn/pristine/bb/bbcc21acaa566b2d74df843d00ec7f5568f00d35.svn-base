package com.traffic.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.traffic.pojo.TbWarningTone;
import com.traffic.web.service.WarningToneService;

@Controller("warningToneAction")
@SuppressWarnings("serial")
public class WarningToneAction extends ActionSupport {
	private Integer pageSize;
	private Integer pageIndex;
	private String trafficIndex;
	private String voiceStatus;
	private String voiceType;
	private String ids;
	// 封装上传文件域的属性
	private File voice;
	// 封装上传文件类型的属性
	private String voiceContentType;
	// 封装上传文件名的属性
	private String voiceFileName;
	// 接受依赖注入的属性
	private String savePath;
	private String voiceInfo;
	private Integer id;
	//@Value("${voicePath}") 
	private String uploadPath;
	private String voiceLocalStores;
	private HashMap<String, Object> result;
	@Autowired
	WarningToneService warningToneService;

	public String doWarningTone() {

		return "success";
	}

	public String getWarningTone() {
		result = warningToneService.getWarningTone(pageSize, pageIndex,
				trafficIndex, voiceStatus);
		return "success";
	}

	public String addWarningTone() throws Exception {
		TbWarningTone tt = new TbWarningTone();
		tt.setTrafficIndex(trafficIndex);
		tt.setVoiceRemark(voiceInfo);
		tt.setVoiceType(voiceType);
		tt.setVoiceStatus("禁用");
		FileInputStream is = new FileInputStream(voice);
		File toFile = new File(uploadPath, this.getVoiceType());
		if (!toFile.exists()) {
			toFile.mkdirs();
		}
		String toFile1 = toFile + "/" + this.getVoiceFileName();
		FileOutputStream os = new FileOutputStream(toFile1);
		byte[] temp1 = new byte[(int) voice.length()];
		while ((is.read(temp1, 0, temp1.length)) != -1) {
			os.write(temp1, 0, temp1.length);
		}
		os.close();
		is.close();
		tt.setVoiceLocalStore(toFile1);
		result = warningToneService.addWarningTone(tt);
		return "success";
	}

	public String updateWarningTone() throws Exception {
		TbWarningTone tt = new TbWarningTone();
		tt.setId(id);
		tt.setTrafficIndex(trafficIndex);
		tt.setVoiceRemark(voiceInfo);
		tt.setVoiceType(voiceType);
		tt.setVoiceStatus("禁用");
		FileInputStream is = new FileInputStream(voice);
		File toFile = new File(uploadPath, this.getVoiceType());
		if (!toFile.exists()) {
			toFile.mkdirs();
		}
		String toFile1 = toFile + "/" + this.getVoiceFileName();
		FileOutputStream os = new FileOutputStream(toFile1);
		byte[] temp1 = new byte[(int) voice.length()];
		while ((is.read(temp1, 0, temp1.length)) != -1) {
			os.write(temp1, 0, temp1.length);
		}
		os.close();
		is.close();
		tt.setVoiceLocalStore(toFile1);
		result = warningToneService.updateWarningTone(tt);
		return "success";
	}

	public String deleteWarningTone() {
		String[] vss = voiceLocalStores.split(",");
		for (String vs : vss) {
			if (!"".equals(vs)) {
				File file = new File(vs);
				if (!file.isDirectory()) {
					file.delete();
				}
			}

		}
		result = warningToneService.removeWarningTone(ids);
		return "success";
	}
	
	public String getAllLs(){
		result = warningToneService.getAllLs();
		return "success";
	}

	public String changeStatus() {
		result = warningToneService.changeStatus(id, trafficIndex, voiceStatus);
		return "success";
	}

	public void viewVoice() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// pic为读取到图片的存储路径(数据库中存储的字段值)
		String pic = new String(
				(request.getParameter("picPath")).getBytes("ISO-8859-1"),
				"utf-8");
		File picFile = new File(pic);
		FileInputStream is = new FileInputStream(picFile.getAbsolutePath());
		int i = is.available(); // 得到文件大小
		byte data[] = new byte[i];
		is.read(data); // 读数据
		is.close();
		response.setContentType("audio/x-mpeg"); // 设置返回的文件类型*/
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		toClient.write(data); // 输出数据
		toClient.close();
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

	public String getVoicePath() {
		result = warningToneService.getVoicePath();
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

	public File getVoice() {
		return voice;
	}

	public void setVoice(File voice) {
		this.voice = voice;
	}

	public String getTrafficIndex() {
		return trafficIndex;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public void setTrafficIndex(String trafficIndex) {
		this.trafficIndex = trafficIndex;
	}

	public String getVoiceStatus() {
		return voiceStatus;
	}

	public void setVoiceStatus(String voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getVoiceType() {
		return voiceType;
	}

	public void setVoiceType(String voiceType) {
		this.voiceType = voiceType;
	}

	public String getVoiceInfo() {
		return voiceInfo;
	}

	public void setVoiceInfo(String voiceInfo) {
		this.voiceInfo = voiceInfo;
	}

	public String getVoiceContentType() {
		return voiceContentType;
	}

	public void setVoiceContentType(String voiceContentType) {
		this.voiceContentType = voiceContentType;
	}

	public String getVoiceFileName() {
		return voiceFileName;
	}

	public void setVoiceFileName(String voiceFileName) {
		this.voiceFileName = voiceFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getVoiceLocalStores() {
		return voiceLocalStores;
	}

	public void setVoiceLocalStores(String voiceLocalStores) {
		this.voiceLocalStores = voiceLocalStores;
	}

}
