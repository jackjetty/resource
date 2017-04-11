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
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<title>三有三好投票首页</title>
<script>
  var count=0;
	function disable(){
/* 	count=0;
	  var box = "check";
	  var pig = "Hiden";
	  for(var i=0;i<=19;i++){
		  var x = box+i;
		  var pic = pig+i;
		  var obj = document.getElementById(x);
		  var pObj = document.getElementById(pic);
		 if(obj.disabled==true){
			obj.disabled=false;
			pObj.style.display="none";
			button.value="取消选择";
			button.style.color="red";
		  }else{
			obj.disabled=true; 
			pObj.style.display="none";
			button.value="点击选择";
			button.style.color="#4ab4de";
			}
		} */
		window.location.href="voteIntro.html";
  }

function change(t){
//	var checkBox =  document.getElementById("check"+t.id).disabled;
	var check =  document.getElementById("Hiden"+t.id).style.display;
	var obj = document.getElementById("Hiden"+t.id);
	var cObj = document.getElementById("check"+t.id);
//	if(checkBox == false){
		if(check == "none"){
			obj.style.display = "block";
			cObj.checked = true;
			count++;
		}else{
			obj.style.display = "none";
			cObj.checked = false;
			count--;
		}
		document.getElementById("startBtn").value="开始投票("+count+")";
//	}
	
} 

function start_(){
	var eles=document.getElementsByName("hobbys");
	var checkCount=0;
	for(var i=0;i<eles.length;i++){
		if(eles[i].checked){
			checkCount++;
		}
	}
	if(count!=10){	
		 alert("请选择10人投票,已选"+count+"人");
		 return;
	}else{
		document.getElementById("startBtn").disabled=true;
		document.getElementById('voteform').submit();
	}
}

function getCandidateDetail(c_id){
	window.location.href="getCandidateDetail.action?id="+c_id;
	/* if(document.getElementById("button").value=='点击选择'){
	}else return; */
}
</script>
</head>
<body>
<div class="page">
<form action="submitVote.action" id="voteform" method="post">
	    <div class="footer">
    	<div class="bottomLeft">
        	<input type="button" id="button" onclick="disable()" value="返回首页"/>
        </div>
        <div class="bottomRight">
        	<input type="button" value="开始投票" id="startBtn" onclick="start_()"/>
        </div>
    </div>
	<div class="main">
	 <s:iterator value="candidates"  status="count">
	 	<s:if test="#count.modulus(3)==1">
	   <div class="con">
        <a href="javascript:void(0)">
        	<div class="conLeft" id=<s:property value="#count.index"/>  onclick="change(this)">
            	<img src="${pageContext.request.contextPath}<s:property value="photo"/>"/>
                <div class="conBottom">
                	<h3><s:property value="sortId"/>&nbsp<s:property value="name"/></h3>
                     <div class="test">
                    	<%-- <span class="S1"><s:property value="workUnit"/></span> --%>
                        <span class="S2" onclick="javascript:getCandidateDetail(<s:property value="c_id"/>)">[详细介绍]</span>
                    </div> 
                    
                </div>
                <input type="checkbox" name="hobbys" value=<s:property value="c_id"/> class="checkbox" id="check<s:property value="#count.index"/>">
                <div class="Z" id="Hiden<s:property value="#count.index"/>" style="display: none">
                	<div class="L"></div>
                	<img src="${pageContext.request.contextPath}/image/check.png" class="Y"/>
                </div>
            </div>
            </a>
	 	</s:if>
	 	<s:if test="#count.modulus(3)==2">
	 		<a href="javascript:void(0)">
            <div class="conC" id=<s:property value="#count.index"/> onclick="change(this)">
            	<img src="${pageContext.request.contextPath}<s:property value="photo"/>"/>
                <div class="conBottom">
                	<h3><s:property value="sortId"/>&nbsp<s:property value="name"/></h3>
                     <div class="test">
                    	<%-- <span class="S1"><s:property value="workUnit"/></span> --%>
                        <span class="S2" onclick="javascript:getCandidateDetail(<s:property value="c_id"/>)">[详细介绍]</span>
                    </div>
                </div>
                <input type="checkbox" name="hobbys" value=<s:property value="c_id"/>  class="checkbox" id="check<s:property value="#count.index"/>">
                <div class="Z"  id="Hiden<s:property value="#count.index"/>"  style="display: none">
                	<div class="L"></div>
                	<img src="${pageContext.request.contextPath}/image/check.png" class="Y"/>
                </div>
            </div>
            </a>
	 	</s:if>
	 	<s:if test="#count.modulus(3)==0">
	 		<a href="javascript:void(0)">
            <div class="conRight" id=<s:property value="#count.index"/> onclick="change(this)">
            	<img src="${pageContext.request.contextPath}<s:property value="photo"/>"/>
                <div class="conBottom">
                	<h3><s:property value="sortId"/>&nbsp<s:property value="name"/></h3>
                     <div class="test">
                    	<%-- <span class="S1"><s:property value="workUnit"/></span> --%>
                        <span class="S2" onclick="javascript:getCandidateDetail(<s:property value="c_id"/>)">[详细介绍]</span>
                    </div>
                </div>
                <input type="checkbox" name="hobbys" value=<s:property value="c_id"/>  class="checkbox" id="check<s:property value="#count.index"/>">
                <div class="Z"  id="Hiden<s:property value="#count.index"/>"  style="display: none">
                	<div class="L"></div>
                	<img src="${pageContext.request.contextPath}/image/check.png" class="Y"/>
                </div>
            </div>
            </a>
        </div>
	 	</s:if>
	</s:iterator>

    </div>
   <!--  <input type="hidden" value=<s:property value="oauth.getOpenid()"/> name="openId" > -->
  </form>

</div>
</body>
</html>