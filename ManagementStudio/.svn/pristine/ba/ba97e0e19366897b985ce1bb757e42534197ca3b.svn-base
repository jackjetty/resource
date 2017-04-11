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
	<input type="hidden" id="editProductId" />
	<table id="dg" class="easyui-datagrid" title="商户管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th data-options="field:'merchantId',width:100">商户编号</th>
				<th data-options="field:'merchantName',width:100">商户名称</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData() {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getMerchantInfo.action",
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
	$(function() {
		var width = $(document).width();
		var height = $(document).height();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 20
		}).datagrid(
				'loadData',
				getData());
		$("#addMerchant").click(function() {
			var merchantId = $("#add_merchantId").val().trim();
			if (merchantId == '') {
				alert("商户编号不能为空！");
				return false;
			}
			if(merchantId.length>10){
				alert("商户编号不能多于10位");
				return false;
			}
			var merchantName = $("#add_merchantName").val().trim();
			if (merchantName == '') {
				alert("商户名称不能为空！");
				return false;
			}
			if(merchantName.length>30){
				alert("商户名称不能多于30位！");
				return false;
			}
			var width = $(document).width();
			var height = $(document).height();
			$.ajax({
				url : "addMerchant.action",
				data : "merchantId=" + merchantId + "&merchantName=" + merchantName,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#dlg').dialog('close');
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData());
					}else{
						alert(data.respInfo);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		});

	$("#updateMerchant").click(function() {
		var merchantId = $("#edit_merchantId").val().trim();
		if (merchantId == '') {
			alert("商户编号不能为空！");
			return false;
		}
		if(merchantId.length>10){
			alert("商户编号不能多于10位");
			return false;
		}
		var merchantName = $("#edit_merchantName").val().trim();
		if (merchantName == '') {
			alert("商户名称不能为空！");
			return false;
		}
		if(merchantName.length>30){
			alert("商户名称不能多于30位！");
			return false;
		}
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "updateMerchant.action",
			async : false,
			data :"merchantId=" + merchantId + "&merchantName=" + merchantName,
			dataType : "json",
			type : "post",
			success : function(data) {
				alert(data.respInfo);
				if (data.respCode == 0) {
					$("#edit-dialog").dialog("close");
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						width : width - 18,
						height : height - 20
					}).datagrid('loadData', getData());
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
			$("#add_merchantId").val('');
			$("#add_merchantId").val('');
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
				$("#edit_merchantId").val(row.merchantId);
				$("#edit_merchantName").val(row.merchantName);
				
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (window.confirm("确认删除这" + rows.length + "记录？")) {
				var merchantIds = '';
				for ( var i = 0; i < rows.length; i++) {
					merchantIds = merchantIds + "," + rows[i].merchantId;
					
				}
				var width = $(document).width();
				var height = $(document).height();
				$.ajax({
					url : "removeMerchant.action",
					data : "merchantIds=" + merchantIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width : width - 18,
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
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
</script>
<div id="dlg" class="easyui-dialog" title="添加电信手机号段信息"
	data-options="iconCls:'icon-save',closed:true"
	style="width: 410px; height: 330px; padding: 10px; background: #DFE8F6;">
	<form action="addMerchant.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">商户编号：<input
						type="text" id="add_merchantId" name="merchantId" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">商户名称：<input
						type="text" id="add_merchantName" name="merchantName"><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				</ul>
		</div>
	</form>
	<div align="center">
		<input type="button" value="添加" id="addMerchant" />
	</div>
</div>
<div id="edit-dialog" class="easyui-dialog" title="编辑返回码"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 330px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">商户编号：<input
						type="text" id="edit_merchantId" disabled="true" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">商户名称：<input
						type="text" id="edit_merchantName" ><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				</ul>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateMerchant" />
		</div>
	</div>
	<div id="selectMerchant" class="easyui-dialog" title="商户"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 480px; padding: 10px; background: #DFE8F6;">
		<div id="mer" class="easyui-panel" title="商户树"
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

