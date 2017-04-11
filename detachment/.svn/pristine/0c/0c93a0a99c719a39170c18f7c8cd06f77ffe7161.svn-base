<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线学习</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<link href="css/fullscode.css" rel="stylesheet" type="text/css" />
<link href='js/skin/skin.css' rel='stylesheet' type='text/css' />
<link href="css/public-v12.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/Cxc-Dialog.js" type="text/javascript"></script>
<script type="text/javascript">
var identityCard='${identityCard}';
var openId='${openId}';
var timer;
var c=0;
var seconds=30;
var timerDown;
var num=0;
function timedCount(){  
    min = parseInt(c / 60);// 分钟数  
    if(min>=60){  
        min=min%60;  
    }  
    lastsecs = c % 60;  
    var msg="本次学习时长<span style='color:Blue;'>"+min+"</span>分";
	$("#lblAskTime").html(msg);  
	c=c+1;
	if(c==600){
		saveFirstStudyTime();
	}
	timer=setTimeout("timedCount()",1000);    
}  
function saveFirstStudyTime(){
	$.ajax({
		url : "saveFirstStudyTime.action",
		async : false,
		data : {identityCard:identityCard,openId:openId} ,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.code==0){
				c=0;
				$("#hours").html(data.hours);
			}else if(data.code==1){
				alert('您的已满12学时,请预约活动后学习!');
				window.location.href="page/appointmentAudit.html";
			}
		},
		error : function(data) {
			alert("网络繁忙!请稍后再试!");
		}
	});
}


function CountDown(){   
	$("#downTime").html(seconds); 
	if(seconds==0){
		 seconds=30;
		 c=0;
	 }
	 seconds=seconds-1; 
	 timerDown=setTimeout("CountDown()",1000);
}  

function getTotalHours(){
	$.ajax({
		url : "getTotalHours.action",
		async : false,
		data : "identityCard="+identityCard,
		dataType : "json",
		type : "post",
		success : function(data) {
			$("#hours").html(data.hours);
		},
		error : function(data) {
			alert("网络繁忙!请稍后再试!");
		}
	});
}



$(function(){
	alert("紧急通知：微信满12分学习功能将在9月14日结束并关闭，请大家在此时间前抓紧完成学时学习并预约");
	var hrefUrl="doPeopleInformation.action?identityCard="+identityCard;
	$("#peopleInformation").attr("href",hrefUrl);
	timedCount();
	CountDown();
	getTotalHours();
	rows=getProblem();
	getOneProblem(num);
	var upnu=$("#upnum").html();
	if(upnu==1){
		$("#upProblem").hide();
	}
	
	 $("#nav_btn").on("click", function () {
         $("#nav_banner").toggle();
     });
	$("#nextProblem").click(function() {
		var upnu=$("#upnum").html();
		if(upnu==800){
			getOneProblem(0);
		}else{
			getOneProblem(upnu);
			if(upnu!=0){
				$("#upProblem").show();
			}
			seconds=30;
		}
		
	});
	
	$("#upProblem").click(function() {
		var upn=$("#upnum").html()-2;
		getOneProblem(upn);
		if(upn==0){
			$("#upProblem").hide();
		}
		seconds=30;
	});
	
	
	$("#goBack").click(function() {
		history.back();
	});
	
}); 	

function score(){
	var result="";
	result=result+"<tr><td style='vertical-align:top;' ><span id='lblTitleText'>感谢您的学习,请继续努力!</span></td></tr>";
	$("#tableProblem").empty();
	$("#tableProblem").append(result);
}


function getOneProblem(num){
	var data=rows[num];
	var answer=data.answer.trim();
	var nm=parseInt(num)+1;
	var title="<tr><td style='vertical-align:top;border:0px solid #CCCCCC;' colspan='2' ><span><span id='upnum'>"+nm+"</span>:"+data.problem+"</span></td></tr>";
	$("#tableProblem").empty();
	var addTr=""+title;
	
	
	if(data.imageUrl!=null && data.imageUrl!=""){
		addTr+="<tr><td style='vertical-align:top;' colspan='2'><img id='detail_Url1' src='"+data.imageUrl+"' style='width:100%;' /></td></tr>";
	}
	
	addTr+="<tr ><td style='vertical-align:top;font-size:15px;' colspan='1' ><span style='font-weight:bold;'>A</span>:"+data.resultA+"</td><td style='vertical-align:top;font-size:15px;' colspan='1' ><span style='font-weight:bold;'>B</span>:"+data.resultB+"</td></tr>";
	
	if(data.resultC!=null && data.resultC!=""){
		addTr+="<tr ><td style='vertical-align:top;font-size:15px;' colspan='1' ><span style='font-weight:bold;'>C</span>:"+data.resultC+"</td>";
	}
	if(data.resultD!=null && data.resultC!=""){
		addTr+="<td style='vertical-align:top;font-size:15px;' colspan='1' ><span style='font-weight:bold;'>D</span>:"+data.resultD+"</td>";
	}
	addTr+="</tr>";
	addTr+="<tr><td style='vertical-align:top;font-size:13px;' colspan='2' >正确答案: <span style='color:red;'>"+answer+"</span><br><p style='margin-top:10px;'>官方解析:</p><p style='color:red;text-indent:30px;'>"+data.explain+"</p></td></tr>";
	//addTr+="<tr><td colspan='2' style='text-align:center;'><input type='button'  value='提 交' id='btnSave' /><input type='button'  value='下一题' id='nextProblem' style='display:none;'/></td></tr><tr><td>&nbsp;</td><td><span id='lblQuestionResult' style='color:Blue;'></span></td></tr>";
	$("#tableProblem").append(addTr);
	
	
}



