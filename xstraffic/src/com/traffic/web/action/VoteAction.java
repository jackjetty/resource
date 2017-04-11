package com.traffic.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.traffic.pojo.TbElector;
import com.traffic.pojo.TbStudyInfo;
import com.traffic.pojo.TbVote;
import com.traffic.util.FileUpload;
import com.traffic.web.service.ElectorService;
import com.traffic.web.service.TenderService;
import com.traffic.web.service.VoteService;



@Controller("voteAction")
public class VoteAction {

	

	@Autowired
	VoteService voteService;
	
	@Autowired
	ElectorService electorService;
	
	@Autowired
	TenderService tenderService;

	private HashMap<String, Object> result;
	private String id;
	private String title;
	private String dir;
	// 封装上传文件域的属性
	private File imgFile;
	// 封装上传文件类型的属性
	private String imgFileContentType;
	// 封装上传文件名的属性
	private String imgFileFileName;
	private String rule;
	private Timestamp createTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private String status;
	private String user;
	private Integer count;
	private Integer type;
	private String content;
	private Integer pageIndex;
	private Integer pageSize;
	private String ids;
	private String voteId;
	private Integer number;
	private String name;
	private String introduce;
	private Integer votes;
	private Double percent;
	private String workUnit;
	private String openId;
	private String nickName;
	private String startDate;
	private String endDate;
	private String achievement;
	private String area;
	
	public String doVote() {
		return "success";
	}

	public String getVoteByPage() {
		result=voteService.getVoteByPage(pageIndex, pageSize, title, status);
		return "success";
	}
	
	public String addVote() throws Exception{
		TbVote vote=new TbVote();
		
		String uploadPath ="D:/xstraffic/vote";
		FileInputStream is = new FileInputStream(imgFile);
		File toFile = new File(uploadPath, imgFileContentType);
		if (!toFile.exists()) {
			toFile.mkdirs();
		}
		String toFile1 = toFile + "/" + String.valueOf(System.currentTimeMillis()) + imgFileFileName;
		FileOutputStream os = new FileOutputStream(toFile1);
		byte[] temp1 = new byte[(int) imgFile.length()];
		while ((is.read(temp1, 0, temp1.length)) != -1) {
			os.write(temp1, 0, temp1.length);
		}
		os.close();
		is.close();
		String address = toFile1;
		vote.setTitle(title);
		vote.setImage(address);
		vote.setRule(rule);
		vote.setStartTime(startTime);
		vote.setEndTime(endTime);
		vote.setStatus(status);
		vote.setUser(user);
		vote.setCount(count);
		vote.setType(type);
		vote.setContent(content);
		result=voteService.addVote(vote);
		return "success";
	}
	
	public String editVote() throws Exception{
		TbVote vote=new TbVote();
		
		if(imgFile!=null){
			String uploadPath ="D:/xstraffic/vote";
			FileInputStream is = new FileInputStream(imgFile);
			File toFile = new File(uploadPath, imgFileContentType);
			if (!toFile.exists()) {
				toFile.mkdirs();
			}
			String toFile1 = toFile + "/" + String.valueOf(System.currentTimeMillis()) + imgFileFileName;
			FileOutputStream os = new FileOutputStream(toFile1);
			byte[] temp1 = new byte[(int) imgFile.length()];
			while ((is.read(temp1, 0, temp1.length)) != -1) {
				os.write(temp1, 0, temp1.length);
			}
			os.close();
			is.close();
			String address = toFile1;
			vote.setImage(address);
		}else{
			vote.setImage("");
		}
		
		vote.setId(id);
		vote.setTitle(title);
		vote.setRule(rule);
		vote.setStartTime(startTime);
		vote.setEndTime(endTime);
		vote.setStatus(status);
		vote.setUser(user);
		vote.setCount(count);
		vote.setType(type);
		vote.setContent(content);
		result=voteService.editVote(vote);
		return "success";
	}
	
	public String deleteVotes() {
		result=voteService.deleteVotesByIds(ids);
		return "success";
	}
	
	public String doElector() {
		return "success";
	}
	
	public String getElectorByPage() {
		result=electorService.getElectorByPage(voteId, pageIndex, pageSize);
		return "success";
	}
	
	public String doVoteDetail() {
		return "success";
	}
	
	public String getTenderByPage() {
		result=tenderService.getTenderByPage(voteId, name, openId, nickName, startDate, endDate, pageIndex, pageSize);
		return "success";
	}
	
	public String addElector() throws Exception{
		TbElector elector=new TbElector();
		
		String uploadPath ="D:/xstraffic/elector";
		FileInputStream is = new FileInputStream(imgFile);
		File toFile = new File(uploadPath, imgFileContentType);
		if (!toFile.exists()) {
			toFile.mkdirs();
		}
		String toFile1 = toFile + "/" + String.valueOf(System.currentTimeMillis()) + imgFileFileName;
		FileOutputStream os = new FileOutputStream(toFile1);
		byte[] temp1 = new byte[(int) imgFile.length()];
		while ((is.read(temp1, 0, temp1.length)) != -1) {
			os.write(temp1, 0, temp1.length);
		}
		os.close();
		is.close();
		String address = toFile1;
		elector.setVoteId(voteId);
		elector.setImg(address);
		elector.setNumber(number);
		elector.setName(name);
		elector.setIntroduce(introduce);
		elector.setVotes(votes);
		elector.setPercent(percent);
		elector.setWorkUnit(workUnit);
		elector.setAchievement(achievement);
		elector.setArea(area);
		result=electorService.addElector(elector);
		return "success";
	}
	
	public String editElector() throws Exception{
		TbElector elector=new TbElector();
		if(imgFile!=null){
			String uploadPath ="D:/xstraffic/elector";
			FileInputStream is = new FileInputStream(imgFile);
			File toFile = new File(uploadPath, imgFileContentType);
			if (!toFile.exists()) {
				toFile.mkdirs();
			}
			String toFile1 = toFile + "/" + String.valueOf(System.currentTimeMillis()) + imgFileFileName;
			FileOutputStream os = new FileOutputStream(toFile1);
			byte[] temp1 = new byte[(int) imgFile.length()];
			while ((is.read(temp1, 0, temp1.length)) != -1) {
				os.write(temp1, 0, temp1.length);
			}
			os.close();
			is.close();
			String address = toFile1;
			elector.setImg(address);
		}else{
			elector.setImg("");
		}
		
		elector.setId(id);
		elector.setVoteId(voteId);
		elector.setNumber(number);
		elector.setName(name);
		elector.setIntroduce(introduce);
		elector.setVotes(votes);
		elector.setPercent(percent);
		elector.setWorkUnit(workUnit);
		elector.setAchievement(achievement);
		elector.setArea(area);
		result=electorService.editElector(elector);
		return "success";
	}
	
	public String deleteElector() {
		TbElector elector = new TbElector();
		elector.setId(id);
		result=electorService.deleteElector(elector);
		return "success";
	}
	
	
	
	public static String saveImage(String voteImageDir, File smallImage,String fileName) {
		
		return voteImageDir+FileUpload.fileUp(smallImage, voteImageDir, fileName);
		
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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


	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}


	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
	 
	 
}
