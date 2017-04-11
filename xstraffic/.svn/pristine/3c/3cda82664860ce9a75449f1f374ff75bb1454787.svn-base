<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="js/idcard.js" type="text/javascript"></script>
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var openId='${openId}';
var toUrl="getOnlineStudy.action";
$(function(){
	if(openId!=""){
		var hm=getPhoneNumber(openId);
		$("#phoneNumber").val(hm.phoneNumber);
	}
	$("#studyType").val("0");
	$("#showcard").click(function() {
		var sType=$("#studyType").val();
		if(sType=="1"){
			var  studyTypeNum=$("#studyTypeNum").val();
			window.location.href=toUrl+"?studyTypeNum="+studyTypeNum;
		}else if(sType=="2"){
			var idCard=$("#idCard").val();
			if(idCard==""){
				wenben("请填写身份证号");
				return false;
			}
			 var checkFlag = new clsIDCard(idCard);    
				if(!checkFlag.IsValid()){ 
					wenben("请检查身份证号");
				    return false; 
			}
			window.location.href=toUrl+"?idCard="+idCard+"&openId="+openId;
		}else{
			var idCard=$("#idCard").val();
			if(idCard==""){
				wenben("请填写身份证号");
				return false;
			}
			 var checkFlag = new clsIDCard(idCard);    
			if(!checkFlag.IsValid()){ 
				wenben("请检查身份证号");
			    return false; 
			}
			var phoneNumber=$("#phoneNumber").val();
			if(phoneNumber==""){
				wenben("请填写手机号");
				return false;
			}
			if(phoneNumber.length<11){
				wenben("请正确填写手机号");
				return false;
			}
			window.location.href=toUrl+"?idCard="+idCard+"&phoneNumber="+phoneNumber+"&studyTypeNum=50&openId="+openId;
		}

	});
	
	function getPhoneNumber(openId){
		var row=null;
		$.ajax({
			url : "getPhoneNumber.action",
			async : false,
			data : "openId="+openId,
			dataType : "json",
			type : "post",
			success : function(data) {
				row=data;
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		return row;
	}
	
	
	
	
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
function changeType(){
	var type=$("#studyType").val();
	if(type=="1"){
		//var conten="<select id='studyTypeNum' class='px'><option value='0'>体验版10题</option><option value='1'>体验版20题</option></select>";
		$("#studyTypeNum").show();
		$("#liCard").hide();
		$("#liPhone").hide();
		$("#showcard").val("开始学习");
		toUrl="getOnlineStudy.action";
	}else if(type=="2"){
		$("#studyTypeNum").hide();
		$("#liCard").show();
		$("#liPhone").hide();
		$("#showcard").val("查询");
		toUrl="doStudyHistory.action";
	}else{
		$("#studyTypeNum").hide();
		$("#liCard").show();
		$("#liPhone").show();
		$("#showcard").val("开始学习");
		toUrl="getOnlineStudy.action";
	}
	
}

</script>
<style type="text/css">
.redfont {
	color: red;
}
</style>
</head>
<body id="onlinebooking" style="">
<img src="image/denglu.jpg" width="100%" alt='' />
		<div class="cardexplain" id="content">
			<ul class="round">
				<li class="title mb"><span class="none">请填写个人信息以便查询</span></li>
				<li class="nob">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="kuang">
						<tbody>
							<tr>
								<th id="th_xsxm">学习类型:</th>
								<td id="tdStudyType"><select id="studyType" class="px" onchange="changeType()" style="background-image:url(images/xiala.png); background-repeat:no-repeat; background-position:right center;" >
									<option value="0">正式版</option>
									<option value="1">体验版</option>
									<option value="2">查看历史记录</option>
								</select>
								<select id="studyTypeNum" class="px" style="display:none;background-image:url(images/xiala.png); background-repeat:no-repeat; background-position:right center;">
									<option value="5">体验版5题</option>
									<option value="10">体验版10题</option>
								</select>
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
								<td><input  class="px" id="idCard" type="text">
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
								<th id="th_jzxm">手机号:</th>
								<td><input  class="px" id="phoneNumber" type="text">
								</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>

			<div class="footReturn">
				<input type="button" id="showcard" class="submit"
					style="width: 100%" value="开始考试">
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
		<div id="testdiv"></div>
</body>
</html>
