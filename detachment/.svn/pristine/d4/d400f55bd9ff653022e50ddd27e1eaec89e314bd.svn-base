package com.detachment.wei.wsdd;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.*;
import java.util.Map;

import javax.xml.namespace.QName; 
import javax.xml.rpc.ServiceException; 
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.rising.wei.bean.ErrCodeBean;

import com.detachment.util.XmlUtil;
public class ClientWSDD{
	 public static final  String nameSpace="http://server.xfire.service.framework.cdt.com";  
	 public static final  String endpoint="http://wscgs.sxga.gov.cn/services/CdtOutAccess"; 
	 public static final  String soapActionURI="http://server.xfire.service.framework.cdt.com/outService"; 
	 public static final  String sendCarMoveMessageIC="A000202";
	 public static final  String logonFullScoreIC="A000183";
	 public static final  String  SYSTEMVOUCHER="A98AC6455C2A6DB94018ED5D0E331848";
	 
	 public   ErrCodeBean sendCarMoveMessage(String carType,String carNum,String carAddress ,String reportPhone) {
		 ErrCodeBean errCodeBean=new ErrCodeBean();
		 errCodeBean.setErrcode("0");
		 errCodeBean.setErrmsg("发送成功！");
		 try  
		    {   
		        Service service = new Service();  
		        Call call = new Call(service);  
		        call.setTargetEndpointAddress(new java.net.URL(endpoint));   
		        call.setUseSOAPAction(true);  
		        call.setSOAPActionURI(soapActionURI);   
		        call.setOperationName(new QName(nameSpace, "outService"));  
		        //输入参数设置   
		        call.addParameter(new QName(nameSpace, "in0"),  
		            org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		         call.addParameter(new QName(nameSpace, "in1"),  
		               org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		        call.addParameter(new QName(nameSpace, "in2"),  
		               org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		      

		        call.setReturnType(new QName(nameSpace, "out"), String.class);   
		        //执行  
		        StringBuffer xmlBuffer=new StringBuffer("");
		        xmlBuffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		        xmlBuffer.append("<root>");
		        xmlBuffer.append("<QueryCondition>");
		        xmlBuffer.append("<hpzl>"+carType+"</hpzl>");
		        xmlBuffer.append("<hphm>"+URLEncoder.encode(carNum, "utf-8")+"</hphm>");
		        xmlBuffer.append("<tfdz>"+URLEncoder.encode(carAddress, "utf-8")+"</tfdz>"); 
		        xmlBuffer.append("<sjhm>"+reportPhone+"</sjhm>");
		        xmlBuffer.append("</QueryCondition>");
		        xmlBuffer.append("</root>");   
		        String output = (String) call.invoke(new Object[]  
		        {sendCarMoveMessageIC,SYSTEMVOUCHER,xmlBuffer.toString() }); 
		        String inMsgXml=URLDecoder.decode(output, "utf-8"); 
		        Map<String, String> map = null;  
		        System.out.println(inMsgXml);
				map = XmlUtil.xml2Map(inMsgXml);  
				String msg=map.get("root.body.item.res");
				if(!msg.equalsIgnoreCase("0")){
					 errCodeBean.setErrcode("1");
					 errCodeBean.setErrmsg(msg);
				} 
		       
		  
		    }  
		    catch (Exception e)  
		    {  
		    	errCodeBean.setErrcode("99");
				errCodeBean.setErrmsg(e.getMessage());
		    }  
		    return errCodeBean; 
		 
		 
	 } 
	 public ErrCodeBean logonFullScore(String identityCard,String fileNum ) {
		 ErrCodeBean errCodeBean=new ErrCodeBean();
		 errCodeBean.setErrcode("0");
		 errCodeBean.setErrmsg("登录成功！");
		 try  
		    {   
		        Service service = new Service();  
		        Call call = new Call(service);  
		        call.setTargetEndpointAddress(new java.net.URL(endpoint));   
		        call.setUseSOAPAction(true);  
		        call.setSOAPActionURI(soapActionURI);   
		        call.setOperationName(new QName(nameSpace, "outService"));  
		        
		        call.addParameter(new QName(nameSpace, "in0"),  
		            org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		         call.addParameter(new QName(nameSpace, "in1"),  
		               org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		        call.addParameter(new QName(nameSpace, "in2"),  
		               org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		      

		        call.setReturnType(new QName(nameSpace, "out"), String.class);   
		        //执行  
		        StringBuffer xmlBuffer=new StringBuffer("");
		        xmlBuffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		        xmlBuffer.append("<root>");
		        xmlBuffer.append("<QueryCondition>");
		        xmlBuffer.append("<sfzmhm>"+identityCard+"</sfzmhm>");
		        xmlBuffer.append("<dabh>"+fileNum+"</dabh>");
		        xmlBuffer.append("</QueryCondition>");
		        xmlBuffer.append("</root>");   
		        String output = (String) call.invoke(new Object[]  
		        {logonFullScoreIC,SYSTEMVOUCHER,xmlBuffer.toString() }); 
		        
		        String inMsgXml=URLDecoder.decode(output, "utf-8"); 
		        Map<String, String> map = null;   
				map = XmlUtil.xml2Map(inMsgXml);  
				String msg=map.get("root.body.item.res");
				if(!msg.equalsIgnoreCase("0")){
					 errCodeBean.setErrcode("1");
					 errCodeBean.setErrmsg(msg);
				} 
		    }  
		    catch (Exception e)  
		    {  
		        //e.printStackTrace();
		        errCodeBean.setErrcode("99");
				errCodeBean.setErrmsg(e.getMessage());
		    }  
		    return errCodeBean;
	 }
	 public static void main(String[] args){
		 ClientWSDD clientWSDD=new ClientWSDD();
		 
		 ErrCodeBean errCodeBean=clientWSDD.sendCarMoveMessage("02","浙D500BU","绍兴市" ,"18757530227");
		    
		 //ErrCodeBean errCodeBean=clientWSDD.logonFullScore("330621198907128983", "330680571254");
		 System.out.println(errCodeBean.getErrcode());
		 //clientWSDD.logonFullScore("33060219861107303X", "220101096879");
	 }
	 
	 
	 
	 
	
	  
}