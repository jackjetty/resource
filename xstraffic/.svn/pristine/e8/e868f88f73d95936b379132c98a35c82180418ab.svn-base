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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href='js/skin/skin.css' rel='stylesheet' type='text/css' />
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/Cxc-Dialog.js" type="text/javascript"></script>
<script type="text/javascript">
var yes=0;
var no=0;
var rows=null;
var num=0;
var answer="";
var idCard='${idCard}';
var phoneNumber='${phoneNumber}';
var studyTypeNum='${studyTypeNum}';
var openId='${openId}';
var index=0;
var maxtime = 60*60; //一个小时，按秒计算，自己调整!  
function CountDown(){  
	 if(maxtime>=0){  
		 minutes = Math.floor(maxtime/60);  
		 seconds = Math.floor(maxtime%60);  
		 msg = "<br/>考试时间还有"+minutes+"分"+seconds+"秒";  
		 $("#timer").html(msg);  
		 if(maxtime == 5*60) CxcDialog('提示框','注意!还剩5分钟','Warning');  
		 	--maxtime;  
		 }  
	 else{  
		 clearInterval(timer);
		 CxcDialog('提示框','时间到!考试结束!','OK'); 
		 alert(idCard);
		 score(yes,no,idCard,phoneNumber,studyTypeNum,openId);
		 $("#nextProblem").hide();
		 $("#resultText").hide();
		 $("#goBack").show();
		 $("#upProblem").hide();
		 $("#btnSave").hide();
	 }  
}  
  
