package com.detachment.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays; import java.util.Date;
import java.util.HashMap; 
import java.util.Random; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller("fileUploadAction")
public class FileUploadAction{ 
	Log log = LogFactory.getLog(FileUploadAction.class);
	
	public String dir;
	public File imgFile; 
	private String imgFileFileName; 
	
	@Value("${root.dir}") 
	protected String saveDir;
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
	//请求 
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	//文件保存目录路径 
	//文件保存目录URL 
	//String saveUrl ="E:/JAVA/test/"; //定义允许上传的文件扩展名 
	String savePath = saveDir+"/html";
	HashMap<String, String> extMap = new HashMap<String, String>(); 
	extMap.put("image", "gif,jpg,jpeg,png,bmp"); 
	//最大文件大小 
	long maxSize = 3145728; 
	if(!ServletFileUpload.isMultipartContent(request)){ 
		out.println(getError("请选择文件。"));
        return null;
	} 
	//检查目录 
	File uploadDir = new File(savePath); 
	if(!uploadDir.isDirectory()){ 
		//out.println(getError("上传目录不存在。"));
		uploadDir.mkdirs();
		//return null;
		} 
	//检查目录写权限 
	if(!uploadDir.canWrite()){ 
		out.println(getError("上传目录没有写权限。"));
        return null;
		} 
	String dirName = dir; 
	if (dirName == null) { 
		dirName = "image"; 
		} 
	if(!extMap.containsKey(dirName)){ 
		out.println(getError("目录名不正确。"));
        return null;
		} 
	//创建文件夹 
	savePath += "/"+dirName + "/"; 
		File saveDirFile = new File(savePath); 
		if (!saveDirFile.exists()) { 
			saveDirFile.mkdirs(); 
			} 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		String ymd = sdf.format(new Date()); 
		savePath += ymd + "/"; 
		File dirFile = new File(savePath); 
		if (!dirFile.exists()) { 
			dirFile.mkdirs(); 
			} 
		if(imgFile != null && !imgFile.toString().equals("")){ 
			long fileSize = imgFile.length(); 
			if(fileSize > maxSize){ 
				out.println(getError("上传文件大小超过限制。"));
		        return null;
				} 
			//检查扩展名 
			String fileExt = imgFileFileName.substring(imgFileFileName.lastIndexOf(".") + 1).toLowerCase(); 
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){ 
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
		        return null;
				} 
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt; 
			File uploadedFile = new File(savePath, newFileName); 
			//获取文件输出流 
			FileOutputStream fos = new FileOutputStream(uploadedFile); 
			//获取内存中当前文件输入流 
			InputStream in = new FileInputStream(imgFile); 
			byte[] buffer = new byte[1024]; 
			int num = 0; 
			while ((num = in.read(buffer)) > 0) { 
				fos.write(buffer, 0, num);
			}
			in.close(); 
			fos.close(); 
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", "viewImages.action?picPath="+savePath + newFileName);
			log.debug(obj);
			out.println(obj.toJSONString());
			log.debug("上传图片:[" + uploadedFile.getName() + "]" + ">>>[" + newFileName + "]成功");
			}else{ 
				out.println(getError("上传的文件不存在!"));
		        return null;
				} 
		
		return null; 
	} 
	
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	
	public void viewImages() throws Exception {
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
		//response.setContentType("jpg/*"); // 设置返回的文件类型
		response.setContentType("jpg");  
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(picFile.getName().getBytes("gb2312"), "ISO8859-1"));
		response.setHeader("Content_Length",new Integer(i).toString());
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		toClient.write(data); // 输出数据
		toClient.close();

	}
	
	
	public String getDir() {
		return dir; 
	} 
	public void setDir(String dir) {
		this.dir = dir; 
	} 
	public File getImgFile() {
		return imgFile; 
	} 
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile; 
	} 
	public String getImgFileFileName() { 
		return imgFileFileName; 
	} 
	public void setImgFileFileName(String imgFileFileName) {
			this.imgFileFileName = imgFileFileName; 
	}





	
	

	


}