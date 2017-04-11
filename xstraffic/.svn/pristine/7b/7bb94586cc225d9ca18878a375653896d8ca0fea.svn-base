<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404 Not Found</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
body {
	font-size: 12px;
	font-family:Arial,宋体;
	margin:0px;
	padding:0px;
	-webkit-text-size-adjust:none;
	padding:70px 0px;
	color:#4F6F7D;
	background:#F6F6F6;
}
h1 {
	font-size:25px;
	color: #87A0A7;
	margin:20px 0px;
	padding:0px;
}
a {
	cursor: pointer;
	outline:none; 
	color:#7698A7;
	blr:expression(this.onFocus=this.blur());
	text-decoration: none;
}
pre {
	background:#fff;
	padding:20px;
	margin:20px 5%;
	width:82%;
	line-height:22px;
	font-family:Arial,宋体;
	border-bottom:9px solid #E7EFF1;
	box-shadow: 1px 0px 5px rgba(100, 100, 100, 0.3);
}
p {
	font-size:10px;
	_font-size:9px;
	margin:20px 5%;
	width:82%;
	color:#919191;
}
</style>
</head>
<body>
<pre>
<h1>404 Not Found</h1>
您所访问的资源已不存在。
查看更多请返回网站主页。<div><span class="times"><span id="time">6</span> 秒后</span></div>
» <a href="${pageContext.request.contextPath}/toMain.action">主页</a>
</pre>
<script>
	var oTime = document.getElementById('time');
	var oSecs = 6;
	function time() {
		oSecs --;
		if (oSecs < 0) {
			clearInterval();
			return;
		}
		
		if (oSecs == 0) {
			clearInterval();
			window.location.href = '${pageContext.request.contextPath}/toMain.action';
			//alert(1)
		}
		oTime.innerHTML = oSecs;
	}
	setInterval(time, 1000);
</script>
</body>
</html>