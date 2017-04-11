package com.traffic.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.traffic.util.CapthchaUtil;
@Controller("captchaAction")
public class CaptchaAction {
	Log log = LogFactory.getLog(CaptchaAction.class);
	public void getCaptchaImage() {
		int width = 70;
		int height = 35;
		HttpServletResponse response = ServletActionContext.getResponse();
		String captchaCode = CapthchaUtil.getCapthchaCode();
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("captchaCode", captchaCode);
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("宋体", Font.BOLD, 30);
		// 设置字体。
		g.setFont(font);
		g.setColor(Color.gray);
		g.drawRect(0, 0, width - 1, height - 1);
		 for(int i=0;i<6;i++){      
	            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
	        //画线
	            g.drawLine(random.nextInt(60),random.nextInt(30), random.nextInt(60), random.nextInt(30));
	        }
		int red = 0, green = 0, blue = 0;
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < 4; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(captchaCode.charAt(i));
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i) * 15, height - 5);
			// 将产生的四个随机数组合在一起。
		}
		response.setContentType("image/jpeg");
		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
			sos.flush();
			sos.close();
		} catch (IOException e) {
			log.error("getCaptchaImage()->" + e);
		}
	}
}
