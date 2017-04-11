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
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
	String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	var zongshu;
	var shengyu;
	var toolbar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$('#dlg').dialog('open');
			$("#add_name").val('');
			$("#add_amount").val('');
			$("#add_leftAmount").val('');
			$("#add_sendWay").val('');
			$("#add_sendScore").val('');
			$("#add_imgName").val('');
			$("#add_img1").val('');
			$("#add_img2").val('');
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
				$('#edit-dialog').dialog('open');
				$("#edit_prizeId").val(row.prizeId);
				$("#edit_name").val(row.name);
				$("#edit_amount").val(row.amount);
				$("#edit_leftAmount").val(row.leftAmount);
				$("#edit_sendWay").val(row.sendWay);
				$("#edit_sendScore").val(row.sendScore);
				$("#edit_imgName").val(row.imgName);
				$("#edit_describe").val(row.describe);
				$("#edit_addTime").val(row.addTime);
				$("#edit_begin").val(row.begin);
				$("#edit_end").val(row.end);
				$('#edit_amount').attr({readonly:false});
				zongshu=row.amount;
				shengyu=row.leftAmount;
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if(window.confirm("确认删除这"+rows.length+"记录？")){
				var prizeIds = '';
				for(var i=0;i<rows.length;i++){
					prizeIds = prizeIds + ","+rows[i].prizeId;
				}
				$.ajax({
					url : "deletePrize.action",
					data : "prizeIds="+ prizeIds,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						hasLeft = $("#hasLeft").is('checked');
						$('#dg').datagrid({
							loadFilter : pagerFilter,
						}).datagrid('loadData',	getData(hasLeft));
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
	$(function() {
		var width = $(document).width();
		var height = $(document).height();
		var hasLeft = $("#hasLeft").is('checked');
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 120
		}).datagrid('loadData',	getData(hasLeft));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var hasLeft = $("#hasLeft").is(':checked');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 120
			}).datagrid('loadData',getData(hasLeft));
		});
		$("#searchAll").click(function() {
			var hasLeft = false;
			$("#hasLeft").attr("checked",false);
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 120
			}).datagrid('loadData',getData(hasLeft));
		});
		
		$("#addPrize").click(function() {
					var name = $("#add_name").val().trim();
					var amount = $("#add_amount").val().trim();
					var leftAmount = $("#add_leftAmount").val().trim();
					var sendScore = $("#add_sendScore").val().trim();
					if (name == '') {
						alert('请输入奖品名称！');
						return false;
					}
					if (amount == '') {
						alert('请输入奖品总数！');
						return false;
					}
					if(amount.length>0){
						var reg=/^-?\d+$/;
						if(!reg.test(amount)){
							alert("奖品总数请输入整数");
							return false;
						}else if(parseInt(amount)<=0){
							alert("奖品总数请输入大于0的整数");
							return false;
						}
					}
					if(leftAmount == ''){
						alert('请输入剩余数量！');
						return false;
					}
					if(leftAmount.length>0){
						var reg=/^-?\d+$/;
						if(!reg.test(leftAmount)){
							alert("剩余数量请输入整数");
							return false;
						}else if(parseInt(leftAmount)<0){
							alert("剩余数量请输入大于0的整数");
							return false;
						}
					}
					if(parseInt(leftAmount)>parseInt(amount)){
						alert("剩余数量不能大于奖品总数");
						return false;
					}
					if(sendScore.length>0){
						var reg=/^-?\d+$/;
						if(!reg.test(sendScore)){
							alert("赠送积分请输入整数");
							return false;
						}
					}
					var options = {
							dataType : "json",
							error : function() {
								alert("后台错误！");
							},
							success : function(data) { 
								alert(data.respInfo);
								if (data.respCode == 0) {
									$("#dlg").dialog("close");
									hasLeft = $("#hasLeft").is('checked');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
									}).datagrid('loadData', getData(hasLeft));
								}
							}
						};
					$("#addForm").ajaxSubmit(options);
					
				});
		
		$("#updatePrize").click(function() {
					var name = $("#edit_name").val().trim();
					var amount = $("#edit_amount").val().trim();
					var leftAmount = $("#edit_leftAmount").val().trim();
					var sendScore = $("#edit_sendScore").val().trim();
					if (name == '') {
						alert('请输入奖品名称！');
						return false;
					}
					if (amount == '') {
						alert('请输入奖品总数！');
						return false;
					}
					if(amount.length>0){
						var reg=/^-?\d+$/;
						if(!reg.test(amount)){
							alert("奖品总数请输入整数");
							return false;
						}else if(parseInt(amount)<=0){
							alert("奖品总数请输入大于0的整数");
							return false;
						}
					}
					if(leftAmount == ''){
						alert('请输入剩余数量！');
						return false;
					}
					if(leftAmount.length>0){
						var reg=/^-?\d+$/;
						if(!reg.test(leftAmount)){
							alert("剩余数量请输入整数");
							return false;
						}else if(parseInt(leftAmount)<=0){
							alert("剩余数量请输入大于0的整数");
							return false;
						}
					}
					if(parseInt(leftAmount)>parseInt(amount)){
						alert("剩余数量不能大于奖品总数");
						return false;
					}
					if(sendScore.length>0){
						var reg=/^-?\d+$/;
						if(!reg.test(sendScore)){
							alert("赠送积分请输入整数");
							return false;
						}
					}
					var options = {
							dataType : "json",
							error : function() {
								alert("后台错误！");
							},
							success : function(data) { 
								alert(data.respInfo);
								if (data.respCode == 0) {
									$("#edit-dialog").dialog("close");
									hasLeft = $("#hasLeft").is('checked');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
									}).datagrid('loadData', getData(hasLeft));
								}
							}
						};
						$("#updateForm").ajaxSubmit(options);
					
				});
		$("#edit_amount").blur(function(){
			var amount = $("#edit_amount").val().trim();
			var reg=/^-?\d+$/;
			if(reg.test(amount)&&parseInt(amount)>0&&amount!=zongshu){
				var cha=parseInt(amount)-parseInt(zongshu);
				var b=parseInt(shengyu)+cha;
				if(b>=0){
					$("#edit_leftAmount").val(b);
					$('#edit_amount').attr({readonly:true});
				}else if(b<0){
					alert("奖品总数减少量不能大于剩余数量");
					$("#edit_amount").val(zongshu);
				}
				
			}
		});
			
		
		
	});
	
	function getData(hasLeft) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getPrize.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&hasLeft=" + hasLeft,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==-1){
					alert(data.respInfo);
					return false;
				}else{
					rows = data;
				}
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
			var hasLeft = $("#hasLeft").is('checked');
			var newData = getData(hasLeft);
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
</script>
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
#div1 td{
	height: 35px;
}
#div2 td{
	height: 35px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="抽奖奖品信息查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;"><input type="checkbox" id="hasLeft">奖品未送完</td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="searchAll" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>所有记录</b>
			</a></td>
			</tr>
		</table>
	</div>

	<table id="dg" class="easyui-datagrid" title="抽奖奖品列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'prizeId',width:80">id</th> -->
				<th data-options="field:'name',width:80">奖品名称</th>
				<th data-options="field:'amount',width:80,hide:'true'">奖品总数</th>
				<th data-options="field:'leftAmount',width:80,sortable:true">剩余数量</th>
				<th data-options="field:'sendWay',width:150,sortable:true">奖品分发方式</th>
				<th data-options="field:'sendScore',width:100,sortable:true">赠送积分</th>
				<th data-options="field:'addTime',width:100,sortable:true">添加时间</th>
				<th data-options="field:'imgName',width:100">图片名称</th>
				<!-- th data-options="field:'begin',width:80,sortable:true">第一次抽奖</th>
				<th data-options="field:'end',width:80,sortable:true">最后一次抽奖</th> -->
				<th data-options="field:'describe',width:150,sortable:true">奖品描述</th>
								
			
			</tr>
		</thead>
	</table>
	
	<div id="dlg" class="easyui-dialog" title="添加奖品"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 400px; height: 480px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;">
		<form action="addPrize.action" method="post" enctype="multipart/form-data" id="addForm">
			<table align="center">
				<tr id="tr1">
					<td>奖品名称：</td>
					<td><input type="text" id="add_name" name="name" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>奖品总数：</td>
					<td><input type="text" id="add_amount" name="amount" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>剩余数量：</td>
					<td><input type="text" id="add_leftAmount" name="leftAmount" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>奖品分发方式：</td>
					<td><input type="text" id="add_sendWay" name="sendWay" style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>赠送积分：</td>
					<td><input type="text" id="add_sendScore" name="sendScore" style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>图片名称：</td>
					<td><input type="text" id="add_imgName" name="imgName" style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>图片1：</td>
					<td><input type="file" id="add_img1" name="img1" style="width: 180px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>图片2：</td>
					<td><input type="file" id="add_img2" name="img2" style="width: 180px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>奖品描述：</td>
					<td><textarea rows="3" cols="19" id="add_describe" name="describe" style="width:180px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center">
			<input type="button" value="添加" id="addPrize" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改奖品信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 400px; height: 480px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;" >
		<form action="updatePrize.action" method="post" enctype="multipart/form-data" id="updateForm">
			<input type="hidden" id="edit_prizeId" name="prizeId" />
			<input type="hidden" id="edit_addTime" name="addTime" />
			<input type="hidden" id="edit_begin" name="begin"/>
			<input type="hidden" id="edit_end" name="end"/>
			<table align="center">
				<tr id="tr2">
					<td>奖品名称：</td>
					<td><input type="text" id="edit_name" name="name" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>奖品总数：</td>
					<td><input type="text" id="edit_amount" name="amount" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>剩余数量：</td>
					<td><input type="text" id="edit_leftAmount" name="leftAmount" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>奖品分发方式：</td>
					<td><input type="text" id="edit_sendWay" name="sendWay"  style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>赠送积分：</td>
					<td><input type="text" id="edit_sendScore" name="sendScore" style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>图片名称：</td>
					<td><input type="text" id="edit_imgName" name="imgName" style="width:180px;"/></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>图片1：</td>
					<td><input type="file" id="edit_img1" name="img1" style="width: 180px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>图片2：</td>
					<td><input type="file" id="edit_img2" name="img2" style="width: 180px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>奖品描述：</td>
					<td><textarea rows="3" cols="19" id="edit_describe" name="describe" style="width:180px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updatePrize" />
		</div>
	</div>
</body>
</html>