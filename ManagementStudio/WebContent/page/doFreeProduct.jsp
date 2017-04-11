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
		title="免费产品查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>产品面额：</td>
				<td><input type="text" id="cost" style="width: 95px;" /></td>
				<td>业务种类：</td>
				<td><select id="busId" style="width: 95px;"></select></td>
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
	<table id="dg" class="easyui-datagrid" title="免费产品管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'freeProductId',width:100">产品代码</th>
				<th data-options="field:'productName',width:100">产品名称</th>
				<th data-options="field:'busDetail',width:100">产品种类</th>
				<th data-options="field:'merchantDetail',width:100">所属商家</th>
				<th data-options="field:'cost',width:100,align:'center'">产品面额</th>
				<th data-options="field:'productDescribe',width:100">产品说明</th>
				<th data-options="field:'SPID',width:100,align:'center'">服务控制</th>
				<th data-options="field:'status',width:100">能否充值</th>
				<th data-options="field:'interfaceAddress',width:100">接口地址</th>
				<th data-options="field:'interfaceParameter',width:100">接口参数</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	
	function getData(busId,cost) {
		if(busId=='null' || busId == null){
			busId = '';
		}
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getFreeProduct.action",
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
			var busId = $("#busId").val().trim();
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
		$.ajax({
			url : "getBusinessType.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option value='null'></option>");
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
		$("#search").click(function() {
					var busId = $("#busId").val().trim();
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
			$("#busId").val('');
			$("#cost").val('');
			
		});
	
	$("#addFreeProduct").click(function() {
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
		if(!(/^(\+|-)?\d+$/.test(busId))|| busId<0){ 
			 alert("产品种类请输入正整数！");
		        return false;  
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
		var SPID = $("#add_SPID").val().trim();
		if(SPID ==''){
			alert("服务控制不能为空！");
			return false;
		}
		if(SPID.length >10){
			alert("服务控制不能多于10位！");
			return false;
		}
		var status = $("#add_status").val().trim();
		if (status == '') {
			alert("能否有效不能为空！");
			return false;
		}
		var interfaceParameter = $("#add_interfaceParameter").val().trim();
		  if(interfaceParameter ==''){
			  alert("请求参数不能为空");
			  return false;
		  }
		  var interfaceAddress = $("#add_interfaceAddress").val().trim();
		  if(interfaceAddress ==''){
			  alert("请求地址不能为空");
			  return false;
		  }
		var productDescribe = $("#add_productDescribe").val().trim();
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addFreeProduct.action",
			data : "productId=" + productId + "&productName=" + productName
					+ "&busId=" + busId + "&merchantId=" + merchantId 
					+ "&productDescribe=" + productDescribe
					+ "&cost=" + cost+ "&SPID=" + SPID+ "&status=" + status
					+ "&interfaceParameter=" + interfaceParameter
					+ "&interfaceAddress=" + interfaceAddress,
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

$("#updateFreeProduct").click(function() {
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
	
	if(!(/^(\+|-)?\d+$/.test(busId))|| busId<0){ 
		 alert("产品种类请输入正整数！");
	        return false;  
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
	var status = $("#edit_status").val().trim();
	if (status == '') {
		alert("能否有效不能为空！");
		return false;
	}
	var SPID = $("#edit_SPID").val().trim();
	if(SPID ==''){
		alert("服务控制不能为空！");
		return false;
	}
	if(SPID.length >10){
		alert("服务控制不能多于10位！");
		return false;
	}
	var interfaceParameter = $("#edit_interfaceParameter").val().trim();
	  if(interfaceParameter ==''){
		  alert("请求参数不能为空");
		  return false;
	  }
	  var interfaceAddress = $("#edit_interfaceAddress").val().trim();
	  if(interfaceAddress ==''){
		  alert("请求地址不能为空");
		  return false;
	  }
	var productDescribe = $("#edit_productDescribe").val().trim();
	var width = $(document).width();
	var height = $(document).height();
	$.ajax({
		url : "updateFreeProduct.action",
		async : false,
		data :"productId=" + productId + "&productName=" + productName
		+ "&busId=" + busId + "&merchantId=" + merchantId 
		+ "&productDescribe=" + productDescribe
		+ "&cost=" + cost+ "&SPID=" + SPID+ "&status=" + status
		+ "&interfaceParameter=" + interfaceParameter
		+ "&interfaceAddress=" + interfaceAddress,
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			var busId = $("#busId").val().trim();
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
			$("#add_productId").val("");
			$("#add_productName").val('');
			$("#add_merchantId").html("");
			$("#add_status").val('');
			$("#add_cost").val('');
			$("#add_busId").html("");
			$("#add_SPID").val('');
			$("#add_productDescribe").val('');
			$("#add_interfaceParameter").val('');
			$("#add_interfaceAddress").val('');
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
			var rows = $('#dg').datagrid('getSelections');
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
						$("#edit_busId").html('');
						for ( var i = 0; i < data.length; i++) {
							var option ="<option value='"+data[i].busId+"'>"+data[i].btype+"</option>";
							$("#edit_busId").append(option);
						}
						$("#edit_busId").val(rows[0].busId);
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$.ajax({ 
					url : "getMerchantType.action",
					async : false,
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						$("#edit_merchantId").html("");
						for ( var i = 0; i < data.length; i++) {
							option = $("<option value='"+data[i].merchantId+"'>"
								+ data[i].merchantName
								+ "</option>");
							$("#edit_merchantId").append(option);
							
						}
						$("#edit_merchantId").val(rows[0].merchantId);
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				var row = $("#dg").datagrid('getSelected');
				$('#edit-dialog').dialog('open');
				$("#edit_productId").val(row.freeProductId);
				$("#edit_productName").val(row.productName);
				$("#edit_busId").val(row.busId);
				$("#edit_productDescribe").val(row.productDescribe);
				$("#edit_cost").val(row.cost);
				$("#edit_SPID").val(row.SPID);
				$("#edit_interfaceAddress").val(row.interfaceAddress);
				$("#edit_interfaceParameter").val(row.interfaceParameter);
				$("#edit_status").val(row.status);
				$("#edit_merchantId").val(row.merchantId);
			}
		}
	}, '-', { 
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if(window.confirm("确认删除这"+rows.length+"记录？")){
				var productIds = '';
				for(var i=0;i<rows.length;i++){
					productIds = productIds + ","+rows[i].freeProductId;
				}
				$.ajax({
					url : "removeFreeProduct.action",
					data : "productIds="+ productIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						var busId = $("#busId").val().trim();
						var cost = $("#cost").val().trim();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
						}).datagrid('loadData',	getData(busId,cost));
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
	<div id="dlg" class="easyui-dialog" title="添加免费产品信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 430px; padding: 10px; background: #DFE8F6;">
		<form action="addFreeProduct.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
			<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">产品代码：<input
						type="text" id="add_productId" name="productId" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品名称：<input
						type="text" id="add_productName" name="productName" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">所属商家：<select
						id="add_merchantId" style="width: 155px;" name="merchantId"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">产品种类：<select
						id="add_busId" style="width: 155px;" name="busId"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>		
				<li style="line-height: 40px; height: 40px;">产品面额：<input
						type="text" id="add_cost" name="cost" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">服务控制：<input
						type="text" id="add_SPID" name="SPID" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">是否有效：<select 
						id="add_status" style="width: 155px;" name="status">
						<option value="">请选择</option>
						<option value="有效">有效</option>
						<option value="无效">无效</option></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">接口地址：<input
						type="text" id="add_interfaceAddress" name="interfaceAddress" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">接口参数：<input
						type="text" id="add_interfaceParameter" name="interfaceParameter" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品说明：<input
						type="text" id="add_productDescribe" name="productDescribe" /><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				</ul>
			</div>
		</form>
		<div align="center">
			<input type="button" value="添加" id="addFreeProduct" />
		</div>
	</div>	
<div id="edit-dialog" class="easyui-dialog" title="编辑免费产品信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 430px; padding: 10px; background: #DFE8F6;">
		<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">产品代码：<input
						type="text" id="edit_productId"disabled="true" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品名称：<input
						type="text" id="edit_productName" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">所属商家：<select
						id="edit_merchantId" style="width: 155px;"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品种类：<select
						id="edit_busId" style="width: 155px;"></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>				
				<li style="line-height: 40px; height: 40px;">产品面额：<input
						type="text" id="edit_cost" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>	
				<li style="line-height: 40px; height: 40px;">服务控制：<input
						type="text" id="edit_SPID"  /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">是否有效：<select 
						id="edit_status" style="width: 155px;">
						<option value="">请选择</option>
						<option value="有效">有效</option>
						<option value="无效">无效</option></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">接口地址：<input
						type="text" id="edit_interfaceAddress" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">接口参数：<input
						type="text" id="edit_interfaceParameter"  /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">产品说明：<input
						type="text" id="edit_productDescribe"/><font
						 color="black">&nbsp;&nbsp;*&nbsp;&nbsp;选填</font></li>
				</ul>
			</div>
		<div align="center">
			<input type="button" value="更新" id="updateFreeProduct" />
		</div>
	</div>
</body>
</html>

