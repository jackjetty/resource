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
		title="奖品验证码查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>奖品名称：</td>
				<td><select id="prizeId" style="width: 115px; height: 23px;">
				</select></td>
				<td>验证码状态：</td>
				<td><select id="status" style="width: 95px; height: 23px;">
					<option value="notSend" id="notSend">未送出</option>
					<option value="hasBeenSend">已送出</option>
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
	<table id="dg" class="easyui-datagrid" title="奖品验证码管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th data-options="field:'prizeName',width:100">奖品名称</th>
				<th data-options="field:'code',width:100">验证码</th>
				<th data-options="field:'effectiveTime',width:100">有效时间至</th>
				<th data-options="field:'sended2',width:100">当前状态</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData(prizeId,status) {
		if(prizeId =="null"){
			prizeId = "";
		}
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getPrizeCode.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+  "&prizeId=" + prizeId + "&status=" + status,
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
			var prizeId = $("#prizeId").val();
			var status = $("#status").val();
			var newData = getData(prizeId,status);
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
			url : "getPrizeInfo.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				option = $("<option id='number1' value='null'>请选择抽奖奖品</option>");
				$("#prizeId").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].prizeId+"'>"+data[i].name+"</option>");
					$("#prizeId").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		var width = $(document).width();
		var height = $(document).height();
		var prizeId = $("#prizeId").val();
		var status = $("#status").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid('loadData',	getData(prizeId,status));
		$("#searchpanel").panel({
			width : width - 18
		});
		
		$("#clear").click(function(){
			$("#prizeId").val("null");	
			$("#status").val("notSend");
		});
		$("#search").click(function() {
			var prizeId = $("#prizeId").val();
			var status = $("#status").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1
			}).datagrid('loadData',	getData(prizeId,status));
		});
	
	
		$("#import").click(function() {
			var prizeId = $("#add_prizeId").val();
			var excel = $("#add_excel").val();
			if(prizeId =="null"){
				alert("请选择对应的奖品！");
				alert(excel);
				return false;
			}
			if(excel==""){
				alert("请选择excel文件!");
				return false;
			}else{
				var tts = excel.split('.');
				if(tts[tts.length-1]!='xls'){
					alert("当前只支持.xls文件上传！请转换之后再上传。");
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
							var prizeId = $("#prizeId").val();
							var status = $("#status").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								width : width - 18,
								height : height - 120
							}).datagrid('loadData',	getData(prizeId,status));
						}
					}
				};
				$("#addForm").ajaxSubmit(options);
		});
	});

	var toolbar = [ {
		text : '导入',
		iconCls : 'icon-add',
		handler : function() {
			$('#dlg').dialog('open');
			$.ajax({
				url : "getPrizeInfo.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					option = $("<option id='number1' value='null'>请选择抽奖奖品</option>");
					$("#add_prizeId").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].prizeId+"'>"+data[i].name+"</option>");
						$("#add_prizeId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==0){
				alert("请选择需要删除的记录！");
				return false;
			}
			if (window.confirm("确认删除这" + rows.length + "记录？")) {
				var Ids = '';
				for ( var i = 0; i < rows.length; i++) {
					Ids = Ids + "," + rows[i].id;
				}
				prizeId = $("#prizeId").val();
				status = $("#status").val();
				var width = $(document).width();
				var height = $(document).height();
				$.ajax({
					url : "removePrizeCode.action",
					data : "Ids=" + Ids,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width : width - 18,
							height : height - 20
						}).datagrid('loadData', getData(prizeId,status));
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
	<div id="dlg" class="easyui-dialog" title="奖品验证码导入"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 260px; padding: 10px; background: #DFE8F6;">
		<form action="importFromFile.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
				
			<table align="center">
				<tr height="60px;">
					<td>对应奖品：</td>
					<td><select id="add_prizeId" name="prizeId" style="width: 180px; height: 23px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr height="60px;">
					<td>选择文件：</td>
					<td><input type="file" id="add_excel" name="excel" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</form>
		<div align="center">
			<input type="button" value="导入" id="import" />
		</div>
	</div>	
	<button id="fresh" style="display: none;"></button>
</body>
</html>

