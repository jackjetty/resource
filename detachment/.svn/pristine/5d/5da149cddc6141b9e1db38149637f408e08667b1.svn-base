<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title id="titleName">实时路况</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="MobileOptimized" content="480" />
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript">
	var unidNum = '${unidNum}';
	//var addressName = '${addressName}';
	$(function(){
		var imgadd="<img  src='http://www.sxga.gov.cn:8833/lwpsp/resource/checkImgVideo/"+unidNum+"/newVideo.jpg' style='width:100%;'>";
	    $("#imgDiv").html(imgadd);
	});
	function colseBack(){
		history.back();
	}
</script>
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
<body id="img-content">
<div style="width:100%;" >
	<div id="titleDiv" >
		<a class="nav-back" onclick="javascript:history.go(-1);">
		<img src="image/fullScode/icon.png" >
          </a>
		<span style="font-size:18px;color:#FFFFFF;margin-right:10px;" id="addressName">视频图像</span>
	</div>
</div>
		
		<div class="cardexplain" id="content">
		<span>视频图像详细信息:</span>
		<div style="width:100%;margin-top:10px;" id="imgDiv">
		
		</div>
		</div>




</body>
</html>