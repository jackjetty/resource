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
				<td>产品面额：</td>
				<td><input type="text" id="cost" style="width: 95px;" /></td>
				<td>产品种类：</td>
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
	<table id="dg" class="easyui-datagrid" title="产品管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th id="productId"data-options="field:'productId',width:100">产品代码</th>
				<th data-options="field:'productName',width:100">产品名称</th>
				<th data-options="field:'busId',width:100">产品种类</th>
				<th data-options="field:'merchantId',width:100">所属商家</th>
				<th data-options="field:'cost',width:100,align:'center'">产品面额</th>
				<th data-options="field:'productDescribe',width:100">产品说明</th>
				<th data-options="field:'SPID',width:100,align:'center'">服务控制</th>
				<th data-options="field:'status',width:100">能否充值</th>
				<th data-options="field:'sendScore',width:100">赠送积分</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	
	function getData(busId,cost) {
		if(busId==null){
			busId = -1;
		}
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getProductInfo.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&busId=" + busId + "&cost=" + cost,
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
			var cost = $("#cost").val().trim();
			var busId = $("#busId").val();
			if(busId =="null"){
				busId = null;
			}
			var newData = getData(busId,cost);
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
		var cost = $("#cost").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(busId,cost));
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
		$("#search").click(function() {
					var busId = $("#busId").val();
					var cost = $("#cost").val().trim();
					if(cost !=""){
					if(!(/^(\+|-)?\d+$/.test(cost))|| cost<0){ 
						 alert("产品面额请输入正整数！");
					        return false;  
					}
					if(cost.length>8){
						alert("产品面额不能多于8位");
						return false;
					}
					}
					if(busId=="null"){
						busId = null;
					}
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid(
							'loadData',
							getData(busId,cost));
				});
		$("#clear").click(function(){
			$("#busid1").attr('selected',true);
			$("#cost").val('');
			
		});
	
	$("#addProduct").click(function() {
		var productId = $("#add_productId").val().trim();
		if (productId == '') {
			alert("产品代码不能为空！");
			return false;
		}
		if(productId.length>10){
			alert("产品代码不能多于10位");
			return false;
		}
		var productName = $("#add_productName").val().trim();
		if (productName == '') {
			alert("产品名称不能为空！");
			return false;
		}
		if(productName.length>30){
			alert("产品名称不能多于30位！");
			return false;
		}
		var busId = $("#add_busId").val();
		if(busId == ''){
			alert("产品种类不能为空");
			return fasle;
		}
		var merchantId = $("#add_merchantId").val().trim();
		if (merchantId == '') {
			alert("所属商家不能为空！");
			return false;
		}
		var cost = $("#add_cost").val().trim();
		if (cost == '') {
			alert("产品面额不能为空！");
			return false;
		}
		if(cost.length>8){
			alert("产品面额不能多于8位");
			return false;
		}
		if(!(/^(\+|-)?\d+$/.test(cost))|| cost<0){ 
			 alert("产品面额请输入正整数！");
		        return false;  
		}  
		var status = $("#add_status").val().trim();
		if (status == '') {
			alert("能否有效不能为空！");
			return false;
		}
		var SPID = $("#add_SPID").val().trim();
		if(SPID.length >10){
			alert("服务控制不能多于10位！");
			return false;
		}
		var sendScore = $("#add_sendScore").val().trim();
		if(sendScore !=''){
			if(!(/^(\+|-)?\d+$/.test(sendScore))|| sendScore<0){ 
				 alert("赠送积分请输入正整数！");
			        return false;  
			}  
		}	
		var productDescribe = $("#add_productDescribe").val().trim();
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addProduct.action",
			data : "productId=" + productId + "&productName=" + productName
					+ "&busId=" + busId + "&merchantId=" + merchantId 
					+ "&productDescribe=" + productDescribe
					+ "&cost=" + cost+ "&SPID=" + SPID+ "&status=" + status
					+ "&sendScore=" + sendScore,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var busId = $("#busId").val();
					var cost = $("#cost").val().trim();
					if(busId=="null"){
						busId = null;
					}
					$('#dlg').dialog('close');
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(busId,cost));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updateProduct").click(function() {
	var productId = $("#edit_productId").val().trim();
	if (productId == '') {
		alert("产品代码不能为空！");
		return false;
	}
	if(productId.length>10){
		alert("产品代码不能多于10位");
		return false;
	}
	var productName = $("#edit_productName").val().trim();
	if (productName == '') {
		alert("产品名称不能为空！");
		return false;
	}
	if(productName.length>30){
		alert("产品名称不能多于30位！");
		return false;
	}
	var busId = $("#edit_busId").val();
	if(busId == ''){
		alert("产品种类不能为空");
		return fasle;
	}
	var merchantId = $("#edit_merchantId").val().trim();
	if (merchantId == '') {
		alert("所属商家不能为空！");
		return false;
	}
	var cost = $("#edit_cost").val().trim();
	if (cost == '') {
		alert("产品面额不能为空！");
		return false;
	}
	if(cost.length>8){
		alert("产品面额不能多于8位");
		return false;
	}
	if(!(/^(\+|-)?\d+$/.test(cost))|| cost<0){ 
		 alert("产品面额请输入正整数！");
	        return false;  
	}
	var sendScore = $("#edit_sendScore").val().trim();
	if(sendScore !=''){
		if(!(/^(\+|-)?\d+$/.test(sendScore))|| sendScore<0){ 
			 alert("产品面额请输入正整数！");
		        return false;  
		}
	}
	
	var status = $("#edit_status").val().trim();
	if (status == '') {
		alert("能否有效不能为空！");
		return false;
	}
	var SPID = $("#edit_SPID").val().trim();
	if(SPID.length >10){
		alert("服务控制不能多于10位！");
		return false;
	}
	var productDescribe = $("#edit_productDescribe").val().trim();
	var width = $(document).width();
	var height = $(document).height();
	$.ajax({
		url : "updateProduct.action",
		async : false,
		data :"productId=" + productId + "&productName=" + productName
		+ "&busId=" + busId + "&merchantId=" + merchantId 
		+ "&productDescribe=" + productDescribe
		+ "&cost=" + cost+ "&SPID=" + SPID+ "&status=" + status
		+ "&sendScore=" + sendScore,
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			var busId = $("#busId").val();
			var cost = $("#cost").val().trim();
			if(busId=="null"){
				busId = null;
			}
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 18,
					height : height - 20
				}).datagrid('loadData', getData(busId,cost));
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
			$("#add_productId").val('');
			$("#add_productName").val('');
			$("#add_merchantId").empty();
			$("#add_status").val('');
			$("#add_cost").val('');
			$("#add_busId").empty();
			$("#add_SPID").val('');
			$("#add_productDescribe").val('');
			$("#add_sendScore").val('');
			$.ajax({
				url : "getBusinessType.action",
				async : false,
				type : "post",
				data : "",
				dataType : "json",
				success : function(data) {
					var option = $("<option value=''>请选择</option>");
					$("#add_busId").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].busId+"'>"+data[i].btype+"</option>");
						$("#add_busId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
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
			var busId="";
			var row = $("#dg").datagrid('getSelected');
			var rows = $('#dg').datagrid('getSelections');
			var merchantId = "";
			if (rows.length == 0) {
				alert("请选择需要编辑的数据！");
				return false;
			} else if (rows.length > 1) {
				alert("一次只能编辑一行！");
				return false;
			} else {
				$.ajax({
				url : "getBusinessType.action",
				async : false,
				type : "post",
				data : "",
				dataType : "json",
				success : function(data) {
					$("#edit_busId").empty();
					for ( var i = 0; i < data.length; i++) {
						var option ="<option value='"+data[i].busId+"'>"+data[i].btype+"</option>";
						var aa=row.busId;
						if(data[i].btype == aa){
							busId = data[i].busId;
						}
						$("#edit_busId").append(option);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			
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
								merchantId = merchantId+data[i].merchantId;
							
							}
							$("#edit_merchantId").append(option);
							
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$('#edit-dialog').dialog('open');
				$("#edit_productId").val(row.productId);
				$("#edit_productName").val(row.productName);
				$("#edit_busId").val(busId);
				$("#edit_productDescribe").val(row.productDescribe);
				$("#edit_cost").val(row.cost);
				$("#edit_SPID").val(row.SPID);
				$("#edit_sendScore").val(row.sendScore);
				$("#edit_status").val(row.status);
				$("#edit_merchantId").val(merchantId);
			}
		}
	}];

	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	</script>
	<div id="dlg" class="easyui-dialog" title="添加产品信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 430px; padding: 10px; background: #DFE8F6;">
		<form action="addProduct.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
			<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">产品代码：<input
						type="text" id="add_productId" name="productId" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品名称：<input
						type="text" id="add_productName" name="productName" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品种类：<select
						id="add_busId" style="width: 155px;" name="busId"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				<li style="line-height: 40px; height: 40px;">所属商家：<select
						id="add_merchantId" style="width: 155px;" name="merchantId"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				<li style="line-height: 40px; height: 40px;">产品面额：<input
						type="text" id="add_cost" name="cost" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">能否有效：<select 
						id="add_status" style="width: 155px;" name="status">
						<option value="">请选择</option>
						<option value="有效">有效</option>
						<option value="无效">无效</option></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品说明：<input
						type="text" id="add_productDescribe" name="productDescribe" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				<li style="line-height: 40px; height: 40px;">服务控制：<input
						type="text" id="add_SPID" name="SPID" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				<li style="line-height: 40px; height: 40px;">赠送积分：<input
						type="text" id="add_sendScore" name="sendScore" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				</ul>
			</div>
		</form>
		<div align="center">
			<input type="button" value="添加" id="addProduct" />
		</div>
	</div>	
<div id="edit-dialog" class="easyui-dialog" title="编辑产品信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 430px; padding: 10px; background: #DFE8F6;">
		<div>
			<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">产品代码：<input
						type="text" id="edit_productId" disabled="true"/><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品名称：<input
						type="text" id="edit_productName" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品种类：<select
						id="edit_busId" style="width: 155px;"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				<li style="line-height: 40px; height: 40px;">所属商家：<select
						id="edit_merchantId" style="width: 155px;"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				<li style="line-height: 40px; height: 40px;">产品面额：<input
						type="text" id="edit_cost" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">能否有效：<select 
						id="edit_status" style="width: 155px;">
						<option value="有效">有效</option>
						<option value="无效">无效</option></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品说明：<input
						type="text" id="edit_productDescribe" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				<li style="line-height: 40px; height: 40px;">服务控制：<input
						type="text" id="edit_SPID" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				<li style="line-height: 40px; height: 40px;">赠送积分：<input
						type="text" id="edit_sendScore" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				</ul>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateProduct" />
		</div>
	</div>
	<div id="selectProduct" class="easyui-dialog" title="产品"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 320px; height: 480px; padding: 10px; background: #DFE8F6;">
		<div id="proc" class="easyui-panel" title="产品树"
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

