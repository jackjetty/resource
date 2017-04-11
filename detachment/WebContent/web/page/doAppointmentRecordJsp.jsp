<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预约记录</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript">
	var openId = '${openId}';
	$(function(){
		$.ajax({
			url : "getAppointmentRecordJsp.action",
			async : false,
			data : "openId="+openId,
			dataType : "json",
			type : "post",
			success : function(data) {
				var rows=data;
				if(rows.length==0){
					$("#content").append("<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><div style='width:100%;text-align:center;'>没有相关数据</div></li></ul>");
				}else{
					var content="";
					for(var i=0;i<rows.length;i++){
						content += "<ul class='round' ><li class='title mb'><span>"+rows[i].appointmentTheme+"</span></li><li class='nob'><table width='100%' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><td>"+rows[i].appointmentDesc+"</td></tr><tr><td>"+rows[i].appointmentAddress+"</td></tr><tr><td>请准时参加现场学习活动!</td></tr></tbody></table></li></ul>";
					}
					$("#content").append(content);
				}
				
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
	});
	
</script>
<script>
 document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
WeixinJSBridge.call('hideOptionMenu');
});
</script>
<style>
#titleDiv{
    background-color: #179F00;
    background-image: -moz-linear-gradient(center bottom , #179F00 0%, #5DD300 100%);
    box-shadow: 0 0px 0 #94E700 inset, 0 1px 2px rgba(0, 0, 0, 0.5);
    color: #FFFFFF;
    width:100%;
    line-height:40px;
    margin:0 auto;
    text-align:center;
}
.nob td{
height:30px;
}
</style>

</head>
<body id="img-content">
<div style="width:100%;" >
	<div id="titleDiv" >
		<span style="font-size:18px;color:#FFFFFF;">满12分预约记录</span>
	</div>
</div>
<div class="cardexplain" id="content">
			
</div>




</body>
</html>