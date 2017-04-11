package com.traffic.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.traffic.pojo.TbProblems;
import com.traffic.pojo.TbStudyInfo;
import com.traffic.util.ExcelUtil;
import com.traffic.web.service.ProblemsService;
import com.traffic.web.service.StudyInfoService;
import com.traffic.web.service.UserService;

@Controller("problemsAction")
public class ProblemsAction {
	private String idCard;
	private String phoneNumber;
	private String resultInfo;
	private String studyNumber;
	private Integer studyTypeNum;
	
	private Integer pageSize;
	private Integer pageIndex;
	private String problem;
	private Integer score;
	
	private File problemFile;
	
	private String problemIds;
	
	private String openId;
	private HashMap<String,Object> result;
	
	private ArrayList<TbProblems> re;
	private ArrayList<TbStudyInfo> tbStudyInfos;
	
	@Autowired
	ProblemsService problemsService;
	@Autowired
	StudyInfoService studyInfoService;
	@Autowired
	UserService userService;
	
	public String getOnlineStudy(){
		return "success";
	}
	
	public String getProblemJsp(){
		re=problemsService.getProblemsByNum(studyTypeNum);
		return "success";
	}
	
	public String getStudyPhone(){
		return "success";
	}
	
	public String saveStudyInfo(){
		TbStudyInfo pb=new TbStudyInfo();
		String number=studyTypeNum+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		pb.setStudyNumber(number);
		pb.setIdCard(idCard);
		pb.setPhoneNumber(phoneNumber);
		pb.setResultInfo(resultInfo);
		pb.setScore(score);
		pb.setOpenId(openId);
		result=studyInfoService.addStudyInfo(pb);
		result.put("studyNumber", number);
		return "success";
	}
	
	public void studyImages(){
		try {
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
			response.setContentType("jpg/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} catch (Exception e) {}
		

	}
	
	
	public String doOnlineStudy(){
		return "success";
	}
	
	public String getProblems(){
		result=problemsService.getProblems(problem, pageSize, pageIndex);
		return "success";
	}
	
	public String addProblemFile(){
		 try {
             // 创建对Excel工作簿文件的引用­
              HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(problemFile));
              // 在Excel文档中，第一张工作表的缺省索引是0
             // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
              HSSFSheet sheet = wookbook.getSheet("Sheet1");
              //获取到Excel文件中的所有行数­
              int rows = sheet.getPhysicalNumberOfRows();
              //遍历行­
            for (int i = 1; i < rows; i++) {
                   // 读取左上端单元格­
                  HSSFRow row = sheet.getRow(i);
                   // 行不为空­
                   if (row != null) {
                          //获取到Excel文件中的所有的列 
                          TbProblems pb = new TbProblems();
                          if(row.getCell(0)==null){
                        	  pb.setProblem("");
                          }else{
                        	  pb.setProblem(row.getCell(0).toString()); 
                          }
                          if(row.getCell(1)==null){
                        	  pb.setResultA("");
                          }else{
                        	  pb.setResultA(row.getCell(1).toString());
                          }
                          if(row.getCell(2)==null){
                        	  pb.setResultB("");
                          }else{
                        	  pb.setResultB(row.getCell(2).toString());
                          }
                          if(row.getCell(3)==null){
                        	  pb.setResultC("");
                          }else{
                        	  pb.setResultC(row.getCell(3).toString()); 
                          }
                          if(row.getCell(4)==null){
                        	  pb.setResultD("");
                          }else{
                        	  pb.setResultD(row.getCell(4).toString()); 
                          }
                          if(row.getCell(5)==null){
                        	  pb.setAnswer("");
                          }else{
                        	  pb.setAnswer(row.getCell(5).toString()); 
                          }
                          if(row.getCell(6)==null){
                        	  pb.setImageUrl("");
                          }else{
                        	  pb.setImageUrl(row.getCell(6).toString()); 
                          }
                          if(row.getCell(7)==null){
                        	  pb.setChoiceType("");
                          }else{
                        	  pb.setChoiceType(row.getCell(7).toString().substring(0, 1)); 
                          }
                          result=problemsService.addProblem(pb);   
                   }
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result.put("respCode", -1);
			result.put("respInfo", "导入时出现异常");
		} catch (IOException e) {
			e.printStackTrace();
			result.put("respCode", -1);
			result.put("respInfo", "导入时出现异常");
		}
		return "success";
	}
	
	public String doStudyInfo(){
		return "success";
	}
	
	public String getStudyInfos(){
		result=studyInfoService.getStudyInfo(studyNumber, idCard,score, pageSize, pageIndex);
		return "success";
	}
	
	public void exportProblem(){
		ArrayList<TbProblems> tps=new ArrayList<TbProblems>();
		if(problemIds==null || "".equals(problemIds)){
			tps=problemsService.getAllProblems();
		}else{
			tps=problemsService.getProblemsByIds(problemIds);
		}
		String filename = null;
		try {
			filename = new String("在线学习题目".getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filename + ".xls");
		try {
			ExcelUtil.exportProblem(response.getOutputStream(), tps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public String getPhoneNumberByOpenId(){
		result=userService.getPhoneNumberByOpenId(openId);
		return "success";
	}
	
	public String doStudyHistory(){
		return "success";
	}
	
	public String getStudyInfoJsp(){
		tbStudyInfos=studyInfoService.getStudyInfoJsp(idCard,openId, pageSize, pageIndex);
		return "success";
	}
	
	
	

	public ArrayList<TbProblems> getRe() {
		return re;
	}

	public void setRe(ArrayList<TbProblems> re) {
		this.re = re;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
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

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public File getProblemFile() {
		return problemFile;
	}

	public void setProblemFile(File problemFile) {
		this.problemFile = problemFile;
	}

	public String getStudyNumber() {
		return studyNumber;
	}

	public void setStudyNumber(String studyNumber) {
		this.studyNumber = studyNumber;
	}

	public Integer getStudyTypeNum() {
		return studyTypeNum;
	}

	public void setStudyTypeNum(Integer studyTypeNum) {
		this.studyTypeNum = studyTypeNum;
	}

	public String getProblemIds() {
		return problemIds;
	}

	public void setProblemIds(String problemIds) {
		this.problemIds = problemIds;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public ArrayList<TbStudyInfo> getTbStudyInfos() {
		return tbStudyInfos;
	}

	public void setTbStudyInfos(ArrayList<TbStudyInfo> tbStudyInfos) {
		this.tbStudyInfos = tbStudyInfos;
	}

	
	
	
	
	
	
}
