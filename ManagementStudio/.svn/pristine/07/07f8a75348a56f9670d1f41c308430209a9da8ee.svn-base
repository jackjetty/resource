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
#div1 td{
	height: 35px;
}
#div2 td{
	height: 35px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
		<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="抽奖活动查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
				<td>活动名称：</td>
				<td><input type="text" id="eventName" style="width: 95px;" /></td>
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
	<table id="dg" class="easyui-datagrid" title="抽奖活动列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'eventName',width:100">活动名称</th>
				<th data-options="field:'startTime',width:150,sortable:true">开始时间</th>
				<th data-options="field:'endTime',width:150,sortable:true">结束时间</th>
				<th data-options="field:'deductScore',width:150,align:'center'">扣除积分数</th>
				<th data-options="field:'times',width:150,sortable:true">抽奖次数</th>
				<th data-options="field:'opt', width:150,formatter:operate,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	
	
	<script type="text/javascript">
	function operate(value, row, index) {
		if (row.status == '启用')
			return '<a href=\'javascript:exchange();\'><font color=\'red\'>结束</font></a>';
		else if (row.status == '结束')
			return '<a href=\'javascript:exchange();\'>启用</a>';
	}
	
	function exchange() {
		var row = $("#dg").datagrid('getSelected');
		$.ajax({
			url : "changeStatus.action",
			async : false,
			data : "id=" + row.id,
			dataType : "json",
			type : "post",
			success : function(data) {
				alert(data.respInfo);
				var eventName = $("#eventName").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
				}).datagrid('loadData', getData(eventName, startTime, endTime));
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	}
	
	
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		}
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$('#dlg').dialog('open');
				$("#add_eventName").val('');
				$("#add_startTime").datebox('setValue','');
				$("#add_endTime").datebox('setValue','');
				$("#add_deductScore").val('');
				$("#add_times").val('');
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
				} else{
					var row = $("#dg").datagrid('getSelected');
					$('#edit-dialog').dialog('open');
					$("#edit_id").val(row.id);
					$("#edit_eventName").val(row.eventName);
					$("#edit_startTime").datebox('setValue',row.startTime);
					$("#edit_endTime").datebox('setValue',row.endTime);
					$("#edit_deductScore").val(row.deductScore);
					$("#edit_times").val(row.times);
					$("#edit_status").val(row.status);
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var ids = '';
					for(var i=0;i<rows.length;i++){
						ids = ids + ","+rows[i].id;
					}
					$.ajax({
						url : "removeSweepStakes.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var eventName = $("#eventName").val().trim();
							var startTime = $("#startTime").datebox('getValue');
							var endTime = $("#endTime").datebox('getValue');
							$('#dg').datagrid({
								loadFilter : pagerFilter,
							}).datagrid('loadData', getData(eventName, startTime, endTime));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					
				}else{
					return false;
				}
			}
		}];
		
		function getData(eventName, startTime, endTime) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getSweepStakes.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&eventName=" + eventName + "&startTime=" + startTime+"&endTime="+endTime,
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
				var eventName = $("#eventName").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var newData = getData(eventName, startTime, endTime);
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
			var eventName = $("#eventName").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(eventName, startTime, endTime));
			
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				eventName = $("#eventName").val().trim();
				startTime = $("#startTime").datebox('getValue');
				endTime = $("#endTime").datebox('getValue');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(eventName, startTime, endTime));
			});
			$("#clear").click(function(){
				$("#startTime").datebox('setValue', '');
				$("#endTime").datebox('setValue', '');
				$("#eventName").val('');
				
			});
			
			$("#addSweepStakes").click(function(){
				var eventName = $("#add_eventName").val().trim();
				var deductScore=$("#add_deductScore").val().trim();
				var times=$("#add_times").val().trim();
				if(eventName==''){
					alert("活动名称不能为空");
					return false;
				}
				if(deductScore==''){
					alert("扣除积分不能为空");
					return false;
				}
				if(times==''){
					alert("抽奖次数不能为空");
					return false;
				}
				if(deductScore.length>0){
					var reg=/^-?\d+$/;
					if(!reg.test(deductScore)){
						alert("扣除积分请输入整数");
						return false;
					}else if(parseInt(deductScore)<=0){
						alert("扣除积分必须大于0");
						return false;
					}
				}
				if(times.length>0){
					var reg=/^-?\d+$/;
					if(!reg.test(times)){
						alert("抽奖次数请输入整数");
						return false;
					}else if(parseInt(times)<=0){
						alert("抽奖次数必须大于0");
						return false;
					}
				}

				$.ajax({
					url : "addSweepStakes.action",
					data : "eventName=" + eventName +"&deductScore="+deductScore+"&times="+times,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dlg').dialog('close');
						eventName = $("#eventName").val().trim();
						startTime = $("#startTime").datebox('getValue');
						endTime = $("#endTime").datebox('getValue');
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(eventName, startTime, endTime));
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
			});
			
			$("#updateSweepStakes").click(function(){
				var eventName = $("#edit_eventName").val().trim();
				var startTime = $("#edit_startTime").datebox('getValue');
				var endTime = $("#edit_endTime").datebox('getValue');
				var deductScore=$("#edit_deductScore").val().trim();
				var times=$("#edit_times").val().trim();
				var status=$("#edit_status").val();
				if(eventName==''){
					alert("活动名称不能为空");
					return false;
				}
				if(deductScore==''){
					alert("扣除积分不能为空");
					return false;
				}
				if(times==''){
					alert("抽奖次数不能为空");
					return false;
				}
				if(deductScore.length>0){
					var reg=/^-?\d+$/;
					if(!reg.test(deductScore)){
						alert("扣除积分请输入整数");
						return false;
					}else if(parseInt(deductScore)<=0){
						alert("扣除积分必须大于0");
						return false;
					}
				}
				if(times.length>0){
					var reg=/^-?\d+$/;
					if(!reg.test(times)){
						alert("抽奖次数请输入整数");
						return false;
					}else if(parseInt(times)<=0){
						alert("抽奖次数必须大于0");
						return false;
					}
				}
				$.ajax({
					url : "updateSweepStakes.action",
					data : "eventName=" + eventName + "&startTime=" + startTime+"&endTime="+endTime+
							"&deductScore="+deductScore+"&times="+times+"&status="+status+
							"&id="+$("#edit_id").val(),
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#edit-dialog').dialog('close');
						eventName = $("#eventName").val().trim();
						startTime = $("#startTime").datebox('getValue');
						endTime = $("#endTime").datebox('getValue');
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(eventName, startTime, endTime));
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			
				
				
		});
	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加抽奖活动信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 380px; height: 320px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
			<table align="center">
				<tr>
					<td>活动名称：</td>
					<td><input type="text" id="add_eventName" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<!-- <tr>
					<td>开始时间：</td>
					<td><input type="text" id="add_startTime" class="easyui-datebox" editable="false" style="width: 158px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input type="text" id="add_endTime" class="easyui-datebox" editable="false" style="width: 158px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr> -->
				<tr>
					<td>扣除积分：</td>
					<td><input type="text" id="add_deductScore" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>抽奖次数：</td>
					<td><input type="text" id="add_times" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addSweepStakes" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改抽奖活动信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 380px; height: 320px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_id" />
			<input type="hidden" id="edit_status" />
			<table align="center">
				<tr>
					<td>活动名称：</td>
					<td><input type="text" id="edit_eventName" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td><input type="text" id="edit_startTime" class="easyui-datebox" editable="false" style="width: 158px;" disabled="true" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input type="text" id="edit_endTime" class="easyui-datebox" editable="false" style="width: 158px;" disabled="true"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>扣除积分：</td>
					<td><input type="text" id="edit_deductScore" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>抽奖次数：</td>
					<td><input type="text" id="edit_times" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
			</table>
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="修改" id="updateSweepStakes" />
		</div>
	</div>
	
	
</body>
</html>