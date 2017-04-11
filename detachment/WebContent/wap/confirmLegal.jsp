<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>绍兴交警网上自助缴费</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/tencent/css/style.css" />
<link href="${pageContext.request.contextPath}/css/legal/use.css" type="text/css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tencent/msgbox.js" type="text/javascript"></script>

<style> 
.content{ margin-left:10px; margin-right:10px;}
.tiXing{ margin-bottom:10px; margin-top:10px; font-size:14px;}
.details{ width:100%; border:solid 1px #d9d9d9; border-radius:3px; -moz-border-radius:3px; -moz-border-radius:3px;}
.hint p{ margin:0; font-size:12px; color:#727272; line-height:18px;} 
.orange{color:#ff6600;}
.colorRed{color:red;}
</style>
</head>

<body>
<div class="header">
<div class="top">
	<h2>告知确认</h2>
</div>
	<a  onclick="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/image/legal/top_l.png" style="position:absolute; margin-top:-50px;"></a>
</div>
<div class="content">
	<p class="tiXing"><s:property value="#request.result['lineContent1']"/>
	</p>
    <div class="details">
    	<p style=" margin:5px; font-size:12px; line-height:18px;"> 
    	    <s:property value="#request.result['lineContentHtml2']" escape="false"/>  
    	</p>
    </div>
    
</div>

<div class="footer">
    <div class="button"><a href="#">下一步</a></div>
    <div class="hint">
    	 <p>温馨提示：</p>
         <p>1、需对以上所述信息确认后，方可进入下一步。选择“是”或者“否” 后点击下一条即可查看下一条信息。</p>
         <p>2、您选择了“否”的记录将不在处理范围内。</p>
         <p>3、如对上述信息无任何异议，则可直接点击（确认,下一步） 进入下一步操作。</p>
    </div> 
</div>
</body>
</html>
<script type="text/javascript">
 $(function() { 
    $(".button").click(function() { 
      var xh ="<s:property value="#request.result['rdotValue']"/>";
      var captcha_rand_flag_refersh ="<s:property value="#request.result['captchaValue']"/>";
      window.location.href="${pageContext.request.contextPath}/wap/verifyPageLegal.action?xh="+xh+"&captcha_rand_flag_refersh"+captcha_rand_flag_refersh;
       
    });
  });
 </script>
