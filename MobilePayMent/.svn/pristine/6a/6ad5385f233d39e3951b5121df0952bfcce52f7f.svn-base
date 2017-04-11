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

import com.rising.mobilepayment.bean.AppStartPicture;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.OperateLogService;
import com.rising.mobilepayment.service.StartImageService;


@Controller
@RequestMapping("/startImage")
public class StartImageAction extends BaseAction {
	
	Log log = LogFactory.getLog(StartImageAction.class);
	
	@Autowired StartImageService startImageService;
	
	@Autowired
	OperateLogService operateLogService;
		
	@RequestMapping("/getImage")
	public void getImage(HttpServletRequest request,HttpServletResponse response){
		HashMap<String,String> map = getParameter(request);
		AppStartPicture asp = startImageService.getNowStartPicture(map);
		byte[] image = null;
		switch (Integer.parseInt(map.get("Resoluation"))) {
		case 320:
			image = asp.getImage1();
			break;
		case 480:
			image = asp.getImage2();
			break;
		case 720:
			image = asp.getImage3();
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
			log.error("getImage()->图片输出时出错!" + e.toString());
		}
	}

}
