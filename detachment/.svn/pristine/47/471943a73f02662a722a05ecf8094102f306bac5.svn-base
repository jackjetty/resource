<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="${pageContext.request.contextPath}/css/introduct.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
<title>获选人信息</title>
<script>
$(document).ready(function(){ 
	$("#introP").html($("#intro").val());
});
</script>
</head>
<body>
<div class="page">
	<div class="J_mess" style="margin-top:0;">
    	<div class="J_top">
<%--         	<div class="J_tL">
            	<span>基本信息</span>
            </div>
            <div class="J_tR">
            </div> --%>
            <img src="${pageContext.request.contextPath}<s:property value="candidate.photo"/>" class="img1"/>
        </div>
    <div class="J_cent">
    	<div class="J_cL">
        	<div class="one">
            	<h3><span></span><s:property value="candidate.name"/> </h3>
           <%--      <h4><span>性别:</span><s:property value="candidate.sex"/> </h4> --%>
            </div>
                         <div class="one">
             <h3><s:property value="candidate.workUnit"/> </h3>
             </div>
            <div class="one">
            	<h3>在本岗位工作时间:<s:property value="candidate.startWork"/> </h3>
            </div>
             <div class="one">
            <h3>从业感悟:<s:property value="candidate.feeling"/> </h3>
             </div>
<%--             <div class="one">
            	<h3><span>民族:</span><s:property value="candidate.nation"/> </h3>
                <h4><span>出生年月:</span><s:property value="candidate.birth"/> </h4>
            </div>
            <div class="one">
            	<h3><span>政治面貌:</span><s:property value="candidate.politicsStatus"/> </h3>
                <h4><span>文化程度:</span><s:property value="candidate.degree"/> </h4>
            </div> --%>
        </div>
<%--       <div class="J_cR">
        	<img src="${pageContext.request.contextPath}<s:property value="candidate.photo"/>" class="img1"/>
        </div>  --%>
    </div>
    
<%--     <h5><b>联系电话:</b><s:property value="candidate.tel"/> </h5>  --%>
    </div>
    <div class="J_mess">
<%--     	<div class="J_top">
        	<div class="J_tL">
            	<span>单位地址</span>
            </div>
            <div class="J_tR">
            </div>
        </div> --%>
    </div>
    <div class="J_mess">
    	<div class="J_top">
        <%-- 	<div class="J_tL">
            	<span>获奖情况</span>
            </div>
            <div class="J_tR">
            </div> --%>
             <img src="../image/vote/rongyu.png" class="img2"/>
            <h5>获奖情况</h5>
        </div>
        <s:iterator value="candidate.awards" var="p">
       <p><s:property value="#p.desc_"/></p>
        </s:iterator>
    </div>
<%--     <div class="J_mess">
    	<div class="J_top">
        	<div class="J_tL">
            	<span>从业感悟</span>
            </div>
            <div class="J_tR">
            </div>
        </div>
       <p><s:property value="candidate.feeling"/> </p>
    </div> --%>
    <div class="J_mess">
    	<div class="J_top">
 <%--        	<div class="J_tL">
            	<span>简要事迹</span>
            </div>
            <div class="J_tR">
            </div> --%>
            <img src="../image/vote/jianjie.png" class="img2"/>
            <h5>简要事迹</h5>
        </div>
       <div class="two" id="introP"></div>
       <input type="hidden" value=<s:property value="candidate.intro"/> id="intro">
    </div>
</div>
</body>
</html>
