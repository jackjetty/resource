package com.rising.appserver.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
//import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile; 

import com.alipay.util.AlipayCore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rising.appserver.bean.BestBill;
import com.rising.appserver.bean.OrderInfo;
import com.rising.appserver.bean.TradeDetail;
import com.rising.appserver.common.BaseAction;
import com.rising.appserver.common.CommonUtil;
import com.rising.appserver.common.Constant;
import com.rising.appserver.service.ChargeService; 
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
@Controller
@RequestMapping("/bestBalance")
public class BestBalance extends BaseAction {

	private Log log = LogFactory.getLog(BestBalance.class);
	@Autowired
	private JavaMailSenderImpl  mailSender; 
	
	
	private String dateOrderRecordURL;
	
	
	
	
	
	public String getDateOrderRecordURL() {
		return dateOrderRecordURL;
	}
	@Value("#{propertiesReader[dateOrderRecordURL]}")
	public void setDateOrderRecordURL(String dateOrderRecordURL) {
		this.dateOrderRecordURL = dateOrderRecordURL;
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody
	String provideUploadInfo() {
		HashMap<String,Object> result = new HashMap<String,Object>(); 
		result.put("respCode", 1);
		result.put("respInfo", "方法不对，需要post！");
		return  encodeUTF_8 (new Gson().toJson(result) ); 
	} 
	@SuppressWarnings("finally")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String handleFileUpload(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,
			@RequestParam("file") MultipartFile file) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8"); 
		String fileName = file.getOriginalFilename();
		HashMap<String,Object> result = new HashMap<String,Object>();
		String accessVoucher=request.getParameter("accessVoucher");  
		String statisticsDate=CommonUtil.trim(request.getParameter("statisticsDate"));  
		
		String  curDateStrForm=CommonUtil.getCurrentDateStrForm();
		File objFile=null;
		int i=0;
		while(i<1000){
			objFile=new File(Constant.BEST_PATH 
					     +statisticsDate+"_"
					     +CommonUtil.castString(i, "000")
					     +".txt");
			if(!objFile.exists())
					break;
			i++;
			
			
		} 
		if(!objFile.getParentFile().exists())
			objFile.getParentFile().mkdirs();
		
		if (file.isEmpty()) {  
			result.put("respCode", 1);
			result.put("respInfo", "上传文件为空！");
			return  encodeUTF_8 (new Gson().toJson(result) );  
		}
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(file.getInputStream());

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(objFile)); 
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (Exception ex) { 
			
			result.put("respCode", 2);
			result.put("respInfo", "上传文件出错！"+ex.getMessage());
			return  encodeUTF_8 (new Gson().toJson(result) );   
		} finally {
			// 关闭流
			if (inBuff != null)
				try {
					inBuff.close();
					inBuff = null;
				} catch (final IOException e) { 
					e.printStackTrace();
				}
			if (outBuff != null)
				try {
					outBuff.close();
					outBuff = null;
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		String xmlContent=CommonUtil.readTxtFile(objFile.getAbsolutePath(),"utf-8"); 
        XStream xstream = new XStream(new DomDriver()); 
        xstream.processAnnotations(BestBill.class);
        //xstream.omitField(BestBalance.class, "balanceFileId");
        @SuppressWarnings("unchecked")
        BestBill bestBill = (BestBill) xstream.fromXML(xmlContent); 
        
        StringBuffer htmlBuffer=new StringBuffer("");
        htmlBuffer.append("<body style='margin:auto;font-family: \"宋体\";font-size: 13px'>");
        htmlBuffer.append("<div style='width:100%;text-align:center;align:center;MARGIN-RIGHT: auto; MARGIN-TOP: 30px;MARGIN-LEFT: auto;'>++++++++++++++++++++++++++++翼支付账单+++++++++++++++++++++++++++++</div>");
        htmlBuffer.append("<table  style='width:80%;margin:10px auto 20px auto' cellpadding=\"4\" cellspacing=\"1\" align=\"center\" valign=\"middle\"");
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: left'>统计开始时间</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getStatisticFromDate()+"</td>");
        htmlBuffer.append("<td style='text-align: left'>统计结束时间</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getStatisticEndDate()+"</td>");
        htmlBuffer.append("<td style='text-align: left'></td>");
        htmlBuffer.append("<td style='text-align: center'></td>");
        htmlBuffer.append("</tr>");
        
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: left'>交易笔数</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getCountSum()+"</td>");
        htmlBuffer.append("<td style='text-align: left'>成功笔数</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getSuccessCountSum()+"</td>");
        htmlBuffer.append("<td style='text-align: left'>失败笔数</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getFailCountSum()+"</td>");
        htmlBuffer.append("</tr>");
        
        
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: left'>交易总金额</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getMoneySum()+"</td>");
        htmlBuffer.append("<td style='text-align: left'>成功总金额</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getSuccessMoneySum()+"</td>");
        htmlBuffer.append("<td style='text-align: left'>失败总金额</td>");
        htmlBuffer.append("<td style='text-align: center'>"+bestBill.getFailMoneySum()+"</td>");
        htmlBuffer.append("</tr>");
 
         
        htmlBuffer.append("</table>");
        
        
        
        htmlBuffer.append("<table  style='width:100%;margin:10px 0px 20px 0px;' cellpadding=\"4\" cellspacing=\"1\" align=\"center\" valign=\"middle\"");
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: center'>流水号</td>");
        htmlBuffer.append("<td style='text-align: center'>手机号码</td>");
        htmlBuffer.append("<td style='text-align: center'>QQ号码</td>");
        htmlBuffer.append("<td style='text-align: center'>充值面额</td>");
        htmlBuffer.append("<td style='text-align: center'>充值金额</td>");
        htmlBuffer.append("<td style='text-align: center'>充值时间</td>");
        htmlBuffer.append("<td style='text-align: center'>交易结果</td>");
        htmlBuffer.append("</tr>"); 
        
        for(TradeDetail tradeDetail:bestBill.getTradeDetailList()){
        	 htmlBuffer.append("<tr style='background-color: #ffffff'>");
             htmlBuffer.append("<td style='text-align: center'>"+tradeDetail.getTradeNo()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+tradeDetail.getPhoneNumber()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+tradeDetail.getQqNumber()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+getProductName(tradeDetail.getProductId())+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+tradeDetail.getPayMoney()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+tradeDetail.getTradeDate()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+getTradeResult(tradeDetail.getChargeStatus())+"</td>");
             htmlBuffer.append("</tr>");
        }  
        htmlBuffer.append("</table>");
        
        
        htmlBuffer.append("<div style='text-align:center;width:100%;align:center; MARGIN-TOP: 30px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;'>++++++++++++++++++++++++++++168支付宝代充+++++++++++++++++++++++++++++</div>");
        
         
        Gson gson=new  Gson();
        HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("payMethodId", "27");
		paraMap.put("statisticsDate", statisticsDate);
		
		String responseGson = null;
		URL url;
		try {
			url = new URL(Constant.INNER_PREURL + dateOrderRecordURL);
			// 打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			// 设置提交方式
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 配置本次连接的Content-Type
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			// 维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置浏览器编码
			conn.setRequestProperty("Charset", "UTF-8");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			// 将请求参数数据向服务器端发送
			out.write(AlipayCore.createLinkString(paraMap));
			out.flush();
			out.close();
			if (conn.getResponseCode() == 200) {
				// 获得服务器端输出流
				InputStream is = conn.getInputStream();
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				is.close();
				responseGson = new String(bos.toByteArray(),"utf-8"); 
			} else {
				 
				result.put("respCode",-202);
				result.put("respInfo", "移动支付服务出现未处理异常！");
				responseGson = gson.toJson(result); 
			}
		} catch (MalformedURLException e) {  
			result.put("respCode",-203);
			result.put("respInfo", "连接移动支付服务失败！");
			responseGson = gson.toJson(result);
		} catch (IOException e) {  
			result.put("respCode",-203);
			result.put("respInfo", "连接移动支付服务失败！");
			responseGson = gson.toJson(result);
		}
		 
		JSONObject  object = JSONObject.fromObject(responseGson);
		int respCode = object.getInt ("respCode");
		String respInfo=CommonUtil.trim(object.getString ("respInfo")); 
		String orderList="[]";
		if(respCode==0){
			orderList=CommonUtil.trim(object.getString ("orderList")); 
		}
		
		List<OrderInfo> orderInfoList = gson.fromJson(orderList, new TypeToken<List<OrderInfo>>(){}.getType());
		 
		
		int countSum=0,successCountSum=0,failCountSum=0;
		float moneySum=0f,successMoneySum=0f,failMoneySum=0f;
		for(OrderInfo orderInfo:orderInfoList){
			countSum++;
			moneySum+=orderInfo.getPayMoney();
			if(orderInfo.getPayReturnCode().equalsIgnoreCase("0")){
				successCountSum++;
				successMoneySum+=orderInfo.getPayMoney();
			}else{
				failCountSum++;
				failMoneySum+=orderInfo.getPayMoney();
				
			} 
		}
		
		
		
	    htmlBuffer.append("<table  style='width:80%;margin:10px auto 20px auto' cellpadding=\"4\" cellspacing=\"1\" align=\"center\" valign=\"middle\"");
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: left'>统计日期</td>");
        htmlBuffer.append("<td style='text-align: center'>"+statisticsDate+"</td>");
        htmlBuffer.append("<td style='text-align: left'></td>");
        htmlBuffer.append("<td style='text-align: center'></td>");
        htmlBuffer.append("<td style='text-align: left'></td>");
        htmlBuffer.append("<td style='text-align: center'></td>");
        htmlBuffer.append("</tr>");
        
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: left'>交易笔数</td>");
        htmlBuffer.append("<td style='text-align: center'>"+countSum+"</td>");
        htmlBuffer.append("<td style='text-align: left'>成功笔数</td>");
        htmlBuffer.append("<td style='text-align: center'>"+countSum+"</td>");
        htmlBuffer.append("<td style='text-align: left'>失败笔数</td>");
        htmlBuffer.append("<td style='text-align: center'>"+failCountSum+"</td>");
        htmlBuffer.append("</tr>");
	        
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: left'>交易总金额</td>");
        htmlBuffer.append("<td style='text-align: center'>"+moneySum+"</td>");
        htmlBuffer.append("<td style='text-align: left'>成功总金额</td>");
        htmlBuffer.append("<td style='text-align: center'>"+successMoneySum+"</td>");
        htmlBuffer.append("<td style='text-align: left'>失败总金额</td>");
        htmlBuffer.append("<td style='text-align: center'>"+failMoneySum+"</td>");
        htmlBuffer.append("</tr>");
        htmlBuffer.append("</table>"); 
        
        htmlBuffer.append("<table  style='width:100%;margin:10px 0px 20px 0px;' cellpadding=\"4\" cellspacing=\"1\" align=\"center\" valign=\"middle\"");
        htmlBuffer.append("<tr style='background-color: #ffffff'>");
        htmlBuffer.append("<td style='text-align: center'>168支付编号</td>");
        htmlBuffer.append("<td style='text-align: center'>翼支付流水号</td>");
        htmlBuffer.append("<td style='text-align: center'>手机号码</td>");
        htmlBuffer.append("<td style='text-align: center'>QQ号码</td>");
        htmlBuffer.append("<td style='text-align: center'>充值面额</td>");
        htmlBuffer.append("<td style='text-align: center'>充值金额</td>");
        htmlBuffer.append("<td style='text-align: center'>充值时间</td>");
        htmlBuffer.append("<td style='text-align: center'>交易结果</td>");
        htmlBuffer.append("</tr>"); 
        
        for(OrderInfo orderInfo:orderInfoList){
        	 htmlBuffer.append("<tr style='background-color: #ffffff'>");
        	 htmlBuffer.append("<td style='text-align: center'>"+orderInfo.getOrderId()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+orderInfo.getHcOrderId()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+orderInfo.getPhoneNumber()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+orderInfo.getTargetNumber()+"</td>"); 
             htmlBuffer.append("<td style='text-align: center'>"+getProductName(orderInfo.getProductId())+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+orderInfo.getPayMoney()+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+CommonUtil.doDateForm( orderInfo.getPayTime(),"yyyy-MM-dd HH:mm:ss")+"</td>");
             htmlBuffer.append("<td style='text-align: center'>"+orderInfo.getPayStatus()+"</td>");
             htmlBuffer.append("</tr>");
        }  
        htmlBuffer.append("</table>");
        
        
        
        htmlBuffer.append("</body>");
        
        
        
        
        
        
      
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper; 
        try {
        	String nick = javax.mail.internet.MimeUtility.encodeText("冉思科技"); 
            helper = new MimeMessageHelper(mime, true, "utf-8");
            helper.setFrom(new InternetAddress(nick + " <"+mailSender.getUsername()+">"));
            helper.setTo(new String[] {"13336022050@189.cn","121437479@qq.com","541386798@qq.com"});
            helper.setCc("shaoxing8642736@126.com");
            helper.setSubject("翼支付统计_"+statisticsDate);
            helper.setText(htmlBuffer.toString(),true);
        } catch (Exception me) {
            me.printStackTrace();
        }
        mailSender.send(mime);    
		result.put("respCode", 0);
		result.put("respInfo", "上传文件成功！" ); 
		return  encodeUTF_8 (new Gson().toJson(result) );  

	}
	
	
	private String getProductName(String productId){
		if(productId.equalsIgnoreCase("16019"))
			 return "10元";
		if(productId.equalsIgnoreCase("16020"))
			 return "20元";
		if(productId.equalsIgnoreCase("16021"))
			 return "30元";
		if(productId.equalsIgnoreCase("16022"))
			 return "50元";
		return productId;
	}
	
	private String getTradeResult(String chargeStatus){
		if(chargeStatus.equalsIgnoreCase("fail"))
			 return "失败";
		if(chargeStatus.equalsIgnoreCase("success"))
			 return "成功";
		 
		return chargeStatus;
	}

}