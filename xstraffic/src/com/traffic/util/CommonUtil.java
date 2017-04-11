package com.traffic.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
 
public    class CommonUtil{
	private static CommonUtil commonUtil;  
	
	public static CommonUtil getInstance(){  
        if(commonUtil == null){  
        	commonUtil = new CommonUtil();  
        }  
        return commonUtil;  
    }  
	public static void main(String[] args){
		System.out.println(isCarNumber("浙b12346"));
	}
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	public static boolean isCarNumber(String str){
		//^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{3}
		   String vehicleNoStyle = "^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$";
		   Pattern pattern = Pattern.compile(vehicleNoStyle,Pattern.CASE_INSENSITIVE);
		   Matcher matcher = pattern.matcher(str);
		   if (matcher.matches()) {
				return true;
			}else {
			return false;
			}  
	}
	public static boolean isMobilePhone(String str) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-1])|(15[7-9])|(183)|(18[7-8]))\\d{8}$");
		pattern = Pattern.compile("^\\d{11}$");
		Matcher matcher = pattern.matcher(str); 
		if (matcher.matches()) {
		return true;
		}else {
		return false;
		}  
	  }
	public static String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null; 
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); //to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public  static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	} 
	public static String   getDateForm() { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date()); 
    }
	public static String   getDateForm(Date date) { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date); 
    }
	public static String   getDateForm(String format) { 
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date()); 
    }
	public static String getDateForm(Object obj, String format) {
		if (obj == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format((Date) obj);
	}
	public static int  getCurrentNum(String currentStr,String prefixStr){
		Pattern pattern;
		Matcher matcher;
		pattern=Pattern.compile("^"+prefixStr+"(0*)(\\d+)$");
		int currentNum=0;
		matcher=pattern.matcher(currentStr);
		if(matcher.find()){
			currentNum=Integer.parseInt(matcher.group(2));   
	    }
		return currentNum; 
	}
	public  static boolean isExist(String filePath){
		filePath=CommonUtil.trim(filePath);
		if(filePath.equalsIgnoreCase(""))
			return false;
		File file=new File(filePath);
		return file.exists();
	}
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public   boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	} 
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public    boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
	 public   void  toFileDir(File dirFrom,File dirTo,File file) {   
        File[] files = file.listFiles();  
        
        for (File f : files) {   
             String tempfrom = f.getAbsolutePath();   
             String tempto = tempfrom.replace(dirFrom.getAbsolutePath(),   
                     dirTo.getAbsolutePath()); // 后面的路径 替换前面的路径名   
            if (f.isDirectory()) {   
                 File tempFile = new File(tempto);   
                 tempFile.mkdirs();   
                 toFileDir(dirFrom,dirTo,f);   
             } else {    
                 int endindex = tempto.lastIndexOf("\\");// 找到"/"所在的位置   
                 String mkdirPath = tempto.substring(0, endindex);   
                 File tempFile = new File(mkdirPath);   
                 tempFile.mkdirs();// 创建立文件夹    
                 copy(tempfrom, tempto);  
                 deleteFile(tempfrom);
             }   
         } 
        deleteDirectory(dirFrom.getAbsolutePath());
     }   
    /**
      * 封装好的文件拷贝方法
      */  
	 
	 // 复制文件
	    public   void copyFile(String sourcePath, String targetPath)   {
	    	
	    	File sourceFile=new File(sourcePath);
	    	if(!sourceFile.exists()) return;
	    	File targetFile=new File(targetPath);
	    	File toParentFile=targetFile.getParentFile() ;
			if(!toParentFile.exists()){
				toParentFile.mkdirs();
			}
	    	
	    	BufferedInputStream inBuff = null;
	        BufferedOutputStream outBuff = null;
	        try {
	            // 新建文件输入流并对它进行缓冲
	            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

	            // 新建文件输出流并对它进行缓冲
	            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

	            // 缓冲数组
	            byte[] b = new byte[1024 * 5];
	            int len;
	            while ((len = inBuff.read(b)) != -1) {
	                outBuff.write(b, 0, len);
	            }
	            // 刷新此缓冲的输出流
	            outBuff.flush();
	        } catch(Exception ex){
	      
	        }
	       finally { 
	            // 关闭流
	            if (inBuff != null)
					try {
						inBuff.close();
						inBuff=null;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            if (outBuff != null)
					try {
						outBuff.close();
						outBuff=null;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        }
	    } 
	 
    public   void copy(String fromPath, String toPath) {
    	
    	File fromFile=new File(fromPath);
    	if(!fromFile.exists()) return;
    	File toFile=new File(toPath);
    	File toParentFile=toFile.getParentFile() ;
		if(!toParentFile.exists()){
			toParentFile.mkdirs();
		} 
        try {   
            InputStream in = new FileInputStream(fromPath);   
            OutputStream out = new FileOutputStream(toPath);    
            byte[] buff = new byte[1024];   
            int len = 0;   
            while ((len = in.read(buff)) != -1) {   
                 out.write(buff, 0, len);   
             }   
             in.close();   
             out.close(); 
             in=null;
             out=null; 
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
         } catch (IOException e) {   
             e.printStackTrace();   
         }   
     }   
	 
	public  boolean saveToLocalFile(String url,String fileStr){ 
		File file=new File(fileStr); 
		File dirFile= file.getParentFile(); 
		if(!dirFile.exists()){
			dirFile.mkdirs();
		} 
		try {
			new HttpKit().downloadNet(url,new File(fileStr));
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return true; 
	} 
	 
	public static String trim(Object obj) {
		if (obj == null) {
			return "";
		}
		return trim(obj.toString());
	}

	 
	public static String trim(String str) {
        if (str == null) {
            return "";
        }
        try {
            str = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception ex) {
        } 
        return str.trim();
    } 
	public static boolean  isInteger(String str){
		 
		Pattern pattern;
		Matcher matcher; 
		pattern=Pattern.compile("^(\\+|-){0,1}\\d+$");
		matcher=pattern.matcher(str); 
	    return matcher.matches();
	} 
	
	 
	public    String saveToLocalPic(String url,String fileStr){ 
		File file=new File(fileStr);
		
		File dirFile= file.getParentFile(); 
		if(!dirFile.exists()){
			dirFile.mkdirs();
		} 
		try {
			new HttpKit().downloadNet(url,new File(fileStr));
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return fileStr;
		
	}
	/**
	 * @author tinker
	 * @create 2014年9月17日
	 * @return 当前日期 例如 2014-09-17
	 */
	public static String getCurrentDateStrForm() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * @author tinker
	 * @create 2014年9月17日
	 * @return 6天前日期 例如 2014-09-11
	 */
	public static String getBeforeWeekDateStrForm() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -6);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	 
	
}