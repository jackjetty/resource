<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/baseUtil.js"></script>
<script type="text/javascript">
	String.prototype.trim = function() {
		return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	var voteId = "${voteId}";
	var toolbar = [
			{
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					$("#add_voteId").val(voteId);
					$("#dlg").dialog('open');
					$("#add_number").val('');
					$("#add_name").val('');
					$("#add_introduce").val('');
					$("#add_votes").val(0);
					$("#add_percent").val(0);
					$("#add_smallImage").val('');
					$("#add_workUnit").val('');
					$("#add_achievement").val('');
					$("#add_area").val('');
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
						return false;
					} else {
						var row = $("#dg").datagrid('getSelected');
						$("#edit_smallImage").val('');
						$("#edit-dialog").dialog('open');
						$("#edit_number").val(row.number);
						$("#edit_name").val(row.name);
						$("#edit_id").val(row.id);
						$("#edit_introduce").val(row.introduce);
						$("#edit_votes").val(row.votes);
						$("#edit_percent").val(row.percent);
						$("#edit_workUnit").val(row.workUnit);
						$("#edit_achievement").val(row.achievement);
						$("#edit_area").val(row.area);
					}
				}
			},
			'-',
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var rows = $('#dg').datagrid('getSelections');
					if (rows.length == 0) {
						alert("请选择需要删除的数据！");
						return false;
					} else if (rows.length > 1) {
						alert("一次只能删除一行！");
						return false;
					} else {
						if (window.confirm("确认删除这条记录？")) {
							var row = $("#dg").datagrid('getSelected');
							var id = row.id;
							$.ajax({
								url : "deleteElector.action",
								data : "id=" + id,
								dataType : "json",
								type : "post",
								success : function(data) {
									if(data.success){
										alert(data.msg);
										window.location.reload();
									}else{
										alert(data.msg);
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
				}
			}];

	function getData(voteId) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getElectorByPage.action",
			async : false,
			data : {
				"voteId" : voteId,
				"pageIndex" : pageIndex,
				"pageSize" : pageSize
			},
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
	
	function reloading(){
		$('#dg').datagrid({
			loadFilter : pagerFilter
		}).datagrid('loadData', getData(voteId));
	}

	$(function() {
		reloading();
		
		$("#addElector").click(function() {
			var number = $("#add_number").val();
			if(number == ""){
				alert("请输入编号!");
				return;
			}
			
			var name = $("#add_name").val();
			if(name == ""){
				alert("请输入名称!");
				return;
			}
			var workUnit = $("#add_workUnit").val();
			if(workUnit == ""){
				alert("请输入单位!");
				return;
			}
			
			var area = $("#add_area").val();
			if(area == ""){
				alert("请输入户籍所属中队!");
				return;
			}
			
			var introduce = $("#add_introduce").val();
			if(introduce == ""){
				alert("请输入介绍!");
				return;
			}
			
			var achievement = $("#add_achievement").val();
			if(achievement == ""){
				alert("请输入参评事迹!");
				return;
			}
							
			var smallImage = $("#add_smallImage").val();
			if (smallImage == '') {
				alert('请选择列表图!');
				return false;
			}
					
			var options = {
				dataType : "json",
				error : function() {
					alert("后台错误！");
				},
				success : function(data) {
					if(data.success){
						alert(data.msg);
						$("#dlg").dialog('close');
						reloading();
					}else{
						alert(data.msg);
					}					
				}
			};
			$("#addForm").ajaxSubmit(options);
			
		});

		$("#updateElector").click(function() {
			var number = $("#edit_number").val();
			if(number == ""){
				alert("请输入编号!");
				return;
			}
			
			var name = $("#edit_name").val();
			if(name == ""){
				alert("请输入名称!");
				return;
			}
			
			var workUnit = $("#edit_workUnit").val();
			if(workUnit == ""){
				alert("请输入工作单位!");
				return;
			}
			
			var area = $("#edit_area").val();
			if(area == ""){
				alert("请输入户籍所属中队!");
				return;
			}
			
			var introduce = $("#edit_introduce").val();
			if(introduce == ""){
				alert("请输入介绍!");
				return;
			}
			
			var achievement = $("#edit_achievement").val();
			if(achievement == ""){
				alert("请输入参评事迹!");
				return;
			}

			var options = {
				dataType : "json",
				error : function() {
					alert("后台错误！");
				},
				success : function(data) {
					if(data.success){
						alert(data.msg);
						$("#edit-dialog").dialog('close');
						reloading();
					}else{
						alert(data.msg);
					}	
				}
			};
			$("#editForm").ajaxSubmit(options);
		});
	});
		
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
			var newData = getData(voteId);
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

	function operate1(value, row, index) {
		return "<img src='viewImages.action?picPath="+row.img+"' style='width:20px;height:20px'/>";
	}
	
</script>
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

#dlg td {
	border: 1px solid #CCCCCC;
	border-bottom: 0px;
}

#edit-dialog td {
	border: 1px solid #CCCCCC;
	border-bottom: 0px;
}


#edit-dialog2 td {
	border: 1px solid #CCCCCC;
	border-bottom: 0px;
}
-->
</style>
</head>
<body class="easyui-layout"
	style="background: #DFE8F6; margin-top: 4px; margin-left: 4px; margin-right: 4px; width: auto;">
	
	<div id="mainPanle" region="center" split="true" border="false">
		<table id="dg" class="easyui-datagrid" title="被投票者管理"
			data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,fit:true,
				multiSort:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th	data-options="field:'img',width:100,formatter:operate1,align:'center'">头像</th>
					<th data-options="field:'number',width:120,sortable:true,align:'center'">编号</th>
					<th data-options="field:'name',width:200,sortable:true,align:'center'">名称</th>
					<th data-options="field:'workUnit',width:200,sortable:true,align:'center'">单位</th>
					<th data-options="field:'area',width:200,sortable:true,align:'center'">户籍所属中队</th>
					<th data-options="field:'introduce',width:120,align:'center'">介绍</th>
					<th data-options="field:'achievement',width:120,align:'center'">参评事迹</th>
					<th data-options="field:'votes',width:120,sortable:true,align:'center'">选票数</th>
					<th data-options="field:'percent',width:120,align:'center'">百分比</th>
				</tr>
			</thead>
		</table>

		<div id="dlg" class="easyui-dialog" title="添加被投票信息"
			data-options="iconCls:'icon-save',closed:true" style="padding: 20px;width:400px;">
			<div id="div1" align="center">
				<form action="addElector.action" method="post"
					enctype="multipart/form-data" id="addForm">
					<input id="add_voteId" name="voteId" type="hidden"/>
					<table align="center" cellspacing="0" width="375px">
				
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">编号：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="add_number" name="number"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">名称：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="add_name" name="name"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">单位：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="add_workUnit" name="workUnit"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">户籍所属中队：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="add_area" name="area"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 100px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">介绍：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="add_introduce" name="introduce" rows="3"
								style="width: 200px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 100px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">参评事迹：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="add_achievement" name="achievement" rows="3"
								style="width: 200px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">选票数量：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="add_votes" name="votes" style="width: 200px;" />
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">百分比：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="add_percent" name="percent"  style="width: 200px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">列表图：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="file" id="add_smallImage" name="imgFile"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
											
					</table>
				</form>
			</div>
			<div align="center" style="margin-top: 20px;">
				<input type="button" value="添加" id="addElector"
					style="width: 100px; background-color: #4691d1; border: 1px solid #047adf; display: block; text-align: center; color: white; font-size: 14px; line-height: 30px; font-family: '微软雅黑';" />
			</div>
		</div>
		<div id="edit-dialog" class="easyui-dialog" title="编辑被投票信息"
			data-options="iconCls:'icon-save',closed:true" style="padding: 20px;width:400px;">
			<div id="div1" align="center">
				<form action="editElector.action" method="post"
					enctype="multipart/form-data" id="editForm">
					<input id="edit_id" name="id" type="hidden"/>
					<table align="center" cellspacing="0" width="375px">

						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">编号：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="edit_number" name="number"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">名称：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="edit_name" name="name"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">单位：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="edit_workUnit" name="workUnit"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">户籍所属中队：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="edit_area" name="area"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">介绍：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="edit_introduce" name="introduce" rows="5"
								style="width: 200px; height: 20px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">参评事迹：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="edit_achievement" name="achievement" rows="5"
								style="width: 200px; height: 20px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">选票数量：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="edit_votes" name="votes" style="width: 200px;" readonly="readonly"/>
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;不可修改</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">百分比：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="edit_percent" name="percent" style="width: 200px;" readonly="readonly"/></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;不可修改</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">列表图：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="file" id="edit_smallImage" name="imgFile"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;选填</font></td>
						</tr>
					
					</table>
				</form>
			</div>
			<div align="center" style="margin-top: 20px;">
				<input type="button" value="更新" id="updateElector"
					style="width: 100px; background-color: #4691d1; border: 1px solid #047adf; display: block; text-align: center; color: white; font-size: 14px; line-height: 30px; font-family: '微软雅黑';" />
			</div>
		</div>
		
	</div>

</body>
</html>