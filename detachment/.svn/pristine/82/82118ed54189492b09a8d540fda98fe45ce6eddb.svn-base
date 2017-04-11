<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title id="titleName">我的推广</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="../web/css/paxy.css" rel="stylesheet" type="text/css">
<script src="../web/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="../web/js/zepto.min.js"></script>
<style> 
.redfont{
		color: red;
	}
.liTitle{
	background-color: #5DD300;
    background-image: -moz-linear-gradient(center bottom , #5DD300 0%, #5DD300 100%);
    margin-bottom: 4px;
}
.xk{
color:#FFFFFF;font-size:18px;
}
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
</style>
<body id="img-content" style="width:100%;background:#ffffff">
<div style="width:100%;" >
	<div id="titleDiv" >
		<span style="font-size:18px;color:#FFFFFF;">我的推广</span>
	</div>
	<div class="cardexplain" id="content">
			<ul class="round">
				<li class="title mb"><span class="none">推荐人信息</span></li>
				<li class="nob"> 
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_personalName">姓名：</th>
								<td><input name="personalName" class="px" id="personalName" type="text" value="<s:property value="#request.result['personalName']" />" >
								</td>
							</tr>
						</tbody>
					</table>
				  </li>
				  <li class="nob"> 
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th id="th_personalPhone">联系号码：</th>
								<td><input name="personalPhone" class="px" id="personalPhone" type="text" value="<s:property value="#request.result['personalPhone']" />" maxlength="11" min="11">
								</td>
							</tr>
						</tbody>
					</table>
				  </li>
			</ul>
		   <div class="footReturn">
				<input type="submit" id="showcard" class="submit"
					style="width: 100%" value="提交">
				<div class="window" id="windowcenter">
					<div id="title" class="wtitle">
						操作提示<span class="close" id="alertclose"></span>
					</div>
					<div class="content">
						<div id="txt"></div>
					</div>
				</div>
			</div>  
			<ul class="round">
			     <li class="title mb"><span class="none">我的二维码</span></li>
			     <li  >
			        <div align="center"  >
			         <img    src="${pageContext.request.contextPath}/image/<s:property value="#request.result['picPath']" />" alt="我的二维码" />
			         </div>
			     </li>
			</ul> 
			<fieldset style="border:1px solid #C6C6C6;border-radius:5px;"><legend>活动细则</legend>
			        <div style="padding: 3px 0px 0px 10px " >活动时间：</div>
			        <div style="padding: 3px 0px 0px 30px " >2014年8月8日至2014年8月28日</div>
			        <div style="padding: 3px 0px 0px 10px " ><a href="http://mp.weixin.qq.com/s?__biz=MzA4NzUzMDAwOA==&mid=200621374&idx=3&sn=038441b7b0ae94c8965e56e7f4cb1244&scene=4#wechat_redirect" style="color:#336699">点击查看活动细则</a></div>
			        <div style="padding: 3px 0px 0px 30px " >注：活动解释权归绍兴支队所有。</div></fieldset> 
				
</div>
</body>
</html>
<script type="text/javascript">
    var oLay = document.getElementById("overlay");
$(document).ready(function() {
    $("#showcard").click(function() {
		$(".redfont").removeClass("redfont");
		 
		 
		var  personalName=$.trim($("#personalName").val());
		if (  personalName=="") {
			$("#th_personalName").addClass("redfont");
			alert('姓名不能为空');
			return false;
		}
		 
		
		var personalPhone = $.trim($("#personalPhone").val());
		if (personalPhone == '' || personalPhone.length!=11) {
			$("#th_personalPhone").addClass("redfont");
			alert('手机不能为空且是11位数字');
			return false;
		}
		
		for (var i = 0; i < personalPhone.length; i++) {  
             //charAt()获取指定位置字符串,charCodeAt()返回该字符串的编码  
                //0的ASCII是48,9的ASCII是57  
             var code = personalPhone.charAt(i).charCodeAt(0);  
             if (code < 48 || code > 57) {  
            	 $("#th_personalPhone").addClass("redfont");
            	 alert('手机只能是11位数字');
				 return false;  
             };
        } 
		 
		 
		$.ajax({
			url: "${pageContext.request.contextPath}/wap/updatePersonalInfo.action",
			data:{openId:"<s:property value="#request.result['openId']"/>",personalName:personalName,personalPhone:personalPhone},
			dataType:"json",
			type:"post",
			success:function(data){
				alert(data.respInfo);
				 
	       			return false; 
	      }
		});
	});
});
$("#windowclosebutton").click(function() {
	$("#windowcenter").slideUp(500);
	oLay.style.display = "none";

});
$("#alertclose").click(function() {
	$("#windowcenter").slideUp(500);
	if(respCode == 0 && submit == true){
		WeixinJSBridge.call('closeWindow');
	}else{
		respCode = null;
		submit = false;
		return false;
	}

});
function alert(title) {
	$("#windowcenter").slideToggle("slow");
	$("#txt").html(title);
	//$("#windowcenter").hide("slow"); 
	//setTimeout('$("#windowcenter").slideUp(1500)', 5000);
};
</script>


