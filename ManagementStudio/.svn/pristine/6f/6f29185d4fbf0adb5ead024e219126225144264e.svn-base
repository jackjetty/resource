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
		title="产品查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>产品名称：</td>
				<td><select id="busId" style="width: 95px; height: 23px;">
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
	<table id="dg" class="easyui-datagrid" title="产品种类管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th data-options="field:'busId',width:100">产品编号</th>
				<th data-options="field:'merchantId',width:100">所属商家</th>
				<th data-options="field:'btype',width:100">产品名称</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData(busId) {
		if(busId==null){
			busId = -1;
		}
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getBusinessInfo.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&busId=" + busId,
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
			var busId = $("#busId").val();
			if(busId =="null"){
				busId = null;
			}
			var newData = getData(busId);
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
		var busId = $("#busId").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(busId));
		$("#searchpanel").panel({
			width : width - 18
		});
		$.ajax({
			url : "getBusinessType.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option id='busid1' value='null'></option>");
				$("#busId").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].busId+"'>"+data[i].btype+"</option>");
					$("#busId").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		$("#clear").click(function(){
			$("#busid1").attr('selected',true);
		});
		$("#search").click(function() {
					var busId = $("#busId").val();
					if(busId=="null"){
						busId = null;
					}
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(busId));
				});
	
	
	$("#addBusiness").click(function() {
		var busId = $("#add_busId").val().trim();
		if (busId == '') {
			alert("产品编号不能为空！");
			return false;
		}
		if(!(/^(\+|-)?\d+$/.test(busId))|| busId<0){ 
			 alert("产品编号请输入正整数！");
		        return false;  
		}  
		if(busId.length>38){
			alert("产品编号不能多于38位");
			return false;
		}
		var merchantId = $("#add_merchantId").val().trim();
		if (merchantId == '') {
			alert("所属商家不能为空！");
			return false;
		}
		if(merchantId.length>10){
			alert("所属商家不能多于10位");
			return false;
		}
		var btype = $("#add_btype").val().trim();
		if(btype==''){
			alert("产品种类名称不能为空");
			return false;
		}
		if(btype.length>20){
			alert("产品种类不能多于20位");
			return false;
		}
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addBusiness.action",
			data : "busId=" + busId + "&merchantId=" + merchantId
					+ "&btype=" + btype,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var busId = $("#busId").val();
					if(busId=="null"){
						busId = null;
					}
					$('#dlg').dialog('close');
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(busId));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updateBusiness").click(function() {
	var busId = $("#edit_busId").val().trim();
	if (busId == '') {
		alert("产品编号不能为空！");
		return false;
	}
	if(!(/^(\+|-)?\d+$/.test(busId))|| busId<0){ 
		 alert("产品编号请输入正整数！");
	        return false;  
	}  
	if(busId.length>38){
		alert("产品编号不能多于38位");
		return false;
	}
	var merchantId = $("#edit_merchantId").val().trim();
	if (merchantId == '') {
		alert("所属商家不能为空！");
		return false;
	}
	if(merchantId.length>10){
		alert("所属商家不能多于10位");
		return false;
	}
	var btype = $("#edit_btype").val().trim();
	if(btype==''){
		alert("产品种类名称不能为空");
		return false;
	}
	if(btype.length>20){
		alert("产品种类不能多于20位");
		return false;
	}
	var width = $(document).width();
	var height = $(document).height();
	$.ajax({
		url : "updateBusiness.action",
		async : false,
		data :"busId=" + busId + "&merchantId=" + merchantId
		+ "&btype=" + btype,
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			var busId = $("#busId").val();
			if(busId=="null"){
				busId = null;
			}
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 18,
					height : height - 20
				}).datagrid('loadData', getData(busId));
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
			$("#add_busId").val('');
			$("#add_merchantId").empty();
			$("add_btype").val('');
			$("#add_merchantId").append("<option value=''>请选择</option>");
			$.ajax({
				url : "getMerchant.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					var merchantId=data.merchantId.split(",");
					var merchantName=data.merchantName.split(",");
					for(var i=0;i<merchantId.length-1;i++){
						var option="<option value='"+merchantId[i]+"'>"+merchantName[i]+"</option>";
						$("#add_merchantId").append(option);
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
			var merchantId = "";
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择需要编辑的数据！");
				return false;
			} else if (rows.length > 1) {
				alert("一次只能编辑一行！");
				return false;
			} else {
				var row = $("#dg").datagrid('getSelected');
				
				$.ajax({ 
					url : "getMerchantType.action",
					async : false,
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						$("#edit_merchantId option").remove();
						for ( var i = 0; i < data.length; i++) {
							option = $("<option value='"+data[i].merchantId+"'>"
								+ data[i].merchantName
								+ "</option>");
							if(data[i].merchantName == row.merchantId){
								merchantId = merchantId + data[i].merchantId;
							}
							$("#edit_merchantId").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$('#edit-dialog').dialog('open');
				$("#edit_busId").val(row.busId);
				$("#edit_btype").val(row.btype);
				$("#edit_merchantId").val(merchantId);
				
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {

			var rows = $('#dg').datagrid('getSelections');
			if (window.confirm("确认删除这" + rows.length + "记录？")) {
				var busIds = '';
				for ( var i = 0; i < rows.length; i++) {
					busIds = busIds + "," + rows[i].busId;
					
				}
				var width = $(document).width();
				var height = $(document).height();
				var busId = $("#busId").val();
				if(busId=="null"){
					busId = null;
				}
				$.ajax({
					url : "removeBusiness.action",
					data : "busIds=" + busIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						if(busId=="null"){
							busId = null;
						}
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width : width - 18,
							height : height - 20
						}).datagrid('loadData', getData(busId));
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
	<div id="dlg" class="easyui-dialog" title="添加产品种类信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 260px; padding: 10px; background: #DFE8F6;">
		<form action="addBusinesst.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
			<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">产品编号：<input
						type="text" id="add_busId" name="busId" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">所属商家：<select
						id="add_merchantId" style="width: 155px;" name="merchantId"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">产品名称：<input
						type="text" id="add_btype" name="btype" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				</ul>
			</div>
		</form>
		<div align="center">
			<input type="button" value="添加" id="addBusiness" />
		</div>
	</div>	
<div id="edit-dialog" class="easyui-dialog" title="编辑产品种类信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 260px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">产品编号：<input
						type="text" id="edit_busId" disabled="true" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">所属商家：<select
						id="edit_merchantId" style="width: 155px;" ></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">产品名称：<input
						type="text" id="edit_btype" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				</ul>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateBusiness" />
		</div>
	</div>
	<div id="selectBusiness" class="easyui-dialog" title="产品种类"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 480px; padding: 10px; background: #DFE8F6;">
		<div id="busi" class="easyui-panel" title="产品种类树"
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

