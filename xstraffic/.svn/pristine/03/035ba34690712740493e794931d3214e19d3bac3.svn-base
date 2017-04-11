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
	var toolbar = [
			{
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					$("#dlg").dialog('open');
					$("#add_title").val('');
					$("#add_rule").val('');
					$("#add_content").val('');
					$("#add_startTime").val('');
					$("#add_endTime").val('');
					$("#add_status").val('');
					$("#add_count").val(0);
					$("#add_type").val(1);
					$("#add_smallImage").val('');
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
						$("#edit_title").val(row.title);
						$("#edit_rule").val(row.rule);
						$("#edit_content").val(row.content);
						$("#edit_id").val(row.id);
						$("#edit_startTime").datetimebox('setValue',new Date(row.startTime).toCommonCase());
						$("#edit_endTime").datetimebox('setValue',new Date(row.endTime).toCommonCase());
						$("#edit_status").val(row.status);
						$("#edit_count").val(row.count);
						$("#edit_type").val(row.type);
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
					} else {
						if (window.confirm("确认删除这" + rows.length + "记录？")) {
							var ids = '';
							for ( var i = 0; i < rows.length; i++) {
								ids = ids + ","
										+ rows[i].id;
							}
							$.ajax({
								url : "deleteVotes.action",
								data : "ids=" + ids,
								dataType : "json",
								type : "post",
								success : function(data) {
									if(data.success){
										alert(data.msg);
										$("#search").click();
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

	function getData(title, status) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getVoteByPage.action",
			async : false,
			data : {
				"title" : title,
				"status" : status,
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
		var title = $("#title").val();
		var status = $("#status").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter
		}).datagrid('loadData', getData(title, status));
	}

	$(function() {
		reloading();
		
		$("#search").click(function() {
			reloading();
		});
		
		$("#clear").click(function() {
			$("#title").val('');
			$("#status").val('');
		});
		
		$("#addVote").click(function() {
			var title = $("#add_title").val();
			if(title == ""){
				alert("请输入标题!");
				return;
			}
			
			var rule = $("#add_rule").val();
			if(rule == ""){
				alert("请输入规则!");
				return;
			}
			
			var startTime = $("#add_startTime").datetimebox('getValue');
			if(startTime == ""){
				alert("请输入开始时间!");
				return;
			}
			
			var endTime = $("#add_endTime").datetimebox('getValue');
			if(endTime == ""){
				alert("请输入结束时间!");
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
						$("#search").click();
					}else{
						alert(data.msg);
					}					
				}
			};
			$("#addForm").ajaxSubmit(options);
			
		});

		$("#updateVote").click(function() {
			var title = $("#edit_title").val();
			if(title == ""){
				alert("请输入标题!");
				return;
			}
			
			var rule = $("#edit_rule").val();
			if(rule == ""){
				alert("请输入规则!");
				return;
			}
			
			var startTime = $("#edit_startTime").datetimebox('getValue');
			if(startTime == ""){
				alert("请输入开始时间!");
				return;
			}
			
			var endTime = $("#edit_endTime").datetimebox('getValue');
			if(endTime == ""){
				alert("请输入结束时间!");
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
						$("#search").click();
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
			var title = $("#title").val();
			var status = $("#status").val();
			var newData = getData(title, status);
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
		return "<img src='viewImages.action?picPath="+row.image+"' style='width:20px;height:20px'/>";
	}
	function operate2(value, row, index) {
		return new Date(value).toCommonCase();
	}
	
	function operate3(value, row, index) {
		return new Date(value).toCommonCase();
	}
	
	function operate4(value, row, index) {
		return new Date(value).toCommonCase();
	}
	
	function operate5(value, row, index) {
		if(value == "0"){
			return "启用";
		}else if(value == "1"){
			return "关闭";
		}
	}
	
	function operate6(value, row, index){
		return "<a href=\"javascript:manager('"+index+"')\">内容管理</a>&nbsp;||&nbsp;<a href=\"javascript:detail('"+index+"')\">投票详情</a>";
	}
	
	function manager(index){
		var rows = $('#dg').datagrid('getRows');
		var row = rows[index];
		var id = row.id;
		self.parent.addTab('内容管理','doElector.action?voteId='+id,'icon-edit');
	}
	
	function detail(index){
		var rows = $('#dg').datagrid('getRows');
		var row = rows[index];
		var id = row.id;
		self.parent.addTab('投票详情','doVoteDetail.action?voteId='+id,'icon-edit');
	}
	
	function operate7(value, row, index){
		if(value == "0"){
			return "人/票";
		}else if(value=="1"){
			return "人/天/票";
		}
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
	<div region="north" border="false" split="true">
		<div id="searchpanel" data-options="fit:true"
			class="easyui-panel panel-margin-buttom" title="投票活动查询"
			style="height: 80px; padding: 10px;">
			<table height="100%">
				<tr
					style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
					<td>标题：</td>
					<td><input type="text" id="title" /></td>
					<td>状态：</td>
					<td><select id="status" >
						<option value=""></option>
						<option value="0">启用</option>
						<option value="1">关闭</option>
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
	</div>
	<div id="mainPanle" region="center" split="true" border="false">
		<table id="dg" class="easyui-datagrid" title="投票活动"
			data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,fit:true,
				multiSort:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th	data-options="field:'image',width:100,formatter:operate1,align:'center'">列表图</th>
					<th data-options="field:'title',width:120,align:'center'">标题</th>
					<th data-options="field:'rule',width:200,sortable:true,align:'center'">规则</th>
					<th data-options="field:'content',width:200,sortable:true,align:'center'">活动内容</th>
					<th data-options="field:'startTime',width:120,align:'center',formatter:operate3">开始时间</th>
					<th data-options="field:'endTime',width:120,align:'center',formatter:operate4">结束时间</th>
					<th data-options="field:'status',width:120,align:'center',formatter:operate5">状态</th>
					<th data-options="field:'type',width:120,align:'center',formatter:operate7">投票类型</th>
					<th data-options="field:'count',width:120,align:'center'">投票总数</th>
					<!--<th data-options="field:'user',width:100,sortable:true,align:'center'">创建人</th>
					--><th data-options="field:'createTime',width:200,sortable:true,align:'center',formatter:operate2">创建时间</th>					
					<th	data-options="field:'enabled',width:170,align:'center',formatter:operate6">操作</th>
				</tr>
			</thead>
		</table>

		<div id="dlg" class="easyui-dialog" title="添加投票信息"
			data-options="iconCls:'icon-save',closed:true" style="padding: 20px;width:425px;">
			<div id="div1" align="center">
				<form action="addVote.action" method="post"
					enctype="multipart/form-data" id="addForm">
					<table align="center" cellspacing="0" width="375px">
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">标题：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="add_title" name="title"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 175px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">规则说明：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="add_rule" name="rule" rows="5"
								style="width: 200px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 175px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">活动 内容：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="add_content" name="content" rows="5"
								style="width: 200px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">开始时间：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="add_startTime" name="startTime" class="easyui-datetimebox" style="width: 200px;" />
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">结束时间：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="add_endTime" name="endTime" class="easyui-datetimebox" style="width: 200px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">状态：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
								<select id="add_status" name="status" style="width: 200px; height: 20px;">
									<option value="0">启用</option>
									<option value="1">关闭</option>
								</select>
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必选</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">状态：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
								<select id="add_type" name="type" style="width: 200px; height: 20px;">
									<option value="0">人/票</option>
									<option value="1">人/天/票</option>
								</select>
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必选</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">投票总数：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="add_count" name="count"
								style="width: 200px; height: 20px;" readonly="readonly" value="0"/></td>
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
				<input type="button" value="添加" id="addVote"
					style="width: 100px; background-color: #4691d1; border: 1px solid #047adf; display: block; text-align: center; color: white; font-size: 14px; line-height: 30px; font-family: '微软雅黑';" />
			</div>
		</div>
		<div id="edit-dialog" class="easyui-dialog" title="编辑活动"
			data-options="iconCls:'icon-save',closed:true" style="padding: 20px;width:425px;">
			<div id="div1" align="center">
				<form action="editVote.action" method="post"
					enctype="multipart/form-data" id="editForm">
					<input id="edit_id" name="id" type="hidden"/>
					<table align="center" cellspacing="0" width="375px">

						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">标题：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="edit_title" name="title"
								style="width: 200px; height: 20px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">规则说明：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="edit_rule" name="rule" rows="5"
								style="width: 200px; height: 20px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">活动内容：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><textarea
								id="edit_content" name="content" rows="5"
								style="width: 200px; height: 20px;" ></textarea></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">开始时间：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="edit_startTime" name="startTime" class="easyui-datetimebox" style="width: 200px;" />
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">结束时间：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
							<input type="text" id="edit_endTime" name="endTime" class="easyui-datetimebox" style="width: 200px;" /></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必填</font></td>
						</tr>
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">状态：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
								<select id="edit_status" name="status" style="width: 200px; height: 20px;">
									<option value="0">启用</option>
									<option value="1">关闭</option>
								</select>
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必选</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">状态：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;">
								<select id="edit_type" name="type" style="width: 200px; height: 20px;">
									<option value="0">人/票</option>
									<option value="1">人/天/票</option>
								</select>
							</td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;必选</font></td>
						</tr>
						
						<tr style="height: 35px;">
							<td
								style="width: 100px; text-align: right; background-color: #EDEDED;">投票总数：</td>
							<td style="width: 200px; text-align: left; border-left: 0px;"><input
								type="text" id="edit_count" name="count"
								style="width: 200px; height: 20px;" readonly="readonly"/></td>
							<td style="width: 70px; text-align: left; border-left: 0px;"><font
								color="red">&nbsp;*&nbsp;不可更改</font></td>
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
				<input type="button" value="更新" id="updateVote"
					style="width: 100px; background-color: #4691d1; border: 1px solid #047adf; display: block; text-align: center; color: white; font-size: 14px; line-height: 30px; font-family: '微软雅黑';" />
			</div>
		</div>
		
	</div>

</body>
</html>