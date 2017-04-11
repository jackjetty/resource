package com.rising.management.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileToByteUtil {
	public static byte[] getFileToByte(File file){
		ByteArrayOutputStream os1 = new ByteArrayOutputStream();  	
		try {
			FileInputStream fis1 = new FileInputStream(file);
	        byte[] temp1 = new byte[(int) file.length()];  
	        while ((fis1.read(temp1, 0, temp1.length)) != -1) {  
	            os1.write(temp1, 0, temp1.length);  
	        }
		    os1.close();  
	        fis1.close();  
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return os1.toByteArray();
	}
}
