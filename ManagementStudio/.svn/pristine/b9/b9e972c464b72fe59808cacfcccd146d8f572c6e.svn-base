<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	var sTime = '${startTime}';
	var eTime = '${endTime}';
	var userData;
	var userData2;
	var oldPrizeId;
	var oldPrizeName;
	var oldStartTime;
	var oldEndTime;
	var oldPayMoneyMin;
	var oldPayMoneyMax;
	var oldScoreMin;
	var oldScoreMax;
	var oldPlace;
	var prizeLeftAmount;
	var winners = new Array();
	var prizeIndex;
	var winnerSize=0;
$(function(){
	$("#startTime").datebox('setValue',sTime);
	$("#endTime").datebox('setValue',eTime);
	var width = $(document).width();
	var height = $(document).height();
	
	$("#searchpanel").panel({
		width : width - 28
	});
	$("#dg").datagrid({
		width:width-28,
		height:height-165
	});
	$.ajax({
		url : "getPrizeInfo.action",
		async : false,
		data : "",
		dataType : "json",
		type : "post",
		success : function(data) {
			option = $("<option id='number1' value='null'>请选择抽奖奖品</option>");
			$("#prize").append(option);
			for(var i=0;i<data.length;i++){
				option = $("<option value='"+data[i].prizeId+"'>"+data[i].name+"</option>");
				$("#prize").append(option);
			}
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
	$.ajax({
		url : "getPlaceInfo.action",
		async : false,
		data : "",
		dataType : "json",
		type : "post",
		success : function(data) {
			option = $("<option id='address1' value='null'>全省</option>");
			$("#place").append(option);
			for(var i=0;i<data.length;i++){
				option = $("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
				$("#place").append(option);
			}
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});	
	$("#search").click(function(){
		var prizeId = $("#prize").val();
		var prizeName = $("#prize option:selected").text();
		if(prizeId == 'null'){
			alert('请选择抽奖奖品!');
			return false;
		}
		var startTime = $("#startTime").datebox('getValue');
		if(startTime ==""){
			alert("请选择计算金额的起始时间！");
			return false;
		}
		var endTime = $("#endTime").datebox('getValue');
		if(endTime ==""){
			alert("请选择计算金额的结束时间！");
			return false;
		}
		var payMoneyMin = $("#payMoneyMin").val().trim();
		var payMoneyMax = $("#payMoneyMax").val().trim(); 
		var scoreMin = $("#scoreMin").val().trim();
		var scoreMax = $("#scoreMax").val().trim();
		var place = $("#place").val();
		$.ajax({
			url : "getTenInfo.action",
			async : false,
			data : "prizeId=" + prizeId + "&startTime=" + startTime + "&endTime=" + endTime + "&payMoneyMin=" + payMoneyMin + "&payMoneyMax=" + payMoneyMax + "&scoreMin=" + scoreMin + "&scoreMax=" + scoreMax + "&place=" + place,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==-1){
					alert(data.respInfo);
					return false;
				}else{
					userData = data;
					userData2 = data;
					data ={
							total : data.length,
							rows:data
					};
					$("#dg").datagrid("loadData",data);
					oldPrizeId = prizeId;
					oldPrizeName = prizeName;
					oldStartTime = startTime;
					oldEndTime = endTime;
					oldPayMoneyMin = payMoneyMin;
					oldPayMoneyMax = payMoneyMax;
					oldScoreMin = scoreMin;
					oldScoreMax = scoreMax;
					oldPlace = place;
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});	
		
	});
	
	$("#clear").click(function(){
		$("#startTime").datebox('setValue',sTime);
		$("#endTime").datebox('setValue',eTime);
		$("#number1").attr('selected',true);
		$("#address1").attr('selected',true);
		$("#payMoneyMin").val('');
		$("#payMoneyMax").val('');
		$("#scoreMin").val('');
		$("#scoreMax").val('');
	});
	
	
	$("#choujiang").click(function(){
		if(userData == undefined){
			alert("请先获取可以参加抽奖的用户信息！");
			return false;
		}else if(check()){
			$("#tr1").html("");
			$("#tr2").html("");
			winners = [];
			$("#zjhm").val("");
			$("#choujiang2").val("开始抽奖");
			$("#choujiang2").attr({disabled:false});
			userData2 = userData;
			$.ajax({
				url : "getPrizeInfoById.action",
				data : "prizeId=" + oldPrizeId,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==-1){
						alert(data.respInfo);
						return false;
					}else{
						prizeLeftAmount = data.leftAmount;
						$("#tr1").append($("<td height='100%' align='right' width='92px'>奖品名称:</td><td align='left' width='92px'>"+data.name+"</td><td align='right' width='92px'>奖品数量:</td><td align='left' width='92px'>"+data.amount+"</td><td align='right' width='92px'>剩余数量:</td><td align='left' width='92px'>"+data.leftAmount+"</td>"));
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});	
			$('#choujiang-dialog').dialog('open');
			
		}else{
			alert("您已更改了查询条件,请重新查询之后再进行抽奖！");
			return false;
		}
	});
	$('#choujiang2').toggle(function(){
		timer=setInterval(random,10);
		$('#choujiang2').val('停止抽奖');
		$("#save").attr({disabled:true});
	},function(){
		window.clearInterval(timer);
		var phoneNumber=$("#zjhm").val();
		winners.push(phoneNumber);
		$("#tr2").append($("<td>"+phoneNumber+"</td>"));
		userData2 = arraydelete(userData2,prizeIndex);
		if(winners.length == prizeLeftAmount){
			$('#choujiang2').val('奖品已抽完！');
			$("#choujiang2").attr({disabled:true});
			$("#save").attr({disabled:false});
		}else if(winners.length == userData.length){
			$('#choujiang2').val('所有资格用户都已中奖！');
			$("#choujiang2").attr({disabled:true});
			$("#save").attr({disabled:false});
		}else{
			$("#save").attr({disabled:false});
			$('#choujiang2').val('继续抽奖');
		}
		
	});
	$("#save").click(function(){
		if(winners.length == 0){
			alert("无中奖号码");
			return false;
		}
		$('#save-dialog').dialog('open');
		$("#prizeName").val(oldPrizeName);
		$("#prizeName").attr({disabled:true});
		$("#sortId").val("");
		$("#numbers").val(winners.toString());
		$("#numbers").attr({disabled:true});

	});
	$("#cancel").click(function(){
		$('#choujiang-dialog').dialog('close');
	});
	$("#saveDetail").click(function(){
		var sortId;
		try{
			sortId = parseInt($("#sortId").val());
		}catch(e){
			alert("您输入的期数不是数字,请重新输入!");
			return false;
		}
		$.ajax({
			url : "addWinner.action",
			async : false,
			data : "sortId="+sortId +"&prizeId="+oldPrizeId+"&numbers="+winners.toString(),
			dataType : "json",
			type : "post",
			success : function(data) {
				alert(data.respInfo);
				$('#save-dialog').dialog('close');
				$('#choujiang-dialog').dialog('close');
			},
			error : function(data) {
				alert("error 后台出现错误！");
				$('#save-dialog-dialog').dialog('close');
				$('#choujiang-dialog').dialog('close');
			}
		});
		 
	});
	
	
	
});
function random(){
	var number = (Math.random()*(userData2.length-1)).toFixed(0);
	var phonenumber=userData2[number].phoneNumber;
	prizeIndex = number;
	$("#zjhm").val(phonenumber);
}
function arraydelete(data, index) {
	var result = new Array();
	for ( var i = 0; i < data.length; i++) {
		if (i != index) {
			result.push(data[i]);
		} else
			continue;
	}
	return result;
}
	function check(){
		var prizeId = $("#prize").val();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		var payMoneyMin = $("#payMoneyMin").val().trim();
		var payMoneyMax = $("#payMoneyMax").val().trim(); 
		var scoreMin = $("#scoreMin").val().trim();
		var scoreMax = $("#scoreMax").val().trim();
		var place = $("#place").val();
		if(oldPrizeId != prizeId){
			return false;
		}
		if(oldStartTime != startTime){
			return false;
		}
		if(oldEndTime != endTime){
			return false;
		}
		if(oldPayMoneyMin != payMoneyMin){
			return false;
		}
		if(oldPayMoneyMax != payMoneyMax){
			return false;
		}
		if(oldScoreMin != scoreMin){
			return false;
		}
		if(oldScoreMax != scoreMax){
			return false;
		}
		if(oldPlace != place){
			return false;
		}
		return true;
	}
	
</script>
<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

.panel-margin-left {
	margin-left: 20px;
}

.panel-margin{
	margin-right: 20px !important;
}

#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("images/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
    float: left;
    height: 24px;
    margin: 4px 5px 0 0;
    outline: medium none;
    text-decoration: none;
    
}
#searchpanel a.pushBtn img {
    float: left;
    margin: 2px 0 4px 6px;
    border:0;
}
#searchpanel a.pushBtn b {
    background: url("images/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
    color: #666666;
    cursor: pointer;
    display: inline-block;
    font-size: 12px;
    font-weight: normal;
    height: 24px;
    line-height: 24px;
    padding: 0 7px 0 5px;
    white-space: nowrap;
}
#searchpanel a.pushBtn:hover {
    background: url("images/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("images/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="抽奖条件查询" style="padding: 10px;overflow: hidden;">
		<table>
			<tr	style="Valign: center; text-decoration: none;height: 30px;">
				<td style="font-size: 12px;">抽奖奖品：<select id="prize" style="width: 120px;"></select></td>
				<td style="font-size: 12px;width:250px;">充值金额要求：大于等于<input type="text" id="payMoneyMin" style="width: 90px;"/>元</td>
				<td style="font-size: 12px;width:180px;" align="right">小于等于<input type="text" id="payMoneyMax" style="width: 90px;"/>元</td>
				<td style="font-size: 12px;width:180px;" align="right">开始时间：<input type="text" id="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;width:180px;" align="right">结束时间：<input type="text" id="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
			</tr>
			<tr>
				<td style="font-size: 12px;width:250px;">积分要求：大于等于<input type="text" id="scoreMin" style="width: 90px;"/>分</td>
				<td style="font-size: 12px;">小于等于<input type="text" id="scoreMax" style="width: 90px;"/>分</td>
				<td style="font-size: 12px;" align="right">指定地区：<select id="place" style="width:90px;"></select></td>
				<td align="right"><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询抽奖用户</b>
			</a></td><td align="right" ><a id="choujiang" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>进行抽奖</b>
			</a><a style="margin-left:30px;" id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" title="可参与抽奖用户信息" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true">
		<thead id="thead1">
			<tr>
				<th data-options="field:'phoneNumber',width:305">支付号码</th>
				<th data-options="field:'payMoney',width:255">当月已支付金额</th>
				<th data-options="field:'allScore',width:255">已得积分</th>
			</tr>
		</thead>
	</table>
	
	<div id="choujiang-dialog" class="easyui-dialog" title="抽奖"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 800px; height: 380px; padding: 10px; background: #DFE8F6;">
		<div class="easyui-panel" title="奖品信息" style="width: 766px; height: 80px;float:left;margin-bottom: 20px;">
			<table width="100%" height="100%"><tr id="tr1" height="100%"></tr></table>
		</div>
		<div class="easyui-panel" title="此次抽奖已获奖名单" style="width: 766px; height: 100px;float:left;margin-bottom: 20px;">
			<table id="userTable"><tr id="tr2"></tr></table>
		</div>
		<div style="float:left;width:766px;">
			<table style="background-color:white;width:766px;border:solid 1px #6593cf;">
				<tr>
					<td><font style="margin-left:20px;">中奖号码：</font><input id="zjhm" type="text" value="" readonly="readonly" disabled /><td>
					<td><input id="choujiang2" type="button" value="开始抽奖" style="margin-left: 120px;height:40px;" /><input id="save" type="button" value="保存中奖号码" style="width:100px;height:40px;" /><input id="cancel" type="button" value="放弃抽奖结果" style="width:100px;height:40px;" /></td>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="save-dialog" class="easyui-dialog" title="中奖信息确认"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 400px; height: 300px; padding: 10px; background: #DFE8F6;">
		<table width="100%" height="100%">
				<tr>
					<td align="right">奖品名称：</td><td><input type="text" id="prizeName"/></td>
				</tr>
				<tr>
					<td align="right">抽奖期数：</td><td><input type="text" id="sortId"/></td>
				</tr>
				<tr>
					<td align="right">获奖号码：</td><td><textarea id="numbers" cols="15" rows="3"> </textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" value="确认并保存" id="saveDetail" /></td>
				</tr>
			</table>
	</div>
</body>
</html>