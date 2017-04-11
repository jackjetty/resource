package com.traffic.wei.thread;

import com.traffic.util.CommonUtil;

 

public class FileThread extends Thread{
    private String picUrl;
    private String filePath; 
    public FileThread(String picUrl,String filePath){
    	super(); 
    	this.picUrl=picUrl;
    	this.filePath=filePath;
    }
    
    public void run() {
    	CommonUtil.getInstance().saveToLocalFile(picUrl , filePath); 
    	 
    	
    }      
} 