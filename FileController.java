package com.rising.admin.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rising.admin.rs.FileUploadRs;
import com.rising.admin.util.CommonUtil;
import com.rising.admin.util.DateUtil;
import com.rising.admin.util.FileUtil;
 

 
@Controller 
@RequestMapping(value="/file")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileController    {
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Value("${image.root}") 
	protected String  IMAGEROOT;
	
	@Value("${image.url}") 
	protected String  IMAGEURL; 
	
	
	
	
	@RequestMapping("/upload")
    public ResponseEntity<Map<String, String>>  uploadPic(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value = "imgfile", required=true) MultipartFile imgFile)   {
        // 设置响应类型为json并设置响应编码为utf-8
		response.setCharacterEncoding("utf-8");
       // response.setContentType("text/html");
        Map<String, String> retMap = null ;
        
        String originFileName = imgFile.getOriginalFilename();
        
        //response.getWriter().write("上传成功");
		File dirFile=new File(IMAGEROOT,DateUtil.getDateForm());
		if(!dirFile.exists())
		     dirFile.mkdirs();
		String ext = FileUtil.getExt(imgFile.getOriginalFilename());  
		String imageName=CommonUtil.getImageName()+"."+ext;
		String imagePath=DateUtil.getDateForm() +"/"+imageName;
		//File.separator
		retMap=new HashMap<String, String>();
        try {
 
                //这里使用Apache的FileUtils方法来进行保存 
                FileUtils.copyInputStreamToFile(imgFile.getInputStream(),
                     new File(dirFile.getAbsolutePath(), imageName));
                retMap.put("code", "0");
                retMap.put("info","");
                retMap.put("imgurl", IMAGEURL+imagePath);
                retMap.put("imgpath", imagePath); 
 
            } catch (IOException e) { 
            	logger.error("文件上传失败 {}", e.getMessage());
            	retMap.put("code", "1");
                retMap.put("info","文件上传失败！");
            	 
	 
	    }   
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return new ResponseEntity<Map<String, String>>(retMap, headers, HttpStatus.OK);
    } 
	
	/*
	@RequestMapping(value="/upload")
	@ResponseBody
	public    Object  upload(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value = "imgfile", required=true) MultipartFile imgFile )
	{ 
		 
		
		String originFileName = imgFile.getOriginalFilename();
		FileUploadRs  fileUploadRs=new FileUploadRs();
		fileUploadRs.setCode(0); 
		
		fileUploadRs.setInfo("");
		File dirFile=new File(IMAGEROOT,DateUtil.getDateForm());
		if(!dirFile.exists())
		     dirFile.mkdirs();
		String ext = FileUtil.getExt(imgFile.getOriginalFilename());  
		String imageName=CommonUtil.getImageName()+"."+ext;
		String imagePath=DateUtil.getDateForm() +"/"+imageName;
		//File.separator
		
        try {
 
                //这里使用Apache的FileUtils方法来进行保存 
                FileUtils.copyInputStreamToFile(imgFile.getInputStream(),
                     new File(dirFile.getAbsolutePath(), imageName));
                
                fileUploadRs.setImgurl(IMAGEURL+imagePath);
                fileUploadRs.setImgpath(imagePath);
 
            } catch (IOException e) { 
            	logger.error("文件上传失败 {}", e.getMessage());
            	fileUploadRs.setCode(1);
            	fileUploadRs.setInfo("文件上传失败！");   
	 
	    }  
		return fileUploadRs;
	}*/
	
	
	
	
	
	@RequestMapping(value="/image.jpg",method = RequestMethod.GET)
	public   void image(HttpServletRequest request,HttpServletResponse response,Model model
			,@RequestParam(value="imgpath", required=true) String imgPath )
	{  
		 
		FileInputStream is=null;
		OutputStream outputStream=null;
		
		try{
			/*String pic = new String(
					(request.getParameter("picPath")).getBytes("ISO-8859-1"),
					"utf-8");*/
			File picFile = new File(IMAGEROOT+imgPath);
			is = new FileInputStream(picFile.getAbsolutePath());
			response.setContentType("image/jpeg"); 
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(picFile.getName().getBytes("gb2312"), "ISO8859-1"));
			
			
			final int MAX_BYTE = 6000;
			long streamTotal = 0;  //接受流的容量
			int streamNum = 0;  //流需要分开的数量
			int leave = 0;  //文件剩下的字符数
			byte[] inOutb;  //byte数组接受文件的数据
			//通过available方法取得流的最大字符数
			streamTotal = is.available();
			//取得流文件需要分开的数量
			streamNum = (int)Math.floor(streamTotal/MAX_BYTE);
			//分开文件之后,剩余的数量
			leave = (int)streamTotal % MAX_BYTE;
			
			
			response.setHeader("Content_Length",new Long(streamTotal).toString());
			outputStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			
			//文件的容量大于60Mb时进入循环
			if (streamNum > 0) {
					for(int i = 0; i < streamNum; ++i){
					inOutb = new byte[MAX_BYTE];
					//读入流,保存在byte数组
					is.read(inOutb, 0, MAX_BYTE);
					outputStream.write(inOutb);  //写出流
					//outputStream.flush();  //更新写出的结果
			      }
			}
			
			
			//写出剩下的流数据
			inOutb = new byte[leave];
			is.read(inOutb, 0, leave);
			outputStream.write(inOutb);
			outputStream.flush(); 
			
		}catch(Exception ex){
			//ex.printStackTrace();
		}
		finally{
			if(is!=null){
				try{
					is.close();
					 
				}catch(Exception ex){
				}
				is=null;
			}
			if(outputStream!=null){
				try{
					outputStream.close(); 
					 
					
				}catch(Exception ex){
				}
				
				outputStream=null;
			}
			
		}
		
	}
}