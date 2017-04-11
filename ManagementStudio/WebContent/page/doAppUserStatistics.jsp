<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		title="App用户信息统计" style="height: 80px; padding: 10px;">
		<form action="getAppUserStatistics.action" method="post" id="form1" style="height: 100%;">
		
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td width="170px" align="right"><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			</tr>
		</table>
		</form>
	</div>

	<table id="display" class="easyui-datagrid" title="App用户信息统计详细"
		style="width: 700px; height: 250px"
		data-options="rownumbers:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'productid',width:100,align:'center'" rowspan="2">项目</th>
				<th colspan="4">地区</th>
			</tr>
			<tr>
				<th data-options="field:'listprice',width:100,align:'center'">浙江</th>				
			</tr>
			<tr> <th>新注册用户数</th><th><a href="#" onclick="view('${viewNewRegisterLink}')">${resultMap.newRegister}</a></th></tr>
			<tr> <th>总用户数</th><th>${resultMap.allRegister}</th></tr>
			</thead>
				
	</table>


	<script type="text/javascript">
		String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		function view(link,place){
			place = "浙江";
			parent.window.addTab(place+"新注册用户列表",link);
		}
		var respCode = '${resultMap.respCode}';
		var respInfo = '${resultMap.respInfo}';
		var viewNewRegisterLink =  '${viewNewRegisterLink}';
		if(respCode==-1){
			alert(respInfo);
		}
		var sTime = '${startTime}';
		var eTime = '${endTime}';
		$("#startTime").val(sTime);
		$("#endTime").val(eTime);
		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			$("#searchpanel").panel({
				width : width - 18
			});
			$("#display").datagrid({
				width:width-18,
				height:height-120
			});
			$("#search").click(function() {
				var startTime = $("#startTime").datebox('getValue');
				if(startTime.trim() ==''){
					alert("请选择起始时间！");
					return false;
				}
				var endTime = $("#endTime").datebox('getValue');
				if(endTime.trim() ==''){
					alert("请选择结束时间！");
					return false;
				}
				$("#startTime").val(startTime);
				$("#endTime").val(endTime);
				$("#form1").submit();
			});

		});
	</script>
</body>
</html>