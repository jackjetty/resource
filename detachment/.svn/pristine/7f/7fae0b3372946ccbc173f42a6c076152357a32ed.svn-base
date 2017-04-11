<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>绍兴交警网上自助缴费</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/tencent/css/style.css" />
<link href="${pageContext.request.contextPath}/css/legal/use.css" type="text/css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tencent/msgbox.js" type="text/javascript"></script>

<style>
.tipbox{ background-color:#f0efef;overflow:hidden;}
.T,.B{ overflow:hidden;}
.T{ line-height:24px; text-align:center;}
h4{ margin:0; float:left; font-family:“宋体”; font-size:12px; line-height:24px;}
.list{ position:relative; border-bottom:dashed 1px #adacac; padding-bottom:10px;}
.all{ margin-left:10px; color:#028afd; ma rgin-top:20px; font-size:12px; }
#details{ color:#656464; margin-left:10px; margin-right:10px; margin-top:10px;}
h6{ font-size:14px; font-weight:normal; line-height:24px;}
.text{ position:relative;}
.pay{ position:absolute; right:10px; margin-top:-99px;}
</style>
</head>

<body>
<div class="header">
<div class="top">
	<h2>待处理记录</h2>
</div>
	<a  onclick="javascript:history.go(-1);" ><img src="${pageContext.request.contextPath}/image/legal/top_l.png" style="position:absolute; margin-top:-50px;"></a>
</div>
<div class="content">
	<div class="shuoming" style="padding:5px;">
    	<p style="font-size:14px; line-height:22px; margin-left:10px; margin-right:10px;"><s:property value="#request.result['content']"/></p>
    </div>
    
    
    <div class="tipbox" >
        	<a href="#" onclick="javascript:fnCanHandle();"><h4 id="tipcanHandle" style="float:left; width:49%; line-height:35px; text-align:center; color:black"><s:property value="#request.result['tipcanHandle']"/></h4></a>
            <div style="width:1%; float:left;line-height:35px; text-align:center;">|</div>
        	<a href="#" onclick="javascript:fnUnpaid();"><h4  id="tipunpaid" style="float:right; width:50%; line-height:35px; text-align:center; color:red;"><s:property value="#request.result['tipunpaid']"/></h4></a>
    </div>
     
  
 <div id="canHandleContent" class="main" style="width:100%;heigh:auto; ">
  <s:if test="!#request.result['canHandleInfo'].equalsIgnoreCase('')">
	<div class="BOX"  > 
	<ul>
	      <li><s:property value="#request.result['canHandleInfo']"/></li>
	 </ul>    
	</div>
</s:if>
  
  <s:iterator value="#request.result['canHandleList']" id="canHandleRecordWapJson">  
	<div class="BOX"  > 
      <ul>
        	<li onclick="select(this)" style="font-size:14px; margin-top:10px; margin-bottom:10px;">
        	<input type="checkbox" value="<s:property value='xh' /> " style="margin-right:20px;"/>
        	<s:property value='illegalActivities' /></li>
            <li style="margin-left:35px; font-size:12px; color:#9b9b9b;">罚金:  <s:property value='amount' /> 
             <span style="margin-left:25px; font-size:12px;color:#9b9b9b;"> 记分：<s:property value='illegalScore' /></li>
            <li style="margin-left:35px; font-size:12px; color:#9b9b9b;">日期:<s:property value='illegalTime' />  </li>
         
      </ul>
   	  <img  onclick="showDetail(this)" src="${pageContext.request.contextPath}/image/legal/down.png" class="IMG2" id="pic1"/>
    </div>
	<div   class="BOX1">
    	<p>违法地点：<s:property value='illegalSites' /> <br>
    	         违法条款：<s:property value='illegalClause' /> <br>
    	         处罚依据：<s:property value='basisPunishment' /> <br>
    	         采集机关：<s:property value='collectionAgency' />  
		</p>
    </div>
    </s:iterator> 
    <s:if test="#request.result['canHandleInfo'].equalsIgnoreCase('')">
		 <div class="footer">
		    <div class="button"><a href="#">下一步</a></div>
		</div>
	</s:if>
    
</div>



<div id="unpaidContent" class="main">
  <s:if test="!#request.result['unpaidInfo'].equalsIgnoreCase('')">
	<div class="BOX"  > 
	<ul>
	      <li><s:property value="#request.result['unpaidInfo']" /></li>
	 </ul>    
	</div>
  </s:if>
<s:iterator value="#request.result['unpaidList']" id="unpaidRecordWapJson">
      
   <div class="text">
    		<h6> <s:property value='illegalInfo'  escape="false" /> </h6>
	</div>
    
    <div class="pay">
    	<input type="button" onclick="javascript: fnPaid('<s:property value='jdsbh' />','<s:property value='orderId' />','<s:property value='orderTime' />');"  value="付款"  style=" line-height:20px; text-align:center; background-color:#fe660b; color:#ffffff; border:solid 1px #fe660b; width:60px; border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px;"/>
    </div> 
 </s:iterator> 



 
</div> 

</div>




</body>
</html>
<script type="text/javascript">
   
  $(function() {
    /* $(".main").find(":checkbox").attr("checked", "true"); */
    fnCanHandle();
    $(".button").click(function() {
      if ($(".main").find(":checked").length == 0) {
    	ZENG.msgbox.show("请选择！！", 5, 3000); 
        return false;
      }
      var xh = $(".main").find(":checked").val();
      
      window.location.href="${pageContext.request.contextPath}/wap/confirmPageLegal.action?xh="+xh;
       
    });
    
    
    
  });
  
  function select(btn) {
     $(".main").find(":checkbox").removeAttr("checked");
     
     $(btn).children(":checkbox").attr("checked", "true");
     return false;
  }
  
  function showDetail(btn) {
    if ($(btn).parents(".BOX").next().is(":visible")) {
      $(btn).parents(".BOX").next().hide();
    } else {
      $(btn).parents(".BOX").next().show();
    }
  }
   
  function fnPaid(jdsbh,orderId,orderTime){
	  
	  //window.location.href="${pageContext.request.contextPath}/wap/paidPageLegal.action?jdsbh="+jdsbh+"&orderId="+orderId+"&orderTime="+orderTime;
	  window.location.href="http://wscgs.sxga.gov.cn/zzwfcl/netpay/payServlet?jdsbh="+jdsbh+"&orderId="+orderId+"&orderTime="+orderTime;
	  
  }
  function fnCanHandle(){
	  $("#canHandleContent").show();
	  $("#unpaidContent").hide();
	  $("#tipcanHandle").css('color','red');
	  $("#tipunpaid").css('color','black');
	  
  }
  function fnUnpaid(){
	  $("#canHandleContent").hide();
	  $("#unpaidContent").show();
	  $("#tipcanHandle").css('color','black');
	  $("#tipunpaid").css('color','red');
  }
  
</script>

