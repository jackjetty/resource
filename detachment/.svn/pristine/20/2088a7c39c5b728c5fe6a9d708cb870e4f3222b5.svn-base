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
    background: url("image/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
    float: left;
    height: 24px;
    margin: 2px 5px 0 0;
    outline: medium none;
    text-decoration: none;
    
}
#searchpanel a.pushBtn img {
    float: left;
    margin: 2px 0 4px 6px;
    border:0;
}
#searchpanel a.pushBtn b {
    background: url("image/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
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
    background: url("image/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("image/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}

-->
</style>
</head>
<body style="background: #DFE8F6;">
<input type="hidden" id="editRoleId" />
	<div id="searchpanel" data-options="collapsible:true,collapsed:true" class="easyui-panel panel-margin-buttom" title="查询条件"
		style="height: 80px; padding: 10px;">
		<div style="float:left;vertical-align: center;height:31px;line-height:31px;">
		角色名称：<input type="text" id="roleName" /> </div>
			<div style="height:100%;float:left;margin-left: 15px;">
			<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="image/icon/search.gif">
				<b>查询</b>
			</a><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></div>
		
	</div>
	<table id="dg" class="easyui-datagrid" title="用户角色列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'roleId',width:300,sortable:true,align:'center'">角色编号</th>
				<!--  <th data-options="field:'status',width:200,sortable:true">角色状态</th>-->
				<th data-options="field:'roleName',width:200">角色名称</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var toolbar = [ {
			text : '修改用户角色权限',
			iconCls : 'icon-add',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length == 0) {
					alert("请选择需要赋权的角色！");
					return false;
				} else if (rows.length > 1) {
					alert("一次只能操作一种角色！");
				} else {
					$('#menutree').dialog('open');
					var row = $("#dg").datagrid('getSelected');
					var roleId = row.roleId;
					$("#editRoleId").val(roleId);
					var url = "web/getAllMenu.action?roleId=" + roleId;
					$('#tree').tree({
						url : url
					});
				}

			}
		}, '-', {
			text : '添加角色',
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
				if (rows.length == 0) {
					alert("请选择要删除的数据！");
					return false;
				} else if (rows.length > 1) {
					alert("一次只能删除一个角色！");
				} else {
					if (window.confirm("确认删除这" + rows.length + "记录？")) {
						var roleIds = '';
						for ( var i = 0; i < rows.length; i++) {
							roleIds = roleIds + "," + rows[i].roleId;
						}
						$.ajax({
							url : "web/removeRole.action",
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
				
			}
		} ];

		function getData(roleName) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "web/getRole.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&roleName=" + roleName,
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
			if(typeof data.originalRows== 'undefined'){
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
			}else{
				var roleName = $("#roleName").val().trim();
				var newData = getData(roleName);
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
			var width = $(document).width();
			var height = $(document).height();
			var roleName = $("#roleName").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid('loadData', getData(roleName));
			
			$("#searchpanel").panel({width:width-20});
			$("#search").click(function() {
				roleName = $("#roleName").val().trim();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(roleName));
			});
			$("#clear").click(function(){
				$("#roleName").val('');
			});
			$("#addRole").click(
					function() {
						roleId = $("#add_roleId").val().trim();
						if (roleId == '') {
							alert('请输入角色编号！');
							return false;
						}
						roleName = $("#add_roleName").val().trim();
						if (roleName == '') {
							alert('请输入角色名称！');
							return false;
						}
						$.ajax({
							url : "web/addRole.action",
							data : "roleId=" + roleId + "&roleName=" + roleName,
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#dlg').dialog('close');
									roleName = $("#roleName").val().trim();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(roleName));
								}else{
									alert(data.respInfo);
								}
								
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#updateRole").click(
					function() {
						roleId = $("#edit_roleId").val().trim();
						if (roleId == '') {
							alert('请输入角色编号！');
							return false;
						}
						roleName = $("#edit_roleName").val().trim();
						if (roleName == '') {
							alert('请输入角色名称！');
							return false;
						}
						/* var status = $("#edit_status").val().trim();
						if (status == '') {
							alert('请选择角色状态！');
						} */
						$.ajax({
							url : "web/updateRole.action",
							data : "roleId=" + roleId + "&roleName=" + roleName,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								$('#edit-dialog').dialog('close');
								roleName = $("#roleName").val().trim();
								$('#dg').datagrid({
									loadFilter : pagerFilter
								}).datagrid('loadData', getData(roleName));
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});

			$("#develop").click(function() {
				var checkednodes = $('#tree').tree('getChecked');
				var menuIds = "";
				var array = new Array();
				if(checkednodes.length>0){
					for(var i=0;i<checkednodes.length;i++){
						if($('#tree').tree('getParent',checkednodes[i].target)!=null){ //不是根节点
							if($('#tree').tree('getChildren',checkednodes[i].target).length>0){ //不是最小子节点
								array.push(checkednodes[i].id);
								var children = $('#tree').tree('getChildren',checkednodes[i].target);
								for(var j=0;j<children.length;j++){
									array.push(children[j].id);
								}
							}else{
								array.push(checkednodes[i].id);
								var node = checkednodes[i];
								while($('#tree').tree('getParent',node.target)!=null){
									var parent = $('#tree').tree('getParent',node.target);
									array.push(parent.id);
									node = parent;
								}
							}
						}else{
							array.push(checkednodes[i].id);
							var children = $('#tree').tree('getChildren',checkednodes[i].target);
							for(var j=0;j<children.length;j++){
								array.push(children[j].id);
							}
						}
					}
				}
				menuIds = deleteTheSame(array);
				var roleId = $("#editRoleId").val().trim();
				$.ajax({
					url : "web/developRoleRight.action",
					data : "roleId=" + roleId + "&menuIds="+menuIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#menutree').dialog('close');
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			
			$("#cancel").click(function(){
				$('#menutree').dialog('close');
			});
		});
		function deleteTheSame(array){
			var newarray = new Array();
			for(var i=0;i<array.length;i++){
				var flag = true;
				for(var j=0;j<newarray.length;j++){
					if(array[i] != newarray[j]){
						continue;
					}else{
						flag = false;
						break;
					}
				}
				if(flag ==true){
					newarray.push(array[i]);
				}else{
					continue;
				}
			}
			var ids = "";
			for(var x = 0;x<newarray.length;x++){
				ids = ids + "," + newarray[x];
			}
			return ids;
		}
	</script>
	<div id="dlg" class="easyui-dialog" title="添加用户角色"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 250px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">角色编号：<input
					type="text" id="add_roleId" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<!-- <li style="line-height: 40px; height: 40px;">角色状态：<select
					id="add_status" style="width: 155px;"><option value="未启用">未启用</option>
						<option value="启用">启用</option>
						<option value="停用">停用</option></select><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li> -->
				<li style="line-height: 40px; height: 40px;">角色名称：<input
					type="text" id="add_roleName" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
			</ul>
		</div>
		<div align="center">
			<input type="button" value="添加" id="addRole" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="编辑用户角色"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 250px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">角色编号：<input
					type="text" id="edit_roleId" disabled="true" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<!-- <li style="line-height: 40px; height: 40px;">角色状态：<select
					id="edit_status" style="width: 155px;"><option value="未启用">未启用</option>
						<option value="启用">启用</option>
						<option value="停用">停用</option></select><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li> -->
				<li style="line-height: 40px; height: 40px;">角色名称：<input
					type="text" id="edit_roleName" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
			</ul>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateRole" />
		</div>
	</div>
	<div id="menutree" class="easyui-dialog" title="权限资源分配"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 470px; padding: 10px; background: #DFE8F6;">
		<div class="easyui-panel" title="权限资源树"
			style="height: 340px; width: 285px; padding: 8px;">
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