function getProblem(){
	var row=null;
	$.ajax({
		url : "getProblemJsp.action",
		async : false,
		data : "",
		dataType : "json",
		type : "post",
		success : function(data) {
			row=data;
		},
		error : function(data) {
			alert("网络繁忙!请稍后再试!");
		}
	});
	return row;
}	
 	

</script>
 <style type="text/css">
 #btnSave{
 background-color:#0099CC;width:80px;height:30px;
 }
 #nextProblem{
 background-color:#0099CC;width:80px;height:30px;
 }
 #saveData{
 background-color:#0099CC;width:80px;height:30px;
 }
 #upProblem{
 background-color:#0099CC;width:80px;height:30px;
 }
 #goBack{
 background-color:#0099CC;width:80px;height:30px;	
 }
 span{
 	font-size:15px;
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



.nav-back span {
    float: left;
    padding: 5px 5px 5px 2px;
    font-size: 14px;
    line-height: 16px;
}
 </style>
</head>
<body class="bodyWrapper">
    <!-- <div id="titleDiv">
    <a class="nav-back" onclick="javascript:history.go(-1);"><i class="icon-arrow arrow-left"> </i><span>
                            返回
          </span>
          </a>
        <div class="navTitle">
       	 满12分驾驶员在线学习
        </div>
    </div> -->
 <div class="nav">
    <h2 class="nav-title">满12分驾驶员在线学习</h2>
    <a class="nav-back" onclick="javascript:history.go(-1);">
    <img src="image/fullScode/icon.png" ></a>
    <div class="nav-mini">
        <a class="nav-mini-btn" id="nav_btn">
            <img src="image/fullScode/n_nav01.png"></a>
        <!-- <div class="nav-mini-pop pop-opacity fn-hide" id="nav_banner" style="display: none">
            <a href="page/studyGuide.html">指南</a> <a href="doFullScodeLogin.action?openId=">退出</a>  <i class="icon-opacity-arrow arrow-top">
                </i>
        </div> -->
        <div class="nav-mini-pop pop-opacity fn-hide" id="nav_banner" style="display: none;height:120px;">
             <a href="page/studyGuide.html" id="peopleInformation">用户信息</a> 
             <a href="page/studyGuide.html">指南</a> <a href="doFullScodeLogin.action?openId=">退出</a>
            <i class="icon-opacity-arrow arrow-top">
                </i>
        </div>
        
        
    </div>
</div>
    
    <div class="mainWrapper" style="margin:0 auto;border-radius:5px;align:center;width:97%;margin-top:5px;margin-bottom:5px;">
    <!-- <img src="study.jpg" width="100%" style="border-radius:5px" /> -->
    <div style="height:10px;"></div>
        <table class="table" cellpadding="0" cellspacing="0" border="0" width="96%" style="margin:auto;">
             <thead>
                <tr style="">
                    <th colspan="1" style="border-bottom:0px solid #CCCCCC;text-align:center;height:20px;">
                        	<span>累计学时:<span style="color:blue;" id="hours">0</span>学时</span>
                    </th>
                    <td style="border-bottom:0px solid #CCCCCC;text-align:center;height:20px;">
                    <span id="lblAskTime" ></span>
                    </td>
                </tr>
                  <tr style="height:20px;">
                    <th colspan="2" style="border-bottom:1px dashed #CCCCCC;text-align:center;height:20px;">
                        	<span>本题剩余操作时间:<span style="color:Blue;" id="downTime">15</span>秒</span>
                    </th>
                </tr>
            	</thead>
            
        </table>
        
        <table id="tableProblem" class="table" cellpadding="0" cellspacing="0" border="0" width="90%" style="margin-left:20px;">
				
        </table>
       <div style="width:100%;text-align:center;margin-bottom:10px;margin-top:20px;">
       
       <img src="image/fullScode/bgleft.jpg" id="upProblem" style="height:35px;border-radius:5px"/>
       <img src="image/fullScode/bgright.jpg" id='nextProblem' style="margin-left:10px;height:35px;border-radius:5px"/>
       
       <!-- <input type="button" value="上一题" id="upProblem" style="background:url(image/fullScode/input.jpg)"/>
       <input type='button'  value='下一题' id='nextProblem' style="background:url(image/fullScode/input.jpg);margin-left:10px;"/> -->
     	<input type="button"  value='返回' id='goBack' style='display:none;background:url(image/fullScode/input.jpg);'/>
       </div>
    </div>
    
   
    
  


</body>
</html>