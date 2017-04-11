<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>热门游戏管理</title>
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
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="热门游戏查询"
		style="height: 80px; padding: 10px;overflow: hidden;">	
		<table>
			<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>游戏名称：</td>
				<td><input type="text" id="packageName" style="width: 95px;" /></td>
				<td>标题：</td>
				<td><input type="text" id="title" style="width: 95px;" /></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true" style="margin-left:10px;">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="热门游戏列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'packageName',width:100">游戏包名</th>
				<th data-options="field:'title',width:100">游戏标题</th>
				<th data-options="field:'star',width:50,sortable:true">游戏评价</th>
				<th data-options="field:'fileSize',width:50">游戏大小</th>
				<th data-options="field:'icoName',width:50">游戏图片</th>
				<th data-options="field:'apkUrl',width:150">游戏链接</th>
			</tr>
		</thead>
	</table>
	
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		}
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$('#dlg').dialog('open');
				$("#add_packageName").val('');
				$("#add_title").val('');
				$("#add_star").val('');
				$("#add_fileSize").val('');
				$("#add_icoName").val('');
				$("#add_icoImg").val('');
				$("#add_apkUrl").val('');
				
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
				} else{
					var row = $("#dg").datagrid('getSelected');
					$('#edit-dialog').dialog('open');
					$("#edit_id").val(row.id);
					$("#edit_packageName").val(row.packageName);
					$("#edit_title").val(row.title);
					$("#edit_star").val(row.star);
					$("#edit_fileSize").val(row.fileSize);
					$("#edit_icoName").val(row.icoName);
					$("#edit_apkUrl").val(row.apkUrl);
				
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var ids = '';
					for(var i=0;i<rows.length;i++){
						ids = ids + ","+rows[i].id;
					}
					$.ajax({
						url : "removeHotGame.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var packageName = $("#packageName").val().trim();
							var title = $("#title").val().trim();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
							}).datagrid('loadData', getData(packageName,title));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					
				}else{
					return false;
				}
			}
		}];
		
		
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
						onSelectPage : function(pageNum,pageSize) {
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
				var packageName = $("#packageName").val().trim();
				var title = $("#title").val().trim();
				var newData = getData(packageName,title);
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
		function getData(packageName,title) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getHotGameInfo.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&packageName=" + packageName + "&title=" + title,
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
			var packageName = $("#packageName").val().trim();
			var title = $("#title").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(packageName,title));
			
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				packageName = $("#packageName").val().trim();
				title = $("#title").val().trim();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(packageName,title));
			});
			
			$("#clear").click(function(){
				$("#packageName").val('');
				$("#title").val('');
			});
			
			$("#addHotGame").click(function(){
				var packageName=$("#add_packageName").val();
				var title=$("#add_title").val();
				var star=$("#add_star").val();
				var fileSize=$("#add_fileSize").val();
				var icoName=$("#add_icoName").val();
				var icoImg1=$("#add_icoImg1").val();
				var icoImg2=$("#add_icoImg2").val();
				var apkUrl=$("#add_apkUrl").val();
				if(packageName==''){alert("游戏名称不能为空");return false;}
				if(title==''){alert("标题不能为空");return false;}
				if(star==''){alert("游戏评价不能为空");return false;}
				if(fileSize==''){alert("游戏大小不能为");return false;}
				if(icoName==''){alert("图片名称不能为空");return false;}
				if(icoImg1=='' || icoImg2==''){alert("游戏图片不能为空");return false;}
				if(apkUrl==''){alert("链接地址不能为空");return false;}
				var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) { 
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#dlg").dialog("close");
								packageName = $("#packageName").val().trim();
								title = $("#title").val().trim();
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(packageName,title));
							}
						}
					};
					$("#addForm").ajaxSubmit(options);
				
			});
			
			$("#updateHotGame").click(function(){
				var packageName=$("#edit_packageName").val();
				var title=$("#edit_title").val();
				var star=$("#edit_star").val();
				var fileSize=$("#edit_fileSize").val();
				var icoName=$("#edit_icoName").val();
				var apkUrl=$("#edit_apkUrl").val();
				if(packageName==''){alert("游戏名称不能为空");return false;}
				if(title==''){alert("标题不能为空");return false;}
				if(star==''){alert("游戏评价不能为空");return false;}
				if(fileSize==''){alert("游戏大小不能为");return false;}
				if(icoName==''){alert("图片名称不能为空");return false;}
				if(apkUrl==''){alert("链接地址不能为空");return false;}
				var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) { 
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#edit-dialog").dialog("close");
								packageName = $("#packageName").val().trim();
								title = $("#title").val().trim();
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(packageName,title));
							}
						}
					};
					$("#updateForm").ajaxSubmit(options);
				
			});
			
				
		});
	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加游戏信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 450px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
		<form action="addHotGame.action" method="post" enctype="multipart/form-data" id="addForm">
			<table align="center">
				<tr>
					<td>游戏包名：</td>
					<td><input type="text" id="add_packageName" name="packageName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏标题：</td>
					<td><input type="text" id="add_title" name="title" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏评价：</td>
					<td>
						<select id="add_star" name="star" style="width: 180px;" >
							<option value="">请选择</option>
							<option value="1">一星</option>
							<option value="2">二星</option>
							<option value="3">三星</option>
							<option value="4">四星</option>
							<option value="5">五星</option>
						</select>
					</td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏大小：</td>
					<td><input type="text" id="add_fileSize" name="fileSize" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>图片名称：</td>
					<td><input type="text" id="add_icoName" name="icoName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏图片1：</td>
					<td><input type="file" id="add_icoImg1" name="icoImg1" style="width: 180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏图片2：</td>
					<td><input type="file" id="add_icoImg2" name="icoImg2" style="width: 180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>链接地址：</td>
					<td><input type="text" id="add_apkUrl" name="apkUrl" style="width: 180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addHotGame" />
		</div>
	</div>
	
	<div id="edit-dialog" class="easyui-dialog" title="修改游戏信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 420px; height: 450px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
		<form action="updateHotGame.action" method="post" enctype="multipart/form-data" id="updateForm">
			<input type="hidden" id="edit_id" name="id" />
			<table align="center">
				<tr>
					<td>游戏包名：</td>
					<td><input type="text" id="edit_packageName" name="packageName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏标题：</td>
					<td><input type="text" id="edit_title" name="title" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏评价：</td>
					<td>
						<select id="edit_star" name="star" style="width: 180px;" >
							<option value="">请选择</option>
							<option value="1">一星</option>
							<option value="2">二星</option>
							<option value="3">三星</option>
							<option value="4">四星</option>
							<option value="5">五星</option>
						</select>
					</td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏大小：</td>
					<td><input type="text" id="edit_fileSize" name="fileSize" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>图片名称：</td>
					<td><input type="text" id="edit_icoName" name="icoName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏图片1：</td>
					<td><input type="file" id="edit_icoImg1" name="icoImg1" style="width: 180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>游戏图片2：</td>
					<td><input type="file" id="edit_icoImg2" name="icoImg2" style="width: 180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>链接地址：</td>
					<td><input type="text" id="edit_apkUrl" name="apkUrl" style="width: 180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="修改" id="updateHotGame" />
		</div>
	</div>
	
</body>
</html>