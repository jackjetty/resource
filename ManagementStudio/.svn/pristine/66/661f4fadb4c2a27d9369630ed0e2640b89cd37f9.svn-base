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
	<input type="hidden" id="editPhoneRound" />
	<table id="dg" class="easyui-datagrid" title="电信手机号段管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<!-- th data-options="field:'ck',checkbox:true"></th> -->
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th id="productId"data-options="field:'phoneRound',width:100">手机号段</th>
				<th data-options="field:'status',align:'center',width:150,formatter:statusFormatter,
						editor:{
							type:'combobox',
							options:{
								valueField:'status',
								textField:'statusName',
								data:Status,
								required:true
							}
						}">状态信息</th>
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
				url : "getPhoneRoundInfo.action",
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
		$("#addPhoneRound").click(function() {
			var phoneRound = $("#add_phoneRound").val().trim();
			if (phoneRound == '') {
				alert("手机号段不能为空！");
				return false;
			}
			if(phoneRound.length>20){
				alert("手机号段不能多于20位");
				return false;
			}
			var status = $("#add_status").val().trim();
			if (status == '') {
				alert("状态信息不能为空！");
				return false;
			}
			var width = $(document).width();
			var height = $(document).height();
			$.ajax({
				url : "addPhoneRound.action",
				data : "phoneRound=" + phoneRound + "&status=" + status,
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
		});

		var toolbar = [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				var dg = $("#dg");
				var opts = dg.datagrid('options');
				for ( var i = 0; i < opts.pageSize; i++) {
					$('#dg').datagrid('endEdit', i);
				}
				var rows = $('#dg').datagrid('getChanges');
				if (rows.length == 0) {
					alert("未修改任何数据!");
					return false;
				}
				var StatusJSON;
				StatusJSON = "[";
				for ( var i = 0; i < rows.length; i++) {
					StatusJSON += "{";
					StatusJSON += "'status':'" + rows[i].status + "',";
					StatusJSON += "'id':'" + rows[i].id + "'},";
				}
				StatusJSON += "]";
				$.ajax({
					url : "updatePhoneRound.action",
					data : "StatusJSON=" + StatusJSON,
					dataType : "json",
					type : "post",
					success : function(data) {
						if (data.success) {
							$.messager.alert('状态管理', '修改成功!', 'info');
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}

				});
				$('#dg').datagrid('acceptChanges');
			}
		}, '-', {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$("#dlg").dialog("open");
				$("#add_phoneRound").val('');
				$("#add_status").val('');
			}
		}];
		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 18,
				height : height - 20,
				onDblClickCell : function(rowIndex, field, value) {
					var dg = $("#dg");
					var opts = dg.datagrid('options');
					for ( var i = 0; i < opts.pageSize; i++) {
						if (i != rowIndex) {
							$('#dg').datagrid('endEdit', i);
						}
					}
					$('#dg').datagrid('beginEdit', rowIndex);
				},
				onClickRow : function() {
					var dg = $("#dg");
					var opts = dg.datagrid('options');
					for ( var i = 0; i < opts.pageSize; i++) {
						$('#dg').datagrid('endEdit', i);
					}
				}
			}).datagrid('loadData', getData());
			$("#searchpanel").panel({
				width : width - 18
			});
		});

		
		var Status = [ {
			status : '有效',
			statusName : '有效'
		}, {
			status : '无效',
			statusName : '无效'
		} ];
		function statusFormatter(value) {
			for ( var i = 0; i < Status.length; i++) {
				if (Status[i].status == value)
					return Status[i].statusName;
			}
		}
		String.prototype.trim = function() {
			  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
			};
	</script>
	<div id="dlg" class="easyui-dialog" title="添加电信手机号段信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 410px; height: 330px; padding: 10px; background: #DFE8F6;">
		<form action="addPhoneRound.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
			<div>
				<ul style="list-style: none;">
				<li style="line-height: 40px; height: 40px;">手机号段：<input
						type="text" id="add_phoneRound" name="phoneRound" /><font
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				<li style="line-height: 40px; height: 40px;">状态信息：<select 
						id="add_status" style="width: 155px;" name="status">
						<option value="">请选择</option>
						<option value="有效">有效</option>
						<option value="无效">无效</option></select><font 
						color="red">&nbsp;&nbsp;*&nbsp;&nbsp;必填</font></li>
				</ul>
			</div>
		</form>
		<div align="center">
			<input type="button" value="添加" id="addPhoneRound" />
		</div>
	</div>	
</body>
</html>

