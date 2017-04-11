<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>打印</title> 
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script> 
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/common.css"  />
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/tip.css"  />
<style>
     .pleft {
		    float: right;
		    overflow: hidden;
		    width: 100%;
		    background: none repeat scroll 0% 0% rgb(246, 251, 255);
		    height: 50px;
			border-width: 2px 1px medium;
			border-style: solid solid none;
			border-color: rgb(0, 114, 188) rgb(157, 179, 219) -moz-use-text-color;
		}  
 #j-im.djd-im, #j-im.djd-im:hover, #j-im.djd-im:visited, #consult #j-im-extra.djd-im, #consult #j-im-extra.djd-im:hover, #consult #j-im-extra.djd-im:visited {
    text-decoration: none;
    cursor: pointer;
}
#j-im.djd-im {
}
.djd-im, a.d-offline {
    float:right;
    display: inline-block;
    padding-left: 27px;
    margin:10px 20px 10px 20px;
    width: 40px;
    height: 24px;
    line-height: 24px;
    background: url("${pageContext.request.contextPath}/image/button/back.jpg") no-repeat scroll 0px 0px transparent;
}
.djd-im b {
    color: rgb(255, 255, 255);
    font-weight: 400;
}
a {
    color: rgb(102, 102, 102);
    text-decoration: none;
}
</style> 
</head>
<body>
<div class="pleft" style="background-color: #dbf0fe;">
         <div id="btnReturn" style="float: right; margin-top: 11px;" onclick="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/image/button/return.png" style="float:left;"><input class="input" type="button" value="返回" style="width:55px; height:24px; background-color:#369be9; color:#FFF; border:1px #b5c5fd solid; margin-top: 3px; margin-right:20px; float:right; cursor:pointer;" onmouseover="this.style.backgroundColor='#1c8fe7'" onmouseout="this.style.backgroundColor='#369be9'"></div>
</div>
 
<iframe id="main" name="main"  width="100%"  frameborder="0" border=0 scrolling="no" marginwidth="0" marginheight="0" src="${pageContext.request.contextPath}<s:property value="#request.pdfPath"/>"></iframe> 
</body>
</html>
<script type="text/javascript">
       $(document).ready(function() {
    	   var fullHeight = $(window).height();
           var fullWidth=$(window).width(); 
           $('#main').css({'height':fullHeight-70},{'width':fullWidth}); 
       });
</script>