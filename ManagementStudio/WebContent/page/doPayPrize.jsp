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
		title="充值赠送规则查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>产品：</td>
				<td><select id="productId" style="width: 95px; height: 23px;">
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
	<table id="dg" class="easyui-datagrid" title="充值赠送规则管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'productName',width:100">充值产品</th>
				<th data-options="field:'freeProductName',width:100">赠送产品</th>
				<th data-options="field:'oneToMany',width:100">赠送比例</th>
				<th data-options="field:'opt', width:150,formatter:operate,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function operate(value, row, index) {
		if (row.status == '启用')
			return '<a href=\'javascript:exchange();\'><font color=\'red\'>结束</font></a>';
		else if (row.status == '结束')
			return '<a href=\'javascript:exchange();\'>启用</a>';
	}
	
	function exchange() {
		var row = $("#dg").datagrid('getSelected');
		$.ajax({
			url : "changePayPrizeStatus.action",
			async : false,
			data : "id=" + row.id,
			dataType : "json",
			type : "post",
			success : function(data) {
				alert(data.respInfo);
				var productId = $("#productId").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter
				}).datagrid('loadData', getData(productId));
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	}
	function getData(productId) {
		if(productId=='null' || productId == null){
			productId = '';
		}
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getPayPrize.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&productId="+productId,
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
			var productId = $("#productId").val();
			var newData = getData(productId);
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
		$.ajax({
			url : "getProductSimpleInfo.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option id='product1' value='null'>请选择产品</option>");
				$("#productId").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].productId+"'>"+data[i].productDescribe+"</option>");
					$("#productId").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		var width = $(document).width();
		var height = $(document).height();
		var productId = $("#productId").val();
		
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(productId));
		$("#searchpanel").panel({
			width : width - 18
		});
		
		$("#search").click(function() {
					var productId = $("#productId").val();
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(productId));
				});
		$("#clear").click(function(){
			$("#productId1").attr('selected',true);			
		});
	
	$("#addPayPrize").click(function() {
		var productId = $("#add_productId").val();
		var freeProductId = $("#add_freeProductId").val();
		var oneToMany = $("#add_oneToMany").val();
		if(!(/^(\+|-)?\d+$/.test(oneToMany))|| oneToMany<0){ 
			 alert("赠送比例请输入正整数！");
		     return false;  
		}  
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addPayPrize.action",
			data : "productId=" + productId + "&freeProductId=" + freeProductId 
					+ "&oneToMany=" + oneToMany,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					$('#dlg').dialog('close');
					var productId = $("#productId").val();
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(productId));
				}else{
					alert(data.respInfo);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updatePayPrize").click(function() {
	var productId = $("#edit_productId").val();
	var freeProductId = $("#edit_freeProductId").val();
	var oneToMany = $("#edit_oneToMany").val();
	var status = $("#edit_status").val();
	var id = $("#edit_id").val();
	if(!(/^(\+|-)?\d+$/.test(oneToMany))|| oneToMany < 0){ 
		 alert("赠送比例请输入正整数！");
	     return false;  
	} 
	$.ajax({
		url : "updatePayPrize.action",
		async : false,
		data :"productId=" + productId + "&freeProductId=" + freeProductId 
		+ "&oneToMany=" + oneToMany+"&id="+id+"&status="+status,
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				var productId = $("#productId").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter
				}).datagrid('loadData', getData(productId));
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
			$("#add_productId").html('');
			$("#add_freeProductId").html('');
			$("#add_oneToMany").val('');
			$.ajax({
				url : "getProductSimpleInfo.action",
				type : "post",
				data : "",
				dataType : "json",
				success : function(data) {
					var option = $("<option id='product1' value='null'>请选择产品</option>");
					$("#add_productId").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].productId+"'>"+data[i].productDescribe+"</option>");
						$("#add_productId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			$.ajax({
				url : "getFreeProductMap.action",
				type : "post",
				data : "",
				dataType : "json",
				success : function(data) {
					var option = $("<option id='freeProduct1' value='null'>请选择免费产品</option>");
					$("#add_freeProductId").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].freeProductId+"'>"+data[i].productName+"</option>");
						$("#add_freeProductId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
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
				$.ajax({
					url : "getProductSimpleInfo.action",
					type : "post",
					data : "",
					dataType : "json",
					success : function(data) {
						for(var i=0;i<data.length;i++){
							option = $("<option value='"+data[i].productId+"'>"+data[i].productDescribe+"</option>");
							$("#edit_productId").append(option);
						}
						$("#edit_productId").val(row.productId);
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$.ajax({
					url : "getFreeProductMap.action",
					type : "post",
					data : "",
					dataType : "json",
					success : function(data) {
						for(var i=0;i<data.length;i++){
							option = $("<option value='"+data[i].freeProductId+"'>"+data[i].productName+"</option>");
							$("#edit_freeProductId").append(option);
						}
						$("#edit_freeProductId").val(row.freeProductId);
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$("#edit_oneToMany").val(row.oneToMany);
				$("#edit_status").val(row.status);
				$("#edit_id").val(row.id);
			}
		}
	}];

	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	</script>
	<div id="dlg" class="easyui-dialog" title="添加充值赠送规则"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 240px; padding: 10px; background: #DFE8F6;">
			<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">充值产品：<select
						id="add_productId" style="width: 157px;"></select><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">赠送产品：<select
					 id="add_freeProductId" style="width: 157px;"></select><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">赠送比例：<input
					type="text"	id="add_oneToMany" style="width: 155px;"/><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				</ul>
			</div>
		<div align="center">
			<input type="button" value="添加" id="addPayPrize" />
		</div>
	</div>	
<div id="edit-dialog" class="easyui-dialog" title="编辑充值赠送规则"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 240px; padding: 10px; background: #DFE8F6;">
		<input type="hidden" id="edit_status" />
		<input type="hidden" id="edit_id" />
		<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">充值产品：<select
						id="edit_productId" style="width: 155px;"></select><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">赠送产品：<select
						id="edit_freeProductId" style="width: 155px;"></select><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">赠送比例：<input
						type="text" id="edit_oneToMany" style="width: 155px;" /><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				</ul>
			</div>
		<div align="center">
			<input type="button" value="更新" id="updatePayPrize" />
		</div>
	</div>
</body>
</html>

