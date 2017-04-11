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
		title="下载记录查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>下载时间：</td>
				<td><input type="text" id="downloadTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
				<td>下载渠道：</td>
				<td><select id="fromWay" style="width: 95px; height: 23px;">
				</select></td>
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
	<table id="dg" class="easyui-datagrid" title="各宣传渠道下载记录"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
			<th data-options="field:'fromWayDetail',width:100">下载渠道</th>
			<th data-options="field:'downloadTime',width:100">下载时间</th>
			<th data-options="field:'ipAddress',width:100">IP地址</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	
	function getData(fromWay,downloadTime) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getDownloadRecord.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&fromWay=" + fromWay+  "&downloadTime=" + downloadTime,
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
			var fromWay = $("#fromWay").val().trim();
			var downloadTime = $("#downloadTime").datebox('getValue');
			var newData = getData(fromWay,downloadTime);
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
		var fromWay = $("#fromWay").val();
		var downloadTime = $("#downloadTime").datebox('getValue');
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(fromWay,downloadTime));
		$("#searchpanel").panel({
			width : width - 18
		});
		$.ajax({
			url : "getAdvertisingDetail.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option id='fromWay1' value='null'></option>");
				$("#fromWay").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].path+"'>"+data[i].detail+"</option>");
					$("#fromWay").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		$("#search").click(function() {
			var fromWay = $("#fromWay").val();
			var downloadTime = $("#downloadTime").datebox('getValue');
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(fromWay,downloadTime));
				});
		$("#clear").click(function(){
			$("#fromWay").val('null');
			$("#downloadTime").datebox('setValue', '');
			
		});

});
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	</script>
</body>
</html>

