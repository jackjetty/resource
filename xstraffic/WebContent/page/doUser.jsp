<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>添加功能菜单</title>
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
<script type="text/javascript">
	String.prototype.trim = function() {
		return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	$(function() {
		var width = $(document).width();
		var height = $(document).height();
		var userId = $("#userId").val().trim();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid('loadData', getData(userId));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#clear").click(function() {
			$("#userId").val('');
		});
		$("#develop").click(
				function() {
					var checkednodes = $('#tree').tree('getChecked');
					var roleIds = "";
					var array = new Array();
					if (checkednodes.length > 0) {
						for ( var i = 0; i < checkednodes.length; i++) {
							if ($('#tree').tree('getParent',
									checkednodes[i].target) == null) { //是根节点
								var children = $('#tree').tree('getChildren',
										checkednodes[i].target);
								for ( var j = 0; j < children.length; j++) {
									array.push(children[j].id);
								}
								break;
							} else {
								array.push(checkednodes[i].id);
							}
						}
					} else {
						if (confirm("确定将该用户修改为不具备任何角色？")) {
							array = [];
						} else {
							return false;
						}
					}
					roleIds = deleteTheSame(array);
					var userId = $("#userId1").val();
					if (checkednodes.length == 1) {
						$.ajax({
							url : "developManagerRole.action",
							data : "roleIds=" + roleIds + "&userId=" + userId,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								$("#fresh").click();
								$('#selectRole').dialog('close');
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					} else {
						alert("一个用户只能具备一个角色,请重新修改");
						return false;
					}

				});

		$("#cancel").click(function() {
			$('#selectRole').dialog('close');
		});
		$("#addUser")
				.click(
						function() {
							var allUserId = getUserIds();
							var userId = $("#add_userId").val().trim();
							if (userId == '') {
								alert("请输入用户账号！");
								return false;
							}
							for ( var i = 0; i < allUserId.length; i++) {
								if (allUserId[i] == userId) {
									alert("用户账号已存在,请重新输入");
									return false;
								}
							}
							var userPassword = $("#add_userPassword").val();
							if (userPassword == '') {
								alert("请输入密码！");
								return false;
							} else if (userPassword.length < userPassword
									.trim().length) {
								alert("密码开头和末尾不能有空格,请重新输入!");
								$("#add_userPassword").val("");
								return false;
							} else if (userPassword.indexOf(" ") != -1) {
								$("#add_userPassword").val("");
								alert("密码中间不能有空格！");
								return false;
							}
							var userName = $("#add_userName").val().trim();
							if (userName == '') {
								alert("请输入用户名称！");
								return false;
							}
							/* var status = $("#add_status").val().trim();
							if (status == '') {
								alert("状态不能为空！");
								return false;
							} */
							var nickName = $("#add_nickName").val().trim();
							var phoneNumber = $("#add_phoneNumber").val().trim();
							var remark = $("#add_remark").val().trim();
							$.ajax({
								url : "addUser.action",
								data : "userId=" + userId + "&userPassword="
										+ userPassword + "&nickName="
										+ nickName + "&remark=" + remark+"&userName="+userName
										+"&phoneNumber="+phoneNumber,
								dataType : "json",
								type : "post",
								success : function(data) {
									alert(data.respInfo);
									$('#dlg').dialog('close');
									var userId = $("#userId").val().trim();
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										width : width - 20,
										height : height - 45
									}).datagrid('loadData', getData(userId));
								},
								error : function(data) {
									alert("error 后台出现错误！");
								}
							});
						});
		function getUserIds() {
			var rows = null;
			$.ajax({
				url : "getAllUserId.action",
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
		$("#updateUser").click(
				function() {
					var row = $("#dg").datagrid('getSelected');
					var allUserId = getUserIds();
					var oldUserId = row.userId;
					var userId = $("#edit_userId").val().trim();
					if (userId == '') {
						alert("请输入用户账号！");
						return false;
					}
					for ( var i = 0; i < allUserId.length; i++) {
						if (oldUserId != userId && allUserId[i] == userId) {
							alert("用户账号已存在,请重新输入");
							return false;
						}
					}
					var userName = $("#edit_userName").val().trim();
					if (userName == '') {
						alert("请输入用户名称！");
						return false;
					}
					var nickName = $("#edit_nickName").val().trim();
					var phoneNumber = $("#edit_phoneNumber").val().trim();
					var remark = $("#edit_remark").val().trim();
					$.ajax({
						url : "updateUser.action",
						type : "post",
						data : "userId=" + userId + "&nickName=" + nickName + "&oldUserId="
								+ oldUserId + "&remark=" + remark+"&userName="+userName
								+"&phoneNumber="+phoneNumber,
						dataType : "json",
						success : function(data) {
							alert(data.respInfo);
							$('#edit-dialog').dialog('close');
							var userId = $("#userId").val().trim();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								width : width - 20,
								height : height - 45
							}).datagrid('loadData', getData(userId));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});

				});

		$("#search").click(function() {
			var userId = $("#userId").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1
			}).datagrid('loadData', getData(userId));
		});
		$("#fresh").click(function() {
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid('loadData', getData(userId));
		});
	});
	function deleteTheSame(array) {
		var newarray = new Array();
		for ( var i = 0; i < array.length; i++) {
			var flag = true;
			for ( var j = 0; j < newarray.length; j++) {
				if (array[i] != newarray[j]) {
					continue;
				} else {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				newarray.push(array[i]);
			} else {
				continue;
			}
		}
		var ids = "";
		for ( var x = 0; x < newarray.length; x++) {
			ids = ids + "," + newarray[x];
		}
		return ids;
	}
	var toolbar = [ {
		text : '修改用户角色',
		iconCls : 'icon-add',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择需要修改角色的用户！");
				return false;
			} else if (rows.length > 1) {
				alert("一次只能操作一个用户！");
			} else {
				var row = $("#dg").datagrid('getSelected');
				var id = row.userId;
				$("#userId1").val(id);
				$('#selectRole').dialog('open');
				var url = "getAllRole.action?roleId=" + id;
				$('#tree').tree({
					url : url
				});
			}

		}
	}, '-', {
		text : '添加用户',
		iconCls : 'icon-add',
		handler : function() {
			$('#dlg').dialog('open');
			$("#add_userId").val("");
			$("#add_userPassword").val("");
			/* $("#add_userName").val(""); */
			$("#add_nickName").val("");
			$("#add_remark").val("");
			$("#rePassword").val("");
			$("#add_userName").val("");
			$("#add_phoneNumber").val("");

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
				$("#edit_userId").val(row.userId1);
				$("#edit_nickName").val(row.nickName);
				$("#edit_remark").val(row.remark);
				$("#edit_userName").val(row.userName);
				$("#edit_phoneNumber").val(row.phoneNumber);
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length > 0) {
				if (window.confirm("确认删除这" + rows.length + "记录？")) {
					var userIds = '';
					for ( var i = 0; i < rows.length; i++) {
						userIds = userIds + "," + rows[i].userId;
					}
					$.ajax({
						url : "deleteUser.action",
						data : "userIds=" + userIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var userId = $("#userId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
							}).datagrid('loadData', getData(userId));
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
	}, '-', {
		text : '初始化密码',
		iconCls : 'icon-edit',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length > 0) {
				if (window.confirm("确认初始化这" + rows.length + "位用户？")) {
					var userIds = '';
					for ( var i = 0; i < rows.length; i++) {
						userIds = userIds + "," + rows[i].userId;
					}
					$.ajax({
						url : "getFirst.action",
						data : "userIds=" + userIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var userId = $("#userId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
							}).datagrid('loadData', getData(userId));
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
	function getData(userId) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getUser.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&userId=" + userId,
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
			var userId = $("#userId").val();
			var newData = getData(userId);
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
	function vidatepassword2() {
		var oldpwd = $("#add_userPassword").val();
		var newpwd = $("#rePassword").val();
		if (oldpwd != newpwd) {
			$.messager.alert('系统提示', '两次密码不匹配,请重新输入！', 'warning');
			$("#rePassword").val('');
		}
	}
	function vidatepassword1() {
		var oldpwd = $("#add_userPassword").val();
		if (oldpwd.length < 6) {
			$.messager.alert('系统提示', '请输入长度为6-16以内的密码！', 'warning');
			$("#add_userPassword").val('');
		}
		if (oldpwd.length > 16) {
			$.messager.alert('系统提示', '请输入长度为6-16以内的密码！', 'warning');
			$("#add_userPassword").val('');
		}
	}
	function operate(value, row, index) {
		if (row.status == '正常')
			return '<a href=\'javascript:exchange();\'><font color=\'red\'>冻结</font></a>';
		else if (row.status == '冻结')
			return '<a href=\'javascript:exchange();\'>解冻</a>';
	}
	function exchange() {
		var row = $("#dg").datagrid('getSelected');
		$.ajax({
			url : "exchangeStatus.action",
			async : false,
			data : "userId=" + row.userId,
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
	function exchange1() {
		var row = $("#dg").datagrid('getSelected');
		$.ajax({
			url : "exchangeStatus1.action",
			async : false,
			data : "userId=" + row.userId,
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
	function operate1(value, row, index) {
		if (row.simplifyProcess == '1')
			return '<a href=\'javascript:exchange1();\'><img src=\"image/duihao.png\" style=\"height:20px;border:0px;\"/></a>';
		else if (row.simplifyProcess == '0')
			return '<a href=\'javascript:exchange1();\'><img src=\"image/delete.png\" style=\"height:20px;border:0px;\"/></a>';
	}
</script>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="userId1" />
	<div id="searchpanel" data-options="collapsible:true,collapsed:true" class="easyui-panel panel-margin-buttom"
		title="查询条件" style="height: 80px; padding: 10px;">
		<div
			style="float: left; vertical-align: center; height: 31px; line-height: 31px;">
			用户账号：<input type="text" id="userId" />
		</div>
		<div style="height: 100%; float: left; margin-left: 15px;">
			<a id="search" class="pushBtn" href="javascript:void(0);"
				hidefocus="true"> <img width="20" height="20"
				src="image/icon/search.gif"> <b>查询</b>
			</a> <a id="clear" class="pushBtn" href="javascript:void(0);"
				hidefocus="true"> <b>条件清空</b>
			</a>
		</div>

	</div>
	<table id="dg" class="easyui-datagrid" title="用户信息列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userId',width:200,hidden:true">伪用户名</th>
				<th data-options="field:'userId1',width:200,align:'center'">用户账号</th>
				<th data-options="field:'userName',width:300,align:'center'">用户名称</th>
				<th data-options="field:'joinTime',width:200,align:'center'">创建时间</th>
				<!--<th data-options="field:'nickName',width:150,align:'center'">微信昵称</th>  -->
				<th data-options="field:'roleId',width:200,align:'center'">角色名称</th>
				<th data-options="field:'phoneNumber',width:200,align:'center'">手机号码</th>
				<!-- <th
					data-options="field:'simplifyProcess',formatter:operate1,width:200,align:'center'">快速处理</th> -->
				<th data-options="field:'remark',width:150,align:'center'">备注信息</th>
				<th data-options="field:'status',width:200,align:'center'">用户状态</th>
				<!-- <th
					data-options="field:'opt', width:150,formatter:operate,align:'center'">操作</th> -->
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" title="添加用户"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 400px; padding: 10px; ">
		<div>
		<table id="table" cellspacing="0" style="width: 370px;">
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">用户账号：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
						<input type="text" id="add_userId" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
						<font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">初始密码：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="password" id="add_userPassword" onBlur="vidatepassword1()" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font>
					</td>
				</tr>
				
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">确认密码：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input id="rePassword" name="rePassword" onBlur="vidatepassword2()" type="password" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font>
					</td>
				</tr>
				
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">用户名称：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="add_userName" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">微信昵称：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="add_nickName" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font >&nbsp;&nbsp;*&nbsp;&nbsp;选填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">手机号码：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="add_phoneNumber" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font >&nbsp;&nbsp;*&nbsp;&nbsp;选填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">备注信息：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="add_remark" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font >&nbsp;&nbsp;*&nbsp;&nbsp;选填</font>
					</td>
				</tr>
		</table>
			
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addUser" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="编辑用户角色"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 350px; padding: 10px; ">
		<div>
			<table id="table" cellspacing="0" style="width: 370px;">
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">用户账号：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
						<input type="text" id="edit_userId" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
						<font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font>
					</td>
				</tr>
				
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">用户名称：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="edit_userName" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">微信昵称：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="edit_nickName" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font >&nbsp;&nbsp;*&nbsp;&nbsp;选填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">手机号码：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="edit_phoneNumber" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font >&nbsp;&nbsp;*&nbsp;&nbsp;选填</font>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td style="width: 100px; text-align: right; border: 1px solid #CCCCCC;border-top: 0px; background-color: #EDEDED;">备注信息：</td>
					<td style="width: 200px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<input type="text" id="edit_remark" style="width:200px;height:20px;"/>	
					</td>
					<td style="width: 70px; text-align: left; border: 1px solid #CCCCCC;border-top: 0px; border-left: 0px;">
						<font >&nbsp;&nbsp;*&nbsp;&nbsp;选填</font>
					</td>
				</tr>
		</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="更新" id="updateUser" />
		</div>
	</div>
	<div id="selectRole" class="easyui-dialog" title="用户角色"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 500px; padding: 10px; background: #DFE8F6;">
		<div id="role" class="easyui-panel" title="用户角色树"
			style="height: 380px; width: 285px; padding: 8px;">
			<ul id="tree" class="easyui-tree"
				data-options="animate:true,checkbox:true,lines:true">
			</ul>
		</div>
		<div align="center" style="heigth: 50px; line-height: 50px;">
			<input type="button" value="确定" id="develop" /> &nbsp;&nbsp;&nbsp;<input
				type="button" value="取消" id="cancel" />
		</div>
	</div>
	<button id="fresh" style="display: none;"></button>
</body>
</html>