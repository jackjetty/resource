package com.rising.management.common;


import java.io.OutputStream;
import java.util.ArrayList;
import com.rising.management.vo.OrderInfoVo;

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
	
	public static void exportClassroom(OutputStream os,ArrayList<OrderInfoVo> list) {  
		WritableWorkbook wbook=null;
		  try {  
		   wbook = Workbook.createWorkbook(os); // 建立excel文件  
		   WritableSheet wsheet = wbook.createSheet("信息交易记录", 0); // 工作表名称  
		   //设置Excel字体  
		   WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,  
		    WritableFont.BOLD, false,  
		   jxl.format.UnderlineStyle.NO_UNDERLINE,  
		   jxl.format.Colour.BLACK);  
		   WritableCellFormat titleFormat = new WritableCellFormat(wfont);  
  
		   String[] title = { "充值时间","支付号码","号码归属地", "产品名称","充值号码","交易结果","支付金额","赠送积分","返回码","手机系统","手机型号","系统版本"};//如果还有字段的话,以此类推  
		   // 设置Excel表头  
		   for (int i = 0; i < title.length; i++) {  
		    Label excelTitle = new Label(i, 0, title[i], titleFormat);  
		    wsheet.addCell(excelTitle);  
		   }  
		   int c = 1; // 用于循环时Excel的行号 
		    
		  for(OrderInfoVo pm:list){
			  	Label content1 = new Label(0, c,  pm.getOrderTime());  
			    Label content2 = new Label(1, c, pm.getPhoneNumber()); 
			    Label content3 = new Label(2, c, pm.getPlace()); 
			    Label content4 = new Label(3, c, pm.getProductName());
			    Label content5 = new Label(4, c, pm.getTargetNumber());
			    Label content6 = new Label(5, c, pm.getPayStatus());
			    Label content7 = new Label(6, c, ((Float)pm.getPayMoney()).toString());
			    Label content8 = new Label(7, c, ((Float)pm.getSendScore()).toString());
			    Label content9 = new Label(8, c, pm.getPayReturnCode());
			    Label content10= new Label(9,c,pm.getOs());
			    Label content11= new Label(10,c,pm.getClient());
			    Label content12= new Label(11,c,pm.getVersion());
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
}
