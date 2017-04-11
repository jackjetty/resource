<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>违法信息查询</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/jquery.mobile-1.4.2.min.css">
<script src="${pageContext.request.contextPath}/web/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/web/js/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript">
	var index= <s:property value="#request.index"/>;
	$(function() {
		if(index==1){
			$("#peopleWf").click();
		}else {
			$("#carWf").click();
		}
		
		$("#peopleBtn").click(function() {
					$("#buttonDiv1").attr("class", "cssbuttonDiv1");
					var vsfzmhm = $("#vsfzmhm").val().trim();
					var vdabh = $("#vdabh").val().trim();
					if(vsfzmhm==""){
						$("#errorvsfzmhm").css("display","block");
						return false;
					}
					if(vdabh==""){
						$("#errorvdabh").css("display","block");
						return false;
					}
					$("#imgLoading").css("display","block");
					$("#tabs").css("display","none");
					$.ajax({
						url : "${pageContext.request.contextPath}/wap/driverScore.action",
						data : {vsfzmhm:vsfzmhm,vdabh:vdabh},
						dataType : "json",
						async : true,
						type : "post",
						success : function(data) {
								if(data.code==0){
									$("#result2").html("<span style=\"color:red;\">查询结果:</span><br/>"+data.result);
									$("#beizhu1").css("display","none");
								};
								$("#imgLoading").css("display","none");
								$("#tabs").css("display","block");
								
						},
						error : function(data) {
							alert("网络繁忙!请稍后再试!");
						}
					});
					$("#peopleWf").addClass("ui-btn-active");
				});
		
		function fnCheck(){
			
			var randValue=$.trim($("#yzm").val());
			if(randValue==""){
				$("#errorYzm").css("display","block");
				return false;
			} 
			$("#imgLoading").css("display","block");
			$("#tabs").css("display","none");


			 
			$.ajax({
				url : "${pageContext.request.contextPath}/wap/checkVerifyCode.action",
				data :  {randValue:randValue},
				dataType : "json",
				async : false,
				type : "post",
				success : function(data) {
					if(data.respCode!=0){
						alert(data.respInfo);
						$("#imgLoading").css("display","none");
						$("#tabs").css("display","block");
					} 
					else{
						fnSubmit();
						
					}
					
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
					 
				}
			}); 
		}
		function fnSubmit(){
			$("#buttonDiv").attr("class", "cssbuttonDiv1");
			var cartype = $("#cartype").val();
			var carTypeValue = $("#cartype option:selected").html().trim();
			var carid = "浙"+$("#carid").val().trim();
			var carno = $("#carno").val().trim();
			if($("#carid").val().trim()==""){
				$("#imgLoading").css("display","none");
				$("#tabs").css("display","block");
				$("#errorCarid").css("display","block");
				
				return false;
			}
			if($("#carid").val().trim().length!=6){
				$("#imgLoading").css("display","none");
				$("#tabs").css("display","block");
				$("#errorCarid").css("display","block");
				return false;
			}
			if(carno==""){
				$("#imgLoading").css("display","none");
				$("#tabs").css("display","block");
				$("#errorCarno").css("display","block");
				return false;
			}
			if(carno.length!=6){
				$("#imgLoading").css("display","none");
				$("#tabs").css("display","block");
				$("#errorCarno").css("display","block");
				return false;
			} 
			
			 
			$.ajax({
				url : "${pageContext.request.contextPath}/wap/driverIllegality.action",
				data : {cartype:cartype,carTypeValue:carTypeValue,carid:carid,carno:carno},
				dataType : "json",
				async : true,
				type : "post",
				success : function(data) {
					if(data.code==0){
						$("#result1").html("<span style=\"color:red;\">查询结果:</span><br/>"+data.result);
						$("#beizhu").css("display","none");	
					}else{
						$("#result1").html(carid+"车辆无非现场违法记录！");
						$("#beizhu").css("display","none");
					};
					//rand();
					$("#imgLoading").css("display","none");
					$("#tabs").css("display","block");
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
					$("#imgLoading").css("display","none");
					$("#tabs").css("display","block");
				}
			}) ;
			$("#carWf").addClass("ui-btn-active");
		}
		$("#carBtn").click(function() { 
			fnCheck();  
		});

		
		
		
	});
	
	function rand(){
		document.getElementById("kaptchaImage").src= "${pageContext.request.contextPath}/wap/verifyCode.action?rand="+Math.floor(Math.random()*100);
	}

</script>
<script>
 document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
WeixinJSBridge.call('hideOptionMenu');
});
</script>

