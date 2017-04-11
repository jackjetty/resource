package com.detachment.util;

import java.io.OutputStream;
import java.util.ArrayList;

import com.detachment.pojo.vo.TbFormalAccidentVo;

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

	public static void exportClassroom(OutputStream os,
			ArrayList<TbFormalAccidentVo> list) {
		WritableWorkbook wbook = null;
		try {
			wbook = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet("交通事故信息记录", 0); // 工作表名称
			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat titleFormat = new WritableCellFormat(wfont);

			String[] title = { "事故编号", "事故时间", "微信昵称", "手机号", "事发地址",
					"纬度", "经度", "归属大队","出警情况","处理状态", "受理人" };// 如果还有字段的话,以此类推
			// 设置Excel表头
			for (int i = 0; i < title.length; i++) {
				Label excelTitle = new Label(i, 0, title[i], titleFormat);
				wsheet.addCell(excelTitle);
			}
			int c = 1; // 用于循环时Excel的行号
			for (TbFormalAccidentVo pm : list) {
				Label content1 = new Label(0, c, pm.getFormlAccidentId());
				Label content2 = new Label(1, c, pm.getReportTimeString());
				Label content3 = new Label(2, c, pm.getNickname());
				Label content4 = new Label(3, c, pm.getReportPhoneNumber());
				Label content5 = new Label(4, c, pm.getAddress());
				Label content6 = new Label(5, c, pm.getLocationX());
				Label content7 = new Label(6, c, pm.getLocationY());
				Label content8 = new Label(7,c,pm.getDepartment());
				Label content9 = new Label(8,c,pm.getEmergencyCallString());
				Label content10 = new Label(9, c, pm.getAccidentState());
				Label content11 = new Label(10, c, pm.getAccepter());
				// 如果还有的话,以此类推
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
				// 如果还有的话,以此类推
				c++;
			}
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
