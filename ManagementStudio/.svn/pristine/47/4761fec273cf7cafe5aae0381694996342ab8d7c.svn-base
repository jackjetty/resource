<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>

<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
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
		title="交易信息统计" style="height: 80px; padding: 10px;overflow: hidden;">
		<form action="getStatisticsByPlace.action" method="post" id="form1" style="height:100%;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">选择地区：</td>
				<td><select id="place" name="place" style="width: 95px;" ></select></td>
				<td style="font-size:12px;">业务种类：</td>
				<td><select id="busId" name="busId" style="width: 95px; height: 23px;">				
				</select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="exchange" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>切换至全省模式</b>
			</a></td>
			</tr>
		</table>
		</form>
	</div>

	<table id="display" class="easyui-datagrid" title="交易信息统计详细"
		style="width: 700px; height: 250px"
		data-options="rownumbers:true,singleSelect:true">
		<thead>
			<tr>
				<th rowspan="1">项目</th>
				<th>总计支付笔数</th>
				<th>支付成功笔数</th>
				<th>支付失败笔数</th>
				<th>总计支付金额</th>
				<th>总计支付号码数</th>
				<th>成功支付号码数</th>
				<th>支付失败号码数</th>
				<th>总计支付金额</th>
			</tr>
			<c:forEach items="${resultMap}" var="item" begin="0"  step="1" varStatus="status">
			<tr>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.productDescribe}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.allPay}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.paySuccess}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.payFailed}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.allPayMoney}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.allPayPhoneNumbers}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.paySuccessPhoneNumbers}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.value.payFailedPhoneNumbers}</th>
			<c:if test="${status.index==0}">
			<th rowspan="${item.value.number}">${item.value.all}</th>
			</c:if>
			</tr>
			</c:forEach>
			</thead>
	</table>
	<script type="text/javascript">
	var respCode = '${resultMap.respCode}';
	var respInfo = '${resultMap.respInfo}';
	var place = '${place}';
	if(respCode==-1){
		alert(respInfo);
	}
		var sTime = '${startTime}';
		var eTime = '${endTime}';
		$("#startTime").val(sTime);
		$("#endTime").val(eTime);
		var busId = '${busId}';
		$(function() {
			$.ajax({
				url : "getBusinessType.action",
				async : false,
				type : "post",
				data : "",
				dataType : "json",
				success : function(data) {
					var option = $("<option value='null'></option>");
					$("#busId").append(option);
					for(var i=0;i<data.length;i++){
						if(data[i].busId==busId){
							option = $("<option value='"+data[i].busId+"'selected='true'>"+data[i].btype+"</option>");
						}else{
							option = $("<option value='"+data[i].busId+"'>"+data[i].btype+"</option>");
						}
						$("#busId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			var width = $(document).width();
			var height = $(document).height();
			
			$("#display").datagrid({
				width:width-20,
				height:height-120
			});
			$("#searchpanel").panel({
				width : width - 20
			});
			$.ajax({
				url : "getPlaceInfo.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					for(var i=0;i<data.length;i++){
						if(data[i].code == place){
							option = $("<option value='"+data[i].code+"' selected>"+data[i].name+"</option>");
						}else{
							option = $("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
						}
						$("#place").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			$("#exchange").click(function() {
				$("#form1").attr('action','doStatistics.action');
				$("#form1").submit();
			});
			$("#search").click(function() {
				var startTime = $("#startTime").datebox('getValue');
				if(startTime ==''){
					alert("请选择起始时间！");
					return false;
				}
				var endTime = $("#endTime").datebox('getValue');
				if(endTime ==''){
					alert("请选择结束时间！");
					return false;
				}
				$("#form1").attr('action','getStatisticsByPlaceAll.action');
				$("#form1").submit();
			});

		});
	</script>
</body>
</html>