<style type="text/css">
.cssbuttonDiv{
	text-align:center;
	background-color: #3388CC;
	border-color: #3388CC;
	color: #333;
	text-shadow: 0 1px 0 #005599;
	line-height:50px;
	border-radius: 10px;
	border:#909090 1px solid;
}
.cssbuttonDiv1{
	text-align:center;
	background-color: #3388CC;
	border-color: #3388CC;
	color: #333;
	text-shadow: 0 1px 0 #005599;
	line-height:50px;
	border-radius: 10px;
	border:#909090 1px solid;

   filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);
   -moz-box-shadow: 4px 4px 10px #909090;
   -webkit-box-shadow: 4px 4px 10px #909090;
   box-shadow:4px 4px 10px #909090;
	
}
</style>
</head>
<body>
<div id="imgLoading" align="center"  style="margin-top:50%;display:none;"><img  src="${pageContext.request.contextPath}/image/loading.gif" /><br />正在加载,请稍后!</div>
	<div data-role="tabs" id="tabs" style="display:block;">
  <div data-role="navbar" class="ui-body-d ui-content" style="">
    <ul>
      <li  class="" id="carWfLi"><a href="#one" data-ajax="false" id="carWf" class="ui-btn-active">车辆违法信息查询</a></li>
      <li  class="" id="peopleWfLi"><a href="#two" data-ajax="false" class="" id="peopleWf" style="border-right: 1px solid #CCCCCC;">驾驶员记分信息查询</a></li>
    </ul>
  </div>
  <div id="one" class="ui-body-d ui-content" style="margin-top:-10px;">
    
   <div id="beizhu" style="display:block;"><span style="color:red;">注：</span>请认真填写完整的车辆信息,详细信息请查看您的驾驶证</div>
   <div id="result1"></div>
   <div></div>
   <br/>
    	<div >
	        <span >车牌种类:
		        <select id="cartype" name="cartype">
			       <option value="02" >小型汽车    </option>
					<option value="01" >大型汽车</option>
					<option value="03" >使馆汽车    </option>
					<option value="04" >领馆汽车    </option>
					<option value="05" >境外汽车    </option>
					<option value="06" >外籍汽车    </option>
					<option value="07" >两、三轮摩托车       </option>
					<option value="08" >轻便摩托车     </option>
					<option value="09" >使馆摩托车     </option>
					<option value="10" >领馆摩托车     </option>
					<option value="11" >境外摩托车     </option>
					<option value="12" >外籍摩托车     </option>
					<option value="13" >农用运输车     </option>
					<option value="14" >拖拉机   </option>
					<option value="15" >挂车  </option>
					<option value="16" >教练汽车    </option>
					<option value="17" >教练摩托车     </option>
					<option value="18" >试验汽车    </option>
					<option value="19" >试验摩托车     </option>
					<option value="20" >临时入境汽车      </option>
					<option value="21" >临时入境摩托车       </option>
					<option value="22" >临时行驶车     </option>
					<option value="23" >公安警车    </option>
					<option value="24" >其他车型    </option>
		    	</select>           
	        </span>
        </div>
 	<div> <span style="width:70px;">车牌号码:</span>
 					<select name="carid1" id="carid1">
                 		<option value="浙">浙</option>
                		
                	</select>
    	<input name="carid" type="text" id="carid" size="8"   style="width:100%;" />
     	<span id="errorCarid" style="color:red;display:none;">请输入正确车牌号!如D01001</span>
     </div>
  	<div><span style="width:100px;">车架号/车辆识别号(后六位):</span>
    	<input name="carno" type="text" id="carno" size="10" style="width:100%;" value="" />
    	<span id="errorCarno" style="color:red;display:none;">请输入正确车架号!后六位</span>
    </div>
    <div>
    
    <span style="width:100px;">验证码:</span>
     
    	<input name="yzm" id="yzm" type="text"   size="10" style="position:relative;display:inline; width:100%;" value="" /> 
    	<img src="${pageContext.request.contextPath}/wap/verifyCode.action" id="kaptchaImage" style="position:absolute;right:15px; margin-top:-42px;float:right; " onclick="rand();" style="cursor: pointer;display:inline;"/>
      
    	<span id="errorYzm" style="color:red;display:none;">请输入验证码</span>
    </div>
   
    	<!-- <input type="button" id="carBtn"  value="查 询"  style="background-color: #3388CC;border-color: #3388CC;color: #FFFFFF;text-shadow: 0 1px 0 #005599;" /> -->
  		<a id="carBtn" href="" style="text-decoration:none;">
  		<div id="buttonDiv" class="cssbuttonDiv" style="">查询</div>
  		</a>
   
 
 
</div>

  <div id="two" class="ui-body-d ui-content" style="margin-top:-10px;">
   
   <div id="beizhu1" style="display:block;"><span style="color:red;">注：</span>请认真填写您的驾驶证号和档案编号</div>
   <div id="result2">
  
   
   </div>
   <br/>
    	
 	<div> <span style="width:70px;">驾驶证号:</span>
    	<input name="vsfzmhm" type="text" id="vsfzmhm" size="8" placeholder="请输入驾驶证号"  style="width:100%;" value=""/>
     	<span id="errorvsfzmhm" style="color:red;display:none;">请输入正确驾驶证号</span>
     </div>
  	<div><span style="width:100px;">档案编号:</span>
    	<input name="vdabh" type="text" id="vdabh" size="10" placeholder="请输入档案编号" style="width:100%;" value="" />
    	<span id="errorvdabh" style="color:red;display:none;">请输入正确档案编号</span>
    </div>
    	<!-- <input type="button" id="peopleBtn"  value="查 询"  style="background-color: #3388CC;" />   -->
    	<a id="peopleBtn" href="" style="text-decoration:none;">
  		<div id="buttonDiv1" class="cssbuttonDiv" style="">查询</div>
  		</a> 
  </div>
</div>



</body>
</html>