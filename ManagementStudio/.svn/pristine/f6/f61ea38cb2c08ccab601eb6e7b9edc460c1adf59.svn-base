<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
		String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			var name = $("#name").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 18,
				height : height - 125
			}).datagrid('loadData', getData(name));
			$("#searchpanel").panel({
				width : width - 18
			});
			$("#clear").click(function(){
				$("#name").val('');
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
									var children = $('#tree').tree(
											'getChildren',
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
						var userId = $("#userId").val();
						$.ajax({
							url : "developManagerRole.action",
							data : "roleIds=" + roleIds + "&managerId="
									+ userId,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								$('#selectRole').dialog('close');
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});

			$("#cancel").click(function() {
				$('#selectRole').dialog('close');
			});
			$("#addManager").click(function() {
				var username = $("#add_name").val().trim();
				if (username == '') {
					alert("请输入用户名！");
					return false;
				}
				var password = $("#add_password").val();
				if (password == '') {
					alert("请输入密码！");
					return false;
				}else if(password.length<password.trim().length){
					alert("密码开头和末尾不能有空格,请重新输入!");
					$("#add_password").val("");
					return false;
				}else if(password.indexOf(" ")!=-1){
					$("#add_password").val("");
					alert("密码中间不能有空格！");
					return false;
				}
				var phoneNumber = $("#add_phoneNumber").val().trim();
				if(phoneNumber==''){
					alert("请输入手机号码！");
					return false;
				}
				var email = $("#add_email").val().trim();
				if(email==''){
					alert("请输入电子邮箱地址！");
					return false;
				}
				$.ajax({
					url : "addUser.action",
					data : "username=" + username + "&password=" + password + "&phoneNumber="+phoneNumber+"&email="+email,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dlg').dialog('close');
						name = $("#name").val().trim();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width : width - 18,
							height : height - 120
						}).datagrid('loadData', getData(name));
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			$("#updateUser").click(function() {
				var managerId = $("#userId").val();
				var name = $("#edit_name").val();
				if (name == '') {
					alert("用户名不能为空！");
					return false;
				} 
				var phoneNumber = $("#edit_phoneNumber").val().trim();
				if(phoneNumber==''){
					alert("请输入手机号码！");
					return false;
				}
				var email = $("#edit_email").val().trim();
				if(email==''){
					alert("请输入电子邮箱地址！");
					return false;
				}
					$.ajax({
						url : "updateUser.action",
						method : "post",
						data : "username=" + name + "&managerId=" + managerId + "&phoneNumber="+phoneNumber+"&email="+email,
						dataType : "json",
						success : function(data) {
							alert(data.respInfo);
							$('#edit-dialog').dialog('close');
							name = $("#name").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								width : width - 18,
								height : height - 120
							}).datagrid('loadData', getData(name));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				
			});

			$("#search").click(function() {
				name = $("#name").val().trim();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(name));
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
					var id = row.managerId;
					$("#userId").val(id);
					var id = row.managerId;
					$('#selectRole').dialog('open');
					var url = "getRole.action?managerId=" + id;
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
				$("#add_name").val("");
				$("#add_password").val("");
				$("#add_phoneNumber").val("");
				$("#add_email").val("");
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
					$("#userId").val(row.managerId);
					$('#edit-dialog').dialog('open');
					$("#edit_name").val(row.name);
					$("#edit_phoneNumber").val(row.phoneNumber);
					$("#edit_email").val(row.email);
				}
			}

		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length > 0) {
					if (window.confirm("确认删除这" + rows.length + "记录？")) {
						var managerIds = '';
						for ( var i = 0; i < rows.length; i++) {
							managerIds = managerIds + "," + rows[i].managerId;
						}
						$.ajax({
							url : "deleteUser.action",
							data : "managerIds=" + managerIds,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								var name = $("#name").val();
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(name));
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
		function getData(name) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getUser.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&username=" + name,
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
				var name = $("#name").val();
				var newData = getData(name);
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
	</script>
<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
	-moz-user-select: none;
	background: url("images/button/btnout_bg_left.gif") no-repeat scroll
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
	background: url("images/button/btnout_bg_right.gif") no-repeat scroll
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
	background: url("images/button/btnover_bg_left.gif") no-repeat scroll
		left top transparent;
	text-decoration: none;
}

#searchpanel a.pushBtn:hover b {
	background: url("images/button/btnover_bg_right.gif") no-repeat scroll
		right top transparent;
	color: #114477;
	font-size: 12px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="userId" />
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="用户查询" style="height: 80px; padding: 10px;">
		<div
			style="float: left; vertical-align: center; height: 31px; line-height: 31px;">
			用户名：<input type="text" id="name" />
		</div>
		<div style="height: 100%; float: left; margin-left: 15px;">
			<a id="search" class="pushBtn" href="javascript:void(0);"
				hidefocus="true"> <img width="20" height="20"
				src="images/icon/search.gif"> <b>查询</b>
			</a>
			<a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
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
				<!-- th data-options="field:'managerId',width:200">用户ID</th> -->
				<th data-options="field:'name',width:200,sortable:true">用户名</th>
				<th data-options="field:'phoneNumber',width:200,sortable:true">手机号码</th>
				<th data-options="field:'email',width:200,sortable:true">电子邮箱</th>
			</tr>
		</thead>
	</table>
	
	<div id="dlg" class="easyui-dialog" title="添加用户"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 300px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">用&nbsp;户&nbsp;名：<input
					type="text" id="add_name" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">初始密码：<input
					type="password" id="add_password" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">手机号码：<input
					type="text" id="add_phoneNumber" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">电子邮箱：<input
					type="text" id="add_email" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
			</ul>
		</div>
		<div align="center">
			<input type="button" value="添加" id="addManager" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="编辑用户角色"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 250px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">用户名称：<input
					type="text" id="edit_name" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">手机号码：<input
					type="text" id="edit_phoneNumber" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">电子邮箱：<input
					type="text" id="edit_email" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
			</ul>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateUser" />
		</div>
	</div>
	<div id="selectRole" class="easyui-dialog" title="用户角色"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 480px; padding: 10px; background: #DFE8F6;">
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
</body>
</html>