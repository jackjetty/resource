<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习登陆</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script src="js/jquery.js" type="text/javascript"></script>
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var openId='${openId}';
$(function(){
	/*if(openId!=null && openId!=""){
		$.ajax({
			url : "toAutomaticLogin.action",
			async : false,
			data : "openId=" + openId,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					window.location.href="doFucScode.action?identityCard="+data.idCard;
				}else if(data.respCode==1){
					window.location.href="doAppointmentJsp.action?openId="+openId;
				}else if(data.respCode==2){
					window.location.href="doAppointmentRecordJsp.action?openId="+openId;
				}else if(data.respCode==3){
					window.location.href="page/appointmentAudit.html";
				}
			},
			error : function(data) {
				//alert("网络繁忙!请稍后再试!");
			}
		});
	}*/
	
	$("#showcard").click(function() {
		var userName=$("#userName").val();
		var phoneNumber=$("#phoneNumber").val();
		if(userName==""){
			wenben("请填写姓名");
			return false;
		}
		if(phoneNumber==""){
			wenben("请填写联系号码");
			return false;
		}
		var identityCard=$("#identityCard").val();
		if(identityCard==""){
			wenben("请填写身份证号");
			return false;
		}
		if(identityCard.length!=15 && identityCard.length!=18){
			wenben("身份证号为15位或18位");
			return false;
		}
		var fileNum=$("#fileNum").val();
		if(fileNum==""){
			wenben("请填写档案号");
			return false;
		}
		if(fileNum.length!=12){
			wenben("档案号为12为数字");
			return false;
		}
		
		$.ajax({
			url : "toFucScodeLogin.action",
			async : false,
			data : "identityCard=" + identityCard + "&fileNum=" + fileNum + "&openId=" + openId
					+"&userName="+userName+"&phoneNumber="+phoneNumber,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.code==0){
					window.location.href="doFucScode.action?identityCard="+identityCard+"&openId="+openId;
				}else{
					wenben(data.codeInfo);
				}
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
	});
	$("#windowclosebutton").click(function() {
		$("#windowcenter").slideUp(500);
		oLay.style.display = "none";

	});
	$("#alertclose").click(function() {
		$("#windowcenter").slideUp(500);
		oLay.style.display = "none";

	});
}); 	
function wenben(title) {
	$("#windowcenter").slideToggle("slow");
	$("#txt").html(title);
}


</script>
<style type="text/css">
.redfont {
	color: red;
}
</style>
</head>
<body id="onlinebooking" style="">
		<div class="cardexplain" id="content" style="margin:0 10px 20px 9px">
		<img src="image/fullScode/stydy1.jpg" width="100%" style="border-radius:5px;margin-bottom:10px;margin-top:10px;" />
			<ul class="round">
				<li class="title mb"><span class="none">请填写个人信息以便查询</span></li>
				<li class="nob" id="liUserName">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_xsxm">用户姓名:</th>
								<td><input  class="px" id="userName" type="text">
								</td>
							</tr>
						</tbody>
					</table>
				</li>
				
				<li class="nob" id="liCard">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_xsxm">身份证号:</th>
								<td><input  class="px" id="identityCard" type="text">
								</td>
							</tr>
						</tbody>
					</table>
				</li>
				<li class="nob" id="liPhone">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_jzxm">档案号:</th>
								<td><input  class="px" id="fileNum" type="text">
								</td>
							</tr>
						</tbody>
					</table>
				</li>
				<li class="nob" id="liPhoneNumber">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_xsxm">联系号码:</th>
								<td><input  class="px" id="phoneNumber" type="text">
								</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>

			<div class="footReturn">
				<input type="button" id="showcard" class="submit"
					style="width: 100%" value="开始学习">
				<div class="window" id="windowcenter">
					<div id="title" class="wtitle">
						操作提示<span class="close" id="alertclose"></span>
					</div>
					<div class="content">
						<div id="txt"></div>
					</div>
				</div>
			</div>
			<fieldset style="border:1px solid #C6C6C6;border-radius:5px;"><legend>简介</legend>
			        <div style="padding: 3px 0px 0px 10px " >6个学时折抵在校学习一次，执勤站岗一天（早晚高峰有两次）</div>
			        <div style="padding: 3px 0px 0px 10px " ><a href="page/studyGuide.html" style="color:#336699">点击查看学习细则</a></div>
			        <div style="padding: 3px 0px 0px 10px " >注：活动解释权归绍兴支队所有。</div></fieldset> 
			
			
		</div>
		<div id="testdiv"></div>
</body>
</html>
