<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/audiojs/audio.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
	-moz-user-select: none;
	background: url("image/button/btnout_bg_left.gif") no-repeat scroll left
		top transparent;
	float: left;
	height: 24px;
	margin: 2px 5px 0 0;
	outline: medium none;
	text-decoration: none;
}

#searchpanel a.pushBtn img {
	float: left;
	margin: 2px 0 4px 6px;
	border: 0;
}

#searchpanel a.pushBtn b {
	background: url("image/button/btnout_bg_right.gif") no-repeat scroll
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
	background: url("image/button/btnover_bg_left.gif") no-repeat scroll
		left top transparent;
	text-decoration: none;
}

#searchpanel a.pushBtn:hover b {
	background: url("image/button/btnover_bg_right.gif") no-repeat scroll
		right top transparent;
	color: #114477;
	font-size: 12px;
}

li {
	list-style: none;
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	zoom: 1;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" data-options="collapsible:true,collapsed:true"
		class="easyui-panel panel-margin-buttom" title="查询条件"
		style="height: 80px; padding: 10px; overflow: hidden;">
		<div id="voiceInfo">
		
		</div>
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>事故选项：</td>
				<td><select id="trafficIndex" style="width: 120px;">
						<option value="">请选择</option>
						<option value="RA">交通事故查询</option>
						<option value="CM">自助移车查询</option>
				</select></td>
				<td>语音状态：</td>
				<td><select id="voiceStatus" style="width: 120px;">
						<option value="">请选择</option>
						<option value="启用">启用</option>
						<option value="禁用">禁用</option>
				</select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <img width="20" height="20"
						src="image/icon/search.gif"> <b>查询</b>
				</a></td>
				<td><a id="clear" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <b>条件清空</b>
				</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="语音信息列表"
		style="height: 423px;"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'trafficIndex',width:200,align:'center'">事故选项</th>
				<th data-options="field:'voiceType',width:300,align:'center'">语音类型</th>
				<th data-options="field:'voiceInfo',width:200,align:'center'">备注信息</th>
				<th
					data-options="field:'voiceLocalStore',width:150,formatter:operate1,align:'center'">语音信息</th>
				<th
					data-options="field:'voiceStatus',width:200,formatter:operate2,align:'center'">语音状态</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" title="添加语音信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 300px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left: 10px; margin-top: 20px;">
			<form action="addWarningTone.action" method="post"
				enctype="application/x-www-form-urlencoded" id="addForm">
				<table align="center">
					<tr>
						<td>事故选项：</td>
						<td><select id="add_trafficIndex" name="trafficIndex"
							style="width: 180px;">
								<option value="">请选择</option>
								<option value="RA">交通事故查询</option>
								<option value="CM">自助移车查询</option>
						</select></td>
						<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					</tr>
					<tr>
						<td>语音类型：</td>
						<td><select id="add_voiceType" name="voiceType"
							style="width: 180px;">
								<option value="">请选择</option>
								<option value="mp3">mp3</option>
						</select></td>
						<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					</tr>
					<tr>
						<td>语音信息：</td>
						<td><input type="file" id="add_voiceLocalStore" name="voice"
							style="width: 180px;" /></td>
						<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					</tr>
					<tr>
						<td>备注信息：</td>
						<td><input type="text" id="add_voiceInfo" name="voiceInfo"
							style="width: 180px;" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center" style="margin-top: 20px;">
			<input type="button" value="添加" id="addWarningTone" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="编辑语音信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 300px; padding: 10px; background: #DFE8F6;">
		<div style="margin-left: 10px; margin-top: 20px;">
			<form action="updateWarningTone.action" method="post"
				enctype="multipart/form-data" id="updateForm">
				<input type="hidden" id="edit_id" name="id" />
				<table align="center">
					<tr>
						<td>事故选项：</td>
						<td><select id="edit_trafficIndex" name="trafficIndex"
							style="width: 180px;">
								<option value="">请选择</option>
								<option value="RA">交通事故查询</option>
								<option value="CM">自助移车查询</option>
						</select></td>
						<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					</tr>
					<tr>
						<td>语音类型：</td>
						<td><select id="edit_voiceType" name="voiceType"
							style="width: 180px;">
								<option value="">请选择</option>
								<option value="mp3">mp3</option>
						</select></td>
						<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					</tr>
					<tr>
						<td>语音信息：</td>
						<td><input type="file" id="edit_voiceLocalStore" name="voice"
							style="width: 180px;" /></td>
						<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					</tr>
					<tr>
						<td>备注信息：</td>
						<td><input type="text" id="edit_voiceInfo" name="voiceInfo"
							style="width: 180px;" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateWarningTone" />
		</div>
	</div>
	<script type="text/javascript">
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		function detail_close() {
			$('#detail_dialog').dialog('close');
		}
		function operate1(value, row, index) {
			if (value != null) {
				return '<a href=\'javascript:play();\'>播放</a>';
			}
		}
		function operate2(value, row, index) {
			if (row.voiceStatus == "启用") {
				return '<a href=\'javascript:display1();\'>启用</a>';
			} else {
				return '<a href=\'javascript:display1();\'>禁用</a>';
			}
		}
		function play() {
			$("#voiceInfo").empty();
			var row = $("#dg").datagrid('getSelected');
			var RA = row.voiceLocalStore;
			var audi = "<audio id=\"info\"autoplay=\"autoplay\"><source src=\"viewVoice.action?picPath="
					+ RA + "\" type=\"audio/mpeg\"></audio>";
			$("#voiceInfo").append(audi);
			audiojs.events.ready(function() {
				audiojs.createAll();
			});
		}
		function display1() {
			var row = $("#dg").datagrid('getSelected');
			$.ajax({
				url : "changeStatus.action",
				async : false,
				data : "id=" + row.id + "&trafficIndex=" + row.trafficIndex
						+ "&voiceStatus=" + row.voiceStatus,
				dataType : "json",
				type : "post",
				success : function(data) {
					alert(data.respInfo);
					$("#fresh").click();
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		}
		$("#addWarningTone").click(
				function() {
					var trafficIndex = $("#add_trafficIndex").val();
					var voiceType = $("#add_voiceType").val();
					if (trafficIndex == '') {
						alert("事故选项不能为空");
						return false;
					}
					if (voiceType == '') {
						alert("语音类型不能为空");
						return false;
					}
					var voiceLocalStore = $("#add_voiceLocalStore").val();
					if (voiceLocalStore == '') {
						alert("语音信息不能为空");
						return false;
					}
					var ls = voiceLocalStore.split('\\');
					var ls1 = ls[ls.length - 1];
					var vl = getAllLs().vls;
					if (vl.length > 0) {
						for ( var i = 0; i < vl.length; i++) {
							var vl1 = vl[i].split('/');
							var vl2 = vl1[vl1.length - 1];
							if (ls1 == vl2) {
								alert("语音文件名已存在,请修改");
								return false;
							}
						}
					}
					var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) {
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#dlg").dialog("close");
								trafficIndex = $("#trafficIndex").val().trim();
								voiceStatus = $("#voiceStatus").val().trim();
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData',
										getData(trafficIndex, voiceStatus));
							}
						}
					};
					$("#addForm").ajaxSubmit(options);

				});
		function getAllLs() {
			var rows = null;
			$.ajax({
				url : "getAllLs.action",
				type : "post",
				async : false,
				data : "",
				dataType : "json",
				success : function(data) {
					rows = data;
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			return rows;
		}
		$("#updateWarningTone").click(
				function() {
					var trafficIndex = $("#edit_trafficIndex").val();
					var voiceType = $("#edit_voiceType").val();
					if (trafficIndex == '') {
						alert("事故选项不能为空");
						return false;
					}
					if (voiceType == '') {
						alert("语音类型不能为空");
						return false;
					}
					var voiceLocalStore = $("#edit_voiceLocalStore").val();
					if (voiceLocalStore == '') {
						alert("语音信息不能为空");
						return false;
					}
					var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) {
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#edit-dialog").dialog("close");
								trafficIndex = $("#trafficIndex").val().trim();
								voiceStatus = $("#voiceStatus").val().trim();
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData',
										getData(trafficIndex, voiceStatus));
							}
						}
					};
					$("#updateForm").ajaxSubmit(options);

				});

		var toolbar = [
				{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$('#dlg').dialog('open');
						$("#add_trafficIndex").val("");
						$("#add_voiceType").val("");
						$("#add_voiceInfo").val("");
						$("#add_voiceLocalStore").val("");

					}
				},
				'-',
				{
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#dg').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择需要编辑的数据！");
							return false;
						} else if (rows.length > 1) {
							alert("一次只能编辑一行！");
						} else {
							var row = $("#dg").datagrid('getSelected');
							$('#edit-dialog').dialog('open');
							$("#edit_id").val(row.id);
							if (row.trafficIndex == "交通事故查询") {
								trafficIndex = "RA";
								$("#edit_trafficIndex").val(trafficIndex);
							}
							if (row.trafficIndex == "自助移车查询") {
								trafficIndex = "CM";
								$("#edit_trafficIndex").val(trafficIndex);
							}
							$("#edit_voiceType").val(row.voiceType);
							$("#edit_voiceInfo").val(row.voiceInfo);
						}
					}

				},
				'-',
				{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var rows = $('#dg').datagrid('getSelections');
						if (rows.length > 0) {
							if (window.confirm("确认删除这" + rows.length + "记录？")) {
								var ids = '';
								var voiceLocalStores = '';
								for ( var i = 0; i < rows.length; i++) {
									ids = ids + "," + rows[i].id;
									voiceLocalStores = voiceLocalStores + ","
											+ rows[i].voiceLocalStore;
								}
								$.ajax({
									url : "deleteWarningTone.action",
									data : "ids=" + ids + "&voiceLocalStores="
											+ voiceLocalStores,
									dataType : "json",
									type : "post",
									success : function(data) {
										alert(data.respInfo);
										var trafficIndex = $("#trafficIndex")
												.val().trim();
										var voiceStatus = $("#voiceStatus")
												.val().trim();
										$('#dg').datagrid({
											loadFilter : pagerFilter,
										}).datagrid(
												'loadData',
												getData(trafficIndex,
														voiceStatus));
									},
									error : function(data) {
										alert("error 后台出现错误！");
									}
								});
							} else {
								return false;
							}
						} else {
							alert("请选择数据！");
							return false;
						}

					}
				} ];
		function getData(trafficIndex, voiceStatus) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getWarningTone.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&trafficIndex=" + trafficIndex + "&voiceStatus="
						+ voiceStatus,
				dataType : "json",
				type : "post",
				success : function(data) {
					if (data.respCode == -1) {
						alert(data.respInfo);
						return false;
					} else {
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
				var trafficIndex = $("#trafficIndex").val().trim();
				var voiceStatus = $("#voiceStatus").val().trim();
				var newData = getData(trafficIndex, voiceStatus);
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
			var trafficIndex = $("#trafficIndex").val().trim();
			var voiceStatus = $("#voiceStatus").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid('loadData', getData(trafficIndex, voiceStatus));
			$("#searchpanel").panel({
				width : width - 20,

			});
			$("#search").click(function() {
				var trafficIndex = $("#trafficIndex").val().trim();
				var voiceStatus = $("#voiceStatus").val().trim();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(trafficIndex, voiceStatus));
			});
			$("#fresh").click(function() {
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 20,
					height : height - 45
				}).datagrid('loadData', getData(trafficIndex, voiceStatus));
			});

			$("#clear").click(function() {
				$("#trafficIndex").val("");
				$("#voiceStatus").val("");
			});
		});
	</script>
	<button id="fresh" style="display: none;"></button>
</body>
</html>