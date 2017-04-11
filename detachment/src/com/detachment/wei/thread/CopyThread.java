package com.detachment.wei.thread;

import com.detachment.util.CommonUtil;

 

public class CopyThread extends Thread{
    private String fromPath;
    private String toPath;
    public CopyThread(String fromPath,String toPath){
    	super();
    	this.fromPath=fromPath;
    	this.toPath=toPath;
    }
    public void run() {
    	CommonUtil.getInstance().copy(fromPath , toPath); 
    }      
} 