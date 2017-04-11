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

<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

div ul {
	list-style: none;
}

div ul li {
	line-height: 40px;
	height: 40px;
}

#div1 td{
	height: 35px;
}
#div2 td{
	height: 35px;
}

#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("images/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
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
    background: url("images/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
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
    background: url("images/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("images/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="editMenuId" />
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="功能菜单查询"
		style="height: 80px; padding: 10px;">	
		<div style="float:left;vertical-align: center;height:31px;line-height:31px;">
		菜单名称：<input type="text" id="text" /> 菜单ID：<input type="text" id="id" /></div>
			<div style="height:100%;float:left;margin-left: 15px;">
			<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></div>
	</div>
	<table id="dg" class="easyui-datagrid" title="功能菜单列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'menuId',width:80,hide:'true'">数据库ID</th> -->
				<th data-options="field:'id',width:80,sortable:true">菜单ID</th>
				<th data-options="field:'grade',width:100,sortable:true">菜单等级</th>
				<th data-options="field:'text',width:200">菜单名称</th>
				<th data-options="field:'link',width:200">链接地址</th>
				<th data-options="field:'iconCls',width:240">icon属性</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$('#dlg').dialog('open');
				$("#add_id").val('');
				$("#add_text").val('');
				$("#add_link").val('');
				$("#add_iconCls").val('');
				if($("#add_grade").val()!=2){
					$("#addtr").remove();
					$("#add_grade").val('2');
				}
			}
		}, '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length == 0) {
					alert("请选择需要编辑的数据！");
					return false;
				} else if (rows.length == 1 && rows[0].id == '0') {
					alert("一级菜单无需编辑！");
					return false;
				} else if (rows.length > 1) {
					alert("一次只能编辑一行！");
				} else {
					var row = $("#dg").datagrid('getSelected');
					$("#editMenuId").val(row.menuId);
					$('#edit-dialog').dialog('open');
					$("#edit_id").val(row.id);
					$("#edit_grade").val(row.grade);
					$("#edit_text").val(row.text);
					$("#edit_link").val(row.link);
					$("#edit_iconCls").val(row.iconCls);
					if($("#edit_grade").val()==2){
						if($("#edittr")!=null){
							$("#edittr").remove();
						}
						var id=row.id;
						var grade=row.grade;
						$.ajax({
							url : "getMenulikeId.action",
							data : "id="+id+"&grade="+grade,
							dataType : "json",
							type : "post",
							success : function(data) {},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					}
					if($("#edit_grade").val()==3){
						if($("#edittr")!=null){
							$("#edittr").remove();
						}
						var id=$("#edit_id").val();
						var grade=row.grade;
						$.ajax({
							url : "getMenulikeId.action",
							data : "id="+id+"&grade="+grade,
							dataType : "json",
							type : "post",
							success : function(data) {},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
						var id2=id.substr(0,2);
						$.ajax({
							url : "getMenuTextId.action",
							data : "",
							dataType : "json",
							type : "post",
							success : function(data) {
								var idcode=data.id.split(",");
								var text=data.text.split(",");
								var tr="<tr id='edittr'><td>父级菜单</td><td><select id='edit_fgrade' style='width: 155px;'>";
								for(var i=0;i<idcode.length-1;i++){
										if(idcode[i]==id2){
											var op="<option value='"+idcode[i]+"' selected='true'>"+text[i]+"</option>";
											tr=tr+op;
										}else{
											var op="<option value='"+idcode[i]+"'>"+text[i]+"</option>";
											tr=tr+op;
										}
								}
								tr=tr+"</select></td><td><font color='red'>&nbsp;*&nbsp;必填</font></td></tr>";
								$("#tr2").after(tr);
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					}
				}
			}

		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var menuIds = '';
					for(var i=0;i<rows.length;i++){
						menuIds = menuIds + ","+rows[i].menuId;
					}
					$.ajax({
						url : "removeMenu.action",
						data : "menuIds="+ menuIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							id = $("#id").val();
							text = $("#text").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(id, text));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				}else{
					return false;
				}
			}
		} ];

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
				var id = $("#id").val().trim();
				var text = $("#text").val().trim();
				var newData = getData(id,text);
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
		function getData(id, text) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getMenuByPage.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&id=" + id + "&text=" + text,
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
			var width = $(document).width();
			var height = $(document).height();
			var id = $("#id").val();
			var text = $("#text").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(id, text));
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				id = $("#id").val().trim();
				text = $("#text").val().trim();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(id, text));
			});
			$("#clear").click(function(){
				$("#text").val('');
				$("#id").val('');
				
			});
			$("#addMenu").click(
					function() {
						id = $("#add_id").val().trim();
						var grade = $("#add_grade").val();
						var fgrade=$("#add_fgrade").val();
						var id2=id.substr(0,2);
						if (id == '') {
							alert('请输入菜单id！');
							return false;
						}
						if(grade==2&&id.length!=2){
							alert('二级菜单id需为2位数');
							return false;
						}
						if(grade==3&&id.length!=4){
							alert('三级菜单id需为4位数');
							return false;
						}
						if(grade==3&&id.length==4&&id2!=fgrade){
							alert('三级菜单id前两位需跟父级菜单一致');
							return false;
						}
						
						text = $("#add_text").val().trim();
						if (text == '') {
							alert('请输入菜单名称！');
							return false;
						}
						var grade = $("#add_grade").val();
						var link = $("#add_link").val().trim();
						if (link == '' && grade == 3) {
							alert('三级菜单的链接地址不能为空！');
							return false;
						} else if (link != '' && grade == 2) {
							alert('二级菜单不需要填写链接地址！');
							return false;
						}
						var iconCls = $("#add_iconCls").val().trim();
						$.ajax({
							url : "addMenu.action",
							data : "iconCls=" + iconCls + "&link=" + link
									+ "&grade=" + grade + "&id=" + id
									+ "&text=" + text,
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#dlg').dialog('close');
									id = $("#id").val();
									text = $("#text").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(id, text));
								}else{
									alert(data.respInfo);
								}
								
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#updateMenu").click(
					function() {
						id = $("#edit_id").val().trim();
						var grade = $("#edit_grade").val();
						var fgrade=$("#edit_fgrade").val();
						var id2=id.substr(0,2);
						if (id == '') {
							alert('请输入菜单id！');
							return false;
						}
						
						if(grade==2&&id.length!=2){
							alert('二级菜单id需为2位数');
							return false;
						}
						if(grade==3&&id.length!=4){
							alert('三级菜单id需为4位数');
							return false;
						}
						if(grade==3&&id.length==4&&id2!=fgrade){
							alert('三级菜单id前两位需跟父级菜单一致');
							return false;
						}
						
						
						text = $("#edit_text").val().trim();
						if (text == '') {
							alert('请输入菜单名称！');
							return false;
						}
						
						var link = $("#edit_link").val().trim();
						if (link == '' && grade == 3) {
							alert('三级菜单的链接地址不能为空！');
							return false;
						} else if (link != '' && grade == 2) {
							alert('二级菜单不需要填写链接地址！');
							return false;
						}
						var iconCls = $("#edit_iconCls").val().trim();
						$.ajax({
							url : "updateMenu.action",
							data : "iconCls=" + iconCls + "&link=" + link
									+ "&grade=" + grade + "&id=" + id
									+ "&text=" + text + "&menuId="
									+ $("#editMenuId").val(),
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#edit-dialog').dialog('close');
									id = $("#id").val().trim();
									text = $("#text").val().trim();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(id, text));
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
		
		function opchange(){
			var grade = $("#add_grade").val();
			if(grade==3){
				$.ajax({
					url : "getMenuTextId.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var idcode=data.id.split(",");
						var text=data.text.split(",");
						var tr="<tr id='addtr'><td>父级菜单：</td><td><select id='add_fgrade' style='width: 155px;'>";
						for(var i=0;i<idcode.length-1;i++){
								var op="<option value='"+idcode[i]+"'>"+text[i]+"</option>";
								tr=tr+op;
						}
						tr=tr+"</select></td><td><font color='red'>&nbsp;*&nbsp;必填</font></td></tr>";
						$("#tr1").after(tr);
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			}
			if(grade==2){
				$("#addtr").remove();
			}
			
		}
		
		function opchangeEdit(){
			var grade = $("#edit_grade").val();
			var id=$("#edit_id").val();
			var id2=id.substr(0,2);
			if(grade==3){
				$.ajax({
					url : "getMenuTextId.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var idcode=data.id.split(",");
						var text=data.text.split(",");
						var tr="<tr id='edittr'><td>父级菜单：</td><td><select id='edit_fgrade' style='width: 155px;'>";
						for(var i=0;i<idcode.length-1;i++){
							if(idcode[i]==id2){
								var op="<option value='"+idcode[i]+"' selected='true'>"+text[i]+"</option>";
								tr=tr+op;
							}else{
								var op="<option value='"+idcode[i]+"'>"+text[i]+"</option>";
								tr=tr+op;
							}	
						}
						tr=tr+"</select></td><td><font color='red'>&nbsp;*&nbsp;必填</font></td></tr>";
						$("#tr2").after(tr);
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			}
			if(grade==2){
				$("#edittr").remove();
			}
			
		}
		
	</script>
	<div id="dlg" class="easyui-dialog" title="添加功能菜单"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:20px;">
			<table align="center">
				<tr id="tr1">
					<td>菜单等级：</td>
					<td><select id="add_grade" style="width: 155px;" onchange="opchange();"><option value="2">二级菜单</option>
						<option value="3">三级菜单</option></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>菜&nbsp;单&nbsp;ID：</td>
					<td><input type="text" id="add_id" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>菜单名称：</td>
					<td><input type="text" id="add_text" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>链接地址：</td>
					<td><input type="text" id="add_link" /></td>
					<td><font color="red">&nbsp;*&nbsp;三级菜单必填</font></td>
				</tr>
				<tr>
					<td>icon&nbsp;属性：</td>
					<td><input type="text" id="add_iconCls" /></td>
					<td><font color="red">&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center">
			<input type="button" value="添加" id="addMenu" />
		</div>
	</div>
	<div id="edit-dialog" class="easyui-dialog" title="添加功能菜单"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:20px;" >
			<table align="center">
				<tr id="tr2">
					<td>菜单等级：</td>
					<td><select id="edit_grade" style="width: 155px;" onchange="opchangeEdit()"><option value="2">二级菜单</option>
						<option value="3">三级菜单</option></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>菜&nbsp;单&nbsp;I&nbsp;D：</td>
					<td><input type="text" id="edit_id" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>菜单名称：</td>
					<td><input type="text" id="edit_text" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td >链接地址：</td>
					<td><input type="text" id="edit_link" /></td>
					<td><font color="red">&nbsp;*&nbsp;三级菜单必填</font></td>
				</tr>
				<tr>
					<td>icon&nbsp;属性：</td>
					<td><input type="text" id="edit_iconCls" /></td>
					<td><font color="red">&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateMenu" />
		</div>
	</div>
</body>
</html>