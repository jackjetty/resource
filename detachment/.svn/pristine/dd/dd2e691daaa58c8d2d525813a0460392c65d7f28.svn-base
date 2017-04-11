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
var identityCard='${identityCard}';
$(function(){
	if(identityCard!=null && identityCard!=""){
		$.ajax({
			url : "getPeopleInfoByIdentityCard.action",
			async : false,
			data : "identityCard=" + identityCard,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					var tfs=data.respInfo;
					$("#userName").val(tfs.userName);
					$("#phoneNumber").val(tfs.phoneNumber);
					$("#identityCard").val(tfs.identityCard);
					$("#fileNum").val(tfs.fileNum);
				}
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
	}
	
	
	
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
		$.ajax({
			url : "updateFucScodeInfo.action",
			async : false,
			data : "identityCard=" + identityCard +"&userName="+userName+"&phoneNumber="+phoneNumber,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					wenben("保存成功!");
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
		/*$("#windowcenter").slideUp(500);
		oLay.style.display = "none";*/
		history.go(-1);

	});
}); 	
function wenben(title) {
	$("#windowcenter").slideToggle("slow");
	$("#txt").html(title);
}


</script>
<style type="text/css">
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
.nav-back {
    display: inline-block;
    height: 26px;
    padding: 7px 0px 7px 6px;
    color: rgb(255, 255, 255);
    position: absolute;
    top: 0px;
    left: 0px;
    overflow: hidden;
}
a {
    color: rgb(59, 89, 152);
    outline: medium none;
    text-decoration: none;
}
.nav-back .arrow-left {
    float: left;
    width: 12px;
    height: 26px;
    background-position: 50% 50%; 
}
.icon-arrow {
    background: url("image/button/bg_arrow.png")  no-repeat;
	background-size:80px auto;  
}

.nav-back span {
    float: left;
    padding: 5px 5px 5px 2px;
    background: none repeat scroll 0% 0%  #8BE553;
    font-size: 14px;
    line-height: 16px;
}
</style>
</head>
<body id="onlinebooking" style="">
<div style="width:100%;" >
	<div id="titleDiv" >
		<a class="nav-back" onclick="javascript:history.go(-1);">
		<img src="image/fullScode/icon.png" >
          </a>
		<span style="font-size:18px;color:#FFFFFF;margin-right:10px;" id="addressName">用户信息</span>
	</div>
</div>


		<div class="cardexplain" id="content" style="margin:0 10px 20px 9px">
		<img src="image/fullScode/stydy1.jpg" width="100%" style="border-radius:5px;margin-bottom:10px;margin-top:10px;" />
			<ul class="round">
				<li class="title mb"><span class="none">以下是您填写的信息</span></li>
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
				<li class="nob" id="liCard">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_xsxm">身份证号:</th>
								<td><input  class="px" id="identityCard" readonly="readonly" type="text">
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
								<td><input  class="px" id="fileNum" readonly="readonly" type="text">
								</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>

			<div class="footReturn">
				<input type="button" id="showcard" class="submit"
					style="width: 100%" value="保存">
				<div class="window" id="windowcenter">
					<div id="title" class="wtitle">
						操作提示<span class="close" id="alertclose"></span>
					</div>
					<div class="content">
						<div id="txt"></div>
					</div>
				</div>
			</div>
			 
		</div>
</body>
</html>
