<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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

-->
#dg th{
	width:100px;
}
#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("images/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
    float: left;
    height: 24px;
    margin: 2px 5px 0 0;
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
</style>
</head>
<body style="background: #DFE8F6;overflow:hidden;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="充值金额排名前十用户查询" style="height: 85px; padding: 10px;">
		<table>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox" editable="false"
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
	</div>
	<table border="0px" style="margin-bottom: 10px;">
		<tr>
			<td><div class="easyui-panel panel-margin-buttom" title="查询结果" style="margin-bottom:0px; width:590px;float:left;">
			</div></td>
			<td>
			<div class="easyui-panel panel-margin-buttom" title="抽奖" style="margin-bottom:0px; width:510px;float:left;">
			</div>
			</td>
		</tr>
		<tr height="300px">
			<td>
				<div style="width:590px;height:400px;">
				<table id="dg" data-options="rownumbers:true,singleSelect:false,
					pageSize:20,remoteSort:false,
					multiSort:true,width:600">
				<thead >
				<tr>
					<th data-options="field:'phoneNumber',width:305">支付号码</th>
					<th data-options="field:'sm',width:255">总金额</th>
				</tr>
					</thead>
					</table>
				</div>
			</td>
			<td>
				<div style="width:590px;height:400px;">
				<table  style="float:top;background-color:white;height:360px;width:510px;border:solid 1px #6593cf;">
				<tr>
					<td><font style="margin-left:20px;font-weight:bold;font-size:20px;">168充值宝注册充值抽奖规则：</font><td>
				</tr>
				<tr>
					<td>
						<font style="margin-left:20px;">1、活动时间：2013年9月1日至2013年12月31日</font><br/><br/>
						<font style="margin-left:20px;">2、奖品：20寸价值400元折叠自行车</font><br/><br/>
						<font style="margin-left:20px;">3、内容：成功充值即可参加随机抽奖，每周中出1个中奖手机号。</font><br/><br/>
						<font style="margin-left:20px;">4、从上一周充值金额排名前十名中随机中出一个用户作为获奖者。</font><br/>
					</td>
				</tr>
				<tr >
					<td><font style="margin-left:20px;">中奖号码：</font><input id="zjhm" type="text" value="" readonly="readonly" disabled='true' /><td>
				</tr>
				<tr>
					<td><input id="choujiang" type="button" value="开始抽奖" style="margin-left: 120px;width:100px;height:40px;" /><input id="save" type="button" value="保存中奖号码" style="width:100px;height:40px;" /></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var sTime = '${startTime}';
		var eTime = '${endTime}';
		var tenInfo;
		var timer;
		function getData(startTime, endTime) {
			var rows = null;
			$.ajax({
				url : "getTenInfo.action",
				async : false,
				data : "startTime=" + startTime
						+ "&endTime=" + endTime,
				dataType : "json",
				type : "post",
				success : function(data) {
					rows = data;
					tenInfo = data.rows;
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			return rows;
		}

		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				width : width - 607,
				height : height - 200
			}).datagrid('loadData',getData(startTime, endTime));
			$("#searchpanel").panel({
				width : width - 54
			});
			$("#search").click(function() {
					var startTime = $("#startTime").datebox('getValue');
					var endTime = $("#endTime").datebox('getValue');
					$('#dg').datagrid('loadData',getData(startTime, endTime));
			});
			$("#clear").click(function(){
				$("#startTime").datebox('setValue',sTime);
				$("#endTime").datebox('setValue',eTime);
				
			});
			$('#choujiang').toggle(function(){
				timer=setInterval(random,10);
				$('#choujiang').val('停止抽奖');
				$("#save").attr({disabled:true});
			},function(){
				window.clearInterval(timer);
				$("#save").attr({disabled:false});
				$('#choujiang').val('抽奖结束！');
				$('#choujiang').attr({disabled:true});
			});
			$("#save").click(function(){
				var phoneNumber=$("#zjhm").val();
				if(phoneNumber==''){
					alert("无中奖号码");
					return false;
				}
				$.ajax({
					url : "addWinner.action",
					data : "phoneNumber=" +phoneNumber,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$("#zjhm").val('');
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});

			});
		});		
		function random(){
			var number = (Math.random()*(tenInfo.length-1)).toFixed(0);
			var phonenumber=tenInfo[number].phoneNumber;
			$("#zjhm").val(phonenumber);
		}

	</script>
	
</body>
</html>