$(function(){
	if(studyTypeNum==null || studyTypeNum==0){
		return false;
	}
	if(studyTypeNum=='50'){
		$("#lblTotalTime").html("正式版共50题");
		timer = setInterval("CountDown()",1000);
	}else{
		$("#lblTotalTime").html("体验版"+studyTypeNum+"题");
	}
	
	rows=getProblem();
	//alert(rows.length);
	answer=getOneProblem(num);
	if(num!=0){
		$("#upProblem").show();
	}
	$("#btnSave").click(function() {
		//$("input:checkbox:checked").length
		var result="";
		if(answer.length>1){
			var check=$("input:checkbox:checked");
			for(var i=0;i<check.length;i++){
				result+=check[i].value;
			}
		}else{
			var rdio=$("input:radio:checked");
			if(rdio.length==0){
				return false;
			};
			result=rdio[0].value;
		}
		if(result==answer){
			$("#btnSave").hide(); //隐藏  
			$("#nextProblem").show(); //显示  
			$("#resultText").html("回答正确");
			$("#resultText").css("color","blue");
			$("#resultText").show();
			if($("#upnum").html()!=1){
				$("#upProblem").show();
			}
			
			if(index==0){
				yes++;
			}
			num++;
			
		}else{
			$("#resultText").css("color","red");
			$("#resultText").html("正确答案为："+answer+" 请重新选择提交");
			$("#resultText").show();
			$("#upProblem").hide();
			if(index==0){
				no++;
			}
			index++;
		}
	});
	$("#nextProblem").click(function() {
		//alert(num);
		var upnu=$("#upnum").html();
		//alert(upnu);
		if(upnu==studyTypeNum){
			if(studyTypeNum==50){
				score(yes,no,idCard,phoneNumber,studyTypeNum,openId);
			}else{
				score1();
			}
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
			if(num!=0){
				$("#upProblem").show();
			}
		}else{
			var upn=$("#upnum").html();
			var ans=getOneProblem(upn);
			$("#resultText").html("正确答案为："+ans+"");
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
		$("#resultText").html("正确答案为："+ans+"");
		$("#resultText").show();
		if(upn==0){
			$("#upProblem").hide();
		}
	});
	
}); 	

function getOneProblem(num){
	var data=rows[num];
	var answer=data.answer.trim();
	var nm=parseInt(num)+1;
	$("#lblAskTime").html(nm);
	var title="<span id='upnum'>"+nm+"</span>:"+data.problem;
	$("#unfinish").empty();
	// <tr id='resultA'><td style='vertical-align:top;'><input  type='checkbox' value="resultA" />A、驾驶与准驾车型不符的机动车</td></tr>
	var addTr="<tr><td style='vertical-align:top;' ><span id='lblTitleText'>"+title+"</span></td></tr>";
	
	
	if(data.imageUrl!=null && data.imageUrl!=""){
		addTr+="<tr><td style='vertical-align:top;' ><img id='detail_Url1' src='"+data.imageUrl+"' style='width:250px;' /></td></tr>"
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
		addTr+="<tr><td style='vertical-align:top;'><input type='radio' name='result' value='A' /><span style='margin-left:10px;'>A:"+data.resultA+"</span></td></tr><tr><td style='vertical-align:top;'><input  type='radio' name='result' value='B' /><span style='margin-left:10px;'>B:"+data.resultB+"</span></td></tr>";
		if(data.resultC!=null && data.resultC!=""){
			addTr+="<tr><td style='vertical-align:top;'><input  type='radio' name='result' value='C' /><span style='margin-left:10px;'>C:"+data.resultC+"</span></td></tr>";
		}
		if(data.resultD!=null && data.resultC!=""){
			addTr+="<tr><td style='vertical-align:top;'><input  type='radio' name='result' value='D' /><span style='margin-left:10px;'>D:"+data.resultD+"</span></td></tr>";
		}
	}
	
	//addTr+="<tr><td colspan='2' style='text-align:center;'><input type='button'  value='提 交' id='btnSave' /><input type='button'  value='下一题' id='nextProblem' style='display:none;'/></td></tr><tr><td>&nbsp;</td><td><span id='lblQuestionResult' style='color:Blue;'></span></td></tr>";
	$("#unfinish").append(addTr);
	
	return answer;
	
}

function score(yes,no,idCard,phoneNumber,studyTypeNum,openId){
	var row=saveStudyInfo(yes,no,idCard,phoneNumber,studyTypeNum,openId);
	//<td style='vertical-align:top;' ><span id='lblTitleText'>答题信息已保存!</span></td>
	var fenshu=yes*2;
	var result="";
	if(fenshu<90){
		result=result+"<tr><td style='vertical-align:top;' ><span id='lblTitleText'>您此次答题得分:"+fenshu+"分!感谢您的学习,请继续努力!</span></td></tr>";
		result="<tr><td style='vertical-align:top;' ><span id='lblTitleText'>答题信息已保存!</span></td></tr>"+result;
	}else{
		result=result+"<tr><td style='vertical-align:top;'><span id='lblTitleText'>您此次答题得分:"+fenshu+"分</span></td></tr>";
		result="<tr><td style='vertical-align:top;' ><span id='lblTitleText'>答题信息已保存!</span></td></tr>"+result;
		result=result+"<tr><td style='vertical-align:top;'><span id='lblTitleText'>您的考试编号为:"+row.studyNumber+"</span></td></tr>";
		//恭喜您!您可通过登录时的身份证号或考试编号到安全学校领取在线学习证明
		}
	//var result="<tr><td style='vertical-align:top;' ><span id='lblTitleText'>您此次答题"+studyTypeNum+"题，答对"+yes+"个题目，答错"+no+"个题目</span></td></tr>";
	$("#unfinish").empty();
	$("#unfinish").append(result);
	
}
function score1(){
	var result="<tr><td style='vertical-align:top;' ><span id='lblTitleText'>感谢您的参与!答对"+yes+"个题目，答错"+no+"个题目</span></td></tr>";
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
			alert("error 后台出现错误！");
		}
	});
	return row;
}	
function saveStudyInfo(yes,no,idCard,phoneNumber,studyTypeNum,openId){
	var resultInfo="您此次答题"+studyTypeNum+"题，答对"+yes+"个题目，答错"+no+"个题目";
	var score=yes*2;
	var row=null;
	$.ajax({
		url : "saveStudyInfo.action",
		async : false,
		data : "idCard="+idCard+"&phoneNumber="+phoneNumber+"&resultInfo="+resultInfo+"&score="+score+"&openId="+openId,
		dataType : "json",
		type : "post",
		success : function(data) {
			row=data;
		},
		error : function(data) {
			alert("error 后台出现错误！");
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
 </style>
</head>
<body class="bodyWrapper">
    <div class="navWrapper navFixed">
        <div class="navTitle">驾驶员在线学习</div>
        
    </div>
    <div class="banner"><img src="image/online.jpg" width="100%" alt='' /></div>
    
    <div class="mainWrapper" style="">
        <table class="table" cellpadding="0" cellspacing="0" border="0" width="90%" style="margin-left:30px;">
             <thead>
                <tr>
                    <th colspan="2">
                      <span id="lblTotalTime"></span>,
                        	<span>当前第</span><span id="lblAskTime" style="color:Blue;"></span><span>题</span>
                        	<span id="timer"></span>
                    </th>
                </tr>
            	</thead>
            <tbody id="unfinish">

            </tbody>
            
        </table>
       <div style="width:100%;text-align:center;margin-bottom:10px;"><span id="resultText" style="color:red;display:none;"></span><br /><input type="button" value="提 交" id="btnSave"/><input type='button'  value='下一题' id='nextProblem' style='display:none;'/>
       <input type="button"  value='返回' id='goBack' style='display:none;'/>
       <input type="button" value="上一题" id="upProblem" style='display:none;'/>
       </div>
    </div>
    
   
    
  


</body>
</html>
