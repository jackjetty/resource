<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		title="Q币包月信息查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<form action="getQQStatistics.action" method="post" id="form1" style="height:100%;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
		</form>
	</div>
	<table id="display" class="easyui-datagrid" title="Q币包月信息统计"
		style="width: 700px; height: 400px"
		data-options="rownumbers:true,singleSelect:true">
		<thead>
			<tr><th data-options="field:'productid',width:100,align:'center'">QQ包月</th><th>总计支付号码数</th><th>总计QQ号码数</th><th>总计积分数</th><th>总计支付金额</th></tr>
			<c:forEach items="${listMap}" var="item" begin="0"  step="1" varStatus="status">
			<tr>
			<th data-options="field:'listprice',width:100,align:'center'">${item.productDescribe}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.payPhoneNumbers}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.QQNumbers}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.sendScore}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.payMoney}</th>
			</tr>
			<c:if test="${(status.index+1)==fn:length(listMap)&&status.index!=0}">
			<tr>
			<th data-options="field:'listprice',width:100,align:'center'">合计</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.AllPayPhoneNumbers}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.AllQQNumber}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.AllSendScore}</th>
			<th data-options="field:'listprice',width:100,align:'center'">${item.AllPayMoney}</th>
			</tr>
			</c:if>
			</c:forEach>
			</thead>
				
	</table>
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var sTime = '${startTime}';
		var eTime = '${endTime}';
		$("#startTime").val(sTime);
		$("#endTime").val(eTime);
		$(function() {
			
			var width = $(document).width();
			var height = $(document).height();
			
			$("#display").datagrid({
				width:width-30,
				height:height-130
			});
			$("#searchpanel").panel({
				width : width - 30
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
					return false;}
				$("#startTime").val(startTime);
				$("#endTime").val(endTime);
				$("#form1").submit();
				
			});
			$("#clear").click(function(){
				var today = new Date();
				var year = today.getFullYear();
				var month = today.getMonth() + 1;
				var day = today.getDate();
				if (month<10){month="0"+month;}
				if (day<10){day="0"+day;}
				var startdate = year+"-"+month+"-01";
				var enddate = year+"-"+month+"-"+day;
				$("#startTime").datebox('setValue', startdate);
				$("#endTime").datebox('setValue', enddate);
				
			});
		});
		
		
	</script>
</body>
</html>