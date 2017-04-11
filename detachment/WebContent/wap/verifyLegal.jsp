<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>绍兴交警网上自助缴费</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/tencent/css/style.css" />
    <link href="${pageContext.request.contextPath}/css/legal/use.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/tencent/msgbox.js" type="text/javascript"></script>
    
<style>
.content{ padding-left:10px; padding-right:10px;}
h4{ margin:0; font-weight:normal; line-height:28px; font-size:14px;}
select{ width:100%; height:35px; border:solid 1px #c9c9c9;-webkit-appearance:none;}
input{width:98%; height:30px; border:solid 1px #c9c9c9; border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px;-webkit-appearance:none;}
.box{ margin-top:5px;}
.footer{padding-left:10px; padding-right:10px; margin-top:30px;}
button{ width:100%; background-color:#48d65f; height:45px; font-size:14px; color:#ffffff; border:solid 1px #48d65f; border-radius:3px; -webkit-border-radius:3px; -moz-border-radius:3px; margin-bottom:10px;}
</style>
</head>

<body>
<div class="header">
<div class="top">
	<h2>验证驾驶证信息</h2>
</div>
	<a  onclick="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/image/legal/top_l.png" style="position:absolute; margin-top:-50px;"></a>
</div>

<div class="content">
	 
	<div class="box">
		<h4>姓名</h4>
    	<input type="text" name="xm" readonly="readonly" value="<s:property value="#request.result['xm']"/>" />
    </div>
    
    <div class="box">
    <h4>驾驶证号</h4>
    	<input type="text" name="sfzmhm" readonly="readonly" value="<s:property value="#request.result['sfzmhm']"/>" />
    </div>
    
    <div class="box">
    <h4>手机短信二次验证码</h4>
    <div style="overflow:hidden;">
    	<div style="float:left;width:70%;">
    	<input style="width:100%;" name="mobilecode"/>
    		
        </div>
        
        <div style="float:right;width:25%;">
        	<div id="btn_sendMsg"style="height:34px; font-size:14px; line-height:34px; text-align:center; background-color:#48d65f; color:#ffffff;border-radius:3px; -webkit-border-radius:3px; -moz-border-radius:3px;">获取验证码</div>
        </div>
    </div>
    
    <div class="footer">
          <div class="button"><a href="#">下一步</a></div>
    </div>
    </div>
    </div>
    
    
     
    


</body>
</html>
<script type="text/javascript">
 $(function() { 
    $("#btn_sendMsg").click(function() { 
    	var xm=$.trim($("input[name=xm]:first").val());
		var sfzmhm=$.trim($("input[name=sfzmhm]:first").val());
		ZENG.msgbox.show("提交中...", 6);
		$.ajax({
			url: "${pageContext.request.contextPath}/wap/sendMsgLegal.action",
			data:{xm:xm,sfzmhm:sfzmhm},
			dataType:"json",
			type:"post",
			success:function(data){ 
				ZENG.msgbox._hide();
				if(data.respCode==0){ 
					ZENG.msgbox.show("发送成功,注意接收！", 4,2000); 
					$("#btn_sendMsg").attr("disabled",true); 
					
					$("#btn_sendMsg").css("background-color","#bababb");
					 
				}else{
					ZENG.msgbox.show(data.respInfo,5,2000);
				} 
	       		return false; 
	      }
		});
    });
	
    $(".button").click(function() { 
    	
    	var mobilecode=$.trim($("input[name=mobilecode]:first").val());
    	if(mobilecode==""){
    		ZENG.msgbox.show("请输入短信验证码！", 5, 3000);
			return;
    	}
    	ZENG.msgbox.show("提交中...", 6);
    	$.ajax({
			url: "${pageContext.request.contextPath}/wap/checkMsgLegal.action",
			data:{mobilecode:mobilecode},
			dataType:"json",
			type:"post",
			success:function(data){ 
				ZENG.msgbox._hide();
				if(data.respCode==0){ 
					
					window.location.href="http://wscgs.sxga.gov.cn/zzwfcl/netpay/payServlet?jdsbh="+data.jdsbh+"&orderId="+data.orderId+"&orderTime="+data.orderTime;
					//window.location.href="${pageContext.request.contextPath}/wap/payPageLegal.action";
				}else{
					ZENG.msgbox.show(data.respInfo,5,2000);
				} 
	       		return false; 
	      }
		});
    	
    	
    	
    	
    	
    });
    
    
    
		
    	
      
      window.location.href="${pageContext.request.contextPath}/wap/verifyPageLegal.action?xh="+xh+"&captcha_rand_flag_refersh"+captcha_rand_flag_refersh;
       
    
  });
 </script>
