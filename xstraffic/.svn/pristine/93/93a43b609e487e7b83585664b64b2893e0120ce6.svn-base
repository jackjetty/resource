<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=90ebaec1467373aecb8ed95b418d5e03"></script>
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
/* focus */
.focus {
	width: 460px;
	height: 280px;
	overflow: hidden;
	position: relative;
}

#focus ul {
	height: 380px;
	position: absolute;
}

#focus ul li {
	float: left;
	width: 460px;
	height: 280px;
	overflow: hidden;
	position: relative;
	background: #000;
}

#focus ul li div {
	position: absolute;
	overflow: hidden;
}

#focus .btnBg {
	position: absolute;
	width: 460px;
	height: 20px;
	left: 0;
	bottom: 0;
	background: #000;
}

#focus .btn {
	position: absolute;
	width: 400px;
	height: 10px;
	padding: 5px 10px;
	right: 0;
	bottom: 0;
	text-align: right;
}

#focus .btn span {
	display: inline-block;
	_display: inline;
	_zoom: 1;
	width: 25px;
	height: 10px;
	_font-size: 0;
	margin-left: 5px;
	cursor: pointer;
	background: #fff;
}

#focus .btn span.on {
	background: #fff;
}

#focus .preNext {
	width: 45px;
	height: 100px;
	position: absolute;
	top: 90px;
	background: url(image/sprite.png) no-repeat 0 0;
	cursor: pointer;
}

#focus .pre {
	left: 0;
}

#focus .next {
	right: 0;
	background-position: right top;
}

#tabbox {
	width: 450px;
	overflow: hidden;
	margin: 0 auto;
	margin-top: -5px;
	margin-left: -10px;
}

.tab_conbox {
	border: 1px solid #999;
	border-top: none;
}

.tab_con {
	display: none;
}

.tabs {
	height: 32px;
	border-bottom: 1px solid #999;
	border-left: 1px solid #999;
	width: 100%;
}

.tabs li {
	height: 31px;
	line-height: 31px;
	float: left;
	border: 1px solid #999;
	margin-bottom: -1px;
	background: #e0e0e0;
	overflow: hidden;
	position: relative;
}

.tabs li a {
	display: block;
	padding: 0 20px;
	border: 1px solid #fff;
	outline: none;
}

.tabs li a:hover {
	background: #ccc;
}

.tabs .thistab,.tabs .thistab a:hover {
	background: #fff;
	border-bottom: 1px solid #fff;
}

.tab_con {
	padding: 12px;
	font-size: 14px;
	line-height: 175%;
}

#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="editRoleId" />
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="微信指令查询" style="height: 80px; padding: 10px; overflow: hidden;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>指令编号：</td>
				<td><input type="text" id="microId"
					style="width: 100px;" /></td>
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
	<table id="dg" class="easyui-datagrid" title="指令信息列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:true,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th
					data-options="field:'microId',width:120,sortable:true,align:'center'">指令编号</th>
				<th
					data-options="field:'microMeaning',width:150,align:'center'">指令信息</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var toolbar = [  {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$('#dlg').dialog('open');
				$("#add_roleId").val("");
				$("#add_roleName").val("");
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
				} else {
					var row = $("#dg").datagrid('getSelected');
					$('#edit-dialog').dialog('open');
					$("#edit_roleId").val(row.roleId);
					$("#edit_roleName").val(row.roleName);
				}
			}

		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (window.confirm("确认删除这" + rows.length + "记录？")) {
					var roleIds = '';
					for ( var i = 0; i < rows.length; i++) {
						roleIds = roleIds + "," + rows[i].roleId;
					}
					$.ajax({
						url : "removeRole.action",
						data : "roleIds=" + roleIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							roleName = $("#roleName").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(roleName));
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

		
		function detail_close() {
			$('#detail_dialog').dialog('close');
		}
		function getData(microId) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getMicroMessage.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&microId=" + microId,
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
				var microId = $("#microId").val().trim();
				var newData = getData(microId);
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
			var microId = $("#microId").val().trim();
			var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid(
					'loadData',
					getData(microId));
			$("#searchpanel").panel({
				width : width - 20,
			});
			$("#search").click(
					function() {
						var microId = $("#microId").val().trim();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid(
								'loadData',
								getData(microId));
					});

			$("#clear").click(function() {
				$("#microId").val("");
			});
		});
	</script>
</body>
</html>