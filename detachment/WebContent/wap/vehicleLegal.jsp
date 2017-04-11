<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
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
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/tencent/msgbox.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/legal/use.css" type="text/css" rel="stylesheet">

 

<style> 
 


.content{ padding-left:10px; padding-right:10px;}
h4{ margin:0; font-weight:normal; line-height:28px;}
input{ font-size:14px; padding-left:5px;color:#b1b1b1;width:98%; height:36px; border:solid 1px #c9c9c9;border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px;-webkit-appearance:none;}
.box{ margin-top:10px;}
option{ font-color:#bdbcbc;}
</style>
</head>

<body>
<div class="header">
<div class="top">
	<h2>车辆信息</h2>
</div>
	<a  onclick="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/image/legal/top_l.png" style="position:absolute; margin-top:-50px;"></a>
</div>

<div class="content">
	<div class="box">
	<h4>号牌种类</h4>
    <div style="margin-right:5px;">
    	<input style="width:100%;" type="text" value="小客车"  readonly="readonly"/> 
    </div>
		 
    </div>
    
    <div class="box">
    <h4>号牌号码</h4>
    
    <div style="overflow:hidden;">
    	<div style="float:left;width:15%;">
    	<input style="width:100%;" type="text" value="浙D" readonly="readonly"/>
        </div>
        
        <div style="float:right;width:80%;">
        	<div style=" margin-right:7px;"><input style="width:100%;" type="text" name="hphm"/></div>
        </div>
    </div>
     
    </div>
    
     <div class="box">
     <h4>车辆识别代号（车架号）</h4>
     	<div style="margin-right:5px;"><input style="width:100%;" type="text" name="cjhm"/></div>
      	<p style="color:red; font-size:12px; line-height:20px;margin:0;">请输入后六位（不足六位请输全）</p> 
    </div>
    
</div>

<div class="footer">
    <div class="button" > <a  id="btnVehicle"  >下一步 </a></div>
</div>
</body>
</html>
<script type="text/javascript"> 
	$("#btnVehicle").click(function() { 
		var hpzl="02"; 
		var hphm=$.trim($("input[name=hphm]:first").val()).toUpperCase();
		var cjhm=$.trim($("input[name=cjhm]:first").val()).toUpperCase();
		if(hphm==""){
			ZENG.msgbox.show("请输入车牌号码！！", 5, 3000);
			return;
		}
		if(cjhm==""){
			ZENG.msgbox.show("请输入车架号！！", 5, 3000);
			return;
		}
		ZENG.msgbox.show("正在加载中，请稍后...", 6,60*3000);
		hphm="D"+hphm;
		$.ajax({
			url: "${pageContext.request.contextPath}/wap/vehicleLegal.action",
			data:{hpzl:hpzl,hphm:hphm,cjhm:cjhm},
			dataType:"json",
			type:"post",
			success:function(data){ 
				ZENG.msgbox._hide();
				if(data.respCode==0){ 
					 window.location.href="${pageContext.request.contextPath}/wap/pendPageLegal.action";
				} 
				else{
					ZENG.msgbox.show(data.respInfo, 5, 3000);
				}
	       		return false; 
	      }
		});
	});
</script>
