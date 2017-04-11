package com.rising.mobilepayment;
import java.io.File;
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
  
public class Test64Bit {  
public static void main(String[] args) {  
// 测试从Base64编码转换为图片文件  
  //library C:\Program Files\Java\jre6\lib\rt.jar
	
 // String strImg = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDABMNDxEPDBMREBEWFRMXHTAfHRsbHTsqLSMwRj5KSUU+RENNV29eTVJpU0NEYYRiaXN3fX59S12Jkoh5kW96fXj/2wBDARUWFh0ZHTkfHzl4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHj/wAARCAAfACEDAREAAhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAQDBQb/xAAjEAACAgICAgEFAAAAAAAAAAABAgADBBESIRMxBSIyQXGB/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/APawEBAQEBAgy8i8ZTVV3UY6V1eU2XoWDDZB19S646Gz39w9fkKsW1r8Wm2yo1PYis1be0JG9H9QNYCAgc35Cl3yuVuJZl0cB41rZQa32dt2y6OuOiOxo61vsLcVblxaVyXD3hFFjL6La7I/sDWAgICAgICB/9k=";  
   //GenerateImage(strImg, "D:\\wangyc.jpg");  
  
// 测试从图片文件转换为Base64编码  
System.out.println(GetImageStr("D:\\qu_05.jpg").trim());  
  
  }  
  
public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
 
	byte[] data = null;  
  
// 读取图片字节数组  
try {  
InputStream in = new FileInputStream(imgFilePath);  
data = new byte[in.available()];  
in.read(data);  
in.close();  
} catch (IOException e) {  
e.printStackTrace();  
}  
  
// 对字节数组Base64编码  
BASE64Encoder encoder = new BASE64Encoder();  
return encoder.encode(data);// 返回Base64编码过的字节数组字符串  
}  
  
public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片  
if (imgStr == null) // 图像数据为空  
return false;  
BASE64Decoder decoder = new BASE64Decoder();  
try {  
// Base64解码  
byte[] bytes = decoder.decodeBuffer(imgStr);  
for (int i = 0; i < bytes.length; ++i) {  
if (bytes[i] < 0) {// 调整异常数据  
bytes[i] += 256;  
}  
}  
// 生成jpeg图片  
OutputStream out = new FileOutputStream(imgFilePath);  
out.write(bytes);  
out.flush();  
out.close();  
return true;  
} catch (Exception e) {  
return false;  
}  
}  
}  