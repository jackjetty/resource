<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
#div1 td{
	height: 35px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="短信文字查询"
		style="height: 80px; padding: 10px;">	
		<div style="float:left;vertical-align: center;height:31px;line-height:31px;">
		信息功能：<input type="text" id="use" /> 信息描述：<input type="text" id="describe" /></div>
			<div style="height:100%;float:left;margin-left: 15px;">
			<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></div>
	</div>
	<table id="dg" class="easyui-datagrid" title="短息文字列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'use',width:50,sortable:true">信息功能</th>
				<th data-options="field:'content',width:200,sortable:true">信息内容</th>
				<th data-options="field:'describe',width:100">信息描述</th>
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
				$("#add_use").val('');
				$("#add_content").val('');
				$("#add_describe").val('');
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
					$("#edit_id").val(row.id);
					$('#edit-dialog').dialog('open');
					$("#edit_use").val(row.use);
					$("#edit_content").val(row.content);
					$("#edit_describe").val(row.describe);
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
				var use = $("#use").val().trim();
				var describe = $("#describe").val().trim();
				var newData = getData(use,describe);
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
		function getData(use, describe) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getPhoneMessage.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&use=" + use + "&describe=" + describe,
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
			var use = $("#use").val();
			var describe = $("#describe").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(use, describe));
			
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				use = $("#use").val().trim();
				describe = $("#describe").val().trim();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(use, describe));
			});
			$("#clear").click(function(){
				$("#describe").val('');
				$("#use").val('');
				
			});
			$("#addMessage").click(
					function() {
						var use = $("#add_use").val();
						var content = $("#add_content").val();
						var describe=$("#add_describe").val();
						if (use == '') {
							alert('请输入用户类型！');
							return false;
						}
						if(content==''){
							alert('请输入信息内容');
							return false;
						}
						if(describe==''){
							alert('请输入信息描述');
							return false;
						}
						$.ajax({
							url : "addPhoneMessage.action",
							data : "use=" + use + "&content=" + content
									+ "&describe=" + describe,
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#dlg').dialog('close');
									use = $("#use").val();
									describe = $("#describe").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(use, describe));
								}else{
									alert(data.respInfo);
								}
								
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#updateMessage").click(
					function() {
						var use = $("#edit_use").val();
						var content = $("#edit_content").val();
						var describe=$("#edit_describe").val();
						if (use == '') {
							alert('请输入用户类型！');
							return false;
						}
						if(content==''){
							alert('请输入信息内容');
							return false;
						}
						if(describe==''){
							alert('请输入信息描述');
							return false;
						}
						$.ajax({
							url : "updatePhoneMessage.action",
							data : "use=" + use + "&content=" + content
							+ "&describe=" + describe +"&id=" +$("#edit_id").val(),
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#edit-dialog').dialog('close');
									use = $("#use").val();
									describe = $("#describe").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(use, describe));
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
	
	<div id="dlg" class="easyui-dialog" title="添加短信信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
			<table align="center">
				<tr>
					<td>信息功能：</td>
					<td><input type="text" id="add_use" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>信息内容：</td>
					<td><textarea rows="6" cols="19" id="add_content" style="width:178px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>信息描述：</td>
					<td><input type="text" id="add_describe" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="添加" id="addMessage" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改短信信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_id" />
			<table align="center">
				<tr>
					<td>用户类型：</td>
					<td><input type="text" id="edit_use" style="width:180px;" disabled='true' /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>信息内容：</td>
					<td><textarea rows="6" cols="19" id="edit_content" style="width:180px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>信息描述：</td>
					<td><input type="text" id="edit_describe" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="更新" id="updateMessage" />
		</div>
	</div>
	
</body>
</html>