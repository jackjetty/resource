<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>满12分预约</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript">
	var openId = '${openId}';
	function toggle(showId, hideId)
	{
	    $('#'+hideId).hide();
	    $('#'+showId).show();
	}

	$(function(){
		$.ajax({
			url : "getAppointmentJsp.action",
			async : false,
			data : "pageSize=5&pageIndex=1",
			dataType : "json",
			type : "post",
			success : function(data) {
				var rows=data;
				if(rows.length==0){
					$("#content").append("<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><div style='width:100%;text-align:center;'>没有相关数据</div></li></ul>");
				}else{
					var content="";
					for(var i=0;i<rows.length;i++){
						var sum=rows[i].appointmentSum;
						var count=rows[i].appointmentCount;
						var peopleNum=count+"人/"+sum+"人";
						if(sum==count){
							content += "<ul class='round' ><li class='title mb'><span>"+rows[i].appointmentTheme+"</span></li><li class='nob'><table width='100%' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><td colspan='2'>"+rows[i].appointmentDesc+"</td></tr><tr><td colspan='2'>"+rows[i].appointmentAddress+"</td></tr><tr><td>"+peopleNum+"</td><td><div class='divTest2'>预约</div></td></tr></tbody></table></li></ul>";
						}else{
							content += "<ul class='round' ><li class='title mb'><span>"+rows[i].appointmentTheme+"</span></li><li class='nob'><table width='100%' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><td colspan='2'>"+rows[i].appointmentDesc+"</td></tr><tr><td colspan='2'>"+rows[i].appointmentAddress+"</td></tr><tr><td>"+peopleNum+"</td><td><div class='divTest' onclick='toAppointment(\""+rows[i].appointmentId+"\",\""+rows[i].appointmentTheme+"\");'>预约</div></td></tr></tbody></table></li></ul>";
						}
					}
					$("#content").append(content);
				}
				if((rows.length)<5){
					$('#load_more').hide();
				}
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
		
	    var page = 2;
	    $('#load_more').click(function(){
	        toggle('loading', 'to_loading');
	       $.ajax({
	    		url : "getAppointmentJsp.action",
	    		async : false,
	    		data : "pageSize=5&pageIndex="+page,
	    		dataType : "json",
	    		type : "post",
	    		success : function(data) {
	    			var rows=data;
					var content="";
					for(var i=0;i<rows.length;i++){
						var sum=rows[i].appointmentSum;
						var count=rows[i].appointmentCount;
						var peopleNum=count+"人/"+sum+"人";
						if(sum==count){
							content += "<ul class='round' ><li class='title mb'><span>"+rows[i].appointmentTheme+"</span></li><li class='nob'><table width='100%' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><td colspan='2'>"+rows[i].appointmentDesc+"</td></tr><tr><td colspan='2'>"+rows[i].appointmentAddress+"</td></tr><tr><td>"+peopleNum+"</td><td><div class='divTest2'>预约</div></td></tr></tbody></table></li></ul>";
						}else{
							content += "<ul class='round' ><li class='title mb'><span>"+rows[i].appointmentTheme+"</span></li><li class='nob'><table width='100%' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><td colspan='2'>"+rows[i].appointmentDesc+"</td></tr><tr><td colspan='2'>"+rows[i].appointmentAddress+"</td></tr><tr><td>"+peopleNum+"</td><td><div class='divTest' onclick='toAppointment(\""+rows[i].appointmentId+"\",\""+rows[i].appointmentTheme+"\")'>预约</div></td></tr></tbody></table></li></ul>";
						}
					}
					$("#content").append(content);
	    			if((rows.length)<5){
	    				$('#load_more').hide();
	                    return;
	    			}
	    			page++;
	    			toggle('to_loading', 'loading');
	    		},
	    		error : function(data) {
	    			alert("网络繁忙!请稍后再试!");
	    		}
	    	});
	           
	    });
	});
	function toAppointment(a,b){
		var r=confirm("确定要预约:"+b+"的活动吗");
	     if (r==true){
	    	 $.ajax({
	 			url : "saveAppointmentRecord.action",
	 			async : false,
	 			data : "appointmentId="+a+"&openId="+openId,
	 			dataType : "json",
	 			type : "post",
	 			success : function(data) {
					if(data.codeInfo==1){
						alert(data.codeResp);
						//var divtest=$("div.divTest");
						//for(var i=0;i<divtest.length;i++){
							//$(divtest[i]).attr("class","divTest2");
						//}
						//WeixinJSBridge.call('closeWindow');
						window.location.href="doAppointmentRecordJsp.action?openId="+openId; 
					}else{
						alert(data.codeResp);
					}
	 			},
	 			error : function(data) {
	 				alert("网络繁忙!请稍后再试!");
	 			}
	 		});
	     }else{
	     	
		 }
	}
	/*function test(){
		var divtest=$("div.divTest");
		for(var i=0;i<divtest.length;i++){
			$(divtest[i]).attr("class","divTest2");
		}
	}*/
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
.divTest{ 
	width:50%; 
	height:15px; 
	padding:5px;border:#909090 1px solid;background:#179F00;color:#333;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);/*ie*/
	-moz-box-shadow: 1px 1px 8px #909090;/*firefox*/
	-webkit-box-shadow: 1px 1px 8px #909090;/*safari或chrome*/
	box-shadow:1px 1px 8px #909090;/*opera或ie9*/
	border-radius: 10px;
	margin:auto;
	text-align:center;
	line-height:15px;
	color:#F2F2F2;
	font-size:13px;
}

.divTest2{ 
	width:50%; 
	height:15px; 
	padding:5px;border:#909090 1px solid;background:#919191;color:#333;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);/*ie*/
	-moz-box-shadow: 1px 1px 8px #909090;/*firefox*/
	-webkit-box-shadow: 1px 1px 8px #909090;/*safari或chrome*/
	box-shadow:1px 1px 8px #909090;/*opera或ie9*/
	border-radius: 10px;
	margin:auto;
	text-align:center;
	line-height:15px;
	color:#F2F2F2;
	font-size:13px;
}
.nob td{
height:30px;
}
</style>

</head>
<body id="img-content">
<div style="width:100%;" >
	<div id="titleDiv" >
		<span style="font-size:18px;color:#FFFFFF;">满12分预约</span>
	</div>
</div>
		<div class="cardexplain" id="content">
			
		</div>
<div style="text-align:center;margin-bottom:10px;" >
    <a id="load_more"  href="javascript:void(0);"><span id="to_loading" style="color:#B0B0B0">点击查看更多</span><span id="loading"  style="display: none;">正在加载...</span></a>
</div>



</body>
</html>