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
<link href="css/study.css" rel="stylesheet" type="text/css" />
<link href='js/skin/skin.css' rel='stylesheet' type='text/css' />
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/Cxc-Dialog.js" type="text/javascript"></script>
<script type="text/javascript">
var yes=0;
var no=0;
var rows=null;
var num=0;
var answer="";
var studyTypeNum=100;
var index=0;
var explain="";
$(function(){
	$("#lblTotalTime").html("共800题");
	rows=getProblem();
	//alert(rows.length);
	answer=getOneProblem(num);
	if(num!=0){
		$("#upProblem").show();
	}
	$("#btnSave").click(function() {
		//$("input:checkbox:checked").length
		var rdio=$("input:checkbox:checked");
		if(rdio.length==0){
			return false;
		};
		var result=rdio[0].value;
		if(result==answer){
			$("#btnSave").hide(); //隐藏  
			$("#nextProblem").show(); //显示  
			$("#resultText").html("回答正确!<br ><p style='margin-top:10px;'>解析:</p><p style='text-indent:30px;'>"+explain+"</p>");
			//$("#resultText").css("color","blue");
			$("#resultText").show();
			if($("#upnum").html()!=1){
				$("#upProblem").show();
			}
			if(index==0){
				yes++;
			}
			num++;
			
		}else{
			//$("#resultText").css("color","red");
			$("#resultText").html("回答错误!正确答案为：<span style='color:red;'>"+answer+"</span><br ><p style='margin-top:10px;'>解析:</p><p style='color:red;text-indent:30px;'>"+explain+"</p>");
			$("#resultText").show();
			$("#btnSave").hide(); //隐藏  
			$("#nextProblem").show(); //显示 
			if($("#upnum").html()!=1){
				$("#upProblem").show();
			}
			if(index==0){
				no++;
			}
			index++;
			num++;
		}
	});
	$("#nextProblem").click(function() {
		//alert(num);
		var upnu=$("#upnum").html();
		//alert(upnu);
		if(upnu==studyTypeNum){
			score();
			$("#nextProblem").hide();
			$("#resultText").hide();
			$("#goBack").show();
			$("#upProblem").hide();
		}else if(upnu==num){
			answer=getOneProblem(num);
			$("#btnSave").show();   
			$("#nextProblem").hide(); 
			$("#resultText").hide();
			index=0;
			/*if(num!=0){
				$("#upProblem").show();
			}*/
			$("#upProblem").hide();
		}else{
			var upn=$("#upnum").html();
			var ans=getOneProblem(upn);
			$("#resultText").html("正确答案为："+ans+"<br ><p style='margin-top:10px;'>解析:</p><p style='text-indent:30px;'>"+explain+"</p>");
			$("#resultText").show();
			$("#nextProblem").show();
			$("#btnSave").hide();
			$("#upProblem").show();
		}
		
	});
	
	$("#goBack").click(function() {
		history.back();
	});
	
	$("#upProblem").click(function() {
		$("#nextProblem").show();
		$("#btnSave").hide();
		var upn=$("#upnum").html()-2;
		var ans=getOneProblem(upn);
		$("#resultText").html("正确答案为："+ans+"<br ><p style='margin-top:10px;'>解析:</p><p style='text-indent:30px;'>"+explain+"</p>");
		$("#resultText").show();
		if(upn==0){
			$("#upProblem").hide();
		}
	});
	
}); 	
function trueCheck(o){
	
	//alert($(o).attr("checked"));
	var val=$(o).val();
	var rdio=$("input:checkbox");
	//alert(rdio.length);
	if($(o).attr("checked")=="checked"){
		for(var i=0;i<rdio.length;i++){
			if(rdio[i].value!=val){
				rdio[i].checked = false;
			}
		}
	}
	
	
	
	
}

