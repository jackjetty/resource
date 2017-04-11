<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>促销信息管理</title>
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
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="图片信息查询"
		style="height: 80px; padding: 10px;overflow: hidden;">	
		<table>
			<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>图片名称：</td>
				<td><input type="text" id="pictureName" style="width: 95px;" /></td>
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
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
	<table id="dg" class="easyui-datagrid" title="图片信息列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:10,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'pictureName',width:100">图片名称</th>
				<th data-options="field:'startTime',width:100">开始时间</th>
				<th data-options="field:'endTime',width:100">结束时间</th>
				<th data-options="field:'opt', width:150,formatter:operate,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	
	<script type="text/javascript">
	function operate(value, row, index) {
		if (row.operate == '启用')
			return '<a href=\'javascript:exchange();\'><font color=\'red\'>结束</font></a>';
		else if (row.operate == '结束')
			return '<a href=\'javascript:exchange();\'>启用</a>';
	}
	
	function exchange() {
		var row = $("#dg").datagrid('getSelected');
		$.ajax({
			url : "changeOperate.action",
			async : false,
			data : "id=" + row.id,
			dataType : "json",
			type : "post",
			success : function(data) {
				alert(data.respInfo);
				var pictureName = $("#pictureName").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
				}).datagrid('loadData', getData(pictureName, startTime, endTime));
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	}

	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		}
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$('#dlg').dialog('open');
				$("#add_pictureName").val('');
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
					$("#edit_pictureName").val(row.pictureName);
					$("#edit_startTime").val(row.startTime);
					$("#edit_endTime").val(row.endTime);
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var pictureIds = '';
					for(var i=0;i<rows.length;i++){
						pictureIds = pictureIds + ","+rows[i].id;
					}
					$.ajax({
						url : "removeAppStartPicture.action",
						data : "pictureIds="+ pictureIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var pictureName = $("#pictureName").val().trim();
							var startTime = $("#startTime").datebox('getValue');
							var endTime = $("#endTime").datebox('getValue');
							$('#dg').datagrid({
								loadFilter : pagerFilter,
							}).datagrid('loadData', getData(pictureName, startTime, endTime));
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
		
		function getData(pictureName, startTime, endTime) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getAppStartPicture.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&pictureName=" + pictureName + "&startTime=" + startTime+"&endTime="+endTime,
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
				var pictureName = $("#pictureName").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var newData = getData(pictureName, startTime, endTime);
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
			var pictureName = $("#pictureName").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(pictureName, startTime, endTime));
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				pictureName = $("#pictureName").val().trim();
				startTime = $("#startTime").datebox('getValue');
				endTime = $("#endTime").datebox('getValue');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(pictureName, startTime, endTime));
			});
			$("#clear").click(function(){
				$("#pictureName").val('');
				$("#startTime").datebox('setValue','');
				$("#endTime").datebox('setValue','');
				
			});
			$("#addAppStartPicture").click(function(){
				var pictureName=$("#add_pictureName").val();
				var image1=$("#add_image1").val();
				var image2=$("#add_image2").val();
				var image3=$("#add_image3").val();
				if(pictureName==''){alert("图片名称不能为空"); return false;}
				if(image1==''){alert('请添加图片'); return false;}
				if(image2==''){alert('请添加图片'); return false;}
				if(image3==''){alert('请添加图片'); return false;}
				var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) { 
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#dlg").dialog("close");
								pictureName = $("#pictureName").val().trim();
								startTime = $("#startTime").datebox('getValue');
								endTime = $("#endTime").datebox('getValue');
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(pictureName, startTime, endTime));
							}
						}
					};
					$("#addForm").ajaxSubmit(options);
				
			});
			$("#updateAppStartPicture").click(function(){
				var pictureName=$("#edit_pictureName").val();
				var startTime=$("#edit_startTime").val();
				var endTime=$("#edit_endTime").val();
				if(pictureName==''){alert("图片名称不能为空"); return false;}
				var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) { 
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#edit-dialog").dialog("close");
								pictureName = $("#pictureName").val().trim();
								startTime = $("#startTime").datebox('getValue');
								endTime = $("#endTime").datebox('getValue');
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(pictureName, startTime, endTime));
							}
						}
					};
					$("#updateForm").ajaxSubmit(options);
			});
					
			
				
		});
	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加图片"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
		<form action="addAppStartPicture.action" method="post" enctype="multipart/form-data" id="addForm">
			<table align="center">
				<tr>
					<td align="right">图片名称：</td>
					<td><input type="text" id="add_pictureName" name="pictureName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>首页图片1(320)：</td>
					<td style="text-align:left;"><input type="file" id="add_image1" name="image1" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #DFE8F6;"><font color="red">*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>首页图片2(480)：</td>
					<td style="text-align:left;"><input type="file" id="add_image2" name="image2" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #DFE8F6;"><font color="red">*&nbsp;必填</font></td>
					
				</tr>
				<tr>
					<td style="border-bottom:0px;">首页图片3(720)：</td>
					<td style="text-align:left;border-bottom:0px;"><input type="file" id="add_image3" name="image3" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #DFE8F6;border-bottom:0px;"><font color="red">*&nbsp;必填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addAppStartPicture" />
		</div>
	</div>
	
	<div id="edit-dialog" class="easyui-dialog" title="修改图片"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
		<form action="updateAppStartPicture.action" method="post" enctype="multipart/form-data" id="updateForm">
			<input type="hidden" id="edit_id" name="id" />
			<input type="hidden" id="edit_startTime" name="startTime" />
			<input type="hidden" id="edit_endTime" name="endTime"/>
			<table align="hidden">
				<tr>
					<td align="right">图片名称：</td>
					<td><input type="text" id="edit_pictureName" name="image1" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
				<tr>
					<td>首页图片1(320)：</td>
					<td style="text-align:left;"><input type="file" id="edit_image1" name="image1" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #DFE8F6;">*&nbsp;选填</td>
				</tr>
				<tr>
					<td>首页图片2(480)：</td>
					<td style="text-align:left;"><input type="file" id="edit_image2" name="image2" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #DFE8F6;">*&nbsp;选填</td>
					
				</tr>
				<tr>
					<td style="border-bottom:0px;">首页图片3(720)：</td>
					<td style="text-align:left;border-bottom:0px;"><input type="file" id="edit_image3" name="image3" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #DFE8F6;border-bottom:0px;">*&nbsp;选填</td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="修改" id="updateAppStartPicture" />
		</div>
	</div>
</body>
</html>