package com.traffic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
* <p>Title : FileUpload</p>
* <p>Description : 上传文件</p>
* <p>Company :杭州冉思科技</p> 
* @author : panlei
* @date : 2016年7月7日 上午9:50:45
 */
public class FileUpload {

	/**
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	/**
	 * 
	 * @Description: 文件下载
	 * @param @param urlString
	 * @param @param fileName   
	 * @return void  
	 * @throws
	 * @author panlei
	 * @date 2016年7月11日
	 */
	public static void downloadFile(String urlString,String filePath,String fileName){
		try{
			File file = new File(filePath, fileName);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			URL url = new URL(urlString);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream(); 
		 
			FileUtils.copyInputStreamToFile(in, file);
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	public static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	public static String fileUp(File file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getName().lastIndexOf(".") >= 0){
				extName = file.getName().substring(file.getName().lastIndexOf("."));
			}
			InputStream in = new FileInputStream(file);
			copyFile(in, filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	public static String fileUpImage(File file, String filePath, String fileName){
		try {
			InputStream in = new FileInputStream(file);
			copyFile(in, filePath, fileName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName;
	}
}
