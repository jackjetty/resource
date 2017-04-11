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
		title="积分规则查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>限制类型：</td>
				<td><input type="text" id="limitType"  style="width: 95px;" /></td>
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
	<input type="hidden" id="editPayLimit" />
	<table id="dg" class="easyui-datagrid" title="系统限额管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!--  <th data-options="field:'limitId',width:100">限额编号</th>-->
				<th data-options="field:'limitName',width:100">限额名称</th>
				<th data-options="field:'limitType',width:100">限额类型</th>
				<th data-options="field:'limit',width:100">最大限额</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData(limitType) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getPayLimit.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&limitType=" + limitType,
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
			var limitType = $("#limitType").val();
			var newData = getData(limitType);
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
		var limitType = $("#limitType").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(limitType));
		$("#searchpanel").panel({
			width : width - 18
		});
		$("#search").click(function() {
			var limitType = $("#limitType").val().trim();
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(limitType));
				});
		$("#clear").click(function(){
			$("#limitType").val('');
			
		});
		
	$("#addPayLimit").click(function() {
		var limitName = $("#add_limitName").val().trim();
		if (limitName == '') {
			alert("限额名称不能为空！");
			return false;
		}
		
		var limitType = $("#add_limitType").val().trim();
		if(limitType==''){
			alert("限额类型不能为空");
			return false;
		}
		
		var limit = $("#add_limit").val().trim();
		if (limit == '') {
			alert("最大限额不能为空！");
			return false;
		}
		if(!(/^(\+|-)?\d+$/.test(limit))|| limit<0){ 
			 alert("最大限额请输入正整数！");
		        return false;  
		}  
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addPayLimit.action",
			data : "limitName=" + limitName
					+ "&limitType=" + limitType+ "&limit=" + limit,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var limitType = $("#limitType").val().trim();
					$('#dlg').dialog('close');
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(limitType));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updatePayLimit").click(function() {

	var limitName = $("#edit_limitName").val().trim();
	if (limitName == '') {
		alert("限额名称不能为空！");
		return false;
	}
	
	
	var limitType = $("#edit_limitType").val().trim();
	if(limitType==''){
		alert("限额类型不能为空");
		return false;
	}
	
	
	var limit = $("#edit_limit").val().trim();
	if (limit == '') {
		alert("最大限额不能为空！");
		return false;
	}
	if(!(/^(\+|-)?\d+$/.test(limit))|| limit<0){ 
		 alert("最大限额请输入正整数！");
	        return false;  
	}  
	
	var width = $(document).width();
	var height = $(document).height();
	
	$.ajax({
		url : "updatePayLimit.action",
		async : false,
		data : "limitName=" + limitName
		+ "&limitType=" + limitType+ "&limit=" + limit
		+"&limitId="+$("#edit_limitId").val(),
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			var limitType = $("#limitType").val().trim();
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 18,
					height : height - 20
				}).datagrid('loadData', getData(limitType));
			}
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
});
});


	var toolbar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$("#dlg").dialog("open");
			$("#add_limitName").val('');
			$("#add_limitType").val('');
			$("#add_limit").val('');
			
			
			
		}
	}, '-', {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择需要编辑的数据！");
				return false;
			} else if (rows.length > 1) {
				alert("一次只能编辑一行！");
				return false;
			} else {
				var row = $("#dg").datagrid('getSelected');
				$('#edit-dialog').dialog('open');
				$('#edit_limitId').val(row.limitId);
				$("#edit_limitName").val(row.limitName);
				$("#edit_limitType").val(row.limitType);
				$("#edit_limit").val(row.limit);
				
				
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (window.confirm("确认删除这" + rows.length + "记录？")) {
				var limitIds = '';
				for ( var i = 0; i < rows.length; i++) {
					limitIds = limitIds + "," + rows[i].limitId;
					
				}
				var width = $(document).width();
				var height = $(document).height();
				var limitType = $("#limitType").val().trim();
				$.ajax({
					url : "removePayLimit.action",
					data : "limitIds=" + limitIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width : width - 18,
							height : height - 20
						}).datagrid('loadData', getData(limitType));
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			} else {
				return false;
			}
		}
	} ];
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
	</script>
	<div id="dlg" class="easyui-dialog" title="添加系统限额信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 350px; padding: 10px; background: #DFE8F6;">
		<table align="center">
				<tr height='40px;'>
					<td>限额名称：</td>
					<td><input type="text" id="add_limitName" name="limitName" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>限额类型：</td>
					<td><input type="text" id="add_limitType"  name="limitType" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='35px;'>
					<td>最大限额：</td>
					<td><input type="text" id="add_limit" name="limit"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		
		<div align="center">
			<input type="button" value="添加" id="addPayLimit" />
		</div>
	</div>	
	<div id="edit-dialog" class="easyui-dialog" title="编辑系统限额信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 350px; padding: 10px; background: #DFE8F6;">
		<table align="center">
		<input type="hidden" id="edit_limitId"/>
				<tr height='40px;'>
					<td>限额名称：</td>
					<td><input type="text" id="edit_limitName" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>限额类型：</td>
					<td><input type="text" id="edit_limitType"  /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='35px;'>
					<td>最大限额：</td>
					<td><input type="text" id="edit_limit" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		<div align="center">
			<input type="button" value="更新" id="updatePayLimit" />
		</div>
	</div>	
</body>
</html>

