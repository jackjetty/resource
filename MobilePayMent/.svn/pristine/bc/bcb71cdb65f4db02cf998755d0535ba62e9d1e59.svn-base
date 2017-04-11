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

import com.rising.mobilepayment.bean.HotGame;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.HotGameService;
import com.rising.mobilepayment.service.OperateLogService;

@Controller
@RequestMapping("/hotgame")
public class HotGameAction extends BaseAction {

	Log log = LogFactory.getLog(HotGameAction.class);

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	HotGameService hotGameService;

	@RequestMapping("/getInfo")
	public void getInfo(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		Integer pageIndex = Integer.parseInt(map.get("PageIndex"));
		String resultJson = hotGameService.getInfo(pageIndex);
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/getImage")
	public void getImage(HttpServletRequest request,
			HttpServletResponse response) {
		byte[] image = null;
		HashMap<String, String> map = getParameter(request);
		HotGame game = hotGameService.getHotGameByImageName(map.get("ImgName").split("_")[1]);
		
		if(map.get("ImgName").startsWith("Small")){
			image = game.getIcoImg1();
		}else{
			image = game.getIcoImg2();
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
