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
		title="新注册用户信息查询" style="height:80px;padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>手机号码：</td>
				<td><input type="text" id="phoneNumber"  style="width:85px;" /></td>
				<td>注册时间：</td>
				<td><input type="text" id="registerTime" class="easyui-datebox" editable="false" style="width:90px;" /></td>
				<td>最后交易时间：</td>
				<td><input type="text" id="lastTradeTime" class="easyui-datebox" editable="false" style="width:90px;" /></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td><td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="editPhoneRound" />
	<table id="dg" class="easyui-datagrid" title="新注册用户信息列表"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th id="phoneNumber" data-options="field:'phoneNumber',width:100">手机号码</th>
				<th id="registerTime" data-options="field:'registerTime',width:100">注册时间</th>
				<th id="successTimes" data-options="field:'successTimes',width:100">目前充值成功次数</th>
				<th id="failedTimes" data-options="field:'failedTimes',width:100">目前充值失败次数</th>
				<th id="failedTimes" data-options="field:'lastTradeTime',width:100">最后交易时间</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		function getData(phoneNumber,registerTime,lastTradeTime) {
			var startTime = '${startTime}';
			var endTime = '${endTime}';
			var p = '${p}';
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getNewUser.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex+ "&startTime=" + startTime
				+ "&endTime=" + endTime+ "&p=" + p+"&phoneNumber="+phoneNumber+"&registerTime="+registerTime
				+"&lastTradeTime="+lastTradeTime,
				dataType : "json",
				type : "post",
				success : function(data) {
						rows = data;
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			return rows;
		}

		function pagerFilter(data) {
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			if(typeof data.originalRows== 'undefined'){
				data = {
						total : data.listSize,
						rows : data.rows
					};
					pager.pagination({
						onSelectPage : function(pageNum, pageSize) {
							opts.pageNumber = pageNum;
							opts.pageSize = pageSize;
							pager.pagination('refresh', {
								pageNumber : pageNum,
								pageSize : pageSize
							});
							dg.datagrid('loadData', data);
						}
					});
					data.originalRows = (data.rows);
					return data;
			}else{
				var phoneNumber = $("#phoneNumber").val().trim();
				var registerTime =  $("#registerTime").datebox('getValue'); 
				var lastTradeTime =  $("#lastTradeTime").datebox('getValue'); 
				var newData = getData(phoneNumber,registerTime,lastTradeTime);
				data.total = newData.listSize;
				data.rows = newData.rows;
				pager.pagination({
					onSelectPage : function(pageNum, pageSize) {
						opts.pageNumber = pageNum;
						opts.pageSize = pageSize;
						pager.pagination('refresh', {
							pageNumber : pageNum,
							pageSize : pageSize
						});
						dg.datagrid('loadData', data);
					}
				});
				data.originalRows = (data.rows);
				return data;
			}	
		}
		
		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			var phoneNumber = $("#phoneNumber").val().trim();
			var registerTime =  $("#registerTime").datebox('getValue'); 
			var lastTradeTime =  $("#lastTradeTime").datebox('getValue'); 
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 18,
				height : height - 120,
				onDblClickCell : function(rowIndex, field, value) {
					var dg = $("#dg");
					var opts = dg.datagrid('options');
					for ( var i = 0; i < opts.pageSize; i++) {
						if (i != rowIndex) {
							$('#dg').datagrid('endEdit', i);
						}
					}
					$('#dg').datagrid('beginEdit', rowIndex);
				},
				onClickRow : function() {
					var dg = $("#dg");
					var opts = dg.datagrid('options');
					for ( var i = 0; i < opts.pageSize; i++) {
						$('#dg').datagrid('endEdit', i);
					}
				}
			}).datagrid('loadData', getData(phoneNumber,registerTime,lastTradeTime));
			$("#searchpanel").panel({
				width : width - 18
			});
			$("#search").click(function() {
				var phoneNumber = $("#phoneNumber").val().trim();
				var registerTime =  $("#registerTime").datebox('getValue'); 
				var lastTradeTime =  $("#lastTradeTime").datebox('getValue'); 
				$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
				}).datagrid('loadData',getData(phoneNumber,registerTime,lastTradeTime));
			});
			
			$("#clear").click(function(){
				$("#phoneNumber").val("");
				$("#registerTime").datebox('setValue','');
				$("#lastTradeTime").datebox('setValue','');
			});
			
		});
		String.prototype.trim = function() {
			  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
			};
	</script>
</body>
</html>

