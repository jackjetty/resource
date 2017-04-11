package com.rising.mobilepayment.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.bean.SalesInformation;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.SalesInformationService;

@Controller
@RequestMapping("/image")
public class GetImageAction extends BaseAction {

	Log log = LogFactory.getLog(GetImageAction.class);
	
	@Autowired
	OperateLogService operateLogService;

	@Autowired
	SalesInformationService salesInformationService;

	@RequestMapping("/getSalesInfoImage")
	public void getSalesInfoImage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		// HashMap<String, String> map = new HashMap<String,String>();
		// map.put("InforId", "3");
		// map.put("ImgName","hbpay_20130808.jpg");
		// map.put("Resoluation", "480");
		Integer id = Integer.parseInt(map.get("InforId").replace(".", "#")
				.split("#")[0]);
		// Integer id =Integer.parseInt(map.get("InforId"));
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("InforId", id);
		param.put("ImgName", map.get("ImgName"));
		byte[] image = null;
		SalesInformation info = salesInformationService
				.getSalesInforImageById(param);
		switch (Integer.parseInt(map.get("Resoluation"))) {
		case 320:
			image = info.getImage1();
			break;
		case 480:
			image = info.getImage2();
			break;
		case 720:
			image = info.getImage3();
			break;
		case 640:
			image = info.getImage6();
			break;
		case 180:
			image = info.getImage5();
			break;
		case 500:
			image = info.getImage4();
			break;
		default:
			break;
		}
		try {
			ServletOutputStream out = response.getOutputStream();
			out.write(image);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("getSalesInfoImage()->图片输出时出错!" + e.toString());
		}
	}
}
