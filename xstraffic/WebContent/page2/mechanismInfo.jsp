<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>象山交警</title>
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"> 	 
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<link rel="stylesheet" href="css/jquery.mobile-1.3.2.min.css" />
<script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="js/lodash.min.js" type="text/javascript"></script>
<script src="js/backbone-min.js" type="text/javascript"></script>
<script src="js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		url : "getMechanismInfo.action",
		async : false,
		data : "",
		dataType : "json",
		type : "post",
		success : function(data) {
			var rows=data;
			content="";
			for(var i=0;i<rows.length;i++){
				if(i==0){
					content += "<li class='ui-li ui-li-static ui-btn-up-c ui-first-child '><h3 class='ui-li-heading' style='width:100%; margin:0 auto; word-break:normal; white-space:normal;margin-bottom:5px;'>"+rows[i].organizationName+"</h3><p class='ui-li-desc' style='width:100%; margin:0 auto; word-break:normal; white-space:normal;'>地址："+rows[i].address+"</p>";
				}else if(i==rows.length-1){
					content += "<li class='ui-li ui-li-static ui-btn-up-c ui-last-child'><h3 class='ui-li-heading' style='width:100%; margin:0 auto; word-break:normal; white-space:normal;margin-bottom:5px;'>"+rows[i].organizationName+"</h3><p class='ui-li-desc' style='width:100%; margin:0 auto; word-break:normal; white-space:normal;'>地址："+rows[i].address+"</p>";
				}else{
					content += "<li class='ui-li ui-li-static ui-btn-up-c'><h3 class='ui-li-heading' style='width:100%; margin:0 auto; word-break:normal; white-space:normal;margin-bottom:5px;'>"+rows[i].organizationName+"</h3><p class='ui-li-desc' style='width:100%; margin:0 auto; word-break:normal; white-space:normal;'>地址："+rows[i].address+"</p>";
				}
				if(rows[i].alarmPhone!=''){
					var alarmPhone=rows[i].alarmPhone;
					content+="<p class='ui-li-desc'>报警电话：<a href='tel:"+alarmPhone+"'>"+alarmPhone+"</a></p>";
				}
				if(rows[i].complaintPhone!=''){
					var complaintPhone=rows[i].complaintPhone;
					content+="<p class='ui-li-desc'>联系电话：<a href='tel:"+complaintPhone+"'>"+complaintPhone+"</a></p>";
				}
				content+="</li>";
				
			}
			$("#infoUl").append(content);
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});

});

</script>
</head>
<body>
<div data-role="page" class="weixin">
	<div class="weixin" data-page="ElecPoliceAddress">
		<div data-role="header" data-theme="b">
			<h2 style="width:100%; margin:0 auto; word-break:normal; white-space:normal; padding:10px 0;">部门联系电话及地址</h2>
		</div>
		<div data-role="content" id="data-content" style="" id="ulDiv">
			<ul data-role="listview" data-inset="true" id="infoUl">
				
			</ul>
		</div>
	</div>
</div>
</body>
</html>