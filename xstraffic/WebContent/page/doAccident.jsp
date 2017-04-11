<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

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
<input type="hidden" id="editRoleId" />
<input type="hidden" id="editAccidentId" />
<div id="searchpanel" data-options="collapsible:true,collapsed:true"
	class="easyui-panel panel-margin-buttom" title="查询条件"
	style="height: 110px; padding: 10px; overflow: hidden;">
	<table height="100%">
		<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
			<td>手机号码：</td>
			<td><input type="text" id="reportPhoneNumber"
				style="width: 100px;" /></td>
			<td>微信昵称：</td>
			<td><input type="text" id="nickName" style="width: 120px;" /></td>
			<td style="display:none;">用户类别：</td>
			<td><select id="reporterType" style="width: 120px;display:none;">
					<option value="">请选择</option>
					<option value="民警">民警</option>
					<option value="公众">公众</option>
			</select></td>
		</tr>
		<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
			<td>开始时间：</td>
			<td>
				<input type="text" id="startTime" class="easyui-datebox" editable="false" style="width: 106px;" />
			</td>
			<td>结束时间：</td>
			<td>
				<input type="text" id="endTime" class="easyui-datebox" editable="false" style="width: 106px;" />
			</td>
			<td>
				<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
					<img width="20" height="20" src="image/icon/search.gif">
					<b>查询</b>
				</a>
			</td>
			<td>
				<a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
					<b>条件清空</b>
				</a>
			</td>
		</tr>
	</table>
</div>
<table id="dg" class="easyui-datagrid" title="事故信息列表"
	style="height: 423px;"
	data-options="rownumbers:true,singleSelect:true, pagination:true,
			pageSize:20,remoteSort:false,fitColumns:true,
			multiSort:true">
	<thead>
		<tr>
			<th
				data-options="field:'accidentId',width:120,sortable:true,align:'center'">事故编号</th>
			 
			<th
				data-options="field:'reportTime',width:150,sortable:true,align:'center'">事故时间</th>
			<th
				data-options="field:'reporterType',width:80,sortable:true,align:'center'">用户类别</th>
			 
			<th
				data-options="field:'nickName',width:80,sortable:true,align:'center'">微信昵称</th>

			<th
				data-options="field:'reportPhoneNumber',width:100,align:'center'">上报人手机号</th>
			<th
				data-options="field:'partyPhoneNumber',width:100,align:'center'">另一方手机号</th>
			<th data-options="field:'address',width:260">事发地址</th>
			<th data-options="field:'accidentState',width:80,align:'center'">处理状态</th>
			 

			<th
				data-options="field:'oprat',align:'center',width:60,formatter:operate1">操作
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
	var managerName = '${session.Manager.userId}';
	var managerRole = '${session.ManagerRole.role}';
	String.prototype.trim = function() {
		return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	 
	function operate1(value, row, index) {
		var accidentId=row.accidentId; 
		
		var urlPath = "${pageContext.request.contextPath}/accidentDetail.action";
		urlPath+="?accidentId="+accidentId;
		if(row.accidentState =='审核通过')
			return '<a href=\''+urlPath+'\'>详情</a>';
		else{
			//row.accidentState='待定';
			return '<a href=\''+urlPath+'\'>审核</a>';
		}
	}
	
	function detail(accidentId){ 
		var urlPath="${pageContext.request.contextPath}/accidentDetail.action";
		urlPath+="?accidentId="+accidentId;
		self.location.href=urlPath; 
	} 
	
	/* var toolbar = [ {
		text : '导出数据',
		iconCls : 'icon-add',
		handler : function() {
			var nickName = $("#nickName").val().trim();
			var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
			var reporterType = $("#reporterType").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var canshu = "nickName=" + nickName + "&reportPhoneNumber="
					+ reportPhoneNumber + "&reporterType=" + reporterType
					+ "&startTime=" + startTime + "&endTime=" + endTime;
			var lianjie = "toExportData.action?" + canshu;
			window.location.href = lianjie;
		}
	} ]; */
	
	function getData(startTime, endTime, reportPhoneNumber, nickName,
			reporterType) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getAccident.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&startTime=" + startTime + "&endTime=" + endTime
					+ "&reportPhoneNumber=" + reportPhoneNumber
					+ "&nickName=" + nickName + "&reporterType="
					+ reporterType,
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
				alert("网络繁忙!请稍后再试!");
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
			var nickName = $("#nickName").val().trim();
			var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
			var reporterType = $("#reporterType").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var newData = getData(startTime, endTime, reportPhoneNumber,
					nickName, reporterType);
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
		$("#startTime").datebox('setValue', "<s:property value='startDate'/>");
		$("#endTime").datebox('setValue', "<s:property value='endDate'/>");
		var width = $(document).width();
		var height = $(document).height();
		var nickName = $("#nickName").val().trim();
		var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
		var reporterType = $("#reporterType").val().trim();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid(
				'loadData',
				getData(startTime, endTime, reportPhoneNumber, nickName,
						reporterType));
		$("#searchpanel").panel({
			width : width - 20,

		});
		$("#search").click(
				function() {
					var nickName = $("#nickName").val().trim();
					var reportPhoneNumber = $("#reportPhoneNumber").val()
							.trim();
					var reporterType = $("#reporterType").val().trim();
					var startTime = $("#startTime").datebox('getValue');
					var endTime = $("#endTime").datebox('getValue');
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(startTime, endTime, reportPhoneNumber,
									nickName, reporterType));
				});

		$("#clear").click(function() {
			$("#startTime").datebox('setValue', "<s:property value='startDate'/>");
			$("#endTime").datebox('setValue', "<s:property value='endDate'/>");
			$("#reportPhoneNumber").val("");
			$("#nickName").val("");
			$("#reporterType").val("");
		});
		$("#edit_save").click(
				function() {
					var row = $("#dg").datagrid('getSelected');
					var accidentId = row.accidentId;
					$.ajax({
						url : "saveProcedureMessage1.action",
						data : "accidentId=" + accidentId
								+ "&managerName=" + managerName,
						dataType : "json",
						type : "post",
						success : function(data) {
							if (data.respCode == 0) {
								alert("审核完毕!回复成功!");
								$('#detail_dialog').dialog('close');
								var nickName = $("#nickName").val().trim();
								var reportPhoneNumber = $("#reportPhoneNumber").val()
										.trim();
								var reporterType = $("#reporterType").val().trim();
								var startTime = $("#startTime").datebox('getValue');
								var endTime = $("#endTime").datebox('getValue');
								$('#dg').datagrid({
									loadFilter : pagerFilter,
									pageNumber : 1
								}).datagrid(
										'loadData',
										getData(startTime, endTime, reportPhoneNumber,
												nickName, reporterType));
							} else {
								alert("网络繁忙!请刷新后重新审核!");
							}

						},
						error : function(data) {
							alert("网络繁忙!请稍后再试!");
						}
					});

				});
	});
</script>
</body>
</html>