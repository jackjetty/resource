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
<body style="background: #DFE8F6;overflow: hidden;">

	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="返回码查询" style="height: 80px; padding: 10px;vertical-align: center;">
		<div style="float:left;vertical-align: center;height:31px;line-height:31px;">
		返回码：&nbsp;&nbsp;<input type="text" id="returnCode" style="width: 95px;height:20px;" /> &nbsp;&nbsp; 业务种类：&nbsp;&nbsp;<select id="busId" style="width: 95px; height: 23px;">
				</select></div>
			<div style="height:100%;float:left;margin-left: 15px;vertical-align: center;height:31px;line-height:31px;">
			<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></div>
	</div>
	<table id="dg" class="easyui-datagrid" title="返回码信息列表"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'bType',width:100">业务种类</th>
				<th data-options="field:'returnCode',width:100">返回码</th>
				<th data-options="field:'codeMeaning',width:100">代码含义</th>
				<th data-options="field:'show',width:100,sortable:true">提示信息</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData(busId,returnCode) {
		if(busId==null){
			busId=-1;
		}
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getReturnCodeInfo.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&busId=" + busId + "&returnCode=" + returnCode + "&hasShow=" + false,
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
			var returnCode = $("#returnCode").val().trim();
			var busId = $("#busId").val();
			if(busId =="null"){
				busId = null;
			}
			var newData = getData(busId,returnCode);
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
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};

	$(function() {
		var width = $(document).width();
		var height = $(document).height();
		var returnCode = $("#returnCode").val();
		var busId = $("#busId").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(busId,returnCode));
		$("#searchpanel").panel({
			width : width - 18
		});
		$.ajax({
			url : "getBusinessType.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option id='busid1' value='null'></option>");
				$("#busId").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].busId+"'>"+data[i].btype+"</option>");
					$("#busId").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		$("#search").click(function() {
					var returnCode = $("#returnCode").val().trim();
					if(returnCode.length > 6){
						alert("返回码不能多于6位！");
						return false;
					}
					var busId = $("#busId").val();
					if(busId=="null"){
						busId = null;
					}
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(busId,returnCode));
				});
		$("#clear").click(function(){
			$("#busid1").attr('selected',true);
			$("#returnCode").val('');
			
		});
	});
	</script>
	
</body>
</html>