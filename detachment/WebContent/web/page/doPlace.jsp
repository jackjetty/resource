<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地区信息管理</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<style type="text/css">
<!--
div ul {
	list-style: none;
}

div ul li {
	line-height: 40px;
	height: 40px;
}

#div1 td {
	height: 35px;
}

#div2 td {
	height: 35px;
}

#searchpanel a.pushBtn {
	-moz-user-select: none;
	background: url("image/button/btnout_bg_left.gif") no-repeat scroll
		left top transparent;
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
-->
</style>
</head>
<body class="easyui-layout"
	style="background: #DFE8F6; margin-top: 4px; margin-left: 4px; margin-right: 4px; width: auto;">

	<input type="hidden" id="editId" />
	<div region="north" border="false" split="true">
		<div id="searchpanel" class="easyui-panel" title="地区信息查询"
			style="height: 80px; padding: 10px;" data-options="fit:true">
			<div style="float: left; vertical-align: center; height: 31px; line-height: 31px;">
				地区名称：<input type="text" id="placeName" /> 
			</div>
			<div style="height: 100%; float: left; margin-left: 15px;">
				<a id="search" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <img width="20" height="20"
					src="image/icon/search.gif"> <b>查询</b>
				</a><a id="clear" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <b>条件清空</b>
				</a>
			</div>
		</div>
	</div>
	<div id="mainPanle" region="center" split="true" border="false">
		<table id="dg" class="easyui-datagrid" title="地区信息列表"
			data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true,fit:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<!-- th data-options="field:'placeId',width:80,hide:'true'">数据库ID</th> -->
					<th data-options="field:'placeName',width:100,sortable:true">地区名称</th>
					<th data-options="field:'remark',width:200">备注</th>
					<!-- th data-options="field:'dataStatus',width:200">经纬度数据情况</th> -->
				</tr>
			</thead>
		</table>
	</div>
	<div region="south" split="true" border="false"
		style="background: #DFE8F6;"></div>
	<script type="text/javascript">
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var toolbar = [
				{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$('#dlg').dialog('open');
						$("#add_placeName").val('');
						$("#add_remark").val('');
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
						}else if (rows.length > 1) {
							alert("一次只能编辑一行！");
						} else {
							var row = $("#dg").datagrid('getSelected');
							$("#editId").val(row.placeId);
							$('#edit-dialog').dialog('open');
							$("#edit_placeName").val(row.placeName);
							$("#edit_remark").val(row.remark);
						}
					}

				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var rows = $('#dg').datagrid('getSelections');
						if (window.confirm("确认删除这" + rows.length + "记录？")) {
							var placeIds = '';
							for (var i = 0; i < rows.length; i++) {
								placeIds = placeIds + "," + rows[i].placeId;
							}
							$.ajax({
								url : "removePlace.action",
								data : "placeIds=" + placeIds,
								dataType : "json",
								type : "post",
								success : function(data) {
									alert(data.respInfo);
									var placeName = $("#placeName").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(placeName));
								},
								error : function(data) {
									alert("error 后台出现错误！");
								}
							});
						} else {
							return false;
						}
					}
				}, '-', {
					text : '上传地区数据',
					iconCls : 'icon-add',
					handler : function() {
						$('#upload-dialog').dialog('open');
						$("#uploadFile").val("");
						$("#uploadPlaceId").html("");
						$("#uploadPlaceId").append($("<option value='default'>请选择对应的地区</option>"));
						$.ajax({
							url : "getPlaceMap.action",
							data : "",
							dataType : "json",
							type : "post",
							success : function(data) {
								for(var i=0;i<data.length;i++){
									var option = $("<option value='"+data[i].value+"'>"+data[i].text+"</option>");
									$("#uploadPlaceId").append(option);
								}
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
						
					}
				} ];

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
				var placeName = $("#placeName").val().trim();
				var newData = getData(placeName);
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
		function getData(placeName) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getAllPlace.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&placeName=" + placeName,
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
		$(function() {
			var placeName = $("#placeName").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
			}).datagrid('loadData', getData(placeName));
			$("#search").click(function() {
				var placeName = $("#placeName").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(placeName));
			});
			$("#clear").click(function() {
				$("#placeName").val('');
			});
			$("#addPlace").click(
					function() {
						placeName = $("#add_placeName").val().trim();
						var remark = $("#add_remark").val();
						
						$.ajax({
							url : "addPlace.action",
							data : "placeName=" + placeName + "&remark=" + remark,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert(data.respInfo);
									$('#dlg').dialog('close');
									placeName = $("#placeName").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(placeName));
								} else {
									alert(data.respInfo);
								}

							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#updatePlace").click(
					function() {
						placeName = $("#edit_placeName").val().trim();
						var remark = $("#edit_remark").val();
						var placeId = $("#editId").val();
						$.ajax({
							url : "updatePlace.action",
							data : "placeName=" + placeName + "&remark=" + remark+ "&placeId=" + placeId,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert(data.respInfo);
									$('#edit-dialog').dialog('close');
									placeName = $("#placeName").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(placeName));
								} else {
									alert(data.respInfo);
								}

							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#uploadData").click(
					function() {
						var placeId = $("#uploadPlaceId").val().trim();
						if(placeId =='default'){
							alert("请选择对应的地区");
							return false;
						}
						var uploadFile = $("#uploadFile").val();
						if (uploadFile == '') {
							alert("请选择要上传的xls文件!");
							return false;
						} else if (uploadFile.split(".")[1].toLowerCase() != 'xls') {
							alert("上传文件格式错误,请重新选择文件!");
							$("#uploadFile").val("");
							return false;
						}
						var options = {
								dataType : "json",//数据类型 
								error : function() {
									alert("后台错误！");
								},
								success : function(data) {//调用Action后返回过来的数据 
									if(data.respCode!=0){
										alert(data.respInfo);
										$("#uploadFile").val("");
									}else{
										alert(data.respInfo);
										$('#upload-dialog').dialog('close');
									}
									
								}
							};
							$("#uploadForm").ajaxSubmit(options);
					});
			
		});
	</script>
	<div id="dlg" class="easyui-dialog" title="添加地区信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 175px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left: 20px;">
			<table align="center">
				<tr id="tr1">
					<td>地区名称：</td>
					<td><input type="text" id="add_placeName" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>备注信息：</td>
					<td><input type="text" id="add_remark" /></td>
					<td><font color="red">&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>

		</div>
		<div align="center">
			<input type="button" value="添加" id="addPlace" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="修改地区信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 175px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left: 20px;">
			<table align="center">
				<tr>
					<td>地区名称：</td>
					<td><input type="text" id="edit_placeName" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>备注信息：</td>
					<td><input type="text" id="edit_remark" /></td>
					<td><font color="red">&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updatePlace" />
		</div>
	</div>
	<div id="upload-dialog" class="easyui-dialog" title="上传地区返回数据"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 175px; padding: 10px; background: #DFE8F6;">
		<form action="uploadPlaceArea.action" method="post" enctype="application/x-www-form-urlencoded" id="uploadForm">
		<div id="div2" style="margin-left: 20px;">
			<table align="center">
				<tr>
					<td>选择地区：</td>
					<td><select id="uploadPlaceId" name="placeId" style='width: 155px;'>
						
					</select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>选择xls文件：</td>
					<td><input type="file" name="uploadFile" id="uploadFile" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</div>
		</form>
		<div align="center">
			<input type="button" value="上传数据" id="uploadData" />
		</div>
		
	</div>
</body>
</html>