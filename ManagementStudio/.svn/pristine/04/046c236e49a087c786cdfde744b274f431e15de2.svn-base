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
<script type="text/javascript" src="js/jquery.form.js"></script>
<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;width:100%;height:100%;overflow: hidden;" >
	<input type="hidden" id="editAppVId" />
	<table id="dg" class="easyui-datagrid" title="App版本信息列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'appVId',width:150">数据库自增长标识</th> -->
				<th
					data-options="field:'versionCode',width:100,sortable:true,align:'center'">版本号</th>
				<th
					data-options="field:'versionName',width:100,sortable:true,align:'center'">具体版本</th>
				<th data-options="field:'phoneType',width:200,align:'center'">操作系统</th>
				<th data-options="field:'couldUse',width:200,align:'center'">适用机型</th>
				<th
					data-options="field:'publishTime',width:200,sortable:true,align:'center'">最后修改时间</th>
				<th data-options="field:'APKUrl',sortable:true,align:'center',formatter:function(){return '<a href=\'javascript:AppDetail();\'>查看</a>'}">升级日志</th>
				<th
					data-options="field:'remark',width:200,sortable:true,align:'center'">状态</th>
				<th
					data-options="field:'opt', width:150,formatter:operate,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		function operate(value, row, index) {
			if (row.remark == '使用')
				return '<a href=\'javascript:exchange();\'><font color=\'red\'>禁用</font></a>';
			else if (row.remark == '禁用')
				return '<a href=\'javascript:exchange();\'>使用</a>';
		}

		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 25,
				height : height - 20
			}).datagrid('loadData', getData());
			$("#addAppVersion")
					.click(
							function() {
								var versionCode = $("#versionCode").val().trim();
								if (versionCode == '') {
									alert("版本号不能为空！");
									return false;
								}
								var versionName = $("#versionName").val().trim();
								if (versionName == '') {
									alert("具体版本不能为空！");
									return false;
								}
								var ApkFile = $("#ApkFile").val();
								if (ApkFile == '') {
									alert("请选择要上传的apk文件!");
									return false;
								} else if (ApkFile.split(".")[1].toLowerCase() != 'apk') {
									alert("上传文件格式错误,请重新选择文件!");
									return false;
								}
								var width = $(document).width();
								var height = $(document).height();
								var options = {
										dataType : "json",//数据类型 
										error : function() {
											alert("后台错误！");
										},
										success : function(data) {//调用Action后返回过来的数据 
											alert(data.respInfo);
											if (data.respCode == 0) {
												$("#dlg").dialog("close");
												$('#dg').datagrid({
													loadFilter : pagerFilter,
													width : width - 23,
													height : height - 20
												}).datagrid('loadData', getData());
											}
										}
									};
									$("#addForm").ajaxSubmit(options);
							});

			$("#updateAppVersion").click(
					function() {
						var appVId = $("#editAppVId").val();
						var versionCode = $("#edit_versionCode").val().trim();
						if (versionCode == '') {
							alert("版本号不能为空！");
							return false;
						}
						var versionName = $("#edit_versionName").val().trim();
						if (versionName == '') {
							alert("具体版本不能为空！");
							return false;
						}
						var phoneType = $("#edit_phoneType").val().trim();
						var couldUse = $("#edit_couldUse").val().trim();
						var remark = $("#edit_remark").val();
						var APKUrl=$("#edit_appAPKUrl").val();
						var width = $(document).width();
						var height = $(document).height();
						APKUrl=encodeURIComponent(APKUrl);
						$.ajax({
							url : "updateAppVersion.action",
							async : false,
							data : "appVId=" + appVId + "&versionCode="
									+ versionCode + "&versionName="
									+ versionName + "&phoneType=" + phoneType
									+ "&couldUse=" + couldUse + "&remark=" + remark +"&APKUrl="+APKUrl,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								if (data.respCode == 0) {
									$("#edit-dialog").dialog("close");
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										width : width - 23,
										height : height - 20
									}).datagrid('loadData', getData());
								}
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#fresh").click(function() {
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 23,
					height : height - 20
				}).datagrid('loadData', getData());
			});
		});

		var toolbar = [ {
			text : '添加新App版本信息',
			iconCls : 'icon-add',
			handler : function() {
				$("#dlg").dialog("open");
				
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
					return false;
				} else {
					var row = $("#dg").datagrid('getSelected');
					$("#editAppVId").val(row.appVId);
					$('#edit-dialog').dialog('open');
					$("#edit_versionCode").val(row.versionCode);
					$("#edit_versionName").val(row.versionName);
					$("#edit_phoneType").val(row.phoneType);
					$("#edit_couldUse").val(row.couldUse);
					$("#edit_remark").val(row.remark);
					$("#edit_appAPKUrl").val(row.APKUrl);
				}
			}

		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {

				var rows = $('#dg').datagrid('getSelections');
				if (window.confirm("确认删除这" + rows.length + "记录？")) {
					var appVIds = '';
					for ( var i = 0; i < rows.length; i++) {
						appVIds = appVIds + "," + rows[i].appVId;
					}
					var width = $(document).width();
					var height = $(document).height();
					$.ajax({
						url : "removeAppVersion.action",
						data : "appVIds=" + appVIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								width : width - 23,
								height : height - 20
							}).datagrid('loadData', getData());
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				} else {
					return false;
				}
			}
		} ];
		function getData() {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getAppVersion.action",
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
		function exchange() {
			var row = $("#dg").datagrid('getSelected');
			$.ajax({
				url : "exchangeStatus.action",
				async : false,
				data : "appVId=" + row.appVId,
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
		String.prototype.trim = function() {
			  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
			};
			
		function AppDetail(){
			$('#detail_dialog').dialog('open');	
			var row = $("#dg").datagrid('getSelected');
			$("#APKUrl_detail").val(row.APKUrl);
		}	
		function APKUrl_back(){
			$('#detail_dialog').dialog('close');
		}
	</script>
	<div id="dlg" class="easyui-dialog" title="添加新App版本信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 350px; padding: 10px; background: #DFE8F6;">
		<input type="hidden" id="edit_remark" />
		<form action="addAppVersion.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
			<div>
				<table align="center">
				<tr>
					<td>版&nbsp;本&nbsp;号：</td>
					<td><input type="text" id="versionCode" name="versionCode" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>具体版本：</td>
					<td><input type="text" id="versionName" name="versionName" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>操作系统：</td>
					<td><select id="phoneType" style="width: 183px;" name="phoneType">
							<option value="Android">Android</option>
							<option value="ios">ios</option></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>适用机型：</td>
					<td><input type="text" id="couldUse" name="couldUse" style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>升级日志：</td>
					<td><textarea rows="5" cols="23" id="APKUrl" name="APKUrl" style="width:180px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>apk&nbsp;文件：</td>
					<td><input type="file" id="ApkFile" name="ApkFile" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			</div>
		</form>
		<div align="center">
			<input type="button" value="添加" id="addAppVersion" style="margin-top: 10px;" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="编辑App版本"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 330px; padding: 10px; background: #DFE8F6;">
		<div>
			<table align="center">
				<tr>
					<td>版&nbsp;本&nbsp;号：</td>
					<td><input type="text" id="edit_versionCode" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>具体版本：</td>
					<td><input type="text" id="edit_versionName" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>操作系统：</td>
					<td><select id="edit_phoneType" style="width: 183px;">
							<option value="Android">Android</option>
							<option value="ios">ios</option></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>适用机型：</td>
					<td><input type="text" id="edit_couldUse" style="width:180px;"/></td>
					<td><font >&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>升级日志：</td>
					<td><textarea rows="5" cols="23" id="edit_appAPKUrl" style="width:180px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				
			</table>
			
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateAppVersion" />
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
	<button id="fresh" style="display: none;"></button>
	
	<div id="detail_dialog" class="easyui-dialog" title="升级日志"
		data-options="closed:true"
		style="width: 400px; height: 250px; padding: 10px;">
		<div id="div1"
			style="margin-left: 30px; margin-top: 20px;">
			<table cellspacing="0">
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;background-color:#F5F7F7;" valign="top"><div align="center" style="margin-top: 10px;">升级日志：</div></td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;" ><textarea rows="7" cols="30" id="APKUrl_detail" style="padding-top:5px;" readonly="true"></textarea></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top: 10px;">
			<input style="width:55px;height:30px;" type="button" value="返回"  id="APKUrl_back" onclick="APKUrl_back()"/>
		</div>
	</div>
	
	
	
</body>
</html>