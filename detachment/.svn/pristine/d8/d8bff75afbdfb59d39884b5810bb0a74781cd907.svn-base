<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>微调查</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script src="${pageContext.request.contextPath}/js/locallib.js"></script>
<script src="${pageContext.request.contextPath}/js/msgbox.js"></script>
<style>
body{ margin:0; padding:0; background-color:#f1f0f0;}
a{ text-decoration:none; display:block;}
.header{ height:40px; background-color:#03cf03; width:100%; position:relative;position:fixed;
		top:0px;
		_position: absolute;
		_top: expression_r(documentElement.scrollTop + "px");}
.header h2{ margin:0;color:#ffffff; font-size:18px;line-height:40px; text-align:center; font-weight:bold;font-family:“微软雅黑”; }
.content{ width:96%; margin:50px 2%;}
.content h5{ font-family:“微软雅黑”; color:#02a3ff; font-size:16px; margin-top:15px; margin-bottom:0;}
.content p{ font-family:“黑体”; font-size:17px; margin-top:10px; margin-bottom:5px;}
select{ width:85%; height:25px;border:1px #d3d3d3 solid;border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px; margin-left:3em;}
.IMG1{float:left; width:18px; height:18px;  margin-top:2px; margin-left:2em; }

.word{font-size:15px;line-height:22px;color:grey; 
margin-left:5px;font-family:“黑体”; float:left;}
.password{ float:left;width:50%; overflow:hidden;}
.password1{ float:left;width:100%; overflow:hidden;}
.img{ width:96%; margin:2% auto;}
.img img{ width:100%;}
.button{ width:96%; margin:0 auto; background-color:#00e700; height:35px; line-height:35px; text-align:center; color:#ffffff;border:1px #00e700 solid;border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px; margin-top:50px;}
textarea{width:96%; margin:10px 2% ; height:200px;border:1px #d3d3d3 solid;border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px;}
</style>
</head>
<body>
<div class="header">
	<h2>微调查</h2>
	<a style='display:none;' href="http://www.baidu.com" onmousedown="mouseDown()" onmouseup="mouseUp()"><img src="${pageContext.request.contextPath}/image/investigate/tu.png" style=" position:absolute; right: 10px; top: 5px;"></a>
</div>
<div class="content">
	<h5>三、交管意见与建议</h5>
	<textarea id="suggestion" name="suggestion"></textarea>
	<div id="submitAnswer" class="button">提&nbsp;&nbsp;交</div>	
</div>
<script>
	var openId = '<%=request.getParameter("openId")%>';
	
	$(function() {
		$("#submitAnswer").click(function() {
			if (openId == "null") {
				$.dialog.alert("温馨提示", "请登陆微信在填写本表单");
				/* $.dialog.alert("请登陆微信在填写本表单", function() {
					
				}); */
				return false;
			}
			
			$.ajax({
				url : "${pageContext.request.contextPath}/web/submitExamine.action",
				data : {
					openId : openId,
					suggestion : $("#suggestion").val()
				},
				dataType : "json",
				type : "post",
				success : function(data) {
					if (data.respCode == 0) {
						$.dialog.alert("提交成功", "感谢您的参与和支持!!");
						setTimeout(function() {
							WeixinJSBridge.call('closeWindow');
						}, 1000);
					} else {
						getLocal().alert({ text : data.respInfo});
					}
				}
			}); 
		});
	});
	
	function mouseDown() {
		$("#tu").attr("src", "../image/investigate/tu1.png");
	}

	function mouseUp() {
		$("#tu").attr("src", "../image/investigate/tu.png");
	}
</script>
</body>
</html>