function getOneProblem(num){
	var data=rows[num];
	var answer=data.answer.trim();
	var nm=parseInt(num)+1;
	$("#lblAskTime").html(nm);
	var title="<span id='upnum'>"+nm+"</span>:"+data.problem;
	$("#unfinish").empty();
	// <tr id='resultA'><td style='vertical-align:top;'><input  type='checkbox' value="resultA" />A、驾驶与准驾车型不符的机动车</td></tr>
	var addTr="<tr><td style='vertical-align:top;'><span id='lblTitleText'>"+title+"</span></td></tr>";
	
	
	if(data.imageUrl!=null && data.imageUrl!=""){
		addTr+="<tr><td style='vertical-align:top;'><img id='detail_Url1' src='"+data.imageUrl+"' style='width:250px;' /></td></tr>"
		//alert(data.imageUrl);
	}
	if(answer.length>1){
		addTr+="<tr><td style='vertical-align:top;'><input type='checkbox' value='A' /><span style='margin-left:10px;'>A:"+data.resultA+"</span></td></tr><tr><td style='vertical-align:top;'><input  type='checkbox' value='B' /><span style='margin-left:10px;'>B:"+data.resultB+"</span></td></tr>";
		if(data.resultC!=null ){
			addTr+="<tr><td style='vertical-align:top;'><input  type='checkbox' value='C' /><span style='margin-left:10px;'>C:"+data.resultC+"</span></td></tr>";
		}
		if(data.resultD!=null ){
			addTr+="<tr><td style='vertical-align:top;'><input  type='checkbox' value='D' /><span style='margin-left:10px;'>D:"+data.resultD+"</span></td></tr>";
		}
	}else{
		addTr+="<tr><td style='vertical-align:top;'><input type='checkbox' onchange='trueCheck(this)' name='result' value='A' id='check1'/><span style='margin-left:10px;'><label for='check1'>A:"+data.resultA+"</label></span></td></tr><tr><td style='vertical-align:top;'><input  type='checkbox' onchange='trueCheck(this)' name='result' value='B' id='check2'/><span style='margin-left:10px;'><label for='check2'>B:"+data.resultB+"</label></span></td></tr>";
		if(data.resultC!=null && data.resultC!=""){
			addTr+="<tr><td style='vertical-align:top;'><input  type='checkbox' onchange='trueCheck(this)' name='result' value='C' id='check3'/><span style='margin-left:10px;'><label for='check3'>C:"+data.resultC+"</label></span></td></tr>";
		}
		if(data.resultD!=null && data.resultC!=""){
			addTr+="<tr><td style='vertical-align:top;'><input  type='checkbox' onchange='trueCheck(this)' name='result' value='D' id='check4'/><span style='margin-left:10px;'><label for='check4'>D:"+data.resultD+"</label></span></td></tr>";
		}
	}
	explain=data.explain;
	//addTr+="<tr><td colspan='2' style='text-align:center;'><input type='button'  value='提 交' id='btnSave' /><input type='button'  value='下一题' id='nextProblem' style='display:none;'/></td></tr><tr><td>&nbsp;</td><td><span id='lblQuestionResult' style='color:Blue;'></span></td></tr>";
	$("#unfinish").append(addTr);
	
	return answer;
	
}

function score(){
	//<td style='vertical-align:top;' ><span id='lblTitleText'>答题信息已保存!</span></td>
	var result="";
	result=result+"<tr><td style='vertical-align:top;' ><span id='lblTitleText'>感谢您的学习,请继续努力!</span></td></tr>";
	$("#unfinish").empty();
	$("#unfinish").append(result);
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
 #saveData{
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
 </style>
</head>
<body class="bodyWrapper">
    <div  id="titleDiv">
        <div class="navTitle">新驾驶员在线学习</div>
    </div>
    <div class="banner" style="text-align:center;">
    <img src="image/fullScode/newstudent.jpg" width="96%" style="border-radius:5px" />
    </div>
    
    <div class="mainWrapper" style="border-radius:5px;align:center;width:96%;margin-bottom:10px;">
        <table class="table" cellpadding="0" cellspacing="0" border="0" width="90%" style="margin:auto;">
             <thead>
                <tr>
                    <th colspan="2" style="border-bottom:1px dashed #CCCCCC">
                      <span id="lblTotalTime" style="font-size:17px;"></span>,
                        	<span style="font-size:17px;">当前第</span><span id="lblAskTime" style="color:Blue;font-size:17px;"></span><span style="font-size:17px;">题</span>
                        	<span id="timer"></span>
                    </th>
                </tr>
            	</thead>
            <tbody id="unfinish">

            </tbody>
            
        </table>
        <div style="width:90%;margin:auto;">
        <span id="resultText" style="display:none;">
       </span>
        </div>
       <div style="width:100%;text-align:center;margin-bottom:10px;">
       <br />
       <img src="image/fullScode/bgleft.jpg" id="upProblem" style="height:35px;display:none;border-radius:5px"/>
       <img src="image/fullScode/bgtrue.jpg" id="btnSave" style="margin-left:10px;height:35px;border-radius:5px"/>
       <img src="image/fullScode/bgright.jpg" id='nextProblem' style="margin-left:10px;height:35px;display:none;border-radius:5px"/>
       <input type="button"  value='返回' id='goBack' style='display:none;'/>
       <!-- <input type="button" value="提 交" id="btnSave"/> 
       <input type='button'  value='下一题' id='nextProblem' style='display:none;'/>
       
       <input type="button" value="上一题" id="upProblem" style='display:none;'/>-->
       </div>
    </div>
    
   
    
  


</body>
</html>
