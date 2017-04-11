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
    margin: 4px 5px 0 0;
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

	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="宣传渠道查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>渠道名称：</td>
				<td><select id="path" style="width: 95px; height: 23px;">
				</select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="editProductId" />
	<table id="dg" class="easyui-datagrid" title="宣传渠道管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				   <th data-options="field:'ck',checkbox:true"></th>
					<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
					<th id="productId"data-options="field:'path',width:100">渠道标号</th>
					<th data-options="field:'detail',width:100">渠道名称</th>
					
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	
	function getData(path) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getAdvertisingWay.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&path=" + path,
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
			var path = $("#path").val();
			var newData = getData(path);
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
		var path = $("#path").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(path));
		$("#searchpanel").panel({
			width : width - 18
		});
		$.ajax({
			url : "getAdvertisingDetail.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option id='path1' value='null'></option>");
				$("#path").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].path+"'>"+data[i].detail+"</option>");
					$("#path").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		$("#search").click(function() {
			var path = $("#path").val();
					if(path=="null"){
						path = null;
					}
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(path));
				});
		$("#clear").click(function(){
			$("#path").val('null');
			
		});
	$("#addAdvertisingWay").click(function() {
		var path = $("#add_path").val().trim();
		if (path == '') {
			alert("渠道标号不能为空！");
			return false;
		}
		var detail = $("#add_detail").val().trim();
		if (detail == '') {
			alert("渠道名称不能为空！");
			return false;
		}
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addAdvertisingWay.action",
			data : "path=" + path + "&detail=" + detail,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var path = $("#path").val();
					if(path=="null"){
						path = null;
					}
					$('#dlg').dialog('close');
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(path));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updateAdvertisingWay").click(function() {
	var path=$("#edit_path").val().trim().trim();
	var detail=$("#edit_detail").val().trim();
	if(path==''){
		alert("渠道标号不能为空");
		return false;
	}
	if(detail==''){
		alert("渠道名称不能为空");
		return false;
	}
	var width = $(document).width();
	var height = $(document).height();
	$.ajax({
		url : "updateAdvertisingWay.action",
		async : false,
		data :"path=" + path + "&detail=" + detail+"&id="+$("#edit_id").val(),
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			var path = $("#path").val();
			if(path=="null"){
				path = null;
			}
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 18,
					height : height - 20
				}).datagrid('loadData', getData(path));
			}
		},
		error : function(data) {
			alert("error 后台出现错误！");
			}
	});
});
});


	var toolbar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$("#dlg").dialog("open");
			$("#add_path").val('');
			$("#add_detail").val('');
		
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
				$('#edit-dialog').dialog('open');
				$("#edit_id").val(row.id);
				$("#edit_path").val(row.path);
				$("#edit_detail").val(row.detail);
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
					url : "removeAdvertisingWay.action",
					data : "ids="+ ids,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						var path = $("#path").val();
						if(path=="null"){
							path = null;
						}
						$('#dg').datagrid({
							loadFilter : pagerFilter,
						}).datagrid('loadData', getData(path));
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
	

	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	</script>
	<div id="dlg" class="easyui-dialog" title="添加宣传渠道信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 380px; height: 220px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
			<table align="center">
				<tr>
					<td>渠道标号：</td>
					<td><input type="text" id="add_path" name="path" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>渠道名称：</td>
					<td><input type="text" id="add_detail" name="detail" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addAdvertisingWay" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改宣传渠道信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 380px; height: 220px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_id" name="id" />
			<table align="center">
				<tr>
					<td>渠道标号：</td>
					<td><input type="text" id="edit_path" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>渠道名称：</td>
					<td><input type="text" id="edit_detail" style="width: 155px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="修改" id="updateAdvertisingWay" />
		</div>
	</div>
	
</body>
</html>

