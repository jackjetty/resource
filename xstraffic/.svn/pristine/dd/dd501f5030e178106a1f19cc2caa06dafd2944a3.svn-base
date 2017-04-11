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
				<td>报案手机号：</td>
				<td><input type="text" id="reportPhoneNumber"
					style="width: 100px;" /></td>
				<td>处理状态：</td>
				<td><select id="congestState" style="width: 120px;">
						<option value="">请选择</option>
						<option value="上报">上报</option>
						<option value="处理">处理</option>
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
	<table id="dg" class="easyui-datagrid" title="拥堵上报列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:true,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th
					data-options="field:'congestId',width:80,sortable:true,align:'center'">拥堵编号</th>
				<th
					data-options="field:'reportTime',width:120,sortable:true,align:'center'">报案时间</th>
				<th
					data-options="field:'reportPhoneNumber',width:80,sortable:true,align:'center'">报案手机号</th>
				<th
					data-options="field:'picInfo',align:'center',width:80,formatter:operate1">图片信息
				</th>
				<th
					data-options="field:'textInfo',align:'center',width:80,formatter:operate2">文本信息
				</th>
				<th data-options="field:'congestState',width:80,align:'center'">处理状态</th>
				<th
					data-options="field:'oprat',align:'center',width:80,formatter:operate3">操作
				</th>
			</tr>
		</thead>
	</table>
	<div id="text_dialog" class="easyui-dialog" title="文本信息"
		data-options="closed:true"
		style="width: 600px; height: 300px; padding: 20px;">
		<div id="div1" style="margin-left: 70px; height: 200px;">
			<table id="textTab" cellspacing="0" style="width: 400px;">
			</table>
		</div>
	</div>
	<script type="text/javascript">
	    var managerName = '${session.Manager.userId}';
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		function detail_close() {
			$('#detail_dialog').dialog('close');
		}
		function operate1(value, row, index) {
			if (value != null) {
				return '<a href=\'javascript:display1();\'>查看</a>';
			}
		}
		function operate2(value, row, index) {
			if (value != null) {
				return '<a href=\'javascript:display2();\'>查看</a>';
			}
		}
		function operate3(value, row, index) {
			return '<a href=\'javascript:display3();\' style=\'text-align:center;\'>处理</a>';
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
		function display1() {
			var row = $("#dg").datagrid('getSelected');
			window.open("page/TbPicInfo.jsp?congestId=" + row.congestId, "");
		}
		 function display2() {
			 $("#textTab").empty();
				var row = $("#dg").datagrid('getSelected');
				var data = getTbAccidentText(row.congestId);
				var tr = "<tr style=\"height: 25px;\"><td style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; width:30%;background-color: #EDEDED;;\"valign=\"top\">时间</td><td style=\"text-align: center; vertical-align:middle;border: 1px solid #CCCCCC;width:70%; background-color: #EDEDED;\"valign=\"top\">备注信息</td></tr>";
				$('#text_dialog').dialog('open');
				for ( var i = 0; i < data.length; i++) {
					var textMessage = data[i].textMessage;
					var textTime = data[i].textTime;
					tr += "<tr style=\"height: 25px;\"><td id=\"textTime\" style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
							+ textTime
							+ "</td><td id=\"textInfo\" style=\"text-align: left;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
							+ textMessage + "</td></tr>";
				}
				$("#textTab").append(tr);
			}
		 function getTbAccidentText(accidentId) {
				$.ajax({
					url : "getTbAccidentText.action",
					async : false,
					data : "accidentId=" + accidentId,
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
		 function display3(){
			 var row = $("#dg").datagrid('getSelected');
			 $.ajax({
					url : "changeCgState.action",
					type : "post",
					async : false,
					data : "congestId=" + row.congestId
					+ "&managerName=" + managerName,
					dataType : "json",
					success : function(data) {
						alert(data.respInfo);
						$("#fresh").click();
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			 
		 }
		function getData(startTime, endTime, reportPhoneNumber,
				congestState) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getCongest.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&startTime=" + startTime + "&endTime=" + endTime
						+ "&reportPhoneNumber=" + reportPhoneNumber
						+ "&congestState="+ congestState,
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
				var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
				var congestState = $("#congestState").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var newData = getData(startTime, endTime, reportPhoneNumber,
						 congestState);
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
			var congestState = $("#congestState").val().trim();
			var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid(
					'loadData',
					getData(startTime, endTime, reportPhoneNumber, 
							congestState));
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#search").click(
					function() {
						var reportPhoneNumber = $("#reportPhoneNumber").val()
								.trim();
						var congestState = $("#congestState").val().trim();
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid(
								'loadData',
								getData(startTime, endTime, reportPhoneNumber,
										congestState));
					});
			$("#fresh").click(function() {
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 20,
					height : height - 45
				}).datagrid('loadData', getData(startTime, endTime, reportPhoneNumber,
						congestState));
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
				$("#reportPhoneNumber").val("");
				$("#congestState").val("");
			});
		});
	</script>
	<button id="fresh" style="display: none;"></button>
</body>
</html>