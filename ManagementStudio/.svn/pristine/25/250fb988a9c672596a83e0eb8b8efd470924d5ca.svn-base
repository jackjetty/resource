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
	background: url("images/button/btnout_bg_left.gif") no-repeat scroll
		left top transparent;
	float: left;
	height: 24px;
	margin: 4px 5px 0 0;
	outline: medium none;
	text-decoration: none;
}

#searchpanel a.pushBtn img {
	float: left;
	margin: 2px 0 4px 6px;
	border: 0;
}

#searchpanel a.pushBtn b {
	background: url("images/button/btnout_bg_right.gif") no-repeat scroll
		right top transparent;
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
	background: url("images/button/btnover_bg_left.gif") no-repeat scroll
		left top transparent;
	text-decoration: none;
}

#searchpanel a.pushBtn:hover b {
	background: url("images/button/btnover_bg_right.gif") no-repeat scroll
		right top transparent;
	color: #114477;
	font-size: 12px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">

	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="返回码查询" style="height: 80px; padding: 10px; overflow: hidden;">
		<table>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>返回码：</td>
				<td><input type="text" id="returnCode" style="width: 95px;" /></td>
				<td>业务种类：</td>
				<td><select id="busId" style="width: 95px; height: 23px;">
				</select></td>
				<td width="130px;" align="center"><input type="checkbox" id="hasShow"/>有提示信息</td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <img width="20" height="20"
						src="images/icon/search.gif"> <b>查询</b>
				</a></td>
				<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="editReturnCodeId" />
	<table id="dg" class="easyui-datagrid" title="返回码管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'id',width:150">数据库自增长标识</th> -->
				<th data-options="field:'bType',width:60">业务种类</th>
				<th data-options="field:'returnCode',width:60,sortable:true">返回码</th>
				<th data-options="field:'codeMeaning',width:150">代码含义</th>
				<th data-options="field:'show',width:200">提示信息</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		function getData(busId, returnCode,hasShow) {
			if (busId == null) {
				busId = -1;
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
						+ "&busId=" + busId + "&returnCode=" + returnCode+"&hasShow=" + hasShow,
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
			if (typeof data.originalRows == 'undefined') {
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
			} else {
				var returnCode = $("#returnCode").val().trim();
				var busId = $("#busId").val();
				if (busId == "null") {
					busId = null;
				}
				var hasShow = $("#hasShow").is(':checked');
				var newData = getData(busId, returnCode,hasShow);
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
			var hasShow = $("#hasShow").is(':checked');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 18,
				height : height - 120
			}).datagrid('loadData', getData(busId, returnCode,hasShow));
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
					for ( var i = 0; i < data.length; i++) {
						option = $("<option value='"+data[i].busId+"'>"
								+ data[i].btype + "</option>");
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
				if (busId == "null") {
					busId = null;
				}
				var hasShow = $("#hasShow").is(':checked');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(busId, returnCode,hasShow));
			});
			$("#clear").click(function(){
				$("#busid1").attr('selected',true);
				$("#returnCode").val('');
				$("#hasShow").attr("checked", false);
				
			});
			$("#addReturnCode").click(
					function() {
						var busId = $("#add_busId").val();
						if (busId == '') {
							alert("业务种类不能为空");
							return false;
						}
						var returnCode = $("#add_returnCode").val().trim();
						if (returnCode == '') {
							alert("返回码代不能为空！");
							return false;
						}
						if(returnCode.length > 6){
							alert("返回代码不能多于6位！");
							return false;
						}
						var codeMeaning = $("#add_codeMeaning").val().trim();
						if (codeMeaning == '') {
							alert("代码含义不能为空！");
							return false;
						}
						var show =  $("#add_show").val().trim();
						var width = $(document).width();
						var height = $(document).height();
						$.ajax({
							url : "addReturnCode.action",
							data : "busId=" + busId + "&returnCode="
									+ returnCode + "&codeMeaning="
									+ codeMeaning+ "&show="
									+ show,
							dataType : "json",
							type : "post",
							success : function(data) {
								var returnCode = $("#returnCode").val().trim();
								var busId = $("#busId").val();
								if (busId == "null") {
									busId = null;
								}
								var hasShow = $("#hasShow").is(':checked');
								if (data.respCode == 0) {
									alert(data.respInfo);
									$('#dlg').dialog('close');
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData',
											getData(busId, returnCode,hasShow));
								} else {
									alert(data.respInfo);
								}

							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});

			$("#updateReturnCode").click(
					function() {
						var id = $("#editReturnCodeId").val();
						var returnCode = $("#edit_returnCode").val().trim();
						var busId = $("#edit_busId").val().trim();
						if (busId == '') {
							alert("业务种类不能为空");
							return false;
						}
						if (returnCode == '') {
							alert("返回代码不能为空！");
							return false;
						}
						if(returnCode.length > 6){
							alert("返回代码不能多于6位！");
							return false;
						}
						var codeMeaning = $("#edit_codeMeaning").val().trim();
						if (codeMeaning == '') {
							alert("代码含义不能为空！");
							return false;
						}
						var show =  $("#edit_show").val().trim();
						var width = $(document).width();
						var height = $(document).height();
						$.ajax({
							url : "updateReturnCode.action",
							async : false,
							data : "id=" + id + "&returnCode=" + returnCode
									+ "&codeMeaning=" + codeMeaning + "&busId="
									+ busId + "&show="
									+ show,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								var returnCode = $("#returnCode").val().trim();
								var busId = $("#busId").val();
								if (busId == "null") {
									busId = null;
								}
								var hasShow = $("#hasShow").is(':checked');
								if (data.respCode == 0) {
									$("#edit-dialog").dialog("close");
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										width : width - 18,
										height : height - 120
									}).datagrid('loadData',
											getData(busId, returnCode,hasShow));
								}
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
		});

		var toolbar = [
				{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$("#dlg").dialog("open");
						$("#add_returnCode").val('');
						$("#add_codeMeaning").val('');
						$("#add_busId").val('');
						$("#add_show").val("");
						$.ajax({
									url : "getBusinessType.action",
									async : false,
									type : "post",
									data : "",
									dataType : "json",
									success : function(data) {
										$("#add_busId option").remove();
										var option = $("<option value=''>请选择</option>");
										$("#add_busId").append(option);
										for ( var i = 0; i < data.length; i++) {
											option = $("<option value='"+data[i].busId+"'>"
													+ data[i].btype
													+ "</option>");
											$("#add_busId").append(option);
										}
									},
									error : function(data) {
										alert("error 后台出现错误！");
									}
								});
					}
				},
				'-',
				{
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var busId = "";
						var rows = $('#dg').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择需要编辑的数据！");
							return false;
						} else if (rows.length > 1) {
							alert("一次只能编辑一行！");
							return false;
						} else {
							var row = $("#dg").datagrid('getSelected');
							$.ajax({
								url : "getBusinessType.action",
								async : false,
								type : "post",
								data : "",
								dataType : "json",
								success : function(data) {
									$("#edit_busId option").remove();
									for ( var i = 0; i < data.length; i++) {
										option = $("<option value='"+data[i].busId+"'>"
											+ data[i].btype
											+ "</option>");
										if(data[i].btype == row.bType){
											busId = busId + data[i].busId;
										}
										$("#edit_busId").append(option);
										
									}
								},
								error : function(data) {
									alert("error 后台出现错误！");
								}
							});
							$("#editReturnCodeId").val(row.id);
							$('#edit-dialog').dialog('open');
							$("#edit_returnCode").val(row.returnCode);
							$("#edit_codeMeaning").val(row.codeMeaning);
							$("#edit_busId").val(busId);
							$("#edit_show").val(row.show);
						}
					}

				},
				'-',
				{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {

						var rows = $('#dg').datagrid('getSelections');
						if (window.confirm("确认删除这" + rows.length + "记录？")) {
							var ids = '';
							for ( var i = 0; i < rows.length; i++) {
								ids = ids + "," + rows[i].id;
							}
							var width = $(document).width();
							var height = $(document).height();

							var returnCode = $("#returnCode").val();
							var busId = $("#busId").val();
							$.ajax({
								url : "removeReturnCode.action",
								data : "ids=" + ids,
								dataType : "json",
								type : "post",
								success : function(data) {
									alert(data.respInfo);
									if (busId == "null") {
										busId = null;
									}
									var hasShow = $("#hasShow").is(':checked');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										width : width - 18,
										height : height - 120
									}).datagrid('loadData',
											getData(busId, returnCode,hasShow));
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
	</script>
	<div id="dlg" class="easyui-dialog" title="添加返回码信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 330px; padding: 10px; background: #DFE8F6;">
		<form action="addReturnCode.action" method="post"
			enctype="application/x-www-form-urlencoded" id="addForm">
			<div>
				<ul style="list-style: none;">
					<li style="line-height: 40px; height: 40px;">业务种类：<select
						id="add_busId" style="width: 155px;" name="busId"></select><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
					<li style="line-height: 40px; height: 40px;">返回代码：<input
						type="text" id="add_returnCode" name="returnCode" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
					<li style="line-height: 40px; height: 40px;">代码含义：<input
						type="text" id="add_codeMeaning" name="codeMeaning" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
					<li style="line-height: 40px; height: 40px;">提示信息：<textarea rows="3" cols="15" id="add_show" name="show" style="width:150px;height:80px;"></textarea>  </li>
				</ul>
			</div>
		</form>
		<div align="center" style="margin-top: 60px;">
			<input type="button" value="添加" id="addReturnCode" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="编辑返回码"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 330px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">业务种类：<select
					id="edit_busId" style="width: 155px;"></select><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">返回代码：<input
					type="text" id="edit_returnCode" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">代码含义：<input
					type="text" id="edit_codeMeaning" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">提示信息：<textarea rows="3" cols="15" id="edit_show" style="width:150px;height:80px;"></textarea>    </li>
			</ul>
		</div>
		<div align="center" style="margin-top: 60px;">
			<input type="button" value="更新" id="updateReturnCode" />
		</div>
	</div>
	
	<button id="fresh" style="display: none;"></button>

</body>
</html>

