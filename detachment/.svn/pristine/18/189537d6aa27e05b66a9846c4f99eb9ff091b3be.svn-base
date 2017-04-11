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
	<div id="mainPanle" region="center" split="true" border="false">
		<table id="dg" class="easyui-datagrid" title="地区信息列表"
			data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true,fit:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<!-- th data-options="field:'placeId',width:80,hide:'true'">数据库ID</th> -->
					<th data-options="field:'departmentId',width:100,sortable:true">部门编号</th>
					<th data-options="field:'region',width:100,sortable:true">部门地区</th>
					<th data-options="field:'departmentName',width:100,sortable:true">部门名称</th>
					<th data-options="field:'parentName',width:100,sortable:true">上级部门</th>
					<th data-options="field:'departmentPhone',width:100,sortable:true">联系电话</th>
					<th data-options="field:'departmentAddress',width:100,sortable:true">联系地址</th>
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
						
						$("#add_parentId").empty();
						$.ajax({
							url : "web/getDepartmentIdAndName.action",
							data : "",
							dataType : "json",
							type : "post",
							success : function(data) {
								var departmentNames=data.departmentName.split(',');
								var departmentIds=data.departmentId.split(',');
								var opt="<option value=''>请选择上级部门</option>";
								$("#add_parentId").append(opt);
								for(var i=0;i<departmentIds.length;i++){
									var option="<option value='"+departmentIds[i]+"'>"+departmentNames[i]+"</option>";
									$("#add_parentId").append(option);
								}
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
						$("#add_departmentId").val('');
						$("#add_departmentName").val('');
						$("#add_region").val('');
						$("#add_departmentPhone").val('');
						$("#add_departmentAddress").val('');
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
							$('#edit-dialog').dialog('open');
							var row = $("#dg").datagrid('getSelected');
							$("#edit_parentId").empty();
							$.ajax({
								url : "web/getDepartmentIdAndName.action",
								data : "",
								dataType : "json",
								type : "post",
								success : function(data) {
									var departmentNames=data.departmentName.split(',');
									var departmentIds=data.departmentId.split(',');
									if(row.parentId==null || row.parentId==''){
										var opt="<option value=''>请选择上级部门</option>";
										$("#edit_parentId").append(opt);
										for(var i=0;i<departmentIds.length;i++){
											if(departmentIds[i]!=row.departmentId){
												var option="<option value='"+departmentIds[i]+"'>"+departmentNames[i]+"</option>";
												$("#edit_parentId").append(option);
											}
											
										}
									}else{
										var opt="<option value=''>请选择上级部门</option>";
										$("#edit_parentId").append(opt);
										for(var i=0;i<departmentIds.length;i++){
											var option="";
											if(departmentIds[i]!=row.departmentId){
												if(departmentIds[i]==row.parentId){
													option="<option value='"+departmentIds[i]+"' selected='selected'>"+departmentNames[i]+"</option>";
												}else{
													option="<option value='"+departmentIds[i]+"'>"+departmentNames[i]+"</option>";
												}
												$("#edit_parentId").append(option);
											}
											
										}
									}
									
								},
								error : function(data) {
									alert("error 后台出现错误！");
								}
							});
							$("#edit_departmentId").val(row.departmentId);
							$("#edit_departmentName").val(row.departmentName);
							$("#edit_region").val(row.region);
							$("#edit_departmentPhone").val(row.departmentPhone);
							$("#edit_departmentAddress").val(row.departmentAddress);
							$("#edit_remark").val(row.remark);
						}
					}

				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var rows = $('#dg').datagrid('getSelections');
						if (window.confirm("确认删除这" + rows.length + "记录？")) {
							var departmentIds = '';
							for (var i = 0; i < rows.length; i++) {
								departmentIds = departmentIds + "," + rows[i].departmentId;
							}
							$.ajax({
								url : "removeDepartment.action",
								data : "departmentIds=" + departmentIds,
								dataType : "json",
								type : "post",
								success : function(data) {
									if(data.respCode==0){
										alert(data.respInfo);
										$('#dg').datagrid({
											loadFilter : pagerFilter
										}).datagrid('loadData', getData());
									}else{
										alert(data.respInfo);
									}
									
								},
								error : function(data) {
									alert("error 后台出现错误！");
								}
							});
						} else {
							return false;
						}
					}
				}];

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
				var newData = getData();
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
		function getData() {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getAllDepartment.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex,
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
			$('#dg').datagrid({
				loadFilter : pagerFilter,
			}).datagrid('loadData', getData());
			$("#addDepartment").click(
					function() {
						var parentId=$("#add_parentId").val();
						var departmentId=$("#add_departmentId").val().trim();
						var departmentName=$("#add_departmentName").val().trim();
						var region=$("#add_region").val().trim();
						var departmentPhone=$("#add_departmentPhone").val().trim();
						var departmentAddress=$("#add_departmentAddress").val().trim();
						var remark=$("#add_remark").val();
						if(departmentId==''){
							alert("部门编号不能为空");
							return false;
						}
						if(departmentName==''){
							alert("部门名称不能为空");
							return false;
						}
						if(region==''){
							alert("地区不能为空");
							return false;
						}
						$.ajax({
							url : "addDepartment.action",
							data : "parentId=" + parentId + "&departmentId=" + departmentId
								+ "&departmentName=" + departmentName + "&region=" + region
								+ "&departmentPhone=" + departmentPhone + "&departmentAddress=" + departmentAddress
								+ "&remark=" + remark,
							dataType : "json",
							type : "post",
							success : function(data) {
									alert(data.respInfo);
									$('#dlg').dialog('close');
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData());
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#updateDepartment").click(
					function() {
						var parentId=$("#edit_parentId").val();
						var departmentId=$("#edit_departmentId").val().trim();
						var departmentName=$("#edit_departmentName").val().trim();
						var region=$("#edit_region").val().trim();
						var departmentPhone=$("#edit_departmentPhone").val().trim();
						var departmentAddress=$("#edit_departmentAddress").val().trim();
						var remark=$("#edit_remark").val();
						if(departmentName==''){
							alert("部门名称不能为空");
							return false;
						}
						if(region==''){
							alert("地区不能为空");
							return false;
						}
						$.ajax({
							url : "updateDepartment.action",
							data : "parentId=" + parentId + "&departmentId=" + departmentId
								+ "&departmentName=" + departmentName + "&region=" + region
								+ "&departmentPhone=" + departmentPhone + "&departmentAddress=" + departmentAddress
								+ "&remark=" + remark,
							dataType : "json",
							type : "post",
							success : function(data) {
									if(data.respCode==0){
										alert(data.respInfo);
										$('#edit-dialog').dialog('close');
										$('#dg').datagrid({
											loadFilter : pagerFilter
										}).datagrid('loadData', getData());
									}else{
										alert(data.respInfo);
									}
									
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
		});
	</script>
	<div id="dlg" class="easyui-dialog" title="添加部门信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 375px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left: 20px;">
			<table align="center">
				<tr id="tr1">
					<td>部门编号：</td>
					<td><input type="text" id="add_departmentId" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门名称：</td>
					<td><input type="text" id="add_departmentName" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门地区：</td>
					<td><input type="text" id="add_region" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr id="tr1">
					<td>上级部门：</td>
					<td><select id="add_parentId" style="width:155px;"></select></td>
					<td><font >&nbsp; &nbsp;选填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门电话：</td>
					<td><input type="text" id="add_departmentPhone" /></td>
					<td><font color="red">&nbsp; &nbsp;选填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门地址：</td>
					<td><input type="text" id="add_departmentAddress" /></td>
					<td><font color="red">&nbsp; &nbsp;选填</font></td>
				</tr>
				<tr>
					<td>备注信息：</td>
					<td><input type="text" id="add_remark" /></td>
					<td><font >&nbsp; &nbsp;选填</font></td>
				</tr>
			</table>

		</div>
		<div align="center">
			<input type="button" value="添加" id="addDepartment" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="修改地区信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 375px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left: 20px;">
		<input type="hidden" id="edit_departmentId" />
			<table align="center">
				
				<tr id="tr1">
					<td>部门名称：</td>
					<td><input type="text" id="edit_departmentName" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门地区：</td>
					<td><input type="text" id="edit_region" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr id="tr1">
					<td>上级部门：</td>
					<td><select id="edit_parentId" style="width:155px;"></select></td>
					<td><font >&nbsp; &nbsp;选填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门电话：</td>
					<td><input type="text" id="edit_departmentPhone" /></td>
					<td><font color="red">&nbsp; &nbsp;选填</font></td>
				</tr>
				<tr id="tr1">
					<td>部门地址：</td>
					<td><input type="text" id="edit_departmentAddress" /></td>
					<td><font color="red">&nbsp; &nbsp;选填</font></td>
				</tr>
				<tr>
					<td>备注信息：</td>
					<td><input type="text" id="edit_remark" /></td>
					<td><font >&nbsp; &nbsp;选填</font></td>
				</tr>
			</table>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateDepartment" />
		</div>
	</div>
	
</body>
</html>