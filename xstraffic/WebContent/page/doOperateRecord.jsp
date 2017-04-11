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
	$(function() {
		var today = new Date();
		var year = today.getFullYear();
		var month = today.getMonth() + 1;
		var day = today.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var startdate = year + "-" + month + "-01";
		var enddate = year + "-" + month + "-" + day;
		$("#startTime").datebox('setValue', startdate);
		$("#endTime").datebox('setValue', enddate);
		var width = $(document).width();
		var height = $(document).height();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid('loadData',	getData(startTime, endTime));
		$("#searchpanel").panel({
			width : width - 18
		});
		$("#search").click(function() {
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 18,
				height : height - 120
			}).datagrid('loadData',getData(startTime, endTime));
		});
		$("#clear").click(function(){
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var day = today.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			var startdate = year + "-" + month + "-01";
			var enddate = year + "-" + month + "-" + day;
			$("#startTime").datebox('setValue', startdate);
			$("#endTime").datebox('setValue', enddate);
		});
		
	});
	
	function getData(startTime, endTime) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getOperateRecord.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&startTime=" + startTime
					+ "&endTime=" + endTime,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==-1){
					alert(data.respInfo);
					return false;
				}else{
					rows = data;
				}
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
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var newData = getData(startTime, endTime);
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
</script>
<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("image/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
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
    background: url("image/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
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
    background: url("image/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("image/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="操作日志查询" style="height: 80px; padding: 10px;overflow: hidden;">
		
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="image/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>

	<table id="dg" class="easyui-datagrid" title="操作日志记录表"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<!-- th data-options="field:'operateId',width:80">操作人</th> -->
				<th data-options="field:'manager',width:80">操作人</th>
				<th data-options="field:'operateType',width:80,hide:'true'">操作类型</th>
				<th data-options="field:'operateContent',width:250,sortable:true">操作内容</th>
				<th data-options="field:'operateTime',width:100,sortable:true">操作时间</th>
				<th data-options="field:'result',width:100,sortable:true">操作结果</th>				
				<th data-options="field:'cause',width:100">失败原因</th>
			</tr>
		</thead>
	</table>
</body>
</html>