<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
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
	<table id="dg" class="easyui-datagrid" title="图片信息列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th
					data-options="field:'picLocalStore',align:'center',width:80,formatter:operate1">缩略图</th>
				<th
					data-options="field:'picTime',width:80,sortable:true,align:'center'">上传时间</th>
				<th
					data-options="field:'picState',width:80,sortable:true,align:'center'">图片状态</th>
				<th
					data-options="field:'operate2',align:'center',width:80,formatter:operate2">原图
				</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		function detail_close() {
			$('#detail_dialog').dialog('close');
		}
		function operate1(value, row, index) {
			if (value != null) {
				return "<img  style=\"width:60px;height:60px;\" id=\"detail_Url1\" src=\"viewImages.action?picPath="
						+ value + "\" alt=\"\" />";
			}
		}
		function operate2(value, row, index) {
			return "<a href=\"javascript:display2('"+row.picLocalStore+"');\">查看</a>";
		}
		/*添加一个新选项卡面板
		self.parent.$('#tabs').tabs('add',{
		 title:'New Tab ',
		 content:'Tab Body ',
		 iconCls:'',
		 closable:true,
		 tools:[{
		 iconCls:'icon-mini-refresh',
		 handler:function(){
		 alert('refresh');
		 }
		 }]
		 });  */
		var toolbar = [ {
			text : '导出',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length > 0) {
					if (window.confirm("确认导出这" + rows.length + "记录？")) {
						var picLocalStores = '';
						for ( var i = 0; i < rows.length; i++) {
							picLocalStores = picLocalStores + ","
									+ rows[i].picLocalStore;
						}
						var lianjie = "toImageDown.action?picLocalStores="+picLocalStores;
						window.location.href = lianjie;
					} else {
						return false;
					}
				} else {
					alert("请选择数据！");
					return false;
				}

			}
		} ,{
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length > 0) {
					if (window.confirm("确认删除这" + rows.length + "记录？")) {
						var picIds = '';
						var picLocalStores = '';
						for ( var i = 0; i < rows.length; i++) {
							picIds = picIds + "," + rows[i].picId;
							picLocalStores = picLocalStores + ","
									+ rows[i].picLocalStore;
						}
						$
								.ajax({
									url : "deleteBakPic.action",
									data : "picIds=" + picIds
											+ "&picLocalStores="
											+ picLocalStores,
									dataType : "json",
									type : "post",
									success : function(data) {
										alert(data.respInfo);
										var startTime = $("#startTime")
												.datebox('getValue');
										var endTime = $("#endTime").datebox(
												'getValue');
										$('#dg').datagrid({
											loadFilter : pagerFilter,
										}).datagrid('loadData',
												getData(startTime, endTime));
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
		function display2(adr) {
			//var row = $("#dg").datagrid('getSelected');
			window.open("page/bakPic.jsp?carMoveId=" + adr, "");
		}
		function getData(startTime, endTime) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getBakPic.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&startTime=" + startTime + "&endTime=" + endTime,
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
			var enddate = year + "-" + month + "-" + day;
			today.setDate((today.getDate() - 6));
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var day = today.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			var startdate = year + "-" + month + "-" + day;
			$("#startTime").datebox('setValue', startdate);
			$("#endTime").datebox('setValue', enddate);
			var width = $(document).width();
			var height = $(document).height();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid('loadData', getData(startTime, endTime));
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#search").click(function() {
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(startTime, endTime));
			});
			$("#fresh").click(function() {
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 20,
					height : height - 45
				}).datagrid('loadData', getData(startTime, endTime));
			});

			$("#clear").click(function() {
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
				var enddate = year + "-" + month + "-" + day;
				today.setDate((today.getDate() - 6));
				var year = today.getFullYear();
				var month = today.getMonth() + 1;
				var day = today.getDate();
				if (month < 10) {
					month = "0" + month;
				}
				if (day < 10) {
					day = "0" + day;
				}
				var startdate = year + "-" + month + "-" + day;
				$("#startTime").datebox('setValue', startdate);
				$("#endTime").datebox('setValue', enddate);
			});
		});
	</script>
	<button id="fresh" style="display: none;"></button>
</body>
</html>