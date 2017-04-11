<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="${pageContext.request.contextPath}/js/studaplayvotepic/votecss.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/studaplayvotepic/Studyplay_vote.js"></script>
<style>
*{
	margin:0;
	padding:0;
}
body{
	background-color:white;
	text-align:center;
}
.header{
	overflow:hidden;
	width:130px;
	margin:20px auto 5px auto;
}
.header img{
	float:left;
	height:30px;
	margin-right:10px;
}

.header span{
	float:left;
	font-size:18px;
	color:#512a3e;
	line-height:30px;
	font-family:"微软雅黑";
}
.t{
	width:92%;
	margin:0 auto;
}
.t .q{
	width:100%;
}
.dd{
	padding:3px 0;
}
.dd .fl{
	width:20%;
	float:left;
}
.dd .fl1{
	width:12%;
	float:right;
}
.dd span{
	width:100%;
	display:block;
	font-size:14px;
	color:#484848;
	font-family:"微软雅黑";
	line-height:18px;
	text-align:left;
}
.dd b{
	width:100%;
	display:block;
	font-size:14px;
	color:#484848;
	font-family:"Arial";
	line-height:18px;
	text-align:center;
	font-weight:normal;
}

.dd .outbar{
	width:67%;
	float:left;
	height:18px;
		border:1px solid #b4b4b4;
}
.dd .inbar{
	height:18px;
}

</style>
<title>票数显示</title>
<script type="text/javascript">
	$(document).ready(function(){
		 $.ajax({  
		        async : false,  
		        cache:false,  
		        type: 'POST',  
		        dataType : "json",  
		        url:"${pageContext.request.contextPath}/wap/getVoteRes.action", 
		        error: function () {
		            alert('请求失败');  
		        },  
		        success:function(data){
		        	$("#z").study_vote(data);
				}});
		    });
</script>

</head>
<body  style="zoom: 1;">
     <div class="header">
     <s:iterator value="map.keySet()" id="key">  
     <s:if test="%{#key=='success'}">
    	<img src="../image/vote/d.png"/>
     </s:if>
     <s:else>
     	<img src="../image/vote/x.png"/>
     </s:else>
       <span><s:property value="map.get(#key)"/></span>
       </s:iterator> 
</div>
<div id='z' class='t'></div>
</body>
</html>