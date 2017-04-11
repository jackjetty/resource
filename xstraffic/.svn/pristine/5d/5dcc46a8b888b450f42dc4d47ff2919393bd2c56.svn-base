package com.traffic.web.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbPublicInfo;
import com.traffic.web.service.PublicInfoService;

@Controller("publicInfoAction")
public class PublicInfoAction {
	
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String publicInfo;
	private String title;
	private String publicTime;
	private Integer id;
	private String ids;
	private Integer codeId;
	private String publicType;
	private Integer thisId;
	private Integer nextId;
	private Integer thisIndex;
	private Integer nextIndex;
	private String description;
	private String radio;
	private String publicTypeName;
	
	private HashMap<String, Object> result;
	 
	private ArrayList<TbPublicInfo> re;
	
	private TbPublicInfo tb;
	

	@Autowired
	PublicInfoService publicInfoService;
	
	public String doPublicInfo(){
		return "success";
	}
	
	public String getPublicInfo(){
		result=publicInfoService.getPublicInfo(startTime, endTime, publicType, pageSize, pageIndex);
		return "success";
	}

	
	public String addPublicInfo(){
		Date now = new Date(); 
		TbPublicInfo pb=new TbPublicInfo();
		pb.setPublicInfo(publicInfo);
		pb.setPublicTime(now);
		pb.setTitle(title);
		pb.setStatus("启用");
		pb.setDescription(description);
		pb.setPublicType(publicType);
		result=publicInfoService.addPublicInfo(pb);
		pb.setPublicIndex(pb.getId());
		publicInfoService.toSetPublicIndex(pb);
		
		return "success";
	}
	
	public String updatePublicInfo(){
		TbPublicInfo pb=new TbPublicInfo();
		pb=publicInfoService.getPublicInfoById(id);
		pb.setTitle(title);
		pb.setPublicInfo(publicInfo);
		pb.setDescription(description);
		pb.setPublicType(publicType);
		result=publicInfoService.updatePublicInfo(pb);
		return "success";
	}
	
	public String removePublicInfo(){
		result=publicInfoService.removePublicInfo(ids);
		return "success";
	}
	
	public String getCodeInfo(){
		result=publicInfoService.getCodeInfo(codeId);
		return "success";
	}
	
	public String publicInfoStatus(){
		result=publicInfoService.publicInfoStatus(id);
		return "success";
	}
	
	public String getTestInfo() throws Exception{
		URL url = new URL("http://wscgs.sxga.gov.cn/wap/drvvio.do"); 
		URLConnection connection = url.openConnection(); 
		connection.setDoOutput(true); 
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1"); 
		out.write("sfzmhm=33060219861107303x&dabh=220101096879"); //这里组织提交信息 
		out.flush(); 
		out.close(); 
		//获取返回数据 
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
		String line = null; 
		StringBuffer content= new StringBuffer(); 
		while((line = in.readLine()) != null) 
		{ 
		   //line为返回值，这就可以判断是否成功、 
		    content.append(line); 
		} 
		in.close() ; 
         
		result=new HashMap<String, Object>();
		result.put("rows", content.toString());
		return "success";
	}
	
	public String publicInfoUpOrDown(){
		result=publicInfoService.publicInfoUpOrDown(thisId,thisIndex, nextId,nextIndex);
		return "success";
	}
	
	public String getCodeJsp(){
		
		return "success";
	}
	public String getAllCodeJsp(){
		re=publicInfoService.getAllCodeJsp(publicType, pageSize, pageIndex);
		return "success";
	}
	
	public String getCodeInfoJsp(){
		return "success";
	}
	
	public String getCodeInfoById(){
		tb=publicInfoService.getPublicInfoById(id);
		return "success";
	}
	
	public String getPublicInfoWB(){
		result=publicInfoService.getPublicInfoWB(pageSize, pageIndex);
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

	public String getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public void setPublicInfo(String publicInfo) {
		this.publicInfo = publicInfo;
	}

	public String getpublicInfo() {
		return publicInfo;
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

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getPublicType() {
		return publicType;
	}

	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}

	public Integer getThisId() {
		return thisId;
	}

	public void setThisId(Integer thisId) {
		this.thisId = thisId;
	}

	public Integer getNextId() {
		return nextId;
	}

	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}

	public Integer getThisIndex() {
		return thisIndex;
	}

	public void setThisIndex(Integer thisIndex) {
		this.thisIndex = thisIndex;
	}

	public Integer getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(Integer nextIndex) {
		this.nextIndex = nextIndex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public ArrayList<TbPublicInfo> getRe() {
		return re;
	}

	public void setRe(ArrayList<TbPublicInfo> re) {
		this.re = re;
	}

	public TbPublicInfo getTb() {
		return tb;
	}

	public void setTb(TbPublicInfo tb) {
		this.tb = tb;
	}

	public String getPublicTypeName() {
		return publicTypeName;
	}

	public void setPublicTypeName(String publicTypeName) {
		this.publicTypeName = publicTypeName;
	}
	
	
}
