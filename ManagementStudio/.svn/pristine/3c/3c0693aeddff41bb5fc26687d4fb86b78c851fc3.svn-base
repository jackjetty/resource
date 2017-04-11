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
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td><td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="editProductId" />
	<table id="dg" class="easyui-datagrid" title="积分规则管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!--  <th data-options="field:'ruleId',width:100">规则编号</th>-->
				<th data-options="field:'ruleContent',width:100">规则说明</th>
				<th data-options="field:'startTime',width:100">开始时间</th>
				<th data-options="field:'endTime',width:100">结束时间</th>
				<!--  <th data-options="field:'remark',width:100">规则评论</th>
				<th data-options="field:'payMoney',width:100">支付金额</th>
				<th data-options="field:'buyScore',width:100">购买积分</th>-->
				<th data-options="field:'operate',width:100">规则操作</th>
				<th data-options="field:'sendScore',width:100">赠送积分</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData(startTime,endTime) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getScoreRule.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&startTime=" + startTime+  "&endTime=" + endTime,
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
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var newData = getData(startTime,endTime);
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
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(startTime,endTime));
		$("#searchpanel").panel({
			width : width - 18
		});
		$("#search").click(function() {
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(startTime,endTime));
				});
		$("#clear").click(function(){
			$("#startTime").datebox('setValue', '');
			$("#endTime").datebox('setValue', '');
			
		});
	$("#addScoreRule").click(function() {
		var ruleContent = $("#add_ruleContent").val().trim();
		if (ruleContent == '') {
			alert("规则说明不能为空！");
			return false;
		}
		
		var startTime = $("#add_startTime").datebox('getValue');
		if(startTime==''){
			alert("开始时间不能为空");
			return false;
		}
		var endTime = $("#add_endTime").datebox('getValue');
		if(endTime==''){
			alert("结束时间不能为空");
			return false;
		}
		if(endTime<startTime){
			alert("结束时间不能在开始时间之前");
			return false;
		}
		var sendScore = $("#add_sendScore").val().trim();
		if (sendScore == '') {
			alert("赠送积分不能为空！");
			return false;
		}
		if(!(/^(\+|-)?\d+$/.test(sendScore))|| sendScore<0){ 
			 alert("购买积分请输入正整数！");
		        return false;  
		}  
		var operate = $("#add_operate").val().trim();
		if (operate == '') {
			alert("规则不能为空！");
			return false;
		}
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addScoreRule.action",
			data : "ruleContent=" + ruleContent
					+ "&startTime=" + startTime+ "&endTime=" + endTime
					+  "&operate=" + operate+ "&sendScore=" + sendScore,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var startTime = $("#startTime").datebox('getValue');
					var endTime = $("#endTime").datebox('getValue');
					$('#dlg').dialog('close');
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(startTime,endTime));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updateScoreRule").click(function() {
	var ruleContent = $("#edit_ruleContent").val().trim();
	if (ruleContent == '') {
		alert("规则说明不能为空！");
		return false;
	}
	
	
	var startTime = $("#edit_startTime").datebox('getValue');
	if(startTime==''){
		alert("开始时间不能为空");
		return false;
	}
	var endTime = $("#edit_endTime").datebox('getValue');
	if(endTime==''){
		alert("结束时间不能为空");
		return false;
	}
	if(endTime<startTime){
		alert("结束时间不能在开始时间之前");
		return false;
	}
	var sendScore = $("#edit_sendScore").val().trim();
	if (sendScore == '') {
		alert("赠送积分不能为空！");
		return false;
	}
	if(!(/^(\+|-)?\d+$/.test(sendScore))|| sendScore<0){ 
		 alert("购买积分请输入正整数！");
	        return false;  
	}  
	var operate = $("#edit_operate").val().trim();
	if (operate == '') {
		alert("规则操作不能为空！");
		return false;
	}
	
	
	var width = $(document).width();
	var height = $(document).height();
	
	$.ajax({
		url : "updateScoreRule.action",
		async : false,
		data : "ruleContent=" + ruleContent
		+ "&startTime=" + startTime+ "&endTime=" + endTime
		+ "&sendScore=" + sendScore+ "&operate=" + operate
		+"&ruleId="+$("#edit_ruleId").val(),
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 18,
					height : height - 20
				}).datagrid('loadData', getData(startTime,endTime));
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
			$("#add_ruleContent").val('');
			$("#add_startTime").datebox('setValue','');
			$("#add_endTime").datebox('setValue','');
			$("#add_sendScore").val('');
			$("#add_operate").val('');
			
			
			
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
				$('#edit_ruleId').val(row.ruleId);
				$("#edit_ruleContent").val(row.ruleContent);
				$("#edit_startTime").datebox('setValue',row.startTime);
				$("#edit_endTime").datebox('setValue',row.endTime);
				$("#edit_sendScore").val(row.sendScore);
				$("#edit_operate").val(row.operate);
				
				
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (window.confirm("确认删除这" + rows.length + "记录？")) {
				var ruleIds = '';
				for ( var i = 0; i < rows.length; i++) {
					ruleIds = ruleIds + "," + rows[i].ruleId;
					
				}
				var width = $(document).width();
				var height = $(document).height();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				$.ajax({
					url : "removeScoreRule.action",
					data : "ruleIds=" + ruleIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width : width - 18,
							height : height - 20
						}).datagrid('loadData', getData(startTime,endTime));
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
	<div id="dlg" class="easyui-dialog" title="添加积分规则信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 350px; padding: 10px; background: #DFE8F6;">
		<table align="center">
				<tr height='40px;'>
					<td>规则说明：</td>
					<td><input type="text" id="add_ruleContent" name="ruleContent" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>开始时间：</td>
					<td><input type="text" id="add_startTime"class="easyui-datebox" editable="false" name="startTime" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='35px;'>
					<td>结束时间：</td>
					<td><input type="text" id="add_endTime"class="easyui-datebox" editable="false" name="endTime"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>赠送积分：</td>
					<td><input type="text" id="add_sendScore" name="sendScore" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>规则操作：</td>
					<td><input type="text" id="add_operate" name="operate"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		
		<div align="center">
			<input type="button" value="添加" id="addScoreRule" />
		</div>
	</div>	
	<div id="edit-dialog" class="easyui-dialog" title="编辑积分规则信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 350px; padding: 10px; background: #DFE8F6;">
		<table align="center">
		<input type="hidden" id="edit_ruleId"/>
				<tr height='40px;'>
					<td>规则说明：</td>
					<td><input type="text" id="edit_ruleContent"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>开始时间：</td>
					<td><input type="text" id="edit_startTime"class="easyui-datebox" editable="false" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='35px;'>
					<td>结束时间：</td>
					<td><input type="text" id="edit_endTime"class="easyui-datebox" editable="false" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>赠送积分：</td>
					<td><input type="text" id="edit_sendScore"  /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height='40px;'>
					<td>规则操作：</td>
					<td><input type="text" id="edit_operate" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		<div align="center">
			<input type="button" value="更新" id="updateScoreRule" />
		</div>
	</div>	
	<div id="selectBusiness" class="easyui-dialog" title="积分规则"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 480px; padding: 10px; background: #DFE8F6;">
		<div id="busi" class="easyui-panel" title="积分规则树"
			style="height: 380px; width: 285px; padding: 8px;">
			<ul id="tree" class="easyui-tree"
				data-options="animate:true,checkbox:true,lines:true">
			</ul>
		</div>
		<div align="center" style="heigth: 50px; line-height: 50px;">
			<input type="button" value="确定" id="develop" /> &nbsp;&nbsp;&nbsp;<input
				type="button" value="取消" id="cancel" />
		</div>
	</div>
	<button id="fresh" style="display: none;"></button>
	
	
</body>
</html>

