package com.traffic.util;


import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.traffic.pojo.TbProblems;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbDoAnswerVo;
import com.traffic.pojovo.TbFeedBackVo;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	/**
	 * 生成EXCEL ，并写入数据。
	 * 
	 * @param fileName
	 * @param data
	 * @param columns
	 * @param heads
	 */
	
	public static void exportClassroom(OutputStream os,ArrayList<TbAccidentVo> list) {  
		WritableWorkbook wbook=null;
		  try {  
		   wbook = Workbook.createWorkbook(os); // 建立excel文件  
		   WritableSheet wsheet = wbook.createSheet("交通事故信息记录", 0); // 工作表名称  
		   //设置Excel字体  
		   WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,  
		    WritableFont.BOLD, false,  
		   jxl.format.UnderlineStyle.NO_UNDERLINE,  
		   jxl.format.Colour.BLACK);  
		   WritableCellFormat titleFormat = new WritableCellFormat(wfont);  
  
		   String[] title = { "事故编号","事故时间","微信昵称", "用户类别","手机号","始发地址","纬度","经度","图片信息","语音信息","处理状态","受理人"};//如果还有字段的话,以此类推  
		   // 设置Excel表头  
		   for (int i = 0; i < title.length; i++) {  
		    Label excelTitle = new Label(i, 0, title[i], titleFormat);  
		    wsheet.addCell(excelTitle);  
		   }  
		   int c = 1; // 用于循环时Excel的行号  
		  for(TbAccidentVo pm:list){
			  	Label content1 = new Label(0, c,  pm.getAccidentId());  
			  	Label content2 = new Label(1, c,  pm.getReportTime()); 
			  	Label content3 = new Label(2, c,  pm.getNickName()); 
			  	Label content4 = new Label(3, c,  pm.getReporterType()); 
			  	Label content5 = new Label(4, c,  pm.getReportPhoneNumber()); 
			  	Label content6 = new Label(5, c,  pm.getAddress()); 
			  	Label content7 = new Label(6, c,  pm.getLocationX()); 
			  	Label content8 = new Label(7, c,  pm.getLocationY()); 
			  	Label content9 = new Label(8, c,  pm.getPicInfo()); 
			  	Label content10 = new Label(9, c,  pm.getVoiceInfo()); 
			  	Label content11 = new Label(10, c,  pm.getAccidentState());
			  	Label content12 = new Label(11, c,  pm.getAccepter());
			    //如果还有的话,以此类推  
			    wsheet.addCell(content1);  
			    wsheet.addCell(content2);
			    wsheet.addCell(content3);
			    wsheet.addCell(content4);
			    wsheet.addCell(content5);
			    wsheet.addCell(content6);
			    wsheet.addCell(content7);
			    wsheet.addCell(content8);
			    wsheet.addCell(content9);
			    wsheet.addCell(content10);
			    wsheet.addCell(content11);
			    wsheet.addCell(content12);
			    //如果还有的话,以此类推  
			    c++;  
		  }

		   wbook.write(); // 写入文件  
		   wbook.close();  
		   os.close();  
		   //System.out.println("导入成功！");  
		  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }  
		 }  
	public static void exportClassroom1(OutputStream os,ArrayList<TbFeedBackVo> list) {  
		WritableWorkbook wbook=null;
		  try {  
		   wbook = Workbook.createWorkbook(os); // 建立excel文件  
		   WritableSheet wsheet = wbook.createSheet("用户反馈信息记录", 0); // 工作表名称  
		   //设置Excel字体  
		   WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,  
		    WritableFont.BOLD, false,  
		   jxl.format.UnderlineStyle.NO_UNDERLINE,  
		   jxl.format.Colour.BLACK);  
		   WritableCellFormat titleFormat = new WritableCellFormat(wfont);  
  
		   String[] title = { "微信昵称","手机号码","反馈时间","反馈内容"};//如果还有字段的话,以此类推  
		   // 设置Excel表头  
		   for (int i = 0; i < title.length; i++) {  
		    Label excelTitle = new Label(i, 0, title[i], titleFormat);  
		    wsheet.addCell(excelTitle);  
		   }  
		   int c = 1; // 用于循环时Excel的行号  
		  for(TbFeedBackVo pm:list){
			  	Label content1 = new Label(0, c,  pm.getNickName());  
			  	Label content2 = new Label(1, c,  pm.getPhoneNumber()); 
			  	Label content3 = new Label(2, c,  pm.getFeedTime()); 
			  	Label content4 = new Label(3, c,  pm.getFeedText()); 
			    //如果还有的话,以此类推  
			    wsheet.addCell(content1);  
			    wsheet.addCell(content2);
			    wsheet.addCell(content3);
			    wsheet.addCell(content4);
			    //如果还有的话,以此类推  
			    c++;  
		  }

		   wbook.write(); // 写入文件  
		   wbook.close();  
		   os.close();  
		   //System.out.println("导入成功！");  
		  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }  
		 }  
	
	
	public static void exportDoAnswer(OutputStream os, List<TbDoAnswerVo> list) {  
		  WritableWorkbook wbook=null;
		  try {  
		   wbook = Workbook.createWorkbook(os); // 建立excel文件  
		   WritableSheet wsheet = wbook.createSheet("用户反馈信息记录", 0); // 工作表名称  
		   //设置Excel字体  
		   WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,  
		    WritableFont.BOLD, false,  
		   jxl.format.UnderlineStyle.NO_UNDERLINE,  
		   jxl.format.Colour.BLACK);  
		   WritableCellFormat titleFormat = new WritableCellFormat(wfont);  
  
		   String[] title = { "微信昵称","手机号码","反馈时间","反馈内容"};//如果还有字段的话,以此类推  
		   // 设置Excel表头  
		   for (int i = 0; i < title.length; i++) {  
		    Label excelTitle = new Label(i, 0, title[i], titleFormat);  
		    wsheet.addCell(excelTitle);  
		   }  
		   int c = 1; // 用于循环时Excel的行号  
		  for(TbDoAnswerVo pm:list){
			  	Label content1 = new Label(0, c,  pm.getNickName());  
			  	Label content2 = new Label(1, c,  pm.getPhoneNumber()); 
			  	Label content3 = new Label(2, c,  pm.getAnswerTime()); 
			  	Label content4 = new Label(3, c,  pm.getAnswerText()); 
			    //如果还有的话,以此类推  
			    wsheet.addCell(content1);  
			    wsheet.addCell(content2);
			    wsheet.addCell(content3);
			    wsheet.addCell(content4);
			    //如果还有的话,以此类推  
			    c++;  
		  }

		   wbook.write(); // 写入文件  
		   wbook.close();  
		   os.close();  
		   //System.out.println("导入成功！");  
		  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }  
		 }  
	
	
	public static void exportProblem(OutputStream os,ArrayList<TbProblems> list) {  
		WritableWorkbook wbook=null;
		  try {  
		   wbook = Workbook.createWorkbook(os); // 建立excel文件  
		   WritableSheet wsheet = wbook.createSheet("在线学习题库", 0); // 工作表名称  
		   //设置Excel字体  
		   WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,  
		    WritableFont.BOLD, false,  
		   jxl.format.UnderlineStyle.NO_UNDERLINE,  
		   jxl.format.Colour.BLACK);  
		   WritableCellFormat titleFormat = new WritableCellFormat(wfont);  
  
		   String[] title = { "题目","选项A","选项B","选项C","选项D","答案","图片地址","单选或多选"};//如果还有字段的话,以此类推  
		   // 设置Excel表头  
		   for (int i = 0; i < title.length; i++) {  
		    Label excelTitle = new Label(i, 0, title[i], titleFormat);  
		    wsheet.addCell(excelTitle);  
		   }  
		   int c = 1; // 用于循环时Excel的行号  
		  for(TbProblems pm:list){
			  	Label content1 = new Label(0, c,  pm.getProblem());  
			  	Label content2 = new Label(1, c,  pm.getResultA()); 
			  	Label content3 = new Label(2, c,  pm.getResultB()); 
			  	Label content4 = new Label(3, c,  pm.getResultC()); 
			  	Label content5 = new Label(4, c,  pm.getResultD());
			  	Label content6 = new Label(5, c,  pm.getAnswer());
			  	Label content7 = new Label(6, c,  pm.getImageUrl());
			  	Label content8 = new Label(7, c,  pm.getChoiceType());
			    //如果还有的话,以此类推  
			    wsheet.addCell(content1);  
			    wsheet.addCell(content2);
			    wsheet.addCell(content3);
			    wsheet.addCell(content4);
			    wsheet.addCell(content5);  
			    wsheet.addCell(content6);
			    wsheet.addCell(content7);
			    wsheet.addCell(content8);
			    //如果还有的话,以此类推  
			    c++;  
		  }

		   wbook.write(); // 写入文件  
		   wbook.close();  
		   os.close();  
		   //System.out.println("导入成功！");  
		  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }  
		 }
	
}
